package org.example.case_md3.controller;

import org.example.case_md3.model.Order;
import org.example.case_md3.model.OrderDetails;
import org.example.case_md3.model.Product;
import org.example.case_md3.model.User;
import org.example.case_md3.service.OrderDetailServiceImpl;
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

@WebServlet(name = "homeUser", value = "/home")
public class HomeUser extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();
    OrderService orderService = new OrderService();
    public static Integer count = 0;

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
            case "cart":
                cart(req, resp);
                break;
            default:
                showList(req, resp);
        }
    }

    private void cart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (UserServiceImpl.name == null) {
            resp.sendRedirect("/loginUsers");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/listBuy.jsp");
            req.setAttribute("buyList", buyList);
            requestDispatcher.forward(req, resp);
        }
    }

    private void order(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
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
        List<Order> orderList = orderService.findAll();
        int idOrder = orderList.get(orderList.size() - 1).getId();
        Order order = orderService.findById(idOrder);
        List<Product> products = productService.findAll();
        for (int i = 0; i < buyList.size(); i++) {
            orderDetailService.add(new OrderDetails(order, buyList.get(i)));
            for (int j = 0; j < products.size(); j++) {
                if (buyList.get(i).getId() == products.get(j).getId()) {
                    if (products.get(j).getId()>=1) {
                        int quantity = products.get(j).getQuantity() - buyList.get(i).getQuantity();
                        Product product = new Product(buyList.get(i).getId(), quantity);
                        productService.updateProduct(product);
                    }
                }
            }
        }
        buyList = new ArrayList<>();
        count = 0;
        resp.sendRedirect("/home");
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
        for (int i = 0; i < buyList.size(); i++) {
            count++;
        }
        List<User>userList=userService.findAll();
        if (UserServiceImpl.name!=null) {
            String name = "";
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getName().equals(UserServiceImpl.name)) {
                    name = userList.get(i).getName();
                }
            }
            req.setAttribute("user", name);
        }
        req.setAttribute("buy", count);
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