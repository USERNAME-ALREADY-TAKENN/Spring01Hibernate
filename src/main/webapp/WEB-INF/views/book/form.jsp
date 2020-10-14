<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Book ${book.id == null ? "add" : "edit"} form</h1>

<%--    <c:if test="${not empty errors}">--%>
<%--        <h4>Errors</h4>--%>
<%--        <c:forEach items="${errors}" var="error">--%>
<%--            <p>${error.propertyPath}:  ${error.message}</p>--%>
<%--        </c:forEach>--%>
<%--    </c:if>--%>


<form:form method="post" action="${pageContext.request.contextPath}/book/save" modelAttribute="book">
    <form:hidden path="id"/>
    <section>
        <form:input path="title" placeholder="Title"/>
        <form:errors path="title"/>
    </section>

    <section>
        <form:input path="rating" placeholder="Rating"/>
        <form:errors path="rating"/>
    </section>
    <section>
        <form:textarea path="description" placeholder="Description"/>
        <form:errors path="description"/>
    </section>
    <section>
        <form:input path="pages" placeholder="Page count"/>
        <form:errors path="pages"/>
    </section>
    <section>
        <form:select path="publisher">
            <form:option value="-">Select publisher</form:option>
            <form:options items="${publishers}" itemLabel="name" itemValue="id"/>
        </form:select>
        <form:errors path="publisher"/>
    </section>
    <section>

        <form:select path="authors" multiple="true">
            <from:options items="${authors}" itemLabel="fullName" itemValue="id"/>
        </form:select>
        <form:errors path="authors"/>
    </section>
    <section>

        <button type="submit">Save</button>
    </section>

</form:form>

</body>
</html>
