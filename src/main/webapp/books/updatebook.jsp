<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Update Book Page</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<div class="row">
    <div class="col-md-10 offset-1">
        <h3 class="text-center">Update Book Page</h3>
        <form method="post" action="/books/update/${book.getId()}" enctype="multipart/form-data">
            <input type="hidden"  name="id" value="${book.getId()}">

            <div class="col-md-5 offset-1">
                <label for="validationServer01" class="form-label">Title</label>
                <input type="text" class="form-control is-valid" id="validationServer01" name="title" value="${book.getTitle()}" >

            </div>
            <div class="col-md-4 offset-1">
                <label for="validationServer02" class="form-label">Author(s)</label>
                <input type="text" class="form-control is-valid" id="validationServer02" name="author" value="${book.getAuthor()}">
            </div>
            <div class="col-md-4 offset-1">
                <label for="validationServer04" class="form-label">Author url</label>
                <input type="text" class="form-control is-valid" id="validationServer04" name="url" value="${book.getUrl()}">
            </div>
            <div class="col-md-4 offset-1">
                <label for="valid" class="form-label">Pages</label>
                <input type="number" class="form-control is-valid" id="valid" name="pages" value="${book.getPages()}">
            </div>
            <div class="col-md-5 offset-1">
                <label for="validationServer03" class="form-label">Publisher</label>
                <input type="text" class="form-control" id="validationServer03" name="publisher" value="${book.getPublisher()}">
            </div>

            <div class="col-md-4 offset-1">
                <select class="form-select" required aria-label="select example" style="margin-top: 30px" id="category_id" name="category_id">
                    <option value="0" >Choose a category</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.getId()}">${category.getName()}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">Example invalid select feedback</div>
            </div>

            <div class="col-md-10 offset-1" style="margin-bottom: 10px">
                <label for="validationTextarea1" class="form-label">Description</label>
                <textarea class="form-control " rows="3" id="validationTextarea1" name="description"
                          placeholder="Write the description of the book here" > ${book.getDescription()} </textarea>
                <div class="invalid-feedback">
                    field
                </div>
            </div>

            <div class="col-sm-2 offset-1">
                <label for="date" class="form-label">Date of publication</label>
                <div class="input-group date" id="datepicker">
                    <input type="date" class="form-control" id="date" name="publishedAt" placeholder="02/09/2023"  />
                    <i class="fa fa-calendar"></i>
                </div>
            </div>

            <div class="col-md-3 offset-1">
                <label for="image" class="form-label">Upload the Image File</label>

                <input type="file" class="form-control" id="image" name="image" aria-label="file example" >
                <div class="invalid-feedback">Example invalid form file feedback</div>
            </div>

            <div class="col-md-3 offset-1">
                <label for="file" class="form-label">Upload the PDF File</label>
                <input type="file" class="form-control" id="file" name="file" aria-label="file example"  >
                <div class="invalid-feedback">Example invalid form file feedback</div>
            </div>


            <div class="col-12 offset-1">
                <div class="form-check">
                    <input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3"
                           aria-describedby="invalidCheck3Feedback"  >
                    <label class="form-check-label" for="invalidCheck3">
                        <a href="" style="text-decoration: none"> Agree to terms and conditions</a>
                    </label>
                    <div id="invalidCheck3Feedback" class="invalid-feedback">
                        You must agree before submitting.
                    </div>
                </div>
            </div>
            <a href="/booklist" class="btn btn-warning">Back</a>
            <button type="submit" class="btn btn-primary">Update Book</button>
        </form>
    </div>
</div>
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<jsp:include page="/fragments/css.jsp"/>
</body>
</html>