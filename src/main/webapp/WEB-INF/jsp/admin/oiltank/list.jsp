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
		
		function refesh(){
			alert("刷新！")
		}
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加有机液体储罐信息",
				width:"646px",
				height:"519px",
				btn:['保存','取消'],
				url : "addPage.jhtml",
				yes : function(index){
					if($("#oiltank_form").submit()){
						//layer.close(index);
					}
				},
				btn3:function(){
					$("#oiltank_form").submit();
					grid1.loadData();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$("#editBtn").click(function(){
			if($(this).attr("disabled")) return;
			var opts = {
				title:"修改有机液体储罐信息",
				width:"646px",
				height:"519px",
				btn:['更新','取消'],
				url : "updatePage.jhtml",
				data:{"id":grid1.getSingleSelectedValue('id')},
				yes : function(index){
					$("#oiltank_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		var opts = {
			tab:"tab",
			checkbox:false,
			num:false,
			data:{pageSize:10,pageNumber:0},
			url:"query.jhtml",
			columns :[
			{name:"id",caption:"编号",width:60/5,hidden:true,type:"text",prefix:"储罐"},
			{name:"no",caption:"编号",width:60/5,hidden:false,type:"text",prefix:"储罐"},
			{name:"tankMaterialType",caption:"材料类型",width:80/5,hidden:false,type:"text"},
			{name:"tankType",caption:"储罐类型",width:80/5,hidden:false,type:"text"},
			{name:"height",caption:"高度/长度<br/>（米）",width:81/5,hidden:false,type:"text"},
			{name:"avgDiameter",caption:"平均直径<br/>（米）",width:60/5,hidden:false,type:"text"},
			{name:"capacity",caption:"储罐容量<br/>（立方米）",width:81/5,hidden:false,type:"text"},
			{name:"workDays",caption:"全年使用<br/>天数（天）",width:81/5,hidden:false,type:"text"},
			{name:"storageCapacity",caption:"储罐年总<br/>储量（吨）",width:81/5,hidden:false,type:"text"},
			{name:"storageCycle",caption:"储存周期<br/>（天）",width:60/5,hidden:false,type:"text"},
			{name:"fillTimes",caption:"年装卸/<br/>填充次数",width:80/5,hidden:false,type:"text"},
			

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
			<i class="iconfont icon-chuguanxinxi2"></i>
			有机液体储罐信息
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
			<p>1．一个有机液体储罐对应填写一行信息，特别说明，此处统计的存储物质为石油产品。</p>
			<p>2．高度/长度：指该储罐的高度（立式）或者长度（卧式）</p>
			<p>3．平均直径：指该储罐圆形横截面的直径</p>
			<p>4．储罐容量：指该储罐的平均工作容量，可通过储罐体积与利用率进行估算</p>
			<p>5．储罐年总储量：该储罐于2017年度的总储量，可通过6.储罐容量和7.全年使用天数，或者9.每年装卸/填充次数和每次的平均装卸量来进行估算</p>	
			<p>6．储存周期：该储罐消耗与5.储罐体积相等的石油产品所需时间</p>
			<p>7．年装卸/填充次数：指2017年度填充存储货品的次数，无论填充时罐内是否还有剩余，进行一次填充作业就算作一次。</p>
		</div>
			
		</div>	
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>