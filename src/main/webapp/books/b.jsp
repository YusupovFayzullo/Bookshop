<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jlkesh
  Date: 09/02/23
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>

<div class="row">
    <div class="col-md-10 offset-1">

         <a href="/addbook" style="text-decoration: none">Add Book</a>
        <c:forEach items="${books}" var="book">
            <div class="card">
                <img src="/download?filename=${book.getCoverGeneratedFileName()}" style="width: 250px; height: 150px;" class="card-img-top " alt="${book.getCoverOriginalFileName()}" />
                <p style="text-align: center">Ko'rishlar soni: ${book.getViews()}</p>
                <div class="card-body">
                    <h6 class="card-title" style="text-align: center"> ${book.getTitle()}</h6>
                    <h6 class="card-title"> Janr/bo'lim:  ${book.getCategory()}</h6>
                    <h6 class="card-title"> Betlar soni: ${book.getPages()}</h6>
                    <h6 class="card-title"> Muallif:    <a href="${book.getUrl()}" style="text-decoration: none"> ${book.getAuthor()} </a></h6>
                    <h6 class="card-title"> Nashr yili: ${book.getPublishedAt()}</h6>
                    <p class="card-text">${book.getDescription()}</p>
                </div>
                <div class="card-body">
                    <a href="/books/update/${book.getId()}" class="card-link" style="text-decoration: none">Update</a>
                    <a href="/deleteBook/${book.getId()}" class="card-link" style="text-decoration: none">Delete</a>
                </div>
            </div>

        </c:forEach>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
