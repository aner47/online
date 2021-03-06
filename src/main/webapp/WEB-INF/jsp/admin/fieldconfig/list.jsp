<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet/less" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/from.less" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
	var moduleId = ${moduleId};
	$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条模块信息信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(','),moduleId:moduleId},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除模块信息成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除模块信息失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加模块信息信息",
				url : "addPage.jhtml",
				data:{moduleId:moduleId},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改字段信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0],moduleId:moduleId},
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
				data:{pageSize:10,pageNumber:0,moduleId:moduleId},
				url:"query.jhtml",
				columns :[
				    {name:"name",caption:"字段名称",hidden:false,type:"text",sort:true},
				    {name:"value",caption:"字段名称对应key",hidden:false,type:"text"}, 
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
				  		 events :[{
			  				  type:'click',
			  				  select:'#view',
			  				  handle:function(){
		  				  			var id =grid1.getCurrentRowValue(this,'id');
		  							var opts = {
		  									title:"查看菜单详细信息",
		  									url : "viewPage.jhtml?id="+id,
		  									yes : function(index){
		  										layer.close(index);
		  									},
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
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-wuranyuan"></i> 字段信息
		</h1>
		
	</div>
	<div class="grid-tools">
		<button id="add" class="btn-general">添加</button>
		<button id="update" class="btn-warning" data-grid="tab1"
			data-enable="1">修改</button>
		<button id="delete" class="btn-denger" data-grid="tab1"
			data-enable="2">删除</button>
	</div>
	<div id="tab1"></div>
	<div class="buttonF">
         <div class="align-right">
             <a class="btn" id="back" href="javascript:history.back(-1)">返回</a>
         </div>
     </div>
</body>
</html>