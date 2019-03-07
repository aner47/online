<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加模块信息</title>
</head>

<script type="text/javascript">
require(["validate","select","ajaxform"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		$("#inputForm #tableType").select({filter:true,name:"tableType",zIndex:3,change:function(event, value){}});
		
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
			        data:{moduleId:'${moduleId}'},
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
				<label for="name">字段名称 ：</label> 
				<input type="text" id="name" name="name" placeholder="字段名称" />
			</div>
			<div class="form-line">
				<label for="name">字段对应值 ：</label> 
				<input type="text" id="value" name="value" placeholder="字段对应值" />
			</div>
			<div class="form-line">
				<label for="name">父级id ：</label> 
				<input type="text" id="parent" name="parent"  />
			</div>
			
		</div>

	</form>
</body>
</html>