package org.example.case_md3.controller;

import org.example.case_md3.model.*;
import org.example.case_md3.service.*;

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
public class Home extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();
    TypeProductServiceImpl typeProductService = new TypeProductServiceImpl();
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
                try {
                    buy(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "order":
                try {
                    order(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "":
                searchProduct(req, resp);
                break;
            case "cart":
                cart(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "listBuy":
                listBuy(req, resp);
                break;
            case "invoice":
                listInvoice(req, resp);
                break;
            case "showList":
                try {
                    showList(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            
        }
    }

    private void listInvoice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/listInvoice.jsp");
        int idOrder = Integer.parseInt(req.getParameter("idOrder"));
        List<OrderDetails> orderDetails = orderDetailService.findAll();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (int i = 0; i < orderDetails.size(); i++) {
            if (orderDetails.get(i).getIdOrder().getId() == idOrder) {
                orderDetailsList.add(orderDetails.get(i));
            }
        }
        req.setAttribute("listOrderDetail", orderDetailsList);

        requestDispatcher.forward(req, resp);
    }

    private void listBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/listBuy.jsp");
        List<Order> orderList = orderService.findAll();
        List<Order> orders = new ArrayList<>();
        int id = UserServiceImpl.id;
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getIdUser().getId() == id) {
                orders.add(orderList.get(i));
            }

        }
        req.setAttribute("listOrder", orders);
        requestDispatcher.forward(req, resp);
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        List<Product> products;
        if (search != null) {
            products = productService.findByName(search);
        } else {
            products = productService.findAll();
        }
        List<TypeProduct> typeProducts = typeProductService.findAll();
        List<User> userList = userService.findAll();
        if (UserServiceImpl.name != null) {
            String name = "";
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getName().equals(UserServiceImpl.name)) {
                    name = userList.get(i).getName();
                }
            }
            req.setAttribute("user", name);
        }
        req.setAttribute("tpr", typeProducts);
        req.setAttribute("danhSach", products);
        req.setAttribute("buy", buyList.size());
        req.getRequestDispatcher("user/home.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/cart.jsp");
        int id = Integer.parseInt(req.getParameter("id"));
        double total = 0;
        boolean check = false;
        for (int i = 0; i < buyList.size(); i++) {
            if (buyList.get(i).getId() == id) {
                buyList.remove(buyList.get(i));
                req.setAttribute("buyList", buyList);
                check = true;
                break;
            }
        }
        if (check) {
            for (int i = 0; i < buyList.size(); i++) {
                total += (buyList.get(i).getQuantity() * buyList.get(i).getPrice());
            }
        }
        req.setAttribute("total", total);
        requestDispatcher.forward(req, resp);
    }

    private void cart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (UserServiceImpl.name == null) {
            resp.sendRedirect("/loginUsers");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/cart.jsp");
            req.setAttribute("buyList", buyList);
            List<User> userList = userService.findAll();
            if (UserServiceImpl.name != null) {
                String name = "";
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getName().equals(UserServiceImpl.name)) {
                        name = userList.get(i).getName();
                    }
                }
                req.setAttribute("user", name);
            }

            requestDispatcher.forward(req, resp);
        }
    }

    private void order(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        double total = 0;
        for (int i = 0; i < buyList.size(); i++) {
            total += (buyList.get(i).getPrice() * buyList.get(i).getQuantity());
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
            if (buyList.get(i).getQuantity() > 0) {
                orderDetailService.add(new OrderDetails(order, buyList.get(i)));
                for (int j = 0; j < products.size(); j++) {
                    if (buyList.get(i).getId() == products.get(j).getId()) {
                        if (products.get(j).getQuantity() >= 1) {
                            int quantity = products.get(j).getQuantity() - buyList.get(i).getQuantity();
                            Product product = new Product(buyList.get(i).getId(), quantity);
                            productService.updateProduct(product);
                            if (quantity == 0) {
                                String status = "hết";
                                Product produc = new Product(buyList.get(i).getId(), status);
                                productService.updateStatus(produc);
                            }
                        }
                    }
                }
            }
        }
        buyList = new ArrayList<>();
        count = 0;
        resp.sendRedirect("/home");
        req.setAttribute("danhSach", products);
    }

    private void buy(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        if (UserServiceImpl.name == null) {
            resp.sendRedirect("/loginUsers");
        } else {
            List<Product> productList = productService.findAll();
            boolean check = false;
            int id = Integer.parseInt(req.getParameter("id"));
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getStatus().equals("Hết") && id == productList.get(i).getId()) {
                    check = true;
                }
            }
            if (!check) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/cart.jsp");
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getId() == id && productList.get(i).getQuantity() > 0) {
                        boolean check2 = false;
                        for (int j = 0; j < buyList.size(); j++) {
                            if (buyList.get(j).getId() == productList.get(i).getId()) {
                                if (productList.get(i).getQuantity() < buyList.get(j).getQuantity() + 1) {

                                } else {
                                    check2 = true;
                                    break;
                                }
                            }
                        }
                        if (!check2) {
                            Product product = productList.get(i);
                            product.setQuantity(1);
                            buyList.add(product);
                        } else {
                            for (int j = 0; j < buyList.size(); j++) {
                                if (buyList.get(j).getQuantity() != 0 && buyList.get(j).getId() == id) {
                                    buyList.get(j).setQuantity(buyList.get(j).getQuantity() + 1);
                                    break;
                                }
                            }

                        }
                    }
                    double total = 0;
                    for (int j = 0; j < buyList.size(); j++) {
                        total += (buyList.get(j).getQuantity() * buyList.get(j).getPrice());
                    }
                    req.setAttribute("total", total);
                    req.setAttribute("buyList", buyList);

                }
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("/home");
            }

        }
    }


    private void showList(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException, SQLException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/home.jsp");
        for (int i = 0; i < buyList.size(); i++) {
            count++;
        }
        List<User> userList = userService.findAll();

        if (UserServiceImpl.name != null) {
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
        List<Product> products1 = new ArrayList<>();
        for (Product product : products) {
            if (!product.getStatus().equals("Đã Xóa")) {
                products1.add(product);
            }
        }
        req.setAttribute("danhSach", products1);
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