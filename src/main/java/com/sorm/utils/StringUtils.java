package com.sorm.utils;

/**
 * 类 名 称：StringUtils
 * 类 描 述：封装了字符串常用操作
 * 创建时间：2019/4/29 10:37
 * 创建人：Mical
 */
public class StringUtils {

    /**
     * 将目标首字母变为大写
     * @param str 目标字符串
     * @return 首字母变为大写的字符串
     */
    public static String firstChar2UpperCase(String str){
        return str.toUpperCase().substring(0, 1)+str.substring(1);
    }
}
