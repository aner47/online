<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导出excel</title>
<script type="text/javascript">
	require(["grid","select","panel"],function(g){
		var polluteSourceobj={'boiler':'锅炉','openYard':'露天','oilTank':'石油','pullutesource-other':'工段和炉窑'};
		var idTemp;
		var polluteSourceTemp;
		//项目选择
		var project = $("#project").select({name:"proejctCode",selected:true,codeValue:"name",code:"id",zIndex:8,url:"../project/queryAll.jhtml",initAfter:function(event, value){
			idTemp=value;
			$('.titleBtn').attr('href','<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id='+idTemp+'&polluteSource='+polluteSourceTemp);
        },change:function(event, value){
			idTemp=value;
			$('.titleBtn').attr('href','<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id='+idTemp+'&polluteSource='+polluteSourceTemp);
        }});
		var project = $("#type").select({name:"proejctCode",selected:true,simpleData:polluteSourceobj,codeValue:"name",code:"id",zIndex:8,url:"../project/queryAll.jhtml",initAfter:function(event, value){
			polluteSourceTemp=value;
			$('.titleBtn').html('导出'+polluteSourceobj[value]).attr('href','<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id='+idTemp+'&polluteSource='+polluteSourceTemp);
        },change:function(event, value){
			polluteSourceTemp=value;
			$('.titleBtn').html('导出'+polluteSourceobj[value]).attr('href','<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id='+idTemp+'&polluteSource='+polluteSourceTemp);
        }});
		
		
			
	})

</script>
<style>
.block{
	padding-bottom: 120px;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-wuranzhilicuoshi"></i>
			导出企业信息表
			<a class="titleBtn" id="exportA" >导出</a>
			<%-- <a class="titleBtn" id="exportA" href="<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id=4&polluteSource=boiler"/>导出锅炉</a>
			<a class="titleBtn" id="exportA" href="<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id=4&polluteSource=openYard"/>导出露天</a>
			<a class="titleBtn" id="exportA" href="<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id=4&polluteSource=oilTank"/>导出石油</a>
			<a class="titleBtn" id="exportA" href="<%=request.getContextPath()%>/admin/exportexcel/export.jhtml?id=4&polluteSource=pullutesource-other"/>导出工段和炉窑</a> --%>
			<%-- <a   href="<%=request.getContextPath()%>/admin/exportpdf/export.jhtml"/>导出pdf</a> --%>
		</h1> 
		<div class="form">
		<div class="formLine">
			<div class="formPara">
				<label class="label">项目</label>
				<div id="project" data-code="projectCode" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
				<label class="label">导出类型</label>
				<div id="type" data-code="type" class="projectdiv selectF"></div>
			</div>
		</div>
		</div>
		<div id="tab" class="tab"></div>
		
	</div>
</body>
</html>