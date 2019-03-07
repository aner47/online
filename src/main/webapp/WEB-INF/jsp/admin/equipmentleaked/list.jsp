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
<title>设备泄漏</title>
<script type="text/javascript">
require(["grid","hide","select","panel","less"],function(g,hide){
		$('#delete').click(function(e){//删除
			if($(this).attr("disabled")) return;
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条设备泄露信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除设备泄露成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除设备泄露失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#addBtn").click(function(){
			var opts = {
				title: "添加设备泄露信息",
				url : "addPage.jhtml",
				width:"646px",
				height:"842px",
				data:"",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#editBtn").click(function(){
			if($(this).attr("disabled")) return;
			var opts = {
				title: "修改设备泄露信息",
				url : "updatePage.jhtml",
				width:"646px",
				height:"842px",
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
				autoW:true,
				columns :[
								    {name:"production",caption:"生产设备数量（套）",hidden:false,type:"text"},
								    {name:"involvedVoc",caption:"涉及voc设备数量（套）",hidden:false,type:"text"},
								    {name:"developLdar",caption:"开展LDAR设备数量（套）",hidden:false,type:"text"},
								    {name:"developLdarTime",caption:"开展LDAR（轮）",hidden:false,type:"text"},
								    {name:"noDevelopLdar",caption:"未开展LDAR设备数量（套）",hidden:false,type:"text"},
								    {name:"exemption",caption:"豁免装置（套）",hidden:false,type:"text"},
								    {name:"controlledSealedPoint",caption:"受控密封点（个）",hidden:false,type:"text"},
								    {name:"unreachablePoint",caption:"不可达点（个）",hidden:false,type:"text"},
								    {name:"estimationEethod",caption:"估算方法",hidden:false,type:"text"},
								    {name:"leakedEmissions",caption:"泄露排放量（吨）",hidden:false,type:"text"},
								    {name:"standard",caption:"是否达标",hidden:false,type:"text",valueSet:"是否"},
								    {name:"domesticLevel",caption:"是否达到国内水平",hidden:false,type:"text",valueSet:"是否"},
								    {name:"reducePotential",caption:"消减潜力（吨）",hidden:false,type:"text"},
								    {name:"domesticAdvancedLevel",caption:"是否达到国内先进水平",hidden:false,type:"text",valueSet:"是否"},
								    {name:"valveGas",caption:"阀气体（件）",hidden:false,type:"text"},
								    {name:"valveLightLiquid",caption:"阀轻液体（件）",hidden:false,type:"text"},
								    {name:"valveHeavyLiquid",caption:"阀重液体（件）",hidden:false,type:"text"},
								    {name:"pumpLightLiquid",caption:"泵轻液体（件）",hidden:false,type:"text"},
								    {name:"pumpHeavyLiquid",caption:"泵重液体（件）",hidden:false,type:"text"},
								    {name:"compressor",caption:"压缩机（件）",hidden:false,type:"text"},
								    {name:"safetyValveGas",caption:"安全阀气体（件）",hidden:false,type:"text"},
								    {name:"safetyValveLightLiquid",caption:"安全阀轻液体（件）",hidden:false,type:"text"},
								    {name:"safetyValveHeavyLiquid",caption:"安全阀重液体（件）",hidden:false,type:"text"},
								    {name:"flangeGas",caption:"法兰气体（件）",hidden:false,type:"text"},
								    {name:"flangeLightLiquid",caption:"法兰轻液体（件）",hidden:false,type:"text"},
								    {name:"flangeHeavyLiquid",caption:"法兰重液体（件）",hidden:false,type:"text"},
								    {name:"openPiping",caption:"开口管线（件）",hidden:false,type:"text"},
								    {name:"samplingLinks",caption:"采样链接（件）",hidden:false,type:"text"},
								    {name:"id",caption:"ID",hidden:true,type:"text"},
								    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;">添加检测</a>',
								    	events :[{
							  				  type:'click',
							  				  select:'#view',
							  				  handle:function(){
						  				  			var id =grid1.getCurrentRowValue(this,'id');
						  				  			location.href = "../leakedcheck/list.jhtml?equipment_leaked="+id;
							  				  }
						  				  	}]
									}	
								    	
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
		
	})
</script>
</head>
<style>
.block{
	padding-bottom: 120px;
}
</style>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-shebeixielou"></i>
			设备泄漏信息
			<c:if test="${principal.userType != 'system'}">
				<div id="addBtn" class="titleBtn">添加</div>
				
				<div id="editBtn" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div>
				<div id="delete" class="titleBtn" data-grid="tab1" data-enable="2">删除</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
			</c:if>
		</h1> 
		<div id="tab1" class="tab" ></div> 
		
	</div>

	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>