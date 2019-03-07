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
    <span class="view-label">法定代表人/联系人:</span><span class="view-value">${inCoDryClean.contacts}</span>
</div>
<div class="form-line">
    <span class="view-label">联系电话:</span><span class="view-value">${inCoDryClean.contactNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">行政区:</span><span class="view-value">${inCoDryClean.city}</span>
</div>
<div class="form-line">
    <span class="view-label">详细地址:</span><span class="view-value">${inCoDryClean.houseNumber}</span>
</div>
<div class="form-line">
    <span class="view-label">干洗剂用量/吨:</span><span class="view-value">${inCoDryClean.detergent}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${inCoDryClean.id}</span>
</div>
    </div>

</form>
</body>
</html>