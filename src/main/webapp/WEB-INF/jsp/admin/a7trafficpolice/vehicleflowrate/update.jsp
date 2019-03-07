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
<title>道路类型及数量</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
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
		
		$("#roadType", "#boiler_form").select({name:"roadType",defaultValue:"${vehicleFlowrate.roadType}",zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../vehicleflowrate/update.jhtml',
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
				<input type="hidden" name="id" value="${vehicleFlowrate.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路名称</label>
					<div class="infpCon">
						<input type="" class="input" name="roadName" value="${vehicleFlowrate.roadName }" required>
					</div>
				</div>
			</div>
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路类型</label>
					<div class="infpCon">
						<div data-code="交警支队车流量信息【道路类型】" id="roadType" name="roadType" class="selF"></div>
						<%-- <input type="" class="input" name="roadType" value="${vehicleFlowrate.roadType }"> --%>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">日期</label>
					<div class="infpCon">
						<input type="" class="input" id="roadDate" name="roadDate" value="${vehicleFlowrate.roadDate }" onfocus="this.blur()">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">开始时间</label>
					<div class="infpCon">
						<input type="" class="input" id="startTime" name="startTime" value="${vehicleFlowrate.startTime }">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">结束时间</label>
					<div class="infpCon">
						<input type="" class="input" id="endTime" name="endTime" value="${vehicleFlowrate.endTime }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">大型车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowLargeCar" value="${vehicleFlowrate.flowLargeCar }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">小型车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowSmallCar" value="${vehicleFlowrate.flowSmallCar }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">公交车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowBus" value="${vehicleFlowrate.flowBus }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">摩托车车流量（辆/小时）</label>
					<div class="infpCon">
						<input type="" class="input" name="flowMotorbike" value="${vehicleFlowrate.flowMotorbike }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			
			
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${vehicleFlowrate.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>