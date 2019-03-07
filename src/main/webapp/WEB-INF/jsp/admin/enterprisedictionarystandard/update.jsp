<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改名录库</title>
</head>

<script type="text/javascript">
	require([ "validate", "ajaxform" ], function(a) {
		var project1Select = $("#project1", "#inputForm").select({
			name : "projectId",
			zIndex : 1,
			defaultValue : "${taskManager.projectId}"
		});
		$("#status", "#inputForm").select({
			name : "status",
			zIndex : 2,
			defaultValue : "${taskManager.status}"
		});
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules : {},
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
						grid1.loadData();
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
			<div class="form-line">
				<label for="enterpriseName">企业名称 ：</label> <input type="text"
					id="enterpriseName" name="enterpriseName" placeholder="企业名称"
					value="${taskManager.enterpriseName}" />
			</div>
			
			<div class="form-line">
				<label for="countyName">县（地区，街道） ：</label> <input type="text"
					id="countyName" name="countyName" placeholder="县（地区，街道）"
					value="${taskManager.countyName}" />
			</div>
			
			<div class="form-line">
				<label for="detailAddress">详细地址 ：</label> <input type="text"
					id="detailAddress" name="detailAddress" placeholder="详细地址"
					value="${taskManager.detailAddress}" />
			</div>

			<input type="hidden" id="id" name="id" value="${taskManager.id}" />


		</div>

	</form>
</body>
</html>