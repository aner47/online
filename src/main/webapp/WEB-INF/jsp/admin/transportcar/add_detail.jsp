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
<title>增加运输车信息</title>
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
		        
				$("#no", "#boiler_form").select({name:"no",param:"id",zIndex:9,change:function(event, value){
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
										//年消耗量与分月消耗量作比较
										/* var january=Number($("input[name=january]").val());
										var february=Number($("input[name=february]").val());
										var march=Number($("input[name=march]").val());
										var april=Number($("input[name=april]").val());
										var may=Number($("input[name=may]").val());
										var june=Number($("input[name=june]").val());
										var july=Number($("input[name=july]").val());
										var august=Number($("input[name=august]").val());
										var september=Number($("input[name=september]").val());
										var october=Number($("input[name=october]").val());
										var november=Number($("input[name=november]").val());
										var december=Number($("input[name=december]").val());
										var annualConsumption=Number($("input[name=annualConsumption]").val());
										var monthlySum=january+february+march+april+may+june+july+august+september+october+november+december;
										if(monthlySum > annualConsumption){
											layer.msg('错误：月消耗量总和大于年总消耗量',{icon:3});
											return false;
										} */
										//年消耗量与分月消耗量作比较-end
										var options = {
											url : base
													+ '/web/transportcar/save.jhtml',
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
					<label for="" class="infpLable">数量</label>
					<div class="infpCon">
						<input type="" class="input" name="num"
						 >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">运输物料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="materialName"  />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">运输车辆类型</label>
					<div class="infpCon">
						<div id="carType" data-code="大宗物料运输车【运输车辆类型】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">运输车排放标准</label>
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
					<label for="" class="infpLable">运输车次（次/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="transportNumber" number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均额定功率(kw)</label>
					<div class="infpCon">
						<input type="" class="input" name="ratedPower" number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均运行功率(kw)</label>
					<div class="infpCon">
						<input type="" class="input" name="avgPower" number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单车油耗量(升/公里)</label>
					<div class="infpCon">
						<input type="" class="input" name="oilWear" number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">主要来源地或目的地</label>
					<div class="infpCon">
						<input type="" class="input" name="source"  >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均单次运距（km）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDistance" number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单车额定载重(t)</label>
					<div class="infpCon">
						<input type="" class="input" name="ratedLoad" number=true >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单次平均载重（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgLoad" number=true >
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