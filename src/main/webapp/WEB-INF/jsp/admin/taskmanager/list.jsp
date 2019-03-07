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
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条任务管理信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除任务管理成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除任务管理失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
		
		function compareData (compareArr) {
				console.log(compareArr);
				
		      var aaa = compareArr;
		      var returnResult = false;
		      
		      for (var i = 0; i < aaa.length - 1; i++) {
		    	  console.log("aaaaaaa");
		    	  
		        if (aaa[i] != aaa[i+1]) {
		          console.log("两者不相等");
		          returnResult  = true;
		        }
		      }
		      return returnResult;
		 }

		
		$('#distribution').click(function(e){
			var ids = grid1.getSelectedValue('id');
			var projectIds = grid1.getSelectedValue('projectId');
			console.log(ids);
			console.log(projectIds);
			if(projectIds.length == 1){
		    	  if(!projectIds[0]){
						layer.msg("请给任务分配项目");
						return true;
					}
		    }else{
		    	if(compareData(projectIds)){
					layer.msg("请选择同一项目下任务");return
				}
		    }
			
			
			
			if(ids.length==0){layer.msg("请选择任务");return};
			var opts = {
					title: "选择调查人",
					url : "distributionPage.jhtml",
					data:{"ids":ids.join(','),projectId:projectIds[0]},
					yes : function(){
						$("#inputForm").submit();
					},
					cancel:function(){}
				}
			qy.panel(opts);
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加任务管理信息",
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
				title: "修改任务管理信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0]},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		$("#updateName").click(function(){
			var opts = {
				title: "修改企业名称",
				url : "updateNamePage.jhtml",
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
		
		$("#export").click(function(){
			var param ="";
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				param += v.name+"="+v.value+"&";
			}) 
			param = param.substring(0,param.lastIndexOf("&"));
			window.location.href=base+"/admin/taskmanager/exportTaskManager.jhtml?"+param;
		});
		
			
		var projectSelect = $("#project").select({name:"project"})	
		var project1Select = $("#project1").select({name:"projectId",filter: true,})	
		$("#status","#queryForm").select({name:"status"})	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
				    {name:"alreadyEnterpriseId",caption:"已调查企业id",hidden:false,type:"text",format:function(value){return value==-1?"":value}},
				    {name:"enterpriseName",caption:"企业名称",hidden:false,type:"text"},
				    {name:"inputIndustry",caption:"行业",hidden:false,type:"text"},
				    {name:"contactPerson",caption:"联系人",hidden:false,type:"text"},
				    {name:"contactPhone",caption:"联系电话",hidden:false,type:"text"},
				    {name:"countyName",caption:"区县",hidden:false,type:"text"},
				    {name:"executor",caption:"调查人",hidden:false,type:"text"},
				    {name:"detailAddress",caption:"详细地址",hidden:false,type:"text"},
				    {name:"code",caption:"组织机构代码",hidden:false,type:"text"},
				    {name:"corporation",caption:"法人",hidden:false,type:"text"},
				    {name:"status",caption:"任务状态",hidden:false,type:"text",valueSet:"【任务管理】状态"},
				    {name:"projectId",caption:"项目",hidden:false,type:"text",valueSet:"全局【项目】"},
				    {name:"projectTypeId",caption:"项目类型",hidden:false,type:"text",valueSet:"企业【项目类型】"},
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
			</i> 任务管理信息
		</h1>
		 <div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="project1">项目</label> 
                   <div id="project1" class="selectF" data-code="全局【项目】" ></div>
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="status">状态</label>
                    <div id="status" class="selectF" data-code="【任务管理】状态" ></div>
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="executor">企业名称</label>
                    <input type="text" name="enterpriseName" />
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="executor">区县</label>
                    <input type="text" name="countyName" />
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="executor">行业</label>
                    <input type="text" name="inputIndustry" />
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="executor">调查人</label>
                    <input type="text" name="executor" />
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
		<button id="distribution" class="btn-denger" data-grid="tab1"
			data-enable="3">分配任务</button>
		<button id="export" class="btn-denger" data-grid="tab1"
			data-enable="4">导出</button>
		<button id="updateName" class="btn-denger" data-grid="tab1"
			data-enable="2">修改企业名称</button>
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