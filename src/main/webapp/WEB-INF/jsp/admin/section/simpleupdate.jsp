<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>更新工段</title>
</head>
<style>
	/* .infpUnit {
		display: inline;
	} */
</style>
<script type="text/javascript">
	require(
			["hide", "validate", "ajaxform", "panel" ],
			function(hide) {
				var productUnit = $("#productUnit","#section_form").select({name:"product.unit",isCustom: true,zIndex:20,
					defaultValue : '${section.product.unit}',
					initAfter:function(){
						console.log(123);
						$('#productCapacityUnit').html('<span>'+productUnit.getSelectValue()+'</span>');
					},
					change: function(data, value) {
						$('#productCapacityUnit').html('<span>'+value+'</span>');
					}
				});
				$("#useStatus","#section_form").select({filter: false,name:"useStatus",zIndex:20,isCustom : true,defaultValue:'${section.useStatus}'});
			    
				$("#rawMaterials1Unit","#section_form").select({name:"rawMaterials1.unit",
					defaultValue : '${section.rawMaterials1.unit}',
					isCustom: true,zIndex:19});
				$("#rawMaterials2Unit","#section_form").select({name:"rawMaterials2.unit",
					defaultValue : '${section.rawMaterials2.unit}',isCustom: true,zIndex:18});
				$("#rawMaterials3Unit","#section_form").select({name:"rawMaterials3.unit",
					defaultValue : '${section.rawMaterials3.unit}',isCustom: true,zIndex:17});
				
				$("#fuelType","#section_form").select({name:"fuelType",
					defaultValue : '${section.fuelType}',isCustom: true,zIndex:16});
				$("#fuelUnit","#section_form").select({name:"fuelUnit",
					defaultValue : '${section.fuelUnit}',fuelTypeisCustom: true,zIndex:15});
				
				var select3 = $("#governanceMeasures1", "#section_form")
						.select({
							name : "governanceMeasures1",
							zIndex : 4,
							multselect : true,isCustom : true
						});
				var select3 = $("#governanceMeasures2", "#section_form")
						.select({
							name : "governanceMeasures2",
							zIndex : 2,
							multselect : true,isCustom : true
						});
				var select3 = $("#governanceMeasures3", "#section_form")
						.select({
							name : "governanceMeasures3",
							zIndex : 3,
							multselect : true,isCustom : true
						});
				var select3 = $("#governanceMeasures4", "#section_form")
						.select({
							name : "governanceMeasures4",
							zIndex : 1,
							multselect : true,isCustom : true
						});
				var select4 = $("#exhaustionHoleId", "#section_form").select({
					name : "exhaustionHole",
					defaultValue : '${section.enterpriseEmissionsManagement.exhaustionHole.id}'
				});
				
				
				
				$('#commit').click(function() {
					$("#section_form").submit();
				})
				//提交表单
				$("#section_form")
						.validate(
								{
									rules: {
										name:{
											required:true
										},
										"product.name":{
											required:true
										},
										"product.yield":{
											required:true,
											min:0
										},
										productionLines:{
											required:true,
											min:0
										},
										productCapacity:{
											required:true,
											min:0,
											maxlength:16
										},
										"rawMaterials1.consumption":{
											min:0,
											maxlength:16
										},
										fuelConsumption:{
											min:0,
											maxlength:16
										},
										"enterpriseEmissionsManagement.exhaustionHole.height":{
											min:0
										},
										"product.unit":{
											checkUnit:/\D/,
											required:true
										},
										"rawMaterials1.unit":{
											checkUnit:/\D/
										},
										"rawMaterials2.unit":{
											checkUnit:/\D/
										},
										"rawMaterials3.unit":{
											checkUnit:/\D/
										},
										fuelUnit:{
											checkUnit:/\D/
										},
										governanceMeasures1:{
											required:true
										},
										governanceMeasures2:{
											required:true
										},
										governanceMeasures3:{
											required:true
										},
										governanceMeasures4:{
											required:true
										},
										description:{
											maxlength:255
										}
									},
									messages:{
										
									},
									submitHandler : function(form) {
										if (!isSubmit($(
										"input[name ='product.name']")
										.val(), $(
										"input[name ='product.yield']")
										.val(), $(
										"input[name ='product.unit']")
										.val())) {
									qy.suc3({
										title : "产品必须填写3个参数！"
									});
									return false
								}
										if (!isSubmit(
												$(
														"input[name ='rawMaterials1.name']")
														.val(),
												$(
														"input[name ='rawMaterials1.consumption']")
														.val(),
												$(
														"input[name ='rawMaterials1.unit']")
														.val())) {
											qy.suc3({
												title : "原料信息必须填写3个参数！"
											});
											return false
										}
										if (!isSubmit(
												$(
														"input[name ='fuelType']")
														.val(),
												$(
														"input[name ='fuelConsumption']")
														.val(),
												$(
														"input[name ='fuelUnit']")
														.val())) {
											qy.suc3({
												title : "燃料必须填写3个参数！"
											});
											return false
										}
										
										

										var options = {
											url : 'updateSimple.jhtml',
											type : 'post',
											dataType : 'json',
											success : function(data) {
												qy.suc2({
													title : '更新成功！'
												});
												grid1.loadData();
												layer.closeAll('page');
											},
											error : function() {
												qy.fail({
													title : '更新失败'
												});
											}
										};
										$(form).ajaxSubmit(options);
										return false;
									}
								});
				function isSubmit(str1, str2, str3) {
					if (str1 && str2 && str3) {
						return true;
					}
					if (!str1 && !str2 && !str3) {
						return true
					}
					return false;
				}
				hide.hide();
			});
</script>

<body>
	<form id="section_form" style="padding: 0; width: 100%;">

		<input type="hidden" name="id" value="${section.id }" />
		<input type="hidden" name="enterpriseEmissionsManagement.governanceMeasures1.id" value="${section.enterpriseEmissionsManagement.governanceMeasures1.id }" />
		<input type="hidden" name="enterpriseEmissionsManagement.governanceMeasures2.id" value="${section.enterpriseEmissionsManagement.governanceMeasures2.id }" />
		<input type="hidden" name="enterpriseEmissionsManagement.governanceMeasures3.id" value="${section.enterpriseEmissionsManagement.governanceMeasures3.id }" />
		<input type="hidden" name="enterpriseEmissionsManagement.governanceMeasures4.id" value="${section.enterpriseEmissionsManagement.governanceMeasures4.id }" />
		

		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">工段/工艺名称或炉窑类型</label>
					<div class="infpCon">
						<input type="" class="input" name="name" value="${section.name}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">生产线数量</label>
					<div class="infpCon">
						<input type="" class="input" name="productionLines"
							value="${section.productionLines}" number=true>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">设备/工序使用状态</label>
					<div class="infpCon">
						<div class="selF" id="useStatus" data-code="锅炉【锅炉状态】"></div>
					</div>
				</div>
			</div>

			<div class="infh1">产品信息</div>

			<input type="hidden" name="product.id" value="${section.product.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">产品名称</label>
					<div class="infpCon">
						<input type="" class="input" name="product.name"
							value="${section.product.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年产量</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield"
						value="<fmt:formatNumber value="${section.product.yield}" pattern="#.##"/>"
						number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="product.unit"
							value="${section.product.unit }" id="productUnit"> --%>
							<div class="selF" id="productUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">设计产能(产品/年)</label>
					<div class="infpCon">
						<input type="" class="input" name="productCapacity"
						value="<fmt:formatNumber value="${section.productCapacity}" pattern="#.##"/>"
						number=true>
					</div>
					<label for="" class="infpUnit" id="productCapacityUnit"></label>
				</div>
			</div>

			<div class="infh1">原辅料1信息</div>

			<input type="hidden" name="rawMaterials1.id"
				value="${section.rawMaterials1.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.name"
							value="${section.rawMaterials1.name }" required/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.consumption"
						value="<fmt:formatNumber value="${section.rawMaterials1.consumption}" pattern="#.##" />"
						number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="rawMaterials1.unit"
							value="${section.rawMaterials1.unit }" required/> --%>
							<div class="selF" id="rawMaterials1Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			<div class="infh1">原辅料2信息</div>

			<input type="hidden" name="rawMaterials2.id"
				value="${section.rawMaterials2.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.name"
							value="${section.rawMaterials2.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.consumption"
						value="<fmt:formatNumber value="${section.rawMaterials2.consumption}" pattern="#.##"/>"
						number=true/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="rawMaterials2.unit"
							value="${section.rawMaterials2.unit }"> --%>
							<div class="selF" id="rawMaterials2Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			<div class="infh1">原辅料3信息</div>

			<input type="hidden" name="rawMaterials3.id"
				value="${section.rawMaterials3.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.name"
							value="${section.rawMaterials3.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.consumption"
						value="<fmt:formatNumber value="${section.rawMaterials3.consumption}" pattern="#.##"/>"
						number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="rawMaterials3.unit"
							value="${section.rawMaterials3.unit }"> --%>
							<div class="selF" id="rawMaterials3Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="fuelType"
							value="${section.fuelType }"> --%>
						<div id="fuelType" data-code="燃料类型" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption"
							value="${section.fuelConsumption}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="fuelUnit"
							value="${section.fuelUnit }"> --%>
							<div class="selF" id="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">污染物治理措施（可多选）</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硫工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="简表【脱硫工艺】"
							data-defaultValue="${section.enterpriseEmissionsManagement.governanceMeasures1.name }"
							class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硝工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="简表【脱硝工艺】"
							data-defaultValue="${section.enterpriseEmissionsManagement.governanceMeasures3.name }"
							class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">除尘工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="简表【除尘工艺】"
							data-defaultValue="${section.enterpriseEmissionsManagement.governanceMeasures2.name }"
							class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs措施</label>
					<div class="infpCon">
						<div id="governanceMeasures4" data-code="简表【VOC措施】"
							data-defaultValue="${section.enterpriseEmissionsManagement.governanceMeasures4.name }"
							class="selF"></div>
					</div>
				</div>
			</div>

			<input type="hidden"
				name="enterpriseEmissionsManagement.exhaustionHole.id"
				value="${section.enterpriseEmissionsManagement.exhaustionHole.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口高度（米）</label>
					<div class="infpCon">
						<input type="" class="input"
							name="enterpriseEmissionsManagement.exhaustionHole.height"
							value="${section.enterpriseEmissionsManagement.exhaustionHole.height}"
							number=true>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${section.description}</textarea>
					</div>
				</div>
			</div>

		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>