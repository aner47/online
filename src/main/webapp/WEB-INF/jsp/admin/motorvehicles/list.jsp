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
<title>机动车调查</title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
		$('#delete').click(function(e){//删除
			if($(this).attr("disabled")) return;
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条机动车调查信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除机动车调查成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除机动车调查失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#addBtn").click(function(){
			var opts = {
				title: "添加机动车调查信息",
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
				title: "修改机动车调查信息",
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
				    {name:"surveyPlace",caption:"调查地点",hidden:false,type:"text"},
				    {name:"surveyPeople",caption:"调查人",hidden:false,type:"text"},
				    {name:"surveyDate",caption:"调查日期",hidden:false,type:"date"},
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;">调查车辆</a>',
				    	events :[{
			  				  type:'click',
			  				  select:'#view',
			  				  handle:function(){
		  				  			var id =grid1.getCurrentRowValue(this,'id');
		  				  			location.href = "../surveycar/list.jhtml?motor_vehicles="+id;
			  				  }
		  				  	}]
					}	
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
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
			<i class="iconfont icon-paifangkoutongji1"></i>
			机动车调查信息
			<c:if test="${principal.userType != 'system'}">
				<div id="addBtn" class="titleBtn">添加</div>
				<div id="editBtn" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div>
				<div id="delete" class="titleBtn" data-grid="tab1" data-enable="2">删除</div>
			</c:if>
		</h1> 
		<div id="tab1" class="tab" ></div> 
		
	</div>

	
</body>
</html>