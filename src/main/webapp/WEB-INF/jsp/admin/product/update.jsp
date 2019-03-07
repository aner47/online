<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改产品</title>
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
	<div class="form-line">
    <label for="unit" >单位 ：</label>
    <input type="text" id="unit" name="unit" placeholder="单位" value="${product.unit}"/>
</div>
	<div class="form-line">
    <label for="name" >名称 ：</label>
    <input type="text" id="name" name="name" placeholder="名称" value="${product.name}"/>
</div>
	<div class="form-line">
    <label for="yield" >年产量 ：</label>
    <input type="text" id="yield" name="yield" placeholder="年产量" value="${product.yield}"/>
</div>
	 <input type="hidden" id="id" name="id" value="${product.id}"/>
 

    </div>

</form>
</body>
</html>