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
    <span class="view-label">密封点(个:</span><span class="view-value">${leakedCheck.sealedPoint}</span>
</div>
<div class="form-line">
    <span class="view-label">泄露点(个:</span><span class="view-value">${leakedCheck.leakedSealedPoint}</span>
</div>
<div class="form-line">
    <span class="view-label">修复(个):</span><span class="view-value">${leakedCheck.fixedSealedPoint}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${leakedCheck.id}</span>
</div>
    </div>

</form>
</body>
</html>