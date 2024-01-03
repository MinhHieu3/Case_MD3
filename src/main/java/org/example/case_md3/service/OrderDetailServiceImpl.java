package org.example.case_md3.service;

import org.example.case_md3.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements GeneralService<OrderDetails>{
    OrderService orderService=new OrderService();
    ProductServiceImpl productService=new ProductServiceImpl();
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
        List<OrderDetails> orderDetails = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderdetail")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrder =rs.getInt("idOrder");
                int idProduct =rs.getInt("idProduct");
                int quantity= rs.getInt("quantity");
                double price=rs.getDouble("price");
                Order order=orderService.findById(idOrder);
                Product product=productService.findById(idProduct);
                orderDetails.add(new OrderDetails(order, product,quantity,price));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderDetails;

    }

    @Override
    public void add(OrderDetails orderDetails) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into orderdetail (idOrder,idProduct,quantity,price) values (?,?,?,?)")) {
            preparedStatement.setInt(1, orderDetails.getIdOrder().getId());
            preparedStatement.setInt(2, orderDetails.getIdProduct().getId());
            preparedStatement.setInt(3, orderDetails.getIdProduct().getQuantity());
            preparedStatement.setDouble(4, orderDetails.getIdProduct().getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public OrderDetails findById(int id) {
        OrderDetails orderDetails=new OrderDetails();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from orderdetail where id=? ")) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idOrder =rs.getInt("idOrder");
                int idProduct =rs.getInt("idProduct");
                int quantity= rs.getInt("quantity");
                double price=rs.getDouble("price");
                Order order=orderService.findById(idOrder);
                Product product=productService.findById(idProduct);
                orderDetails=new OrderDetails(order, product,quantity,price);
            }
        } catch (SQLException e) {
        }
        return orderDetails;

    }

//    public double sum() throws SQLException {
//        double totalPrice = 0;
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("select sum(price) as total_Price from orderdetail ");
//        ResultSet rs = preparedStatement.executeQuery();
//        if (rs.next()){
//            totalPrice = rs.getDouble("totalPrice");
//        }
//        return totalPrice;
//    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
