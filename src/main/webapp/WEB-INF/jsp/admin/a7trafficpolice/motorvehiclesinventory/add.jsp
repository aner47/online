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
<title>工程机械信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				
				
				$("#stockgroundDustMeasures", "#boiler_form").select({name:"stockgroundDustMeasures",multselect:true,zIndex:9});
				$("#feedinletDustMeasures", "#boiler_form").select({name:"feedinletDustMeasures",multselect:true,zIndex:8});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {
						
						var options = {
							url : '../betonstirstation/save.jhtml',
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
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区域</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.address.city.name" >
					</div>
				</div>
			</div>
			
			<div class="clearfix"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">项目名称</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.name" required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">工程详细地址</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.address.houseNumber">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">中心经度</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.address.longitude" >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">中心纬度</label>
					<div class="infpCon">
						<input type="" class="input" name="enterprise.address.latitude" >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">产能（立方米/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="capacity" number=true />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">占地面积（平方米）</label>
					<div class="infpCon">
						<input type="" class="input" name="floorSpace" number=true />
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
						<input type="" class="input" name="isBagTypeDuster" />
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