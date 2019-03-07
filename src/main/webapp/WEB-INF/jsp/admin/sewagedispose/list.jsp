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
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>污水处理信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel","less","commonBase" ],function(g,hide){
		var opts = {
				tab:"tab",
				checkbox:false,
				num:false,
				autoW:true,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"sewagetype",caption:"污水类型",hidden:false,type:"text"},
					{name:"emissionQuantity",caption:"污水排放量(吨)",hidden:false,type:"text"},
					{name:"disposeRate",caption:"污水自处理率(%)",hidden:false,type:"text"},
					{name:"disposeTechnology",caption:"污水处理工艺",hidden:false,type:"text"},
					{name:"disposeEfficiency",caption:"治理效率（%）",hidden:false,type:"text"},
					{name:"isCapping",caption:"污水处理池是否加盖(是/否)",hidden:false,type:"text"},
					{name:"isGather",caption:"废气是否收集处理(是/否)",hidden:false,type:"text"},
					{name:"disposeWay",caption:"废气处理方式（请说明）",hidden:false,type:"text"},
					{name:"id",caption:"ID",hidden:true,type:"text"},
				],
			}
		hide.gridHide(opts);
		grid1 =  g.grid(opts);
		grid1.loadData();
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加污水处理信息",
				width:"780px",//646
				height:"783px",
				btn:['保存','取消'],
				url : "addPage.jhtml",
				yes : function(index){
					if($("#sewage_form").submit()){
						//layer.close(index);
					}
				
				},
				btn3:function(){
					$("#sewage_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$("#editBtn").click(function(){
			if($(this).attr("disabled")) return;
			var opts = {
					title:"修改污水处理信息",
					width:"780px",
					height:"783px",
					btn:['更新','取消'],
					url : "updatePage.jhtml",
					data:{"id":grid1.getSingleSelectedValue('id')},
					yes : function(index){
						$("#sewage_form").submit();
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
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-shengchanxinxi2"></i>
			污水处理信息
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