package org.example.case_md3.service;

import org.example.case_md3.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements GeneralService<Order> {
    UserServiceImpl userService = new UserServiceImpl();

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
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from `order`")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idUser = rs.getInt("idUser");
                double total = rs.getDouble("total");
                String time = rs.getString("time");
                User user = userService.findById(idUser);
                orders.add(new Order(id, user, total, time));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orders;
    }

    @Override
    public void add(Order order) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into `order` (idUser,total,time) values (?,?,?)")) {
            preparedStatement.setInt(1, order.getIdUser().getId());
            preparedStatement.setDouble(2, order.getTotal());
            preparedStatement.setString(3, order.getTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Order findById(int id) {
        Order order = new Order();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from `order` where id=? ")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ids = rs.getInt("id");
                int idUser = rs.getInt("idUser");
                double total = rs.getDouble("total");
                String time = rs.getString("time");
                User idFind = userService.findById(idUser);
                order = new Order(ids, idFind, total, time);
            }
        } catch (SQLException e) {
        }
        return order;
    }

    public List<Order> findSumTotal() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select sum(`order`.total) as 'total'\n" +
                     "from `order`\n")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                double total = rs.getDouble("total");
                orders.add(new Order(total));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orders;
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
