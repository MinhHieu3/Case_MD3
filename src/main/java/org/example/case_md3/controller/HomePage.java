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
    TypeProductServiceImpl typeProductService = new TypeProductServiceImpl();

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
            case "searchType":
                searchType(req, resp);
                break;
            default:
                showList(req, resp);
                break;
        }
    }

    private void searchType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = Integer.parseInt(req.getParameter("id"));
        List<Product> products = productService.findAll();
        List<Product>product=new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType().getId() == id) {
                product.add(products.get(i));
            }
        }
        List<TypeProduct> typeProducts = typeProductService.findAll();
        req.setAttribute("list", product);
        req.setAttribute("listType", typeProducts);
        req.getRequestDispatcher("user/menu.jsp").forward(req, resp);
    }


    private void listBuy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (UserServiceImpl.name == null) {
            resp.sendRedirect("http://localhost:8080/loginUsers");
        }
    }
    private void cart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (UserServiceImpl.name == null) {
            resp.sendRedirect("http://localhost:8080/loginUsers");
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/menu.jsp");
        List<Product> products = productService.findAll();
        List<TypeProduct> list = typeProductService.findAll();
        req.setAttribute("listType", list);
        req.setAttribute("list", products);
        requestDispatcher.forward(req, resp);
    }
}