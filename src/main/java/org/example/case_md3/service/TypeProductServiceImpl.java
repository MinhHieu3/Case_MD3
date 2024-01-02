package org.example.case_md3.service;

import org.example.case_md3.model.TypeProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeProductServiceImpl implements GeneralService<TypeProduct>{

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
    public List<TypeProduct> findAll() {
        List<TypeProduct> typeProducts = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from type")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String producer=rs.getString("producer");
                String describe = rs.getString("describe");

                typeProducts.add(new TypeProduct(id, name,producer,describe));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return typeProducts;
    }

    @Override
    public void add(TypeProduct typeProduct) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into user (name,producer,describe) values (?,?,?)")) {
            preparedStatement.setString(1, typeProduct.getName());
            preparedStatement.setString(2, typeProduct.getProducer());
            preparedStatement.setString(3, typeProduct.getDescribe());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public TypeProduct findById(int id) {
        TypeProduct typeProducts=new TypeProduct();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from type where id=? ")) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ids =rs.getInt("id");
                String names=rs.getString("name");
                String producer=rs.getString("producer");
                String describe = rs.getString("describe");
                typeProducts=new TypeProduct(ids, names,producer,describe);
            }
        } catch (SQLException e) {
        }
        return typeProducts;
    }

    @Override
    public boolean update(TypeProduct typeProduct) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
    public TypeProduct findByName(String name) {
        TypeProduct typeProducts=new TypeProduct();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ? ")) {
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String names=rs.getString("name");
                String producer=rs.getString("producer");
                String describe = rs.getString("describe");
                typeProducts=new TypeProduct( names,producer,describe);
            }
        } catch (SQLException e) {
        }
        return typeProducts;

    }
}