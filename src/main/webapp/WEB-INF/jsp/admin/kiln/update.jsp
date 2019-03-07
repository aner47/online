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
<title>编辑炉窑</title>
</head>

<script type="text/javascript">
	require(
			["hide","localCache", "validate", "ajaxform", "panel", "date","year" ],
			function(hide,localCache) {
				
				var putProductionYear = localCache.getCookie("putProductionYear");
				console.log(putProductionYear)
				
				var year = $('#kiln_form #year').year({'name':'year',defaultValue:'${kiln.year}'});

				
				var changeNames = ["fuelConsumption","fuelUnit","'product.yield'","'product.unit'"];
				var changeUseStatus = ["#rawMaterials1", "#rawMaterials2", "#rawMaterials3"];
				var _changeNames = ["stopData"];
				 
				
				required('${kiln.useStatus}');
				
				function required(value){
					for(var i = 0; i < changeNames.length; i++){
						if(value == '备用' || value == '停用'){
							$('[name='+changeNames[i]+']', "#kiln_form").removeAttr('required');
						}else{
							$('[name='+changeNames[i]+']', "#kiln_form").attr('required','true');
						}
					}
					for(var i = 0; i < _changeNames.length; i++){
						if(value == '停用'){
							$('[name='+_changeNames[i]+']', "#kiln_form").attr('required','true');
							
						}else{
							$('[name='+_changeNames[i]+']', "#kiln_form").removeAttr('required');
						}
					}
					for (var i = 0; i < changeUseStatus.length; i++) {
						if (value === '停用' || value === '备用') {
							$(changeUseStatus[i], "#kiln_form").removeAttr('required');
						} else{
							$(changeUseStatus[i], "#kiln_form").attr('required','true');
						}
					}
				}
				

				
				$("#putOnDate", "#kiln_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        })
		        $("#abarbeitungTime", "#kiln_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        })
		        $("#stopData", "#kiln_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        })
		        /* 日期比较-开始  */
				function duibi(d1,d2)
				{
				  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
				}
				/* 日期比较-结束 */
				
				
				 $("#startManufacture", "#kiln_form").jeDate({
					 format:"hh:mm:ss",
		            zIndex:999999900
		        })
		         $("#endManufacture", "#kiln_form").jeDate({
		        	 format:"hh:mm:ss",
		            zIndex:999999900
		        })
		        

				var kilnType = $("#kilnType", "#kiln_form").select({
					name : "kilnType",isCustom : true,
					empty : true,
					zIndex : 20,
					defaultValue : '${kiln.kilnType}'
				});
				
				 $("#useStatus", "#kiln_form").select({
						name: "useStatus",
						zIndex: 19,
						filter: false,
						defaultValue: '${kiln.useStatus}',
						change: function(data,value) {
						  required(value);
					}});
				 
				 $("#rawMaterials1Unit","#kiln_form").select({
		        	 name:"rawMaterials1.unit",zIndex:18,isCustom : true,
		        	 defaultValue: '${kiln.rawMaterials1.unit }'
		        });
		        $("#rawMaterials2Unit","#kiln_form").select({
		        	 name:"rawMaterials2.unit",zIndex:17,isCustom : true,
		        	 defaultValue: '${kiln.rawMaterials2.unit }'
		        });
		        $("#rawMaterials3Unit","#kiln_form").select({
		        	 name:"rawMaterials3.unit",zIndex:16,isCustom : true,
		        	 defaultValue: '${kiln.rawMaterials3.unit }'
		        });
		        $("#rawMaterials4Unit","#kiln_form").select({
		        	 name:"rawMaterials4.unit",zIndex:15,isCustom : true,
		        	 defaultValue: '${kiln.rawMaterials4.unit }'
		        });
		        $("#rawMaterials5Unit","#kiln_form").select({
		        	 name:"rawMaterials5.unit",zIndex:14,isCustom : true,
		        	 defaultValue: '${kiln.rawMaterials5.unit }'
		        });
		        var select2 = $("#fuelType", "#kiln_form").select({
					name : "fuelType",isCustom : true,
					empty : true,
					zIndex : 13,
					defaultValue : '${kiln.fuelType}'
				});
		        
		        $("#fuelUnit","#kiln_form").select({
		        	 name:"fuelUnit",zIndex:12,isCustom : true,
		        	 defaultValue: '${kiln.fuelUnit }'
		        });
		        $("#productUnit","#kiln_form").select({
		        	 name:"product.unit",zIndex:11,isCustom : true,
		        	 defaultValue: '${kiln.product.unit }'
		        });
		        
		        
		        var defaultgovernanceMeasures1 = '${kiln.enterpriseEmissionsManagement.governanceMeasures1.id}'?'${kiln.enterpriseEmissionsManagement.governanceMeasures1.id}':-1;
		        var defaultgovernanceMeasures2 = '${kiln.enterpriseEmissionsManagement.governanceMeasures2.id}'?'${kiln.enterpriseEmissionsManagement.governanceMeasures2.id}':-1;
		        var defaultgovernanceMeasures3 = '${kiln.enterpriseEmissionsManagement.governanceMeasures3.id}'?'${kiln.enterpriseEmissionsManagement.governanceMeasures3.id}':-1;
				
				
				$("#governanceMeasures1", "#kiln_form")
						.select(
								{
									name : "governanceMeasures1",
									empty : true,
									defaultValue : defaultgovernanceMeasures1,
									zIndex : 7
								});
				$("#governanceMeasures2", "#kiln_form")
						.select(
								{
									name : "governanceMeasures2",
									empty : true,
									defaultValue : defaultgovernanceMeasures2,
									zIndex : 6
								});
				$("#governanceMeasures3", "#kiln_form")
						.select(
								{
									name : "governanceMeasures3",
									empty : true,
									defaultValue : defaultgovernanceMeasures3,
									zIndex : 5
								});
				var select4 = $("#exhaustionHoleId", "#kiln_form")
						.select(
								{
									name : "exhaustionHoleId",
									empty : true,
									defaultValue : '${kiln.enterpriseEmissionsManagement.exhaustionHole.id}'
								});
				var select5 = $("#exhaustionHoleTailId", "#kiln_form").select({
					name : "exhaustionHoleTailId",
					empty : true,
					defaultValue : '${kiln.exhaustionHoleTail.id}'
				});
				$('#commit').click(function() {
					$("#kiln_form").submit();
				})

				var month = $("[name=productionMonth]").val();
				if (month) {
					$.each(month.split(","), function(i, v) {
						$("[name=productionMonthTemp][value=" + v + "]").attr(
								"checked", "checked");
					})
				}

				//提交表单
				$("#kiln_form")
						.validate(
								{
									rules : {
										kilnType:{
											required:true
										},
										"rawMaterials1.consumption":{
											min:0,
											maxlength:16
										},
										"rawMaterials2.consumption":{
											min:0,
											maxlength:16
										},
										"rawMaterials3.consumption":{
											min:0,
											maxlength:16
										},
										"rawMaterials4.consumption":{
											min:0,
											maxlength:16
										},
										"rawMaterials5.consumption":{
											min:0,
											maxlength:16
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
										"rawMaterials4.unit":{
											checkUnit:/\D/
										},
										"rawMaterials5.unit":{
											checkUnit:/\D/
										},
										fuelUnit:{
											checkUnit:/\D/
										},
										"product.unit":{
											checkUnit:/\D/
										},
										fuelConsumption:{
											min:0,
											maxlength:16
										},
										fuelUnit:{
											checkUnit:/\D/
										},
										fuelSulfurContent : {
											range : [ 0, 100 ]
										},
										"product.yield":{
											min:0
										},
										"product.unit":{
											checkUnit:/\D/
										},
										fuelType:{
											required:true
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
										"enterpriseEmissionsManagement.exhaustionHole.id":{
											required:true
										},
										"exhaustionHoleTail.id":{
											required:true
										},
										useStatus:{
											required:true
										},
										exhaustionHoleTailId:{
											required:true
										},
										description:{
											maxlength:255
										},
									},
									messages : {
										description:{
											maxlength:"最多255字"
										}
									},
									submitHandler : function(form) {
										//日期比较
										if(duibi($("#kiln_form #putOnDate").val(),$("#kiln_form #stopData").val())){
											layer.msg('关停时间不能早于投运时间',{icon:3});
											return false;
										}
										if(duibi($("#kiln_form #putOnDate").val(),$("#kiln_form #abarbeitungTime").val())){
											layer.msg('整改时间不能早于投运时间',{icon:3});
											return false;
										}
										
										if (putProductionYear > $("#kiln_form #putOnDate").val()) {
											layer.msg('投运时间不能早于开业时间',{icon:3});
											return false;
										}
										
										var month = [];
										$("[name=productionMonthTemp]:checked").each(function(i, v) {
											month.push($(v).val())
										});
										$("[name=productionMonth]").val(month.join(","));
										
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
												title : "辅料1必须填写3个参数！"
											});
											return false
										}
										console.log('qqqq');
										if (!isSubmit(
												$("input[name ='rawMaterials2.name']").val(),
												$("input[name ='rawMaterials2.consumption']").val(),
												$("input[name ='rawMaterials2.unit']") .val())) {
											qy.suc3({
												title : "辅料2必须填写3个参数！"
											});
											return false
										}
										if (!isSubmit(
												$(
														"input[name ='rawMaterials3.name']")
														.val(),
												$(
														"input[name ='rawMaterials3.consumption']")
														.val(),
												$(
														"input[name ='rawMaterials3.unit']")
														.val())) {
											qy.suc3({
												title : "辅料3必须填写3个参数！"
											});
											return false
										}
										if (!isSubmit(
												$(
														"input[name ='rawMaterials4.name']")
														.val(),
												$(
														"input[name ='rawMaterials4.consumption']")
														.val(),
												$(
														"input[name ='rawMaterials4.unit']")
														.val())) {
											qy.suc3({
												title : "辅料4必须填写3个参数！"
											});
											return false
										}
										if (!isSubmit(
												$(
														"input[name ='rawMaterials5.name']")
														.val(),
												$(
														"input[name ='rawMaterials5.consumption']")
														.val(),
												$(
														"input[name ='rawMaterials5.unit']")
														.val())) {
											qy.suc3({
												title : "辅料5必须填写3个参数！"
											});
											return false
										}
										/* if (!isSubmit(
												select2.getSelectValue(),
												$(
														"input[name ='fuelConsumption']")
														.val(),
												$("input[name ='fuelUnit']")
														.val())) {
											qy.suc3({
												title : "燃料信息必须填写3个参数！"
											});
											return false
										} */
										/* if (!isSubmit($(
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
										} */
										var options = {
											url : 'update.jhtml',
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
												qy.fail2({
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
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form class="hideCls" id="kiln_form" style="padding:0;width:100%;">

		<input type="hidden" name="id" value="${kiln.id }" />
		<input type="hidden" name="no" value="${kiln.no }" />
		<input name="rawMaterials1.id" type="hidden" value="${kiln.rawMaterials1.id }">
		<input name="rawMaterials2.id" type="hidden" value="${kiln.rawMaterials2.id }">
		<input name="rawMaterials3.id" type="hidden" value="${kiln.rawMaterials3.id }">
		<input name="rawMaterials4.id" type="hidden" value="${kiln.rawMaterials4.id }">
		<input name="rawMaterials5.id" type="hidden" value="${kiln.rawMaterials5.id }">
		<input name="product.id" type="hidden" value="${kiln.product.id }">
		<input name="enterpriseEmissionsManagement.exhaustionHole.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.exhaustionHole.id }">
		<input name="enterpriseEmissionsManagement.governanceMeasures1.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.governanceMeasures1.id }">
		<input name="enterpriseEmissionsManagement.governanceMeasures2.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.governanceMeasures2.id }">
		<input name="enterpriseEmissionsManagement.governanceMeasures3.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.governanceMeasures3.id }">

		<div class="infLine">

			<div class="infh1">工艺描述</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>窑炉类型</label>
					<div class="infpCon">
						<div id="kilnType" data-code="炉窑类型" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>使用状态</label>
					<div class="infpCon">
						<div id="useStatus" data-code="排放口【使用状态】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>投运时间</label>
					<div class="infpCon">
						<input type="" class="input" id="putOnDate" name="putOnDate" value="<fmt:formatDate value='${kiln.putOnDate}' pattern='yyyy-MM'/>" onfocus="this.blur()" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
						<input class="input" id="abarbeitungTime" name="abarbeitungTime" type="text" 
						value="<fmt:formatDate value='${kiln.abarbeitungTime}' pattern='yyyy-MM'/>"
						  onfocus="this.blur()" />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopData" name="stopData"
						 value="<fmt:formatDate value='${kiln.stopData}' pattern='yyyy-MM'/>" 
						 onfocus="this.blur()">
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<input type="hidden" name="productionMonth" value='${kiln.productionMonth}'>
					<div class="infParaCon">
						<div class="infpLable">运行月份</div>
						<div class="infpCon">
							<div class="months">
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="1" id="cheB1">
								<div class="label2f">
									<label for="cheB1" class="label2">一月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="2" id="cheB2">
								<div class="label2f">
									<label for="cheB2" class="label2">二月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="3" id="cheB3">
								<div class="label2f">
									<label for="cheB3" class="label2">三月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="4" id="cheB4">
								<div class="label2f">
									<label for="cheB4" class="label2">四月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="5" id="cheB5">
								<div class="label2f">
									<label for="cheB5" class="label2">五月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="6" id="cheB6">
								<div class="label2f">
									<label for="cheB6" class="label2">六月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="7" id="cheB7">
								<div class="label2f">
									<label for="cheB7" class="label2">七月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="8" id="cheB8">
								<div class="label2f">
									<label for="cheB8" class="label2">八月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="9" id="cheB9">
								<div class="label2f">
									<label for="cheB9" class="label2">九月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="10" id="cheB10">
								<div class="label2f">
									<label for="cheB10" class="label2">十月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="11" id="cheB11">
								<div class="label2f">
									<label for="cheB11" class="label2">十一月</label>
								</div>
								<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="12" id="cheB12">
								<div class="label2f">
									<label for="cheB12" class="label2">十二月</label>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">生产时段</label>
						<div class="infpCon">
							<div class="inputs">
								<div class="inputChild col6">
									<input type="" class="input" id="startManufacture" name="startManufacture" value="<fmt:formatDate value="${kiln.startManufacture}" pattern="HH:mm:ss"/>" />
								</div>
								<div class="inputChild col6">
									<input type="" class="input" id="endManufacture" name="endManufacture" value="<fmt:formatDate value="${kiln.endManufacture}" pattern="HH:mm:ss"/>" placeholder="" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年份</label>
						<div class="infpCon">
							<div id="year" class="yearF iconfont icon-nianfen"></div>
						</div>
					</div>
				</div>

			<div class="infh1">原料信息1</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>原料名称</label>
					<div class="infpCon">
						<input id="rawMaterials1" type="" class="input" name="rawMaterials1.name" value="${kiln.rawMaterials1.name }" />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗量</label>
					<div class="infpCon">
						<input type="" id="rawMaterials2"  class="input" name="rawMaterials1.consumption" value="<fmt:formatNumber value="${kiln.rawMaterials1.consumption }" pattern="#.##"/>" number="true" />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" id="rawMaterials3"  class="input" name="rawMaterials1.unit"  value="${kiln.rawMaterials1.unit }"/> --%>
						<div class="selF" id="rawMaterials1Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">原料信息2</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.name" value="${kiln.rawMaterials2.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.consumption" value="<fmt:formatNumber value="${kiln.rawMaterials2.consumption }" pattern="#.##"/>" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="rawMaterials2.unit"  value="${kiln.rawMaterials2.unit }"> --%>
						<div class="selF" id="rawMaterials2Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">原料信息3</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.name" value="${kiln.rawMaterials3.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.consumption" value="<fmt:formatNumber value="${kiln.rawMaterials3.consumption }" pattern="#.##"/>" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="rawMaterials3.unit"  value="${kiln.rawMaterials3.unit }"> --%>
						<div class="selF" id="rawMaterials3Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">原料信息4</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.name" value="${kiln.rawMaterials4.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.consumption" value="<fmt:formatNumber value="${kiln.rawMaterials4.consumption }" pattern="#.##"/>" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="rawMaterials4.unit"  value="${kiln.rawMaterials4.unit }"> --%>
						<div class="selF" id="rawMaterials4Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">原料信息5</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.name" value="${kiln.rawMaterials5.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.consumption" value="<fmt:formatNumber value="${kiln.rawMaterials5.consumption }" pattern="#.##"/>" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="rawMaterials5.unit"  value="${kiln.rawMaterials5.unit }"> --%>
						<div class="selF" id="rawMaterials5Unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="燃料类型" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" value="<fmt:formatNumber value="${kiln.fuelConsumption }" pattern="#.##"/>" number="true" required/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="fuelUnit" value="${kiln.fuelUnit}" required/> --%>
						<div class="selF" id="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" value="${kiln.fuelSulfurContent }" number="true" />
					</div>
				</div>
			</div>

			<div class="infh1">产品信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>产品名称</label>
					<div class="infpCon">
						<input type="" class="input" name="product.name" value="${kiln.product.name }" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年产量</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield" value="<fmt:formatNumber value="${kiln.product.yield }" pattern="#.##"/>" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="product.unit" value="${kiln.product.unit }" required> --%>
						<div class="selF" id="productUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara"></div>

			<div class="infh1">治理措施</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施编号1</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="污染治理措施" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施编号2</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="污染治理措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施编号3</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="污染治理措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara"></div>

			<div class="infh1">排放口编号</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">窑头</label>
					<div class="infpCon">
						<div id="exhaustionHoleId" data-code="排放口" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>窑尾</label>
					<div class="infpCon">
						<div id="exhaustionHoleTailId" data-code="排放口" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${kiln.description }</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>