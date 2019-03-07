<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加pdf模块信息</title>
</head>

<script type="text/javascript">
require(["validate","select","ajaxform"],function(a){
		
	
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		var project1Select = $("#projectId","#inputForm").select({name:"projectId",zIndex:10,change:function(event, value){
			projectTemplateName.loadData("projectId:"+value+",templatesType:审核");
		}}); 
		var enterpriseType = $("#inputForm #enterpriseType").select({filter:true,name:"enterpriseType",zIndex:9,change:function(event, value){
			var projectId1 = project1Select.getSelectValue();
			projectTypeId.loadData("project:"+projectId1+",enterpriseType:"+value);
		}});
		
		var projectTypeId = $("#inputForm #projectTypeId").select({filter:true,name:"projectTypeId",zIndex:8});
		
		
		$("#inputForm #projectModelName").select({filter:true,name:"projectModelName",param:"type:base",zIndex:7,change:function(event, value){}});
		var projectTemplateName = $("#inputForm #projectTemplateName").select({filter:true,param:"templatesType:审核",
			name:"projectTemplateName",zIndex:6,change:function(event, value){}});
		
		
		
		//提交表单
		$("#inputForm").validate({
			rules: {
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		layer.msg('添加成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content, {icon: 3})
							
			        	}
						
			        },
			        error:function(){
			        	layer.msg('添加失败',{icon:3});
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
			
			<input type="hidden" name="pdftemplateName" value="审核"/>
			<div class="form-line">
				<label for="name">项目：</label> 
				<div id="projectId" data-code="全局【项目】" class="selF"></div>
			</div>
			<div class="form-line">
				<label for="name">填表类型：</label> 
				<div id="enterpriseType" data-code="企业【企业类型】" class="selF"></div>
			</div>
			<div class="form-line">
				<label for="projectTypeId">项目类型：</label>
				<div data-code="企业【项目类型】" id="projectTypeId" class=" selectF"></div>
			</div>
			<div class="form-line">
				<label for="moduleType">企业其他表格模板：</label> 
				<div id="projectTemplateName" data-code="模板管理【模板名称】" class="selF"></div>

			</div>
		</div>

	</form>
</body>
</html>