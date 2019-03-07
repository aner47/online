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
    <span class="view-label">角色名称</span><span class="view-value">${systemRole.name}</span>
</div>
<div class="form-line">
    <span class="view-label">ID</span><span class="view-value">${systemRole.id}</span>
</div>
<div class="form-line">
    <span class="view-label">创建日期</span><span class="view-value">${systemRole.createDate}</span>
</div>
<div class="form-line">
    <span class="view-label">修改日期</span><span class="view-value">${systemRole.modifyDate}</span>
</div>
    </div>

</form>
</body>
</html>