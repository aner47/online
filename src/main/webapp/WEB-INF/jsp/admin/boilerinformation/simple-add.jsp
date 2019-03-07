<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<meta charset="UTF-8">
<title>增加锅炉信息</title>
</head>

<script type="text/javascript">
	require(
			["hide", "validate", "ajaxform", "select", "panel","date" ],
			function(hide) {
				
				$("#status","#boiler_form").select({name:"status", zIndex:10,
		        	change:function(event, value){
						show(value);
					}
		        });
				 
				 function show(value){
					 if(value === '备用' || value === '停用'){
						$("#year_consume").removeAttr("required");
						$("#consume_unit").removeAttr("required");
						
					} else {
						$("#year_consume").attr("required", true);
						$("#consume_unit").attr("required", true);
					}
					if (value === '停用') {
						 $("#stopData", "#stopData_wrap").attr("required", true);
						 console.log(111111)
					 } else {
						 $("#stopData", "#stopData_wrap").removeAttr("required");
						 console.log(2222222222)
					 }
				 }
				
		       $("#stopData", "#boiler_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900,
		        })
		        $("#operation", "#boiler_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900,
		        })
		        $("#abarbeitungTime", "#boiler_form").jeDate({
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
		        
				var select1 = $("#boilerType", "#boiler_form").select({
					name : "boilerType",isCustom : true,
					zIndex : 10,
					afterInit : function() {
						alert(select.getSelectValue());
					}
				});
				
				$("#status","#boiler_form").select({name:"status",zIndex:9});
				$("#purpose","#boiler_form").select({name:"purpose",zIndex:8});
				var select2 = $("#governanceMeasures1", "#boiler_form").select(
						{
							name : "governanceMeasures1",isCustom : true,
							zIndex : 7,
							multselect:true,
							
						});
				var select2 = $("#governanceMeasures2", "#boiler_form").select(
						{
							name : "governanceMeasures2",isCustom : true,
							zIndex : 6,
							multselect:true,
							
						});
				var select2 = $("#governanceMeasures3", "#boiler_form").select(
						{
							name : "governanceMeasures3",isCustom : true,
							zIndex : 5,
							multselect:true,
							
						});
				var select4 = $("#fuelType", "#boiler_form").select({
					name : "fuelType",isCustom : true,
					zIndex : 8,
					afterInit : function() {
						alert(select.getSelectValue());
					}
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
										steamTon:{
											min:0
										},
										fuelConsumption:{
											min:0,
										},
										"emissionsManagement.exhaustionHole.height":{
											required:true,
											min:0
										},
										fuelUnit:{
											checkUnit:/\D/,
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
										status:{
											required:true
										},
										purpose:{
											required:true
										},
										description:{
											maxlength:255
										}
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
										if(duibi($("#boiler_form #operation").val(), $("#boiler_form #stopData").val())){
											layer.msg('关停时间不能早于投运时间', {icon:3});
											return false;
										}
										if(duibi($("#boiler_form #operation").val(), $("#boiler_form #abarbeitungTime").val())){
											layer.msg('整改时间不能早于投运时间', {icon:3});
											return false;
										}
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
													+ '/web/boilerinformation/simplesave.jhtml',
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
	<form id="boiler_form" style="padding:0;width:100%;">
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
						<input type="" class="input" name="typeOfBoiler">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">蒸吨数（t/h）</label>
					<div class="infpCon">
						<input type="" class="input" name="steamTon" number=true required/>
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
					<label for="" class="infpLable">使用状态</label>
					<div class="infpCon">
						<div id="status" data-code="锅炉【锅炉状态】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="" id="operation" name="operation" class="Yinput" onfocus="this.blur()">
						</div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
					<div class="yearF iconfont icon-nianfen">
						<input type="" class="Yinput" id="abarbeitungTime" name="abarbeitungTime" onfocus="this.blur()" >
					</div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div id="stopData_wrap" class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="" id="stopData" name="stopData" class="Yinput"/>
						</div>
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
					<label for="" class="infpLable">年消耗总量</label>
					<div class="infpCon">
						<input type="" id="year_consume" class="input" name="fuelConsumption" number=true/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<input type="" id="consume_unit" class="input" name="fuelUnit">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelAsh" number=true>
					</div>
				</div>
			</div>

			<div class="infh1">污染物治理措施（可多选）</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硫工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="简表【脱硫工艺】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硝工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="简表【脱硝工艺】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">除尘工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="简表【除尘工艺】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口高度（米）</label>
					<div class="infpCon">
						<input type="" class="input" name="emissionsManagement.exhaustionHole.height" number=true>
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