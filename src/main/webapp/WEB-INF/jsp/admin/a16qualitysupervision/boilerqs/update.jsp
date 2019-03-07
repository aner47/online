<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改锅炉信息</title>
</head>

<script type="text/javascript">
	require(
			[ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {
				
				$("#lastExamineDate", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        });
				$("#nextExamineDate", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        });

				$("#fuelType", "#boiler_form")
						.select(
								{
									name : "fuelType",
									isCustom:true,
									defaultValue : "${boilerQs.fuelType}",
									zIndex : 9
								});

				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../boilerqs/update.jhtml',
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
					<label for="" class="infpLable">所在地</label>
					<div class="infpCon">
						<input type="" class="input" id="palce" name="palce"
							value="${boilerQs.palce}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" id="city" name="city"
							value="${boilerQs.city}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">企业名称</label>
					<div class="infpCon">
						<input type="" class="input" id="enterprise.name" name="enterprise.name"
							value="${boilerQs.enterprise.name}" required>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" id="houseNumber" name="houseNumber"
							value="${boilerQs.houseNumber}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心经度</label>
					<div class="infpCon">
						<input type="" class="input" id="longitude" name="longitude"
							value="${boilerQs.longitude}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心纬度</label>
					<div class="infpCon">
						<input type="" class="input" id="latitude" name="latitude"
							value="${boilerQs.latitude}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉型号</label>
					<div class="infpCon">
						<input type="" class="input" id="boilerModel" name="boilerModel"
							value="${boilerQs.boilerModel}">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="质监局锅炉燃料【燃料类型】" class="selF"></div>
						<%-- <input type="" class="input" id="fuelType" name="fuelType"
							value="${boilerQs.fuelType}"> --%>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">蒸吨数</label>
					<div class="infpCon">
						<input type="" class="input" id="steamTon" name="steamTon"
							value="${boilerQs.steamTon}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">上次检测日期</label>
					<div class="infpCon">
						<input type="" class="input" id="lastExamineDate"
							name="lastExamineDate" value="${boilerQs.lastExamineDate}" onfocus="this.blur()">
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">下次检测日期</label>
					<div class="infpCon">
						<input type="" class="input" id="nextExamineDate"
							name="nextExamineDate" value="${boilerQs.nextExamineDate}" onfocus="this.blur()">
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${boilerQs.id}" />

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${boilerQs.description}</textarea>
					</div>
				</div>
			</div>


		</div>

	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>