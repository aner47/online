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
<title>市海事局-客货运输船舶活动信息</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {
		var projectId = '${projectId}';

		$("#enterpriseId", "#boiler_form").select({name:"enterpriseId",defaultValue:"${transportShip.enterprise.id}",zIndex:10,param:projectId});
		$("#shipType", "#boiler_form").select({name:"shipType",defaultValue:"${transportShip.shipType}",isCustom: true,zIndex:9});
		
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						enterpriseId:{
							required:true
						},
						shipType:{
							required:true
						},
						sulfurRate:{
							range:[0,100]
						}
					},
					messages:{
						
					},
					submitHandler : function(form) {

						var options = {
							url : '../transportship/update.jhtml',
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
				<input type="hidden" name="id" value="${transportShip.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">港区名称</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="enterprise.name" value="${transportShip.enterprise.name }" required> --%>
						<div data-code="海事局港口【港口名称】" id="enterpriseId" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">船舶类型</label>
					<div class="infpCon">
						<div data-code="客货运输船舶活动信息【船舶类型】" id="shipType" class="selF"></div>
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">数量（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="number"  value="${transportShip.number }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">抵离港次数（次）</label>
					<div class="infpCon">
						<input type="" class="input" name="arriveLeaveTotal" value="${transportShip.arriveLeaveTotal }"  number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">平均载重吨（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgLoad" value="${transportShip.avgLoad }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">主机平均功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" name="mainAvgPower" value="${transportShip.mainAvgPower }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">辅机平均功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" name="auxiliaryAvgPower" value="${transportShip.auxiliaryAvgPower }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉功率（千瓦）</label>
					<div class="infpCon">
						<input type="" class="input" name="boilerPower" value="${transportShip.boilerPower }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">燃油类型</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelType" value="${transportShip.fuelType }" />
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">消耗量（吨/年）</label>
					<div class="infpCon">
						<input type="" class="input" name="consumption" value="${transportShip.consumption }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫率（％）</label>
					<div class="infpCon">
						<input type="" class="input" name="sulfurRate" value="${transportShip.sulfurRate }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${transportShip.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>