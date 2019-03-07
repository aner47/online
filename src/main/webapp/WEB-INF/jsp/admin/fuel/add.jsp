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
<title>增加分月信息</title>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel","year"],function(hide){
	
	var fuelType = '${fuelType}';
	
	//发电机组
	if(fuelType == 'powerplant'){
		$('#powerPlantId').val('${sourceId}');
	//锅炉	
	}else if(fuelType =='boiler'){
		$('#boilerId').val('${sourceId}');
	//煤气炉	
	}else if(fuelType =='gasstove'){
		$('#gasstoveId').val('${sourceId}');
	//炉窑	
	}else if(fuelType =='kiln'){
		$('#kilnId').val('${sourceId}');
	}
	
	
	
	$('#fuel_form #fuelYear').year({'name':'fuelYear'});
	
	var fuelType = $('#fuel_form #fuelType').select({isCustom: true,name:'fuelType',zIndex:18,param:fuelType});
	
	$("#fuelUnit","#fuel_form").select({name:"fuelUnit",zIndex:17,isCustom : true});
	
		$('#commit').click(function(){
			$("#fuel_form").submit();
		})
		//提交表单
		$("#fuel_form").validate({
			rules: {
				
				fuelType: {
					required:true,
				},
				fuelYear: {
					required:true,
				},
				fuelUnit: {
					checkUnit:/\D/,
						required:true,
				},
				
				description:{
					maxlength:255
				},
				
			},
			messages:{
				fuelUnit:{
					checkUnit:"字符及符号"
				},
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'添加成功！'})
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
	<form id="fuel_form" style="padding: 0; width: 100%;">
		<div class="infLine">

			<div class="infh1">数据信息</div>
			<input type="hidden" class="input" id="powerPlantId"
				name="powerPlantId" /> <input type="hidden" class="input"
				id="boilerId" name="boilerId" /> <input type="hidden" class="input"
				id="gasstoveId" name="gasstoveId" /> <input type="hidden"
				class="input" id="kilnId" name="kilnId" /> <input type="hidden"
				class="input" id="sectionId" name="sectionId" /> <input
				type="hidden" class="input" id="rawMaterialsId"
				name="rawMaterialsId" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料年份</label>
					<div class="infpCon">
						<div id="fuelYear" class="yearF iconfont icon-nianfen"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="燃料【燃料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗总量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" min="0"
							number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="fuelUnit" required> -->
						<div class="selF" id="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelAsh" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料挥发分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelVolatiles" number=true>
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