package org.example.case_md3.service;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    public java.sql.Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/friend?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
