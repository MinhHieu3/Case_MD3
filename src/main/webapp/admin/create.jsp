<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 1/2/2024
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('https://kenh14cdn.com/203336854389633024/2023/2/3/photo-11-1675394504826326670265.png') center fixed;
            background-size: revert;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <form method="post">

        <h1 style="color: #5cb85c">Thêm Sản Phẩm</h1>

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
        </div>
        <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Enter quantity">
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="text" class="form-control" id="price" name="price" placeholder="Enter price">
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <input type="text" class="form-control" id="status" name="status" placeholder="Enter status">
        </div>
        <div class="mb-3">
            <label for="idType" class="form-label">Type</label>
            <select class="form-select" id="idType" name="idType">
                <c:forEach items='${listType}' var="type">
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
