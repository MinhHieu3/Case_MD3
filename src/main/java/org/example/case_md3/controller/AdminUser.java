package org.example.case_md3.controller;

import org.example.case_md3.model.Product;
import org.example.case_md3.model.TypeProduct;
import org.example.case_md3.service.ProductServiceImpl;
import org.example.case_md3.service.TypeProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminUser", value = "/admins")
public class AdminUser extends HttpServlet {

    private TypeProductServiceImpl typeProductService = new TypeProductServiceImpl();
   private ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" :
                adminAddGet(request,response);
                break;
            case "delete" :
                try {
                    deleteProduct(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;  
            case "edit" :
                editGet(request, response);
                break;
            default:
                showList(request, response);
                break;
        }
    }

    private void editGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        request.setAttribute("TypeProduct", typeProductService.findAll());
        request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("/admins");
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("danhSach", products);
        request.getRequestDispatcher("user/admin.jsp").forward(request, response);
    }

    private void adminAddGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/add.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    adminAddPost(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit" :
                try {
                    editPost(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showList(request, response);
                break;
        }
    }

    private void editPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        int idType = Integer.parseInt(request.getParameter("idType"));
        String status = request.getParameter("status");
        TypeProduct typeProduct = typeProductService.findById(idType);
        Product product = new Product(id, name, quantity, price, typeProduct, status);
        productService.update(product);
        response.sendRedirect("/admins");
    }

    private void adminAddPost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        int idType = Integer.parseInt(request.getParameter("idType"));
        String status = request.getParameter("status");
        TypeProduct type = typeProductService.findById(idType);
        productService.add(new Product(0, name, quantity, price, type, status));
        response.sendRedirect("/admins");
    }
}