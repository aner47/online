<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/from.less" />
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
	
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条区域信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加信息",
				url : "addPage.jhtml",
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
				title: "修改信息",
				url : "updatePage.jhtml",
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
					    {name:"modifier",caption:"修改人",hidden:false,type:"text"},
					    {name:"description",caption:"修改内容",hidden:false,type:"text"},
					    {name:"createDate",caption:"修改时间",hidden:false,type:"date_time"},
					    {name:"id",caption:"ID",hidden:true,type:"text"},
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
			$("#fixed_return").on("click", function() {
				window.location.href = "../enterprise/enterpriseTaskList.jhtml";
				top.loadMenu('188');
			})
	})
</script>
</head>
<style>
.display_common {
	float: right;
	margin-right: 19px;
	font-size: 16px;
	cursor: pointer;
}
.display_common:hover {
	color: #4d4d4d;
}
</style>
<body>
<div class="block ">
		<h1 class="title">
			<span>修改记录信息</span>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn display_common">返回</div>
			</c:if>
		</h1> 
			<div class="form-line form-line2 align-1">
			<form id="queryForm" action="#" onsubmit="return false">
				<div class="group col-lg-5f1 col-md-3 unmust"> 
					<label class="form-label" for="name">修改人</label>
					<input type="text" name="modifier" id="modifier" >
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust"> 
					<label class="form-label" for="pollution">修改内容</label>
					<input type="text" name="description" id="description" >
				</div>
				
				<div class="group unCol unmust btnF">
					<a class="btn" id="query">查询</a>
				</div>
			</form>
		</div>
	</div>
	<div class = "grid-tools">
		<button id = "add" class = "btn-general" >添加</button>
		<!-- <button id = "update" class = "btn-warning"  data-grid = "tab1" data-enable = "1" >修改</button>
		<button id = "delete" class = "btn-denger" data-grid = "tab1" data-enable = "2" >删除</button> -->
	</div>
	<div id="tab1" ></div> 
</body>
</html>