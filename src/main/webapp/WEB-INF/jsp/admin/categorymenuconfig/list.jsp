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
	var enterpriseType = $("#enterpriseType", "#queryForm").select({
        name : "enterpriseType",
        empty: true
    });
	
	//行业类别（大类）
	var categoryMain =  $("#industryCategoryCodeMainQuery").select({filter:true,name:"industryCategoryCodeMain",zIndex:8,param:"categoryLevel:MAIN",change:function(event, value){
    	//var text = categoryMain.getSelectCaption();
    	//$("input[name=industryCategoryNameMain]").val(text);
    	categoryMiddle.clear();
    	categoryMiddle.loadData("parent:"+value)
    }});
	//行业类别（中类）
	var categoryMiddle =  $("#industryCategoryCodeMiddleQuery").select({name:"industryCategoryCodeMiddle",zIndex:7,change:function(event, value){
    	//var text = categoryMiddle.getSelectCaption();
    	//$("input[name=industryCategoryNameMiddle]").val(text);
    	categorySmall.clear();
    	categorySmall.loadData("parent:"+value);
    }});
	//行业类别（小类）
	var categorySmall =  $("#industryCategoryCodeSmallQuery").select({name:"industryCategoryCodeSmall",zIndex:6,change:function(event, value){
    //	var text = categorySmall.getSelectCaption();
    	//$("input[name=industryCategoryNameSmall]").val(text);
    }});
	
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条行业分类信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除行业分类成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除行业分类失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加行业对应菜单",
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
				title: "修改行业分类信息",
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
					    {name:"id",caption:"ID",hidden:true,type:"text"},
					    {name:"enterpriseType",caption:"填表类型",hidden:false,type:"text",valueSet:"企业【企业类型】"},
					    {name:"categoryId",caption:"行业",hidden:false,type:"text",valueSet:"行业"},
					    {name:"menusName",caption:"显示菜单",hidden:false,type:"text"},
					   
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">行业分类</h1>
		<form id="queryForm" action="" method="post">
			<div class="form-line form-line2">
				<div class="group col-lg-5f1 col-md-3 unmust">
					<label class="form-label" for="parent">填表类型</label>
					<div id="enterpriseType" class="selF" data-code="企业【企业类型】"
						style="width: 100%; height: 100%"></div>
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust">
					<label class="form-label" for="parent">行业(大类)</label>
					<div id="industryCategoryCodeMainQuery" class="selF" data-code="行业"
						style="width: 100%; height: 100%"></div>
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust">
					<label class="form-label" for="parent">行业(中类)</label>
					<div id="industryCategoryCodeMiddleQuery" class="selF" data-code="行业"
						style="width: 100%; height: 100%"></div>
				</div>
				<div class="group col-lg-5f1 col-md-3 unmust">
					<label class="form-label" for="parent">行业(小类)</label>
					<div id="industryCategoryCodeSmallQuery" class="selF" data-code="行业"
						style="width: 100%; height: 100%"></div>
				</div>
				


				<div class="group  unmust btnF">
					<a class="btn" id="query">查询</a>
				</div>
			</div>
		</form>
	</div>
	<div class="grid-tools">
		<button id="add" class="btn-general">添加</button>
		<button id="update" class="btn-warning" data-grid="tab1"
			data-enable="1">修改</button>
		<button id="delete" class="btn-denger" data-grid="tab1"
			data-enable="2">删除</button>
	</div>
	<div id="tab1"></div>
</body>
</html>