package com.navercorp.pinpoint.flink.mapper.thrift.stat;

import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.navercorp.pinpoint.flink.mapper.thrift.ThriftBoMapper;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpan;
import com.navercorp.pinpoint.thrift.dto.flink.TFSpanChunk;
import com.tk.convert.SpanBoConvertMapper;

/**
 * 将TFSpanChunk转换为TFSpan
 */
public class SpanChunk2SpanMapper implements ThriftBoMapper<TFSpan, TFSpanChunk> {

    @Override
    public TFSpan map(TFSpanChunk thriftObject) {
        TFSpan convert = SpanBoConvertMapper.INSTANCE.convert(thriftObject);
        return convert;
    }
}
