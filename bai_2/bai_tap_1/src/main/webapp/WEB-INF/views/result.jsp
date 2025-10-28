<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h1>Your Sandwich Includes:</h1>
<ul>
    <c:forEach var="item" items="${condiments}">
        <li>${item}</li>
    </c:forEach>
</ul>
<a href="home">Back</a>
</body>
</html>
