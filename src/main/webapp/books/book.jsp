<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/12/2023
  Time: 07:45
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Book Page</title>
</head>
<style>
    /* Style the search field */
    form.example input[type=text] {
        padding: 10px;
        font-size: 17px;
        border: 1px solid grey;
        float: left;
        width: 80%;
        background: #f1f1f1;
    }

    /* Style the submit button */
    form.example button {
        float: left;
        width: 20%;
        padding: 10px;
        background: #2196F3;
        color: white;
        font-size: 17px;
        border: 1px solid grey;
        border-left: none; /* Prevent double borders */
        cursor: pointer;
    }

    form.example button:hover {
        background: #0b7dda;
    }

    /* Clear floats */
    form.example::after {
        content: "";
        clear: both;
        display: table;
    }

    body{
        background-color: #0dcaf0;
    }
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        width: 200px;
        background-color: #f1f1f1;
    }

    li a {
        display: block;
        color: #000;
        padding: 8px 16px;
        text-decoration: none;
    }

    li a:hover:not(.active) {
        background-color: #555;
        color: white;
    }
</style>
<body>


<form method="post" action="/search">

    <div class="col-md-4 offset-1">
        <select class="form-select"   aria-label="select example" style="margin-top: 30px" id="category_id"
                name="category_id">
            <option value="0">Kategoriya tanlang</option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.getId()}">${category.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <input type="text" placeholder="Search.." name="search">
    <button type="button" class="btn btn-primary"> Search </button>
</form>

     <br>


<ul>
    <li><a  href="/">Home</a></li>
    <li><a href="/addbook">Add Book</a></li>
    <li><a href="/booklist">Book List</a></li>
    <li><a href="#about">About</a></li>
</ul>

</body>
</html>
