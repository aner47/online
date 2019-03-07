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
<title>修改燃料</title>
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
	
	$('#fuel_form #fuelYear').year({'name':'fuelYear',defaultValue:'${fuel.fuelYear}'})
	
	var fuelType = $('#fuel_form #fuelType').select({isCustom: true,name:'fuelType',zIndex:18,param:fuelType,defaultValue:'${fuel.fuelType}'});
	
	$("#fuelUnit","#fuel_form").select({name:"fuelUnit",zIndex:17,isCustom : true,defaultValue:"${fuel.fuelUnit}"});
	
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
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'更新成功！'})
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
	<form id="fuel_form" style="padding: 0; width: 100%;">

		<input type="hidden" name="id" value="${fuel.id }" /> <input
			type="hidden" class="input" id="powerPlantId" name="powerPlantId"
			value="${fuel.powerPlantId }" /> <input type="hidden"
			class="input" id="boilerId" name="boilerId"
			value="${fuel.boilerId }" /> <input type="hidden"
			class="input" id="gasstoveId" name="gasstoveId"
			value="${fuel.gasstoveId }" /> <input type="hidden"
			class="input" id="kilnId" name="kilnId"
			value="${fuel.kilnId }" /> 

		<div class="infLine">

			<div class="infh1">数据信息</div>



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
						value="${fuel.fuelConsumption }"
							number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="fuelUnit" value="${fuel.fuelUnit }" required /> --%>
						<div class="selF" id="fuelUnit" data-code="数量单位" ></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" value="${fuel.fuelSulfurContent }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelAsh" value="${fuel.fuelAsh }" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料挥发分(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelVolatiles" value="${fuel.fuelVolatiles }" number=true>
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${fuel.description }</textarea>
					</div>
				</div>
			</div>

		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>