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
package com.navercorp.pinpoint.collector.mapper.thrift.stat;

import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanChunkBo;
import com.navercorp.pinpoint.common.server.bo.SpanEventBo;
import com.navercorp.pinpoint.profiler.context.SpanChunk;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpan;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpanChunk;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpanEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author minwoo.jung
 */
public class TFSpanChunkMapper {

    public TFSpanChunk map(SpanChunkBo spanChunkBo) {
        TFSpanChunk tfSpanChunk = new TFSpanChunk();
        tfSpanChunk.setAgentId(spanChunkBo.getAgentId());
        tfSpanChunk.setAgentStartTime(spanChunkBo.getAgentStartTime());
        tfSpanChunk.setApplicationName(spanChunkBo.getApplicationId());
        tfSpanChunk.setApplicationServiceType(spanChunkBo.getApplicationServiceType());
        tfSpanChunk.setEndPoint(spanChunkBo.getEndPoint());
        tfSpanChunk.setServiceType(spanChunkBo.getServiceType());
        if (spanChunkBo.getSpanEventBoList() != null) {
            List<TFSpanEvent> list = new ArrayList<>();
            for (SpanEventBo tmp : spanChunkBo.getSpanEventBoList()) {
                TFSpanEvent event = new TFSpanEvent();
                event.setApiId(tmp.getApiId());
                event.setAsyncId(tmp.getAsyncId());
                event.setEndElapsed(tmp.getEndElapsed());
                event.setEndPoint(tmp.getEndPoint());
                event.setNextAsyncId(tmp.getNextAsyncId());
                event.setNextSpanId(tmp.getNextSpanId());
                event.setSequence(tmp.getSequence());
                event.setServiceType(tmp.getServiceType());
                event.setStartElapsed(tmp.getStartElapsed());
                list.add(event);
            }
            tfSpanChunk.setSpanEventList(list);
        }

        tfSpanChunk.setSpanId(spanChunkBo.getSpanId());
        tfSpanChunk.setVersion(spanChunkBo.getVersion());
        return tfSpanChunk;
    }
}
