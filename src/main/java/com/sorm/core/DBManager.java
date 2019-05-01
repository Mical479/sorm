package com.sorm.core;

import com.sorm.bean.Configuration;
import com.sorm.pool.DBConnPool;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 类 名 称：DBManager
 * 类 描 述：根据配置信息，维持连接对象的管理（增加连接池功能）
 * 创建时间：2019/4/29 10:36
 * 创建人：Mical
 */
@SuppressWarnings("all")
public class DBManager {

    private static Configuration conf;
    private static DBConnPool pool;

    static {    //静态代码块，只加载一次
        Properties pros = new Properties();
        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        conf = new Configuration();
        conf.setDriver(pros.getProperty("driver"));
        conf.setPoPackage(pros.getProperty("poPackage"));
        conf.setPwd(pros.getProperty("pwd"));
        conf.setSrcPath(pros.getProperty("srcPath"));
        conf.setUrl(pros.getProperty("url"));
        conf.setUser(pros.getProperty("user"));
        conf.setUsingDB(pros.getProperty("usingDB"));
        conf.setQueryClass(pros.getProperty("queryClass"));
        conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
        conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));

        try {
            Class.forName("com.sorm.core.TableContext");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //从连接池中获取连接
    public static Connection getConn() {
        if (pool == null){
            pool = new DBConnPool();
        }
        return pool.getConnection();
    }

    //创建新的数据库连接对象
    public static Connection createConn() {
        try {
            Class.forName(conf.getDriver());
            //直接建立连接，后期增加连接池处理，提高效率
            return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //关闭数据库相关的对象
    public static void close(ResultSet rs, Statement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.close(conn);
    }

    public static void close(Statement ps, Connection conn) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.close(conn);
    }

    /**
     * 返回Configuration对象
     *
     * @return
     */
    public static Configuration getConf() {
        return conf;
    }
}
