package com.sorm.core;

/**
 * 类 名 称：TypeConvertor
 * 类 描 述：负责java数据类型和数据库类型的互相转换
 * 创建时间：2019/4/29 10:33
 * 创建人：Mical
 */
public interface TypeConvertor {

    /**
     * 将数据库类型转换为java的数据类型
     * @param columnType 数据库字段的数据类型
     * @return java的数据类型
     */
    public String databaseType2JavaType(String columnType);

    /**
     * 将java数据类型转化为数据库数据类型
     * @param javaDataType java数据类型
     * @return 数据库数据类型
     */
    public String javaType2DatabaseType(String javaDataType);
}
