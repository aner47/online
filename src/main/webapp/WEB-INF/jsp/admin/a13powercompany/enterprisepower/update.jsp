<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改企业用电信息表</title>
</head>

<script type="text/javascript">
require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

	$("#year", "#boiler_form").jeDate({
        format:"YYYY",
        isTime:false, 
        zIndex:999999900
    });
	
		$("#stockgroundDustMeasures", "#boiler_form").select({name:"stockgroundDustMeasures",defaultValue:"${betonStirStation.stockgroundDustMeasures}",zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../enterprisepower/update.jhtml',
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
					<label for="" class="infpLable">企业名称</label>
					<div class="infpCon">
						<input type="" class="input" id="city" name="enterprise.name" value="${enterprisePower.enterprise.name}" required>
					</div>
				</div>
			</div>
    		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" id="city" name="city" value="${enterprisePower.city}">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" id="houseNumber" name="houseNumber" value="${enterprisePower.houseNumber}">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">用电户号</label>
					<div class="infpCon">
						<input type="" class="input" id="powerNo" name="powerNo" value="${enterprisePower.powerNo}">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">年份</label>
					<div class="infpCon">
						<input type="" class="input" id="year" name="year" value="${enterprisePower.year}" number="true" onfocus="this.blur()">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">年用电量（万千瓦时）</label>
					<div class="infpCon">
						<input type="" class="input" id="electricityConsumption" name="electricityConsumption" value="${enterprisePower.electricityConsumption}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
	 <input type="hidden" id="id" name="id" value="${enterprisePower.id}"/>
 
		<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${enterprisePower.description}</textarea>
					</div>
				</div>
			</div>


    </div>

</form>
<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>