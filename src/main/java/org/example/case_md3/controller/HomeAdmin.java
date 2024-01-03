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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeAdmin", value = "/homeAdmin")
public class HomeAdmin extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    TypeProductServiceImpl typeProductService = new TypeProductServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showListProduct(request, response);
                break;
        }

    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search =request.getParameter("search");
        List<Product> products;
        if (search != null) {
            products = productService.findByName(search);
        } else {
            products = productService.findAll();
        }
        List<TypeProduct> typeProducts = typeProductService.findAll();
        request.setAttribute("tpr", typeProducts);
        request.setAttribute("menuNav", products);
        request.getRequestDispatcher("admin/list.jsp").forward(request, response);
    }
//    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/list.jsp");
//        List<Product> products = productService.findAll();
//        request.setAttribute("menuNav",products);
//        requestDispatcher.forward(request,response);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
