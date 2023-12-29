package org.example.case_md3.service;

import org.example.case_md3.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements GeneralService<User>{
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void add(User user) throws SQLException {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean update(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
