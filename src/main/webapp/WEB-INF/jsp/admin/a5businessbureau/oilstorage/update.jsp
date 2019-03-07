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
<title>市商务局-储油库信息</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#oilType", "#boiler_form").select({name:"oilType",isCustom:true,defaultValue:"${oilStorage.oilType}",zIndex:10});
		$("#storageType", "#boiler_form").select({name:"storageType",defaultValue:"${oilStorage.storageType}",zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../oilstorage/update.jhtml',
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
				<input type="hidden" name="id" value="${oilStorage.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">储油库名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name" value="${oilStorage.enterprise.name }" required>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${oilStorage.city }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" name="houseNumber" value="${oilStorage.houseNumber }">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心经度</label>
					<div class="infpCon">
						<input type="" class="input" name="longitude" value="${oilStorage.longitude }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">中心纬度</label>
					<div class="infpCon">
						<input type="" class="input" name="latitude" value="${oilStorage.latitude }"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">油品类型</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="oilType" value="${oilStorage.oilType }" /> --%>
						<div data-code="商务局储油库信息【油品类型】" id="oilType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">年总储量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="annualTotal" value="${oilStorage.annualTotal }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">储罐类型</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="storageType" value="${oilStorage.storageType }" /> --%>
						<div data-code="商务局储油库信息【储罐类型】" id="storageType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">储存周期（天）</label>
					<div class="infpCon">
						<input type="" class="input" name="storageCycle" value="${oilStorage.storageCycle }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">装卸车过程是否已安装油气回收</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="isOilRecycle"  value="${oilStorage.isOilRecycle }"/> --%>
						<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isOilRecycle" value="是"
									<c:if test="${oilStorage.isOilRecycle=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isOilRecycle" value="否"
									<c:if test="${oilStorage.isOilRecycle=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
							</div>
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