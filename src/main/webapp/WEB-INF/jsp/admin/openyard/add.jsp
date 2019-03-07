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
<title>增加露天堆场信息</title>
<style type="text/css">
	#materialType-error{
		position:absolute;
		right:20px;
		bottom:4px;
	}
	#loadAmount-error,#cargoTrips-error,#carryAmount-error,#area-error,#height-error{
		position:absolute;
		right:0px;
	}
	#moistureContent-error{
		position:absolute;
		left:100px;
	}
</style>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel"],function(hide){
	$('[name=broken]').on('change', function(){
		  if ($(this).val() === 'true') {
			  $('#cheC1').removeAttr('disabled')
			$('#cheC1').removeAttr('checked')		
			} else {
				$('#cheC1').attr('disabled', 'disabled')
				$('#cheC1').removeAttr('checked')
			}
	  })	
	
	$('#commit').click(function(){
			$("#openyard_form").submit();
		})
		var material = $("#material","#openyard_form").select({name:"material",zIndex:9,isCustom : true});
		var materialType = $("#materialType","#openyard_form").select({name:"materialType",zIndex:8,isCustom : true});
		var measure1 = $("#measure1","#openyard_form").select({name:"measure1",zIndex:7,isCustom : true});
		var measure2 = $("#measure2","#openyard_form").select({name:"measure2",zIndex:6,isCustom : true});
		var measure3 = $("#measure3","#openyard_form").select({name:"measure3",zIndex:5,isCustom : true});
		
		
		
		//提交表单
		$("#openyard_form").validate({
			rules: {
				moistureContent: {
					range: [0,100]
				},
				materialType: {
					required:true
				},
				
				loadAmount: {
					required:true,
					min:0
				},
				area: {
					required:true,
					range: [0,1000000]
				},
				cargoTrips: {
					min:0
				},
				carryAmount: {
					min:0
				},
				height: {
					range: [0,50]
				},
				material: {
					required:true
				},
				measure1: {
					required:true
				},
				carCleaning: {
					required:true
				},
				broken: {
					required:true
				},
				closed: {
					required:true
				},
				unloadCoalClosed: {
					required:true
				},
				description:{
					maxlength:255
				}
			},
			messages:{
				materialType: {
					required:"必选字段"
				},
				moistureContent: {
					range: "0~100!"
				},
				loadAmount:{
					min:"最小为0"
				},
				cargoTrips:{
					min:"最小为0"
				},
				carryAmount:{
					min:"最小为0"
				},
				area:{
					range: "0~1000000!"
				},
				height:{
					range: "0~50!"
				},
				description:{
					maxlength:"最多255字"
				}
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
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
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form class="hideCls" id="openyard_form" style="padding:0;width:100%;">
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>堆料类型</label>
					<div class="infpCon">
						<div id="materialType" data-code="祥表【堆料类型】" class="selF"></div>
					</div>
				</div>
			</div>
			
			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>料堆数量</label>
					<div class="infpCon">
						<input type="" class="input" name="pileNum" number="true" required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>料堆占地面积(平方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="area" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料堆最高高度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>堆场装卸总量(吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="loadAmount" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每车运载量(吨/车)</label>
					<div class="infpCon">
						<input type="" class="input" name="carryAmount" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每年物料运载车次(车)</label>
					<div class="infpCon">
						<input type="" class="input" name="cargoTrips" number="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>料堆材料</label>
					<div class="infpCon">
						<div id="material" data-code="详表【料堆材料】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">物料含水率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="moistureContent" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>扬尘控制措施1</label>
					<div class="infpCon">
						<div id="measure1" data-code="控尘措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">扬尘控制措施2</label>
					<div class="infpCon">
						<div id="measure2" data-code="控尘措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">扬尘控制措施3</label>
					<div class="infpCon">
						<div id="measure3" data-code="控尘措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>出入车辆是否清洗</label>
					<div class="infpCon errorType2">
						<input type="radio" class="radio" id="cheA1" name="carCleaning" value="true">
						<label for="cheA1" class="label">是</label>
						<input type="radio" class="radio" id="cheA2" name="carCleaning" value="false">
						<label for="cheA2" class="label" style="margin-right: 0;">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否进行破碎/筛选</label>
					<div class="infpCon errorType2">
						<input type="radio" class="radio" id="cheB1" name="broken" value="true">
						<label for="cheB1" class="label">是</label>
						<input type="radio" class="radio" id="cheB2" name="broken" value="false">
						<label for="cheB2" class="label" style="margin-right: 0;">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>破碎环境是否封闭</label>
					<div class="infpCon errorType2">
						<input type="radio" class="radio" id="cheC1" name="closed" value="true">
						<label for="cheC1" class="label">是</label>
						<input type="radio" class="radio" id="cheC2" name="closed" value="false">
						<label for="cheC2" class="label" style="margin-right: 0;">否</label>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>卸煤沟是否封闭</label>
					<div class="infpCon errorType2">
						<input type="radio" class="radio" id="unloadCoalClosed1" name="unloadCoalClosed" value="true">
						<label for="unloadCoalClosed1" class="label">是</label>
						<input type="radio" class="radio" id="unloadCoalClosed2" name="unloadCoalClosed" value="false">
						<label for="unloadCoalClosed2" class="label" style="margin-right: 0;">否</label>
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