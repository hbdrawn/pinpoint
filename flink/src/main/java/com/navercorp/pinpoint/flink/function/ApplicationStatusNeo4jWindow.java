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

import com.navercorp.pinpoint.common.server.bo.stat.join.JoinApplicationStatBo;
import com.navercorp.pinpoint.common.server.bo.stat.join.JoinStatBo;
import com.navercorp.pinpoint.flink.Bootstrap;
import com.navercorp.pinpoint.flink.vo.ApplicationStatus4Neo4j;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author minwoo.jung
 */
public class ApplicationStatusNeo4jWindow extends RichWindowFunction<Tuple3<String, JoinStatBo, Long>, String, Tuple, TimeWindow> {
    public static final int WINDOW_SIZE = 10000;
    public static final int ALLOWED_LATENESS = 45000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private transient ApplicationStatBoWindowInterceptor applicationStatBoWindowInterceptor;

    MapState<String, ApplicationStatus4Neo4j> mapState;

    @Override
    public void open(Configuration parameters) throws Exception {
        applicationStatBoWindowInterceptor = Bootstrap.getInstance().getApplicationStatBoWindowInterceptor();
        MapStateDescriptor<String, ApplicationStatus4Neo4j> infoState = new MapStateDescriptor<>(
                "infoState",
                String.class,
                ApplicationStatus4Neo4j.class);
        StateTtlConfig stateTtlConfig = StateTtlConfig.newBuilder(Time.minutes(1)).
                setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite).
                setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired).build();
        infoState.enableTimeToLive(stateTtlConfig);
        mapState = getRuntimeContext().getMapState(infoState);
    }

    @Override
    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple3<String, JoinStatBo, Long>> values, Collector<String> out) throws Exception {
        String tupleKey = (String) tuple.getField(0);

        try {
            ApplicationStatus4Neo4j f1 = (ApplicationStatus4Neo4j) values.iterator().next().f1;
            //获得最大时间的status即可
            for (Tuple3<String, JoinStatBo, Long> value : values) {
                ApplicationStatus4Neo4j tmp = (ApplicationStatus4Neo4j) value.f1;
                if (tmp.isCreate()) {
                    f1 = tmp;
                    break;
                } else if (tmp.getLastTime().getTime() > f1.getLastTime().getTime()) {
                    f1 = tmp;
                }
            }
            ApplicationStatus4Neo4j applicationStatus4Neo4j = mapState.get(tupleKey);
            if (applicationStatus4Neo4j == null) {
                f1.setFirstTime(new Date());
                f1.setCreate(true);
                mapState.put(tupleKey, f1);
            } else {
                applicationStatus4Neo4j.setCreate(false);
                if(f1.getLastTime().getTime() > applicationStatus4Neo4j.getLastTime().getTime()){
                    applicationStatus4Neo4j.setLastTime(f1.getLastTime());
                }
            }

            ApplicationStatus4Neo4j status4Neo4j = mapState.get(tupleKey);
            //创建neo4j语句
            StringBuilder sb = new StringBuilder();
            if (status4Neo4j.isCreate()) {
                sb.append("create(n:TestServerNode:" + status4Neo4j.getApplicationId());
                sb.append("{");
                sb.append("'agentId':'" + status4Neo4j.getAgentId() + "'");
                sb.append(",'首次更新时间':'" + status4Neo4j.getFirstTime() + "'");
                sb.append(",'最新更新时间':'" + status4Neo4j.getLastTime() + "'");
                sb.append("});");
            } else {
                sb.append("match(n:TestServerNode:" + status4Neo4j.getApplicationId() + ") set n.最新更新时间='" + status4Neo4j.getLastTime() + "';");
            }
            logger.info("======={}", sb.toString());
            out.collect(sb.toString());
        } catch (Exception e) {
            logger.error("window function error", e);
        } finally {
            applicationStatBoWindowInterceptor.after();
        }
    }

    private JoinApplicationStatBo join(Iterable<Tuple3<String, JoinStatBo, Long>> values) {
        List<JoinApplicationStatBo> joinApplicaitonStatBoList = new ArrayList<JoinApplicationStatBo>();

        for (Tuple3<String, JoinStatBo, Long> value : values) {
            joinApplicaitonStatBoList.add((JoinApplicationStatBo) value.f1);
        }

        return JoinApplicationStatBo.joinApplicationStatBoByTimeSlice(joinApplicaitonStatBoList);

    }
}