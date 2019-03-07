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
<title>锅炉信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		
		var opts = {
				tab:"tab",
				checkbox:false,
				num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"id",caption:"编号",width:60/5,hidden:true,type:"text",prefix:"锅炉"},
					{name:"no",caption:"编号",width:60/5,hidden:false,type:"text",prefix:"锅炉"},
					{name:"boilerType",caption:"锅炉类型",width:60/5,hidden:false,type:"text"},
					{name:"typeOfBoiler",caption:"锅炉铭牌型号",width:60/5,hidden:false,type:"text"},
					{name:"steamTon",caption:"锅炉蒸吨",width:60/5,hidden:false,type:"text"},
					{name:"operation",caption:"投运时间",width:60/5,hidden:false,type:"date_yyyy_MM"},
					{name:"stopData",caption:"关停时间",width:60/5,hidden:false,type:"date_yyyy_MM"},
					{name:"fuel",caption:"燃料信息",width:200/5,hidden:false,type:"text",format:function(value,data,handle){ return data.fuelType+" " + (data.fuelConsumption ?data.fuelConsumption:"")+data.fuelUnit }},
					{name:"emissionsManagement",caption:"治理措施",width:120/5,hidden:false,type:"text",format:function(value,data,handle){
					var str  = [];
					if(data.emissionsManagement.governanceMeasures1){
						str.push("治理"+data.emissionsManagement.governanceMeasures1.gmno);
					}
					if(data.emissionsManagement.governanceMeasures2){
						str.push("治理"+data.emissionsManagement.governanceMeasures2.gmno);
					}
					if(data.emissionsManagement.governanceMeasures3){
						str.push("治理"+data.emissionsManagement.governanceMeasures3.gmno);
					}
					if(data.emissionsManagement.governanceMeasures4){
						str.push("治理"+data.emissionsManagement.governanceMeasures4.gmno);
					}
					return str.join(',')
				}},
				{name:"emissionsManagement.exhaustionHole.exhaustionHoleNum",caption:"排放口编号",width:60/5,hidden:false,type:"text",format:function(value){return value?"排放口"+value:""}},
				{name:"operate",caption:"分月信息",width:60/5,isHtml:true,html:'<a id="view"  style="cursor: pointer; font-weight: 900;" class="a_button">分月</a>',	
					events :[{
						type:'click',
						select:'#view',
						handle:function(){
							var id =grid1.getCurrentRowValue(this,'id');
  				  			location.href = "../monthlyinformation/list.jhtml?monthlyType=boiler&sourceId="+id;
  				  			
						}
					}] 
				},		
				{name:"operate",caption:"更多燃料",width:60/5,isHtml:true,html:'<a id="fuelbutton"  style="cursor: pointer;">更多燃料</a>',	
					events :[{
						type:'click',
						select:'#fuelbutton',
						handle:function(){
							var id =grid1.getCurrentRowValue(this,'id');
  				  			location.href = "../fuel/list.jhtml?fuelType=boiler&sourceId="+id;
  				  			
						}
					}] 
				},		

				],
			}
			opts.columns.splice(2,1
			,{name:"status",caption:"锅炉状态",width:50/5,hidden:false,type:"text"}
			);
			opts.columns.splice(3,1
			,{name:"purpose",caption:"锅炉用途",width:50/5,hidden:false,type:"text"}
			);
			hide.gridHide(opts);
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加锅炉信息",
				width:"786px",
				height:"571px",
				btn:['保存','取消'],
				url : "addPage.jhtml",
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
					width:"786px",
					height:"571px",
					btn:['更新','取消'],
					url : "updatePage.jhtml",
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
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-guoluxinxi3"></i>
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
		<div class="tips">
			<div class="tipscontent">
				<p>1.一个锅炉对应填写一行信息。</p>
				<p>2. 燃料年消耗总量：只能填写数字，不可填文字。如不可填“11万”，应为“110000”</p>
				<p>3．单位：固体燃料为吨，液体燃料为吨或万升，气体燃料为万立方米，用电量为千瓦</p>
				<p>4．治理措施编号：对应表3治理措施编号，系统下拉框中选择</p>
				<p>5．排放口编号：对应表2排放口编号，系统下拉框中选择</p>
			</div>
		</div>		
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
	
</body>
</html>