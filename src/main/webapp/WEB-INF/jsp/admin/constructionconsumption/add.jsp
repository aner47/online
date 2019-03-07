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
<title>添加机械消耗信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				
		        
				$("#mechanical", "#boiler_form").select({name:"mechanical",zIndex:9});
				$("#oilStandard", "#boiler_form").select({name:"oilStandard",zIndex:8});
				$("#avgConsumptionUnit", "#boiler_form").select({name:"avgConsumptionUnit",zIndex:7});
				
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form")
						.validate(
								{
									rules : {
										fuelSulfurContent : {
											range : [ 0, 100 ],
										},
										fuelAsh : {
											range : [ 0, 100 ],
										},
										fuelVolatiles : {
											range : [ 0, 100 ],
										},
										avgRate:{
											min:0
										},
										avgConsumption:{
											min:0
										},
										description:{
											maxlength:255
										}

									},
									messages : {
										fuelSulfurContent : {
											range : "0~100!",
										},
										fuelAsh : {
											range : "0~100!",
										},
										fuelVolatiles : {
											range : "0~100!",
										},
										
									},
									submitHandler : function(form) {
										
										var options = {
											url : base
													+ '/web/constructionconsumption/save.jhtml',
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
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">施工机械</label>
					<div class="infpCon">
						<div id="mechanical" data-code="施工工地【施工机械】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">油品标准</label>
					<div class="infpCon">
						<div id="oilStandard" data-code="施工工地【油品标准】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div style="clear:both;"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械平均功率（千瓦/辆）</label>
					<div class="infpCon">
						<input id="avgRate" class="input" name="avgRate" placeholder="机械平均功率" number=true />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单车油耗量 </label>
					<div class="infpCon">
						<input id="avgConsumption" class="input" name="avgConsumption" placeholder="单车油耗量" number=true />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位 </label>
					<div class="infpCon">
						<div id="avgConsumptionUnit" data-code="施工工地【单车油耗量单位】" class="selF"></div>
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
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>