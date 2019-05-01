package com.sorm.utils;

import java.lang.reflect.Method;

/**
 * 类 名 称：ReflectUtils
 * 类 描 述：封装常用反射操作
 * 创建时间：2019/4/29 10:38
 * 创建人：Mical
 */
public class ReflectUtils {

    /**
     * 调用obj对象的对应属性 fieldName 的get方法
     *
     * @param fieldName 属性
     * @param obj       对象
     * @return 调用方法后返回值
     */
    public static Object invokeGet(String fieldName, Object obj) {
        //通过反射机制，调用属性对应的get方法或set方法
        try {
            Class c = obj.getClass();
            Method m = c.getDeclaredMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
            return m.invoke(obj, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void invokeSet(Object obj, String columnName, Object columnValue) {
        Method m = null;
        try {
            if(columnValue != null){
                m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName),
                        columnValue.getClass());
                m.invoke(obj, columnValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


