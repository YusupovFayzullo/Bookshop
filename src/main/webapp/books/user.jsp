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
        <% int c=0; %>
        <c:forEach items="${books}" var="book">
            <div class="card">
                <img src="/download?filename=${book.getCoverGeneratedFileName()}" style="width: 250px; height: 150px;" class="card-img-top " alt="${book.getCoverOriginalFileName()}" />
                <p style="text-align: center">Ko'rishlar soni:${book.getViews()} </p>
                <a href="/download?filename=${book.getDocumentGeneratedFileName()}" style="text-decoration: none; text-align: center">Yuklab olish</a>
                <div class="card-body">
                    <h6 class="card-title" style="text-align: center"> ${book.getTitle()}</h6>
                    <h6 class="card-title"> Janr/bo'lim:  ${book.getCategory()}</h6>
                    <h6 class="card-title"> Betlar soni: ${book.getPages()}</h6>
                    <h6 class="card-title"> Muallif:    <a href="${book.getUrl()}" style="text-decoration: none"> ${book.getAuthor()} </a></h6>
                    <h6 class="card-title"> Nashr yili: ${book.getPublishedAt()}</h6>
                    <p class="card-text">${book.getDescription()}</p>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
