/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jzuniga
 */
public class DB {
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "root";
    private static String DB_NAME = "javapp?useSSL=false&serverTimezone=UTC";
    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
        return connection;
    }
}
