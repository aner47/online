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
<title>修改锅炉燃料消耗信息</title>
<style type="text/css">
#annualConsumption-error,#unit-error,#fuelType-error{
	top:0px;
}
.infParaCon .infpCon label.error{
	top:20px;
	width:70px;
}
</style>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel","date"  ],
			function(hide) {
				
				$("#stopData", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        })
		        $("#operation", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        })
		        /* 日期比较-开始  */
				function duibi(d1,d2)
				{
		  			//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		  			return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
				}
				/* 日期比较-结束 */
		        
				$("#no", "#boiler_form").select({name:"no",param:"id",defaultValue : "${singleboilerfuel.no}",zIndex:9,change:function(event, value){
					//fuelType.loadData(value);
					qy.ajax({
						url: base+"/web/singleboilerinformation/getFuelType.jhtml",
						data:{id:value},
						callBack:function(data){
							$("[name = 'fuelType']").val(data.entity.fuelType);
						}
					})
				}});
				$("#carType", "#boiler_form").select({filter: false,name:"carType",zIndex:8});
				$("#dischargeStandard", "#boiler_form").select({filter: false,name:"dischargeStandard",zIndex:7});
				$("#fuelType", "#boiler_form").select({filter: false,name:"fuelType",zIndex:6,isCustom : true});
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
										annualConsumption:{
											min:0
										},
										unit:{
											checkUnit:/\D/
										},
										description:{
											maxlength:255
										},
										num:{
											min:0,
										},
										avgDistance:{
											min:0
										},
										avgLoad:{
											min:0
										},

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
											url : 'update.jhtml',
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
	<form class="hideCls" id="boiler_form">
		<div class="dialog_div">
			<input type="hidden" name="id" value="${transportCar.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量</label>
					<div class="infpCon">
						<input type="" class="input" name="num"
						value="${transportCar.num}" 
						 >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">运输物料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="materialName"
						value="${transportCar.materialName}" 
						 >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">运输车辆类型</label>
					<div class="infpCon">
						<div id="carType" data-code="大宗物料运输车【运输车辆类型】" 
						data-defaultValue="${transportCar.carType}" 
						class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">运输车排放标准</label>
					<div class="infpCon">
						<div id="dischargeStandard" data-code="机动车调查【排放标准】" 
						data-defaultValue="${transportCar.dischargeStandard}" class="selF"></div>
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="大宗物料运输车【燃料类型】" 
						data-defaultValue="${transportCar.fuelType}"
						class="selF"></div>
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">运输车次（次/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="transportNumber"
						value="${transportCar.transportNumber}"
						number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均额定功率(kw)</label>
					<div class="infpCon">
						<input type="" class="input" name="ratedPower"
						value="${transportCar.ratedPower}"
						number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均运行功率(kw)</label>
					<div class="infpCon">
						<input type="" class="input" name="avgPower"
						value="${transportCar.avgPower}"
						 number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单车油耗量(L/km)</label>
					<div class="infpCon">
						<input type="" class="input" name="oilWear"
						value="${transportCar.oilWear}" 
						number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">主要来源地或目的地</label>
					<div class="infpCon">
						<input type="" class="input" name="source"
						value="${transportCar.source}"  >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均单次运距（km）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDistance" 
						value="${transportCar.avgDistance}" 
						number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单车额定载重(t)</label>
					<div class="infpCon">
						<input type="" class="input" name="ratedLoad"
						value="${transportCar.ratedLoad}"
						 number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单次平均载重（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgLoad" value="${transportCar.avgLoad}" number=true >
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${transportCar.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>