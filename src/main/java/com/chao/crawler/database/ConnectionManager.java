package com.chao.crawler.database;

import org.apache.http.util.Asserts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by 354649 on 2017/6/19.
 */
public class ConnectionManager {
    private static PreparedStatement pstmt;
    private static Connection connection;
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3308/crawler";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static PreparedStatement getStatement(String sql) throws SQLException {
        Asserts.notNull(connection,"数据库未连接");
        pstmt = connection.prepareStatement(sql);
        return pstmt;
    }
    public static Connection getConnection(){
        return connection;
    }
    public static void closePreparedStatement() throws SQLException{
        if (pstmt != null){
            pstmt.close();
        }
    }

}
