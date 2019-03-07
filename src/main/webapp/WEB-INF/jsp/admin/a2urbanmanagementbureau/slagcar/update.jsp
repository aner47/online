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
<title>全市渣土车信息表</title>
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
							url : '../slagcar/update.jhtml',
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
				<input type="hidden" name="id" value="${slagCar.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">所属公司</label>
					<div class="infpCon">
						<input type="" class="input" name="companies" value="${slagCar.companies }" required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">输出地</label>
					<div class="infpCon">
						<input type="" class="input" name="outputPlace" value="${slagCar.outputPlace }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">输入地</label>
					<div class="infpCon">
						<input type="" class="input" name="inputPlace" value="${slagCar.inputPlace }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均运输距离</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDistance" value="${slagCar.avgDistance }" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单车运载量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="carryingcapacity" value="${slagCar.carryingcapacity }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')" />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每日活动时间</label>
					<div class="infpCon">
						<input type="" class="input" name="activityTime" value="${slagCar.activityTime }" />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">月运载次数</label>
					<div class="infpCon">
						<input type="" class="input" name="monthCarryingNum" value="${slagCar.monthCarryingNum }"  number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						<!-- <div data-code="混凝土搅拌站信息【料场抑尘措施】" id="stockgroundDustMeasures" class="selF"></div> -->
					</div>
				</div>
			</div>
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">尺寸（长*宽*高）</label>
					<div class="infpCon">
						<input type="" class="input" name="size" value="${slagCar.size }"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">是否封闭</label>
					<div class="infpCon">
						<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isClose" value="是"
									<c:if test="${slagCar.isClose=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isClose" value="否"
									<c:if test="${slagCar.isClose=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
							</div>
						<%-- <input type="" class="input" name="isClose" value="${slagCar.isClose }" /> --%>
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">出入工地/场地是否冲洗</label>
					<div class="infpCon">
						<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isClean" value="是"
									<c:if test="${slagCar.isClean=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isClean" value="否"
									<c:if test="${slagCar.isClean=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
							</div>
						<%-- <input type="" class="input" name="isClean" value="${slagCar.isClean }"/> --%>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${slagCar.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>