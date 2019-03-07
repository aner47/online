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
<title>添加有机溶剂使用情况</title>
<style type="text/css">
#vocsContent-error,#vocsVolatility-error,#annual-error,#solventType-error,#solventNature-error,#unit-error{
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
				
				$("#solventType", "#boiler_form").select({name:"solventType",zIndex:9});
				$("#solventNature", "#boiler_form").select({name:"solventNature",zIndex:8});
				
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
										solventType:{
											required:true
										},
										solventNature:{
											required:true
										},
										vocsContent:{
											range:[0,100]
										},
										vocsVolatility:{
											range:[0,100]
										},
										annual:{
											min:0
										},
										unit:{
											checkUnit:/\D/
										},
										january:{
											min:0
										},
										february:{
											min:0
										},
										march:{
											min:0
										},
										april:{
											min:0
										},
										may:{
											min:0
										},
										june:{
											min:0
										},
										july:{
											min:0
										},
										august:{
											min:0
										},
										september:{
											min:0
										},
										october:{
											min:0
										},
										november:{
											min:0
										},
										december:{
											min:0
										},
										description:{
											maxlength:255
										}

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
										var january=Number($("input[name=january]").val());
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
										var usageAmount=Number($("input[name=annual]").val());
										var monthlySum=january+february+march+april+may+june+july+august+september+october+november+december;
										console.log("总和"+new Number(monthlySum).toFixed(2));
										console.log(new Number(usageAmount).toFixed(2));
										if(parseFloat(new Number(monthlySum).toFixed(2)) > parseFloat(new Number(usageAmount).toFixed(2))){
											layer.msg('错误：月消耗量总和大于年用量',{icon:3});
											return false;
										}
										//年消耗量与分月消耗量作比较-end
										var options = {
											url : base
													+ '/web/breakdownservicesolvent/save.jhtml',
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
	<form id="boiler_form">
		<div class="dialog_div">
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类型</label>
					<div class="infpCon">
						<div id="solventType" data-code="汽修【溶剂类型】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<div id="solventNature" data-code="汽修【溶剂性质】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs含量（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="vocsContent" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs挥发度（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="vocsVolatility" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年用量</label>
					<div class="infpCon">
						<input type="" class="input" name="annual" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="unit" required>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">一月</label>
					<div class="infpCon">
						<input type="" class="input" name="january" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">二月</label>
					<div class="infpCon">
						<input type="" class="input" name="february" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">三月</label>
					<div class="infpCon">
						<input type="" class="input" name="march" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">四月</label>
					<div class="infpCon">
						<input type="" class="input" name="april" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">五月</label>
					<div class="infpCon">
						<input type="" class="input" name="may" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">六月</label>
					<div class="infpCon">
						<input type="" class="input" name="june" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">七月</label>
					<div class="infpCon">
						<input type="" class="input" name="july" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">八月</label>
					<div class="infpCon">
						<input type="" class="input" name="august" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">九月</label>
					<div class="infpCon">
						<input type="" class="input" name="september" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">十月</label>
					<div class="infpCon">
						<input type="" class="input" name="october" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">十一月</label>
					<div class="infpCon">
						<input type="" class="input" name="november" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">十二月</label>
					<div class="infpCon">
						<input type="" class="input" name="december" number="true">
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
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>