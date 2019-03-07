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
<title>市海事局-客货运输船舶活动信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		var opts = {
				tab:"tab",
				//checkbox:false,
				//num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"enterprise.name",caption:"港区名称",hidden:false,type:"text"},
					{name:"shipType",caption:"船舶类型",hidden:false,type:"text"},
					{name:"number",caption:"数量（艘）",hidden:false,type:"text"},
					{name:"arriveLeaveTotal",caption:"抵离港次数（次）",hidden:false,type:"text"},
					{name:"avgLoad",caption:"平均载重吨（吨）",hidden:false,type:"text"},
					{name:"mainAvgPower",caption:"主机平均功率（千瓦）",hidden:false,type:"text"},
					{name:"auxiliaryAvgPower",caption:"辅机平均功率（千瓦）",hidden:false,type:"text"},
					{name:"boilerPower",caption:"锅炉功率（千瓦）",hidden:false,type:"text"},
					{name:"fuelType",caption:"燃油类型",hidden:false,type:"text"},
					{name:"consumption",caption:"消耗量（吨/年）",hidden:false,type:"text"},
					{name:"sulfurRate",caption:"含硫率（％）",hidden:false,type:"text"},
					{name:"id",caption:"ID",hidden:true,type:"text"},
					{name:"operate",caption:"操作",width:120,isHtml:true,html:'<a id="view" style="cursor: pointer;color:#888888;"><i id="tianbao" class="iconfont icon-bianji" style="color:#6495ED;">上传图片</i></a>',	
						events :[{
								type:'click',
								select:'#tianbao',
								handle:function(){
									var id =grid1.getCurrentRowValue(this,'enterprise.id');
									location.href="../photofile/departmentlist.jhtml?enterpriseId="+id+"&departmentType=HaiShiJu";
									
								}
							}] 
					},

				],
			}
			hide.gridHide(opts);
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加",
				width:"646px",
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
					title:"修改",
					width:"646px",
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
				title:'是否删除所选'+grid1.getSelectedValue('id').length+'条信息?',
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
	})
</script>
<style>
.block{
	padding-bottom: 120px;
}
.examine {
	width: 42px;
	height: 30px;
	line-height: 30px!important;
	text-align: center;
	color: #fff !important;
	border-radius: 13px;
	background: #4fc1ee;
	margin-top: 9px !important;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-xuqin"></i>
			客货运输船舶活动信息
			<c:if test="${principal.userType != 'system'}">
			<!-- <div id="back" class="titleBtn">返回</div> -->
			<div id="addBtn" class="titleBtn">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<!-- <div id="fixed_return" class="titleBtn">返回</div> -->
				<div id="check" class="titleBtn examine">审核</div>
			</c:if>
		</h1> 
		<div id="tab" class="tab"></div>
		<div class="tips">
			<div class="tipscontent">
				<!-- <p>1.一个锅炉对应填写一行信息。</p>
				<p>2. 燃料年消耗总量：只能填写数字，不可填文字。如不可填“11万”，应为“110000”</p>
				<p>3．单位：固体燃料为吨，液体燃料为吨或万升，气体燃料为万立方米，用电量为千瓦</p>
				<p>4．治理措施编号：对应表3治理措施编号，系统下拉框中选择</p>
				<p>5．排放口编号：对应表2排放口编号，系统下拉框中选择</p> -->	
			</div>
		</div>		
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>