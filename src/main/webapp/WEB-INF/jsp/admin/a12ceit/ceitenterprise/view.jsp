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
    <span class="view-label">法定代表人/联系人:</span><span class="view-value">${ceitEnterprise.contacts}</span>
</div>
<div class="form-line">
    <span class="view-label">联系电话:</span><span class="view-value">${ceitEnterprise.contactNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">行政区:</span><span class="view-value">${ceitEnterprise.city}</span>
</div>
<div class="form-line">
    <span class="view-label">详细地址:</span><span class="view-value">${ceitEnterprise.houseNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">用电量信息:</span><span class="view-value">${ceitEnterprise.electricityConsumption}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${ceitEnterprise.id}</span>
</div>
    </div>

</form>
</body>
</html>