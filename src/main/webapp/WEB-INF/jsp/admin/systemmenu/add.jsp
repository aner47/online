<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加菜单</title>
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
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.refresh();
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
	            <label for="name" >菜单名称 </label>
	            	             <input type="text" id="name" name="name" placeholder="菜单名称" required/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="url" >链接地址 </label>
	            	             <input type="text" id="url" name="url" placeholder="链接地址" required/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="icon" >图标 </label>
	            	             <input type="text" id="icon" name="icon" placeholder="图标" required/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="pid" >上级菜单 </label>
	            	             <input type="text" id="pid" name="pid" placeholder="上级菜单" required/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="sort" >菜单排序 </label>
	            	             <input type="text" id="sort" name="sort" placeholder="菜单排序" required/>
	            	           
	        </div>
												

    </div>

</form>
</body>
</html>