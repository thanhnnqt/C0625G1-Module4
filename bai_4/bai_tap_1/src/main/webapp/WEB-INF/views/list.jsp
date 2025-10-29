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
    <title>Title</title>
</head>
<body>
<div class="container">
    <h1>Email</h1>
    <ul>
        <li><strong>Language:</strong> ${settings.language}</li>
        <li><strong>Page Size:</strong> ${settings.pageSize}</li>
        <li><strong>Spam Filter:</strong> ${settings.filter ? 'Enabled' : 'Disabled'}</li>
        <li><strong>Signature:</strong> ${settings.sign}</li>
    </ul>
    <a href="${pageContext.request.contextPath}/list">⬅ Back to Add</a>
</div>
</body>
</html>
