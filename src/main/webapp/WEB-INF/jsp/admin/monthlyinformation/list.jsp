<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/from.less" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生产信息</title>
<script type="text/javascript">
	require(["grid","hide","select","panel"],function(g,hide){
		
		// 参数
		var monthlyType = '${monthlyType}';
		
		//发电机组
		if(monthlyType == 'powerplant'){
			$("#tips_id .tipscontent").children().eq(0).html('1. 数据来源： 来源于自备发电机组')
			$("#tips_id .tipscontent").children().eq(1).html('2. 名称： 发电量、发热量、燃料名称')
			$("#tips_id .tipscontent").children().eq(2).html('3. 单位： 单位使用本行业中通用的计量单位，但尽量使用体积单位与重量单位，避免使用“桶、扎、盒、片、箱”等数量单位')
			$("#tips_id .tipscontent").children().eq(3).html('4. 每月产量/消耗量： 指该企业内在2017年度每月的燃料消耗量、原料使用量和产品产量，12个月加起来的总量应该等于前表中的“年产量”对应')
		//锅炉	
		}else if(monthlyType =='boiler'){
			$("#tips_id .tipscontent").children().eq(0).html('1. 数据来源： 来源于锅炉')
			$("#tips_id .tipscontent").children().eq(1).html('2. 名称： 燃料的名称')
			$("#tips_id .tipscontent").children().eq(2).html('3. 单位： 单位使用本行业中通用的计量单位，但尽量使用体积单位与重量单位，避免使用“桶、扎、盒、片、箱”等数量单位')
			$("#tips_id .tipscontent").children().eq(3).html('4. 每月消耗量： 指该企业内在2017年度每月的燃料消耗量、原料使用量和产品产量，12个月加起来的总量应该等于前表中的“年产量”对应')
		//煤气炉	
		}else if(monthlyType =='gasstove'){
			$("#tips_id .tipscontent").children().eq(0).html('1. 数据来源： 来源于煤气发生炉')
			$("#tips_id .tipscontent").children().eq(1).html('2. 名称： 指煤气、燃料的名称')
			$("#tips_id .tipscontent").children().eq(2).html('3. 单位： 单位使用本行业中通用的计量单位，但尽量使用体积单位与重量单位，避免使用“桶、扎、盒、片、箱”等数量单位')
			$("#tips_id .tipscontent").children().eq(3).html('4. 每月产量/消耗量： 指该企业内在2017年度每月的燃料消耗量、原料使用量和产品产量，12个月加起来的总量应该等于前表中的“年产量”对应')
		//炉窑	
		}else if(monthlyType =='kiln'){
			$("#tips_id .tipscontent").children().eq(0).html('1. 数据来源： 来源于炉窑')
			$("#tips_id .tipscontent").children().eq(1).html('2. 名称： 指燃料、原料、产品的名称')
			$("#tips_id .tipscontent").children().eq(2).html('3. 单位： 单位使用本行业中通用的计量单位，但尽量使用体积单位与重量单位，避免使用“桶、扎、盒、片、箱”等数量单位')
			$("#tips_id .tipscontent").children().eq(3).html('4. 每月产量/消耗量： 指该企业内在2017年度每月的燃料消耗量、原料使用量和产品产量，12个月加起来的总量应该等于前表中的“年产量”对应')
		//原辅料	
		}else if(monthlyType =='rawmaterials'){
			$("#tips_id .tipscontent").children().eq(0).html('1. 数据来源： 来源于生产工段、原辅料')
			$("#tips_id .tipscontent").children().eq(1).html('2. 名称： 指原辅料、产品的名称')
			$("#tips_id .tipscontent").children().eq(2).html('3. 单位： 单位使用本行业中通用的计量单位，但尽量使用体积单位与重量单位，避免使用“桶、扎、盒、片、箱”等数量单位')
			$("#tips_id .tipscontent").children().eq(3).html('4. 每月产量/消耗量： 指该企业内在2017年度每月的燃料消耗量、原料使用量和产品产量，12个月加起来的总量应该等于前表中的“年产量”对应')
		}
		
		
		var a='${enterpriseId}';
		$("#add").click(function(){
			var opts = {
				title:"添加分月数据信息",
				width:"646px",
				height:"611px",
				data:{monthlyType:'${monthlyType}',sourceId:'${sourceId}'},
				btn:['保存','取消'],
				url : "addPage.jhtml",
				yes : function(index){
					$("#monthinfo_form").submit();
					//layer.close(index);
				},
				btn3:function(){
					$("#monthinfo_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		
		
		$("#update").click(function(){
			if($(this).attr("disabled"))return;
			var opts = {
				title:"修改分月数据信息",
				width:"646px",
				height:"611px",
				url : "updatePage.jhtml",
				data:{"id":grid1.getSingleSelectedValue('id'),monthlyType:'${monthlyType}',sourceId:'${sourceId}'},
				yes : function(index){
					$("#monthinfo_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		var opts = {
			tab:"tab",
			checkbox:false,
			num:false,
			autoW: true,
			data:{pageSize:10,pageNumber:0,monthlyType:'${monthlyType}',sourceId:'${sourceId}'},
			 url:"query.jhtml",
			columns :[
			//{name:"monthlyInformationProcess",caption:"数据来源",width:80/5,hidden:false,type:"text",valueSet:"祥表【数据来源】"},
			{name:"name",caption:"数据名称",width:80/5,hidden:false,type:"text"},
			{name:"unit",caption:"单位",width:40/5,hidden:false,type:"text"},
			{name:"year",caption:"年份",width:40/5,hidden:false,type:"text"},
			{name:"monthType",caption:"类型",width:40/5,hidden:false,type:"text",format:function(value){
				if(value == 'product'){
					return "产品";
				}else if(value == 'fuel'){
					return "燃料";
				}else if(value == 'rawMaterials'){
					return "原辅料";
				}
			}},
			{name:"january",caption:"一月",width:40/5,hidden:false,type:"text"},
			{name:"february",caption:"二月",width:40/5,hidden:false,type:"text"},
			{name:"march",caption:"三月",width:40/5,hidden:false,type:"text"},
			{name:"april",caption:"四月",width:40/5,hidden:false,type:"text"},
			{name:"may",caption:"五月",width:40/5,hidden:false,type:"text"},
			{name:"june",caption:"六月",width:40/5,hidden:false,type:"text"},
			{name:"july",caption:"七月",width:40/5,hidden:false,type:"text"},
			{name:"august",caption:"八月",width:40/5,hidden:false,type:"text"},
			{name:"september",caption:"九月",width:40/5,hidden:false,type:"text"},
			{name:"october",caption:"十月",width:40/5,hidden:false,type:"text"},
			{name:"november",caption:"十一月",width:40/5,hidden:false,type:"text"},
			{name:"december",caption:"十二月",width:40/5,hidden:false,type:"text"},
			{name:"total",caption:"总计",width:40/5,hidden:false,type:"text",format:function(value,data){
				return parseFloat((data.january + data.february+data.march+data.april+data.may+data.june+data.july+data.august+data.september+data.october+data.november+data.december).toFixed(10));
				}
			},
			{name:"id",caption:"ID",hidden:true,type:"text"},

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
				},
				cancel:function(){
					// 重新加载表格
					grid1.loadData();
				}
			});
		});
		
	})
</script>
<style>
.block{
	padding-bottom: 120px;
}
.buttonF {
	font-size: 14px;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-fenyueshuju1"></i>
			分月数据信息
			<c:if test="${principal.userType != 'system'}">
			<div id="add" class="titleBtn" >添加</div>
			<div id="update" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			<div style="padding: 0 34px 0 0;" class="buttonF">
				
			</div>
			</c:if>
			<div class="align-right">
					<a class="titleBtn" id="back" href="javascript:history.back(-1)">返回</a>
				</div>
		</h1> 
		<div id="tab" class="tab"></div>
		<div id="tips_id" class="tips">
			<div class="tipscontent">
				<p>1．数据来源：来源于自备发电机组</p>
				<p>2．名称：发电量、发热量、燃料名称</p>
				<p>3．单位：单位使用本行业中通用的计量单位，但尽量使用体积单位与重量单位，避免使用“桶、扎、盒、片、箱”等数量单位</p>
				<p>4．每月产量/消耗量：指该企业内在2017年度每月的燃料消耗量、原料使用量和产品产量， 12个月加起来的总量应该等于前表中的“年产量”对应。</p>
			</div>
		</div>
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>