package org.example.case_md3.controller;

import org.example.case_md3.model.Order;
import org.example.case_md3.model.OrderDetails;
import org.example.case_md3.model.Product;
import org.example.case_md3.model.User;
import org.example.case_md3.service.OrderService;
import org.example.case_md3.service.ProductServiceImpl;
import org.example.case_md3.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeUser", value = "/homeUser")
public class HomeUser extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    OrderDetails orderDetails = new OrderDetails();
    OrderService orderService = new OrderService();

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
            case "order":
                try {
                    order(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showList(req, resp);
        }
    }

    private void order(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        List<Order> orders = orderService.findAll();
        double total = 0;
        for (int i = 0; i < buyList.size(); i++) {
            total += buyList.get(i).getPrice();
        }
        String time = String.valueOf(LocalDateTime.now());
        List<User> user = LoginUser.userList;
        int idUser = 0;
        for (int j = 0; j < user.size(); j++) {
            idUser = user.get(j).getId();
            break;
        }
        User user1 = userService.findById(idUser);
        orderService.add(new Order(user1, total, time));
        resp.sendRedirect("/homeUser");

    }

    private void buy(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (UserServiceImpl.name == null) {
            resp.sendRedirect("/loginUsers");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/listBuy.jsp");
            int id = Integer.parseInt(req.getParameter("id"));
            List<Product> productList = productService.findAll();
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == id) {
                    boolean check = false;
                    for (int j = 0; j < buyList.size(); j++) {
                        if (buyList.get(j).getId() == productList.get(i).getId()) {
                            check = true;
                            break;
                        }
                    }
                    if (!check) {
                        Product product = productList.get(i);
                        product.setQuantity(1);
                        buyList.add(product);

                    } else {
//                        int quantity = Integer.parseInt(req.getParameter("quantity"));
                        for (int j = 0; j < buyList.size(); j++) {
                            if (buyList.get(j).getQuantity() != 0 && buyList.get(j).getId() == id) {
                                buyList.get(i).setQuantity(buyList.get(j).getQuantity() + 1);
                                break;
                            }
                        }

                    }
                }
                req.setAttribute("buyList", buyList);
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