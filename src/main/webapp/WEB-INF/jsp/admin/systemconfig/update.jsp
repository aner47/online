<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改系统配置</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
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
	 <input type="hidden" id="syskey" name="syskey" value="${systemConfig.syskey}"/>
 	<div class="form-line">
    <label for="sysValue" >值 </label>
    <input type="text" id="sysValue" name="sysValue" placeholder="值" value="${systemConfig.sysValue}"/>
</div>
	<div class="form-line">
    <label for="description" >描述 </label>
    <input type="text" id="description" name="description" placeholder="描述" value="${systemConfig.description}"/>
</div>
	 <input type="hidden" id="id" name="id" value="${systemConfig.id}"/>
 

    </div>

</form>
</body>
</html>