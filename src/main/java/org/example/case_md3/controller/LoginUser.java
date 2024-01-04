package org.example.case_md3.controller;

import org.example.case_md3.model.Product;
import org.example.case_md3.model.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginUser", value = "/loginUsers")
public class LoginUser extends HttpServlet {

    UserServiceImpl userService = new UserServiceImpl();
    ProductServiceImpl productService = new ProductServiceImpl();
    public static List<User>userList=new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showList(req, resp);
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                try {
                    CheckLogin(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void CheckLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        if (user != null && pass != null) {
            List<User> list = userService.findAll();
            boolean check = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUsername().equals(user) && list.get(i).getPassword().equals(pass)) {
                    check = true;
                    String name = list.get(i).getName();
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/home.jsp");
                    List<Product> products = productService.findAll();
                    req.setAttribute("danhSach", products);
                    User user1=list.get(i);
                    userList.add(user1);
                    req.setAttribute("id", userList);
                    UserServiceImpl.name = name;
                    req.setAttribute("buy", Home.count=0);
                    req.setAttribute("user", name);
                    requestDispatcher.forward(req, resp);
                    break;
                }
            }
            if (!check) {
                resp.sendRedirect("/loginUsers");
            }
        } else {
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            userService.add(new User(name, phone, username, password));
            resp.sendRedirect("/loginUsers");
        }
    }
}