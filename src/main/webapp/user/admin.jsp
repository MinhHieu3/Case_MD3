<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/2/2024
  Time: 8:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    <title>admin</title>


</head>
<body>
accccc
<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>quantity</th>
        <th>price</th>
        <th>Type</th>
        <th>status</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${danhSach}" var="pr">
    <tr>
        <th>${danhSach.id}</th>
        <th>${danhSach.name}</th>
        <th>${danhSach.quantity}</th>
        <th>${danhSach.price}</th>
        <th>${danhSach.Type}</th>
        <th>${danhSach.status}</th>
    </tr>
</c:forEach>
    </tbody>

</table>


</body>
</html>
