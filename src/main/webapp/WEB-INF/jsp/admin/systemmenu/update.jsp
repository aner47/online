<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改菜单</title>
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
    <label for="name" >菜单名称 </label>
    <input type="text" id="name" name="name" placeholder="菜单名称" value="${systemMenu.name}"/>
</div>
	<div class="form-line">
    <label for="url" >链接地址 </label>
    <input type="text" id="url" name="url" placeholder="链接地址" value="${systemMenu.url}"/>
</div>
	<div class="form-line">
    <label for="icon" >图标 </label>
    <input type="text" id="icon" name="icon" placeholder="图标" value="${systemMenu.icon}"/>
</div>
	<div class="form-line">
    <label for="pid" >上级菜单 </label>
    <input type="text" id="pid" name="pid" placeholder="上级菜单" value="${systemMenu.pid}"/>
</div>
	<div class="form-line">
    <label for="sort" >菜单排序 </label>
    <input type="text" id="sort" name="sort" placeholder="菜单排序" value="${systemMenu.sort}"/>
</div>
<div class="form-line">
    <label for="perms" >授权信息 </label>
    <input type="text" id="perms" name="perms" placeholder="授权信息" value="${systemMenu.perms}"/>
</div>
	 <input type="hidden" id="id" name="id" value="${systemMenu.id}"/>
 

    </div>

</form>
</body>
</html>