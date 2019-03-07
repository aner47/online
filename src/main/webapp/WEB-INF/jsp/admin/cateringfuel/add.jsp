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
<title>添加餐饮燃料</title>
<style type="text/css">
#vocsContent-error,#vocsVolatility-error,#annual-error{
	top:0px;
}
.infParaCon .infpCon label.error{
	top:20px;
}
.infParaChange{
	width:100%;
}
</style>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				$("#lampblackDate", "#boiler_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        })
		        
				$("#fuelType", "#boiler_form").select({name:"fuelType",zIndex:9});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form")
						.validate(
								{
									rules : {
										unit:{
											checkUnit:/\D/
										},
										annualUse:{
											min:0
										},
										maintainDays:{
											min:0,
											digits:true
										},
										commissioningRatio:{
											range:[0,100]
										},
										cleansingRatio:{
											range:[0,100]
										},
										description:{
											maxlength:255
										},
										fuelType:{
											required:true
										},
										smokeReleaseRatio:{
											min:0
										}

									},
									messages : {
										maintainDays:{
											digits:"整数！"
										},
									},
									submitHandler : function(form) {
										
										var options = {
											url : base
													+ '/web/cateringfuel/save.jhtml',
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
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="餐饮【燃料类型】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年使用量</label>
					<div class="infpCon">
						<input type="" class="input" name="annualUse" number=true required>
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
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">油烟净化器名称</label>
					<div class="infpCon">
						<input type="" class="input" name="lampblackModel">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">油烟净化器安装日期</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="" id="lampblackDate" name="lampblackDate" class="Yinput" onfocus="this.blur()" required>
						</div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">油烟净化器维护周期（天）</label>
					<div class="infpCon">
						<input type="" class="input" name="maintainDays" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara infParaChange">
				<div class="infParaCon">
					<label for="" class="infpLable">烟气排放速率（立方米/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="smokeReleaseRatio" number=true>
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">净化器投运率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="commissioningRatio" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">净化效率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="cleansingRatio" number=true>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">灶头数（个）</label>
					<div class="infpCon">
						<input type="" class="input" name="kitchenNum" number=true>
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