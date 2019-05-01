package com.sorm.core;

import com.sorm.bean.TableInfo;

import java.util.Map;

/**
 * 类 名 称：QueryFactory
 * 类 描 述：返回相关的 Query 对象
 * 创建时间：2019/4/29 10:32
 * 创建人：Mical
 */
public class QueryFactory {

    private static QueryFactory factory = new QueryFactory();
    private static Query prototypeObj; //原型对象
    static {
        String queryClass = DBManager.getConf().getQueryClass();
        try {
            Class c = Class.forName(queryClass);
            prototypeObj = (Query) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private QueryFactory(){ //私有构造器

    }

    public static Query createQuery(){
        try {
            return (Query) prototypeObj.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, TableInfo> tables = TableContext.tables;
    }

}
