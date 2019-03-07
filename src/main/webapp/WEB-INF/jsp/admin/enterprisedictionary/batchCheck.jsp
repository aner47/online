<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>审核名录库</title>
</head>

<script type="text/javascript">
	require([ "validate", "ajaxform" ], function(a) {
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules : {
				
			},
			messages : {},
			submitHandler : function(form) {
				var options = {
					url : 'batchCheck.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('审核成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('审核失败', {
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
	            <label for="projectId" >审核意见：</label>
	            <textarea rows="1" cols="1" id="opinion" name="opinion"></textarea>
	        </div>
	        
			<input type="hidden" id="ids" name="ids" value="${ids}" />
			<input type="hidden" id="status" name="status"  />					
		</div>

	</form>
</body>
</html>