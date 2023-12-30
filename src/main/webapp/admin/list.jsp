<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 12/30/2023
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items='${danhSach}' var="product">
    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.quantity}</td>
        <td>${product.price}</td>
        <td>${product.type.name}</td>
        <td>${product.status}</td>
    </tr>
</c:forEach>
</body>
</html>
