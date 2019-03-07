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
    <span class="view-label">编号:</span><span class="view-value">${loadingInformation.no}</span>
</div>
<div class="form-line">
    <span class="view-label">作业部/车间:</span><span class="view-value">${loadingInformation.location}</span>
</div>
<div class="form-line">
    <span class="view-label">物料类型:</span><span class="view-value">${loadingInformation.materialType}</span>
</div>
<div class="form-line">
    <span class="view-label">装载方式:</span><span class="view-value">${loadingInformation.loadMethod}</span>
</div>
<div class="form-line">
    <span class="view-label">操作方式:</span><span class="view-value">${loadingInformation.operationMethod}</span>
</div>
<div class="form-line">
    <span class="view-label">年装载量:</span><span class="view-value">${loadingInformation.annualLoad}</span>
</div>
<div class="form-line">
    <span class="view-label">物料密度:</span><span class="view-value">${loadingInformation.materialDensity}</span>
</div>
<div class="form-line">
    <span class="view-label">回收技术:</span><span class="view-value">${loadingInformation.recycling}</span>
</div>
<div class="form-line">
    <span class="view-label">回收效率（%）:</span><span class="view-value">${loadingInformation.recoveryRate}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${loadingInformation.id}</span>
</div>
    </div>

</form>
</body>
</html>