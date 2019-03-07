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
<title>修改锅炉信息</title>
</head>

<script type="text/javascript">
	require(
			["hide", "validate", "ajaxform", "select", "panel","date","year"  ],
			function(hide) {
				
				var fuelYear = $('#boiler_form #fuelYear').year({'name':'fuelYear',defaultValue:'${boilerInformation.fuelYear}'});
				
				var changeNames = ["fuelConsumption","fuelUnit"];
				var _changeNames = ["stopData"];
				$("#status","#boiler_form").select({
					name:"status",
					zIndex:9,
					defaultValue:'${boilerInformation.status}',
					filter: false,
					change:function(data,value){
						required(value);
				}});
				required('${boilerInformation.status}');
				
				function required(value){
					for(var i = 0; i < changeNames.length; i++){
						if(value == '备用' || value == '停用'){
							$('[name='+changeNames[i]+']', "#boiler_form").removeAttr('required');
						}else{
							$('[name='+changeNames[i]+']', "#boiler_form").attr('required','true');
						}
						
						if (value === '停用') {
							$('[name='+ _changeNames[i]+']', "#boiler_form").attr('required','true');
						}else{
							$('[name='+ _changeNames[i]+']', "#boiler_form").removeAttr('required');
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
		        
		        
				
				$("#boilerType", "#boiler_form").select({
					name : "boilerType",
					zIndex : 10,
					filter: false,
		  			isCustom : true,
					defaultValue : '${boilerInformation.boilerType}'
				});
		       
				
				$("#purpose", "#boiler_form").select({
					name : "purpose",
					zIndex : 7,
					filter: false,
					defaultValue : '${boilerInformation.purpose}'
				});
				
				var defaultgovernanceMeasures1 = '${boilerInformation.emissionsManagement.governanceMeasures1.id}'?'${boilerInformation.emissionsManagement.governanceMeasures1.id}':-1;
				var defaultgovernanceMeasures2 = '${boilerInformation.emissionsManagement.governanceMeasures2.id}'?'${boilerInformation.emissionsManagement.governanceMeasures2.id}':-1;
				var defaultgovernanceMeasures3 = '${boilerInformation.emissionsManagement.governanceMeasures3.id}'?'${boilerInformation.emissionsManagement.governanceMeasures3.id}':-1;
				var defaultgovernanceMeasures4 = '${boilerInformation.emissionsManagement.governanceMeasures4.id}'?'${boilerInformation.emissionsManagement.governanceMeasures4.id}':-1;
				
				
				$("#governanceMeasures1", "#boiler_form")
						.select(
								{
									name : "governanceMeasures1",
									defaultValue : defaultgovernanceMeasures1,
									zIndex : 7,
									param : "enterprise:enterprise,project:project"
								});
				$("#governanceMeasures2", "#boiler_form")
						.select(
								{
									name : "governanceMeasures2",
									defaultValue : defaultgovernanceMeasures2,
									zIndex : 6,
									param : "enterprise:enterprise,project:project"
								});
				$("#governanceMeasures3", "#boiler_form")
						.select(
								{
									name : "governanceMeasures3",
									defaultValue : defaultgovernanceMeasures3,
									zIndex : 5,
									param : "enterprise:enterprise,project:project"
								});
				$("#governanceMeasures4", "#boiler_form")
						.select(
								{
									name : "governanceMeasures4",
									defaultValue : defaultgovernanceMeasures4,
									zIndex : 5,
									param : "enterprise:enterprise,project:project"
								});
				$("#exhaustionHoleId", "#boiler_form")
						.select(
								{
									name : "exhaustionHoleId",
									defaultValue : '${boilerInformation.emissionsManagement.exhaustionHole.id}',
									param : "enterprise:enterprise,project:project"
								});
				$("#fuelType", "#boiler_form").select({
					name : "fuelType",isCustom : true,
					zIndex : 8,
					defaultValue : '${boilerInformation.fuelType}'
				});
				$("#fuelUnit", "#boiler_form").select({
			  		name : "fuelUnit",isCustom : true,
			  		zIndex : 7,
			  		validate : 'required',
			  		defaultValue : '${boilerInformation.fuelUnit}'
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
										operation : {
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
										governanceMeasures4:{
											required:true
										},
										exhaustionHoleId:{
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
										if(duibi($("#boiler_form #operation").val(), $("#boiler_form #stopData").val())){
											layer.msg('关停时间不能早于投运时间',{icon:3});
											return false;
										}
										if(duibi($("#boiler_form #operation").val(), $("#boiler_form #abarbeitungTime").val())){
											layer.msg('整改时间不能早于投运时间',{icon:3});
											return false;
										}
										
										var month = [];
										$("[name=productionMonthTemp]:checked").each(function(i, v) {
											month.push($(v).val())
										});
										$("[name=productionMonth]").val(month.join(","));
										
										
										
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
	<form class="hideCls" id="boiler_form" style="padding:0;width:100%;">
		<input type="hidden" name="id" value="${boilerInformation.id }" />
		<input type="hidden" name="no" value="${boilerInformation.no }" />
		<div class="infLine">

			<div class="infh1">工艺描述</div>

			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉类型</label>
					<div class="infpCon">
						<div id="boilerType" data-code="锅炉类型" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉铭牌型号</label>
					<div class="infpCon">
						<input type="" class="input" name="typeOfBoiler" value="${boilerInformation.typeOfBoiler }" required="true">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉蒸吨(t/h)</label>
					<div class="infpCon">
						<input type="" class="input" name="steamTon" value="${boilerInformation.steamTon }" number=true required/>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉用途</label>
					<div class="infpCon">
						<div id="purpose" data-code="详表锅炉【用途】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉使用状态</label>
					<div class="infpCon">
						<div id="status" data-code="锅炉【锅炉状态】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>投运时间</label>
					<div class="infpCon">
						<input type="" class="input" id="operation" name="operation" value="<fmt:formatDate value='${boilerInformation.operation}' pattern='yyyy-MM-dd' />" onfocus="this.blur()" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
						<input type="" class="input" id="abarbeitungTime" name="abarbeitungTime"
						 onfocus="this.blur()" value="<fmt:formatDate value='${boilerInformation.abarbeitungTime}' pattern='yyyy-MM-dd' />" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopData" name="stopData" value="<fmt:formatDate value='${boilerInformation.stopData}' pattern='yyyy-MM-dd' />" onfocus="this.blur()">
					</div>
				</div>
			</div>
			
				<div class="infPara infPara2">
						<input type="hidden" name="productionMonth" value='${boilerInformation.productionMonth}'>
						<div class="infParaCon">
						<div class="dialog_title_left">运行月份</div>
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
								<input type="text" class="input" id="startManufacture" name="startManufacture" 
								value="<fmt:formatDate value="${boilerInformation.startManufacture}" pattern="HH:mm:ss"/>" placeholder="" onfocus="this.blur()"/>
							</div>
							<div class="inputChild col6">
								<input type="text" class="input" id="endManufacture" name="endManufacture"  placeholder="" 
								value="<fmt:formatDate value="${boilerInformation.endManufacture}" pattern="HH:mm:ss"/>" placeholder="" onfocus="this.blur()"/>
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
						<div id="fuelType" data-code="燃料类型" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗总量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" value="${boilerInformation.fuelConsumption }" number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="fuelUnit" value="${boilerInformation.fuelUnit }" required> --%>
						<div class="selF" id="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" value="${boilerInformation.fuelSulfurContent }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelAsh" value="${boilerInformation.fuelAsh }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料挥发分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelVolatiles" value="${boilerInformation.fuelVolatiles }" number=true>
					</div>
				</div>
			</div>
			
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
						<textarea class="textarea" name="description">${boilerInformation.description }</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>