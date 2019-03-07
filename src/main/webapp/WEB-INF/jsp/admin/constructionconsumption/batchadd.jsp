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
<title>添加机械消耗信息</title>
<style type="text/css">
	table{
		width:100%;
	}
	td{
		width:20%;
		height:30px;
		border:1px solid #ccc;
		text-align:center;
		position:relative;
	}
	input{
		border:none;
		width:100%;
		height:30px;
	}
	.infPara{
		padding:20px 0px;
	}
	td label.error{
		position:absolute;
		left:70px;
		top:5px;
		width:80px;
	}
	div{
		border:none;
	}
</style>
</head>

<script type="text/javascript">
	require(
			[ "validate", "ajaxform", "select", "panel" ,"date" ],
			function(a) {
				console.log('add');
				
				var unit1 = $("#excavator3", "#boiler_form").select({name:"excavator3",zIndex:9});
				var unit2 = $("#loader3", "#boiler_form").select({name:"loader3",zIndex:8});
				var unit3 = $("#bulldozer3", "#boiler_form").select({name:"bulldozer3",zIndex:7});
				var unit4 = $("#scraper3", "#boiler_form").select({name:"scraper3",zIndex:6});
				var unit5 = $("#grader3", "#boiler_form").select({name:"grader3",zIndex:5});
				var unit6 = $("#hoistingMachinery3", "#boiler_form").select({name:"hoistingMachinery3",zIndex:4});
				var unit7 = $("#pileDrivingMachinery3", "#boiler_form").select({name:"pileDrivingMachinery3",zIndex:3});
				var unit8 = $("#engineeringVehicles3", "#boiler_form").select({name:"engineeringVehicles3",zIndex:2});
				var unit9 = $("#forkLiftTruck3", "#boiler_form").select({name:"forkLiftTruck3",zIndex:1});
				
				var select1 = $("#excavator4", "#boiler_form").select({name:"excavator4",zIndex:9});
				var select2 =$("#loader4", "#boiler_form").select({name:"loader4",zIndex:8});
				var select3 =$("#bulldozer4", "#boiler_form").select({name:"bulldozer4",zIndex:7});
				var select4 =$("#scraper4", "#boiler_form").select({name:"scraper4",zIndex:6});
				var select5 =$("#grader4", "#boiler_form").select({name:"grader4",zIndex:5});
				var select6 =$("#hoistingMachinery4", "#boiler_form").select({name:"hoistingMachinery4",zIndex:4});
				var select7 =$("#pileDrivingMachinery4", "#boiler_form").select({name:"pileDrivingMachinery4",zIndex:3});
				var select8 =$("#engineeringVehicles4", "#boiler_form").select({name:"engineeringVehicles4",zIndex:2});
				var select9 =$("#forkLiftTruck4", "#boiler_form").select({name:"forkLiftTruck4",zIndex:1});
				
				//提交表单
				$("#boiler_form")
						.validate(
								{
									rules : {
										excavator1:{
											min:0
										},
										excavator2:{
											min:0
										},
										loader1:{
											min:0
										},
										loader2:{
											min:0
										},
										bulldozer1:{
											min:0
										},
										bulldozer2:{
											min:0
										},
										scraper1:{
											min:0
										},
										scraper2:{
											min:0
										},
										grader1:{
											min:0
										},
										grader2:{
											min:0
										},
										hoistingMachinery1:{
											min:0
										},
										hoistingMachinery2:{
											min:0
										},
										pileDrivingMachinery1:{
											min:0
										},
										pileDrivingMachinery2:{
											min:0
										},
										engineeringVehicles1:{
											min:0
										},
										engineeringVehicles2:{
											min:0
										},
										forkLiftTruck1:{
											min:0
										},
										forkLiftTruck2:{
											min:0
										},
										description:{
											maxlength:255
										}
									},
									messages : {
									},
									submitHandler : function(form) {							
										//批量添加数据--start
										//设备种类数据--start
										var speciesValue=[];
										$(".species").each(function(i){
											speciesValue[i]=$(this).html();
										})
										//设备种类数据--end
										//机械平均功率（千瓦/辆）数据--start
										var inputOddValue=[];
										$("td:nth-child(2) input[id]").each(function(i){
											inputOddValue[i]=$(this).val()?$(this).val():" ";
										})
										//机械平均功率（千瓦/辆）数据--end
										//单车油耗量（吨/辆/年）数据--start
										var inputEvenValue=[];
										$("td:nth-child(3) input[id]").each(function(i){
											inputEvenValue[i]=$(this).val()?$(this).val():" ";
										})
										//单车油耗量（吨/辆/年）数据--end
										//油品标准下拉框数据--start
										var unitValue=[];
										for(var i=0;i<$(".selF").length;i++){
											unitValue[i]=eval("unit"+(i+1)).getSelectValue()?eval("unit"+(i+1)).getSelectValue():" ";
										}
										//油品标准下拉框数据--end
										//油品标准下拉框数据--start
										var selectValue=[];
										for(var i=0;i<$(".selF").length;i++){
											selectValue[i]=eval("select"+(i+1)).getSelectValue()?eval("select"+(i+1)).getSelectValue():" ";
										}
										//油品标准下拉框数据--end
										//全部数据汇总--start										
										var allValue=[];
										for(var i=0;i<$(".selF").length;i++){
											allValue[i]=speciesValue[i]+"-"+inputOddValue[i]+"-"+inputEvenValue[i]+"-"+unitValue[i]+"-"+selectValue[i];
										}
										console.log(allValue)
										//全部数据汇总--end
										//批量添加数据--end
										var options = {
											url : base+ '/web/constructionconsumption/batchsave.jhtml',
											type : 'post',
											dataType : 'json',
											data:{
												allValue:allValue
											},
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
			});
</script>

<body>
	<form id="boiler_form">
		<div class="dialog_div">
			
				<table>
				<tr>
					<td><b>施工机械类型</b></td>
					<td><b>机械平均功率（千瓦/辆）</b></td>
					<td><b>单车油耗量 </b></td>
					<td><b>单位 </b></td>
					<td><b>油品标准</b></td>
				</tr>
				<tr>
					<td class="species">挖掘机</td>
					<td><input id="excavator1" type="" name="excavator1" number="true" /></td>
					<td><input id="excavator2" type="" name="excavator2" number="true" /></td>
					<td><div id="excavator3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="excavator4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">装载机</td>
					<td><input id="loader1" type="" name="loader1" number="true" /></td>
					<td><input id="loader2" type="" name="loader2" number="true" /></td>
					<td><div id="loader3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="loader4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">推土机</td>
					<td><input id="bulldozer1" type="" name="bulldozer1" number="true"></td>
					<td><input id="bulldozer2" type="" name="bulldozer2" number="true"></td>
					<td><div id="bulldozer3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="bulldozer4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">铲运机</td>
					<td><input id="scraper1" type="" name="scraper1" number="true"></td>
					<td><input id="scraper2" type="" name="scraper2" number="true"></td>
					<td><div id="scraper3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="scraper4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">平地机</td>
					<td><input id="grader1" type="" name="grader1" number="true"></td>
					<td><input id="grader2" type="" name="grader2" number="true"></td>
					<td><div id="grader3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="grader4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">起重机械</td>
					<td><input id="hoistingMachinery1" type="" name="hoistingMachinery1" number="true"></td>
					<td><input id="hoistingMachinery2" type="" name="hoistingMachinery2" number="true"></td>
					<td><div id="hoistingMachinery3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="hoistingMachinery4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">桩工机械</td>
					<td><input id="pileDrivingMachinery1" type="" name="pileDrivingMachinery1" number="true"></td>
					<td><input id="pileDrivingMachinery2" type="" name="pileDrivingMachinery2" number="true"></td>
					<td><div id="pileDrivingMachinery3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="pileDrivingMachinery4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">工程车辆</td>
					<td><input id="engineeringVehicles1" type="" name="engineeringVehicles1" number="true"></td>
					<td><input id="engineeringVehicles2" type="" name="engineeringVehicles2" number="true"></td>
					<td><div id="engineeringVehicles3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="engineeringVehicles4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
				<tr>
					<td class="species">叉车</td>
					<td><input id="forkLiftTruck1" type="" name="forkLiftTruck1" number="true"></td>
					<td><input id="forkLiftTruck2" type="" name="forkLiftTruck2" number="true"></td>
					<td><div id="forkLiftTruck3" data-code="施工工地【单车油耗量单位】" class="selF3" style="border:none;"></div></td>
					<td><div id="forkLiftTruck4" data-code="施工工地【油品标准】" class="selF" style="border:none;"></div></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>