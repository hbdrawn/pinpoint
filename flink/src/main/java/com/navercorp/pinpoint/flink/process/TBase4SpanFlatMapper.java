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

import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.stat.join.JoinAgentStatBo;
import com.navercorp.pinpoint.common.server.bo.stat.join.JoinApplicationStatBo;
import com.navercorp.pinpoint.flink.Bootstrap;
import com.navercorp.pinpoint.flink.function.ApplicationStatBoWindow;
import com.navercorp.pinpoint.flink.mapper.thrift.stat.JoinAgentStatBoMapper;
import com.navercorp.pinpoint.flink.mapper.thrift.stat.SpanBoMapper;
import com.navercorp.pinpoint.flink.vo.ApplicationStatus4Neo4j;
import com.navercorp.pinpoint.flink.vo.RawData;
import com.navercorp.pinpoint.thrift.dto.flink.TFAgentStatBatch;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpan;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
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
public class TBase4SpanFlatMapper extends RichFlatMapFunction<RawData, Tuple3<String, SpanBo, Long>> {
    private final static List<Tuple3<String, SpanBo, Long>> EMPTY_LIST = Collections.emptyList();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private transient SpanBoMapper spanBoMapper;
    private transient ApplicationCache applicationCache;


    public TBase4SpanFlatMapper() {
    }

    public TBase4SpanFlatMapper(SpanBoMapper spanBoMapper, ApplicationCache applicationCache) {
        this.spanBoMapper = spanBoMapper;
        this.applicationCache = applicationCache;
    }

    public void open(Configuration parameters) throws Exception {
        this.spanBoMapper = new SpanBoMapper();
        Bootstrap bootstrap = Bootstrap.getInstance();
        applicationCache = bootstrap.getApplicationCache();
    }

    @Override
    public void flatMap(RawData rawData, Collector<Tuple3<String, SpanBo, Long>> out) throws Exception {
        final Object data = rawData.getData();
        if (!(data instanceof TBase)) {
            logger.error("data is not TBase type {}", data);
            return;
        }

        TBase tBase = (TBase) data;


        try {
            List<Tuple3<String, SpanBo, Long>> outData = serverRequestFlatMap(tBase);
            if (outData.size() == 0) {
                return;
            }
            for (Tuple3<String, SpanBo, Long> tuple : outData) {
                out.collect(tuple);
            }
        } finally {
        }
    }

    private List<Tuple3<String, SpanBo, Long>> serverRequestFlatMap(TBase tBase) throws Exception {
        List<Tuple3<String, SpanBo, Long>> outData = new ArrayList<>(5);
        if (tBase instanceof TFSpan) {
            if (logger.isDebugEnabled()) {
                logger.debug("raw data : {}", tBase);
            }
            final TFSpan tfSpan = (TFSpan) tBase;
            final SpanBo spanBo;
            try {
                spanBo = spanBoMapper.map(tfSpan);

                if (spanBo.getAgentId() == null) {
                    return EMPTY_LIST;
                }
            } catch (Exception e) {
                logger.error("can't create tfSpan object {}", tfSpan, e);
                return EMPTY_LIST;
            }

            outData.add(new Tuple3<String, SpanBo, Long>(spanBo.getApplicationId(), spanBo, spanBo.getStartTime()));
        }
        return outData;
    }

}