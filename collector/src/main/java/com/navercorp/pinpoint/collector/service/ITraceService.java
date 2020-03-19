package com.navercorp.pinpoint.collector.service;

import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.common.server.bo.SpanChunkBo;

public interface ITraceService {

    public void insertSpanChunk(final SpanChunkBo spanChunkBo);

    public void insertSpan(final SpanBo spanBo);
}
