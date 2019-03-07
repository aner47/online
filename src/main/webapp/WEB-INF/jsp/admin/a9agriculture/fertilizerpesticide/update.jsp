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
<title>农业委-农业机械信息</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		var type = $("#type", "#boiler_form").select({name:"type",defaultValue:"${fertilizerPesticide.type }",zIndex:9
			,initAfter:function(data,value,other){
				
				actualType.loadData(value);
				if("农药" == value){
					$("#nitrogenPurificationDiv").hide();
					$("input[name ='nitrogenPurification']").val('');
				}else{
					$("#nitrogenPurificationDiv").show();
				}
			}
			,change:function(data,value){
				actualType.clear();
				actualType.loadData(value);
				if("农药" == value){
					$("#nitrogenPurificationDiv").hide();
					$("input[name ='nitrogenPurification']").val('');
				}else{
					$("#nitrogenPurificationDiv").show();
				}
		}
		});
		var actualType = $("#actualType", "#boiler_form").select({name:"actualType",defaultValue:"${fertilizerPesticide.actualType }",zIndex:8,initLoad:false});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../fertilizerpesticide/update.jhtml',
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
				<input type="hidden" name="id" value="${fertilizerPesticide.id }" />
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">类型</label>
					<div class="infpCon">
						<div data-code="农业委化肥与农药施用【类型】" id="type" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">具体类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="machineryType" required> -->
						<div data-code="农业委化肥与农药施用【具体类型】" id="actualType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">使用量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="useQuantity" value="${fertilizerPesticide.useQuantity }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara " id="nitrogenPurificationDiv" style="display:none">
				<div class="infParaCon">
					<label for="" class="infpLable">氮折纯量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="nitrogenPurification" value="${fertilizerPesticide.nitrogenPurification }" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">施用月份</label>
					<div class="infpCon">
						<input type="" class="input" name="useMonth" value="${fertilizerPesticide.useMonth }"/> 
					</div>
				</div>
			</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${fertilizerPesticide.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>