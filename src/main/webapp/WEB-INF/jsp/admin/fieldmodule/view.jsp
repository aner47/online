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
    <span class="view-label">模块类型:</span><span class="view-value">${moduleInfo.moduleType}</span>
</div>
<div class="form-line">
    <span class="view-label">模块名称:</span><span class="view-value">${moduleInfo.name}</span>
</div>
<div class="form-line">
    <span class="view-label">模块排序:</span><span class="view-value">${moduleInfo.order}</span>
</div>
<div class="form-line">
    <span class="view-label">状态:</span><span class="view-value">${moduleInfo.status}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${moduleInfo.id}</span>
</div>
    </div>

</form>
</body>
</html>