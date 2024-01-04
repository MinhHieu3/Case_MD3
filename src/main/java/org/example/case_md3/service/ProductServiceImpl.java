package org.example.case_md3.service;

import org.example.case_md3.model.Product;
import org.example.case_md3.model.TypeProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements GeneralService<Product> {

    TypeProductServiceImpl typeProductService = new TypeProductServiceImpl();

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
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int type = rs.getInt("idType");
                TypeProduct typeProduct = typeProductService.findById(type);
                String status = rs.getString("status");
                products.add(new Product(id, name, quantity, price, typeProduct, status));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return products;

    }

    @Override
    public void add(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into product (name,quantity,price,idType,status) values (?,?,?,?,?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getType().getId());
            preparedStatement.setString(5, product.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id=? ")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ids = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int type = rs.getInt("idType");
                TypeProduct typeProduct = typeProductService.findById(type);
                String status = rs.getString("status");
                product = (new Product(ids, name, quantity, price, typeProduct, status));
            }
        } catch (SQLException e) {
        }
        return product;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update product set name=?,price=?,quantity=?,idType=?,status=? where id =?");) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getType().getId());
            preparedStatement.setString(5, product.getStatus());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean updateProduct(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update product set quantity=? where id =?");) {
            preparedStatement.setInt(1, product.getQuantity());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }


    @Override
    public boolean delete(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        return false;
    }
}
