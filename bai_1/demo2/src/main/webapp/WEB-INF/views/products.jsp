<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        /* Đặt vị trí Toast */
        #toastContainer {
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1080;
        }
    </style>
</head>
<body class="p-4">
<h1 class="mb-4">Product List</h1>
<form action="${pageContext.request.contextPath}/products/search" method="get" class="d-flex mb-3">
    <input type="text" name="keyword" value="${keyword}" class="form-control me-2" placeholder="Search by name...">
    <button type="submit" class="btn btn-outline-primary">Search</button>
</form>
<!-- Toast Container -->
<div id="toastContainer" aria-live="polite" aria-atomic="true">
    <c:if test="${not empty message}">
        <div class="toast align-items-center text-bg-success border-0 show" id="successToast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                        ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </c:if>
</div>

<a href="${pageContext.request.contextPath}/products/create" class="btn btn-success mb-3">Create new product</a>

<table class="table table-bordered table-striped">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>
                <a href="${pageContext.request.contextPath}/products/edit/${p.id}" class="btn btn-primary btn-sm">Edit</a>
                <button class="btn btn-danger btn-sm" onclick="confirmDelete(${p.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Modal xác nhận xóa -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this product?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Delete</a>
            </div>
        </div>
    </div>
</div>

<script>
    // Hiển thị Toast tự động nếu có message
    document.addEventListener('DOMContentLoaded', function () {
        const toastEl = document.getElementById('successToast');
        if (toastEl) {
            const toast = new bootstrap.Toast(toastEl, {delay: 3000});
            toast.show();
        }
    });

    // Hiển thị modal xác nhận xóa
    function confirmDelete(id) {
        const contextPath = '${pageContext.request.contextPath}';
        const deleteUrl = `${contextPath}/products/delete?id=` + id;
        document.getElementById('confirmDeleteBtn').href = deleteUrl;
        const modal = new bootstrap.Modal(document.getElementById('deleteModal'));
        modal.show();
    }
</script>
</body>
</html>
