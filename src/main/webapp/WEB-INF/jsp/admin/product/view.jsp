<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>

<form id="inputForm">
    <div class="container">
<div class="form-line">
    <span class="view-label">单位:</span><span class="view-value">${product.unit}</span>
</div>
<div class="form-line">
    <span class="view-label">名称:</span><span class="view-value">${product.name}</span>
</div>
<div class="form-line">
    <span class="view-label">年产量:</span><span class="view-value">${product.yield}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${product.id}</span>
</div>
    </div>

</form>
</body>
</html>