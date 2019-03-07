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
<title>市政项目建筑工地信息</title>
<script type="text/javascript">
	require(["grid","hide","upload","upload","select","panel"],function(g,hide,upload){
		var opts = {
				tab:"tab",
				//checkbox:false,
				//num:false,
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"city",caption:"行政区",hidden:false,type:"text"},
					{name:"enterprise.name",caption:"项目名称",hidden:false,type:"text"},
					{name:"houseNumber",caption:"工程详细地址",hidden:false,type:"text"},
					{name:"longitude",caption:"中心经度",hidden:false,type:"text"},
					{name:"latitude",caption:"中心纬度",hidden:false,type:"text"},
					{name:"floorSpace",caption:"占地面积（平方米）",hidden:false,type:"text"},
					{name:"buildSpace",caption:"建筑面积（平方米）",hidden:false,type:"text"},
					{name:"designExcavations",caption:"设计开挖土方量（立方米）",hidden:false,type:"text"},
					{name:"excavatedVolume",caption:"已开挖土方量（立方米）",hidden:false,type:"text"},
					{name:"startDate",caption:"开工时间（年/月/日）",hidden:false,type:"date"},
					{name:"endDate",caption:"竣工时间（年/月/日）",hidden:false,type:"date"},
					{name:"dustMeasures",caption:"扬尘控制措施",hidden:false,type:"text",valueSet:"房屋建筑工地【扬尘控制措施】"},
					{name:"paintUseVol",caption:"建筑涂料使用量（吨）",hidden:false,type:"text"},
					{name:"id",caption:"ID",hidden:true,type:"text"},
					{name:"operate",caption:"操作",width:120,isHtml:true,html:'<a id="view" style="cursor: pointer;color:#888888;"><i id="tianbao" class="iconfont icon-bianji" style="color:#6495ED;">上传图片</i></a>',	
						events :[{
								type:'click',
								select:'#tianbao',
								handle:function(){
									var id =grid1.getCurrentRowValue(this,'enterprise.id');
									location.href="../photofile/departmentlist.jhtml?enterpriseId="+id+"&departmentType=ZJWShiZhengJianZhuGongDi";
									
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
		
		//导入
		$("#import").click(function(){
			$('#fileHandle').click();
		});
		//模板下载
		$("#download").click(function(){
			location.href="../citybuildsite/downloanExcel.jhtml"
		});
		
		upload.upload({element:"#fileHandle",acceptCallBack:function(handle,data){
			if(data.message.code == 20000){
				/* $("#fileName").html(handle.file.name);
				$("#filePath").val(data.realPath);
				console.log(data.url); */
				console.log(data.url);
				 qy.ajax({
					url:"import.jhtml",
					data:{filePath:data.realPath},
					callBack:function(data){
						if(data.code == '20000'){
							/* qy.suc({title:data.tips});
							
							grid1.refresh(); */
							console.log(data.entity);
							sessionStorage["par1"]=JSON.stringify(data.entity);
							//qy.suc({title:data.tips+data.content.successNum+"条"});
							 var opts1={
									title:"导入日志消息",
									url:"viewLog.jhtml",
									data:{content:''},
									width:'800px',
									yes:function(){
										layer.closeAll('page');
									},cancel:function(){}
									};
							qy.panel(opts1); 
							grid1.refresh();
						}else{
							qy.fail({title:data.content});
						}
						
					}
				}) 
			}else{
				layer.msg("文件上传失败"+data.message.content);
			}
		
		}
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
			市政项目建筑工地信息
			<c:if test="${principal.userType != 'system'}">
			<!-- <div id="back" class="titleBtn">返回</div> -->
			<div id="addBtn" class="titleBtn">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			<div id="import" class="titleBtn" >导入</div>
			<div id="download" class="titleBtn" >模板下载</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<!-- <div id="fixed_return" class="titleBtn">返回</div> -->
				<div id="check" class="titleBtn examine">审核</div>
			</c:if>
		</h1> 
		<div id="tab" class="tab"></div>
		<input type="hidden" id="fileHandle" style="margin-left: 10px" value="导入" />
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