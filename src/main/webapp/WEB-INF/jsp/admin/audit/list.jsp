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
	$("#queryForm #projectId").select({filter:true,name:"projectId",zIndex:10,change:function(event, value){
		projectTypeId.loadData("project:"+value);
	}});
	$("#queryForm #enterpriseType").select({filter:true,name:"enterpriseType",zIndex:9,change:function(event, value){}});
    var projectTypeId = $("#queryForm #projectTypeId").select({filter:true,initLoad:false,name:"projectTypeId",zIndex:8,change:function(event, value){}});
    $("#queryForm #projectTemplateName").select({filter:true,name:"projectTemplateName",zIndex:7,param:"templatesType:审核",change:function(event, value){}});
		
    $('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条pdf模块信息信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除模块信息成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除模块信息失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加审核模块信息",
				url : "addauditPage.jhtml",
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
				title: "修改审核模块信息",
				url : "updateauditPage.jhtml",
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
				data:{pageSize:10,pageNumber:0,pdftemplateName:'审核'},
				url:"query.jhtml",
				columns :[
						{name:"projectId",caption:"项目",hidden:false,type:"text",valueSet : "全局【项目】"},
						{name:"enterpriseType",caption:"填表类型",hidden:false,type:"text",valueSet : "企业【企业类型】"},
						{name:"projectTypeId",caption:"项目类型",hidden:false,type:"text",valueSet : "企业【项目类型】"},
					    {name:"projectTemplateName",caption:"表格配置",hidden:false,type:"text",valueSet : "模板管理【模板名称】"},
					    
				]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
</head>
<body>
	<div class="block    ">
		<h1 class="title">
			</i> PDF模板信息
		</h1>
        <div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="projectModelName">项目名称</label> 
                   <div id="projectId" class="selectF" data-code="全局【项目】" ></div>
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="enterpriseType">填表类型</label>
                    <div id="enterpriseType" class="selectF" data-code="企业【企业类型】" ></div>
                </div>
                
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="projectModelName">项目类型</label> 
                   <div id="projectTypeId" class="selectF" data-code="企业【项目类型】" ></div>
                </div>
                
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="projectTemplateName">表格名称</label>
                    <div id="projectTemplateName" class="selectF" data-code="模板管理【模板名称】" ></div>
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
</body>
</html>