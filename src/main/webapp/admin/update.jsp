<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 1/2/2024
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <form method="post">
        <div class="form-group">
            <label for="productName">Name</label>
            <input type="text" class="form-control" id="productName" name="name" placeholder="Name" value="${product.name}">
        </div>
        <div class="form-group">
            <label for="productQuantity">Quantity</label>
            <input type="number" class="form-control" id="productQuantity" name="quantity" placeholder="Quantity" value="${product.quantity}">
        </div>
        <div class="form-group">
            <label for="productPrice">Price</label>
            <input type="text" class="form-control" id="productPrice" name="price" placeholder="Price" value="${product.price}">
        </div>
        <div class="form-group">
            <label for="productStatus">Status</label>
            <input type="text" class="form-control" id="productStatus" name="status" placeholder="Status" value="${product.status}">
        </div>
        <div class="form-group">
            <label for="productType">Select Type</label>
            <select class="form-control" id="productType" name="idType">
                <c:forEach items='${listType}' var="type">
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Edit</button>
    </form>
</div>
<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
