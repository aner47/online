<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改模块信息</title>
</head>

<script type="text/javascript">
	require([ "validate", "select", "ajaxform" ], function(a) {
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		$("#inputForm #moduleClassification").select({
			filter : true,
			name : "moduleClassification",
			defaultValue : "${moduleInfo.moduleClassification}",
			zIndex : 3,
			change : function(event, value) {
			}
		});
		$("#inputForm #type").select({
			filter : true,
			name : "type",
			defaultValue : "${moduleInfo.type}",
			zIndex : 2,
			change : function(event, value) {
			}
		});
		//提交表单"${moduleInfo.moduleClassification}"
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
				<label for="name">模块名称 ：</label> <input type="text" id="name"
					name="name" placeholder="模块名称" value="${moduleInfo.name}" />
			</div>
			<div class="form-line">
				<label for="moduleType">模块类型 ：</label> <input type="text"
					id="moduleType" name="moduleType" placeholder="模块类型"
					value="${moduleInfo.moduleType}" />
			</div>
			<div class="form-line">
				<label for="order">模块排序 ：</label> <input type="text" id="order"
					name="order" placeholder="模块排序" value="${moduleInfo.order}"
					required />
			</div>
			<div class="form-line">
				<label for="moduleClassification">填表类型：</label>
				<div data-code="企业【企业类型】" id="moduleClassification" class=" selectF"></div>
			</div>
			<div class="form-line">
				<label for="moduleClassification">模块类型：</label>
				<div data-code="字段模块【类型】" id="type" class=" selectF"></div>
			</div>
			<div class="form-line">
				<label for="status">状态 ：</label> <input type="text" id="status"
					name="status" placeholder="状态" value="${moduleInfo.status}" />
			</div>
			<input type="hidden" id="id" name="id" value="${moduleInfo.id}" />


		</div>

	</form>
</body>
</html>