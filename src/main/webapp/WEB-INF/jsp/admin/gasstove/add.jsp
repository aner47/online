<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>增加煤气炉信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","localCache", "validate", "ajaxform", "select", "panel" ,"date" ,"year"],
			function(hide,localCache) {
				
				var putProductionYear = localCache.getCookie("putProductionYear");
				console.log(putProductionYear)
				
				var fuelYear = $('#boiler_form #fuelYear').year({'name':'fuelYear'});

				var projectYear = '${project.dataYear}';
				fuelYear.setYearValue(projectYear);
				var changeNames = ["fuelConsumption","fuelUnit","rateOfFlow","annualOutput"];
				
				var _changeNames = ["stopData"];
				
				
				
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
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900,
		        })
		        $("#abarbeitungTime", "#boiler_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900,
		        })
		        $("#operation", "#boiler_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900,
		        })
		        /* 日期比较-开始  */
				function duibi(d1,d2)
				{
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
		        
				var select1 = $("#boilerType", "#boiler_form").select({
					name : "boilerType",isCustom : true,filter: false,
					zIndex : 9,
					afterInit : function() {
						alert(select.getSelectValue());
					}
				});
				var select1 = $("#status", "#boiler_form").select({
					name: "status",filter: false,
					zIndex: 8,
					filter: false,
					change: function(data,value) {
					      required(value);
					}
				});
				var select1 = $("#purpose", "#boiler_form").select({
					name : "purpose",filter: false,
					zIndex : 7,
					filter: false
				});
				
				var select3 = $("#exhaustionHoleId", "#boiler_form").select({
					name : "exhaustionHoleId",
					param : "enterprise:enterprise,project:project"
				});
				var select4 = $("#fuelType", "#boiler_form").select({
					name : "fuelType",isCustom : true,
					zIndex : 8,
					filter: false,
					afterInit : function() {
						alert(select.getSelectValue());
					}
				});
				var select4 = $("#fuelUnit", "#boiler_form").select({
			  		name : "fuelUnit",isCustom : true,
			  		zIndex : 7,
			  		validate : 'required',
			  	});
				var oldAppliance = '';
				var appliance = $("#appliance", "#boiler_form").select({
					name : "appliance",isCustom : true,filter: false,
					 multselect: true,
					zIndex : 6,
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
										boilerType:{
											required:true
										},
										status:{
											required:true
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
										fuelConsumption:{
											min:0
										},
										fuelUnit:{
											checkUnit:/\D/
										},
										steamTon:{
											min:0,
											number:true
										},
										fuelType:{
											required:true
										},
										purpose:{
											required:true
										},
										fuelSulfurContent:{
											range:[0,100]
										},
										fuelAsh:{
											range:[0,100]
										},
										fuelVolatiles:{
											range:[0,100]
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
										exhaustionHoleId:{
											required:true
										},
										appliance:{
											required:true
										},
										description:{
											maxlength:255
										}
									},
									messages : {
										description:{
											maxlength:"最多255字"
										}
									},
									submitHandler : function(form) {
										//日期比较
										if(duibi($("#boiler_form #operation").val(),$("#boiler_form #stopData").val())){
											layer.msg('关停时间不能早于投运时间',{icon:3});
											return false;
										}
										if(duibi($("#boiler_form #operation").val(),$("#boiler_form #abarbeitungTime").val())){
											layer.msg('整改时间不能早于投运时间',{icon:3});
											return false;
										}
										
										if (putProductionYear > $("#boiler_form #operation").val()) {
											layer.msg('投运时间不能早于开业时间',{icon:3});
											return false;
										}
										
										var month = [];
										$("[name=productionMonthTemp]:checked").each(function(i, v) {
											month.push($(v).val())
										});
										$("[name=productionMonth]").val(month.join(","));
										
										/* if (!((select4.getSelectValue()
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
										} */
										var options = {
											url : base
													+ '/web/gasstove/save.jhtml',
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
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form class="hideCls" id="boiler_form" style="padding: 0; width: 100%;">
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
						<input type="" class="input" name="diameter">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">煤气出口温度(℃)</label>
					<div class="infpCon">
						<input type="" class="input" name="outTemperature">
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
							onfocus="this.blur()" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
						<input type="" class="input" id="abarbeitungTime"
							name="abarbeitungTime" onfocus="this.blur()" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopData" name="stopData"
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
									name="startManufacture" onfocus="this.blur()" />
							</div>
							<div class="inputChild col6">
								<input type="text" class="input" id="endManufacture"
									name="endManufacture" placeholder="" onfocus="this.blur()" />
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
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗总量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" number=true
						>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="fuelUnit" required> -->
						<div class="selF" id="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelAsh" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料挥发分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelVolatiles" number=true>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>煤气流量(标立方米/小时)</label>
					<div class="infpCon">
						<input type="" class="input" name="rateOfFlow" number=true required="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>煤气年产量(万立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="annualOutput" number=true required="true">
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
						<input type="" class="input" name="applianceRemovalRate" number=true>
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
						<textarea class="textarea" name="description"></textarea>
					</div>
				</div>
			</div>

		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>