/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.navercorp.pinpoint.thrift.dto.command;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2020-03-16")
public class TCmdActiveThreadLightDump implements org.apache.thrift.TBase<TCmdActiveThreadLightDump, TCmdActiveThreadLightDump._Fields>, java.io.Serializable, Cloneable, Comparable<TCmdActiveThreadLightDump> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TCmdActiveThreadLightDump");

  private static final org.apache.thrift.protocol.TField LIMIT_FIELD_DESC = new org.apache.thrift.protocol.TField("limit", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField THREAD_NAME_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("threadNameList", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField LOCAL_TRACE_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("localTraceIdList", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TCmdActiveThreadLightDumpStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TCmdActiveThreadLightDumpTupleSchemeFactory();

  private int limit; // optional
  private java.util.List<java.lang.String> threadNameList; // optional
  private java.util.List<java.lang.Long> localTraceIdList; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LIMIT((short)1, "limit"),
    THREAD_NAME_LIST((short)2, "threadNameList"),
    LOCAL_TRACE_ID_LIST((short)3, "localTraceIdList");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // LIMIT
          return LIMIT;
        case 2: // THREAD_NAME_LIST
          return THREAD_NAME_LIST;
        case 3: // LOCAL_TRACE_ID_LIST
          return LOCAL_TRACE_ID_LIST;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __LIMIT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.LIMIT,_Fields.THREAD_NAME_LIST,_Fields.LOCAL_TRACE_ID_LIST};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LIMIT, new org.apache.thrift.meta_data.FieldMetaData("limit", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.THREAD_NAME_LIST, new org.apache.thrift.meta_data.FieldMetaData("threadNameList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.LOCAL_TRACE_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("localTraceIdList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TCmdActiveThreadLightDump.class, metaDataMap);
  }

  public TCmdActiveThreadLightDump() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCmdActiveThreadLightDump(TCmdActiveThreadLightDump other) {
    __isset_bitfield = other.__isset_bitfield;
    this.limit = other.limit;
    if (other.isSetThreadNameList()) {
      java.util.List<java.lang.String> __this__threadNameList = new java.util.ArrayList<java.lang.String>(other.threadNameList);
      this.threadNameList = __this__threadNameList;
    }
    if (other.isSetLocalTraceIdList()) {
      java.util.List<java.lang.Long> __this__localTraceIdList = new java.util.ArrayList<java.lang.Long>(other.localTraceIdList);
      this.localTraceIdList = __this__localTraceIdList;
    }
  }

  public TCmdActiveThreadLightDump deepCopy() {
    return new TCmdActiveThreadLightDump(this);
  }

  @Override
  public void clear() {
    setLimitIsSet(false);
    this.limit = 0;
    this.threadNameList = null;
    this.localTraceIdList = null;
  }

  public int getLimit() {
    return this.limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
    setLimitIsSet(true);
  }

  public void unsetLimit() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LIMIT_ISSET_ID);
  }

  /** Returns true if field limit is set (has been assigned a value) and false otherwise */
  public boolean isSetLimit() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LIMIT_ISSET_ID);
  }

  public void setLimitIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LIMIT_ISSET_ID, value);
  }

  public int getThreadNameListSize() {
    return (this.threadNameList == null) ? 0 : this.threadNameList.size();
  }

  public java.util.Iterator<java.lang.String> getThreadNameListIterator() {
    return (this.threadNameList == null) ? null : this.threadNameList.iterator();
  }

  public void addToThreadNameList(java.lang.String elem) {
    if (this.threadNameList == null) {
      this.threadNameList = new java.util.ArrayList<java.lang.String>();
    }
    this.threadNameList.add(elem);
  }

  public java.util.List<java.lang.String> getThreadNameList() {
    return this.threadNameList;
  }

  public void setThreadNameList(java.util.List<java.lang.String> threadNameList) {
    this.threadNameList = threadNameList;
  }

  public void unsetThreadNameList() {
    this.threadNameList = null;
  }

  /** Returns true if field threadNameList is set (has been assigned a value) and false otherwise */
  public boolean isSetThreadNameList() {
    return this.threadNameList != null;
  }

  public void setThreadNameListIsSet(boolean value) {
    if (!value) {
      this.threadNameList = null;
    }
  }

  public int getLocalTraceIdListSize() {
    return (this.localTraceIdList == null) ? 0 : this.localTraceIdList.size();
  }

  public java.util.Iterator<java.lang.Long> getLocalTraceIdListIterator() {
    return (this.localTraceIdList == null) ? null : this.localTraceIdList.iterator();
  }

  public void addToLocalTraceIdList(long elem) {
    if (this.localTraceIdList == null) {
      this.localTraceIdList = new java.util.ArrayList<java.lang.Long>();
    }
    this.localTraceIdList.add(elem);
  }

  public java.util.List<java.lang.Long> getLocalTraceIdList() {
    return this.localTraceIdList;
  }

  public void setLocalTraceIdList(java.util.List<java.lang.Long> localTraceIdList) {
    this.localTraceIdList = localTraceIdList;
  }

  public void unsetLocalTraceIdList() {
    this.localTraceIdList = null;
  }

  /** Returns true if field localTraceIdList is set (has been assigned a value) and false otherwise */
  public boolean isSetLocalTraceIdList() {
    return this.localTraceIdList != null;
  }

  public void setLocalTraceIdListIsSet(boolean value) {
    if (!value) {
      this.localTraceIdList = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case LIMIT:
      if (value == null) {
        unsetLimit();
      } else {
        setLimit((java.lang.Integer)value);
      }
      break;

    case THREAD_NAME_LIST:
      if (value == null) {
        unsetThreadNameList();
      } else {
        setThreadNameList((java.util.List<java.lang.String>)value);
      }
      break;

    case LOCAL_TRACE_ID_LIST:
      if (value == null) {
        unsetLocalTraceIdList();
      } else {
        setLocalTraceIdList((java.util.List<java.lang.Long>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case LIMIT:
      return getLimit();

    case THREAD_NAME_LIST:
      return getThreadNameList();

    case LOCAL_TRACE_ID_LIST:
      return getLocalTraceIdList();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case LIMIT:
      return isSetLimit();
    case THREAD_NAME_LIST:
      return isSetThreadNameList();
    case LOCAL_TRACE_ID_LIST:
      return isSetLocalTraceIdList();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TCmdActiveThreadLightDump)
      return this.equals((TCmdActiveThreadLightDump)that);
    return false;
  }

  public boolean equals(TCmdActiveThreadLightDump that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_limit = true && this.isSetLimit();
    boolean that_present_limit = true && that.isSetLimit();
    if (this_present_limit || that_present_limit) {
      if (!(this_present_limit && that_present_limit))
        return false;
      if (this.limit != that.limit)
        return false;
    }

    boolean this_present_threadNameList = true && this.isSetThreadNameList();
    boolean that_present_threadNameList = true && that.isSetThreadNameList();
    if (this_present_threadNameList || that_present_threadNameList) {
      if (!(this_present_threadNameList && that_present_threadNameList))
        return false;
      if (!this.threadNameList.equals(that.threadNameList))
        return false;
    }

    boolean this_present_localTraceIdList = true && this.isSetLocalTraceIdList();
    boolean that_present_localTraceIdList = true && that.isSetLocalTraceIdList();
    if (this_present_localTraceIdList || that_present_localTraceIdList) {
      if (!(this_present_localTraceIdList && that_present_localTraceIdList))
        return false;
      if (!this.localTraceIdList.equals(that.localTraceIdList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetLimit()) ? 131071 : 524287);
    if (isSetLimit())
      hashCode = hashCode * 8191 + limit;

    hashCode = hashCode * 8191 + ((isSetThreadNameList()) ? 131071 : 524287);
    if (isSetThreadNameList())
      hashCode = hashCode * 8191 + threadNameList.hashCode();

    hashCode = hashCode * 8191 + ((isSetLocalTraceIdList()) ? 131071 : 524287);
    if (isSetLocalTraceIdList())
      hashCode = hashCode * 8191 + localTraceIdList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TCmdActiveThreadLightDump other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetLimit()).compareTo(other.isSetLimit());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLimit()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.limit, other.limit);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetThreadNameList()).compareTo(other.isSetThreadNameList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetThreadNameList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.threadNameList, other.threadNameList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLocalTraceIdList()).compareTo(other.isSetLocalTraceIdList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocalTraceIdList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.localTraceIdList, other.localTraceIdList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TCmdActiveThreadLightDump(");
    boolean first = true;

    if (isSetLimit()) {
      sb.append("limit:");
      sb.append(this.limit);
      first = false;
    }
    if (isSetThreadNameList()) {
      if (!first) sb.append(", ");
      sb.append("threadNameList:");
      if (this.threadNameList == null) {
        sb.append("null");
      } else {
        sb.append(this.threadNameList);
      }
      first = false;
    }
    if (isSetLocalTraceIdList()) {
      if (!first) sb.append(", ");
      sb.append("localTraceIdList:");
      if (this.localTraceIdList == null) {
        sb.append("null");
      } else {
        sb.append(this.localTraceIdList);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TCmdActiveThreadLightDumpStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TCmdActiveThreadLightDumpStandardScheme getScheme() {
      return new TCmdActiveThreadLightDumpStandardScheme();
    }
  }

  private static class TCmdActiveThreadLightDumpStandardScheme extends org.apache.thrift.scheme.StandardScheme<TCmdActiveThreadLightDump> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCmdActiveThreadLightDump struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LIMIT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.limit = iprot.readI32();
              struct.setLimitIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // THREAD_NAME_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list56 = iprot.readListBegin();
                struct.threadNameList = new java.util.ArrayList<java.lang.String>(_list56.size);
                java.lang.String _elem57;
                for (int _i58 = 0; _i58 < _list56.size; ++_i58)
                {
                  _elem57 = iprot.readString();
                  struct.threadNameList.add(_elem57);
                }
                iprot.readListEnd();
              }
              struct.setThreadNameListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LOCAL_TRACE_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list59 = iprot.readListBegin();
                struct.localTraceIdList = new java.util.ArrayList<java.lang.Long>(_list59.size);
                long _elem60;
                for (int _i61 = 0; _i61 < _list59.size; ++_i61)
                {
                  _elem60 = iprot.readI64();
                  struct.localTraceIdList.add(_elem60);
                }
                iprot.readListEnd();
              }
              struct.setLocalTraceIdListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TCmdActiveThreadLightDump struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetLimit()) {
        oprot.writeFieldBegin(LIMIT_FIELD_DESC);
        oprot.writeI32(struct.limit);
        oprot.writeFieldEnd();
      }
      if (struct.threadNameList != null) {
        if (struct.isSetThreadNameList()) {
          oprot.writeFieldBegin(THREAD_NAME_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.threadNameList.size()));
            for (java.lang.String _iter62 : struct.threadNameList)
            {
              oprot.writeString(_iter62);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.localTraceIdList != null) {
        if (struct.isSetLocalTraceIdList()) {
          oprot.writeFieldBegin(LOCAL_TRACE_ID_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.localTraceIdList.size()));
            for (long _iter63 : struct.localTraceIdList)
            {
              oprot.writeI64(_iter63);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TCmdActiveThreadLightDumpTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TCmdActiveThreadLightDumpTupleScheme getScheme() {
      return new TCmdActiveThreadLightDumpTupleScheme();
    }
  }

  private static class TCmdActiveThreadLightDumpTupleScheme extends org.apache.thrift.scheme.TupleScheme<TCmdActiveThreadLightDump> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TCmdActiveThreadLightDump struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetLimit()) {
        optionals.set(0);
      }
      if (struct.isSetThreadNameList()) {
        optionals.set(1);
      }
      if (struct.isSetLocalTraceIdList()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetLimit()) {
        oprot.writeI32(struct.limit);
      }
      if (struct.isSetThreadNameList()) {
        {
          oprot.writeI32(struct.threadNameList.size());
          for (java.lang.String _iter64 : struct.threadNameList)
          {
            oprot.writeString(_iter64);
          }
        }
      }
      if (struct.isSetLocalTraceIdList()) {
        {
          oprot.writeI32(struct.localTraceIdList.size());
          for (long _iter65 : struct.localTraceIdList)
          {
            oprot.writeI64(_iter65);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TCmdActiveThreadLightDump struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.limit = iprot.readI32();
        struct.setLimitIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list66 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.threadNameList = new java.util.ArrayList<java.lang.String>(_list66.size);
          java.lang.String _elem67;
          for (int _i68 = 0; _i68 < _list66.size; ++_i68)
          {
            _elem67 = iprot.readString();
            struct.threadNameList.add(_elem67);
          }
        }
        struct.setThreadNameListIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list69 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.localTraceIdList = new java.util.ArrayList<java.lang.Long>(_list69.size);
          long _elem70;
          for (int _i71 = 0; _i71 < _list69.size; ++_i71)
          {
            _elem70 = iprot.readI64();
            struct.localTraceIdList.add(_elem70);
          }
        }
        struct.setLocalTraceIdListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

