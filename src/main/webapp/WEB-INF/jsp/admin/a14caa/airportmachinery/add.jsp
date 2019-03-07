<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>增加机场车辆信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				
				
				$("#fuelType", "#boiler_form").select({name:"fuelType",isCustom:true,zIndex:9});
				
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
					submitHandler : function(form) {
						
						var options = {
							url : '../airportmachinery/save.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								qy.suc2({
									title : '添加成功！'
								});
								grid1.loadData();
								layer.closeAll('page');
							},
							error : function() {
								qy.fail({
									title : '添加失败'
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


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">机场名称</label>
					<div class="infpCon">
						<input type="" class="input" id="enterprise.name"
							name="enterprise.name" required>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">机械类型</label>
					<div class="infpCon">
						<input type="" class="input" id="machineryType"
							name="machineryType">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">机械保有量（台/辆）</label>
					<div class="infpCon">
						<input type="" class="input" id="machineryInventory"
							name="machineryInventory" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">单台机械平均功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" id="avgPower" name="avgPower" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">机械总功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" id="totalPower" name="totalPower" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="民航局机场车辆【燃料类型】" class="selF"></div>
						<!-- <input type="" class="input" id="fuelType" name="fuelType"> -->
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料消耗量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" id="fuelConsume" name="fuelConsume" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率（％）</label>
					<div class="infpCon">
						<input type="" class="input" id="fuelSulfurRate"
							name="fuelSulfurRate" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>







			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description"></textarea>
					</div>
				</div>
			</div>

		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>