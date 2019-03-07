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
    <span class="view-label">项目名称:</span><span class="view-value">${fieldShowConfig.projectId}</span>
</div>
<div class="form-line">
    <span class="view-label">填报类型:</span><span class="view-value">${fieldShowConfig.tableType}</span>
</div>
<div class="form-line">
    <span class="view-label">显示菜单:</span><span class="view-value">${fieldShowConfig.fieldModule}</span>
</div>
<div class="form-line">
    <span class="view-label">隐藏字段:</span><span class="view-value">${fieldShowConfig.hideField}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${fieldShowConfig.id}</span>
</div>
    </div>

</form>
</body>
</html>