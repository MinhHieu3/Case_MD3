package org.example.case_md3.controller;
import org.example.case_md3.model.Admin;
import org.example.case_md3.model.Order;
import org.example.case_md3.model.OrderDetails;
import org.example.case_md3.service.AdminServiceImpl;
import org.example.case_md3.service.OrderDetailServiceImpl;
import org.example.case_md3.service.OrderService;
import sun.util.resources.cldr.rof.CalendarData_rof_TZ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginAdmin", value = "/loginAdmin")

public class LoginAdmin extends HttpServlet {
    AdminServiceImpl adminService = new AdminServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "showLoginAdmin":
                showLoginAdmin(req,resp);
                break;
        }
    }
    private void showLoginAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                showCheckAdmin(req, resp);
                break;
        }

    }

    private void showCheckAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nameAdmin = req.getParameter("userAdmin");
        String passAdmin = req.getParameter("passAdmin");
        List<Admin> adminList = adminService.findAll();
        boolean check = false;
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getName().equals(nameAdmin) && adminList.get(i).getPass().equals(passAdmin)) {
                check = true;
            }
        }
        if (check) {
            resp.sendRedirect("/homeAdmin");
        } else {
            resp.sendRedirect("admin/login.jsp");

        }
    }

}
