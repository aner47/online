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
<title>市农业委-渔船每月活动水平信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				
				
				$("#fuelType", "#boiler_form").select({name:"fuelType",isCustom:true,zIndex:9});
				$("#breedingMethod", "#boiler_form").select({name:"breedingMethod",zIndex:8});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {
						
						var options = {
							url : '../fishingboatmonth/save.jhtml',
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
					<label for="" class="infpLable">港区名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">渔船类型</label>
					<div class="infpCon">
						<input type="" class="input" name="boatType" > 
						<!-- <div data-code="农业委农作物产量及秸秆利用【农作物类型】" id="cropType" class="selF"></div> -->
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">一月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="january" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">二月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="february" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">三月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="march" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">四月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="april" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">五月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="may" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">六月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="june" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">七月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="july" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">八月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="august" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">九月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="september" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">十月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="october" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">十一月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="november" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">十二月（艘）</label>
					<div class="infpCon">
						<input type="" class="input" name="december" number="true" onKeyUp="value=value.replace(/[\D]/g,'')">
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