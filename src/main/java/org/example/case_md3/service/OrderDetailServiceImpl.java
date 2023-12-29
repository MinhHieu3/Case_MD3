package org.example.case_md3.service;

import org.example.case_md3.model.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl implements GeneralService<OrderDetails>{
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
