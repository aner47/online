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
<title>城市道路保洁信息汇总表</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#roadType", "#boiler_form").select({name:"roadType",defaultValue:"${cityRoadClean.roadType}",isCustom:true,zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						machineCleanRate:{
							range:[0,100]
						}
					},
					messages:{
						
					},
					submitHandler : function(form) {

						var options = {
							url : '../cityroadclean/update.jhtml',
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
				<input type="hidden" name="id" value="${cityRoadClean.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区域</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${cityRoadClean.city }">
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路类型</label>
					<div class="infpCon">
					<div data-code="城管局城市道路保洁信息【道路类型】" id="roadType" class="selF"></div>
						<%-- <input type="" class="input" name="roadType" value="${cityRoadClean.roadType }" required> --%>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路数量（条）</label>
					<div class="infpCon">
						<input type="" class="input" name="roadNum" value="${cityRoadClean.roadNum }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路累计长度（千米）</label>
					<div class="infpCon">
						<input type="" class="input" name="roadlength" value="${cityRoadClean.roadlength }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">清扫面积（平方米）</label>
					<div class="infpCon">
						<input type="" class="input" name="cleanArea" value="${cityRoadClean.cleanArea }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每天清扫频次（次）</label>
					<div class="infpCon">
						<input type="" class="input" name="cleanFrequency" value="${cityRoadClean.cleanFrequency }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')" />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机扫率（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="machineCleanRate" value="${cityRoadClean.machineCleanRate }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')" />
					</div>
				</div>
			</div>
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">每天洒水频次（次）</label>
					<div class="infpCon">
						<input type="" class="input" name="wateringFrequency" value="${cityRoadClean.wateringFrequency }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">清扫方式</label>
					<div class="infpCon">
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="cleanMethod" value="干法"
									<c:if test="${cityRoadClean.cleanMethod=='干法' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">干法</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="cleanMethod" value="湿法"
									<c:if test="${cityRoadClean.cleanMethod=='湿法' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">湿法</label>
								
							</div>
						</div>
						<%-- <input type="" class="input" name="cleanMethod" value="${cityRoadClean.cleanMethod }" /> --%>
						<!-- <div data-code="混凝土搅拌站信息【料场抑尘措施】" id="stockgroundDustMeasures" class="selF"></div> -->
					</div>
				</div>
			</div>
			
			

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${cityRoadClean.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>