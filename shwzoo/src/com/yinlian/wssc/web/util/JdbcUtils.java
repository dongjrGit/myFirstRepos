/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcUtils.java
 * @author Liang.ma.s
 * @version $Id: JdbcUtils.java, v 0.1 2016年4月11日 下午6:11:16 Administrator Exp $
 */
public class JdbcUtils {

    private static final String URL        = "jdbc:mysql://192.168.1.52:3307/yl_20150616?characterEncoding=utf-8&allowMultiQueries=true";
    private static final String USER       = "root";
    private static final String PASSWORD   = "mysql";
    private static final String DRIVERNAME = "com.mysql.jdbc.Driver";

    private Connection          connection;

    private PreparedStatement   pstmt;
    static {
        try {
            Class.forName(DRIVERNAME);
            System.out.println("数据库连接");
        } catch (ClassNotFoundException e) {

        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {

        }
        return connection;
    }

    /**
     * 批量插入
     * 
     * @param sql
     * @param list
     * @return
     */
    public int insertBatch(String sql, List<?> list) {
        int result = 0;
        try {
            //对sql进行改变
            //.........
            pstmt = connection.prepareStatement(sql);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {

        }
        return result;
    }
}
