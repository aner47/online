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
<title>污染治理措施</title>
<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		var opts = {
				tab:"tab",
				checkbox:false,
				num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
				{name:"id",caption:"ID",hidden:true,type:"text",prefix:"ZL"},
				{name:"gmno",caption:"编号",width:60/5,hidden:false,type:"text",prefix:"治理"},
				{name:"measure",caption:"措施",width:80/5,hidden:false,type:"text"},
				{name:"name",caption:"治理工艺",width:80/5,hidden:false,type:"text"},
				{name:"putDate",caption:"投运时间",width:90/5,hidden:false,type:"date_yyyy_MM"},
				{name:"stopDate",caption:"停运时间",width:90/5,hidden:false,type:"date_yyyy_MM"},
				{name:"putintoRate",caption:"投运率(%)",width:80/5,hidden:false,type:"text"},
				{name:"collectEfficiency",caption:"废气收集效率（%）",width:80/5,hidden:false,type:"text"},
				{
					name:"contaminantType",
					caption:"污染物类型",
					width:80/5,hidden:false,type:"text",
					format: function (value, calValue, handle) {
						if (value) {
							switch (value) {
								case "SO2": return "SO₂";
								case "NOx": return "NOₓ";
								case "NH3": return "NH₃";
								default: return value;
							}
						}
					}
				},
				{name:"contaminantRate",caption:"污染物去除效率（%）",width:80/5,hidden:false,type:"text"},
				{name:"isGGH",caption:"是否安装GGH(烟气加热)",width:80/5,hidden:false,type:"text"},
				{name:"firstPut",caption:"初次投入成本（万元）",width:80/5,hidden:false,type:"text"},
				{name:"operatingCost",caption:"运行成本（万元/月）",width:80/5,hidden:false,type:"text"},
				
				],
			}
		
			hide.gridHide(opts);
			grid1 = g.grid(opts);
			grid1.loadData();
		
		$("#addBtn").click(function(){
			var opts = {
				title:"添加污染治理措施",
				width:"646px",
				height:"676px",
				btn:['保存','取消'],
				url : "addPage.jhtml",
				yes : function(index){
					$("#control_form").submit();
					grid1.loadData();
					//layer.close(index);
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$("#editBtn").click(function(){
			if($(this).attr("disabled")) return;
			var opts = {
					title:"修改污染治理措施",
					width:"646px",
					height:"676px",
					btn:['更新','取消'],
					url : "updatePage.jhtml",
					data:{"id":grid1.getSingleSelectedValue('id')},
					yes : function(index){
						$("#control_form").submit();
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
								qy.fail2({title:'删除失败！</br>可能在窑炉，或者工段，锅炉有被引用！'});
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
			<i class="iconfont icon-wuranzhilicuoshi"></i>
			废气污染治理措施信息
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
				<p>1.一套治理措施对应填写一行信息。</p>
				<p>2．治理工艺：选填以下对应编号即可</p>
				<p>【脱硫工艺】（1）烟气循环流化床法（2）炉内喷钙法（3）石灰石/石灰-石膏法（4）双碱法（5）氧化镁法（6）氨法（7）旋转喷雾干燥法</p>
				<p>【脱硝工艺】（8）低氮燃烧器（9）选择性非催化还原法（SNCR）（10）选择性催化还原法（SCR）</p>
				<p>【除尘工艺】（11）重力沉降法（12）湿法除尘法（13）静电除尘法（14）布袋除尘法（15）电袋复合除尘法（16）旋风除尘法（17）无组织尘控制技术</p>
				<p>【有机废气处理技术】（18）冷凝法（19）吸收法（20）吸附法（21）燃烧法（22）催化氧化法（23）催化还原法（24）其他处理技术（请说明）（25）无</p>
				<p>3．年工作时间：指该设备2017年度实际工作时间</p>
				<p>4．废气收集效率：指进入治理装置处理的废气量占总废气量的百分比，如果从废气收集系统开始处到治理设施结束处是一个完全封闭的系统，则其废气收集效率为100%</p>
				<p>5．污染物去除效率：指进入治理装置废气，经处理后所去除的污染物量占进入装置前含有的总污染物量的百分比，可填写设计污染物处理效率</p>
			</div>
		</div>
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>