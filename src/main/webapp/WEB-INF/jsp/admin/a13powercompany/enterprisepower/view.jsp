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
    <span class="view-label">行政区:</span><span class="view-value">${enterprisePower.city}</span>
</div>
<div class="form-line">
    <span class="view-label">详细地址:</span><span class="view-value">${enterprisePower.houseNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">用电户号:</span><span class="view-value">${enterprisePower.powerNo}</span>
</div>
<div class="form-line">
    <span class="view-label">2017年用电量（万千瓦时）:</span><span class="view-value">${enterprisePower.electricityConsumption}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${enterprisePower.id}</span>
</div>
    </div>

</form>
</body>
</html>