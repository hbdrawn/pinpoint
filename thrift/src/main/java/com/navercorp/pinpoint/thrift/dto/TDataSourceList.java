/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.navercorp.pinpoint.thrift.dto;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2020-03-16")
public class TDataSourceList implements org.apache.thrift.TBase<TDataSourceList, TDataSourceList._Fields>, java.io.Serializable, Cloneable, Comparable<TDataSourceList> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TDataSourceList");

  private static final org.apache.thrift.protocol.TField DATA_SOURCE_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("dataSourceList", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TDataSourceListStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TDataSourceListTupleSchemeFactory();

  private java.util.List<TDataSource> dataSourceList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DATA_SOURCE_LIST((short)1, "dataSourceList");

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
        case 1: // DATA_SOURCE_LIST
          return DATA_SOURCE_LIST;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DATA_SOURCE_LIST, new org.apache.thrift.meta_data.FieldMetaData("dataSourceList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TDataSource.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TDataSourceList.class, metaDataMap);
  }

  public TDataSourceList() {
  }

  public TDataSourceList(
    java.util.List<TDataSource> dataSourceList)
  {
    this();
    this.dataSourceList = dataSourceList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TDataSourceList(TDataSourceList other) {
    if (other.isSetDataSourceList()) {
      java.util.List<TDataSource> __this__dataSourceList = new java.util.ArrayList<TDataSource>(other.dataSourceList.size());
      for (TDataSource other_element : other.dataSourceList) {
        __this__dataSourceList.add(new TDataSource(other_element));
      }
      this.dataSourceList = __this__dataSourceList;
    }
  }

  public TDataSourceList deepCopy() {
    return new TDataSourceList(this);
  }

  @Override
  public void clear() {
    this.dataSourceList = null;
  }

  public int getDataSourceListSize() {
    return (this.dataSourceList == null) ? 0 : this.dataSourceList.size();
  }

  public java.util.Iterator<TDataSource> getDataSourceListIterator() {
    return (this.dataSourceList == null) ? null : this.dataSourceList.iterator();
  }

  public void addToDataSourceList(TDataSource elem) {
    if (this.dataSourceList == null) {
      this.dataSourceList = new java.util.ArrayList<TDataSource>();
    }
    this.dataSourceList.add(elem);
  }

  public java.util.List<TDataSource> getDataSourceList() {
    return this.dataSourceList;
  }

  public void setDataSourceList(java.util.List<TDataSource> dataSourceList) {
    this.dataSourceList = dataSourceList;
  }

  public void unsetDataSourceList() {
    this.dataSourceList = null;
  }

  /** Returns true if field dataSourceList is set (has been assigned a value) and false otherwise */
  public boolean isSetDataSourceList() {
    return this.dataSourceList != null;
  }

  public void setDataSourceListIsSet(boolean value) {
    if (!value) {
      this.dataSourceList = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case DATA_SOURCE_LIST:
      if (value == null) {
        unsetDataSourceList();
      } else {
        setDataSourceList((java.util.List<TDataSource>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case DATA_SOURCE_LIST:
      return getDataSourceList();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case DATA_SOURCE_LIST:
      return isSetDataSourceList();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TDataSourceList)
      return this.equals((TDataSourceList)that);
    return false;
  }

  public boolean equals(TDataSourceList that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_dataSourceList = true && this.isSetDataSourceList();
    boolean that_present_dataSourceList = true && that.isSetDataSourceList();
    if (this_present_dataSourceList || that_present_dataSourceList) {
      if (!(this_present_dataSourceList && that_present_dataSourceList))
        return false;
      if (!this.dataSourceList.equals(that.dataSourceList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetDataSourceList()) ? 131071 : 524287);
    if (isSetDataSourceList())
      hashCode = hashCode * 8191 + dataSourceList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TDataSourceList other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetDataSourceList()).compareTo(other.isSetDataSourceList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataSourceList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataSourceList, other.dataSourceList);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TDataSourceList(");
    boolean first = true;

    sb.append("dataSourceList:");
    if (this.dataSourceList == null) {
      sb.append("null");
    } else {
      sb.append(this.dataSourceList);
    }
    first = false;
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TDataSourceListStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TDataSourceListStandardScheme getScheme() {
      return new TDataSourceListStandardScheme();
    }
  }

  private static class TDataSourceListStandardScheme extends org.apache.thrift.scheme.StandardScheme<TDataSourceList> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TDataSourceList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DATA_SOURCE_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list48 = iprot.readListBegin();
                struct.dataSourceList = new java.util.ArrayList<TDataSource>(_list48.size);
                TDataSource _elem49;
                for (int _i50 = 0; _i50 < _list48.size; ++_i50)
                {
                  _elem49 = new TDataSource();
                  _elem49.read(iprot);
                  struct.dataSourceList.add(_elem49);
                }
                iprot.readListEnd();
              }
              struct.setDataSourceListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TDataSourceList struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dataSourceList != null) {
        oprot.writeFieldBegin(DATA_SOURCE_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.dataSourceList.size()));
          for (TDataSource _iter51 : struct.dataSourceList)
          {
            _iter51.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TDataSourceListTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TDataSourceListTupleScheme getScheme() {
      return new TDataSourceListTupleScheme();
    }
  }

  private static class TDataSourceListTupleScheme extends org.apache.thrift.scheme.TupleScheme<TDataSourceList> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TDataSourceList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDataSourceList()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetDataSourceList()) {
        {
          oprot.writeI32(struct.dataSourceList.size());
          for (TDataSource _iter52 : struct.dataSourceList)
          {
            _iter52.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TDataSourceList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list53 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.dataSourceList = new java.util.ArrayList<TDataSource>(_list53.size);
          TDataSource _elem54;
          for (int _i55 = 0; _i55 < _list53.size; ++_i55)
          {
            _elem54 = new TDataSource();
            _elem54.read(iprot);
            struct.dataSourceList.add(_elem54);
          }
        }
        struct.setDataSourceListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

