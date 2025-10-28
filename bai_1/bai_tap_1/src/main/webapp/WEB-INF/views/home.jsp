<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
<h2>Chuyển đổi từ USD sang VNĐ</h2>
<form action="convert" method="post">
    <label>Tỉ giá (VNĐ/USD): </label>
    <input type="number" step="0.01" name="rate" required><br><br>
    <label>Số tiền USD: </label>
    <input type="number" step="0.01" name="usd" required><br><br>
    <input type="submit" value="Chuyển đổi">
</form>
</body>
</html>
