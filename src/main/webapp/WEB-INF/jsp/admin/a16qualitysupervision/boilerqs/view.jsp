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
    <span class="view-label">所在地:</span><span class="view-value">${boilerQs.palce}</span>
</div>
<div class="form-line">
    <span class="view-label">行政区:</span><span class="view-value">${boilerQs.city}</span>
</div>
<div class="form-line">
    <span class="view-label">详细地址:</span><span class="view-value">${boilerQs.houseNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">中心经度:</span><span class="view-value">${boilerQs.longitude}</span>
</div>
<div class="form-line">
    <span class="view-label">中心纬度:</span><span class="view-value">${boilerQs.latitude}</span>
</div>
<div class="form-line">
    <span class="view-label">锅炉型号:</span><span class="view-value">${boilerQs.boilerModel}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料类型:</span><span class="view-value">${boilerQs.fuelType}</span>
</div>
<div class="form-line">
    <span class="view-label">蒸吨数:</span><span class="view-value">${boilerQs.steamTon}</span>
</div>
<div class="form-line">
    <span class="view-label">上次检测日期:</span><span class="view-value">${boilerQs.lastExamineDate}</span>
</div>
<div class="form-line">
    <span class="view-label">下次检测日期:</span><span class="view-value">${boilerQs.nextExamineDate}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${boilerQs.id}</span>
</div>
    </div>

</form>
</body>
</html>