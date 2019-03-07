<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改干洗企业工商登记信息</title>
</head>

<script type="text/javascript">
	require(
			[ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

				$("#stockgroundDustMeasures", "#boiler_form")
						.select(
								{
									name : "stockgroundDustMeasures",
									defaultValue : "${betonStirStation.stockgroundDustMeasures}",
									zIndex : 9
								});
				$("#feedinletDustMeasures", "#boiler_form").select({
					name : "feedinletDustMeasures",
					defaultValue : "${betonStirStation.feedinletDustMeasures}",
					zIndex : 8
				});

				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						contactNumber:{
							isTelephone:true
						}
					},
					submitHandler : function(form) {

						var options = {
							url : '../incodryclean/update.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if(data.code == 20000){
									qy.suc2({
										title : '修改成功！'
									});
									grid1.loadData();
									layer.closeAll('page');
								}else{
									qy.fail({
										title : '修改失败'+data.content
									});
								}
							},
							error : function() {
								qy.fail({
									title : '修改失败'
								});
							}
						};
						$(form).ajaxSubmit(options);

						return false;
					}
				});
				hide.hide();
			});
</script>

<body>

	<form id="boiler_form">
		<div class="container">

			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">干洗企业名称</label>
					<div class="infpCon">
						<input type="" class="input" id="enterprise.name"
							name="enterprise.name" value="${inCoDryClean.enterprise.name}" required>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">法定代表人/联系人</label>
					<div class="infpCon">
						<input type="" class="input" id="contacts" name="contacts"
							value="${inCoDryClean.contacts}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">联系电话</label>
					<div class="infpCon">
						<input type="" class="input" id="contactNumber"
							name="contactNumber" value="${inCoDryClean.contactNumber}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" id="city" name="city"
							value="${inCoDryClean.city}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" id="houseNumber" name="houseNumber"
							value="${inCoDryClean.houseNumber}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">干洗剂用量/吨</label>
					<div class="infpCon">
						<input type="" class="input" id="detergent" name="detergent"
							value="${inCoDryClean.detergent}"
							number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${inCoDryClean.id}" />
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${inCoDryClean.description}</textarea>
					</div>
				</div>
			</div>



		</div>

	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>