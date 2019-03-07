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
    <span class="view-label">数据关键字:</span><span class="view-value">${moduleDetail.key}</span>
</div>
<div class="form-line">
    <span class="view-label">对应表头:</span><span class="view-value">${moduleDetail.head}</span>
</div>
<div class="form-line">
    <span class="view-label">排序字段:</span><span class="view-value">${moduleDetail.moduleOrder}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${moduleDetail.id}</span>
</div>
    </div>

</form>
</body>
</html>