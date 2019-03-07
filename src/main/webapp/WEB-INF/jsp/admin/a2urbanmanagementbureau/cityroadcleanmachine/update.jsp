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
<title>城市道路保洁信息机械化统计</title>
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
							url : '../cityroadcleanmachine/update.jhtml',
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
				<input type="hidden" name="id" value="${cityRoadCleanMachine.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">区县</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${cityRoadCleanMachine.city }" >
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路名称</label>
					<div class="infpCon">
						<input type="" class="input" name="roadName" value="${cityRoadCleanMachine.roadName }" required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路级别（主/次/支）</label>
					<div class="infpCon">
						<input type="" class="input" name="roadGrade" value="${cityRoadCleanMachine.roadGrade }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">起止点</label>
					<div class="infpCon">
						<input type="" class="input" name="enthesis" value="${cityRoadCleanMachine.enthesis }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">长度（m）</label>
					<div class="infpCon">
						<input type="" class="input" name="length" value="${cityRoadCleanMachine.length }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">快车道宽度（m）</label>
					<div class="infpCon">
						<input type="" class="input" name="fastRoadWidth" value="${cityRoadCleanMachine.fastRoadWidth }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')" />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">人行道宽度（m）</label>
					<div class="infpCon">
						<input type="" class="input" name="sidewalkWidth" value="${cityRoadCleanMachine.sidewalkWidth }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')" />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">快车道道路面积（㎡）</label>
					<div class="infpCon">
						<input type="" class="input" name="fastRoadArea" value="${cityRoadCleanMachine.fastRoadArea }"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						<!-- <div data-code="混凝土搅拌站信息【料场抑尘措施】" id="stockgroundDustMeasures" class="selF"></div> -->
					</div>
				</div>
			</div>
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">人行道道路面积（㎡）</label>
					<div class="infpCon">
						<input type="" class="input" name="sidewalkArea" value="${cityRoadCleanMachine.sidewalkArea }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">合计</label>
					<div class="infpCon">
						<input type="" class="input" name="areaTotal" value="${cityRoadCleanMachine.areaTotal }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">洗扫车（台）</label>
					<div class="infpCon">
						<input type="" class="input" name="cleaningSweeperTruck" value="${cityRoadCleanMachine.cleaningSweeperTruck }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">吸尘车（台）</label>
					<div class="infpCon">
						<input type="" class="input" name="vacuumSweeper" value="${cityRoadCleanMachine.vacuumSweeper }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">雾炮车（台）</label>
					<div class="infpCon">
						<input type="" class="input" name="mistCannonTruck" value="${cityRoadCleanMachine.mistCannonTruck }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">其他车辆（台）</label>
					<div class="infpCon">
						<input type="" class="input" name="otherTruck" value="${cityRoadCleanMachine.otherTruck }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${cityRoadCleanMachine.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>