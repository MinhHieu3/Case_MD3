package org.example.case_md3.service;

import org.example.case_md3.model.TypeProduct;
import org.example.case_md3.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements GeneralService<User> {
    public static String name =null;
    public static int id=0;
    protected Connection getConnection() {
        Connection connection = null;
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager_store?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return connection;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from user")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name=rs.getString("name");
                String phone=rs.getString("phone");
                String username = rs.getString("username");
                String pass = rs.getString("password");

                users.add(new User(id, name,phone,username, pass));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }

    @Override
    public void add(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into user (name,phone,username,password) values (?,?,?,?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public User findById(int id) {
        User user=new User();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id=? ")) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ids =rs.getInt("id");
                String name=rs.getString("name");
                String phone=rs.getString("phone");
                String username=rs.getString("username");
                String password = rs.getString("password");
                user=new User(ids, name,phone,username,password);
            }
        } catch (SQLException e) {
        }
        return user;
    }
    public List<User> findByName(String name) {
        List<User>users=new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ? ")) {
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ids =rs.getInt("id");
                String names=rs.getString("name");
                String phone=rs.getString("phone");
                String username=rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(ids, names,phone,username,password));
            }
        } catch (SQLException e) {
        }
        return users;

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
