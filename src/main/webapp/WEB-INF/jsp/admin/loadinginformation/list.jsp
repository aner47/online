<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>有机液体装载信息</title>
<script type="text/javascript">
require(["grid","hide","select","panel","less"],function(g,hide){
		$('#delete').click(function(e){//删除
			if($(this).attr("disabled"))return;
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条有机液体装载信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除装载成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除装载失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加有机液体装载信息",
				url : "addPage.jhtml",
				data:"",
				width: "646px",
				height: "500px",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			if($(this).attr("disabled"))return;
			var opts = {
				title: "修改有机液体装载信息",
				url : "updatePage.jhtml",
				width: "646px",
				height: "500px",
				data:{id:grid1.getSelectedValue("id")[0]},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
			//查询
		$('#query').click(function(){
			var data = {};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data[v.name] = v.value;	
				
			})
			grid1.loadData(data);
		});
	
		var opts = {
				tab:"tab",
				checkbox:false,
				num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"id",caption:"装卸环节编号",hidden:true,type:"text",prefix:"储罐"},
				    {name:"no",caption:"装卸环节编号",hidden:false,type:"text"},
				    {name:"location",caption:"作业部/车间",hidden:false,type:"text"},
				    {name:"platformNo",caption:"装车站台编号",hidden:false,type:"text"},
				    {name:"materialType",caption:"装卸物料名称",hidden:false,type:"text"},
				    {name:"loadMethod",caption:"装载方式",hidden:false,type:"text"},
				    {name:"operationMethod",caption:"操作方式",hidden:false,type:"text"},
				    {name:"annualLoad",caption:"年装载量",hidden:false,type:"text"},
				    {name:"materialDensity",caption:"物料密度",hidden:false,type:"text"},
				    {name:"recycling",caption:"回收技术",hidden:false,type:"text"},
				    {name:"recoveryRate",caption:"回收效率（%）",hidden:false,type:"text"},
				  //  {name:"id",caption:"ID",hidden:true,type:"text"},
								    	
					]
			}
			hide.gridHide(opts);
			grid1 =  g.grid(opts);
			grid1.loadData();
			$("#fixed_return").on("click", function() {
				window.location.href = "../enterprise/enterpriseTaskList.jhtml";
				top.loadMenu('188');
				top.layer.closeAll('page');
			})
			$("#back").on("click", function() {
				window.location.href = "../enterprise/list.jhtml";
				top.loadMenu('2','31');
			})
		
	})
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-youjiyetizhuangzaixinxi"></i>
			有机液体装载信息
			<c:if test="${principal.userType != 'system'}">
			<div id="back" class="titleBtn">返回</div>
			<div id="add" class="titleBtn">添加</div>
			<div id="update" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
			</c:if>
		</h1> 
		<div id="tab" class="tab"></div>
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>