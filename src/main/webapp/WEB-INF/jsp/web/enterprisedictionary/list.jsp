<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
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
	background: #F7FAFF;
}

.form-row>div {
	float: left;
	padding: 10px;
}
</style>
<script type="text/javascript">
require(["grid","select","panel","less","upload"],function(g,s,p,less,upload){
	
	var projectSelect = $("#project").select({name:"project"})	
	var projectIdSelect = $("#projectId").select({name:"projectId"})	
	
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
		$("#insert").click(function(){
			var ids = grid1.getSelectedValue('id');
			if(ids.length ==0){layer.msg("请选择企业");return};
			qy.ajax({
				url:"insert.jhtml",
				data:{"ids":ids.join(',')},
				callBack:function(data){
					if(data.code == '20000'){
						layer.msg('生成任务成功！');
						grid1.refresh();
					}else{
						layer.msg(data.content, {icon: 5});
					}
					
				}
			})
			
			
		});
/* 		$("#insert").click(function(){
			var ids = grid1.getSelectedValue('id');
			var opts = {
				title: "加入标准名录库",
				url : "insertPage.jhtml",
				
				data :{"ids":ids.join(',')},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		}); */
		$("#filter").click(function(){
			
			qy.ajax({
				url:"filtrate.jhtml",
				data:{},
				callBack:function(data){
					if(data.code == '20000'){
						layer.msg('导入成功！');
						grid1.refresh();
					}
					
				}
			})
			
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
			
		$("#status","#queryForm").select({name:"status"})	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"enterpriseName",caption:"企业名称",hidden:false,type:"text"},
				    {name:"countyName",caption:"区县",hidden:false,type:"text"},
				    {name:"detailAddress",caption:"详细地址",hidden:false,type:"text"},
				    {name:"inputIndustry",caption:"行业",hidden:false,type:"text"},
				    {name:"code",caption:"组织机构代码",hidden:false,type:"text"},
				    {name:"contacts",caption:"联系人",hidden:false,type:"text"},
				    {name:"contactsPhone",caption:"联系电话",hidden:false,type:"text"},
				    {name:"corporation",caption:"法人",hidden:false,type:"text"},
				    {name:"projectId",caption:"所属项目",hidden:false,type:"text",valueSet:"全局【项目】"},
				    {name:"status",caption:"状态",hidden:false,type:"text",valueSet:"名录库【状态】",
				    	format:function(value,rowValue,handle){
							if(value !='checknot'){
								var parent = handle.parent("tr");
								parent.find("#view").html("");
							}
							return value;
				    	}
				    },
				    {name:"opinion",caption:"审核意见",hidden:false,type:"text"},
				    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;">修改</a>',	
						events :[{
							type:'click',
							select:'#view',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'id');
								var opts = {
										title: "修改名录库信息",
										url : "updatePage.jhtml",
										data:{id:id},
										yes : function(){
											$("#inputForm").submit();
										},
										cancel:function(){}
									}
									qy.panel(opts);
		
							}
						}] 
					},
								    	
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
			$("#back").on("click", function() {
				window.location.href = "../enterprise/list.jhtml";
				top.loadMenu('2','31');
			})
		
	})
</script>
</head>
<body>
	<div class="block    ">
		<h1 class="title">
			</i> 名录库信息
			<c:if test="${principal.userType != 'system'}">
				<div id="back" class="titleBtn">返回</div>
			</c:if>
			<div class="titleBtn"  id="add">添加</div>
		</h1>
            <form id="queryForm" action="" class="form" onsubmit="return false">
            
            <div class="formLine">
				<div class="formPara">
					<label class="label">企业名称</label> 
					<input type="text" class="input" id="enterpriseName" name="enterpriseName"/>
				</div>

				<button id="query" class="button">搜索</button>
			</div>
            </form>
    
	<div id="tab1" class="tab" style="border-top: 1px solid #e7ebee;"></div>
	</div>
	
</body>
</html>