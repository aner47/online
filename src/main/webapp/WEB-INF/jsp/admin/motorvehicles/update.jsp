<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<title>修改设备泄露</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">

<script type="text/javascript">
	require([ "validate", "ajaxform" ], function(a) {
		$("#surveyDate", "#inputForm").jeDate({
	        format:"YYYY-MM-DD",
	        isTime:false, 
	        zIndex:999999900
	    })
	    
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		$("#estimationEethod", "#inputForm").select({
			name : "estimationEethod",
			defaultValue : '${equipmentLeaked.estimationEethod}',
			zIndex : 10
		});
		//提交表单
		$("#inputForm").validate({
			rules : {},
			messages : {},
			submitHandler : function(form) {
				var options = {
					url : 'update.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('修改成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('修改失败', {
							icon : 3
						});
					}
				};
				$(form).ajaxSubmit(options);
				return false;
			}
		});
	});
</script>
	<form id="inputForm" style="padding:0;width:100%;">
		<input type="hidden" id="id" name="id" value="${motorVehicles.id}" />
		<div class="infLine">
		
		<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">调查地点</label>
					<div class="infpCon">
						<input type="" class="input" id="surveyPlace" name="surveyPlace" placeholder="调查地点" value="${motorVehicles.surveyPlace}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">调查人</label>
					<div class="infpCon">
						<input type="" class="input" id="surveyPeople" name="surveyPeople" placeholder="调查人" value="${motorVehicles.surveyPeople}">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">调查日期</label>
					<div class="infpCon">
						<input type="text"  class="input" id="surveyDate" name="surveyDate" placeholder="调查日期" 
						value="<fmt:formatDate value='${motorVehicles.surveyDate}'  pattern='yyyy-MM-dd'/>" />
					</div>
				</div>
			</div>


		</div>
	</form>