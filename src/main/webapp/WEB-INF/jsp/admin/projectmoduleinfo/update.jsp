<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改项目模块信息</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
	
	var moduleClassification = $('#inputForm #moduleClassification').select({name:'moduleClassification',zIndex:13,defaultValue:'${projectModuleInfo.moduleClassification}',change:function(event,value){
	}});
	
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
				moduleClassification:{
					required:true
				}
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'update.jhtml',
			        data:{moduleId:'${moduleId}'},
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
	 <input type="hidden" id="id" name="id" value="${projectModuleInfo.id}"/>
	 <div class="form-line">
		<label for="moduleClassification">模块类型：</label>
		<div data-code="字段模块【类型】" id="moduleClassification" class=" selectF"></div>
	</div>
	 <div class="form-line">
	    <label for="name" >菜单名称 ：</label>
	    <input type="text" id="name" name="name" placeholder="模块名称" value="${projectModuleInfo.name}"/>
	</div>
    <div class="form-line">
	    <label for="order" >菜单排序 ：</label>
	    <input type="text" id="order" name="order" placeholder="模块排序" value="${projectModuleInfo.order}" required/>
	</div>

    <div class="form-line">
	    <label for="status" >状态 ：</label>
	    <input type="text" id="status" name="status" placeholder="状态" value="${projectModuleInfo.status}" readonly="readonly"/>
	</div>

    </div>

</form>
</body>
</html>