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
    <span class="view-label">区域名称:</span><span class="view-value">${area.name}</span>
</div>
<div class="form-line">
    <span class="view-label">区域等级:</span><span class="view-value">${area.level}</span>
</div>
<div class="form-line">
    <span class="view-label">区域编码:</span><span class="view-value">${area.code}</span>
</div>
<div class="form-line">
    <span class="view-label">上级区域等级编码:</span><span class="view-value">${area.parent}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${area.id}</span>
</div>
    </div>

</form>
</body>
</html>