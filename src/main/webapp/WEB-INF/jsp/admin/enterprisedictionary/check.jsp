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
		var project1Select = $("#projectId","#inputForm").select({name:"projectId"
			,defaultValue:"${enterpriseDictionary.projectId}",zIndex:1}); 
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules : {
				enterpriseName:{
					required:true
				},
				projectId:{
					required:true
				}
				
			},
			messages : {},
			submitHandler : function(form) {
				var options = {
					url : 'check.jhtml',
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
	        
			<input type="hidden" id="status" name="status"  />
			<input type="hidden" id="id" name="id" value="${enterpriseDictionary.id}" />
			<input type="hidden" id="alreadyEnterpriseId" name="alreadyEnterpriseId" value="${enterpriseDictionary.alreadyEnterpriseId}" />
			<input type="hidden" id="projectId" name="projectId" value="${enterpriseDictionary.projectId}" />
			<input type="hidden" id="corporation" name="corporation" value="${enterpriseDictionary.corporation}" />
			<input type="hidden" id="contacts" name="contacts" value="${enterpriseDictionary.contacts}" />
			<input type="hidden" id="contactsPhone" name="contactsPhone" value="${enterpriseDictionary.contactsPhone}" />
			<input type="hidden" id="code" name="code" value="${enterpriseDictionary.code}" />
			<input type="hidden" id="inputIndustry" name="inputIndustry" value="${enterpriseDictionary.detailAddress}" />
			<input type="hidden" id="detailAddress" name="detailAddress" value="${enterpriseDictionary.detailAddress}" />
			<input type="hidden" id="countyName" name="countyName" value="${enterpriseDictionary.countyName}" />		
			<input type="hidden" id="enterpriseName" name="enterpriseName" value="${enterpriseDictionary.enterpriseName}" />
								
		</div>

	</form>
</body>
</html>