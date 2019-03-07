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
<title>城管局-餐饮行业信息</title>
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
					rules:{
						lampblackPurificationRate:{
							range:[0,100]
						}
					},
					messages:{
						
					},
					submitHandler : function(form) {

						var options = {
							url : '../catering/update.jhtml',
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
				<input type="hidden" name="id" value="${catering.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区域</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${catering.city }">
					</div>
				</div>
			</div>
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" name="houseNumber" value="${catering.houseNumber }">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">餐企名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name" value="${catering.enterprise.name }" required>
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">是否安装油烟净化装置</label>
					<div class="infpCon">
						<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isLampblackPurification" value="是"
									<c:if test="${catering.isLampblackPurification=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isLampblackPurification" value="否"
									<c:if test="${catering.isLampblackPurification=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
							</div>
						<%-- <input type="" class="input" name="isLampblackPurification" value="${catering.isLampblackPurification }"/> --%>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">油烟净化效率</label>
					<div class="infpCon">
						<input type="" class="input" name="lampblackPurificationRate" value="${catering.lampblackPurificationRate }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${catering.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>