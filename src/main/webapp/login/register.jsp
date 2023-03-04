<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2023
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>

<style>
    .container {
        padding:1px;

    }
    body{
        background-color: darkslateblue;
    }

    /* Full-width input fields */
    input[type=text], input[type=email], input[type=password] {
        width: 28%;
        height: 5px;
        padding: 15px;
        margin-left: 60px;
        margin-top: 10px;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus,  input[type=email], input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    /* Overwrite default styles of hr */
    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    /* Set a style for the submit/register button */
    .registerbtn {
        background-color: #04AA6D;
        color: white;
        padding: 6px 20px;
        margin: 1px 410px;
        border: none;
        cursor: pointer;
        width: 200px;
        height: 40px;
        opacity: 0.9;
        border-radius: 7px;
    }

    .registerbtn:hover {
        background-color: red;
    }

    /* Add a blue text color to links */
    a {
        color: dodgerblue;
    }

</style>
<body>

<form method="post" action="/register">
    <div class="container">
        <h1 style="text-align: center;">Register</h1>

        <hr>

        <label for="firstname" style="margin-left: 300px;"><b>Ism</b></label>
        <input autofocus type="text" placeholder="Ismingizni kiriting" name="firstname" id="firstname" required style="margin-left: 161px;">
        <br>
        <label for="lastname" style="margin-left: 300px;" ><b>Familiya</b></label>
        <input type="text" placeholder="Familiyangizni kiriting" name="lastname" id="lastname" required style="margin-left: 125px;">
        <br>
        <label for="email" style="margin-left: 300px;" ><b>Email</b></label>
        <input type="email" placeholder="Emailingizni kiriting" name="email" id="email" required style="margin-left: 145px;">
        <br>
        <label for="password" style="margin-left: 300px;" ><b>Parol</b></label>
        <input type="password" placeholder="Parol kiriting" name="password" id="password" required style="margin-left: 149px;">
        <br>
        <label for="psw-repeat" style="margin-left: 300px;" ><b>Parolni tasdiqlang</b></label>
        <input type="password" placeholder="Parol tasdiqi" name="pre-password" id="psw-repeat" required>
        <c:if test="${password_error == null}">
            <snap class="small text-danger"><c:out value="${password_error}"></c:out></snap>
        </c:if>
        <br>
        <br>
        <hr>
        <button type="submit" class="registerbtn">Send</button>
    </div>
</form>
</body>
</html>
