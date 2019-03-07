<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改模块明细</title>
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
	<div class="form-line">
    <label for="key" >数据关键字 ：</label>
    <input type="text" id="key" name="key" placeholder="数据关键字" value="${moduleDetail.key}"/>
</div>
	<div class="form-line">
    <label for="head" >对应表头 ：</label>
    <input type="text" id="head" name="head" placeholder="对应表头" value="${moduleDetail.head}"/>
</div>
	<div class="form-line">
    <label for="moduleOrder" >排序字段 ：</label>
    <input type="text" id="moduleOrder" name="moduleOrder" placeholder="排序字段" value="${moduleDetail.moduleOrder}"/>
</div>
	 <input type="hidden" id="id" name="id" value="${moduleDetail.id}"/>
 

    </div>

</form>
</body>
</html>