package org.example.case_md3.service;

import org.example.case_md3.model.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService implements GeneralService<Order>{
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void add(Order order) throws SQLException {

    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public boolean update(Order order) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
