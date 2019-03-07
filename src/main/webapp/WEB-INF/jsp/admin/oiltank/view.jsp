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
    <span class="view-label">材料类型:</span><span class="view-value">${oilTank.tankMaterialType}</span>
</div>
<div class="form-line">
    <span class="view-label">储罐类型:</span><span class="view-value">${oilTank.tankType}</span>
</div>
<div class="form-line">
    <span class="view-label">高度（米）:</span><span class="view-value">${oilTank.height}</span>
</div>
<div class="form-line">
    <span class="view-label">平均直径（米）:</span><span class="view-value">${oilTank.avgDiameter}</span>
</div>
<div class="form-line">
    <span class="view-label">储罐容量（立方米）:</span><span class="view-value">${oilTank.capacity}</span>
</div>
<div class="form-line">
    <span class="view-label">全年使用天数（天）:</span><span class="view-value">${oilTank.workDays}</span>
</div>
<div class="form-line">
    <span class="view-label">储罐年总储量（吨）:</span><span class="view-value">${oilTank.storageCapacity}</span>
</div>
<div class="form-line">
    <span class="view-label">储存周期（天）:</span><span class="view-value">${oilTank.storageCycle}</span>
</div>
<div class="form-line">
    <span class="view-label">年装卸/填充次数:</span><span class="view-value">${oilTank.fillTimes}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${oilTank.id}</span>
</div>
    </div>

</form>
</body>
</html>