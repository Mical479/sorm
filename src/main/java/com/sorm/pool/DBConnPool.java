package com.sorm.pool;

import com.sorm.core.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类 名 称：DBConnPool
 * 类 描 述：连接池的类
 * 创建时间：2019/5/1 18:14
 * 创建人：Mical
 */
public class DBConnPool {
    private List<Connection> pool; //连接池对象

    private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize(); //最大连接数
    private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize(); //最小连接数

    /**
     * 初始化连接池，使池中的连接数达到最小值
     */
    public void initPool(){
        if (pool == null){
            pool = new ArrayList();
        }

        while (pool.size() < DBConnPool.POOL_MIN_SIZE){
            pool.add(DBManager.createConn());
            System.out.println("初始化池，池中连接数：" + pool.size());
        }
    }

    /**
     * 从连接池中取出一个连接
     * @return 连接对象
     */
    public synchronized Connection getConnection(){
        int last_index = pool.size() - 1;
        Connection conn = pool.get(last_index);
        pool.remove(last_index);
        return conn;
    }

    /**
     * 将连接放回池中
     * @param conn 连接
     */
    public synchronized void close(Connection conn){
        if (pool.size() >= POOL_MAX_SIZE){
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            pool.add(conn);
        }
    }

    public DBConnPool(){
        initPool();
    }
}
