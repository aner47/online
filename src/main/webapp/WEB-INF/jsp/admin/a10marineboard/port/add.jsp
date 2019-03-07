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
<title>市海事局-港口信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {
						
						var options = {
							url : '../port/save.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if(data.code == 20000){
									qy.suc2({
										title : '添加成功！'
									});
									grid1.loadData();
									layer.closeAll('page');
								}else{
									qy.fail({
										title : '添加失败'+data.content
									});
								}
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
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">港口名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">经度</label>
					<div class="infpCon">
						<input type="" class="input" name="longitude" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">纬度</label>
					<div class="infpCon">
						<input type="" class="input" name="latitude" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">泊位数</label>
					<div class="infpCon">
						<input type="" class="input" name="berths" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">客运量（万人次）</label>
					<div class="infpCon">
						<input type="" class="input" name="passengerCapacity" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">货运量（万吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="commodityCapacity" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">集装箱吞吐量（万标准箱）</label>
					<div class="infpCon">
						<input type="" class="input" name="containerCapacity" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">船舶抵离港总次数</label>
					<div class="infpCon">
						<input type="" class="input" name="arriveLeaveTotal" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
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