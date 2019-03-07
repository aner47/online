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
    <span class="view-label">机械类型:</span><span class="view-value">${airportMachinery.machineryType}</span>
</div>
<div class="form-line">
    <span class="view-label">机械保有量（台/辆）:</span><span class="view-value">${airportMachinery.machineryInventory}</span>
</div>
<div class="form-line">
    <span class="view-label">单台机械平均功率（千瓦）:</span><span class="view-value">${airportMachinery.avgPower}</span>
</div>
<div class="form-line">
    <span class="view-label">机械总功率（千瓦）:</span><span class="view-value">${airportMachinery.totalPower}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料类型:</span><span class="view-value">${airportMachinery.fuelType}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料消耗量（吨）:</span><span class="view-value">${airportMachinery.fuelConsume}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料含硫率（％）:</span><span class="view-value">${airportMachinery.fuelSulfurRate}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${airportMachinery.id}</span>
</div>
    </div>

</form>
</body>
</html>