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
<title>编辑石油产品储罐</title>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel"],function(hide){
	$("#tankMaterialType","#oiltank_form").select({name:"tankMaterialType",isCustom : true,defaultValue:'${oilTank.tankMaterialType}'});
	$("#tankType","#oiltank_form").select({name:"tankType",defaultValue:'${oilTank.tankType}'});
		$('#commit').click(function(){
			$("#oiltank_form").submit();
		})
		//提交表单
		$("#oiltank_form").validate({
			rules: {
				workDays: {
					range:[0,366],
				},
				storageCycle: {
					range:[0,366],
				},
				height:{
					range:[0,100],
				},
				avgDiameter:{
					min:0
				},
				capacity:{
					min:0
				},
				storageCapacity:{
					min:0
				},
				fillTimes:{
					min:0,
					range:[0,1000000000],
					digits:true,
				},
				tankMaterialType:{
					required:true
				},
				tankType:{
					required:true
				},
				description:{
					maxlength:255
				}
			},
			messages:{
				workDays: {
				range:"0~366!"
				},
				storageCycle: {
					range:"0~366!"
				},
				height:{
					range:"0~100!"
				},
				avgDiameter:{
					min:"最小为0"
				},
				capacity:{
					min:"最小为0"
				},
				storageCapacity:{
					min:"最小为0"
				},
				fillTimes:{
					min:"最小为0",
					range:"0~1000000000!",
					digits:"整数！",
				}
			},
			submitHandler:function(form){
				var options  = {
			        url:"update.jhtml",
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'更新成功！'});
			        	grid1.loadData();
			        	layer.closeAll('page');
			        },
			        error:function(){
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
	<form id="oiltank_form" style="padding:0;width:100%;">

		<input type="hidden" name="id" value="${oilTank.id }" />

		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">材料类型</label>
					<div class="infpCon">
						<div id="tankMaterialType" data-code="储罐【材料】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储罐类型</label>
					<div class="infpCon">
						<div id="tankType" data-code="储罐【类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">高度/长度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" value="${oilTank.height}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均直径</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDiameter" value="${oilTank.avgDiameter}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储罐容量(立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="capacity" value="${oilTank.capacity}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">全年使用天数(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="workDays" value="${oilTank.workDays}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储罐年总储量(吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="storageCapacity" value="${oilTank.storageCapacity}" number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储存周期(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="storageCycle" value="${oilTank.storageCycle}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年装卸/填充次数(次)</label>
					<div class="infpCon">
						<input type="" class="input" name="fillTimes" value="${oilTank.fillTimes}" number=true>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${oilTank.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>