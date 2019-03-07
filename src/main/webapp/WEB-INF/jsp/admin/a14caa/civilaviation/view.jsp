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
    <span class="view-label">详细地址:</span><span class="view-value">${civilAviation.houseNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">中心经度:</span><span class="view-value">${civilAviation.longitude}</span>
</div>
<div class="form-line">
    <span class="view-label">中心纬度:</span><span class="view-value">${civilAviation.latitude}</span>
</div>
<div class="form-line">
    <span class="view-label">客运量（万人次）:</span><span class="view-value">${civilAviation.passengerCapacity}</span>
</div>
<div class="form-line">
    <span class="view-label">货运量（万吨）:</span><span class="view-value">${civilAviation.commodityCapacity}</span>
</div>
<div class="form-line">
    <span class="view-label">总起降次数:</span><span class="view-value">${civilAviation.totalOffLand}</span>
</div>
<div class="form-line">
    <span class="view-label">一月:</span><span class="view-value">${civilAviation.january}</span>
</div>
<div class="form-line">
    <span class="view-label">二月:</span><span class="view-value">${civilAviation.february}</span>
</div>
<div class="form-line">
    <span class="view-label">三月:</span><span class="view-value">${civilAviation.march}</span>
</div>
<div class="form-line">
    <span class="view-label">四月:</span><span class="view-value">${civilAviation.april}</span>
</div>
<div class="form-line">
    <span class="view-label">五月:</span><span class="view-value">${civilAviation.may}</span>
</div>
<div class="form-line">
    <span class="view-label">六月:</span><span class="view-value">${civilAviation.june}</span>
</div>
<div class="form-line">
    <span class="view-label">七月:</span><span class="view-value">${civilAviation.july}</span>
</div>
<div class="form-line">
    <span class="view-label">八月:</span><span class="view-value">${civilAviation.august}</span>
</div>
<div class="form-line">
    <span class="view-label">九月:</span><span class="view-value">${civilAviation.september}</span>
</div>
<div class="form-line">
    <span class="view-label">十月:</span><span class="view-value">${civilAviation.october}</span>
</div>
<div class="form-line">
    <span class="view-label">十一月:</span><span class="view-value">${civilAviation.november}</span>
</div>
<div class="form-line">
    <span class="view-label">十二月:</span><span class="view-value">${civilAviation.december}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${civilAviation.id}</span>
</div>
    </div>

</form>
</body>
</html>