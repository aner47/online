<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>泄露检测</title>
<script type="text/javascript">
require(["grid","select","panel"],function(g){
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条设备泄露检测信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(','),equipment_leaked:"${equipment_leaked}"},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除设备泄露检测成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除设备泄露检测失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加设备泄露检测信息",
				url : "addPage.jhtml",
				data:{equipment_leaked:"${equipment_leaked}"},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			if($(this).attr("disabled")) return;
			var opts = {
				title: "修改设备泄露检测信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0],equipment_leaked:"${equipment_leaked}"},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
			//查询
		$('#query').click(function(){
			var data = {equipment_leaked:"${equipment_leaked}"};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data[v.name] = v.value;	
				
			})
			grid1.loadData(data);
		});
	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0,equipment_leaked:"${equipment_leaked}"},
				url:"query.jhtml",
				columns :[
								    {name:"sealedPoint",caption:"密封点(个)",hidden:false,type:"text"},
								    {name:"leakedSealedPoint",caption:"泄露点(个)",hidden:false,type:"text"},
								    {name:"fixedSealedPoint",caption:"修复(个)",hidden:false,type:"text"},
								    {name:"id",caption:"ID",hidden:true,type:"text"},
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-guoluxinxi"></i>
			设备泄露检测
			<c:if test="${principal.userType != 'system'}">
			<div id="add" class="titleBtn">添加</div>
			<div id="update" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab1" data-enable="2">删除</div>
			</c:if>
		</h1> 
		<div id="tab1" class="tab"></div>
				
	</div>
</body>
</html>