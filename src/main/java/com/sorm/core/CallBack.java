package com.sorm.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 类 名 称：CallBack
 * 类 描 述：Query类中的回调接口
 * 创建时间：2019/5/1 16:43
 * 创建人：Mical
 */
public interface CallBack {
    public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs);
}
