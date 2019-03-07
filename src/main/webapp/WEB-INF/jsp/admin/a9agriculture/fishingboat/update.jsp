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
<title>市农业委-全市渔船信息</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#fuelType", "#boiler_form").select({name:"fuelType",defaultValue:"${fishingBoat.fuelType}",isCustom:true,zIndex:9});
		$("#breedingMethod", "#boiler_form").select({name:"breedingMethod",defaultValue:"${livestockBreeding.breedingMethod}",zIndex:8});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						fuelSulphurRate:{
							range:[0,100]
						}
					},
					messages:{
						
					},
					submitHandler : function(form) {

						var options = {
							url : '../fishingboat/update.jhtml',
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
				<input type="hidden" name="id" value="${fishingBoat.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">船型</label>
					<div class="infpCon">
						<input type="" class="input" name="boatType" value="${fishingBoat.boatType }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" name="power" value="${fishingBoat.power }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"> 
						<!-- <div data-code="农业委农作物产量及秸秆利用【农作物类型】" id="cropType" class="selF"></div> -->
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="number" value="${fishingBoat.number }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃油类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="fuelType" /> -->
						<div data-code="农业委渔船信息【燃油类型】" id="fuelType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">消耗量（吨/年/艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="annualConsume" value="${fishingBoat.annualConsume }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/> 
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率（％）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulphurRate" value="${fishingBoat.fuelSulphurRate }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${fishingBoat.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>