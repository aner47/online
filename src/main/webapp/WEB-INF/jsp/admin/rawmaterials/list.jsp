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
<title>原辅料信息</title>

<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		
		$('#delete').click(function(e){
			//默认情况下删除不可点击
			if($(this).attr("disabled")){
				return false;
			}
			//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条溶剂信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除溶剂成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除溶剂失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#editBtn").click(function() {
			if ($(this).attr("disabled"))
				return;
			var opts = {
				title : "修改原辅料信息",
				width : "646px",
				height : "600px",
				btn : [ '更新', '取消' ],
				url : "updatePage.jhtml",
				data : {
					"id" : grid1.getSingleSelectedValue('id')
				},
				yes : function(index) {
					$("#inputForm").submit();
				},
				cancel : function() {
				}
			}
			qy.panel(opts);
		});
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加原辅料信息",
				width:"646px",
				height:"1822px",
				url : "addPage.jhtml",
				yes : function(index){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
				    {name:"year",caption:"年份",hidden:false,type:"text"},
				    {name:"productName",caption:"产品名称",hidden:false,type:"text"},
				    {name:"name",caption:"原辅料名称",hidden:false,type:"text"},
				    {name:"consumption",caption:"年消耗量",hidden:false,type:"text"},
				    {name:"unit",caption:"单位",hidden:false,type:"text"},
				    {name:"solventCategory",caption:"类型",hidden:false,type:"text",valueSet:"原辅料【溶剂类型】"},
				    {name:"solventType",caption:"溶剂性质",hidden:false,type:"text",valueSet:"原辅料【溶剂性质】"},
				    {name:"vocRate",caption:"voc含量（%）",hidden:false,type:"text"},
				    {name:"vocVolatility",caption:"voc挥发度（%）",hidden:false,type:"text"},
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"operate",caption:"分月信息",isHtml:true,html:'<a id="view"  style="cursor: pointer; font-weight: 900;" class="a_button">分月</a>',	
						events :[{
							type:'click',
							select:'#view',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'id');
	  				  			location.href = "../monthlyinformation/list.jhtml?monthlyType=rawmaterials&sourceId="+id;
	  				  			
							}
						}] 
					},				    	
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
<style>
	.a_button {
		border-radius: 4px;
		height: 25px;
		background-color: #2eaee1;
		width: 50px;
		color: #fff;
		display: inline-block;
		font-weight: 900;
		line-height: 25px; 
	}
</style>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-youjirongjixinxi"></i>
			原辅料信息    （填此表前请先填写产品工段信息表）
			<c:if test="${principal.userType != 'system'}">
			<div id="back" class="titleBtn">返回</div>
			<div id="addBtn" class="titleBtn">添加</div>
			
			<div id="editBtn" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab1" data-enable="2">删除</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
			</c:if>
		</h1> 
		<div id="tab1" class="tab"></div>
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>