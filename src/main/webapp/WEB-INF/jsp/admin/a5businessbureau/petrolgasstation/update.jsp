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
<title>市商务局-加油站/加气站信息</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#oilType", "#boiler_form").select({name:"oilType",isCustom:true,defaultValue:"${petrolGasStation.oilType}",zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						oilRecycleRate:{
							range:[0,100]
						}
					},
					messages:{
						
					},
					submitHandler : function(form) {

						var options = {
							url : '../petrolgasstation/update.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if(data.code == 20000){
									qy.suc2({
										title : '修改成功！'
									});
									grid1.loadData();
									layer.closeAll('page');
								}else{
									qy.fail({
										title : '修改失败'+data.content
									});
								}
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
				<input type="hidden" name="id" value="${petrolGasStation.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">加油站名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name" value="${petrolGasStation.enterprise.name }" required>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${petrolGasStation.city }">
					</div>
				</div>
			</div>
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" name="houseNumber" value="${petrolGasStation.houseNumber }">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心经度</label>
					<div class="infpCon">
						<input type="" class="input" name="longitude" value="${petrolGasStation.longitude }"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心纬度</label>
					<div class="infpCon">
						<input type="" class="input" name="latitude" value="${petrolGasStation.latitude }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">油品类型</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="oilType" value="${petrolGasStation.oilType }" /> --%>
						<div data-code="商务局加油站/加气站信息【油品类型】" id="oilType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">年销售量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="annualSell" value="${petrolGasStation.annualSell }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">油品标准</label>
					<div class="infpCon">
						<input type="" class="input" name="oilStandard" value="${petrolGasStation.oilStandard }"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">地下储罐容量（立方米）</label>
					<div class="infpCon">
						<input type="" class="input" name="undergroundStorage" value="${petrolGasStation.undergroundStorage }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">补给频率（次/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="supplyFrequency" value="${petrolGasStation.supplyFrequency }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">油气回收率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="oilRecycleRate" value="${petrolGasStation.oilRecycleRate }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">是否安装油气回收装置</label>
					<div class="infpCon">
						<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isOilRecycle" value="是"
									<c:if test="${petrolGasStation.isOilRecycle=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isOilRecycle" value="否"
									<c:if test="${petrolGasStation.isOilRecycle=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
						</div>
						<%-- <input type="" class="input" name="isOilRecycle" value="${petrolGasStation.isOilRecycle }"/> --%>
					</div>
				</div>
			</div>
			

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${oilStorage.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>