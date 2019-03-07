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
    <span class="view-label">地区:</span><span class="view-value">${project.areaCode}</span>
</div>
<div class="form-line">
    <span class="view-label">名称:</span><span class="view-value">${project.name}</span>
</div>
<div class="form-line">
    <span class="view-label">开始时间:</span><span class="view-value">${project.startDate}</span>
</div>
<div class="form-line">
    <span class="view-label">结束时间:</span><span class="view-value">${project.endDate}</span>
</div>
<div class="form-line">
    <span class="view-label">邀请码:</span><span class="view-value">${project.invitationCode}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${project.id}</span>
</div>
    </div>

</form>
</body>
</html>