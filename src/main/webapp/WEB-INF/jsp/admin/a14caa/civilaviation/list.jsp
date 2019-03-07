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
<title>民航飞机信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		var opts = {
				tab:"tab",
				//checkbox:false,
				//num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"enterprise.name",caption:"机场名称",hidden:false,type:"text"},
					{name:"houseNumber",caption:"详细地址",hidden:false,type:"text"},
				    {name:"longitude",caption:"中心经度",hidden:false,type:"text"},
				    {name:"latitude",caption:"中心纬度",hidden:false,type:"text"},
				    {name:"passengerCapacity",caption:"客运量（万人次）",hidden:false,type:"text"},
				    {name:"commodityCapacity",caption:"货运量（万吨）",hidden:false,type:"text"},
				    /* {name:"totalOffLand",caption:"总起降次数",hidden:false,type:"text"},
				    {name:"january",caption:"一月",hidden:false,type:"text"},
				    {name:"february",caption:"二月",hidden:false,type:"text"},
				    {name:"march",caption:"三月",hidden:false,type:"text"},
				    {name:"april",caption:"四月",hidden:false,type:"text"},
				    {name:"may",caption:"五月",hidden:false,type:"text"},
				    {name:"june",caption:"六月",hidden:false,type:"text"},
				    {name:"july",caption:"七月",hidden:false,type:"text"},
				    {name:"august",caption:"八月",hidden:false,type:"text"},
				    {name:"september",caption:"九月",hidden:false,type:"text"},
				    {name:"october",caption:"十月",hidden:false,type:"text"},
				    {name:"november",caption:"十一月",hidden:false,type:"text"},
				    {name:"december",caption:"十二月",hidden:false,type:"text"}, */
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view" style="cursor: pointer;color:#888888;"><i id="qiluo" class="iconfont icon-bianji" style="color:#6495ED;">起降次数</i></a>',	
						events :[{
								type:'click',
								select:'#qiluo',
								handle:function(){
									var id =grid1.getCurrentRowValue(this,'id');
									location.href="../civilaviation/addlist.jhtml?airId="+id;
									
								}
							}] 
					},
					{name:"operate",caption:"操作",width:120,isHtml:true,html:'<a id="view" style="cursor: pointer;color:#888888;"><i id="tianbao" class="iconfont icon-bianji" style="color:#6495ED;">上传图片</i></a>',	
						events :[{
								type:'click',
								select:'#tianbao',
								handle:function(){
									var id =grid1.getCurrentRowValue(this,'enterprise.id');
									location.href="../photofile/departmentlist.jhtml?enterpriseId="+id+"&departmentType=MinHangJu";
									
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
			民航飞机信息
			<div id="addBtn" class="titleBtn">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
		</h1> 
		<div id="tab" class="tab"></div>
		<div class="tips">
			<div class="tipscontent">
					
			</div>
		</div>		
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>