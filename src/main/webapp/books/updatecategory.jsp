<%--
  Created by IntelliJ IDEA.
  User: jlkesh
  Date: 06/02/23
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Category Page</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<div class="row">
    <div class="col-md-10 offset-1">
        <h3 class="text-center">Update Category Page</h3>
        <form method="post" action="/category/update/${category.getId()}">
            <input type="hidden"  name="id" value="${category.getId()}">
            <div class="mb-3">
                <label for="firstName" class="form-label">Name</label>
                <input type="text" class="form-control" id="firstName" name="name" value="${category.getName()}">
            </div>

            <a href="/category" class="btn btn-warning">Back</a>
            <button type="submit" class="btn btn-primary">Update Category</button>
        </form>
    </div>
</div>
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
