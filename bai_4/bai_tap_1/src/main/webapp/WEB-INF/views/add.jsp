<%--
  Created by IntelliJ IDEA.
  User: GAMING-PC
  Date: 29/10/2025
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1>Settings</h1>
<form:form method="post" modelAttribute="settings">
    Language: <form:select path="language" items="${language}"/><br>
    Page Sizes: Show <form:select path="pageSize" items="${pageSize}"/> email per page<br>
    Spams filter: <form:checkbox path="filter"/> Enable spams filter <br>
    Signature: <form:textarea path="sign" rows="5" cols="10"/> <br>
    <button type="submit">Update</button>
    <button>Cancel</button>
</form:form>
</body>
</html>
