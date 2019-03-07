<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>修改钢铁</title>
</head>

<script type="text/javascript">
	require(["hide","validate", "ajaxform", "select","panel","date" ], function(hide) {
		
		var manufactureProcess = $("#sewagetype", "#sewage_form").select({
			name : "sewagetype",isCustom : true,
			zIndex : 9,
			empty: true,
			defaultValue:'${sewageDispose.sewagetype }',
			change:function(event, value){
				
			}
		});
		
       
		
		//提交表单
		$("#sewage_form").validate({
			rules : {
				
				
				description:{
					maxlength:255
				},
			},
			messages : {
				description:{
					maxlength:"最多255字"
				}
			},
			submitHandler : function(form) {
				
					var options = {
						url : 'update.jhtml',
						type : 'post',
						dataType : 'json',
						success : function(data) {
							qy.suc2({title:'更新成功！'});
							grid1.loadData();
							layer.closeAll('page');
						},
						error : function() {
							qy.fail2({title:'更新失败'});
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
	<form class="hideCls" id="sewage_form" style="padding:0;width:100%;">
	<div class="infLine">
		<input type="hidden" name="id" value="${sewageDispose.id }" />
		<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">污水类型</label>
					<div class="infpCon">
						<div id="sewagetype" data-code="污水处理【污水类型】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">污水排放量(吨)</label>
					<div class="infpCon">
						<input type="" class="input" id="emissionQuantity" name="emissionQuantity"
						value="${sewageDispose.emissionQuantity }" number=true/>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">污水自处理率(%)</label>
					<div class="infpCon">
						<input type="" class="input" id="disposeRate" name="disposeRate"
						value="${sewageDispose.disposeRate }" number=true/>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">污水处理工艺</label>
					<div class="infpCon">
						<input type="" class="input" id="disposeTechnology" name="disposeTechnology"
						value="${sewageDispose.disposeTechnology }"/>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">治理效率（%）</label>
					<div class="infpCon">
						<input type="" class="input" id="disposeEfficiency" name="disposeEfficiency"
						value="${sewageDispose.disposeEfficiency }" number=true/>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">污水处理池是否加盖(是/否)</label>
					<div class="infpCon">
						<%-- <input type="" class="input" id="isCapping" name="isCapping"
						value="${sewageDispose.isCapping }"/> --%>
						<input type="radio" class="radio" id="isCapping1"
							name="isCapping" value="是"
							<c:if test="${sewageDispose.isCapping=='是' }">checked='checked'</c:if>>
						<label for="isCapping1" class="label">是</label> <input
							type="radio" class="radio" id="isCapping2"
							name="isCapping" value="否"
							<c:if test="${sewageDispose.isCapping=='否' }">checked='checked'</c:if>>
						<label for="isCapping2" class="label">否</label>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">废气是否收集处理(是/否)</label>
					<div class="infpCon">
						<%-- <input type="" class="input" id="isGather" name="isGather"
						value="${sewageDispose.isGather }"/> --%>
						<input type="radio" class="radio" id="isGather1"
							name="isGather" value="是"
							<c:if test="${sewageDispose.isGather=='是' }">checked='checked'</c:if>>
						<label for="isGather1" class="label">是</label> <input
							type="radio" class="radio" id="isGather2"
							name="isGather" value="否"
							<c:if test="${sewageDispose.isGather=='否' }">checked='checked'</c:if>>
						<label for="isGather2" class="label">否</label>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">废气处理方式（请说明）</label>
					<div class="infpCon">
						<input type="" class="input" id="disposeWay" value="${sewageDispose.disposeWay }" 
						name="disposeWay"/>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${sewageDispose.description }</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>