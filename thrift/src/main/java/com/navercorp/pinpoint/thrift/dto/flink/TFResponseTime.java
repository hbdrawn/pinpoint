/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.navercorp.pinpoint.thrift.dto.flink;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2020-03-16")
public class TFResponseTime implements org.apache.thrift.TBase<TFResponseTime, TFResponseTime._Fields>, java.io.Serializable, Cloneable, Comparable<TFResponseTime> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TFResponseTime");

  private static final org.apache.thrift.protocol.TField AVG_FIELD_DESC = new org.apache.thrift.protocol.TField("avg", org.apache.thrift.protocol.TType.I64, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TFResponseTimeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TFResponseTimeTupleSchemeFactory();

  private long avg; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AVG((short)1, "avg");

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
        case 1: // AVG
          return AVG;
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
  private static final int __AVG_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.AVG};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AVG, new org.apache.thrift.meta_data.FieldMetaData("avg", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TFResponseTime.class, metaDataMap);
  }

  public TFResponseTime() {
    this.avg = 0L;

  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TFResponseTime(TFResponseTime other) {
    __isset_bitfield = other.__isset_bitfield;
    this.avg = other.avg;
  }

  public TFResponseTime deepCopy() {
    return new TFResponseTime(this);
  }

  @Override
  public void clear() {
    this.avg = 0L;

  }

  public long getAvg() {
    return this.avg;
  }

  public void setAvg(long avg) {
    this.avg = avg;
    setAvgIsSet(true);
  }

  public void unsetAvg() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AVG_ISSET_ID);
  }

  /** Returns true if field avg is set (has been assigned a value) and false otherwise */
  public boolean isSetAvg() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AVG_ISSET_ID);
  }

  public void setAvgIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AVG_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case AVG:
      if (value == null) {
        unsetAvg();
      } else {
        setAvg((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case AVG:
      return getAvg();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case AVG:
      return isSetAvg();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TFResponseTime)
      return this.equals((TFResponseTime)that);
    return false;
  }

  public boolean equals(TFResponseTime that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_avg = true && this.isSetAvg();
    boolean that_present_avg = true && that.isSetAvg();
    if (this_present_avg || that_present_avg) {
      if (!(this_present_avg && that_present_avg))
        return false;
      if (this.avg != that.avg)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetAvg()) ? 131071 : 524287);
    if (isSetAvg())
      hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(avg);

    return hashCode;
  }

  @Override
  public int compareTo(TFResponseTime other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAvg()).compareTo(other.isSetAvg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAvg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.avg, other.avg);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TFResponseTime(");
    boolean first = true;

    if (isSetAvg()) {
      sb.append("avg:");
      sb.append(this.avg);
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

  private static class TFResponseTimeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TFResponseTimeStandardScheme getScheme() {
      return new TFResponseTimeStandardScheme();
    }
  }

  private static class TFResponseTimeStandardScheme extends org.apache.thrift.scheme.StandardScheme<TFResponseTime> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TFResponseTime struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AVG
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.avg = iprot.readI64();
              struct.setAvgIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TFResponseTime struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetAvg()) {
        oprot.writeFieldBegin(AVG_FIELD_DESC);
        oprot.writeI64(struct.avg);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TFResponseTimeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TFResponseTimeTupleScheme getScheme() {
      return new TFResponseTimeTupleScheme();
    }
  }

  private static class TFResponseTimeTupleScheme extends org.apache.thrift.scheme.TupleScheme<TFResponseTime> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TFResponseTime struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAvg()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetAvg()) {
        oprot.writeI64(struct.avg);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TFResponseTime struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.avg = iprot.readI64();
        struct.setAvgIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

