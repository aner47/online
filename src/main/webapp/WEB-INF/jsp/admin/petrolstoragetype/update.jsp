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
<title>修改储罐类型信息</title>
<style type="text/css">
	#colour{
		width:90%;
	}
	#colour ._s_contain1{
		left:-10px;
	}
	._caption{
		padding-left:10px;
	}
</style>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel","date"  ],
			function(hide) {
				
		        
		        $("#storageTinType", "#boiler_form").select({name:"storageTinType",defaultValue : "${petrolStorageType.storageTinType}",zIndex:9});
				$("#storageMatterName", "#boiler_form").select({name:"storageMatterName",defaultValue : "${petrolStorageType.storageMatterName}",zIndex:8});
				$("#annualStorageSumUnit","#boiler_form").select({name:"annualStorageSumUnit",defaultValue : "${petrolStorageType.annualStorageSumUnit}",zIndex:7});
				$("#colour", "#boiler_form").select({name:"colour",defaultValue : "${petrolStorageType.colour}",zIndex:6});
				$("#shell", "#boiler_form").select({name:"shell",defaultValue : "${petrolStorageType.shell}",zIndex:5	});
				
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
										avgWorkCubage:{
											min:0
										},
										annualUseDays:{
											range:[0,366]
										},
										annualPadNumber:{
											min:0
										},
										annualStorageSum:{
											min:0
										},
										description:{
											maxlength:255
										},
										storageTinType:{
											required:true,
										},
										storageMatterName:{
											required:true,
										},

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
													+ '/web/petrolstoragetype/update.jhtml',
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
			<input type="hidden" name="id" value="${petrolStorageType.id }" />
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储罐类型</label>
					<div class="infpCon">
						<div id="storageTinType" data-code="加油站【储罐类型】" class="selF" required></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">存储物质名称</label>
					<div class="infpCon">
						<div id="storageMatterName" data-code="加油站【存储物质名称】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均工作容量（立方米）</label>
					<div class="infpCon">
						<input type="" class="input" name="avgWorkCubage" value="${petrolStorageType.avgWorkCubage }" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年使用天数</label>
					<div class="infpCon">
						<input type="" class="input" name="annualUseDays" value="${petrolStorageType.annualUseDays }" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年填充次数</label>
					<div class="infpCon">
						<input type="" class="input" name="annualPadNumber" value="${petrolStorageType.annualPadNumber }" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年总存储量</label>
					<div class="infpCon">
						<input type="" class="input" name="annualStorageSum" value="${petrolStorageType.annualStorageSum }" number=true required>
					</div>
				</div>
			</div>
			<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年总存储量单位</label>
						<div class="infpCon">
							<div data-code="施工工地【渣土清运量单位】" id="annualStorageSumUnit" class="selF"></div>
						</div>
					</div>
			</div>
			<!-- <div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">颜色</label>
					<div class="infpCon">
						<div id="colour" data-code="加油站【颜色】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">外壳</label>
					<div class="infpCon">
						<div id="shell" data-code="加油站【外壳】" class="selF"></div>
					</div>
				</div>
			</div> -->
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">油漆情况</label>
					<div class="infpCon">
						<div id="colour" data-code="加油站【颜色】" class="selF" placeholder="--颜色--"></div>
					</div>
					<div class="infpCon">
						<div id="shell" data-code="加油站【外壳】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${petrolStorageType.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>