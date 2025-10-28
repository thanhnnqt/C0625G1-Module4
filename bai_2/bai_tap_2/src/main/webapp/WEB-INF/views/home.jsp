<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Calculator</h1>
<form action="calculate" method="post">
    <input type="number" name="num1" value="${num1}" step="any" required>
    <input type="number" name="num2" value="${num2}" step="any" required><br><br>
    <button name="operator" value="add">Addition(+)</button>
    <button name="operator" value="sub">Subtraction(-)</button>
    <button name="operator" value="mul">Multiplication(X)</button>
    <button name="operator" value="div">Division(/)</button><br>
</form>

<c:if test="${not empty result}">
    <div class="result">Result ${operation}: ${result}</div>
</c:if>
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>
</body>
</html>
