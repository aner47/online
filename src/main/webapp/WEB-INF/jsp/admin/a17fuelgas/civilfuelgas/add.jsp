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
<title>增加民用燃气消耗信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				//var myDate = new Date();
				//myDate.getYear(); //获取当前年份(2位)
				//var year = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
				
				var year = '${project.dataYear}';
				
				$("#year1Label").html((year-3)+"年");
				$("#year2Label").html((year-2)+"年");
				$("#year3Label").html((year-1)+"年");
				$("#stockgroundDustMeasures", "#boiler_form").select({name:"stockgroundDustMeasures",multselect:true,zIndex:9});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {
						
						var options = {
							url : '../civilfuelgas/save.jhtml',
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
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" id="city" name="city">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">乡镇（街道）</label>
					<div class="infpCon">
						<input type="" class="input" id="county" name="county">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">村（居委会）</label>
					<div class="infpCon">
						<input type="" class="input" id="village" name="village">
					</div>
				</div>
			</div>
			<div class="infh1">天然气消耗量（万立方米）</div>

			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable" id="year1Label">2015年</label>
					<div class="infpCon">
						<input type="" class="input" id="year1" name="year1" number="true">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable" id="year2Label">2016年</label>
					<div class="infpCon">
						<input type="" class="input" id="year2" name="year2" number="true">
					</div>
				</div>
			</div>


			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable" id="year3Label">2017年</label>
					<div class="infpCon">
						<input type="" class="input" id="year3" name="year3" number="true">
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
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>