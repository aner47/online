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
<title>产品工段信息</title>
<script type="text/javascript">
	require(
			[ "grid","hide", "select", "panel","commonBase" ],
			function(g,hide) {
				var opts = {
					tab : "tab",
					checkbox : false,
					num : true,
					data : {
						pageSize : 10,
						pageNumber : 0
					},
					url : "query.jhtml",

					columns : [
						//{name:"id",caption:"ID",hidden:false,type:"text",prefix:"工段",width:80/5},
						{name:"name",caption:"生产单元名称",hidden:false,type:"text"},
						{name:"productionTechnique",caption:"工艺名称",hidden:false,type:"text"},
						{name:"facilityName",caption:"生产设施名称",hidden:false,type:"text"},
						{name:"facilityNo",caption:"生产设施编号",hidden:false,type:"text"},
						{name:"facilityNum",caption:"生产设施数量",hidden:false,type:"text"},
						{name:"useStatus",caption:"设备/工序使用状态",hidden:false,type:"text"},
						{name:"facilityParamName",caption:"参数名称",hidden:false,type:"text"},
						{name:"facilityDesignValue",caption:"设计值",hidden:false,type:"text"},
						{name:"facilityUnit",caption:"计量单位",hidden:false,type:"text"},
						//{name:"isSealing",caption:"是否封闭作业",width:80/5,hidden:false,type:"text"},
						/* {name:"rawMaterials1.name",caption:"主要原料信息",width:200/5,
								hidden : false,
								type : "text",
								format : function(value, data, handle) {
									var str = [];
									if (data.rawMaterials1
											&& data.rawMaterials1.name) {
										str.push(data.rawMaterials1.name);
									}
									if (data.rawMaterials2
											&& data.rawMaterials2.name) {
										str.push(data.rawMaterials2.name);
									}
									if (data.rawMaterials3
											&& data.rawMaterials3.name) {
										str.push(data.rawMaterials3.name);
									}
									if (data.rawMaterials4
											&& data.rawMaterials4.name) {
										str.push(data.rawMaterials4.name);
									}
									if (data.rawMaterials5
											&& data.rawMaterials5.name) {
										str.push(data.rawMaterials5.name);
									}
									return str.join(',')
								}
							}, */
							{
								name : "product.name",
								caption : "产品信息",
								
								hidden : false,
								type : "text",
								format : function(value, data, handle) {
									if (objC(data,'product','name')) {
										return  data.product.name + (data.product.yield || data.product.yield === 0  ?data.product.yield:"") +(data.product.unit?data.product.unit:"");
									} else {
										return "";

									}

								}
							},
							{name:"contaminantType",caption:"污染物种类",hidden:false,type:"text"},
							{name:"dischargeModality",caption:"排放形式",hidden:false,type:"text"},
							
							{name:"enterpriseEmissionsManagement",caption:"治理措施",hidden:false,type:"text",format:function(value,data,handle){
								var str  = [];
								if(data.enterpriseEmissionsManagement.governanceMeasures1){
									str.push("ZL"+data.enterpriseEmissionsManagement.governanceMeasures1.gmno);
								}
								if(data.enterpriseEmissionsManagement.governanceMeasures2){
									str.push("ZL"+data.enterpriseEmissionsManagement.governanceMeasures2.gmno);
								}
								if(data.enterpriseEmissionsManagement.governanceMeasures3){
									str.push("ZL"+data.enterpriseEmissionsManagement.governanceMeasures3.gmno);
								}
								if(data.enterpriseEmissionsManagement.governanceMeasures4){
									str.push("ZL"+data.enterpriseEmissionsManagement.governanceMeasures4.gmno);
								}
								return str.join(',')
							}},
						
							{
								name : "enterpriseEmissionsManagement.exhaustionHole.exhaustionHoleNum",
								caption : "排放口编号",
								
								hidden : false,
								type : "text",
								format:function(value){return value?"P"+value:""}
							}, 
							{name:"operate",caption:"分月信息",isHtml:true,html:'<a id="view"  style="cursor: pointer; font-weight: 900;" class="a_button">分月</a>',	
								events :[{
									type:'click',
									select:'#view',
									handle:function(){
										var id =grid1.getCurrentRowValue(this,'id');
			  				  			location.href = "../monthlyinformation/list.jhtml?monthlyType=section&sourceId="+id;
			  				  			
									}
								}] 
							}
							
							],
				}
				hide.gridHide(opts);
				grid1 = g.grid(opts);
				grid1.loadData();

				$("#addBtn").click(function() {
					var opts = {
						title : "添加产品工段信息",
						width : "646px",
						height : "1807px",
						btn : ['保存','取消'],
						url : "addPage.jhtml",
						yes : function(index) {
							$('input[value=inorganic]:checked ~ .natureC :checked')
									.removeAttr('checked');
							$("#section_form").submit();
							grid1.loadData();
							//layer.close(index);
						},
						btn3 : function() {
							$('input[value=inorganic]:checked ~ .natureC :checked')
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
						title : "修改产品工段信息",
						width : "646px",
						height : "1807px",
						btn : [ '更新', '取消' ],
						url : "updatePage.jhtml",
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
										url : 'delete.jhtml',
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
					top.layer.closeAll('page');
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
			<i class="iconfont icon-shengchanxian"></i> 产品工段信息
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
		<div class="tips">
			<div class="tipscontent">
				<p>1．统计对象为相对独立的产品工段产品工段 </p>
				<p>2．主要原料：尽量使用体积单位与重量单位，避免使用“桶、扎、盒、片、箱”等数量单位 </p>
				<p>3．有机溶剂：名称应使用本行业中的通用名称，选填其性质属于“油性”或“水性”；避免使用“桶、扎、盒、片、箱”等数量单位。主要有机溶剂类型：涂料、油漆、润滑剂、胶粘剂、清洗剂、稀释剂、油墨、等 </p>
				<p>4．产品信息：指该产品工段/产品工段生产的产值比重最大的主要产品，单位使用本行业中通用的计量单位，避免使用“桶、扎、盒、片、箱”等数量单位 </p>
				<p>5．治理措施编号：对应表3治理措施编号 </p>
				<p>6．排放口编号：对应表2排放口编号 </p>
			</div>
		</div>
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>