<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/form/form.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/js/z-tree/css/zTreeStyle/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
require(["grid","select","panel","multselect"],function(g){
		
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条角色信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除角色成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除角色失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加角色信息",
				url : "addrolePage.jhtml",
				data:"",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改角色信息",
				url : "updaterolePage.jhtml",
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
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
				    {name:"name",caption:"角色名称",hidden:false,type:"text"},
				    {name:"url",caption:"链接地址",hidden:false,type:"text"},
				    {name:"icon",caption:"图标",hidden:false,type:"text"},
				    {name:"pid",caption:"上级角色",hidden:false,type:"text"},
				    {name:"sort",caption:"角色排序",hidden:false,type:"text"},
				    {name:"id",caption:"ID",hidden:true,type:"text"},
			    	{name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;">[查看]</a>',
				  		 events :[{
			  				  type:'click',
			  				  select:'#view',
			  				  handle:function(){
		  				  			var id =grid1.getCurrentRowValue(this,'id');
		  							var opts = {
		  									title:"查看角色详细信息",
		  									url : "viewRolePage.jhtml?id="+id,
		  									cancel:function(){}
		  								}
		  								qy.panel(opts);
		  				  			
		  				  			
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
<body>
	<div class="query_block">
		<div class="query_title">
	      	<span >角色信息列表</span>
	   	</div>
		<div class = "container">
		<form id="queryForm" action="">
			<div id = 'queryPanel' class = "pull-left form-inline">
			
		      			      				    			      				    			      				    			      				    			      				    			      				    			      				    			      				    			    	
	           	<input type = "button" id = "query" class = "btn-general" value = '查询'></input>	
			</div>
		</form>	
		</div>
	</div>
	<div class = "grid-tools">
	    #if($shiro.hasPermission("admin:systemrole:delete"))
		<button id = "delete" class = "btn-denger" data-grid = "tab1" data-enable = "2" >删除</button>
		#if($shiro.hasPermission("admin:systemrole:update"))
		<button id = "update" class = "btn-warning"  data-grid = "tab1" data-enable = "1" >修改</button>
		#if($shiro.hasPermission("admin:systemrole:add"))
		<button id = "add" class = "btn-general" >添加</button>
	</div>
	<div id="tab1"  style ="width:99%;"></div> 
	
	<div id="mults"></div>
</body>
</html>