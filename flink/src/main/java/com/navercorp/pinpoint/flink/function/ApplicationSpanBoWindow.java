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

import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.stat.join.JoinApplicationStatBo;
import com.navercorp.pinpoint.flink.Bootstrap;
import com.navercorp.pinpoint.flink.vo.ApplicationStatus4Neo4j;
import com.tk.convert.SpanBoConvertMapper;
import com.tk.neo4j.domain.node.ServerInfo;
import com.tk.neo4j.domain.relationship.RelationShip;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.windowing.RichProcessAllWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author minwoo.jung
 */
public class ApplicationSpanBoWindow extends RichProcessAllWindowFunction<Tuple3<String, SpanBo, Long>, Tuple2<ServerInfo, List<RelationShip>>, Tuple, TimeWindow> {
    public static final int WINDOW_SIZE = 10000;
    public static final int ALLOWED_LATENESS = 10000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    MapState<String, ServerInfo> mapState;
    MapState<String, RelationShip> mapState4RelationShip;


    @Override
    public void open(Configuration parameters) throws Exception {
        MapStateDescriptor<String, ServerInfo> infoState = new MapStateDescriptor<>(
                "serverInfoState",
                String.class,
                ServerInfo.class);
        StateTtlConfig stateTtlConfig = StateTtlConfig.newBuilder(Time.days(1)).
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
    }

    @Override
    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple3<String, SpanBo, Long>> values, Collector<Tuple2<ServerInfo, List<RelationShip>>> out) throws Exception {
        String tupleKey = (String) tuple.getField(0);
        try {

            //将此时间段内与当前节点有关系的所有的信息进行聚合计算，最终生成节点的最新信息和与此节点相关的所有的关系
            boolean isCreate = false;
            Map<String, Boolean> cachedRelationShip = new HashMap<>();
            for (Tuple3<String, SpanBo, Long> tmp : values) {
                ServerInfo serverInfo = mapState.get(tmp.f0);
                if (serverInfo == null) {
                    serverInfo = SpanBoConvertMapper.INSTANCE.convert(tmp.f1);
                    isCreate = true;
                    mapState.put(tmp.f0, serverInfo);
                } else {
                    if (serverInfo.getParentSpanId() != -1) {
                        //有调用关系，需要处理
                        serverInfo.setInvokeTimes(serverInfo.getInvokeTimes() + 1);
                        

                    }
                }


            }

            //将最终的信息更新到neo4j图数据库进行展示

            ArrayList<RelationShip> relationShips = new ArrayList<>();
            Tuple2 resultTuple = (new Tuple2<>(new ServerInfo(), relationShips));
            out.collect(resultTuple);
        } catch (Exception e) {
            logger.error("window function error", e);
        } finally {
        }
    }


}