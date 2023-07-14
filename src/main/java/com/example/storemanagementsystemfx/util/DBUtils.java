package com.example.storemanagementsystemfx.util;

import java.sql.*;

public class DBUtils {
//    private static String url = "jdbc:mysql://sql6.freemysqlhosting.net/sql6632248";
//    private static String username = "sql6632248";
//    private static String password = "35CqJVLsnr";
    private static String url = "jdbc:mysql://localhost:3306/storems-fx";
    private static String username = "root";
    private static String password = "Khung1235!";
    private static String driver = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnection() throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    //Two-args without ResultSet
    public static void close(Connection connection, PreparedStatement preparedStatement) throws Exception {
        if(preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection!=null){
            connection.close();
        }
    }

    //Three-args
    public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) throws Exception{
        if(resultSet!=null){
            resultSet.close();
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        if (connection!=null){
            connection.close();
        }
    }
}
