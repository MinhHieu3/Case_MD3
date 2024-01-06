package org.example.case_md3.controller;

import org.example.case_md3.model.Product;
import org.example.case_md3.model.TypeProduct;
import org.example.case_md3.model.User;
import org.example.case_md3.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "homePage", value = "/homePage")
public class HomePage extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();
    TypeProductServiceImpl typeProductService = new TypeProductServiceImpl();

    public static List<Product> productList=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "cart":
                cart(req, resp);
                break;
            case "buy":
                listBuy(req, resp);
                break;
            default:
                showList(req,resp);

        }
    }


    private void listBuy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (UserServiceImpl.name == null){
            resp.sendRedirect("/loginUsers");
        }
    }

    private void cart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (UserServiceImpl.name == null){
            resp.sendRedirect("/loginUsers");
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("user/menu.jsp");
        List<Product> products = productService.findAll();
        for (Product product : products) {
            if (!product.getStatus().equals("Đã Xóa")) {
                productList.add(product);
            }
        }
        req.setAttribute("list", productList);
        requestDispatcher.forward(req, resp);
    }
}