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

package com.navercorp.pinpoint.flink.mapper.thrift.stat;

import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanEventBo;
import com.navercorp.pinpoint.common.server.bo.stat.join.*;
import com.navercorp.pinpoint.flink.mapper.thrift.ThriftBoMapper;
import com.navercorp.pinpoint.thrift.dto.flink.TFAgentStat;
import com.navercorp.pinpoint.thrift.dto.flink.TFAgentStatBatch;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpan;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpanEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author minwoo.jung
 */
public class SpanBoMapper implements ThriftBoMapper<SpanBo, TFSpan> {

    @Override
    public SpanBo map(TFSpan thriftObject) {
        SpanBo tfSpan = new SpanBo();
        tfSpan.setAcceptorHost(thriftObject.getAcceptorHost());
        tfSpan.setAgentId(thriftObject.getAgentId());
        tfSpan.setAgentStartTime(thriftObject.getAgentStartTime());
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

        tfSpan.setAcceptorHost(thriftObject.getAcceptorHost());
        tfSpan.setApiId(thriftObject.getApiId());
        tfSpan.setApplicationId(thriftObject.getApplicationName());
        tfSpan.setApplicationServiceType(thriftObject.getApplicationServiceType());
        tfSpan.setElapsed(thriftObject.getElapsed());
        tfSpan.setEndPoint(thriftObject.getEndPoint());
        tfSpan.setErrCode(thriftObject.getErr());
//        tfSpan.setExceptionInfo(spanBo.getExceptionMessage());
        tfSpan.setFlag(thriftObject.getFlag());
        tfSpan.setLoggingTransactionInfo(thriftObject.getLoggingTransactionInfo());
        tfSpan.setParentApplicationServiceType(thriftObject.getParentApplicationType());
        tfSpan.setParentSpanId(thriftObject.getParentSpanId());
        tfSpan.setRemoteAddr(thriftObject.getRemoteAddr());
        tfSpan.setRpc(thriftObject.getRpc());
        tfSpan.setServiceType(thriftObject.getServiceType());
        if (thriftObject.getSpanEventList() != null) {
            List<SpanEventBo> list = new ArrayList<>();
            for (TFSpanEvent tmp : thriftObject.getSpanEventList()) {
                SpanEventBo event = new SpanEventBo();
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
            tfSpan.addSpanEventBoList(list);
        }

        tfSpan.setSpanId(thriftObject.getSpanId());
        tfSpan.setStartTime(thriftObject.getStartTime());
        tfSpan.setVersion(thriftObject.getVersion());
        return tfSpan;
    }
}
