package org.example.case_md3.controller;

import org.example.case_md3.model.Product;
import org.example.case_md3.model.TypeProduct;
import org.example.case_md3.service.ProductServiceImpl;
import org.example.case_md3.service.TypeProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "product", value = "/product")
public class ProductServlet extends HttpServlet {
    ProductServiceImpl productService = new ProductServiceImpl();
    TypeProductServiceImpl typeProductService = new TypeProductServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(req, resp);
                break;
            case "update":
                showUpdate(req, resp);
                break;
            case "delete":
                try {
                    showDelete(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "createType":
                showCreateType(req, resp);
                break;
            case "updateType":
                showUpdateType(req,resp);
                break;
        }
    }

    private void showUpdateType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/updateType.jsp");
        int id = Integer.parseInt(req.getParameter("id"));
        TypeProduct typeProduct=typeProductService.findById(id);
        req.setAttribute("list", typeProduct);
        requestDispatcher.forward(req, resp);
    }

    private void showCreateType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/createType.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        resp.sendRedirect("/homeAdmin");
    }

    private void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/update.jsp");
        List<TypeProduct> typeProducts = typeProductService.findAll();
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        req.setAttribute("listType", typeProducts);
        requestDispatcher.forward(req, resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/create.jsp");
        List<TypeProduct> typeProducts = typeProductService.findAll();
        req.setAttribute("listType", typeProducts);
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createProduct(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    updateProduct(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "createType":
                try {
                    createType(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "updateType":
                try {
                    updateType(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }

    private void updateType(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String producer = req.getParameter("producer");
        String describe = req.getParameter("describe");
        TypeProduct typeProduct = new TypeProduct(id,name, producer, describe);
        typeProductService.update(typeProduct);
        resp.sendRedirect("/homeAdmin?action=showType");
    }

    private void createType(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String name = req.getParameter("name");
        String producer = req.getParameter("producer");
        String describe = req.getParameter("describe");
        TypeProduct typeProduct = new TypeProduct(name, producer, describe);
        typeProductService.add(typeProduct);
        resp.sendRedirect("/homeAdmin?action=showType");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));
        int idType = Integer.parseInt(req.getParameter("idType"));
        TypeProduct typeProduct = typeProductService.findById(idType);
        String status = req.getParameter("status");
        Product product = new Product(id, name, quantity, price, typeProduct, status);
        productService.update(product);
        resp.sendRedirect("/homeAdmin");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));
        int idType = Integer.parseInt(req.getParameter("idType"));
        TypeProduct typeProduct = typeProductService.findById(idType);
        String status = req.getParameter("status");
        productService.add(new Product(name, quantity, price, typeProduct, status));
        resp.sendRedirect("/homeAdmin");
    }

}
