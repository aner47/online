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
<title>增加石油产品储罐</title>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel"],function(hide){
	$("#tankMaterialType","#oiltank_form").select({name:"tankMaterialType"});
	$("#tankType","#oiltank_form").select({name:"tankType"});
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
				},
				num:{
					min:0,
					digits:true,
					required:true,
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
				},
				num:{
					digits:"整数！",
				},
			},
			submitHandler:function(form){
				var options  = {
			        url:base+"/web/oiltank/save.jhtml",
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'添加成功！'});
			        	grid1.loadData();
			        	layer.closeAll('page');
			        },
			        error:function(){
			        	qy.fail2({title:'添加失败'});
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
					<label for="" class="infpLable">数量</label>
					<div class="infpCon">
						<input type="" class="input" name="num" number=true>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">高度/长度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均直径</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDiameter" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储罐容量(立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="capacity" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">全年使用天数(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="workDays" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储罐年总储量(吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="storageCapacity" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">储存周期(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="storageCycle" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年装卸/填充次数(次)</label>
					<div class="infpCon">
						<input type="" class="input" name="fillTimes" number=true>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

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
</body>
</html>