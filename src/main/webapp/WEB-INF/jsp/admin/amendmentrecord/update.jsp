<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改区域</title>
</head>

<script type="text/javascript">
require([ "validate","select", "ajaxform" ], function(a) {
	
	$('#commit').click(function() {
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules : {
				modifier:{
					required:true
				},
				description:{
					required:true
				},
			},
			messages : {},
			submitHandler : function(form) {
				var options = {
					url : 'update.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('修改成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.refresh();
					},
					error : function() {
						layer.msg('修改失败', {
							icon : 3
						});
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
			<input type="hidden" name="id" value="${amendmentrecord.id }"/>
			<div class="form-line">
				<label for="name">修改人 ：</label> 
				<input type="text" id="modifier" name="modifier" value="${amendmentrecord.modifier}" />

			</div>
			<div class="form-line">
				<label for="name">修改内容 ：</label>
				<textarea type="text" id="description" name="description">${amendmentrecord.modifier}</textarea>

			</div>
		</div>
	</form>
</body>
</html>