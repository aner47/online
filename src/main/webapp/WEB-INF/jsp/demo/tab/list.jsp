<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<title></title>
<style>
#tabs {
	margin-top: 40px;
	height: 600px;
	width: 100%;
}
</style>
<script>
	require([ "tab" ], function() {
		$("#tabs").tab({
			tabs : [ {
				caption : "测试1",
				html : "欢迎使用tab插件"
			}, {
				caption : "百度",
				url : "http://www.baidu.com",
				iframe : true
			}, {
				caption : "菜单管理",
				url : base+"/admin/systemmenu/list.jhtml",
				handle : function() {
					
				}
			} ]
		})

	})
</script>
</head>
<body>
	<div id="tabs"></div>
</body>
</html>