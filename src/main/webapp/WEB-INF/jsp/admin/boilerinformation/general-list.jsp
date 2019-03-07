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
<title>生产信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		var opts = {
				tab:"tab",
				checkbox:false,
				num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"boilerType",caption:"锅炉类型",width:60/5,hidden:false,type:"text"},
					{name:"typeOfBoiler",caption:"锅炉型号",width:60/5,hidden:false,type:"text"},
					{name:"steamTon",caption:"锅炉蒸吨",width:60/5,hidden:false,type:"text"},
					{name:"status",caption:"锅炉状态",width:60/5,hidden:false,type:"text"},
					{name:"purpose",caption:"锅炉用途",width:60/5,hidden:false,type:"text"},
					{name:"operation",caption:"投运时间",width:60/5,hidden:false,type:"date"},
					{name:"stopData",caption:"关停时间",width:60/5,hidden:false,type:"date"},
					{name:"emissionsManagement.governanceMeasures1.name",caption:"脱硫工艺",width:60/5,hidden:false,type:"text"},
					{name:"emissionsManagement.governanceMeasures2.name",caption:"除尘工艺",width:60/5,hidden:false,type:"text"},
					{name:"emissionsManagement.governanceMeasures3.name",caption:"脱硝工艺",width:60/5,hidden:false,type:"text"},
					{name:"fuel",caption:"燃料信息",width:200/5,hidden:false,type:"text",format:function(value,data,handle){ return data.fuelType+" " + data.fuelConsumption+data.fuelUnit }},
					{
                        name : "emissionsManagement.exhaustionHole.height",
                        caption : "排放口高度（米）",
                        width : 60 / 5,
                        hidden : false,
                        type : "text",
                    }, 
					{name:"id",caption:"ID",hidden:true,type:"text"},

				],
			}
			hide.gridHide(opts);
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加锅炉信息",
				width:"646px",
				height:"764px",
				btn:['保存','取消'],
				url : "addgeneralPage.jhtml",
				yes : function(index){
					$("#boiler_form").submit();
				},
				btn3:function(){
					$("#boiler_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$("#editBtn").click(function(){
			if($(this).attr("disabled")) return;
			var opts = {
					title:"修改锅炉信息",
					width:"646px",
					height:"764px",
					btn:['更新','取消'],
					url : "updategeneralPage.jhtml",
					data:{"id":grid1.getSingleSelectedValue('id')},
					yes : function(index){
						$("#boiler_form").submit();
					},
					cancel:function(){}
				}
				qy.panel(opts);
		});
		
		$('#delete').click(function(e){//删除
			if($(this).attr("disabled")) return;
			qy.sure({
				title:'是否删除所选'+grid1.getSelectedValue('id').length+'条锅炉信息?',
				yes:function(index){
					layer.close(index);
					var ids = grid1.getSelectedValue('id');
					var opts ={
						url:'generaldelete.jhtml',
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
				}
			});
		});
		$("#fixed_return").on("click", function() {
			window.location.href = "../enterprise/enterpriseTaskList.jhtml";
			top.loadMenu('188');
		})
		$("#back").on("click", function() {
			window.location.href = "../enterprise/list.jhtml";
			top.loadMenu('2','31');
		})
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
			<i class="iconfont icon-guoluxinxi2"></i>
			锅炉信息
			<c:if test="${principal.userType != 'system'}">
			<div id="back" class="titleBtn">返回</div>
			<div id="addBtn" class="titleBtn">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
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