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
<title>海事局-港口机械信息</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {
		var projectId = '${projectId}';
		
		$("#enterpriseId", "#boiler_form").select({name:"enterpriseId",defaultValue:"${portMechanical.enterprise.id}",zIndex:10,param:projectId});
		$("#mechanicalType", "#boiler_form").select({name:"mechanicalType",defaultValue:"${portMechanical.mechanicalType}",isCustom: true,zIndex:9});
		
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						enterpriseId:{
							required:true
						},
						mechanicalType:{
							required:true
						},
						fuelSulfurRate:{
							range:[0,100]
						}
					},
					messages:{
						
					},
					submitHandler : function(form) {

						var options = {
							url : '../portmechanical/update.jhtml',
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
				<input type="hidden" name="id" value="${portMechanical.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">港口名称</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="enterprise.name" value="${portMechanical.enterprise.name }" required > --%>
						<div data-code="海事局港口【港口名称】" id="enterpriseId" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机械类型</label>
					<div class="infpCon">
					<div data-code="海事局港口机械信息【机械类型】" id="mechanicalType" class="selF"></div>
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">机械保有量（台）</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanicalInventory" value="${portMechanical.mechanicalInventory }" number="true" required onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">总功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" name="totalPower" value="${portMechanical.totalPower }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料消耗量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" value="${portMechanical.fuelConsumption }" number="true" required onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurRate" value="${portMechanical.fuelSulfurRate }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${portMechanical.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>