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
    <style>
        th {
            text-align: right;
            font-weight: bold;
            padding-right: 15px;
        }
        table {
            margin-left: 50px;
            padding-bottom: 15px;
        }
        .title {
            font-weight: bold;
            font-size: large;
        }
    </style>
</head>
<body>
    <h1>book list</h1>
    <c:forEach items="${books}" var="book">
    <table>
        <tbody>
            <tr>
                <td></td>
                <td>
                    <span class="title">${book.title}</span>
                    <a href="/book/edit/${book.id}">Edit</a>
                    <a href="/book/delete/${book.id}">Delete</a>
                </td>
            </tr>
            <tr>
                <th>Autorzy: </th>
                <td>
                    <c:forEach items="${book.authors}" var="author">
                        ${author.firstName} ${author.lastName},
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>Wydawca: </th>
                <td>${book.publisher.name}</td>
            </tr>
            <tr>
                <th>Strony: </th>
                <td>${book.pages}</td>
            </tr>
            <tr>
                <th>Ocena: </th>
                <td>
                    <c:forEach begin="1" end="${book.rating}">
                        <img src="https://i.imgur.com/VtqJRKf.png" width="15px" height="15px"/>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>Opis: </th>
                <td><em>${book.description}</em></td>
            </tr>
        </tbody>
    </table>
    </c:forEach>
</body>
</html>
