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
<title>添加露天堆场信息</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
		        
				$("#materials", "#boiler_form").select({name:"materials",zIndex:9,isCustom: true});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form")
						.validate(
								{
									rules : {
										fuelSulfurContent : {
											range : [ 0, 100 ],
										},
										fuelAsh : {
											range : [ 0, 100 ],
										},
										fuelVolatiles : {
											range : [ 0, 100 ],
										},
										materials:{
											required:true
										},
										holdArea:{
											min:0
										},
										height:{
											min:0
										},
										stock:{
											min:0
										},
										trainNumber:{
											min:0
										},
										carryingCapacities:{
											min:0
										},
										description:{
											maxlength:255
										}
									},
									messages : {
										fuelSulfurContent : {
											range : "0~100!",
										},
										fuelAsh : {
											range : "0~100!",
										},
										fuelVolatiles : {
											range : "0~100!",
										},
									},
									submitHandler : function(form) {
										
										var options = {
											url : base
													+ '/web/constructionyard/save.jhtml',
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
					<label for="" class="infpLable">料堆材料</label>
					<div class="infpCon">
						<div id="materials" data-code="施工工地【料堆类型】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">占地面积（平方米）</label>
					<div class="infpCon">
						<input class="input" name="holdArea" placeholder="" number=true required />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">最高高度（米）</label>
					<div class="infpCon">
						<input class="input" name="height" placeholder="" number=true />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">日常储存量（吨）</label>
					<div class="infpCon">
						<input class="input" name="stock" placeholder="" number=true required/>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年物料运载车次（车）</label>
					<div class="infpCon">
						<input class="input" name="trainNumber" placeholder="" number=true />
					</div>
				</div>
			</div>	

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单车运载量（吨/车）</label>
					<div class="infpCon">
						<input class="input" name="carryingCapacities" placeholder="" number=true />
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