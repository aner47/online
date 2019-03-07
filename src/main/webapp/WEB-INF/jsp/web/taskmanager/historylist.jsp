<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title></title>
	<style>
		
	</style>
	<script type="text/javascript">
		require(["grid","select","panel","upload","radio","date","validate","commonBase"],function(g,s,p,upload){

			
			var enterpriseStatus = $('#enterpriseStatus').select({name:'enterpriseStatus',zIndex:1});
			var status = $('#status').select({name:'status',zIndex:1});



			
			var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"historyquery.jhtml",
				checkbox:false,
				num:false,
				columns :[
					{name:"id",caption:"ID",hidden:true,type:"text"},
					{name:"enterpriseName",caption:"企业名称",hidden:false,type:"text"},
					/* {name:"reportEnterpriseName",caption:"上报企业名称",hidden:false,type:"text"}, */
					{name:"contactPerson",caption:"联系人",hidden:false,type:"text"},
					{name:"contactPhone",caption:"联系电话",hidden:false,type:"text"},
					{name:"countyName",caption:"区县",hidden:false,type:"text"},
					/* {name:"report",caption:"是否上报",hidden:false,type:"text",valueSet:"是否"}, */
					/* {name:"photoPath",caption:"照片",icon:true,hidden:false,type:"text",valueSet:"是否"}, */
					{name:"detailAddress",caption:"详细地址",hidden:false,type:"text"},
					{name:"enterpriseStatus",caption:"企业状态",hidden:false,type:"text",valueSet:"【任务管理】企业状态"},
					{name:"status",caption:"任务状态",hidden:false,type:"text",valueSet:"【任务管理】状态"},
					{name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',	
						events :[{
							type:'click',
							select:'.icon-xiangqing',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'id');
								var opts = {
	  									title:"查看详细信息",
	  									url : "viewPage.jhtml?id="+id,
	  									width: '600px',
	  									yes:function(){
	  										layer.close(layer.index)
	  									},
	  									cancel:function(){}
	  								}
	  							qy.panel(opts);
								
		
							}
						}] 
					},


				],
				events:{
					rowSelected:function(handle){
						
					}
				}
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
			
			
			
			//查询
			$('#search').click(function(){
				var data = {};
				var array = $('#queryForm').serializeArray();
				$.each(array,function(i,v){
					data[v.name] = v.value;	

				})
				grid1.loadData(data);
			});
			//查询
			$('#export').click(function(){
				var param ="";
				var array = $('#queryForm').serializeArray();
				$.each(array,function(i,v){
					param += v.name+"="+v.value+"&";
				}) 
				param = param.substring(0,param.lastIndexOf("&"));
				location.href="../taskmanager/exportTaskManager.jhtml?"+param;
			});
			
		});
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-loutianweichang1"></i> 任务列表
		</h1>
		<form class="form" id="queryForm" action="#" onsubmit="return false">
			
			<div class="formLine">
				
				<div class="formPara">
					<label class="label">企业名称</label>
					<input id="enterpriseName" name="enterpriseName" class="projectdiv selectF"/>
				</div>
				<div class="formPara">
					<label class="label">企业状态</label>
					<div id="enterpriseStatus" data-code="【任务管理】企业状态" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">任务状态</label>
					<div data-code="【任务管理】状态" id="status"
						class="userSearchdiv selectF"></div>
				</div>
				
			</div>
			<div class="formLine">
				
				<button id="search" class="button" style="margin-right: 120px">搜索</button>
				<button id="export" class="button" >导出</button>
				
			</div>
			
		</form>
		<div id="tab1" class="tab" style="border-top: 1px solid #e7ebee; margin-top: 40px"></div>
	
	</div>
	
	
</body>
</html>