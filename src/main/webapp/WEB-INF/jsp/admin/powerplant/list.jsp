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
<title>自备发电机组</title>
<script type="text/javascript">
require(["grid","hide","select","panel"],function(g,hide){
		$('#delete').click(function(e){//删除
			if($(this).attr("disabled")) return;
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条自备发电机组信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除自备发电机组成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除自备发电机组失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		// 关停/取缔 或者 停产, 改为非必填项, 正常生产时保留必填项
		var changeNames = ["productionMonthTemp"];
		
		 if('${baseInformation.enterpriseState}'){
			 show('${baseInformation.enterpriseState}');
		 }
		
	  	// 控制关停时间的显示
		 function show(value){
			 if(value == '停用'){
				 $("#product_form").removeAttr('required');
				}else{
				$("#product_form").attr('required');
			}
			
		 }
	  	
		
		$("#add").click(function(){
			var opts = {
				title: "添加自备发电机组信息",
				url : "addPage.jhtml",
				width: "646px",
				height: "1094px",
				data:"",
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
				title: "修改自备发电机组信息",
				url : "updatePage.jhtml",
				width: "646px",
				height: "1094px",
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
				tab:"tab1",
				checkbox:false,
				num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
				    {name:"id",caption:"id",hidden:true,type:"text",prefix:"机组"},
				    {name:"no",caption:"机组编号",hidden:false,type:"text",prefix:"机组"},
				    {name:"installedCapacity",caption:"装机容量（兆瓦）",hidden:false,type:"text"},
				    {name:"useStatus",caption:"使用状态",hidden:false,type:"text"},
				    {name:"putOnTime",caption:"投运时间",hidden:false,type:"date_yyyy_MM"},
				    {name:"abarbeitungTime",caption:"整改时间",hidden:false,type:"date_yyyy_MM"},
				    {name:"stopTime",caption:"关停时间",hidden:false,type:"date_yyyy_MM"},
				    {name:"annualPowerGeneration",caption:"年发电量（万千瓦时）",hidden:false,type:"text"},
				    {name:"annualHeatSupply",caption:"年供热量（万吉焦）",hidden:false,type:"text"},
				    {name:"boilerType",caption:"锅炉类型",hidden:false,type:"text"},
				    {name:"boilerModel",caption:"锅炉型号",hidden:false,type:"text"},
				    {name:"steamTon",caption:"锅炉蒸吨(蒸吨/小时)",hidden:false,type:"text"},
				    {name:"fuelYear",caption:"燃料年份",hidden:false,type:"text"},
				    {name:"fuelType",caption:"燃料类型",hidden:false,type:"text"},
				    {name:"fuelConsumption",caption:"燃料消耗量",hidden:false,type:"text"},
				    {name:"fuelUnit",caption:"燃料单位",hidden:false,type:"text"},
				    {name:"fuelSulfurContent",caption:"燃料含硫率",hidden:false,type:"text"},
				    {name:"fuelAshContent",caption:"燃料灰分",hidden:false,type:"text"},
				    {name:"fuelVolatiles",caption:"燃料挥发分",hidden:false,type:"text"},
				    {name:"operate",caption:"分月信息",isHtml:true,html:'<a id="view"  style="cursor: pointer; font-weight: 900;" class="a_button">分月</a>',	
						events :[{
							type:'click',
							select:'#view',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'id');
	  				  			location.href = "../monthlyinformation/list.jhtml?monthlyType=powerplant&sourceId="+id;
	  				  			
								 /* var id =grid1.getCurrentRowValue(this,'id');
								qy.ajax({
									url: base+"/web/monthlyinformation/list.jhtml",
									data:{powerplantId:id},
									callBack:function(){
										
									}
								})  */
							}
						}] 
					},	
					{name:"operate",caption:"更多燃料",isHtml:true,html:'<a id="fuelbutton"  style="cursor: pointer;">更多燃料</a>',	
						events :[{
							type:'click',
							select:'#fuelbutton',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'id');
	  				  			location.href = "../fuel/list.jhtml?fuelType=powerplant&sourceId="+id;
	  				  			
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
			<i class="iconfont icon-fadianji"></i>
			自备发电机组
			<c:if test="${principal.userType != 'system'}">
			<div id="back" class="titleBtn">返回</div>
			<div id="add" class="titleBtn" >添加</div>
			
			<div id="update" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div>
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