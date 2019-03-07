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
			["hide", "validate", "ajaxform", "select", "panel","date"  ],
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
				//燃料类型
				//$("#fuelType", "#boiler_form").select({name:"fuelType",defaultValue : "${singleboilerfuel.fuelType}",zIndex:7});
				
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
										no:{
											required:true
										},
										annualConsumption:{
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
										var annualConsumption=Number($("input[name=annualConsumption]").val());
										var monthlySum=january+february+march+april+may+june+july+august+september+october+november+december;
										if(monthlySum > annualConsumption){
											layer.msg('错误：月消耗量总和大于年总消耗量',{icon:3});
											return false;
										}
										//年消耗量与分月消耗量作比较-end
										var options = {
											url : base
													+ '/web/singleboilerfuel/update.jhtml',
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
	<form id="boiler_form">
		<div class="dialog_div">
			<input type="hidden" name="id" value="${singleboilerfuel.id }" />
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉编号</label>
					<div class="infpCon">
						<div id="no" data-code="单独锅炉【锅炉编号】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
					<input type="" class="input" name="fuelType" placeholder="燃料类型" value="${singleboilerfuel.fuelType}" number=true required readonly="readonly">
						<!-- <div id="fuelType" data-code="单独锅炉【燃料类型】" class="selF"></div> -->
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="annualConsumption" value="${singleboilerfuel.annualConsumption}" placeholder="年消耗量" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="unit" value="${singleboilerfuel.unit}" placeholder="单位" required >
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">一月</label>
					<div class="infpCon">
						<input type="" class="input" name="january" value="${singleboilerfuel.january}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">二月</label>
					<div class="infpCon">
						<input type="" class="input" name="february" value="${singleboilerfuel.february}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">三月</label>
					<div class="infpCon">
						<input type="" class="input" name="march" value="${singleboilerfuel.march}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">四月</label>
					<div class="infpCon">
						<input type="" class="input" name="april" value="${singleboilerfuel.april}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">五月</label>
					<div class="infpCon">
						<input type="" class="input" name="may" value="${singleboilerfuel.may}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">六月</label>
					<div class="infpCon">
						<input type="" class="input" name="june" value="${singleboilerfuel.june}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">七月</label>
					<div class="infpCon">
						<input type="" class="input" name="july" value="${singleboilerfuel.july}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">八月</label>
					<div class="infpCon">
						<input type="" class="input" name="august" value="${singleboilerfuel.august}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">九月</label>
					<div class="infpCon">
						<input type="" class="input" name="september" value="${singleboilerfuel.september}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">十月</label>
					<div class="infpCon">
						<input type="" class="input" name="october" value="${singleboilerfuel.october}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">十一月</label>
					<div class="infpCon">
						<input type="" class="input" name="november" value="${singleboilerfuel.november}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">十二月</label>
					<div class="infpCon">
						<input type="" class="input" name="december" value="${singleboilerfuel.december}" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${singleboilerfuel.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>