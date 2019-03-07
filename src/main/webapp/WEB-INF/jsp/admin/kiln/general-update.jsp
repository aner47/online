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
			["hide","validate", "ajaxform", "panel", "date" ],
			function(hide) {
				$("#putDate1", "#kiln_form").jeDate({
			        format:"YYYY-MM",
			        isTime:false, 
			        zIndex:999999900,
			    })
				$("#putDate2", "#kiln_form").jeDate({
			        format:"YYYY-MM",
			        isTime:false, 
			        zIndex:999999900,
			    })
				$("#putDate3", "#kiln_form").jeDate({
			        format:"YYYY-MM",
			        isTime:false, 
			        zIndex:999999900,
			    })
				$("#putDate4", "#kiln_form").jeDate({
			        format:"YYYY-MM",
			        isTime:false, 
			        zIndex:999999900,
			    })

				$("#putOnDate", "#kiln_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        })
		        $("#stopData", "#kiln_form").jeDate({
		            format:"YYYY-MM-DD",
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
		        });
				 $("#endManufacture", "#kiln_form").jeDate({
			        	format:"hh:mm:ss",
			            zIndex:999999900
			        });
		        var select1 = $("#year", "#kiln_form").select({
					name : "year",
					 defaultValue : '${kiln.year}',
					zIndex : 9,
					empty: true
				});

				var select1 = $("#kilnType", "#kiln_form").select({
					name : "kilnType",
					empty : true,
					zIndex : 9,
					defaultValue : '${kiln.kilnType}'
				});
				var select2 = $("#fuelType", "#kiln_form").select({
					name : "fuelType",
					empty : true,
					zIndex : 8,
					defaultValue : '${kiln.fuelType}'
				});
				var select3 = $("#governanceMeasures1","#kiln_form").select({name:"governanceMeasures1",multselect:true,zIndex:4});
				var select3 = $("#governanceMeasures2","#kiln_form").select({name:"governanceMeasures2",multselect:true,zIndex:3});
				var select3 = $("#governanceMeasures3","#kiln_form").select({name:"governanceMeasures3",multselect:true,zIndex:2});
				var select3 = $("#governanceMeasures4","#kiln_form").select({name:"governanceMeasures4",multselect:true,zIndex:1});
				
				var select4 = $("#exhaustionHoleId", "#kiln_form")
						.select(
								{
									name : "enterpriseEmissionsManagement.exhaustionHole.id",
									empty : true,
									defaultValue : '${kiln.enterpriseEmissionsManagement.exhaustionHole.id}'
								});
				var select5 = $("#exhaustionHoleTailId", "#kiln_form").select({
					name : "exhaustionHoleTail.id",
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
										
										year:{
											required:true
										},
										putDate1:{
											required:true
										},
										putDate2:{
											required:true
										},
										putDate3:{
											required:true
										},
										putDate4:{
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
										governanceMeasures4:{
											required:true
										},
										
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
										description:{
											maxlength:255
										},
										"enterpriseEmissionsManagement.exhaustionHole.height":{
											range : [ 0, 100 ]
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
										if (!isSubmit(
												$(
														"input[name ='rawMaterials2.name']")
														.val(),
												$(
														"input[name ='rawMaterials2.consumption']")
														.val(),
												$(
														"input[name ='rawMaterials2.unit']")
														.val())) {
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
										if (!isSubmit(
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
										}
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
										var options = {
											url : base
													+ '/web/kiln/generalupdate.jhtml',
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
	<form id="kiln_form" style="padding:0;width:100%;">

		<input type="hidden" name="id" value="${kiln.id }" />
		<input name="rawMaterials1.id" type="hidden" value="${kiln.rawMaterials1.id }">
		<input name="product.id" type="hidden" value="${kiln.product.id }">
		
		<input name="enterpriseEmissionsManagement.exhaustionHole.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.exhaustionHole.id }">
		<input name="enterpriseEmissionsManagement.governanceMeasures1.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.governanceMeasures1.id }">
		<input name="enterpriseEmissionsManagement.governanceMeasures2.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.governanceMeasures2.id }">
		<input name="enterpriseEmissionsManagement.governanceMeasures3.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.governanceMeasures3.id }">
		<input name="enterpriseEmissionsManagement.governanceMeasures4.id" type="hidden" value="${kiln.enterpriseEmissionsManagement.governanceMeasures4.id }">

		<div class="infLine">

			<div class="infh1">工艺描述</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年份</label>
					<div class="infpCon">
						<div id="year" data-code="工段【年份】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">窑炉类型</label>
					<div class="infpCon">
						<div id="kilnType" data-code="普查表炉窑【炉窑类型】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
					<input type="hidden" name="productionMonth" value="${kiln.productionMonth }">
					<div class="infParaCon">
						<label for="" class="infpLable">生产月份</label>
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


			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" class="input" id="putOnDate" name="putOnDate" value="<fmt:formatDate value='${kiln.putOnDate}' pattern='yyyy-MM-dd'/>" onfocus="this.blur()" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopData" name="stopData" value="<fmt:formatDate value='${kiln.stopData}' pattern='yyyy-MM-dd'/>" readonly>
					</div>
				</div>
			</div>
			
			<div class="infh1">产品信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">产品名称</label>
					<div class="infpCon">
						<input type="" class="input" name="product.name" value="${kiln.product.name }" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年产量</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield" value="<fmt:formatNumber value="${kiln.product.yield }" pattern="#.##"/>" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="product.unit"value="${kiln.product.unit }" required>
					</div>
				</div>
			</div>

			

			<div class="infh1">原料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.name" value="${kiln.rawMaterials1.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.consumption" value="<fmt:formatNumber value="${kiln.rawMaterials1.consumption }" pattern="#.##"/>" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.unit"  value="${kiln.rawMaterials1.unit }">
					</div>
				</div>
			</div>

			

			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="燃料类型" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" value="<fmt:formatNumber value="${kiln.fuelConsumption }" pattern="#.##"/>" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelUnit" value="${kiln.fuelUnit}">
					</div>
				</div>
			</div>

			

			

			<div class="infh1">污染物治理措施（可多选）</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硫工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="简表【脱硫工艺】" class="selF"
						data-defaultValue="${kiln.enterpriseEmissionsManagement.governanceMeasures1.name }"
						></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate1" name="putDate1" class="Yinput" 
						value="<fmt:formatDate value='${kiln.enterpriseEmissionsManagement.governanceMeasures1.putDate}' pattern='yyyy-MM' />"
						 onfocus=this.blur() required />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">除尘工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="简表【除尘工艺】" class="selF"
						data-defaultValue="${kiln.enterpriseEmissionsManagement.governanceMeasures2.name }"
						></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate2" name="putDate2" class="Yinput" 
						value="<fmt:formatDate value='${kiln.enterpriseEmissionsManagement.governanceMeasures2.putDate}' pattern='yyyy-MM' />"
						 onfocus=this.blur() required />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硝工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="简表【脱硝工艺】" class="selF"
						data-defaultValue="${kiln.enterpriseEmissionsManagement.governanceMeasures3.name }"
						></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate3" name="putDate3" class="Yinput" 
						value="<fmt:formatDate value='${kiln.enterpriseEmissionsManagement.governanceMeasures3.putDate}' pattern='yyyy-MM' />"
						onfocus=this.blur() required />
					</div>
				</div>
			</div>

			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs措施</label>
					<div class="infpCon">
						<div id="governanceMeasures4" data-code="简表【VOC措施】" class="selF"
						data-defaultValue="${kiln.enterpriseEmissionsManagement.governanceMeasures4.name }"
						></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate4" name="putDate4" class="Yinput"
						value="<fmt:formatDate value='${kiln.enterpriseEmissionsManagement.governanceMeasures4.putDate}' pattern='yyyy-MM' />"
						onfocus=this.blur() required />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口高度（米）</label>
					<div class="infpCon">
						<input type="" class="input" name="enterpriseEmissionsManagement.exhaustionHole.height"
						value="${kiln.enterpriseEmissionsManagement.exhaustionHole.height}" number=true>
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
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>