package com.sorm.core;

/**
 * 类 名 称：MySqlTypeConvertor
 * 类 描 述：mysql数据类型和java数据类型的转化
 * 创建时间：2019/4/29 21:19
 * 创建人：Mical
 */
public class MySqlTypeConvertor implements TypeConvertor {
    @Override
    public String databaseType2JavaType(String columnType) {

        //varchar --> String
        if ("varchar".equalsIgnoreCase(columnType) || "char".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("int".equalsIgnoreCase(columnType)
                || "tinyint".equalsIgnoreCase(columnType)
                || "smallint".equalsIgnoreCase(columnType)
                || "integer".equalsIgnoreCase(columnType)) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(columnType)) {
            return "Long";
        } else if ("double".equalsIgnoreCase(columnType)
                || "float".equalsIgnoreCase(columnType)) {
            return "Double";
        }else if("clob".equalsIgnoreCase(columnType)){
            return "java.sql.CLob";
        }else if("blob".equalsIgnoreCase(columnType)){
            return "java.sql.BLob";
        }else if("date".equalsIgnoreCase(columnType)){
            return "java.sql.Time";
        }else if("timestamp".equalsIgnoreCase(columnType)){
            return "java.sql.Timestamp";
        }else if("decimal".equalsIgnoreCase(columnType)){
            return "Double";
        }else if("mediumtext".equalsIgnoreCase(columnType)){
            return "String";
        }
        System.out.println("创建类型失败：" + columnType);
        return null;
    }

    @Override
    public String javaType2DatabaseType(String javaDataType) {
        return null;
    }
}
