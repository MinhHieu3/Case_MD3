package org.example.case_md3.service;

import org.example.case_md3.model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements GeneralService<Admin>{
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
    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from admin ");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                adminList.add(new Admin(id,name,pass));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return adminList;
    }

    @Override
    public void add(Admin admin) throws SQLException {

    }

    @Override
    public Admin findById(int id) {

        return null ;
    }

    @Override
    public boolean update(Admin admin) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
