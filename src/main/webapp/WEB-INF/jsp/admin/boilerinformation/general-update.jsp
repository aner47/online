<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">

<meta charset="UTF-8">
<title>修改锅炉信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel","date" ],
			function(hide) {
				
				$("#putDate1", "#boiler_form").jeDate({
			        format:"YYYY-MM",
			        isTime:false, 
			        zIndex:999999900,
			    })
				$("#putDate2", "#boiler_form").jeDate({
			        format:"YYYY-MM",
			        isTime:false, 
			        zIndex:999999900,
			    })
				$("#putDate3", "#boiler_form").jeDate({
			        format:"YYYY-MM",
			        isTime:false, 
			        zIndex:999999900,
			    })
			    
				var fuelYear = $("#fuelYear","#boiler_form").select({name:"fuelYear",defaultValue : '${boilerInformation.fuelYear}',zIndex:6});
				
				var boilerType = $("#boilerType", "#boiler_form").select({
					name : "boilerType",
					zIndex : 10,
					defaultValue : '${boilerInformation.boilerType}',
					change:function(event, value){
						fuelType.loadData(value);
					}
				});
				
				$("#status","#boiler_form").select({name:"status",defaultValue : '${boilerInformation.status}',zIndex:9});
				$("#purpose","#boiler_form").select({name:"purpose",defaultValue : '${boilerInformation.purpose}',zIndex:8});
				
				$("#stopData", "#boiler_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900,
		            /* choosefun:function(elem, val, date) {
		            	$("#stopData").val(date);
		            }, */
		        })
		        $("#operation", "#boiler_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900,
		            /* choosefun:function(elem, val, date) {
		            	$("#stopData").val(date);
		            }, */
		        })
		        /* 日期比较-开始  */
				function duibi(d1,d2)
				{
		  			//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		  			return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
				}
				/* 日期比较-结束 */
				
				var select2 = $("#governanceMeasures1", "#boiler_form")
						.select(
								{
									name : "governanceMeasures1",
									zIndex : 7,
									multselect:true,
								});
				var select2 = $("#governanceMeasures2", "#boiler_form")
						.select(
								{
									name : "governanceMeasures2",
									zIndex : 6,
									multselect:true,
								});
				var select2 = $("#governanceMeasures3", "#boiler_form")
						.select(
								{
									name : "governanceMeasures3",
									zIndex : 5,
									multselect:true,
								});
				var fuelType = $("#fuelType", "#boiler_form").select({
					name : "fuelType",
					zIndex : 8,
					defaultValue : '${boilerInformation.fuelType}',
					param:'${boilerInformation.boilerType}',
					"initLoad":true,
				});
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
											min:0
										},
										fuelAsh : {
											range : [ 0, 100 ],
											min:0
										},
										fuelVolatiles : {
											range : [ 0, 100 ],
										},
										operation : {
										},
										steamTon:{
											min:0
										},
										fuelConsumption:{
											min:0,
											required:true
										},
										"emissionsManagement.exhaustionHole.height":{
											required:true,
											min:0
										},
										fuelUnit:{
											checkUnit:/\D/,
											required:true
										},
										boilerType:{
											required:true
										},
										operation:{
											required:true
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
										description:{
											maxlength:255
										},
										status:{
											required:true
										},
										status:{
											required:true
										},
										purpose:{
											required:true
										},
										fuelYear:{
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
									},
									messages : {
										fuelSulfurContent : {
											range : "0~100!",
											min:"大于0"
										},
										fuelAsh : {
											range : "0~100!",
											min:"大于0"
										},
										fuelVolatiles : {
											range : "0~100!",
										},
										steamTon:{
											min:"大于0"
										},
										fuelConsumption:{
											min:"大于0"
										},
										"emissionsManagement.exhaustionHole.height":{
											min:"大于0"
										}
									},
									submitHandler : function(form) {
										//日期比较
										if(duibi($("#boiler_form #operation").val(),$("#boiler_form #stopData").val())){
											layer.msg('关停时间不能早于投运时间',{icon:3});
											return false;
										}
										if (!((fuelType.getSelectValue()
												&& $(
														"input[name=fuelConsumption]")
														.val() && $(
												"input[name=fuelUnit]").val()) || (!select4
												.getSelectValue()
												&& !$(
														"input[name=fuelConsumption]")
														.val() && !$(
												"input[name=fuelUnit]").val()))) {
											qy.suc3({
												title : '燃料信息、年消耗量、单位三项补充完整！'
											});
											return false;
										}
										var options = {
											url : base
													+ '/web/boilerinformation/generalupdate.jhtml',
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
	<form id="boiler_form" style="padding:0;width:100%;">
		<input type="hidden" name="id" value="${boilerInformation.id }" />
		<input type="hidden" name="emissionsManagement.exhaustionHole.id" value="${boilerInformation.emissionsManagement.exhaustionHole.id }" />
		<input type="hidden" name="emissionsManagement.governanceMeasures1.id" value="${boilerInformation.emissionsManagement.governanceMeasures1.id }" />
		<input type="hidden" name="emissionsManagement.governanceMeasures2.id" value="${boilerInformation.emissionsManagement.governanceMeasures2.id }" />
		<input type="hidden" name="emissionsManagement.governanceMeasures3.id" value="${boilerInformation.emissionsManagement.governanceMeasures3.id }" />
		<div class="infLine">

			<div class="infh1">基本信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉类型</label>
					<div class="infpCon">
						<div id="boilerType" data-code="锅炉类型" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉型号</label>
					<div class="infpCon">
						<input type="" class="input" name="typeOfBoiler" value="${boilerInformation.typeOfBoiler }" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">蒸吨数（t/h）</label>
					<div class="infpCon">
						<input type="" class="input" name="steamTon" value="${boilerInformation.steamTon }" number=true required> 
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉状态</label>
					<div class="infpCon">
						<div id="status" data-code="锅炉【锅炉状态】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉用途</label>
					<div class="infpCon">
						<div id="purpose" data-code="详表锅炉【用途】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="text" id="operation" name="operation" class="Yinput" value="<fmt:formatDate value='${boilerInformation.operation}' pattern='yyyy-MM-dd' />" onfocus="this.blur()">
						</div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="text" id="stopData" name="stopData" class="Yinput" value="<fmt:formatDate value='${boilerInformation.stopData}'  pattern='yyyy-MM-dd' />" readonly>
						</div>
					</div>
				</div>
			</div>

			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年份</label>
					<div class="infpCon">
						<div id="fuelYear" data-code="工段【年份】" class="selF"></div>
					</div>
				</div>
			</div>
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
					<label for="" class="infpLable">年/月消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" value="${boilerInformation.fuelConsumption }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelUnit" value="${boilerInformation.fuelUnit }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" value="${boilerInformation.fuelSulfurContent }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelAsh" value="${boilerInformation.fuelAsh }" number=true>
					</div>
				</div>
			</div>

			<div class="infh1">污染物治理措施（可多选）</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硫工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="简表【脱硫工艺】" data-defaultValue="${boilerInformation.emissionsManagement.governanceMeasures1.name }" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate1" name="putDate1" 
						value="<fmt:formatDate value='${boilerInformation.emissionsManagement.governanceMeasures1.putDate}' pattern='yyyy-MM' />"
						class="Yinput" >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">除尘工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="简表【除尘工艺】" data-defaultValue="${boilerInformation.emissionsManagement.governanceMeasures2.name }" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate2" name="putDate2"
						value="<fmt:formatDate value='${boilerInformation.emissionsManagement.governanceMeasures2.putDate}' pattern='yyyy-MM' />"
						 class="Yinput" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硝工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="简表【脱硝工艺】" data-defaultValue="${boilerInformation.emissionsManagement.governanceMeasures3.name }" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate3" name="putDate3"
						value="<fmt:formatDate value='${boilerInformation.emissionsManagement.governanceMeasures3.putDate}' pattern='yyyy-MM' />"
						 class="Yinput" >
					</div>
				</div>
			</div>

			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口高度（米）</label>
					<div class="infpCon">
						<input type="" class="input" name="emissionsManagement.exhaustionHole.height" value='${boilerInformation.emissionsManagement.exhaustionHole.height}' number=true>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${boilerInformation.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>