<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 1/2/2024
  Time: 8:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</head>
<body>
<form action="/homeUser">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">ID Sản Phẩm </th>
        <th scope="col">Tên Sản Phẩm</th>
        <th scope="col">Số Lượng</th>
        <th scope="col">Giá</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items='${buyList}' var="products">
    <tr>
        <td>${products.id}</td>
        <td>${products.name}</td>
        <td>${products.quantity}</td>
<%--        <td><input type="text" name="quantity"></td>--%>
        <td>${products.price}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
    <button>Mua Thêm</button>
</form>

    <button><a href="/homeUser?action=order">Thanh Toán</a></button>


</body>
</html>
