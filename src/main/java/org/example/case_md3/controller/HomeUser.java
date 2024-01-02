package org.example.case_md3.controller;

import org.example.case_md3.model.Product;
import org.example.case_md3.service.ProductServiceImpl;
import org.example.case_md3.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeUser", value = "/homeUser")
public class HomeUser extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    public static List<Product> buyList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "buy":
                buy(req, resp);
                break;
            default:
                showList(req, resp);
        }
    }

    private void buy(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (UserServiceImpl.name == null) {
            resp.sendRedirect("/loginUsers");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/listBuy.jsp");
            int id = Integer.parseInt(req.getParameter("id"));
            List<Product> productList = productService.findAll();
            boolean check = false;
            for (int i = 0; i < productList.size(); i++) {
                for (int j = 0; j < buyList.size(); j++) {
                    if (productList.get(i).getId() == id) {
                        if (buyList.get(j).getId() != productList.get(i).getId()) {
                        buyList.add(productList.get(i));
                        req.setAttribute("buyList", buyList);
                            break;
                        }
                    }
                }

            }
            requestDispatcher.forward(req, resp);
        }

    }


private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/home.jsp");
    List<Product> products = productService.findAll();
    req.setAttribute("danhSach", products);
    requestDispatcher.forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    if (action == null) {
        action = "";
    }
    switch (action) {

    }
}

}