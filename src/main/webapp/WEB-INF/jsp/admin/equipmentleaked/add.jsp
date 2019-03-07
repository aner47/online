<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加设备泄露</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<style>
#noDevelopLdar-error{
	right:-10px;
}
</style>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform"],function(hide){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		$("#estimationEethod", "#inputForm").select({
			name : "estimationEethod",
			zIndex : 10
		});
		// $("#standard", "#inputForm").select({
		// 	name : "standard",
		// 	zIndex : 9
		// });
		// $("#domesticLevel", "#inputForm").select({
		// 	name : "domesticLevel",
		// 	zIndex : 8
		// });
		// $("#domesticAdvancedLevel", "#inputForm").select({
		// 	name : "domesticAdvancedLevel",
		// 	zIndex : 7
		// });
		//提交表单
		$("#inputForm").validate({
			rules: {
				production:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				involvedVoc:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				developLdar:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				developLdarTime:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				noDevelopLdar:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				exemption:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				controlledSealedPoint:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				unreachablePoint:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				leakedEmissions:{
					min:0,
					number:true,
					maxlength:9
				},
				reducePotential:{
					min:0,
					number:true,
					maxlength:9
				},
				valveGas:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				valveLightLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				valveHeavyLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				pumpLightLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				pumpHeavyLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				compressor:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				safetyValveGas:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				safetyValveLightLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				safetyValveHeavyLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				flangeGas:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				flangeLightLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				flangeHeavyLiquid:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				openPiping:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
				samplingLinks:{
					min:0,
					digits:true,
					number:true,
					maxlength:9
				},
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
			        },
			        error:function(){
			        	layer.msg('添加失败',{icon:3});
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
	<form class="hideCls" id="inputForm" style="padding:0;width:100%;">
		
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">生产装置总数（套）</label>
					<div class="infpCon">
						<input type="" class="input" id="production" name="production" placeholder="生产装置总数（套）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">涉及voc装置（套）</label>
					<div class="infpCon">
						<input type="" class="input" id="involvedVoc" name="involvedVoc" placeholder="涉及voc装置（套）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">开展LDAR装置（套）</label>
					<div class="infpCon">
						<input type="" class="input" id="developLdar" name="developLdar" placeholder="开展LDAR设备数量（套）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">开展LDAR（轮）</label>
					<div class="infpCon">
						<input type="" class="input" id="developLdarTime" name="developLdarTime" placeholder="开展LDAR（轮）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">未开展LDAR设备数量（套）</label>
					<div class="infpCon">
						<input type="" class="input" id="noDevelopLdar" name="noDevelopLdar" placeholder="未开展LDAR设备数量（套）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">豁免装置（套）</label>
					<div class="infpCon">
						<input type="" class="input" id="exemption" name="exemption" placeholder="豁免装置（套）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">受控密封点（个）</label>
					<div class="infpCon">
						<input type="" class="input" id="controlledSealedPoint" name="controlledSealedPoint" placeholder="受控密封点（个）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">不可达点（个）</label>
					<div class="infpCon">
						<input type="" class="input" id="unreachablePoint" name="unreachablePoint" placeholder="不可达点（个）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">估算方法</label>
					<div class="infpCon">
						<div  data-code="泄漏信息【估算方法】" id="estimationEethod" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">泄露排放量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" id="leakedEmissions" name="leakedEmissions" placeholder="泄露排放量（吨）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">是否达标</label>
					<div class="infpCon">
						<input type="radio" class="radio" name="standard" id="cheA1" value="true" >
						<label for="cheA1" class="label">是</label>
						<input type="radio" class="radio" name="standard" id="cheA2" value="false" >
						<label for="cheA2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">是否达到国内水平</label>
					<div class="infpCon">
						<input type="radio" class="radio" name="domesticLevel" id="cheB1" value="true" >
						<label for="cheB1" class="label">是</label>
						<input type="radio" class="radio" name="domesticLevel" id="cheB2" value="false" >
						<label for="cheB2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">消减潜力（吨）</label>
					<div class="infpCon">
						<input type="" class="input" id="reducePotential" name="reducePotential" placeholder="消减潜力（吨）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">是否达到国内先进水平</label>
					<div class="infpCon">
						<input type="radio" class="radio" name="domesticAdvancedLevel" id="cheC1" value="true" >
						<label for="cheC1" class="label">是</label>
						<input type="radio" class="radio" name="domesticAdvancedLevel" id="cheC2" value="false" >
						<label for="cheC2" class="label" style="margin-right:0;">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">阀气体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="valveGas" name="valveGas" placeholder="阀气体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">阀轻液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="valveLightLiquid" name="valveLightLiquid" placeholder="阀轻液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">阀重液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="valveHeavyLiquid" name="valveHeavyLiquid" placeholder="阀重液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">泵轻液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="pumpLightLiquid" name="pumpLightLiquid" placeholder="泵轻液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">泵重液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="pumpHeavyLiquid" name="pumpHeavyLiquid" placeholder="泵重液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">压缩机（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="compressor" name="compressor" placeholder="压缩机（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">安全阀气体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="safetyValveGas" name="safetyValveGas" placeholder="安全阀气体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">安全阀轻液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="safetyValveLightLiquid" name="safetyValveLightLiquid" placeholder="安全阀轻液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">安全阀重液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="safetyValveHeavyLiquid" name="safetyValveHeavyLiquid" placeholder="安全阀重液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">法兰气体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="flangeGas" name="flangeGas" placeholder="法兰气体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">法兰轻液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="flangeLightLiquid" name="flangeLightLiquid" placeholder="法兰轻液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">法兰重液体（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="flangeHeavyLiquid" name="flangeHeavyLiquid" placeholder="法兰重液体（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">开口管线（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="openPiping" name="openPiping" placeholder="开口管线（件）" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">采样链接（件）</label>
					<div class="infpCon">
						<input type="" class="input" id="samplingLinks" name="samplingLinks" placeholder="采样链接（件）" number="true">
					</div>
				</div>
			</div>

		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>