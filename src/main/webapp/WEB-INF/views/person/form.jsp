<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 13.10.2020
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>person</title>
</head>
<body>
    <form:form method="post" action="/person/save" modelAttribute="person">
        <form:hidden path="id" />

        <form:label path="email">Email:</form:label>
        <form:input path="email" />

        <form:label path="login">Login:</form:label>
        <form:input path="login" />

        <form:label path="password">Password:</form:label>
        <form:password path="password" />

        <input type="submit" />

    </form:form>
</body>
</html>
