package com.sorm.bean;

/**
 * 类 名 称：ColumnInfo
 * 类 描 述：封装表中一个字段的信息
 * 创建时间：2019/4/29 10:39
 * 创建人：Mical
 */
public class ColumnInfo {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段数据类型
     */
    private String dataType;

    /**
     * 字段键类型，0 普通键，1 主键， 2 外键
     */
    private int keyType;

    public ColumnInfo() {
    }

    public ColumnInfo(String name, String dataType, int keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }
}
