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
<title>炉窑信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel","commonBase"],function(g,hide){
		var opts = {
				tab:"tab",
				checkbox:false,
				num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"year",caption:"年份",width:80/5,hidden:false,type:"text"},
					{name:"productionMonth",caption:"生产月份",width:80/5,hidden:false,type:"text"},
					{name:"kilnType",caption:"炉窑类型",width:80/5,hidden:false,type:"text"},
					{name:"rawMaterials1.name",caption:"主要原料信息",width:200/5,hidden:false,type:"text",format:function(value,data,handle){
						var str  = [];
						if(data.rawMaterials1&&data.rawMaterials1.name){
							str.push(data.rawMaterials1.name);
						}
						if(data.rawMaterials2&&data.rawMaterials2.name){
							str.push(data.rawMaterials2.name);
						}
						if(data.rawMaterials3&&data.rawMaterials3.name){
							str.push(data.rawMaterials3.name);
						}
						if(data.rawMaterials4&&data.rawMaterials4.name){
							str.push(data.rawMaterials4.name);
						}
						if(data.rawMaterials5&&data.rawMaterials5.name){
							str.push(data.rawMaterials5.name);
						}
						return str.join(',')
						
					}},
					{name:"fuel",caption:"燃料信息",width:200/5,hidden:false,type:"text",format:function(value,data,handle){ 
						if(data.fuelType){
						return data.fuelType+" " + data.fuelConsumption+data.fuelUnit
						}else{
							return "";
							
						}
						}},
					{name:"product.name",caption:"产品信息",width:80/5,hidden:false,type:"text",format:function(value,data,handle){
						if(objC(data,"product","name")){
							return  data.product.name + (data.product.yield?data.product.yield:"") +(data.product.unit?data.product.unit:"");
						}else{
							return "";
							
						}
						
					}},
					{name:"enterpriseEmissionsManagement.governanceMeasures1.name",caption:"脱硫工艺",width:60/5,hidden:false,type:"text"},
                    {name:"enterpriseEmissionsManagement.governanceMeasures3.name",caption:"脱硝工艺",width:60/5,hidden:false,type:"text"},
                    {name:"enterpriseEmissionsManagement.governanceMeasures2.name",caption:"除尘工艺",width:60/5,hidden:false,type:"text"},
                    {name:"enterpriseEmissionsManagement.governanceMeasures4.name",caption:"VOC措施",width:60/5,hidden:false,type:"text"},
					{name:"enterpriseEmissionsManagement.exhaustionHole.height",caption:"排放口高度",width:60/5,hidden:false,type:"text",},
					{name:"id",caption:"ID",hidden:true,type:"text"},
				],
			}
		hide.gridHide(opts);
		grid1 =  g.grid(opts);
		grid1.loadData();
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加炉窑信息",
				width:"780px",//646
				height:"783px",
				btn:['保存','取消'],
				url : "generaladdPage.jhtml",
				yes : function(index){
					if($("#kiln_form").submit()){
						//layer.close(index);
					}
				
				},
				btn3:function(){
					$("#kiln_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$("#editBtn").click(function(){
			if($(this).attr("disabled")) return;
			var opts = {
					title:"修改炉窑信息",
					width:"780px",
					height:"783px",
					btn:['更新','取消'],
					url : "generalupdatePage.jhtml",
					data:{"id":grid1.getSingleSelectedValue('id')},
					yes : function(index){
						$("#kiln_form").submit();
					},
					cancel:function(){}
				}
				qy.panel(opts);
		});
		
		$('#delete').click(function(e){//删除
			if($(this).attr("disabled")) return;
			qy.sure({
				title:'是否删除所选'+grid1.getSelectedValue('id').length+'条菜单信息?',
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
			<i class="iconfont icon-luyaoxinxi"></i>
			炉窑信息
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
				<p>1.一个炉窑对应填写一行信息。</p>
				<p>2．主要原料信息：炉窑加工的三种主要原料名称、年消耗量和计量单位，单位使用本行业中通用的计量单位，避免使用“桶、扎、盒、片、箱”等数量单位</p>
				<p>3．单位：固体燃料为吨，液体燃料为吨或万升，气体燃料为万立方米，用电量为千瓦</p>
				<p>4．燃料含硫率：指2016年度消耗的燃料的平均含硫率</p>
				<p>5．产品信息：指该炉窑生产的产值比重最大的主要产品名称、年产量和计量单位，单位使用本行业中通用的计量单位，避免使用“桶、扎、盒、片、箱”等数量单位</p>
				<p>6．治理措施编号：对应表3治理措施编号</p>
				<p>7．排放口编号：对应表2排放口编号</p>
			</div>
		</div>			
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>