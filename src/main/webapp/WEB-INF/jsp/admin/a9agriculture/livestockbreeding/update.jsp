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
<title>农业委-畜禽养殖信息表</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#livestockType", "#boiler_form").select({name:"livestockType",defaultValue:"${livestockBreeding.livestockType}",isCustom:true,zIndex:9});
		$("#breedingMethod", "#boiler_form").select({name:"breedingMethod",defaultValue:"${livestockBreeding.breedingMethod}",zIndex:8});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../livestockbreeding/update.jhtml',
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
				<input type="hidden" name="id" value="${livestockBreeding.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">区县</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${livestockBreeding.city }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">乡镇</label>
					<div class="infpCon">
						<input type="" class="input" name="town" value="${livestockBreeding.town }">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">养殖场名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name"  value="${livestockBreeding.enterprise.name }" required>
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">畜禽类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="livestockType" /> -->
						<div data-code="农业委畜禽养殖【畜禽类型】" id="livestockType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">养殖方式</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="breedingMethod" /> -->
						<div data-code="农业委畜禽养殖【养殖方式】" id="breedingMethod" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">平均饲养周期（天）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgBreedingPeriod" value="${livestockBreeding.avgBreedingPeriod }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">总饲养量（万只）</label>
					<div class="infpCon">
						<input type="" class="input" name="breedNum" value="${livestockBreeding.breedNum }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">总出栏数（万只）</label>
					<div class="infpCon">
						<input type="" class="input" name="outputTotal" value="${livestockBreeding.outputTotal }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">年末存栏量（万只）</label>
					<div class="infpCon">
						<input type="" class="input" name="reserveNum" value="${livestockBreeding.reserveNum }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${livestockBreeding.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>