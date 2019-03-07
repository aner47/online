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
<title>混凝土搅拌站信息</title>
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
							url : '../betonstirstation/update.jhtml',
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
				<input type="hidden" name="id" value="${betonStirStation.id }" />
				<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区域</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${betonStirStation.city }" >
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">项目名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name" value="${betonStirStation.enterprise.name }" required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">工程详细地址</label>
					<div class="infpCon">
						<input type="" class="input" name="houseNumber" value="${betonStirStation.houseNumber }">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">中心经度</label>
					<div class="infpCon">
						<input type="" class="input" name="longitude" value="${betonStirStation.longitude}" number="true"   onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">中心纬度</label>
					<div class="infpCon">
						<input type="" class="input" name="latitude" value="${betonStirStation.latitude}" number="true"   onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">产能（立方米/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="capacity" value="${betonStirStation.capacity}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')" />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">占地面积（平方米）</label>
					<div class="infpCon">
						<input type="" class="input" name="floorSpace" value="${betonStirStation.floorSpace}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料场抑尘措施</label>
					<div class="infpCon">
						<div data-code="混凝土搅拌站信息【料场抑尘措施】" id="stockgroundDustMeasures" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">上料口抑尘措施</label>
					<div class="infpCon">
						<div data-code="混凝土搅拌站信息【上料口抑尘措施】" id="feedinletDustMeasures" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">粉料筒仓上部是否配套高效布袋除尘器</label>
					<div class="infpCon">
					<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isBagTypeDuster" value="是"
									<c:if test="${betonStirStation.isBagTypeDuster=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isBagTypeDuster" value="否"
									<c:if test="${betonStirStation.isBagTypeDuster=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
							</div>
						</div>
						<%-- <input type="" class="input" name="isBagTypeDuster" value="${betonStirStation.isBagTypeDuster}"/> --%>
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${betonStirStation.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>