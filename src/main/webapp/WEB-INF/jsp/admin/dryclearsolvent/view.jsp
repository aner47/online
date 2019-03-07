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
    <span class="view-label">锅炉类型:</span><span class="view-value">${boilerInformation.boilerType}</span>
</div>
<div class="form-line">
    <span class="view-label">锅炉名称:</span><span class="view-value">${boilerInformation.boilerName}</span>
</div>
<div class="form-line">
    <span class="view-label">锅炉蒸吨:</span><span class="view-value">${boilerInformation.steamTon}</span>
</div>
<div class="form-line">
    <span class="view-label">锅炉蒸吨:</span><span class="view-value">${boilerInformation.operation}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料类型:</span><span class="view-value">${boilerInformation.fuelType}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料消耗量:</span><span class="view-value">${boilerInformation.fuelConsumption}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料单位:</span><span class="view-value">${boilerInformation.fuelUnit}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料含硫率:</span><span class="view-value">${boilerInformation.fuelSulfurContent}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料灰分:</span><span class="view-value">${boilerInformation.fuelAsh}</span>
</div>
<div class="form-line">
    <span class="view-label">燃料挥发分:</span><span class="view-value">${boilerInformation.fuelVolatiles}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${boilerInformation.id}</span>
</div>
    </div>

</form>
</body>
</html>