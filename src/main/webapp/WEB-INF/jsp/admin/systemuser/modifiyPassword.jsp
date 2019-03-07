<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改用户信息</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","less"],function(a){
		$('#commit').click(function(){
			$("#modifyinput").submit();
		})
		//提交表单
		$("#modifyinput").validate({
			rules: {
				newPassword2:{
					equalTo:"#newPassword1"
				}
			},
			messages:{
			},
			submitHandler:function(form){
				var url ="../admin/systemuser/updatePassword.jhtml";
				var options  = {
			        url:url,
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
<style>
	.layui-layer-page .layui-layer-content .form-line{
		float: left;
		width: 100%;
	}
	.layui-layer-page .layui-layer-content .form-line label {
		padding-bottom: 12px;
	}
	.layui-layer-page .layui-layer-content form {
		padding: 1px 17px 0 20px;
	}
</style>
<body>

	<form id="modifyinput">
		<div class="container">
			<!-- <div class="form-line">
				<label for="username" >用户名:  &nbsp;  ${User.username} </label>
			</div> -->
			<div class="form-line">
				<label for="Password" >原密码 </label>
				<input type="password" id="Password"  autocomplete="off" name="Password" placeholder="" value="${systemUser.password}" required/>
			</div>
			<div class="form-line">
				<label for="newPassword" >新密码 </label>
				<input type="password" id="newPassword1"  autocomplete="off" name="newPassword" placeholder="" value="${systemUser.password}" required/>
			</div>
			<div class="form-line">
				<label for="affirmnewPassword" >重复新密码 </label>
				<input type="password" id="newPassword2"  autocomplete="off" name="newPassword2" placeholder="" value="${systemUser.password}" required/>
			</div>
			<input type="hidden" id="id" name="id" value="${User.id}"/>
		</div>
	</form>
</body>
</html>