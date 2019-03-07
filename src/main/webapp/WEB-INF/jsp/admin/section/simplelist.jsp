<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>工段信息</title>
<style>
body {
	position: relative;
}
.fixed_return_cla {
	position: absolute;
	top: 1770px;
	right: 29px;
	z-index: 99999;
	width: 40px;
	height: 28px;
	line-height: 24px;
	text-align: center;
	color: #fff;
	border-radius: 3px;
	background-color: #2eaee1;
	cursor: pointer;
}
</style>
<script type="text/javascript">
	require(
			[ "grid","hide", "select", "panel","commonBase" ],
			function(g,hide) {
				var opts = {
					tab : "tab",
					checkbox : false,
					num : false,
					 autoW: true,
					data : {
						pageSize : 10,
						pageNumber : 0
					},
					url : "query.jhtml",
					 autoW: true,
					columns : [
						{
							name : "id",
							caption : "ID",
							width : 80 / 5,
							hidden : false,
							type : "text",
							prefix:"GD"
						},
						
							{
								name : "name",
								caption : "工段/工艺名称或炉窑类型",
								width : 80 / 5,
								hidden : false,
								type : "text"
							},
							{
								name : "productionLines",
								caption : "数量",
								width : 80 / 5,
								hidden : false,
								type : "text"
							},
							{
								name : "useStatus",
								caption : "设备/工序使用状态",
								width : 80 / 5,
								hidden : false,
								type : "text"
							},
							{
								name : "productCapacity",
								caption : "设计产能(产品/年)",
								width : 80 / 5,
								hidden : false,
								type : "text"
							},
							{
								name : "product.name",
								caption : "产品信息",
								width : 80 / 5,
								hidden : false,
								type : "text",
								format : function(value, data, handle) {
									if (objC(data,"product","name")) {
										return  data.product.name + (data.product.yield || data.product.yield === 0 ? data.product.yield : "") +(data.product.unit?data.product.unit:"");
									} else {
										return "";

									}

								}
							},
							{
								name : "rawMaterials1.name",
								caption : "主要原料信息",
								width : 200 / 5,
								hidden : false,
								type : "text",
								format : function(value, data, handle) {
									var str = [];
									if (data.rawMaterials1
											&& data.rawMaterials1.name) {
										str.push(data.rawMaterials1.name  + (data.rawMaterials1.consumption || data.rawMaterials1.consumption === 0 ? data.rawMaterials1.consumption : "") +(data.rawMaterials1.unit?data.rawMaterials1.unit:""));
									}
									if (data.rawMaterials2
											&& data.rawMaterials2.name) {
										str.push(data.rawMaterials2.name  + (data.rawMaterials2.consumption || data.rawMaterials2.consumption === 0 ? data.rawMaterials2.consumption : "") +(data.rawMaterials2.unit?data.rawMaterials2.unit:""));
									}
									if (data.rawMaterials3
											&& data.rawMaterials3.name) {
										str.push(data.rawMaterials3.name  + (data.rawMaterials3.consumption || data.rawMaterials3.consumption === 0 ? data.rawMaterials3.consumption : "") +(data.rawMaterials3.unit?data.rawMaterials3.unit:""));
									}
									
									return str.join(',')
								}
							},
							
							{
								name : "fuelType",
								caption : "燃料类型",
								width : 80 / 5,
								hidden : false,
								type : "text",
								format : function(value, data, handle) {
									if (objC(data,"product","name")) {
										return  data.fuelType + (data.fuelConsumption || data.fuelConsumption === 0 ? data.fuelConsumption : "") +(data.fuelUnit?data.fuelUnit:"");
									} else {
										return "";

									}

								}
							},
							
						
							
							//{name:"enterpriseEmissionsManagement",caption:"治理措施",width:120/5,hidden:false,type:"text"},
							{name:"enterpriseEmissionsManagement.governanceMeasures1.name",caption:"脱硫工艺",width:60/5,hidden:false,type:"text"},
		                    {name:"enterpriseEmissionsManagement.governanceMeasures3.name",caption:"脱硝工艺",width:60/5,hidden:false,type:"text"},
		                    {name:"enterpriseEmissionsManagement.governanceMeasures2.name",caption:"除尘工艺",width:60/5,hidden:false,type:"text"},
		                    {name:"enterpriseEmissionsManagement.governanceMeasures4.name",caption:"VOC措施",width:60/5,hidden:false,type:"text"},
							{
								name : "enterpriseEmissionsManagement.exhaustionHole.height",
								caption : "排放口高度（米）",
								width : 60 / 5,
								hidden : false,
								type : "text",
							},  ],
				}
				hide.gridHide(opts);
				grid1 = g.grid(opts);
				grid1.loadData();

				$("#addBtn")
						.click(
								function() {
									var opts = {
										title : "添加工段信息",
										width : "646px",
										height : "1068px",
										btn : ['保存','取消'],
										url : "simpleAddPage.jhtml",
										yes : function(index) {
											$(
													'input[value=inorganic]:checked ~ .natureC :checked')
													.removeAttr('checked');
											$("#section_form").submit();
											grid1.loadData();
											//layer.close(index);
										},
										btn3 : function() {
											$(
													'input[value=inorganic]:checked ~ .natureC :checked')
													.removeAttr('checked');
											$("#section_form").submit();
											grid1.loadData();
										},
										cancel : function() {
										}
									}
									qy.panel(opts);
								});

				$("#editBtn").click(function() {
					if ($(this).attr("disabled"))
						return;
					var opts = {
						title : "修改工段信息",
						width : "646px",
						height : "1068px",
						btn : [ '更新', '取消' ],
						url : "simpleUpdatePage.jhtml",
						data : {
							"id" : grid1.getSingleSelectedValue('id')
						},
						yes : function(index) {
							$("#section_form").submit();
						},
						cancel : function() {
						}
					}
					qy.panel(opts);
				});

				$('#delete').click(
						function(e) {//删除
							if($(this).attr("disabled")) return;
							qy.sure({
								title : '是否删除所选'
										+ grid1.getSelectedValue('id').length
										+ '条菜单信息?',
								yes : function(index) {
									layer.close(index);
									var ids = grid1.getSelectedValue('id');
									var opts = {
										url : 'simpledelete.jhtml',
										data : {
											"ids" : ids.join(',')
										},
										callBack : function(data, errcode) {
											if (errcode !== "000000") {
												qy.suc2({
													title : '删除成功！'
												});
												grid1.loadData();
											} else {
												qy.fail2({
													title : '删除失败！'
												});
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
			<i class="iconfont icon-shengchangongduangongxinxi"></i> 工段信息
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
	<!-- <div id="fixed_return" class="fixed_return_cla"> 返回 </div> -->
</body>
</html>