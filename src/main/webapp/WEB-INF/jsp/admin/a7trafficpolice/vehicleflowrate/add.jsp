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
<title>车流量信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				$("#roadDate", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        });
				$("#startTime", "#boiler_form").jeDate({
		            format:"hh:mm",
		            zIndex:999999900
		        });
				$("#endTime", "#boiler_form").jeDate({
		            format:"hh:mm",
		            zIndex:999999900
		        });
				
				
				$("#roadType", "#boiler_form").select({name:"roadType",zIndex:9});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {
						
						var options = {
							url : '../vehicleflowrate/save.jhtml',
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
					<label for="" class="infpLable">道路名称</label>
					<div class="infpCon">
						<input type="" class="input" name="roadName" required>
					</div>
				</div>
			</div>
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="roadType" > -->
						<div data-code="交警支队车流量信息【道路类型】" id="roadType" name="roadType" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">日期</label>
					<div class="infpCon">
						<input type="" class="input" id="roadDate" name="roadDate"  onfocus="this.blur()" >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">开始时间</label>
					<div class="infpCon">
						<input type="" class="input" id="startTime" name="startTime" >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">结束时间</label>
					<div class="infpCon">
						<input type="" class="input" id="endTime" name="endTime" >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">大型车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowLargeCar" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">小型车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowSmallCar" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">公交车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowBus" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">摩托车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowMotorbike" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
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