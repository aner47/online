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
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条原辅料名称配置信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除原辅料名称配置成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除原辅料名称配置失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加原辅料名称配置信息",
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
				title: "修改原辅料名称配置信息",
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
                    {name:"otherName",caption:"原辅料名称",hidden:false,type:"text"},
                    {name:"industryCode",caption:"行业代码",hidden:false,type:"text"},
                    {name:"rawMaterialsStandardName.standardName",caption:"标准名称",hidden:false,type:"text"},
                    {name:"rawMaterialsStandardName.standardUnit",caption:"标准单位",hidden:false,type:"text"},
                        
    ]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
<style>
.input {
    height: 30px;
    line-height: 28px;
    border: 1px solid #ccc;
    padding: 0 5px;
}
</style>
</head>
<body>
    <div class="block ">
        <h1 class="title">
            原辅料名称
        </h1> 
        <div class="form-line form-line2">
        <form id="queryForm" action="">
            <div class="group col-lg-3 col-md-4 unmust"> 
            <label class="form-label" for="otherName">辅料名称</label>
                <input type="search" name="otherName" id="otherName" class="input" >
            </div>
            <div class="group col-lg-5f1 col-md-4 unmust"> 
            <label class="form-label" for="industryCode">行业代码</label>
                <input type="search" name="industryCode" id="industryCode" class="input">
            </div>
            <div class="group unCol unmust btnF">
                <a class="btn" id="query">查询</a>
            </div>
        </form>
        </div>
    </div>
    <div class = "grid-tools">
        <button id = "add" >添加</button>
        <button id = "update"  data-grid = "tab1" data-enable = "1" >修改</button>
        <button id = "delete" data-grid = "tab1" data-enable = "2" >删除</button>
    </div>
    <div id="tab1"  class="tab"></div> 
</body>
</html>