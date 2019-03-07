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
    <span class="view-label">名称:</span><span class="view-value">${enterprise.name}</span>
</div>
<div class="form-line">
    <span class="view-label">行业类别:</span><span class="view-value">${enterprise.industryCategoryCode}</span>
</div>
<div class="form-line">
    <span class="view-label">组织编码:</span><span class="view-value">${enterprise.code}</span>
</div>
<div class="form-line">
    <span class="view-label">联系人:</span><span class="view-value">${enterprise.contacts}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${enterprise.id}</span>
</div>
    </div>

</form>
</body>
</html>