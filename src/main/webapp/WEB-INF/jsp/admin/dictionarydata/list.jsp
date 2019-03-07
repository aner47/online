<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/from.less" />
	<link rel="stylesheet"  href="<%=request.getContextPath()%>/resources/css/form/form.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>字典管理</title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
		$("#add").click(function(){
			var opts = {
				title:"添加",
				url : "addPage.jhtml",
				data:{dictionaryId:dictionaryId},
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
		
		var dictionaryId = ${dictionaryId};
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0,dictionaryId:dictionaryId},
				url:"query.jhtml",
				columns :[{name:"id",caption:"主键",hidden:true},
					  {name:"code",caption:"编码"},
					  {name:"codeValue",caption:"值"},
					  {name:"groupbyName",caption:"所属分组"},
					  {name:"secondGroupbyName",caption:"二级分组"},
					  {name:"order",caption:"排序"}
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
			$('#delete').click(function(e){//删除
				qy.del({
					title:'是否删除所选'+grid1.getSelectedValue('id').length+'条菜单信息?',
					yes:function(index){
						layer.close(index);
						var ids = grid1.getSelectedValue('id');
						var opts ={
							url:'delete.jhtml',
							data :{"ids":ids.join(',')},
							callBack:function(data,errcode){
								if(errcode!=="000000"){
									qy.suc({title:'删除成功！'});
									grid1.loadData();
								}else{
									qy.fail({title:'删除失败！'});
								}
							}
						}
						qy.ajax(opts);
					}
				});
			});
	})
</script>
</head>
<body>
	<div class="block onlyTitle">
		<h1 class="title">
			<i class="iconfont icon-caidanguanli"></i>
			字典明细
		</h1> 
	</div>
	<div class = "grid-tools">
		<button id = "add" >添加</button>
		<button id = "update"  data-grid = "tab1" data-enable = "1" >修改</button>
		<button id = "delete" data-grid = "tab1" data-enable = "2" >删除</button>
	</div>
	<div id="tab1" class="tab" style ="width:99%;"></div> 
</body>
</html>