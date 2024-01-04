<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 1/3/2024
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="cssLogin/loginAdmin.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<div class="login">
    <form method="post" action="loginAdmin">
        <label aria-hidden="true">Login Acount Admin</label>
        <input type="text" name="userAdmin" placeholder="userAdmin" required="">
        <input type="password" name="passAdmin" placeholder="passAdmin" required="">
        <button>Login Admin</button>
    </form>
   <div class="exit"> <a class="exit1" href="/homeUser">Exit</a></div>
</div>


</body>
</html>
