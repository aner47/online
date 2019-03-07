<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改模块信息</title>
</head>

<script type="text/javascript">
require(["validate","select","ajaxform"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		var project1Select = $("#projectId","#inputForm").select({name:"projectId",defaultValue:'${pdfTemplateFile.projectId}',zIndex:10,change:function(event, value){
			projectTemplateName.loadData("projectId:"+value+",templatesType:审核");
		}});
		
		$("#inputForm #enterpriseType").select({filter:true,name:"enterpriseType",defaultValue:'${pdfTemplateFile.enterpriseType}',zIndex:9,change:function(event, value){
			var projectId1 = project1Select.getSelectValue();
			projectTypeId.loadData("project:"+projectId1+",enterpriseType:"+value);
		}});
		
		var projectTypeId = $("#inputForm #projectTypeId").select({filter:true,name:"projectTypeId",defaultValue:"${pdfTemplateFile.projectTypeId}",zIndex:8});
		
		$("#inputForm #projectModelName").select({filter:true,name:"projectModelName",param:"type:base",defaultValue:'${pdfTemplateFile.projectModelName}',zIndex:7,change:function(event, value){}});
		var projectTemplateName = $("#inputForm #projectTemplateName").select({filter:true,param:"templatesType:审核",name:"projectTemplateName",defaultValue:'${pdfTemplateFile.projectTemplateName}',zIndex:6,change:function(event, value){}});
		if('${pdfTemplateFile.projectId}'){
			projectTemplateName.loadData("projectId:"+'${pdfTemplateFile.projectId}'+",templatesType:审核");
		}
		
		if('${pdfTemplateFile.projectId}' && '${pdfTemplateFile.enterpriseType}'){
			projectTypeId.loadData("project:"+'${pdfTemplateFile.projectId}'+",enterpriseType:"+'${pdfTemplateFile.enterpriseType}');
		}
		
		
		//提交表单"${moduleInfo.moduleClassification}"
		$("#inputForm").validate({
			rules: {
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		layer.msg('修改成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content, {icon: 3})
							
			        	}
						
			        },
			        error:function(){
			        	layer.msg('修改失败',{icon:3});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});		
	});
</script>

<body>

	<form id="inputForm">
		<div class="container">
			<input type="hidden" id="id" name="id" value="${pdfTemplateFile.id}"/>
			<div class="form-line">
				<label for="name">项目：</label> 
				<div id="projectId" data-code="全局【项目】" class="selF"></div>
			</div>
			<div class="form-line">
				<label for="name">填表类型 ：</label>
				<div id="enterpriseType" data-code="企业【企业类型】" class="selF"></div>
			</div>
			<div class="form-line">
				<label for="projectTypeId">项目类型：</label>
				<div data-code="企业【项目类型】" id="projectTypeId" class=" selectF"></div>
			</div>
			<!-- <div class="form-line">
				<label for="order">企业基本信息模板 ：</label>
				<div id="projectModelName" data-code="全局【项目模块】" class="selF"></div>

			</div> -->
			<div class="form-line">
				<label for="moduleType">企业其他表格模板：</label>
				<div id="projectTemplateName" data-code="模板管理【模板名称】" class="selF"></div>

			</div>
		</div>

	</form>
</body>
</html>