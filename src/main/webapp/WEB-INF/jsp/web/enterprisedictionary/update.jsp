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
			<!-- <div class="form-line">
	            <label for="projectId" >项目 ：</label>
	            <div id="projectId" class="selectF" data-code="全局【项目】" ></div>
	        </div> -->
	        
			<div class="form-line">
				<label for="enterpriseName">企业名称 ：</label> <input type="text"
					id="enterpriseName" name="enterpriseName" placeholder="企业名称"
					value="${enterpriseDictionary.enterpriseName}" />
			</div>
			
			<div class="form-line">
				<label for="countyName">区县 ：</label> <input type="text"
					id="countyName" name="countyName" placeholder="区县"
					value="${enterpriseDictionary.countyName}" />
			</div>
			
			<div class="form-line">
				<label for="detailAddress">详细地址 ：</label> <input type="text"
					id="detailAddress" name="detailAddress" placeholder="详细地址"
					value="${enterpriseDictionary.detailAddress}" />
			</div>
			<div class="form-line">
				<label for="inputIndustry">行业：</label> <input type="text"
					id="inputIndustry" name="inputIndustry" placeholder="inputIndustry"
					value="${enterpriseDictionary.detailAddress}" />
			</div>
			<div class="form-line">
	            <label for="code" >组织机构代码：</label>
	            <input type="text" id="code" name="code" placeholder="组织机构代码"
	            value="${enterpriseDictionary.code}" />
	        </div>
			<div class="form-line">
	            <label for="contacts" >联系人 ：</label>
	            <input type="text" id="contacts" name="contacts" placeholder="联系人"
	            value="${enterpriseDictionary.contacts}" />
	        </div>
			<div class="form-line">
	            <label for="corporation" >法人：</label>
	            <input type="text" id="corporation" name="corporation" placeholder="法人"
	            value="${enterpriseDictionary.corporation}" />
	        </div>

			<input type="hidden" id="id" name="id" value="${enterpriseDictionary.id}" />
			<input type="hidden" id="projectId" name="projectId" value="${enterpriseDictionary.projectId}" />
			<input type="hidden" id="opinion" name="opinion" value="${enterpriseDictionary.opinion}" />
		</div>

	</form>
</body>
</html>