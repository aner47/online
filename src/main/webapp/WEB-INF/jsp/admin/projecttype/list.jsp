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
<style type="text/css">
.form-row {
	border: 1px solid #ccc;
	box-shadow: 1px 1px 5px #ccc;
	min-height: 140px;
	background: :#F7FAFF;
}

.form-row>div {
	float: left;
	padding: 10px;
}
</style>
<script type="text/javascript">
require(["grid","select","panel","less","upload"],function(g,s,p,less,upload){
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条项目类型信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除项目类型成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除项目类型失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
		$('#distribution').click(function(e){
			var ids = grid1.getSelectedValue('id');
			console.log(ids);
			if(ids.length==0){layer.msg("请选择任务");return};
			var opts = {
					title: "选择调查人",
					url : "distributionPage.jhtml",
					data:{"ids":ids.join(',')},
					yes : function(){
						$("#inputForm").submit();
					},
					cancel:function(){}
				}
			qy.panel(opts);
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加项目类型信息",
				url : "addPage.jhtml",
				data:{projectId:'${projectId}'},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改项目类型信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0]},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$('#queryForm').submit(function(){
			return false;
		})
			//查询
		$('#query').click(function(){
			var data = {};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data[v.name] = v.value;	
				
			})
			grid1.loadData(data);
		});
			
		var projectSelect = $("#project").select({name:"project"})	
		var project1Select = $("#project1").select({name:"projectId"})	
		$("#enterpriseType","#queryForm").select({name:"enterpriseType"})	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0,projectId:'${projectId}'},
				url:"query.jhtml",
				columns :[
					{name:"projectType",caption:"子项目名称",hidden:false,type:"text"},
				    {name:"enterpriseType",caption:"填表类型",hidden:false,type:"text",valueSet:"企业【企业类型】"},
				    {name:"project",caption:"所属项目",hidden:false,type:"text",valueSet:"全局【项目】"},
				    {name:"menusName",caption:"显示菜单",hidden:false,type:"text"},
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
			upload.upload({element:"#fileHandle",acceptCallBack:function(handle,data){
				$("#fileName").html(handle.file.name);
				$("#filePath").val(data.realPath);
				console.log(data.url);
				}
			});
			$("#importTask").click(function(){
				var filePath = $("#filePath").val();
				var projectId = projectSelect.getSelectValue();
				if(!projectId){layer.msg("请选择项目");return};
				if(!filePath){layer.msg("请选择文件");return};
				qy.ajax({
					url:"export.jhtml",
					data:{filePath:filePath,projectId:projectId},
					callBack:function(data){
						if(data.code == '20000'){
							layer.msg('导入成功！');
							grid1.refresh();
						}
						
					}
				})
				
			})
		
	})
</script>
</head>
<body>
	<div class="block    ">
		<h1 class="title">
			</i> 项目类型信息
		</h1>
		 <div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="project1">项目</label> 
                   <div id="project1" class="selectF" data-code="全局【项目】" ></div>
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="status">填表类型</label>
                    <div id="enterpriseType" class="selectF" data-code="企业【企业类型】" ></div>
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
	<div style="clear: both"></div>
	
</body>
</html>