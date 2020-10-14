<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Do you want to delete Book: ${book.title}</>
<a href="/book">No</a>
<a href="/book/delete/${book.id}?accept=true">Yes</a>

</body>
</html>