/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.flink.process;

import com.navercorp.pinpoint.common.server.bo.stat.join.JoinAgentStatBo;
import com.navercorp.pinpoint.common.server.bo.stat.join.JoinApplicationStatBo;
import com.navercorp.pinpoint.flink.Bootstrap;
import com.navercorp.pinpoint.flink.function.ApplicationStatBoWindow;
import com.navercorp.pinpoint.flink.mapper.thrift.stat.JoinAgentStatBoMapper;
import com.navercorp.pinpoint.flink.mapper.thrift.stat.SpanChunk2SpanMapper;
import com.navercorp.pinpoint.flink.vo.ApplicationStatus4Neo4j;
import com.navercorp.pinpoint.flink.vo.RawData;
import com.navercorp.pinpoint.thrift.dto.flink.TFAgentStatBatch;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpan;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpanChunk;
import com.tk.neo4j.domain.node.ServerInfo;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;
import org.apache.thrift.TBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author minwoo.jung
 */
public class TBase4SpanFlatMapper extends RichFlatMapFunction<RawData, Tuple3<String, TFSpan, Long>> {
    private final static List<Tuple3<String, TFSpan, Long>> EMPTY_LIST = Collections.emptyList();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private transient SpanChunk2SpanMapper spanChunk2SpanMapper;
    private transient ApplicationCache applicationCache;
//    public final static MapStateDescriptor<Long, TFSpan> infoState = new MapStateDescriptor<>(
//            "TFSpanState",
//            Long.class,
//            TFSpan.class);

    public TBase4SpanFlatMapper() {
    }

    public TBase4SpanFlatMapper(SpanChunk2SpanMapper spanChunk2SpanMapper, ApplicationCache applicationCache) {
        this.spanChunk2SpanMapper = spanChunk2SpanMapper;
        this.applicationCache = applicationCache;
    }

    public void open(Configuration parameters) throws Exception {
        this.spanChunk2SpanMapper = new SpanChunk2SpanMapper();
        Bootstrap bootstrap = Bootstrap.getInstance();
        applicationCache = bootstrap.getApplicationCache();
    }

    @Override
    public void flatMap(RawData rawData, Collector<Tuple3<String, TFSpan, Long>> out) throws Exception {
        final Object data = rawData.getData();
        if (!(data instanceof TBase)) {
            logger.error("data is not TBase type {}", data);
            return;
        }

        TBase tBase = (TBase) data;


        try {
            List<Tuple3<String, TFSpan, Long>> outData = serverRequestFlatMap(tBase);
            if (outData.size() == 0) {
                return;
            }
            for (Tuple3<String, TFSpan, Long> tuple : outData) {
                out.collect(tuple);
            }
        } finally {
        }
    }

    private List<Tuple3<String, TFSpan, Long>> serverRequestFlatMap(TBase tBase) throws Exception {
        List<Tuple3<String, TFSpan, Long>> outData = new ArrayList<>(5);
        if (tBase instanceof TFSpan) {
            if (logger.isDebugEnabled()) {
                logger.debug("raw data : {}", tBase);
            }
            final TFSpan tfSpan = (TFSpan) tBase;
            try {

                if (tfSpan.getAgentId() == null) {
                    return EMPTY_LIST;
                }
            } catch (Exception e) {
                logger.error("can't create tfSpan object {}", tfSpan, e);
                return EMPTY_LIST;
            }

            outData.add(new Tuple3<String, TFSpan, Long>(tfSpan.getApplicationName(), tfSpan, tfSpan.getStartTime()));
        } else if (tBase instanceof TFSpanChunk) {
            if (logger.isDebugEnabled()) {
                logger.debug("raw data : {}", tBase);
            }
            final TFSpanChunk tfSpanChunk = (TFSpanChunk) tBase;
            final TFSpan tfSpan;
            try {
                tfSpan = spanChunk2SpanMapper.map(tfSpanChunk);

                if (tfSpan.getAgentId() == null) {
                    return EMPTY_LIST;
                }
            } catch (Exception e) {
                logger.error("can't create tfSpan object {}", tfSpanChunk, e);
                return EMPTY_LIST;
            }
            outData.add(new Tuple3<String, TFSpan, Long>(tfSpan.getApplicationName(), tfSpan, tfSpan.getStartTime()));
        }
        return outData;
    }

}