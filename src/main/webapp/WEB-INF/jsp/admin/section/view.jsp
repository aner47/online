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
    <span class="view-label">工艺名称:</span><span class="view-value">${governanceMeasures.name}</span>
</div>
<div class="form-line">
    <span class="view-label">年工作时间:</span><span class="view-value">${governanceMeasures.workHours}</span>
</div>
<div class="form-line">
    <span class="view-label">收集效率:</span><span class="view-value">${governanceMeasures.collectEfficiency}</span>
</div>
<div class="form-line">
    <span class="view-label">SO2:</span><span class="view-value">${governanceMeasures.so2}</span>
</div>
<div class="form-line">
    <span class="view-label">NOX:</span><span class="view-value">${governanceMeasures.nox}</span>
</div>
<div class="form-line">
    <span class="view-label">CO:</span><span class="view-value">${governanceMeasures.co}</span>
</div>
<div class="form-line">
    <span class="view-label">PM10:</span><span class="view-value">${governanceMeasures.pm10}</span>
</div>
<div class="form-line">
    <span class="view-label">PM2.5:</span><span class="view-value">${governanceMeasures.pm25}</span>
</div>
<div class="form-line">
    <span class="view-label">BC:</span><span class="view-value">${governanceMeasures.bc}</span>
</div>
<div class="form-line">
    <span class="view-label">OC:</span><span class="view-value">${governanceMeasures.oc}</span>
</div>
<div class="form-line">
    <span class="view-label">VOCs:</span><span class="view-value">${governanceMeasures.voc}</span>
</div>
<div class="form-line">
    <span class="view-label">NH3:</span><span class="view-value">${governanceMeasures.nh3}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${governanceMeasures.id}</span>
</div>
    </div>

</form>
</body>
</html>