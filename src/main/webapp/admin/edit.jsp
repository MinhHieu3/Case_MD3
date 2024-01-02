<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/2/2024
  Time: 8:33 PM
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
    <table border="1">
        <tr>
            <input type="text" name="name" placeholder="name">
            <input type="text" name="quantity" placeholder="quantity">
            <input type="text" name="price" placeholder="price">
            <input type="text" name="status" placeholder="status">
            <select name="idType">
                <c:forEach items="${id}" var="ty">
                    <option value="${ty.id}">${ty.name}</option>
                </c:forEach>
            </select>
            <button>OKE</button>
        </tr>
    </table>
</form>
</body>
</html>
