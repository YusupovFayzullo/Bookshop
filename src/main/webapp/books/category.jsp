<%--
  Created by IntelliJ IDEA.
  User: jlkesh
  Date: 06/02/23
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Category List</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<div class="row">
    <div class="col-md-10 offset-1">
        <h3>Category List</h3>
        <%--        <a href="/students/add" class="btn btn-success">Add</a>--%>
        <button class="btn btn-success" data-bs-target="#exampleModalToggle" data-bs-toggle="modal">Add</button>
        <nav aria-label="...">
            <ul class="pagination mt-2">
                <c:if test="${hasPrevious}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${previous}">Previous</a>
                    </li>
                </c:if>
                <c:set value="${currentPage}" var="cur"/>
                <c:forEach begin="0" end="${pageCount}" var="i">
                    <li class="page-item ${cur == i ? "active":""}">
                        <a class="page-link" href="?page=${i}">${i+1}</a>
                    </li>
                </c:forEach>
                <c:if test="${hasNext}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${next}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categorys}" var="category">
                <tr>
                    <td>${category.getId()}</td>
                    <td>${category.getName()}</td>
                    <td>
                        <a class="btn btn-warning" href="/category/update/${category.getId()}">Update</a> ||
                        <a class="btn btn-danger" href="/category/delete/${category.getId()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel"
     tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalToggleLabel">Create Category</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="/category/add">
                    <div class="mb-3">
                        <label for="Name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="Name" name="name">
                    </div>

                    <button type="submit" class="btn btn-success">Save Category</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/main.js"></script>
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>

