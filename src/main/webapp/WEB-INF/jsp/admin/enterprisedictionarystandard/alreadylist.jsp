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
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条名录库信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除名录库成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除名录库失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加名录库信息",
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
				title: "修改名录库信息",
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
		$("#status","#queryForm").select({name:"status"})	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
				    {name:"enterpriseName",caption:"企业名称",hidden:false,type:"text"},
				    {name:"countyName",caption:"县（地区，街道）",hidden:false,type:"text"},
				    {name:"detailAddress",caption:"详细地址",hidden:false,type:"text"},
				    {name:"projectId",caption:"所属项目",hidden:false,type:"text",valueSet:"全局【项目】"},
				    {name:"industryCategoryNameMain",caption:"行业大类",hidden:false,type:"text"},
				    {name:"industryCategoryNameMiddle",caption:"行业中类",hidden:false,type:"text"},
				    {name:"status",caption:"状态",hidden:false,type:"text"},
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
			</i> 名录库信息
		</h1>
		 <div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="project1">项目</label> 
                   <div id="project1" class="selectF" data-code="全局【项目】" ></div>
                </div>
                
                <div class="group unCol unmust btnF">
                    <a class="btn" id="query">查询</a>
                </div>
            </form>
    </div>
	</div>
	<div class="grid-tools">
		<!-- <button id="add" class="btn-general">添加</button>
		<button id="update" class="btn-warning" data-grid="tab1"
			data-enable="1">修改</button>
		<button id="delete" class="btn-denger" data-grid="tab1"
			data-enable="2">删除</button> -->
	</div>
	<div id="tab1"></div>
	<div style="clear: both"></div>
	<!-- <div class="form-row">
		<div style="width: 40%">
			<label>选择项目</label>
			<div id="project" data-code="全局【项目】" style="width: 70%"></div>
		</div>
		<div style="width: 40%">
			<div>
				<span id="fileName" style="float: left">选择文件</span> <input
					id="filePath" name="filePath" type="hidden">
				<button id="fileHandle" style="margin-left: 10px">选择文件</button>
			</div>
		</div>
		<div class="bottonCatain">
			<button id="importTask">导入</button>
		</div>
	</div> -->
</body>
</html>