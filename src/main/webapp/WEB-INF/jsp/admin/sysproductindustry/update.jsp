<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改产品行业</title>
</head>

<script type="text/javascript">
	require([ "validate", "ajaxform" ], function(a) {
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
				<label for="name">产品行业名称 </label> 
				<input type="text" id="industryName"
					value="${sysProductIndustry.industryName}"
					name="industryName" placeholder="产品行业名称" required />

			</div>
			<div class="form-line">
				<label for="name">产品行业对应值 </label> 
				<input type="text" id="industryValue"
					value="${sysProductIndustry.industryValue}"
					name="industryValue" placeholder="产品行业对应值" required />

			</div>
			<div class="form-line">
				<label for="sort">产品行业排序 </label> 
				<input type="text" id="sort"
					value="${sysProductIndustry.sort}"
					name="sort" placeholder="产品行业排序" required />

			</div>
			<input type="hidden" id="id" name="id" value="${sysProductIndustry.id}" />


		</div>

	</form>
</body>
</html>