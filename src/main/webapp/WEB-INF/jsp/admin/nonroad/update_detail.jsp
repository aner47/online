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
<title>修改非道路机械信息</title>
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
				$("#mechanicalType", "#boiler_form").select({name:"mechanicalType",zIndex:11,initAfter:function(){
					nonroadMechanical.loadData('${nonRoad.mechanicalType}');
				},change:function(event, value){
					nonroadMechanical.loadData(value);
				}});
				var nonroadMechanical = $("#nonroadMechanical", "#boiler_form").select({filter: false,initLoad:false,name:"nonroadMechanical",zIndex:10,isCustom : true});
				$("#ownOrLease", "#boiler_form").select({filter: false,name:"ownOrLease",zIndex:9});
				$("#dischargeStandard", "#boiler_form").select({filter: false,name:"dischargeStandard",zIndex:8});
				$("#fuelType", "#boiler_form").select({filter: false,name:"fuelType",zIndex:7,isCustom : true});
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form")
				.validate(
						{
					rules : {
						
						description:{
							maxlength:255
						},
						
						num:{
							min:0
						},
						avgPower:{
							min:0
						},
						singleOilConsumption:{
							min:0
						},

					},
					messages : {
					},
					submitHandler : function(form) {
						
						var options = {
							url :'update.jhtml',
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
			<input type="hidden" name="id" value="${nonRoad.id }" />
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械类型</label>
					<div class="infpCon">
						<div id="mechanicalType" data-code="非道路【机械类型】" data-defaultValue="${nonRoad.mechanicalType}" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">非道路机械</label>
					<div class="infpCon">
						<div id="nonroadMechanical" data-code="普查表【非道路机械】" data-defaultValue="${nonRoad.nonroadMechanical}" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">自有或租赁</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="ownOrLease"
						value="${nonRoad.ownOrLease}"
						 > --%>
						 <div id="ownOrLease" data-defaultValue="${nonRoad.ownOrLease}"
						 data-code="机动车调查【自有或租赁】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械排放标准</label>
					<div class="infpCon">
						<div id="dischargeStandard" data-defaultValue="${nonRoad.dischargeStandard}"
						 data-code="机动车调查【排放标准】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="大宗物料运输车【燃料类型】"
						data-defaultValue="${nonRoad.fuelType}"
						 class="selF"></div>
					</div>
				</div>
			</div>
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量(台)</label>
					<div class="infpCon">
						<input type="" class="input" name="num" value="${nonRoad.num}"
						  number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均额定功率(kw)</label>
					<div class="infpCon">
						<input type="" class="input" name="ratedPower" 
						value="${nonRoad.ratedPower}"
						number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均运行功率（kw）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgPower" value="${nonRoad.avgPower}" 
						 number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单机油耗量（L/h）</label>
					<div class="infpCon">
						<input type="" class="input" name="singleOilConsumption" 
						value="${nonRoad.singleOilConsumption}" number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单机油耗花费（元/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="oilWearExpenditure"
						value="${nonRoad.oilWearExpenditure}"
						  number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃油来源地</label>
					<div class="infpCon">
						<input type="" class="input" name="oilSource" 
						value="${nonRoad.oilSource}" >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">日均工作小时(小时)</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDayofhourWork" 
						value="${nonRoad.avgDayofhourWork}"
						 number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年均工作天数(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="avgYearofdayWork"
						value="${nonRoad.avgYearofdayWork}"
						  number=true >
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${nonRoad.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>