namespace java com.navercorp.pinpoint.thrift.dto.flink

enum TFJvmGcType {
    UNKNOWN,
    SERIAL,
    PARALLEL,
    CMS,
    G1
}

struct TFServiceInfo {
    1: optional string          serviceName
    2: optional list<string>    serviceLibs
}

struct TFServerMetaData {
    1: optional string              serverInfo
    2: optional list<string>        vmArgs
    10: optional list<TFServiceInfo>  serviceInfos
}

struct TFJvmInfo {
    1:          i16         version = 0
    2: optional string      vmVersion
    3: optional TFJvmGcType  gcType = TFJvmGcType.UNKNOWN
}

struct TFAgentInfo {
	1: string	hostname
	2: string	ip
	3: string	ports
	4: string	agentId
	5: string	applicationName
	6: i16	    serviceType
	7: i32      pid
	8: string   agentVersion;
	9: string   vmVersion;

	10: i64	    startTimestamp

	11: optional i64     endTimestamp
	12: optional i32     endStatus

	20: optional TFServerMetaData   serverMetaData

	30: optional TFJvmInfo   jvmInfo

    40: optional bool container = false
}

struct TFJvmGc {
    1: TFJvmGcType   type = TFJvmGcType.UNKNOWN
    2: i64          jvmMemoryHeapUsed
    3: i64          jvmMemoryHeapMax
    4: i64          jvmMemoryNonHeapUsed
    5: i64          jvmMemoryNonHeapMax
    6: i64          jvmGcOldCount
    7: i64          jvmGcOldTime
    8: optional TFJvmGcDetailed    jvmGcDetailed
}

struct TFDirectBuffer {
    1: optional i64 directCount
    2: optional i64 directMemoryUsed
    3: optional i64 mappedCount
    4: optional i64 mappedMemoryUsed
}

struct TFJvmGcDetailed {
    1: optional i64 jvmGcNewCount
    2: optional i64 jvmGcNewTime
    3: optional double jvmPoolCodeCacheUsed
    4: optional double jvmPoolNewGenUsed
    5: optional double jvmPoolOldGenUsed
    6: optional double jvmPoolSurvivorSpaceUsed
    7: optional double jvmPoolPermGenUsed
    8: optional double jvmPoolMetaspaceUsed
}

struct TFCpuLoad {
    1: optional double       jvmCpuLoad
    2: optional double       systemCpuLoad
}

struct TFTransaction {
    2: optional i64     sampledNewCount
    3: optional i64     sampledContinuationCount
    4: optional i64     unsampledNewCount
    5: optional i64     unsampledContinuationCount
    6: optional i64     skippedNewCount
    7: optional i64     skippedContinuationCount
}

struct TFActiveTraceHistogram {
    1:          i16         version = 0
	2: optional i32         histogramSchemaType
	3: optional list<i32>   activeTraceCount
}

struct TFActiveTrace {
	1: optional TFActiveTraceHistogram   histogram
}

struct TFResponseTime {
    1: optional i64         avg = 0
}

struct TFAgentStat {
    1: optional string      agentId
    2: optional i64         startTimestamp
    3: optional i64         timestamp
    4: optional i64         collectInterval
    10: optional TFJvmGc     gc
    20: optional TFCpuLoad   cpuLoad
    30: optional TFTransaction   transaction
    40: optional TFActiveTrace   activeTrace
    50: optional TFDataSourceList dataSourceList
    60: optional TFResponseTime responseTime
    80: optional TFFileDescriptor fileDescriptor
    90: optional TFDirectBuffer directBuffer
    200: optional string    metadata
}

struct TFAgentStatBatch {
    1: string                   agentId
    2: i64                      startTimestamp
    10: list<TFAgentStat>        agentStats
}

struct TFDataSource {
    1: i32                      id
    2: optional i16             serviceTypeCode
    3: optional string          databaseName
    4: optional string          url
    5: optional i32             activeConnectionSize = 0
    6: optional i32             maxConnectionSize
}

struct TFDataSourceList {
    1: list<TFDataSource> dataSourceList
}

struct TFFileDescriptor {
    1: i64                      openFileDescriptorCount
}



const i8 TRACE_V1 = 0;
const i8 TRACE_V2 = 1;

struct TFIntStringValue {
     1: i32 intValue;
     2: optional string stringValue;
}

struct TFIntStringStringValue {
    1: i32 intValue;
    2: optional string stringValue1;
    3: optional string stringValue2;
}

struct TFLongIntIntByteByteStringValue {
    1: i64 longValue;
    2: i32 intValue1;
    3: optional i32 intValue2;
    4: optional i8 byteValue1;
    5: optional i8 byteValue2;
    6: optional string stringValue;
}

struct TFIntBooleanIntBooleanValue {
    1: i32 intValue1;
    2: bool boolValue1;
    3: i32 intValue2;
    4: bool boolValue2;
}

struct TFStringStringValue {
     1: string stringValue1;
     2: optional string stringValue2;
}

struct TFSpanEvent {

    7: optional i64 spanId
    8: i16 sequence

    // 1.6.x- : delta of the span startTime
    // 1.7.0+: delta of startTime of previous SpanEvent
    //         If SpanEvent is the first SpanEvent, startElapsed is span startTime
    9: i32 startElapsed = 0;

    10: optional i32 endElapsed = 0

    11: optional string rpc  ( deprecated )
    12: i16 serviceType
    13: optional string endPoint

    14: optional list<TFAnnotation> annotations

    15: optional i32 depth = -1
    16: optional i64 nextSpanId = -1

    20: optional string destinationId

    25: optional i32 apiId;
    26: optional TFIntStringValue exceptionInfo;

    30: optional i32 asyncId;
    31: optional i32 nextAsyncId;
    32: optional i16 asyncSequence;
}

struct TFSpan {

    1: string agentId
    2: string applicationName
    3: i64 agentStartTime

    // identical to agentId if null
    //4: optional string traceAgentId
    //5: i64 traceAgentStartTime;
    //6: i64 traceTransactionSequence;
    4: binary  transactionId;

    7: i64 spanId
    8: optional i64 parentSpanId = -1

    // span event's startTimestamp
    9: i64 startTime
    10: optional i32 elapsed = 0

    11: optional string rpc

    12: i16 serviceType
    13: optional string endPoint
    14: optional string remoteAddr

    15: optional list<TFAnnotation> annotations
    16: optional i16 flag = 0

    17: optional i32 err

    18: optional list<TFSpanEvent> spanEventList

    19: optional string parentApplicationName
    20: optional i16 parentApplicationType
    21: optional string acceptorHost

    25: optional i32 apiId;
    26: optional TFIntStringValue exceptionInfo;

    30: optional i16 applicationServiceType;
    31: optional i8 loggingTransactionInfo;

    32: optional i8 version = TRACE_V2;
}

struct TFSpanChunk {
    1: string agentId
    2: string applicationName
    3: i64 agentStartTime

    // @deprecate (1.7.0)
    4: i16 serviceType ( deprecated )

    // identical to agentId if null
    //5: optional string traceAgentId
    //6: i64 traceAgentStartTime;
    //7: i64 traceTransactionSequence;
    5: binary  transactionId;

    8: i64 spanId

    9: optional string endPoint

    10: list<TFSpanEvent> spanEventList

    11: optional i16 applicationServiceType

    // @since 1.7.0 time for data compression
    12: optional i64 keyTime;

    13: optional i8 version = TRACE_V2;
}


union TFAnnotationValue {
    1: string stringValue
    2: bool boolValue;
    3: i32 intValue;
    4: i64 longValue;
    5: i16 shortValue
    6: double doubleValue;
    7: binary binaryValue;
    8: i8 byteValue;
    9: TFIntStringValue intStringValue;
    10: TFIntStringStringValue intStringStringValue;
    11: TFLongIntIntByteByteStringValue longIntIntByteByteStringValue;
    12: TFIntBooleanIntBooleanValue intBooleanIntBooleanValue;
    13: TFStringStringValue stringStringValue;
}

struct TFAnnotation {
    1: i32 key,
    2: optional TFAnnotationValue value
}