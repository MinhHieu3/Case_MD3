<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 12/29/2023
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Slide Navbar</title>
    <link rel="stylesheet" type="text/css" href="cssLogin/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body style="background:url('https://cdn.24h.com.vn/upload/3-2018/images/2018-08-11/Da-dep-dang-xinh-voi-sua-chua-uong-hoa-qua-tu-tay-pha-che-2-1533977400-740-width650height433.jpg') ;
!important ;">
<div class="main">
    <input type="checkbox" id="chk" aria-hidden="true">
    <div class="signup">
        <form method="post" action="loginUsers">
            <label for="chk" aria-hidden="true" style="color: #573b8a ">Sign up</label>
            <input type="text" name="name" placeholder="Name" required="">
            <input type="number" name="phone" placeholder="Phone" required="">
            <input type="text" name="username" placeholder="Username" required="">
            <input type="password" name="password" placeholder="Password" required="">
            <button>Sign up</button>
        </form>
    </div>

    <div class="login">
        <form method="post" action="loginUsers">
            <label for="chk" aria-hidden="true">Login</label>
            <input type="text" name="user" placeholder="Username" required="">
            <input type="password" name="pass" placeholder="Password" required="">
            <button>Login</button>
        </form>
    </div>
</div>
<<<<<<< HEAD

=======
>>>>>>> ece102ac044995d951cf902faaed7b14e149c967
</body>
</html>