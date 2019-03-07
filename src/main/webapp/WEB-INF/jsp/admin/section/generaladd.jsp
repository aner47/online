<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<meta charset="UTF-8">
<title>增加工段</title>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","panel","date"],function(hide){
	
	$("#putDate1", "#section_form").jeDate({
        format:"YYYY-MM",
        isTime:false, 
        zIndex:999999900,
    })
	$("#putDate2", "#section_form").jeDate({
        format:"YYYY-MM",
        isTime:false, 
        zIndex:999999900,
    })
	$("#putDate3", "#section_form").jeDate({
        format:"YYYY-MM",
        isTime:false, 
        zIndex:999999900,
    })
	$("#putDate4", "#section_form").jeDate({
        format:"YYYY-MM",
        isTime:false, 
        zIndex:999999900,
    })
    
	var productUnit = $("#productUnit","#section_form").select({name:"productUnit",zIndex:20});
	
	
	
	
	$("#year","#section_form").select({name:"year",zIndex:6});
	$("#fuelType","#section_form").select({name:"fuelType",zIndex:5});
	$("#governanceMeasures1","#section_form").select({name:"governanceMeasures1",multselect:true,zIndex:4});
	$("#governanceMeasures2","#section_form").select({name:"governanceMeasures2",multselect:true,zIndex:3});
	$("#governanceMeasures3","#section_form").select({name:"governanceMeasures3",multselect:true,zIndex:2});
	$("#governanceMeasures4","#section_form").select({name:"governanceMeasures4",multselect:true,zIndex:1});
	$("#exhaustionHoleId","#section_form").select({name:"exhaustionHoleId"});
	
	$('#productUnit').on('change',function(){
		if ($(this).val()) {
			$('#productCapacityUnit').html('<span>'+$(this).val()+'</span>');
		}else{
			$('#productCapacityUnit').html('');
		};
	})
		$('#commit').click(function(){
			$("#section_form").submit();
		})
		
		var month = $("[name=productionMonth]").val();
				if (month) {
					$.each(month.split(","), function(i, v) {
						$("[name=productionMonthTemp][value=" + v + "]").attr(
								"checked", "checked");
					})
				}
				
		
		//提交表单
		$("#section_form").validate({
			rules: {
				year:{
					required:true
				},
				putDate1:{
					required:true
				},
				putDate2:{
					required:true
				},
				putDate3:{
					required:true
				},
				putDate4:{
					required:true
				},
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
				var month = [];
				$("[name=productionMonthTemp]:checked").each(function(i, v) {
					month.push($(v).val())
				});
				$("[name=productionMonth]").val(month.join(","));
				if (!isSubmit($("input[name ='product.name']").val(), 
						$("input[name ='product.yield']").val(), 
						$("input[name ='product.unit']").val()))
					{qy.suc3({title:"产品必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials1.name']").val(), 
						$("input[name ='rawMaterials1.consumption']").val(), 
						$("input[name ='rawMaterials1.unit']").val()))
					{qy.suc3({title:"原料信息必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='fuelType']").val(),
						$("input[name ='fuelConsumption']").val(),
						$("input[name ='fuelUnit']").val()))
					{qy.suc3({title : "燃料必须填写3个参数！"});return false}
				var options  = {
			        url:base+'/web/section/generalsave.jhtml',
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
					<label for="" class="infpLable">年份</label>
					<div class="infpCon">
						<div id="year" data-code="工段【年份】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">工段名称</label>
					<div class="infpCon">
						<input type="" class="input" name="name">
					</div>
				</div>
			</div>

			<div class="infPara infPara2">
					<input type="hidden" name="productionMonth">
					<div class="infParaCon">
						<label for="" class="infpLable">生产月份</label>
						<div class="infpCon">
						<div class="months">
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="1" id="cheB1">
							<div class="label2f">
								<label for="cheB1" class="label2">一月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="2" id="cheB2">
							<div class="label2f">
								<label for="cheB2" class="label2">二月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="3" id="cheB3">
							<div class="label2f">
								<label for="cheB3" class="label2">三月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="4" id="cheB4">
							<div class="label2f">
								<label for="cheB4" class="label2">四月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="5" id="cheB5">
							<div class="label2f">
								<label for="cheB5" class="label2">五月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="6" id="cheB6">
							<div class="label2f">
								<label for="cheB6" class="label2">六月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="7" id="cheB7">
							<div class="label2f">
								<label for="cheB7" class="label2">七月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="8" id="cheB8">
							<div class="label2f">
								<label for="cheB8" class="label2">八月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="9" id="cheB9">
							<div class="label2f">
								<label for="cheB9" class="label2">九月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="10" id="cheB10">
							<div class="label2f">
								<label for="cheB10" class="label2">十月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="11" id="cheB11">
							<div class="label2f">
								<label for="cheB11" class="label2">十一月</label>
							</div>
							<input type="checkbox" class="checkbox"
								name="productionMonthTemp" value="12" id="cheB12">
							<div class="label2f">
								<label for="cheB12" class="label2">十二月</label>
							</div>
						</div>
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
					<label for="" class="infpLable">年/月产量</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="product.unit" id="productUnit"> -->
						<div class="selF" id="productUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">设计生产能力</label>
					<div class="infpCon">
						<input type="" class="input" name="productCapacity" number=true>
					</div>
					<label for="" class="infpUnit" id="productCapacityUnit"></label>
				</div>
			</div>

			<div class="infh1">原料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.name">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年/月消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.consumption" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.unit">
					</div>
				</div>
			</div>

			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
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
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelUnit">
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
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate1" name="putDate1" class="Yinput" onfocus=this.blur() required>
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
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate2" name="putDate2" class="Yinput" onfocus=this.blur() required>
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
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate3" name="putDate3" class="Yinput" onfocus=this.blur() required>
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
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" id="putDate4" name="putDate4" class="Yinput" onfocus=this.blur() required>
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
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>