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
    <span class="view-label">菜单名称</span><span class="view-value">${systemMenu.name}</span>
</div>
<div class="form-line">
    <span class="view-label">链接地址</span><span class="view-value">${systemMenu.url}</span>
</div>
<div class="form-line">
    <span class="view-label">图标</span><span class="view-value">${systemMenu.icon}</span>
</div>
<div class="form-line">
    <span class="view-label">上级菜单</span><span class="view-value">${systemMenu.pid}</span>
</div>
<div class="form-line">
    <span class="view-label">菜单排序</span><span class="view-value">${systemMenu.sort}</span>
</div>
<div class="form-line">
    <span class="view-label">ID</span><span class="view-value">${systemMenu.id}</span>
</div>
<div class="form-line">
    <span class="view-label">创建日期</span><span class="view-value">${systemMenu.createDate}</span>
</div>
<div class="form-line">
    <span class="view-label">修改日期</span><span class="view-value">${systemMenu.modifyDate}</span>
</div>
    </div>

</form>
</body>
</html>