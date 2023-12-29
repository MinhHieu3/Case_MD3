package org.example.case_md3.service;

import org.example.case_md3.model.OrderDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl implements GeneralService<OrderDetails>{
    protected Connection getConnection() {
        Connection connection = null;
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager_store?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<OrderDetails> findAll() {
        return null;
    }

    @Override
    public void add(OrderDetails orderDetails) throws SQLException {

    }

    @Override
    public OrderDetails findById(int id) {
        return null;
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
