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
<title>道路类型及数量</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#roadType", "#boiler_form").select({name:"roadType",defaultValue:"${roadTypeNum.roadType}",isCustom:true,zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../roadtypenum/update.jhtml',
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
				<input type="hidden" name="id" value="${roadTypeNum.id }" />
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区域</label>
					<div class="infpCon">
						<input type="" class="input" name="city" value="${roadTypeNum.city }">
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路类型</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="roadType" value="${roadTypeNum.roadType }" required> --%>
						<div data-code="交通局【道路类型】" id="roadType" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路数量（条）</label>
					<div class="infpCon">
						<input type="" class="input" name="roadNum" value="${roadTypeNum.roadNum }" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">道路累计长度（千米）</label>
					<div class="infpCon">
						<input type="" class="input" name="roadlength" value="${roadTypeNum.roadlength }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">清扫面积（平方米）</label>
					<div class="infpCon">
						<input type="" class="input" name="checkArea" value="${roadTypeNum.checkArea }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${roadTypeNum.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>