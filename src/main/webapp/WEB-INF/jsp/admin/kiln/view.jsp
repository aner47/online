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
    <span class="view-label">企业:</span><span class="view-value">${exhaustionHole.enterprise}</span>
</div>
<div class="form-line">
    <span class="view-label">类型:</span><span class="view-value">${exhaustionHole.type}</span>
</div>
<div class="form-line">
    <span class="view-label">高度:</span><span class="view-value">${exhaustionHole.height}</span>
</div>
<div class="form-line">
    <span class="view-label">直径:</span><span class="view-value">${exhaustionHole.diameter}</span>
</div>
<div class="form-line">
    <span class="view-label">温度:</span><span class="view-value">${exhaustionHole.temperature}</span>
</div>
<div class="form-line">
    <span class="view-label">流量:</span><span class="view-value">${exhaustionHole.flow}</span>
</div>
<div class="form-line">
    <span class="view-label">排放量:</span><span class="view-value">${exhaustionHole.emissions}</span>
</div>
<div class="form-line">
    <span class="view-label">so2浓度:</span><span class="view-value">${exhaustionHole.so2}</span>
</div>
<div class="form-line">
    <span class="view-label">nox浓度:</span><span class="view-value">${exhaustionHole.nox}</span>
</div>
<div class="form-line">
    <span class="view-label">tsp浓度:</span><span class="view-value">${exhaustionHole.tsp}</span>
</div>
<div class="form-line">
    <span class="view-label">voc浓度:</span><span class="view-value">${exhaustionHole.voc}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${exhaustionHole.id}</span>
</div>
    </div>

</form>
</body>
</html>