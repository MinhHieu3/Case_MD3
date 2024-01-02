package org.example.case_md3.controller;

import org.example.case_md3.model.Product;
import org.example.case_md3.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "homeAdmin", value = "/homeAdmin")
public class HomeAdmin extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/list.jsp");
        List<Product> products = productService.findAll();
        req.setAttribute("menuNav",products);
        requestDispatcher.forward(req,resp);
    }
}
