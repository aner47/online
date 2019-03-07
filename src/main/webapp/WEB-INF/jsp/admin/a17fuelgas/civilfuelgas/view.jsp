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
    <span class="view-label">行政区:</span><span class="view-value">${civilFuelGas.city}</span>
</div>
<div class="form-line">
    <span class="view-label">乡镇（街道）:</span><span class="view-value">${civilFuelGas.county}</span>
</div>
<div class="form-line">
    <span class="view-label">村（居委会）:</span><span class="view-value">${civilFuelGas.village}</span>
</div>
<div class="form-line">
    <span class="view-label">天然气消耗量1:</span><span class="view-value">${civilFuelGas.year1}</span>
</div>
<div class="form-line">
    <span class="view-label">天然气消耗量2:</span><span class="view-value">${civilFuelGas.year2}</span>
</div>
<div class="form-line">
    <span class="view-label">天然气消耗量3:</span><span class="view-value">${civilFuelGas.year3}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${civilFuelGas.id}</span>
</div>
    </div>

</form>
</body>
</html>