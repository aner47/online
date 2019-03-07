<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>农业委-农业机械信息</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#machineryType", "#boiler_form").select({name:"machineryType",defaultValue:"${agriculturalMachinery.machineryType}",isCustom:true,zIndex:9});
		$("#breedingMethod", "#boiler_form").select({name:"breedingMethod",defaultValue:"${livestockBreeding.breedingMethod}",zIndex:8});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						fuelSulfurRate:{
							range:[0,100]
						}
					},
					messages:{
						
					},
					submitHandler : function(form) {

						var options = {
							url : '../agriculturalmachinery/update.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								qy.suc2({
									title : '修改成功！'
								});
								grid1.loadData();
								layer.closeAll('page');
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
		<div class="dialog_div">
				<input type="hidden" name="id" value="${agriculturalMachinery.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${agriculturalMachinery.city }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="machineryType" required> -->
						<div data-code="农业委农业机械【机械类型】" id="machineryType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械保有量（台/辆）</label>
					<div class="infpCon">
						<input type="" class="input" name="machineryInventory" value="${agriculturalMachinery.machineryInventory }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">总功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" name="totalPower" value="${agriculturalMachinery.totalPower }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料消耗量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsume" value="${agriculturalMachinery.fuelConsume }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/> 
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率（％）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurRate" value="${agriculturalMachinery.fuelSulfurRate }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">年工作时间（小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="annualWorkHours" value="${agriculturalMachinery.annualWorkHours }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${agriculturalMachinery.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>