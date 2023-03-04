<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2023
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>

<style>

    *{
        margin: 0;
        padding: 0;
        text-decoration: none;
        list-style: none;
        box-sizing: border-box;
        font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    }


    body {
        background-image: url('https://images.unsplash.com/photo-1481627834876-b7833e8f5570?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bGlicmFyeXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60');
    }


    .block {
        position: absolute;
        top:50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
    }

    table, tr, td{
        outline: none;
    }
    h1{
        margin-bottom: 20px;
        font-size: 40px;
        margin-left: 45px;
    }

    input{
        padding: 5px 10px;
    }
    .Email {
        margin-left: 34px;
        margin-bottom: 10px;
    }

    .Password {
        margin-left: 38px;
        margin-bottom: 10px;
    }

    .btn {
        margin-top: 10px;
        margin-left: 91px;
        width: 170px;
        height: 30px;
        background-color: blue;
        color:white;
        transition: 0.5s ease;
        border: none;
    }
    .signupbtn {
        margin-top: 10px;
        margin-left: 91px;
        width: 170px;
        height: 30px;
        background-color: green;
        color:white;
        transition: 0.5s ease;
        border: none;


    }
    .k{
        margin-left: 7px;
    }


    button:hover {
        background-color:white;
        color:black;
    }

</style>
<body>



    <form method="post" action="/home">
    <div class="block">
        <h1>Kutubxonamizga xush kelibsiz</h1>


                  <div class="k">
                    <label for="email" autofocus>Email:</label>
                    <input type="email" id="email" class="Email" placeholder="Email kiriting " name="email">
                     <br>
                    <label for="password">Parol:</label>
                    <input type="password" id="password" class="Password" placeholder="Parol kiriting " name="password" required>
                  </div>

        <button type="submit" class="btn">Kirish</button> <br>

        <a href="/register"> <button type="button" class="signupbtn">Ro'yxatdan o'tish</button> </a>
    </div>

    </form>
</body>

</html>
