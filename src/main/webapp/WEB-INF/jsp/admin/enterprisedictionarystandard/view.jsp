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
    <span class="view-label">企业名称:</span><span class="view-value">${taskManager.enterpriseName}</span>
</div>
<div class="form-line">
    <span class="view-label">联系人:</span><span class="view-value">${taskManager.contactPerson}</span>
</div>
<div class="form-line">
    <span class="view-label">联系电话:</span><span class="view-value">${taskManager.contactPhone}</span>
</div>
<div class="form-line">
    <span class="view-label">县（地区，街道）:</span><span class="view-value">${taskManager.countyName}</span>
</div>
<div class="form-line">
    <span class="view-label">执行人:</span><span class="view-value">${taskManager.executor}</span>
</div>
<div class="form-line">
    <span class="view-label">详细地址:</span><span class="view-value">${taskManager.detailAddress}</span>
</div>
<div class="form-line">
    <span class="view-label">任务状态:</span><span class="view-value">${taskManager.status}</span>
</div>
<div class="form-line">
    <span class="view-label">所属项目Id:</span><span class="view-value">${taskManager.projectId}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${taskManager.id}</span>
</div>
    </div>

</form>
</body>
</html>