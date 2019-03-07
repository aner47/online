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
    <span class="view-label">行政区:</span><span class="view-value">${enterpriseFuelGas.city}</span>
</div>
<div class="form-line">
    <span class="view-label">详细地址:</span><span class="view-value">${enterpriseFuelGas.houseNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">天然气消耗量1:</span><span class="view-value">${enterpriseFuelGas.year1}</span>
</div>
<div class="form-line">
    <span class="view-label">天然气消耗量2:</span><span class="view-value">${enterpriseFuelGas.year2}</span>
</div>
<div class="form-line">
    <span class="view-label">天然气消耗量3:</span><span class="view-value">${enterpriseFuelGas.year3}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${enterpriseFuelGas.id}</span>
</div>
    </div>

</form>
</body>
</html>