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
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条任务内容信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除任务内容成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除任务内容失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加任务内容信息",
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
				title: "修改任务内容信息",
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
				data:{pageSize:10,pageNumber:0,taskId:'${taskId}'},
				url:"query.jhtml",
				columns :[
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"name",caption:"名称",hidden:false,type:"text"},
				    {name:"standardNameId",caption:"标准名称ID",hidden:false,type:"text"},
				    {name:"standardUnitId",caption:"标准单位ID",hidden:false,type:"text"},
				    {name:"standardName",caption:"标准名称",hidden:false,type:"text"},
				    {name:"standardUnit",caption:"标准单位",hidden:false,type:"text"},
				    {name:"conversionFactor",caption:"转换系数",hidden:false,type:"text"},
				    {name:"industryCode",caption:"行业代码",hidden:false,type:"text"},
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">任务内容</h1>
		<div class="form-line form-line2">
			<form id="queryForm" action="">
				<div id='queryPanel' class="pull-left form-inline"></div>
			</form>
		</div>
	</div>
	<div class="grid-tools">
		<!-- <button id = "add" class = "btn-general" >添加</button> -->
		<button id="update" class="btn-warning" data-grid="tab1"
			data-enable="1">修改</button>
		<!-- <button id = "delete" class = "btn-denger" data-grid = "tab1" data-enable = "2" >删除</button> -->
	</div>
	<div id="tab1"></div>
	<div class="buttonF">
        <div class="align-right">
            <%String ref = request.getHeader("REFERER");%>
      <input type="button" id="backBtn" name="button" class="button_return" value="返回"  onclick="javascript:window.location='<%=ref%>'">
        </div>
    </div>
</body>
</html>