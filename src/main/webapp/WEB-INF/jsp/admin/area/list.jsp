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
	
	$("#source").select({filter:true,name:"source",zIndex:3,change:function(event, value){}});
	
	
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条区域信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除区域成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除区域失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加区域信息",
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
				title: "修改区域信息",
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
					    {name:"name",caption:"区域名称",hidden:false,type:"text"},
					    {name:"level",caption:"区域等级",hidden:false,type:"text"},
					    {name:"code",caption:"区域编码",hidden:false,type:"text"},
					    {name:"parent",caption:"上级区域编码",hidden:false,type:"text"},
					    /* {name:"longitudeMax",caption:"经度最大范围",hidden:false,type:"text"},
					    {name:"longitudeMin",caption:"经度最小范围",hidden:false,type:"text"},
					    {name:"latitudeMax",caption:"纬度最大范围",hidden:false,type:"text"},
					    {name:"latitudeMin",caption:"纬度最小范围",hidden:false,type:"text"}, */
					    {name:"id",caption:"ID",hidden:true,type:"text"},
					    {name:"source",caption:"数据来源",type:"text",format:function(value,rowValue,handle){
							if(value =='1'){
								//var parent = handle.parent("tr");
								//parent.find("#view").html("");
								return "导入";
							}else if(value =='2'){
								return "自定义";	
							}
							
						}},
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
	})
</script>
</head>
<body>
<div class="block ">
		<h1 class="title">
			区域信息
		</h1> 
			<div class="form-line form-line2 align-1">
			<form id="queryForm" action="#" onsubmit="return false">
			<div class="group col-lg-5f1 col-md-3 unmust"> 
					<label class="form-label" for="name">区域名称</label>
					<input type="text" name="name" id="name" >
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust"> 
					<label class="form-label" for="pollution">级别</label>
					<input type="text" name="level" id="level" >
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust"> 
					<label class="form-label" for="pollution">编码</label>
					<input type="text" name="code" id="code" >
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust"> 
					<label class="form-label" for="pollution">上级编码</label>
					<input type="text" name="parent" id="parent" >
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust"> 
					<label class="form-label" for="pollution">数据来源</label>
					<div data-code="区域【数据来源】" id="source" name="source" class=" selectF"></div>
				</div>
				<div class="group unCol unmust btnF">
					<a class="btn" id="query">查询</a>
				</div>
			</form>
		</div>
	</div>
	<div class = "grid-tools">
		<button id = "add" class = "btn-general" >添加</button>
		<button id = "update" class = "btn-warning"  data-grid = "tab1" data-enable = "1" >修改</button>
		<button id = "delete" class = "btn-denger" data-grid = "tab1" data-enable = "2" >删除</button>
	</div>
	<div id="tab1" ></div> 
</body>
</html>