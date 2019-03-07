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
    <span class="view-label">机组编号:</span><span class="view-value">${powerPlant.no}</span>
</div>
<div class="form-line">
    <span class="view-label">装机容量（兆瓦）:</span><span class="view-value">${powerPlant.installedCapacity}</span>
</div>
<div class="form-line">
    <span class="view-label">投运时间:</span><span class="view-value">${powerPlant.putOnTime}</span>
</div>
<div class="form-line">
    <span class="view-label">关停时间:</span><span class="view-value">${powerPlant.stopTime}</span>
</div>
<div class="form-line">
    <span class="view-label">年发电量（万千瓦时）:</span><span class="view-value">${powerPlant.annualPowerGeneration}</span>
</div>
<div class="form-line">
    <span class="view-label">年供热量（吉焦）:</span><span class="view-value">${powerPlant.annualHeatSupply}</span>
</div>
<div class="form-line">
    <span class="view-label">锅炉类型:</span><span class="view-value">${powerPlant.boilerType}</span>
</div>
<div class="form-line">
    <span class="view-label">锅炉型号:</span><span class="view-value">${powerPlant.boilerModel}</span>
</div>
<div class="form-line">
    <span class="view-label">锅炉蒸吨(蒸吨/小时):</span><span class="view-value">${powerPlant.steamTon}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料消耗量:</span><span class="view-value">${powerPlant.fuelConsumption}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料单位:</span><span class="view-value">${powerPlant.fuelUnit}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料含硫率:</span><span class="view-value">${powerPlant.fuelSulfurContent}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料灰分:</span><span class="view-value">${powerPlant.fuelAshContent}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料挥发分:</span><span class="view-value">${powerPlant.fuelVolatiles}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${powerPlant.id}</span>
</div>
    </div>

</form>
</body>
</html>