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
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条项目模块明细信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除项目模块明细成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除项目模块明细失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	    
		$("#add").click(function(){
			var opts = {
				title: "添加项目模块明细信息",
				url : "addPage.jhtml",
				data:{"projectModuleInfoId":projectModuleInfoId},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改项目模块明细信息",
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
		var projectModuleInfoId = "${projectModuleInfoId}";
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0,projectModuleInfoId:projectModuleInfoId},
				url:"query.jhtml",
				columns :[
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"key",caption:"数据关键字",hidden:false,type:"text" },
                    {name:"head",caption:"对应表头",hidden:false,type:"text" ,sort:true},
                    {name:"headgroup",caption:"所属分组",hidden:false,type:"text" ,sort:true},
                    {name:"moduleOrder",caption:"排序字段",hidden:false,type:"text" ,sort:true},
                    {name:"projectModuleInfo.name",caption:"所属菜单模块",hidden:false,type:"text"},
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
			<i class="iconfont icon-wuranyuan"></i> 项目模块信息明细
		</h1>
		<div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="name" style="width:99px">数据关键字</label> 
                    <input type="search" name="key" id="key" class="information_input" />
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="templateType">对应表头</label>
                    <input type="search" name="head" id="head" class="information_input" />
                </div>
                <div class="group unCol unmust btnF">
                    <a class="btn" id="query">查询</a>
                </div>
            </form>
		</div>
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
			<!-- <a class="btn" id="back" href="../projectmoduleinfo/list.jhtml">返回</a> -->
			<a class="btn" id="back" href="javascript:history.back(-1)">返回</a>
		</div>
	</div>
</body>
</html>