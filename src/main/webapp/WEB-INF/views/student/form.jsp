<%--
  Created by IntelliJ IDEA.
  User: wojtek
  Date: 13.10.2020
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>student</title>
</head>
<body>
    <form:form action="/student/add" modelAttribute="student" method="post">

        gender (radio button)
        country (select z możliwością pojedynczego wyboru)

        programmingSkills (select z możliwością wyboru wielu opcji)

        <form:label path="firstName">First name:</form:label>
        <form:input path="firstName" /><br />
        <form:label path="lastName">Last name:</form:label>
        <form:input path="lastName" /><br />
        <form:label path="gender">Gender:</form:label>
        <form:radiobutton path="gender" />
        <form:radiobutton path="gender" /><br />
        <form:label path="country">country:</form:label>
        <form:input path="country" /><br />
        <form:label path="notes">notes:</form:label>
        <form:textarea path="notes" /><br />
        <form:label path="mailingList">mailingList:</form:label>
        <form:checkbox path="mailingList" /><br />

<%--        Php: <form:select path="programmingSkills" multiple="true" items="" />--%>
<%--        Java: <form:checkbox path="programmingSkills" value="java"/>--%>
<%--        Ruby: <form:checkbox path="programmingSkills" value="ruby"/>--%>
<%--        Python: <form:checkbox path="programmingSkills" value="python"/>--%>
<%--        <br />--%>
        games: <form:checkbox path="hobbies" value="games"/>
        movies: <form:checkbox path="hobbies" value="movies"/>
        fishing: <form:checkbox path="hobbies" value="fishing"/>
        biking: <form:checkbox path="hobbies" value="biking"/>
        <br />

        <input type="submit" />
    </form:form>
</body>
</html>
