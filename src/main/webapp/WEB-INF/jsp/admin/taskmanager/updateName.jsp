<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改企业名称</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
	var project1Select = $("#project1","#inputForm").select({name:"projectId",zIndex:1,defaultValue:"${taskManager.projectId}"}); 
    $("#status","#inputForm").select({immutable: true,name:"status",zIndex:2,defaultValue:"${taskManager.status}"});   
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'updateName.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		layer.msg('修改成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content, {icon: 6})
							layer.closeAll('page');
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
			
			<div class="form-line">
				<label for="enterpriseName">企业名称 ：</label> <input type="text"
					id="enterpriseName" name="enterpriseName" placeholder="企业名称"
					value="${taskManager.enterpriseName}" required/>
			</div>
			
			<input type="hidden" id="id" name="id" value="${taskManager.id}" />


		</div>

	</form>
</body>
</html>