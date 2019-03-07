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
			<div class="form-line">
				<label for="name">字段名称 ：</label> 
				<input type="text" id="name" name="name" placeholder="字段名称" value="${fieldConfig.name }"/>
			</div>
			<div class="form-line">
				<label for="name">字段对应值 ：</label> 
				<input type="text" id="value" name="value" placeholder="字段对应值" value="${fieldConfig.value }"/>
			</div>
			<div class="form-line">
				<label for="name">父级id ：</label> 
				<input type="text" id="parent" name="parent" value="${fieldConfig.parent }" />
			</div>
			
		</div>
	 	<input type="hidden" id="id" name="id" value="${fieldConfig.id}"/>
	 	<input type="hidden" id="moduleId" name="moduleId" value="${moduleId}"/>
 



</form>
</body>
</html>