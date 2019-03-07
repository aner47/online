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
    <span class="view-label">料堆类型:</span><span class="view-value">${openYard.materialType}</span>
</div>
<div class="form-line">
    <span class="view-label">堆场装卸总量(吨):</span><span class="view-value">${openYard.loadAmount}</span>
</div>
<div class="form-line">
    <span class="view-label">每年物料运载车次（车）:</span><span class="view-value">${openYard.cargoTrips}</span>
</div>
<div class="form-line">
    <span class="view-label">每车运载量(吨/车):</span><span class="view-value">${openYard.carryAmount}</span>
</div>
<div class="form-line">
    <span class="view-label">料堆占地面积(平方米):</span><span class="view-value">${openYard.area}</span>
</div>
<div class="form-line">
    <span class="view-label">料堆最高高度(米):</span><span class="view-value">${openYard.height}</span>
</div>
<div class="form-line">
    <span class="view-label">物料含水率（%）:</span><span class="view-value">${openYard.moistureContent}</span>
</div>
<div class="form-line">
    <span class="view-label">出入车辆是否清洗:</span><span class="view-value">${openYard.carCleaning}</span>
</div>
<div class="form-line">
    <span class="view-label">是否进行破碎、筛选:</span><span class="view-value">${openYard.broken}</span>
</div>
<div class="form-line">
    <span class="view-label">破碎环境是否封闭:</span><span class="view-value">${openYard.closed}</span>
</div>
<div class="form-line">
    <span class="view-label">措施1:</span><span class="view-value">${openYard.measure1}</span>
</div>
<div class="form-line">
    <span class="view-label">措施2:</span><span class="view-value">${openYard.measure2}</span>
</div>
<div class="form-line">
    <span class="view-label">措施3:</span><span class="view-value">${openYard.measure3}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${openYard.id}</span>
</div>
    </div>

</form>
</body>
</html>