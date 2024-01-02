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
</head>
<body>
<form method="post">
    <input type="text" name="name" placeholder="name ">
    <input type="number" name="quantity" placeholder="quantity">
    <input type="text" name="price" placeholder="price">
    <input type="text" name="status" placeholder="status">
    <select name="idType">
        <c:forEach items='${listType}' var="type">
            <option value="${type.id}"> ${type.name}</option>
        </c:forEach>
    </select>
    <button>Sửa</button>
</form>
</body>
</html>
