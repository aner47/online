<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/from.less" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>燃料信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		var a='${enterpriseId}';
		console.log(a);
		$("#add").click(function(){
			var opts = {
				title:"添加燃料信息",
				width:"646px",
				height:"611px",
				data:{fuelType:'${fuelType}',sourceId:'${sourceId}'},
				btn:['保存','取消'],
				url : "addPage.jhtml",
				yes : function(index){
					$("#fuel_form").submit();
					//layer.close(index);
				},
				btn3:function(){
					$("#fuel_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$("#update").click(function(){
			if($(this).attr("disabled"))return;
			var opts = {
				title:"修改燃料信息",
				width:"646px",
				height:"611px",
				url : "updatePage.jhtml",
				data:{"id":grid1.getSingleSelectedValue('id'),fuelType:'${fuelType}',sourceId:'${sourceId}'},
				yes : function(index){
					$("#fuel_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		var opts = {
			tab:"tab",
			checkbox:false,
			num:false,
			autoW: true,
			data:{pageSize:10,pageNumber:0,fuelType:'${fuelType}',sourceId:'${sourceId}'},
			 url:"query.jhtml",
			columns :[
			{name:"fuelYear",caption:"燃料年份",width:80/5,hidden:false,type:"text"},
			{name:"fuelType",caption:"燃料类型",width:80/5,hidden:false,type:"text"},
			{name:"fuelConsumption",caption:"年消耗量",width:40/5,hidden:false,type:"text"},
			{name:"fuelUnit",caption:"统计单位",width:40/5,hidden:false,type:"text"},
			{name:"fuelSulfurContent",caption:"燃料含硫率",width:40/5,hidden:false,type:"text"},
			{name:"fuelAsh",caption:"燃料灰分",width:40/5,hidden:false,type:"text"},
			{name:"fuelVolatiles",caption:"燃料挥发分",width:40/5,hidden:false,type:"text"},
			{name:"id",caption:"ID",hidden:true,type:"text"},

			]
		}
		
		
		hide.gridHide(opts);
		grid1 =  g.grid(opts);
		grid1.loadData();
		
		$('#delete').click(function(e){//删除
			if($(this).attr("disabled")) return;
			qy.sure({
				title:'是否删除所选'+grid1.getSelectedValue('id').length+'条菜单信息?',
				yes:function(index){
					layer.close(index);
					var ids = grid1.getSelectedValue('id');
					var opts ={
						url:'delete.jhtml',
						data :{"ids":ids.join(',')},
						callBack:function(data,errcode){
							if(errcode!=="000000"){
								qy.suc2({title:'删除成功！'});
								grid1.loadData();
							}else{
								qy.fail2({title:'删除失败！'});
							}
						}
					}
					qy.ajax(opts);
				},
				cancel:function(){
					// 重新加载表格
					grid1.loadData();
				}
			});
		});
		
	})
</script>
<style>
.block{
	padding-bottom: 120px;
}
.buttonF {
	font-size: 14px;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-fenyueshuju1"></i>
			燃料信息
			<c:if test="${principal.userType != 'system'}">
			<div id="add" class="titleBtn" >添加</div>
			<div id="update" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			<div style="padding: 0 34px 0 0;" class="buttonF">
				
			</div>
			</c:if>
			<div class="align-right">
					<a class="titleBtn" id="back" href="javascript:history.back(-1)">返回</a>
				</div>
		</h1> 
		<div id="tab" class="tab"></div>
		<!-- <div class="tips">
			<div class="tipscontent">
			</div>
		</div> -->
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>