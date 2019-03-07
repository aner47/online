<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加模块明细</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
			        data:{moduleId:'${moduleId}'},
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
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
						<div class="form-line">
	            <label for="key" >数据关键字 ：</label>
	            	             <input type="text" id="key" name="key" placeholder="数据关键字"/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="head" >对应表头 ：</label>
	            	             <input type="text" id="head" name="head" placeholder="对应表头"/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="moduleOrder" >排序字段 ：</label>
	            	             <input type="text" id="moduleOrder" name="moduleOrder" placeholder="排序字段"/>
	            	           
	        </div>
						

    </div>

</form>
</body>
</html>