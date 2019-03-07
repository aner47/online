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
<title>修改煤气炉信息</title>
</head>

<script type="text/javascript">
	require(
			[ "hide","localCache", "validate", "ajaxform", "select", "panel", "date" ,"year"],
			function(hide,localCache) {
				
				var putProductionYear = localCache.getCookie("putProductionYear");
				console.log(putProductionYear)
				
				var fuelYear = $('#boiler_form #fuelYear').year({'name':'fuelYear',defaultValue:'${gasStove.fuelYear}'});
				
				var changeNames = ["fuelConsumption","fuelUnit","rateOfFlow","annualOutput"];
				var _changeNames = ["stopData"];
				 
				
				required('${gasStove.status}');
				
				function required(value){
					for(var i = 0; i < changeNames.length; i++){
						if(value == '备用' || value == '停用'){
							$('[name='+changeNames[i]+']', "#boiler_form").removeAttr('required');
						}else{
							$('[name='+changeNames[i]+']', "#boiler_form").attr('required','true');
						}
					}
					for(var i = 0; i < _changeNames.length; i++){
						if(value == '停用'){
							$('[name='+_changeNames[i]+']', "#boiler_form").attr('required','true');
							
						}else{
							$('[name='+_changeNames[i]+']', "#boiler_form").removeAttr('required');
						}
					}
				}
				

				$("#stopData", "#boiler_form").jeDate({
					format : "YYYY-MM",
					isTime : false,
					zIndex : 999999900,
				})
				$("#abarbeitungTime", "#boiler_form").jeDate({
					format : "YYYY-MM",
					isTime : false,
					zIndex : 999999900,
				})
				$("#operation", "#boiler_form").jeDate({
					format : "YYYY-MM",
					isTime : false,
					zIndex : 999999900,
				})
				/* 日期比较-开始  */
				function duibi(d1, d2) {
					//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
					return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
				}
				/* 日期比较-结束 */

				$("#startManufacture", "#boiler_form").jeDate({
					format:"hh:mm:ss",
		            zIndex:999999900
				})
				
				$("#endManufacture", "#boiler_form").jeDate({
					format:"hh:mm:ss",
		            zIndex:999999900
				})
				var boilerType = $("#boilerType", "#boiler_form").select({
					name : "boilerType",isCustom : true,filter: false,
					zIndex : 9,
					defaultValue : '${gasStove.boilerType}'
				});
				var status = $("#status", "#boiler_form").select({
					name : "status",
					zIndex : 8,
					filter: false,
					defaultValue : '${gasStove.status}',
					change: function(data,value){
						  required(value);
					}
				});

				var purpose = $("#purpose", "#boiler_form").select({
					name : "purpose",
					zIndex : 7,
					filter: false,
					defaultValue : '${gasStove.purpose}'
				});
				
				var exhaustionHoleId = $("#exhaustionHoleId", "#boiler_form")
						.select(
								{
									name : "exhaustionHoleId",
									defaultValue : '${gasStove.emissionsManagement.exhaustionHole.id}',
									param : "enterprise:enterprise,project:project"
								});
				var fuelType = $("#fuelType", "#boiler_form").select({
					name : "fuelType",isCustom : true,
					zIndex : 8,
					filter: false,
					defaultValue : '${gasStove.fuelType}'
				});
				var fuelUnit = $("#fuelUnit", "#boiler_form").select({
					name : "fuelUnit",isCustom : true,
					zIndex : 7,
					filter: false,
					defaultValue : '${gasStove.fuelUnit}'
				});
				
				var oldAppliance = '';
				var appliance = $("#appliance", "#boiler_form").select({
					name : "appliance",isCustom : true,multselect: true,filter: false,
					zIndex : 6,
					defaultValue : '${gasStove.appliance}',
						change:function(data,value){
							if(value.match("无") && value != "无" && !oldAppliance.match("无")){
								appliance.clearSelectValue();
								appliance.setSelectValue("无");
								oldAppliance = "无";
							}else if(value.match("无") && value != "无"){
								value = value.replace("无","");
								value = value.replace(",","");
								appliance.clearSelectValue();
								appliance.setSelectValue(value);
								oldAppliance = value;
							}else{
								oldAppliance = value;
							}
						}
				});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				var month = $("[name=productionMonth]").val();
				if (month) {
					$.each(month.split(","), function(i, v) {
						$("[name=productionMonthTemp][value=" + v + "]").attr(
								"checked", "checked");
					})
				}

				//提交表单
				$("#boiler_form")
						.validate(
								{
									rules : {
										boilerType : {
											required : true
										},
										status : {
											required : true
										},
										fuelSulfurContent : {
											range : [ 0, 100 ],
										},
										fuelAsh : {
											range : [ 0, 100 ],
										},
										fuelVolatiles : {
											range : [ 0, 100 ],
										},
										fuelConsumption : {
											min : 0
										},
										fuelUnit : {
											checkUnit : /\D/
										},
										steamTon : {
											min : 0,
											number : true
										},
										fuelType : {
											required : true
										},
										purpose : {
											required : true
										},
										fuelSulfurContent : {
											range : [ 0, 100 ]
										},
										fuelAsh : {
											range : [ 0, 100 ]
										},
										fuelVolatiles : {
											range : [ 0, 100 ]
										},
										"emissionsManagement.governanceMeasures1.id" : {
											required : true
										},
										"emissionsManagement.governanceMeasures2.id" : {
											required : true
										},
										"emissionsManagement.governanceMeasures3.id" : {
											required : true
										},
										"emissionsManagement.exhaustionHole.id" : {
											required : true
										},
										appliance : {
											required : true
										},
										description : {
											maxlength : 255
										}
									},
									messages : {
										description : {
											maxlength : "最多255字"
										}
									},
									submitHandler : function(form) {
										//日期比较
										if (duibi($("#boiler_form #operation")
												.val(), $(
												"#boiler_form #stopData").val())) {
											layer.msg('关停时间不能早于投运时间', {
												icon : 3
											});
											return false;
										}
										if (duibi($("#boiler_form #operation")
												.val(), $(
												"#boiler_form #abarbeitungTime").val())) {
											layer.msg('整改时间不能早于投运时间', {
												icon : 3
											});
											return false;
										}
										
										if (putProductionYear > $("#boiler_form #operation").val()) {
											layer.msg('投运时间不能早于开业时间',{icon:3});
											return false;
										}
										
										var month = [];
										$("[name=productionMonthTemp]:checked")
												.each(function(i, v) {
													month.push($(v).val())
												});
										$("[name=productionMonth]").val(
												month.join(","));

										var options = {
											url :'update.jhtml',
											type : 'post',
											dataType : 'json',
											success : function(data) {
												qy.suc2({
													title : '更新成功！'
												})
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
				hide.hide();
			});
</script>

<body>
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form class="hideCls" id="boiler_form" style="padding: 0; width: 100%;">
		<input type="hidden" name="id" value="${gasStove.id }" />
		<input type="hidden" name="no" value="${gasStove.no}" />
		<div class="infLine">

			<div class="infh1">工艺描述</div>


			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>类型</label>
					<div class="infpCon">
						<div id="boilerType" data-code="详表【煤气炉类型】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">炉膛直径(m)</label>
					<div class="infpCon">
						<input type="" class="input" name="diameter"
							value="${gasStove.diameter }">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">煤气出口温度(℃)</label>
					<div class="infpCon">
						<input type="" class="input" name="outTemperature"
							value="${gasStove.outTemperature }">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>用途</label>
					<div class="infpCon">
						<div id="purpose" data-code="详表锅炉【用途】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>状态</label>
					<div class="infpCon">
						<div id="status" data-code="锅炉【锅炉状态】" class="selF"></div>
					</div>
				</div>
			</div>




			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>投运时间</label>
					<div class="infpCon">
						<input type="" class="input" id="operation" name="operation"
							value="<fmt:formatDate value='${gasStove.operation}' pattern='yyyy-MM' />"
							onfocus="this.blur()" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
						<input type="" class="input" id="abarbeitungTime"
							name="abarbeitungTime" onfocus="this.blur()"
							value="<fmt:formatDate value='${gasStove.abarbeitungTime}' pattern='yyyy-MM' />"
							readonly>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopData" name="stopData"
							value="<fmt:formatDate value='${gasStove.stopData}' pattern='yyyy-MM' />"
							onfocus="this.blur()">
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">日运行时段</label>
					<div class="infpCon">
						<div class="inputs">
							<div class="inputChild col6">
								<input type="text" class="input" id="startManufacture"
									name="startManufacture"
									value="<fmt:formatDate value="${gasStove.startManufacture}" pattern="HH:mm:ss"/>"
									placeholder="" onfocus="this.blur()" />
							</div>
							<div class="inputChild col6">
								<input type="text" class="input" id="endManufacture"
									name="endManufacture" placeholder=""
									value="<fmt:formatDate value="${gasStove.endManufacture}" pattern="HH:mm:ss"/>"
									placeholder="" onfocus="this.blur()" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="infh1">燃料信息</div>

			<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">燃料年份</label>
						<div class="infpCon">
							<div id="fuelYear" class="yearF iconfont icon-nianfen"></div>
						</div>
					</div>
				</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="煤气发生炉【燃料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗总量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption"
							value="${gasStove.fuelConsumption }" number=true
							>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="fuelUnit"
							value="${gasStove.fuelUnit }" required> --%>
						<div class="selF" id="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent"
							value="${gasStove.fuelSulfurContent }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelAsh"
							value="${gasStove.fuelAsh }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料挥发分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelVolatiles"
							value="${gasStove.fuelVolatiles }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>煤气流量(标立方米/小时)</label>
					<div class="infpCon">
						<input type="" class="input" name="rateOfFlow"
						value="${gasStove.rateOfFlow }" number=true required="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>煤气年产量(万立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="annualOutput"
						value="${gasStove.annualOutput }" number=true required="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>配套装置(多选)</label>
					<div class="infpCon">
						<div id="appliance" data-code="详表煤气炉【配套装置】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">装置去除效率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="applianceRemovalRate"
						value="${gasStove.applianceRemovalRate }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>排放口编号</label>
					<div class="infpCon">
						<div id="exhaustionHoleId" data-code="排放口" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${gasStove.description }</textarea>
					</div>
				</div>
			</div>

		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>