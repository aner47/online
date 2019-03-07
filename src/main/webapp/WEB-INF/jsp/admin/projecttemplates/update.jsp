<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改项目模板</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		 $("#inputForm #templateType").select({filter:true,name:"templateType",defaultValue:'${projectTemplates.templateType}',zIndex:2,change:function(event, value){
        }});
		 $("#inputForm #projectId").select({filter:true,name:"projectId",zIndex:1,defaultValue:'${projectTemplates.projectId}',change:function(event, value){
         }});
		 $("#inputForm #templatesType").select({filter:true,name:"templatesType",zIndex:3,defaultValue:'${projectTemplates.templatesType}',change:function(event, value){
         }});
		//提交表单
		$("#inputForm").validate({
			rules: {
                templateType:{
                    required:true
                },
                templatesType:{
					required:true
				},
		        projectId:{
		            required:true
		        }
            },
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('修改成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
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
	 <input type="hidden" id="id" name="id" value="${projectTemplates.id}"/>
  <div class="container">
            <div class="form-line">
                <label for="name">模块名称 ：</label> <input type="text" id="name"
                    name="name" placeholder="模块名称"  value="${projectTemplates.name}"/>
            </div>
            <div class="form-line">
                <label for="formType">配置类型 ：</label> 
               <div data-code="导出【模板类型】" id="templatesType" class=" selectF"></div>
            </div>
            <div class="form-line">
                <label for="formType">填表类型 ：</label> 
               <div data-code="企业【企业类型】" id="templateType" class=" selectF"></div>
            </div>
              <div class="form-line">
                <label for=""projectId"">项目 ：</label> 
               <div data-code="全局【项目】" id="projectId" class=" selectF"></div>

            </div>
        </div>

    </div>

</form>
</body>
</html>