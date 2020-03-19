package com.tk.convert;

import com.navercorp.pinpoint.common.server.bo.SpanBo;
import com.tk.neo4j.domain.node.ServerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpanBoConvertMapper {

    SpanBoConvertMapper INSTANCE = Mappers.getMapper(SpanBoConvertMapper.class);

    ServerInfo convert(SpanBo spanBo);
}
