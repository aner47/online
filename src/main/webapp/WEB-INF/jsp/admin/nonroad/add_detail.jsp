<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>增加非道路机械信息</title>
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
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
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
		        
				$("#mechanicalType", "#boiler_form").select({name:"mechanicalType",zIndex:11,change:function(event, value){
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
										
										annualConsumption:{
											min:0
										},
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
										
										//年消耗量与分月消耗量作比较-end
										var options = {
											url : base
													+ '/web/nonroad/save.jhtml',
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
	<form class="hideCls" id="boiler_form">
		<div class="dialog_div">
		
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械类型</label>
					<div class="infpCon">
						<div id="mechanicalType" data-code="非道路【机械类型】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">非道路机械</label>
					<div class="infpCon">
						<div id="nonroadMechanical" data-code="普查表【非道路机械】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">自有或租赁</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="ownOrLease" /> -->
						<div id="ownOrLease" 
						 data-code="机动车调查【自有或租赁】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械排放标准</label>
					<div class="infpCon">
						<div id="dischargeStandard" data-code="机动车调查【排放标准】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="大宗物料运输车【燃料类型】" class="selF"></div>
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量(台)</label>
					<div class="infpCon">
						<input type="" class="input" name="num" number=true />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均额定功率(kw)</label>
					<div class="infpCon">
						<input type="" class="input" name="ratedPower" number=true />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均运行功率（kw）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgPower" number=true />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单机油耗量（L/h）</label>
					<div class="infpCon">
						<input type="" class="input" name="singleOilConsumption" number=true />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单机油耗花费（元/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="oilWearExpenditure" number=true />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃油来源地</label>
					<div class="infpCon">
						<input type="" class="input" name="oilSource" />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">日均工作小时(小时)</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDayofhourWork" 
						 number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年均工作天数(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="avgYearofdayWork"
						  number=true >
					</div>
				</div>
			</div>
			
			
			
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
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>