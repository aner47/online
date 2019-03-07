<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>农业委-化肥与农药施用信息表</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				
				
				var type = $("#type", "#boiler_form").select({name:"type",zIndex:9,change:function(data,value){
					actualType.loadData(value);
					if("农药" == value){
						$("#nitrogenPurificationDiv").hide();
						$("input[name ='nitrogenPurification']").val('');
					}else{
						$("#nitrogenPurificationDiv").show();
					}
				}});
				var actualType = $("#actualType", "#boiler_form").select({name:"actualType",zIndex:8,initLoad:false});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {
						
						var options = {
							url : '../fertilizerpesticide/save.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								qy.suc2({
									title : '添加成功！'
								});
								grid1.loadData();
								layer.closeAll('page');
							},
							error : function() {
								qy.fail({
									title : '添加失败'
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
						<input type="" class="input" name="useQuantity" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara " id="nitrogenPurificationDiv" style="display:none">
				<div class="infParaCon">
					<label for="" class="infpLable">氮折纯量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="nitrogenPurification" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">施用月份</label>
					<div class="infpCon">
						<input type="" class="input" name="useMonth" /> 
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description"></textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>