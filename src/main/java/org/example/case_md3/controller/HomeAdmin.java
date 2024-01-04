package org.example.case_md3.controller;

import org.example.case_md3.model.Order;
import org.example.case_md3.model.OrderDetails;
import org.example.case_md3.model.Product;
import org.example.case_md3.service.OrderDetailServiceImpl;
import org.example.case_md3.service.OrderService;
import org.example.case_md3.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeAdmin", value = "/homeAdmin")
public class HomeAdmin extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();
    OrderService orderService=new OrderService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "showBill":
                showBill(req,resp);
                break;
            case "showSale":
                showSale(req,resp);
                break;
            default:
                showList(req, resp);
        }

    }

    private void showSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/listSale.jsp");
        List<Order> order= orderService.findByTime();
        List<Order>orderList=orderService.findAll();
        List<Product>products=productService.findAll();
        int count=orderList.size();
        List<OrderDetails>orderDetails=orderDetailService.findAll();
        int quantity=0;
        for (int i = 0; i < orderDetails.size(); i++) {
            quantity+=orderDetails.get(i).getQuantity();
        }
        int quantityProduct=0;
        for (int i = 0; i < products.size(); i++) {
            quantityProduct+=products.get(i).getQuantity();
        }
        req.setAttribute("sale",order);
        req.setAttribute("orderBuy",count);
        req.setAttribute("quantityBuy",quantity);
        req.setAttribute("quantity",quantityProduct);
        requestDispatcher.forward(req,resp);
    }

    private void showBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/listBill.jsp");
        List<OrderDetails> orderDetails = orderDetailService.findAll();
        req.setAttribute("listOrderDetail",orderDetails);
        requestDispatcher.forward(req,resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/list.jsp");
        List<Product> products = productService.findAll();
        req.setAttribute("menuNav",products);
        requestDispatcher.forward(req,resp);
    }
}
