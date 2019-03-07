<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<meta charset="UTF-8">
<title>增加工段</title>
</head>
<style>
	#labeldown {
		position: relative;
	}
</style>
<script type="text/javascript">
require(["hide","validate","ajaxform","panel"],function(hide){
	var productUnit = $("#productUnit","#section_form").select({name:"product.unit",isCustom: true,zIndex:20,
		change: function(data, value) {
			$('#productCapacityUnit').html('<span>'+value+'</span>');
			}
	});
	$("#useStatus","#section_form").select({filter: false,name:"useStatus",zIndex:20,isCustom : true});
    
	$("#rawMaterials1Unit","#section_form").select({name:"rawMaterials1.unit",isCustom: true,zIndex:19});
	$("#rawMaterials2Unit","#section_form").select({name:"rawMaterials2.unit",isCustom: true,zIndex:18});
	$("#rawMaterials3Unit","#section_form").select({name:"rawMaterials3.unit",isCustom: true,zIndex:17});
	
	$("#fuelType","#section_form").select({name:"fuelType",isCustom: true,zIndex:16});
	$("#fuelUnit","#section_form").select({name:"fuelUnit",isCustom: true,zIndex:15});
	
	
	$("#governanceMeasures1","#section_form").select({name:"governanceMeasures1",multselect:true,zIndex:4,isCustom : true});
	$("#governanceMeasures2","#section_form").select({name:"governanceMeasures2",multselect:true,zIndex:2,isCustom : true});
	$("#governanceMeasures3","#section_form").select({name:"governanceMeasures3",multselect:true,zIndex:3,isCustom : true});
	$("#governanceMeasures4","#section_form").select({name:"governanceMeasures4",multselect:true,zIndex:1,isCustom : true});
	$("#exhaustionHoleId","#section_form").select({name:"exhaustionHoleId"});
	
		$('#commit').click(function(){
			$("#section_form").submit();
		})
		
		//提交表单
		$("#section_form").validate({
			rules: {
				name:{
					required:true
				},
				"product.name":{
					required:true
				},
				"product.yield":{
					required:true,
					min:0,
					maxlength:16
				},
				productionLines:{
					required:true,
					min:0
				},
				productCapacity:{
					required:true,
					min:0,
					maxlength:16
				},
				"rawMaterials1.consumption":{
					min:0,
					maxlength:16
				},
				fuelConsumption:{
					min:0,
					maxlength:16
				},
				"enterpriseEmissionsManagement.exhaustionHole.height":{
					min:0
				},
				"product.unit":{
					checkUnit:/\D/,
					required:true
				},
				"rawMaterials1.unit":{
					checkUnit:/\D/
				},
				"rawMaterials2.unit":{
					checkUnit:/\D/
				},
				"rawMaterials3.unit":{
					checkUnit:/\D/
				},
				fuelUnit:{
					checkUnit:/\D/
				},
				governanceMeasures1:{
					required:true
				},
				governanceMeasures2:{
					required:true
				},
				governanceMeasures3:{
					required:true
				},
				governanceMeasures4:{
					required:true
				},
				description:{
					maxlength:255
				}
			},
			messages:{
				
			},
			submitHandler:function(form){
				/* $("#labeldown .infpCon label").eq(0).css({
					"position": "absolute",
					"top": "8px",
					"right": "0px"
				}) */
				if (!isSubmit($("input[name ='product.name']")
						.val(), $(
						"input[name ='product.yield']")
						.val(), $("input[name ='product.unit']")
						.val())){qy.suc3({title:"产品必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials1.name']")
						.val(), $(
						"input[name ='rawMaterials1.consumption']")
						.val(), $("input[name ='rawMaterials1.unit']")
						.val())){qy.suc3({title:"原辅料信息必须填写3个参数！"});return false}
				if (!isSubmit(
						$(
								"input[name ='fuelType']")
								.val(),
						$(
								"input[name ='fuelConsumption']")
								.val(),
						$(
								"input[name ='fuelUnit']")
								.val())) {
					qy.suc3({
						title : "燃料必须填写3个参数！"
					});
					return false
				}
				var options  = {
			        url:base+'/web/section/simplesave.jhtml',
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
		function isSubmit(str1, str2, str3) {
			if (str1 && str2 && str3) {
				return true;
			}
			if (!str1 && !str2 && !str3) {
				return true
			}
			return false;
		}
		hide.hide();
	});
</script>

<body>
	<form id="section_form" style="padding:0;width:100%;">
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">工段/工艺名称或炉窑类型</label>
					<div class="infpCon">
						<input type="" class="input" name="name">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">生产线数量</label>
					<div class="infpCon">
						<input type="" class="input" name="productionLines" number=true>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">设备/工序使用状态</label>
					<div class="infpCon">
						<div class="selF" id="useStatus" data-code="锅炉【锅炉状态】"></div>
					</div>
				</div>
			</div>

			<div class="infh1">产品信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">产品名称</label>
					<div class="infpCon">
						<input type="" class="input" name="product.name">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年产量</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="product.unit" id="productUnit"> -->
						<div class="selF" id="productUnit" name="product.unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div id="labeldown" class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">设计产能(产品/年)</label>
					<div class="infpCon">
						<input type="" class="input" name="productCapacity" number=true>
					</div>
					<label for="" class="infpUnit" id="productCapacityUnit"></label>
				</div>
			</div>

			<div class="infh1">原辅料1信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.name" required/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.consumption" number=true required/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials1.unit" required/> -->
						<div class="selF" id="rawMaterials1Unit" name="rawMaterials1.unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			<div class="infh1">原辅料2信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.name">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.consumption" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials2.unit"> -->
						<div class="selF" id="rawMaterials2Unit" name="rawMaterials2.unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			<div class="infh1">原辅料3信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.name">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.consumption" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials3.unit"> -->
						<div class="selF" id="rawMaterials3Unit" name="rawMaterials3.unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="fuelType"> -->
						<div id="fuelType" data-code="燃料类型" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="fuelUnit"> -->
						<div class="selF" id="fuelUnit" name="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infh1">污染物治理措施（可多选）</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硫工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="简表【脱硫工艺】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硝工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="简表【脱硝工艺】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">除尘工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="简表【除尘工艺】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs措施</label>
					<div class="infpCon">
						<div id="governanceMeasures4" data-code="简表【VOC措施】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口高度（米）</label>
					<div class="infpCon">
						<input type="" class="input" name="enterpriseEmissionsManagement.exhaustionHole.height" number=true>
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
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>