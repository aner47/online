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
<title>客运站/货运物流中心调查表</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#stockgroundDustMeasures", "#boiler_form").select({name:"stockgroundDustMeasures",defaultValue:"${betonStirStation.stockgroundDustMeasures}",zIndex:9});
		$("#feedinletDustMeasures", "#boiler_form").select({name:"feedinletDustMeasures",defaultValue:"${betonStirStation.feedinletDustMeasures}",zIndex:8});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../passengerstation/update.jhtml',
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
				<input type="hidden" name="id" value="${passengerStation.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区划</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${passengerStation.city }">
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">公司名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name" value="${passengerStation.enterprise.name }" required>
					</div>
				</div>
			</div>
			
			
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">地址</label>
					<div class="infpCon">
						<input type="" class="input" name="houseNumber" value="${passengerStation.houseNumber }">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">占地面积</label>
					<div class="infpCon">
						<input type="" class="input" name="area" value="${passengerStation.area }" number="true"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">经度</label>
					<div class="infpCon">
						<input type="" class="input" name="longitude" value="${passengerStation.longitude }" number="true"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">纬度</label>
					<div class="infpCon">
						<input type="" class="input" name="latitude" value="${passengerStation.latitude }"  number="true"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">全年进/出车辆次数</label>
					<div class="infpCon">
						<input type="" class="input" name="yearInout" value="${passengerStation.yearInout }" number="true"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">每天进/出车辆次数</label>
					<div class="infpCon">
						<input type="" class="input" name="dayInout" value="${passengerStation.dayInout }" number="true"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">车辆类型</label>
					<div class="infpCon">
						<input type="" class="input" name="carType" value="${passengerStation.carType }"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃油类型</label>
					<div class="infpCon">
						<input type="" class="input" name="oilType" value="${passengerStation.oilType }" />
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">车辆平均停留时间</label>
					<div class="infpCon">
						<input type="" class="input" name="stayTime" value="${passengerStation.stayTime }"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${passengerStation.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>