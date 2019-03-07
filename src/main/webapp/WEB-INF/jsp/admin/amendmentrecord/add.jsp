<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加区域</title>
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
					url : 'save.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('添加成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('添加失败', {
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
			<div class="form-line">
				<label for="name">修改人 ：</label> 
				<input type="text" id="modifier" name="modifier"  />

			</div>
			<div class="form-line">
				<label for="name">修改内容 ：</label>
				<textarea type="text" id="description" name="description" />

			</div>
			
		</div>
	</form>
</body>
</html>