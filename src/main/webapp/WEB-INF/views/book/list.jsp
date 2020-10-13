<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 13.10.2020
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>book list</title>
</head>
<body>
    <h1>book list</h1>
    <ul>
        <c:forEach items="${books}" var="book">
            <li><h3>${book.title}</h3>
            ${book.publisher.name}</li>
        </c:forEach>
    </ul>
</body>
</html>
