/*
 * Copyright 2017 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.flink.function;

import com.navercorp.pinpoint.common.server.bo.SpanEventBo;
import com.navercorp.pinpoint.common.server.bo.stat.join.JoinApplicationStatBo;
import com.navercorp.pinpoint.common.trace.ServiceTypeCategory;
import com.navercorp.pinpoint.flink.Bootstrap;
import com.navercorp.pinpoint.flink.process.TBase4SpanFlatMapper;
import com.navercorp.pinpoint.flink.vo.ApplicationStatus4Neo4j;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpan;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpanEvent;
import com.tk.convert.SpanBoConvertMapper;
import com.tk.neo4j.domain.node.ServerInfo;
import com.tk.neo4j.domain.relationship.RelationShip;
import org.apache.commons.collections.map.HashedMap;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.windowing.RichAllWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author minwoo.jung
 */
public class ApplicationTFSpanWindow extends RichAllWindowFunction<Tuple3<String, TFSpan, Long>, Tuple2<List<ServerInfo>, List<RelationShip>>, TimeWindow> {
    public static final int WINDOW_SIZE = 10000;
    public static final int ALLOWED_LATENESS = 10000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    MapState<String, ServerInfo> mapState;
    MapState<String, RelationShip> mapState4RelationShip;
    //<spanId,applicationId>
    MapState<Long, String> mapState4SpanCache;


    @Override
    public void open(Configuration parameters) throws Exception {
        MapStateDescriptor<String, ServerInfo> infoState = new MapStateDescriptor<>(
                "serverInfoState",
                String.class,
                ServerInfo.class);
        StateTtlConfig stateTtlConfig = StateTtlConfig.newBuilder(Time.minutes(10)).
                setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite).
                setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired).build();
        infoState.enableTimeToLive(stateTtlConfig);
        mapState = getRuntimeContext().getMapState(infoState);

        MapStateDescriptor<String, RelationShip> infoState4RelationShip = new MapStateDescriptor<>(
                "relationShipState",
                String.class,
                RelationShip.class);
        infoState4RelationShip.enableTimeToLive(stateTtlConfig);
        mapState4RelationShip = getRuntimeContext().getMapState(infoState4RelationShip);

        MapStateDescriptor<Long, String> mapState4SpanCacheDes = new MapStateDescriptor<>(
                "spanCache",
                Long.class,
                String.class);
        mapState4SpanCacheDes.enableTimeToLive(stateTtlConfig);
        mapState4SpanCache = getRuntimeContext().getMapState(mapState4SpanCacheDes);
    }


    @Override
    public void apply(TimeWindow window, Iterable<Tuple3<String, TFSpan, Long>> values, Collector<Tuple2<List<ServerInfo>, List<RelationShip>>> out) throws Exception {
        try {
            //1. 将相同的applicationId进行聚合，并存储->结果是{<applicationId,ServerInfo>}
            List<ServerInfo> serverInfoList = new ArrayList<>();
            List<RelationShip> relationShipList = new ArrayList<>();
//            Map<Long, Long> relationMap = new HashMap<>();
            //对状态进行缓存
            for (Tuple3<String, TFSpan, Long> tmp : values) {
                //生成relationShip
                mapState4SpanCache.put(tmp.f1.getSpanId(), tmp.f0);
            }
            for (Tuple3<String, TFSpan, Long> tmp : values) {
                ServerInfo serverInfo = mapState.get(tmp.f0);
                if (serverInfo == null) {
                    mapState.put(tmp.f0, SpanBoConvertMapper.INSTANCE.convert(tmp.f1));
                } else {
                    serverInfo.setInvokeTimes(serverInfo.getInvokeTimes() + 1);
                    if (!serverInfo.getEndPoint().equals(tmp.f1.getEndPoint())) {
                        serverInfo.setEndPoint(serverInfo.getEndPoint() + ";" + tmp.f1.getEndPoint());
                    }
                    if (!serverInfo.getAgentId().equals(tmp.f1.getAgentId())) {
                        serverInfo.setAgentId(serverInfo.getAgentId() + ";" + tmp.f1.getAgentId());
                    }
                    if (serverInfo.getStartTime() < tmp.f1.getStartTime()) {
                        ServerInfo convert = SpanBoConvertMapper.INSTANCE.convert(tmp.f1);
                        convert.setInvokeTimes(serverInfo.getInvokeTimes());
                        convert.setAgentId(serverInfo.getAgentId());
                        convert.setEndPoint(serverInfo.getEndPoint());
                        mapState.put(tmp.f0, convert);
                    }

                }
                serverInfoList.add(mapState.get(tmp.f0));

                if (tmp.f1.getParentSpanId() != -1) {
                    createRelationShip(tmp.f0, tmp.f1.getParentSpanId(), relationShipList, "invoke");
                }


                //处理spanEvent TODO
                List<TFSpanEvent> spanEventBoList = tmp.f1.getSpanEventList();
                for (TFSpanEvent tEvent : spanEventBoList) {
                    short serviceType = tEvent.getServiceType();
                    ServiceTypeCategory category = ServiceTypeCategory.findCategory(serviceType);
                    if (category == ServiceTypeCategory.RPC || category == ServiceTypeCategory.LIBRARY
                            || category == ServiceTypeCategory.PINPOINT_INTERNAL
                            || category == ServiceTypeCategory.SERVER) {
                        if(tEvent.getEndPoint() == null){
                            continue;
                        }
                    }
                    String applicationName = serviceType / 100 + "";
                    ServerInfo middleServer = mapState.get(applicationName);
                    if (middleServer == null) {
                        middleServer = new ServerInfo();
                        middleServer.setInvokeTimes(1);
                        middleServer.setStartTime(tmp.f1.getStartTime());
                        middleServer.setApplicationName(applicationName);
                        middleServer.setServiceType(serviceType);
                        middleServer.setElapsed(tEvent.getEndElapsed() - tEvent.getStartElapsed());
                        middleServer.setEndPoint(tEvent.getEndPoint());
                        if (tEvent.getExceptionInfo() != null) {
                            middleServer.setExceptionMessage(tEvent.getExceptionInfo().getStringValue());
                        }

                        middleServer.setParentSpanId(tmp.f1.getSpanId());
                        mapState.put(applicationName, middleServer);
                    } else {
                        middleServer.setInvokeTimes(middleServer.getInvokeTimes() + 1);
                        if (middleServer.getEndPoint() == null) {
                            middleServer.setEndPoint(tEvent.getEndPoint());
                        } else if (middleServer.getEndPoint() != null && !middleServer.getEndPoint().equals(tEvent.getEndPoint())) {
                            middleServer.setEndPoint(middleServer.getEndPoint() + ";" + tEvent.getEndPoint());
                        }
                    }
                    serverInfoList.add(middleServer);
                    createRelationShip(applicationName, tmp.f1.getSpanId(), relationShipList, "middleWare");
                }


            }
            //2. 将聚合后的serverInfo与和relationship与mapstate进行进行比对，产生create和update状态结果
            if (!serverInfoList.isEmpty()) {
                Bootstrap.getInstance().getServerInfoRepository().saveServerInfo(serverInfoList);
            }
            if (!relationShipList.isEmpty()) {
                Bootstrap.getInstance().getRelationShipRepository().saveRelations(relationShipList);
            }

            //3. 将最终的信息更新到neo4j图数据库进行展示 TODO

            ArrayList<RelationShip> relationShips = new ArrayList<>();
            Tuple2 resultTuple = new Tuple2<>(serverInfoList, relationShips);
            out.collect(resultTuple);
        } catch (Exception e) {
            logger.error("window function error", e);
        } finally {
        }
    }

    public void createRelationShip(String applicationId, Long parentSpanId, List<RelationShip> relationShipList, String invokeType) throws Exception {
        String applicationIdParent = mapState4SpanCache.get(parentSpanId);
        RelationShip relationShip = new RelationShip();
        relationShip.setRelationship(applicationIdParent + "_" + applicationId);
        RelationShip relationShip1 = mapState4RelationShip.get(relationShip.getRelationship());
        if (relationShip1 == null) {
            ServerInfo serverInfoChild = mapState.get(applicationId);
            ServerInfo serverInfoParent = mapState.get(applicationIdParent);
            if (serverInfoParent == null) {
                serverInfoParent = new ServerInfo();
                serverInfoParent.setApplicationName(applicationIdParent);
            }
            relationShip.setStartNode(serverInfoParent);
            relationShip.setEndNode(serverInfoChild);
            relationShip.setType(invokeType);
            relationShip.setInvokeTimes(1L);
            mapState4RelationShip.put(relationShip.getRelationship(), relationShip);
        } else {
            relationShip1.setInvokeTimes(relationShip1.getInvokeTimes() + 1);
        }
        relationShipList.add(mapState4RelationShip.get(relationShip.getRelationship()));
    }
}