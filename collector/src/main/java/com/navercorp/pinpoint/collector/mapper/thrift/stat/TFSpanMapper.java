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

import com.navercorp.pinpoint.common.server.bo.AnnotationBo;
import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanEventBo;
import com.navercorp.pinpoint.common.server.bo.stat.ActiveTraceBo;
import com.navercorp.pinpoint.common.server.bo.stat.ActiveTraceHistogram;
import com.navercorp.pinpoint.common.util.TransactionId;
import com.navercorp.pinpoint.profiler.context.Annotation;
import com.navercorp.pinpoint.thrift.dto.flink.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author minwoo.jung
 */
public class TFSpanMapper {

    public TFSpan map(SpanBo spanBo) {
        TFSpan tfSpan = new TFSpan();
        tfSpan.setAcceptorHost(spanBo.getAcceptorHost());
        tfSpan.setAgentId(spanBo.getAgentId());
        tfSpan.setAgentStartTime(spanBo.getAgentStartTime());
//        List<TFAnnotation> tfAnnotationList = new ArrayList<>();
//        if (spanBo.getAnnotationBoList() != null) {
//            for (AnnotationBo tmp : spanBo.getAnnotationBoList()) {
//                TFAnnotation tfAnnotation = new TFAnnotation();
//                tfAnnotation.setKey(tmp.getKey());
//                Object value = tmp.getValue();
//                tfAnnotation.setValue();
//                tfAnnotationList.add(tfAnnotation);
//            }
//            tfSpan.setAnnotations(tfAnnotationList);
//        }

        tfSpan.setAcceptorHost(spanBo.getAcceptorHost());
        tfSpan.setApiId(spanBo.getApiId());
        tfSpan.setApplicationName(spanBo.getApplicationId());
        tfSpan.setApplicationServiceType(spanBo.getApplicationServiceType());
        tfSpan.setElapsed(spanBo.getElapsed());
        tfSpan.setEndPoint(spanBo.getEndPoint());
        tfSpan.setErr(spanBo.getErrCode());
//        tfSpan.setExceptionInfo(spanBo.getExceptionMessage());
        tfSpan.setFlag(spanBo.getFlag());
        tfSpan.setLoggingTransactionInfo(spanBo.getLoggingTransactionInfo());
        tfSpan.setParentApplicationType(spanBo.getParentApplicationServiceType());
        tfSpan.setParentSpanId(spanBo.getParentSpanId());
        tfSpan.setRemoteAddr(spanBo.getRemoteAddr());
        tfSpan.setRpc(spanBo.getRpc());
        tfSpan.setServiceType(spanBo.getServiceType());
        if (spanBo.getSpanEventBoList() != null) {
            List<TFSpanEvent> list = new ArrayList<>();
            for (SpanEventBo tmp : spanBo.getSpanEventBoList()) {
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
            tfSpan.setSpanEventList(list);
        }

        tfSpan.setSpanId(spanBo.getSpanId());
        tfSpan.setStartTime(spanBo.getStartTime());
        tfSpan.setVersion(spanBo.getRawVersion());
        return tfSpan;
    }
}
