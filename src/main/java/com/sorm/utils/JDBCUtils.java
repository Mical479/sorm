package com.sorm.utils;

import com.sorm.bean.Configuration;
import com.sorm.core.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 类 名 称：JDBCUtils
 * 类 描 述：封装了JDBC查询常用操作
 * 创建时间：2019/4/29 10:37
 * 创建人：Mical
 */
public class JDBCUtils {

    /**
     * 给sql设置参数
     *
     * @param ps     预编译sql 语句对象
     * @param params 参数
     */
    public static void handleParams(PreparedStatement ps, Object[] params) {

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject(1 + i, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据数据库url，获取数据库名
     * @return 数据库名称
     */
    public static String getDBName() {
        Configuration conf = DBManager.getConf();
        String url = conf.getUrl();
        String[] split = url.split("\\?")[0].split("/");
        return split[split.length - 1];
    }
}
