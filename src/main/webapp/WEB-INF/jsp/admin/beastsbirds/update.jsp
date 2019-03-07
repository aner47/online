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
<title>修改畜禽养殖</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel","date"  ],
			function(hide) {
				
		        
				$("#kind", "#boiler_form").select({name:"kind",isCustom:true,defaultValue : "${beastsBirds.kind}",zIndex:9});
				
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
										operation : {
										},
										feedingNum:{
											min:0
										},
										saveNum:{
											min:0
										},
										outNum:{
											min:0
										},
										feedingPeriod:{
											min:0
										},
										feedingIndoorProportion:{
											range:[0,100]
										},
										fodderProportion:{
											range:[0,100]
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
										
										var options = {
											url : base
													+ '/web/beastsbirds/update.jhtml',
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
			<input type="hidden" name="id" value="${beastsBirds.id }" />
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">畜禽种类</label>
					<div class="infpCon">
						<div id="kind" data-code="畜禽养殖【畜禽种类】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">饲养量（只）</label>
					<div class="infpCon">
						<input type="" class="input" name="feedingNum" value="${beastsBirds.feedingNum }" number=true>
					</div>
				</div>
			</div>			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">存栏数（只）</label>
					<div class="infpCon">
						<input type="" class="input" name="saveNum" value="${beastsBirds.saveNum }" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">出栏数（只）</label>
					<div class="infpCon">
						<input type="" class="input" name="outNum" value="${beastsBirds.outNum }" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">饲养周期（天）</label>
					<div class="infpCon">
						<input type="" class="input" name="feedingPeriod" value="${beastsBirds.feedingPeriod }" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">饲养室内比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="feedingIndoorProportion" value="${beastsBirds.feedingIndoorProportion }" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">尿液粪便处理方式</label>
					<div class="infpCon">
						<input type="" class="input" name="process" value="${beastsBirds.process }" >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">粪肥作饲料的比重（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="fodderProportion" value="${beastsBirds.fodderProportion }" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${beastsBirds.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>