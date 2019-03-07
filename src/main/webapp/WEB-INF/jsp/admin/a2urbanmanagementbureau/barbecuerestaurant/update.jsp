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
<title>城管局-全市烧烤店统计信息</title>
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
							url : '../barbecuerestaurant/update.jhtml',
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
				<input type="hidden" name="id" value="${barbecueRestaurant.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">烧烤店名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name" value="${barbecueRestaurant.enterprise.name }" required>
					</div>
				</div>
			</div>
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input"  name="city" value="${barbecueRestaurant.city }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" name="houseNumber" value="${barbecueRestaurant.houseNumber }" >
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心经度</label>
					<div class="infpCon">
						<input type="" class="input" name="longitude" value="${barbecueRestaurant.longitude }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心纬度</label>
					<div class="infpCon">
						<input type="" class="input" name="latitude" value="${barbecueRestaurant.latitude }"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">联系人</label>
					<div class="infpCon">
						<input type="" class="input" name="contacts" value="${barbecueRestaurant.contacts }" />
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">联系电话</label>
					<div class="infpCon">
						<input type="" class="input" name="contactNumber" value="${barbecueRestaurant.contactNumber }" />
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">是否露天烧烤</label>
					<div class="infpCon">
						<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isOpenBarbecue" value="是"
									<c:if test="${barbecueRestaurant.isOpenBarbecue=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isOpenBarbecue" value="否"
									<c:if test="${barbecueRestaurant.isOpenBarbecue=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
							</div>
						<%-- <input type="" class="input" name="isOpenBarbecue" value="${barbecueRestaurant.isOpenBarbecue }"/> --%>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">每年经营天数（天）</label>
					<div class="infpCon">
						<input type="" class="input" name="annualBusinessDays" value="${barbecueRestaurant.annualBusinessDays }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">每日经营时间（时）</label>
					<div class="infpCon">
						<input type="" class="input" name="manageHour" value="${barbecueRestaurant.manageHour }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelType" value="${barbecueRestaurant.fuelType }"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" value="${barbecueRestaurant.fuelConsumption }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料单位</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelUnit" value="${barbecueRestaurant.fuelUnit }"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${barbecueRestaurant.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>