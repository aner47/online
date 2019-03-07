<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>基本信息</title>
<script type="text/javascript">
	require(["hide", "grid", "select", "panel", "validate", "ajaxform" ,"date","year","commonBase"], function(hide) {
		
			$("body").on("click","#confirm",function() {
				$("#product_form").submit();
			})
			
			window.complete=function(){
				var forenoonManufactureObj={0:"0", 1:"01", 2: "02", 3: "03", 4: "04", 5:"05",6:"06",7:"07",8:"08",9:"09",10:"10",11:'11',12:'12'};
				var afternoonManufactureObj={12:"12",13:"13",14:"14",15:"15",16:"16",17:"17",18:'18'};
				var nightManufactureObj={18:"18",19:"19",20:"20",21:"21",22:"22",23:"23",24:'24'};
				// 上午
				$("#forenoonManufacture", "#product_form").select({name:"forenoonManufacture"
					,simpleData:forenoonManufactureObj,zIndex:8,defaultValue:parseInt('${baseInformation.forenoonManufacture}')});
				$("#forenoonManufactureEnd", "#product_form").select({name:"forenoonManufactureEnd"
					,simpleData:forenoonManufactureObj,zIndex:8,defaultValue:parseInt('${baseInformation.forenoonManufactureEnd}')});
				//下午
				$("#afternoonManufacture", "#product_form").select({name:"afternoonManufacture"
					,simpleData:afternoonManufactureObj,zIndex:8,defaultValue:parseInt('${baseInformation.afternoonManufacture}')});
				$("#afternoonManufactureEnd", "#product_form").select({name:"afternoonManufactureEnd"
					,simpleData:afternoonManufactureObj,zIndex:8,defaultValue:parseInt('${baseInformation.afternoonManufactureEnd}')});
				//晚上
				$("#nightManufacture", "#product_form").select({name:"nightManufacture"
					,simpleData:nightManufactureObj,zIndex:8,defaultValue:parseInt('${baseInformation.nightManufacture}')});
				$("#nightManufactureEnd", "#product_form").select({name:"nightManufactureEnd"
					,simpleData:nightManufactureObj,zIndex:8,defaultValue:parseInt('${baseInformation.nightManufactureEnd}')});
				
				
				
				// $('#putProductionYear').year({'name':'putProductionYear',defaultValue:'${baseInformation.putProductionYear}'})
				$("#putProductionYear", "#product_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        });
				$("#rebuildTime", "#product_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        });
				$("#stopTime", "#product_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        });
				$("#installTime", "#product_form").jeDate({
		            format:"YYYY-MM",
		            isTime:false, 
		            zIndex:999999900
		        });
				
		        
				 $("#enterpriseState", "#product_form").select({
					name : "enterpriseState",
					defaultValue : '${baseInformation.enterpriseState}',
					zIndex : 10,
					change:function(event, value){
						show(value);
					}
				});
				 
				 /* 日期比较-开始  */
					function duibi(d1,d2)
					{
				  		//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
				  		return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
				  	}
				  	/* 日期比较-结束 */
				  	
				 
				// 关停/取缔 或者 停产, 改为非必填项, 正常生产时保留必填项
				var changeNames = ["productionMonthTemp"];
				var changeNames2 = ["stopTime"]
				 if('${baseInformation.enterpriseState}'){
					 show('${baseInformation.enterpriseState}');
				 }
				
			  	// 控制关停时间的显示
				 function show(value){
					 if(value == '关停/取缔' || value == '停产'){
							$("#stopTimeDiv").show();
							$("#stopTime").attr("disabled", false);
					}else{
							$("#stopTimeDiv").hide();
							$("#stopTime").attr("disabled", true);
					}
					 for(var i = 0; i < changeNames.length; i++){
							if(value == '正常生产'){
								$('[name='+changeNames[i]+']', "#product_form").attr('required','true');
								 $("#dayHours").attr('required', true);
							}else{
								$('[name='+changeNames[i]+']', "#product_form").removeAttr('required');
								 $("#dayHours").removeAttr('required');
							}
							if (value === '关停/取缔' || value == '停产') {
								$('[name='+changeNames2[i]+']', "#product_form").attr('required','true');
							}else{
								$('[name='+changeNames2[i]+']', "#product_form").removeAttr('required');
							}
							
					}
					
				 }
				 
				 $("#emphasisPolluteSource", "#product_form").select({
					name : "emphasisPolluteSource",
					defaultValue : '${baseInformation.emphasisPolluteSource}',
					zIndex : 7
				});
				var month = $("[name=productionMonth]").val();
				if (month) {
					$.each(month.split(","), function(i, v) {
						$("[name=productionMonthTemp][value=" + v + "]").attr(
								"checked", "checked");
					})
				}
				
				//提交表单
				$("#product_form").validate({
					rules : {
						status: {
							required: true
						},
						isDrainContaminationCoverage: {
							required: true
						},
						isPaint: {
							required: true
						},
						fuelUnit:{
							checkUnit:/\D/
						},
						workDay : {
							range:[0,366]
						},
						dayHours : {
							range:[0,24]
						},
						grossProduct : {
							min : 0,
							maxlength:16
						},
						energyUsed : {
							min : 0,
							maxlength:16
						},
						nomalProductionHour : {
							min : 0
						},
						so2ControlLimitationValue : {
							min : 0,
							maxlength:7
						},
						so2ExamineDischargeValue : {
							min : 0,
							maxlength:7
						},
						noxControlLimitationValue : {
							min : 0,
							maxlength:7
						},
						noxExamineDischargeValue : {
							min : 0,
							maxlength:7
						},
						so2Statistics : {
							min : 0,
							maxlength:7
						},
						noxStatistics : {
							min : 0,
							maxlength:7
						},
						smokeStive : {
							min : 0,
							maxlength:7
						},
						vocStatistics : {
							min : 0,
							maxlength:7
						},
						sewageDischargeValue : {
							min : 0,
							maxlength:7
						},
						effluentDisposeRate : {
							range:[0,100]
						},
						licenceNo:{
							maxlength:23
						},
						proportion1:{
							range:[0,100]
						},
						proportion2:{
							range:[0,100]
						},
						proportion3:{
							range:[0,100]
						},
						oilproportion1:{
							range:[0,100]
						},
						oilproportion2:{
							range:[0,100]
						},
						oilproportion3:{
							range:[0,100]
						},
						kitchenNum:{
							min:0,
							digits:true,
							maxlength:10
						},
						specialtyPeoples:{
							min:0,
							maxlength:7
						},
						smokeDischargeRate:{
							min:0,
							maxlength:7
						},
						lampblackWipeRate:{
							range:[0,100]
						},
						putProductionYear:{
							required:true				
						},
						enterpriseState: {
							required:true
						},
						grossExamineCoverage:{
							required:true				
						},
						statisticsCoverage:{
							required:true				
						},
						description:{
							maxlength:255
						}
					},
					messages : {
						grossProduct : {
							maxlength:"最多16位"
						},
						energyUsed : {
							maxlength:"最多16位"
						},
						licenceNo:{
							maxlength:"最多23位"
						},
						kitchenNum:{
							digits:"整数！",
							maxlength:"最多10位"
						},
						specialtyPeoples:{
							digits:"整数！",
							maxlength:"最多7位"
						},
						smokeDischargeRate:{
							maxlength:"最多7位"
						},
						so2ControlLimitationValue : {
							maxlength:"最多7位"
						},
						so2ExamineDischargeValue : {
							maxlength:"最多7位"
						},
						noxControlLimitationValue : {
							maxlength:"最多7位"
						},
						noxExamineDischargeValue : {
							maxlength:"最多7位"
						},
						so2Statistics : {
							maxlength:"最多7位"
						},
						noxStatistics : {
							maxlength:"最多7位"
						},
						smokeStive : {
							maxlength:"最多7位"
						},
						vocStatistics : {
							maxlength:"最多7位"
						},
						sewageDischargeValue : {
							maxlength:"最多7位"
						},
					},
					submitHandler : function(form) {
						
						var month = [];
						$("[name=productionMonthTemp]:checked").each(function(i, v) {
							month.push($(v).val())
						});
						$("[name=productionMonth]").val(month.join(","));
						
						// 将开业时间存储到cookie
						document.cookie="putProductionYear="+$("#putProductionYear").val()+ ";expires=Thu, 18 Dec 4040 12:00:00 GMT;path=/";
						
						//日期比较
			              if(!$("#product_form #stopTime").attr("disabled")){
			            	  if(duibi($("#product_form #putProductionYear").val(), $("#product_form #stopTime").val())){
			  					layer.msg('关停时间不能早于开业时间', {icon:3});
			  					return false;
			  				} 
			              }
			              if(new Date(Date.parse($("#product_form #rebuildTime").val()))>new Date()){
			  					layer.msg('改扩建时间不能晚于当前时间', {icon:3});
			  					return false;
			  				} 
			              
			            var _index;
						var options = {
							url : '../baseinformation/save.jhtml',
							type : 'post',
							dataType : 'json',
							beforeSubmit: function() {
								_index = top.layer.load(2);
								return true;
							},
							success : function(data) {
								qy.suc2({
									title : '保存成功！'
								});
								top.layer.close(_index);
							},
							error : function() {
								qy.fail2({
									title : '保存失败！'
								});
								top.layer.close(_index);
							}
						};
						$(form).ajaxSubmit(options);
						return false;
					}
				});
				
				//判断排污许可是否覆盖选择否
				 var isDrainContaminationCoverage= $("[name=isDrainContaminationCoverage]:checked").val();
				 if(isDrainContaminationCoverage == 'true'){
		        	 $("input[name=licenceNo]").removeAttr('disabled');
		        	 $("input[name=so2Discharge]").removeAttr('disabled');
		        	 $("input[name=noxDischarge]").removeAttr('disabled');
		        	 $("input[name=smokeDischarge]").removeAttr('disabled');
		        	 $("input[name=vocDischarge]").removeAttr('disabled');
		         }else{
		        	 $("input[name=licenceNo]").attr({disabled:'true'});
		        	 $("input[name=so2Discharge]").attr({disabled:'true'});
		        	 $("input[name=noxDischarge]").attr({disabled:'true'});
		        	 $("input[name=smokeDischarge]").attr({disabled:'true'});
		        	 $("input[name=vocDischarge]").attr({disabled:'true'});
		         }  
				 
				 $("[name=isDrainContaminationCoverage]").on('change',function(){
						if ($(this).val()=='true') {
							$("input[name=licenceNo]").removeAttr('disabled');
							$("input[name=so2Discharge]").removeAttr('disabled');
							$("input[name=noxDischarge]").removeAttr('disabled');
							$("input[name=smokeDischarge]").removeAttr('disabled');
							$("input[name=vocDischarge]").removeAttr('disabled');
							
						}else{
							$("input[name=licenceNo]").attr({disabled:'true'});
							$("input[name=so2Discharge]").attr({disabled:'true'});
							$("input[name=noxDischarge]").attr({disabled:'true'});
							$("input[name=smokeDischarge]").attr({disabled:'true'});
							$("input[name=vocDischarge]").attr({disabled:'true'});
						};
					})
					
				 
				//判断总量核查是否选择否
				 var grossExamineCoverage= $("[name=grossExamineCoverage]:checked").val();
				 if(grossExamineCoverage == 'true'){
		        	 $("input[name=so2ControlLimitationValue]").removeAttr('disabled');
		        	 $("input[name=so2ExamineDischargeValue]").removeAttr('disabled');
		        	 $("input[name=noxControlLimitationValue]").removeAttr('disabled');
		        	 $("input[name=noxExamineDischargeValue]").removeAttr('disabled');
		         }else{
		        	 $("input[name=so2ControlLimitationValue]").attr({disabled:'true'});
		        	 $("input[name=so2ExamineDischargeValue]").attr({disabled:'true'});
		        	 $("input[name=noxControlLimitationValue]").attr({disabled:'true'});
		        	 $("input[name=noxExamineDischargeValue]").attr({disabled:'true'});
		         }           
			
				$("[name=grossExamineCoverage]").on('change',function(){
					if ($(this).val()=='true') {
						$("input[name=so2ControlLimitationValue]").removeAttr('disabled');
						$("input[name=so2ExamineDischargeValue]").removeAttr('disabled');
						$("input[name=noxControlLimitationValue]").removeAttr('disabled');
						$("input[name=noxExamineDischargeValue]").removeAttr('disabled');
						
					}else{
						$("input[name=so2ControlLimitationValue]").attr({disabled:'true'});
						$("input[name=so2ExamineDischargeValue]").attr({disabled:'true'});
						$("input[name=noxControlLimitationValue]").attr({disabled:'true'});
						$("input[name=noxExamineDischargeValue]").attr({disabled:'true'});
					};
				})
				
				//判断环统是否选择否
				var statisticsCoverage= $("[name=statisticsCoverage]:checked").val();
				 if(statisticsCoverage == 'true'){
		       	 $("input[name=so2Statistics]").removeAttr('disabled');
		       	 $("input[name=noxStatistics]").removeAttr('disabled');
		       	 $("input[name=smokeStive]").removeAttr('disabled');
		       	 $("input[name=vocStatistics]").removeAttr('disabled');
		        }else{
		       	 $("input[name=so2Statistics]").attr({disabled:'true'});
		       	 $("input[name=noxStatistics]").attr({disabled:'true'});
		       	 $("input[name=smokeStive]").attr({disabled:'true'});
		       	 $("input[name=vocStatistics]").attr({disabled:'true'});
		        }           
			
				$("[name=statisticsCoverage]").on('change',function(){
					if ($(this).val()=='true') {
						$("input[name=so2Statistics]").removeAttr('disabled');
						$("input[name=noxStatistics]").removeAttr('disabled');
						$("input[name=smokeStive]").removeAttr('disabled');
						$("input[name=vocStatistics]").removeAttr('disabled');
						
					}else{
						$("input[name=so2Statistics]").attr({disabled:'true'});
						$("input[name=noxStatistics]").attr({disabled:'true'});
						$("input[name=smokeStive]").attr({disabled:'true'});
						$("input[name=vocStatistics]").attr({disabled:'true'});
					};
				})	
				
				$("#fixed_return").on("click", function() {
					window.location.href = "../enterprise/enterpriseTaskList.jhtml";
					top.loadMenu('188');
					top.layer.closeAll('page');
				})
				$("#back").on("click", function() {
					window.location.href = "../enterprise/list.jhtml";
					top.loadMenu('2','31');
				})
				
				/* $("#check").click(function(){
					var id = ${enterprise.id};
					
					var opts = {
						title: "审核",
						url : "checkPage.jhtml",
						data:{"enterprieId": id},
						btn: ['通过', '不通过'],
						yes : function(){
							$("#inputForm #status").val("3");
							$("#inputForm").submit();
							
						},
						btn2:function(){
							$("#inputForm #status").val("4");
							$("#inputForm").submit();
							
						},
						cancel:function(){}
					}
					qy.panel(opts);
					
					
				}); */
				
				$("#check").on("click", function() {
					var id = ${enterprise.id};
					top.panel1(id);
				})
			}
			/* 日期比较-结束 */
			hide.hide();
		 
	})

</script>


<style>
.block .buttonF {
	padding-right: 6px;
	padding-bottom: 18px;
}
.jedateblue .mainfoot .btnscon .today{
	display: none;
}
.examine {
	width: 42px;
	height: 30px;
	line-height: 30px!important;
	text-align: center;
	color: #fff !important;
	border-radius: 5px;
	background: #4fc1ee;
	margin-top: 9px !important;
}
</style>
</head>
<body class="hideCls">
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #4fc1ee;">注意: * 表示必填项</h4>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
	
	<div class="block hAuto">
		<h1 class="title">
			<c:if test="${principal.userType != 'system'}">
				<i style="padding-left: 20px;width: 0;margin:0;" class="iconfont icon-shengchanxinxi2"></i> 基本信息
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<i class="iconfont icon-shengchanxinxi2"></i>
				<span>企业名称：${enterprise.name }</span>
			</c:if>
			
			<c:if test="${principal.userType != 'system'}">
				<div id="back" class="titleBtn">返回</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
				<c:if test="${sysEnterUser.taskStatus != 'approve'}">
					<div id="check" class="titleBtn examine">审核</div>
				</c:if>
			</c:if>
		</h1>
	</div>
	<div class="block hAuto" style="min-height: calc(100% - 65px);">
		<form id="product_form">
			<input type="hidden" name="id" value="${baseInformation.id}" />
			
			<input type="hidden" id="isSave" name="isSave" value="true" />
			
			<div class="infLine">
				<!-- <div style="width: 100%; height: 50px;line-height: 50px;padding-left:20px;background: #d2d2d2;">企业基本信息</div> -->
				
				<c:if test="${principal.userType == 'investigator'}">
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${baseInformation.inquirer}" required>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${principal.userType == 'investigator'}">
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${baseInformation.inquirerPhone}" required>
						</div>
					</div>
				</div>
				</c:if>
				<div class="infPara infPara2">
					<div class="infParaCon">
					<div class="infParaCon" style="width: 48%;">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>企业状态</label>
						<div class="infpCon">
							<div data-code="简表【企业状态】" id="enterpriseState" class="selF"></div>
						</div>
					</div>
					<div class="infParaCon" id = "stopTimeDiv" style="width: 20%; margin-left: 50px">
						<label for="" class="infpLable">关停时间</label>
						<input
							style="padding: 0 4px; border-radius: 4px; border: 1px solid #d2d2d2"
							type="" class="input" id="stopTime"
							value="<fmt:formatDate value="${baseInformation.stopTime}" pattern="YYYY-MM"/>"
						 	name="stopTime"
							onfocus="this.blur()" />
					</div>
					</div>
				</div>
				
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>开业时间</label> 
						<input
							style="padding: 0 4px; border-radius: 4px; border: 1px solid #d2d2d2"
							type="" class="input" id="putProductionYear"
							value="${baseInformation.putProductionYear}"
							<%-- value="<fmt:formatDate value='${baseInformation.putProductionYear}' pattern='yyyy-MM'/>" --%>
						 name="putProductionYear"
							onfocus="this.blur()" />
						<!-- <div class="infpCon">
                            <div id="putProductionYear" class="yearF iconfont icon-nianfen"></div>
                        </div> -->
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">改扩建时间</label> <input
							style="padding: 0 4px; border-radius: 4px; border: 1px solid #d2d2d2"
							type="" class="input" id="rebuildTime"
							value="${baseInformation.rebuildTime}"
							<%-- value="<fmt:formatDate value="${baseInformation.rebuildTime}" pattern="YYYY-MM"/>" --%>
						 	name="rebuildTime"
							onfocus="this.blur()" />
					</div>
				</div>

				<div class="infPara infPara2">
					<input type="hidden" name="productionMonth"
						value="${baseInformation.productionMonth}">
					<div class="infParaCon">
						<label for="" class="infpLable">生产月份</label>
						<div id="infpCon_tom" class="infpCon errorType2">
							<div class="months">
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp" value="0" id="cheB0" type="hidden">
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

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">日生产小时数(小时)</label>
						<div class="infpCon">
							<input type="" id="input_he" class="input" name="dayHours" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.dayHours}" pattern="#.##"/>"
								number=true  />
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">日生产时间段</label>
						<div class="infpCon">

							<div class="infParaCon"
								style="width: 33%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">上午</label>
								<div class="infpCon">
									<%-- <input type="text" class="input" id="forenoonManufacture"
										name="forenoonManufacture"
										value="${baseInformation.forenoonManufacture}"
										placeholder="" onfocus="this.blur()"/> --%>
										<div data-code="" id="forenoonManufacture" class="selF"></div>
								</div>
								<div class="infpCon">
									<%-- <input type="text" class="input" id="forenoonManufactureEnd"
										name="forenoonManufactureEnd"
										 value="${baseInformation.forenoonManufactureEnd}"
										placeholder="" onfocus="this.blur()"/> --%>
									<div data-code="" id="forenoonManufactureEnd" class="selF"></div>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 33%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">下午</label>
								<div class="infpCon">
									<%-- <input type="text" class="input" id="afternoonManufacture"
										name="afternoonManufacture"
										value="${baseInformation.afternoonManufacture}"
										placeholder="" onfocus="this.blur()"/> --%>
										<div data-code="" id="afternoonManufacture" class="selF"></div>
								</div>
								<div class="infpCon">
									<%-- <input type="text" class="input" id="afternoonManufactureEnd"
										name="afternoonManufactureEnd"
										value="${baseInformation.afternoonManufactureEnd}" 
										placeholder="" onfocus="this.blur()"/> --%>
										<div data-code="" id="afternoonManufactureEnd" class="selF"></div>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 33%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">晚上</label>
								<div class="infpCon">
									<%-- <input type="text" class="input" id="nightManufacture"
										name="nightManufacture"
										value="${baseInformation.nightManufacture}"
										placeholder="" onfocus="this.blur()"/> --%>
										<div data-code="" id="nightManufacture" class="selF"></div>
								</div>
								<div class="infpCon">
									<%-- <input type="text" class="input" id="nightManufactureEnd"
										name="nightManufactureEnd"
										value="${baseInformation.nightManufactureEnd}"
										placeholder="" onfocus="this.blur()"/> --%>
										<div data-code="" id="nightManufactureEnd" name="nightManufactureEnd" class="selF"></div>
								</div>
							</div>



						</div>
					</div>
				</div>


				 <%-- <div class="infPara" style="distory:none">
						<div class="infParaCon">
							<label for="" class="infpLable">生产时段</label>
							<div class="infpCon">
								<div class="inputs">
									<div class="inputChild col6">
										<input type="text" class="input" id="forenoonManufacture" name="forenoonManufacture" value="<fmt:formatDate value="${baseInformation.forenoonManufacture}" pattern="HH:mm:ss"/>" placeholder="" />
									</div>
									<div class="inputChild col6">
										<input type="text" class="input" id="afternoonManufacture" name="afternoonManufacture"
										value="<fmt:formatDate value="${baseInformation.afternoonManufacture}" pattern="HH:mm:ss"/>" placeholder="" />
									</div>
									<div class="inputChild col6">
										<input type="text" class="input" id="nightManufacture" name="nightManufacture"
										value="<fmt:formatDate value="${baseInformation.nightManufacture}" pattern="HH:mm:ss"/>" placeholder="" />
									</div>
									
									
								</div>
							</div>
						</div>
					</div>  --%>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">停机维修时间</label>
						<div class="infpCon">
							<input type="" class="input" name="stopServiceTime"
								value="${baseInformation.stopServiceTime}" placeholder="" />
						</div>
					</div>
				</div>
				
				
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年生产天数(天)</label>
						<div class="infpCon">
							<input type="" id="input_year" class="input" name="workDay"
								value="<fmt:formatNumber value="${baseInformation.workDay}" pattern="#.##"/>"
								number=true required />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年生产小时数（小时）</label>
						<div class="infpCon">
							<input type="" id="input_year_hour" class="input" name="nomalProductionHour"
								value="${baseInformation.nomalProductionHour}"
								number=true required />
						</div>
					</div>
				</div>


				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>全年生产总值（万元）</label>
						<div class="infpCon">
							<input type="" id="input_produ" class="input" name="grossProduct" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.grossProduct}" pattern="#.##"/>"
								number=true required />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年生产成本（万元）</label>
						<div class="infpCon">
							<input type="" id="input_const" class="input" name="annualProductionCost" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.annualProductionCost}" pattern="#.##"/>"
								number=true required />
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">用电户号</label>
						<div class="infpCon">
							<input type="" id="powerHouseNo" class="input" name="powerHouseNo" placeholder=""
								value="${baseInformation.powerHouseNo}"
								required >
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>全年用电量（度）</label>
						<div class="infpCon">
							<input type="" id="energyUsed" class="input" name="energyUsed" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.energyUsed}" pattern="#.##"/>"
								required number=true>
						</div>
					</div>
				</div>


				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">是否废气连续在线监测点</label>
						<div class="infpCon">
							<div data-code="是否废气重点污染源" id="emphasisPolluteSource"
								class="selF"></div>
						</div>
					</div>
				</div>
				

				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">职工数量(人)</label>
						<div class="infpCon">
							<input type="" class="input" name="employeeNum" placeholder=""
								value="${baseInformation.employeeNum}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>排污许可是否覆盖</label>
						<div id="errorType_must" class="infpCon errorType1">
							<input type="radio" class="radio" id="isDrainContaminationCoverage1"
							 name="isDrainContaminationCoverage" value="true"
								<c:if test="${baseInformation.isDrainContaminationCoverage=='true' }">checked='checked'</c:if>>
							<label for="isDrainContaminationCoverage1" class="label">是</label> <input type="radio"
								class="radio" id="isDrainContaminationCoverage2" name="isDrainContaminationCoverage" value="false"
								<c:if test="${baseInformation.isDrainContaminationCoverage=='false' }">checked='checked'</c:if>>
							<label for="isDrainContaminationCoverage2" class="label">否</label>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">排污许可证编号</label>
						<div class="infpCon">
							<input type="" class="input" name="licenceNo" placeholder=""
								value="${baseInformation.licenceNo}">
						</div>
					</div>
				</div>
				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">排放许可量（吨）</label>
						<div class="infpCon">

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">SO<sub>2</sub></label>
								<div class="infpCon">
									<input type="" class="input" name="so2Discharge"
										value="${baseInformation.so2Discharge}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">NO<sub>x</sub></label>
								<div class="infpCon">
									<input type="" class="input" name="noxDischarge"
										value="${baseInformation.noxDischarge}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">烟粉尘</label>
								<div class="infpCon">
									<input type="" class="input" name="smokeDischarge"
										value="${baseInformation.smokeDischarge}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">VOC<sub>s</sub></label>
								<div class="infpCon">
									<input type="" class="input" name="vocDischarge"
										value="${baseInformation.vocDischarge}" number=true>
								</div>
							</div>

						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>总量核查是否覆盖</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1"
								name="grossExamineCoverage" value="true"
								<c:if test="${baseInformation.grossExamineCoverage=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> <input type="radio"
								class="radio" id="cheC2" name="grossExamineCoverage"
								value="false"
								<c:if test="${baseInformation.grossExamineCoverage=='false' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">SO<sub>2</sub>控制限制值（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="so2ControlLimitationValue"
								value="${baseInformation.so2ControlLimitationValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">SO<sub>2</sub>核查排放量（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="so2ExamineDischargeValue"
								value="${baseInformation.so2ExamineDischargeValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">NO<sub>x</sub>控制限制值（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="noxControlLimitationValue"
								value="${baseInformation.noxControlLimitationValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">NO<sub>x</sub>核查排放量（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="noxExamineDischargeValue"
								value="${baseInformation.noxExamineDischargeValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>环统是否覆盖</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheD1"
								name="statisticsCoverage" value="true"
								<c:if test="${baseInformation.statisticsCoverage=='true' }">checked='checked'</c:if>>
							<label for="cheD1" class="label">是</label> <input type="radio"
								class="radio" id="cheD2" name="statisticsCoverage" value="false"
								<c:if test="${baseInformation.statisticsCoverage=='false' }">checked='checked'</c:if>>
							<label for="cheD2" class="label">否</label>
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">环境统计信息（吨）</label>
						<div class="infpCon">

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">SO<sub>2</sub></label>
								<div class="infpCon">
									<input type="" class="input" name="so2Statistics"
										value="${baseInformation.so2Statistics}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">NO<sub>x</sub></label>
								<div class="infpCon">
									<input type="" class="input" name="noxStatistics"
										value="${baseInformation.noxStatistics}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">烟粉尘</label>
								<div class="infpCon">
									<input type="" class="input" name="smokeStive"
										value="${baseInformation.smokeStive}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">VOC<sub>s</sub></label>
								<div class="infpCon">
									<input type="" class="input" name="vocStatistics"
										value="${baseInformation.vocStatistics}" number=true>
								</div>
							</div>

						</div>
					</div>
				</div>
				
				<!-- <div style="clear:both;"></div>
				<div style="width: 100%; height: 50px;line-height: 50px;padding-left:20px;background: #d2d2d2;">污水信息</div> -->
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">污水排放量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="sewageDischargeValue"
								value="${baseInformation.sewageDischargeValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">废水自处理率（%）</label>
						<div class="infpCon">
							<input type="" class="input" name="effluentDisposeRate"
								value="${baseInformation.effluentDisposeRate}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">污水处理池是否加盖</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheE1" name="sewageCap"
								value="true"
								<c:if test="${baseInformation.sewageCap=='true' }">checked='checked'</c:if>>
							<label for="cheE1" class="label">是</label> <input type="radio"
								class="radio" id="cheE2" name="sewageCap" value="false"
								<c:if test="${baseInformation.sewageCap=='false' }">checked='checked'</c:if>>
							<label for="cheE2" class="label">否</label>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">煤炭来源地</label>
						<div class="infpCon">
							<input type="" class="input" name="coalSourcePlace"
								value="${baseInformation.coalSourcePlace}">
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">主要原料来源地</label>
						<div class="infpCon">
							<input type="" class="input" name="rawSourcePlace"
								value="${baseInformation.rawSourcePlace}">
						</div>
					</div>
				</div>
				<%-- <div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">产能规模（吨产品/年）</label>
						<div class="infpCon">
							<input type="" class="input" name="capacityScale" value="${baseInformation.capacityScale}">
						</div>
					</div>
				</div> --%>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">环保专工人数</label>
						<div class="infpCon">
							<input type="" class="input" name="specialtyPeoples"
								value="${baseInformation.specialtyPeoples}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">是否有GGH烟气再加热系统（仅电厂）</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheF1" name="isGGH"
								value="是"
								<c:if test="${baseInformation.isGGH=='是' }">checked='checked'</c:if>>
							<label for="cheF1" class="label">是</label> <input type="radio"
								class="radio" id="cheF2" name="isGGH" value="否"
								<c:if test="${baseInformation.isGGH=='否' }">checked='checked'</c:if>>
							<label for="cheF2" class="label">否</label>
						</div>
					</div>
				</div>
				<div class="infPara infPara2">
					<div id="infParaCon_tran" class="infParaCon">
						<label for="" class="infpLable">煤炭运输方式及其比例（%）</label>
						<div class="infpCon">
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">火车</label>
								<div class="infpCon">
									 <input type="" class="input" id="proportion1" name="proportion1"
										value="${baseInformation.proportion1}" number=true>
								</div>
							</div>
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">汽车</label>
								<div class="infpCon">
									<input type="" class="input" id="proportion2" name="proportion2"
										value="${baseInformation.proportion2}" number=true>
								</div>
							</div>
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">其他</label>
								<div class="infpCon">
									<input type="" class="input" id="proportion3" name="proportion3"
										value="${baseInformation.proportion3}" number=true>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara infPara2">
					<div id="infParaCon_oil" class="infParaCon">
						<label for="" class="infpLable">油品运输方式及其比例（%）</label>
						<div class="infpCon">
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">火车</label>
								<div class="infpCon">
									<input type="" id="oilproportion1" class="input" name="oilproportion1"
										value="${baseInformation.oilproportion1}" number=true>
								</div>
							</div>
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">汽车</label>
								<div class="infpCon">
									<input type="" id="oilproportion2" class="input" name="oilproportion2"
										value="${baseInformation.oilproportion2}" number=true>
								</div>
							</div>
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">管道</label>
								<div class="infpCon">
									<input type="" id="oilproportion3" class="input" name="oilproportion3"
										value="${baseInformation.oilproportion3}" number=true>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年煤炭消耗量(吨)</label>
						<div class="infpCon">
							<input type="" class="input" name="annualCoalConsume"
								value="${baseInformation.annualCoalConsume}" number=true min="0"
								required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年燃气消耗量(万立方米)</label>
						<div class="infpCon">
							<input type="" class="input" name="annualFuelgasConsume"
								value="${baseInformation.annualFuelgasConsume}" number=true
								min="0" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">柴油消耗量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="annualFueloilConsume"
								value="${baseInformation.annualFueloilConsume}" number=true
								min="0" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">其他燃料消耗量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="otherfuel"
								value="${baseInformation.otherfuel}" number=true
								min="0" required>
						</div>
					</div>
				</div> --%>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">企业生产能源消耗全年总量：</label>
					</div>
				</div>
				
					<div id="infParaCon_oil" class="infParaCon">
						
						<div class="infpCon">
							<div class="infParaCon"style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>煤炭(吨)</label>
								<div class="infpCon">
									<input type="" class="input" name="annualCoalConsume"
										value="${baseInformation.annualCoalConsume}" number=true min="0"
										required>
								</div>
							</div>
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>天燃气(万立方米)</label>
								<div class="infpCon">
									<input type=""  class="input" name="annualFuelgasConsume"
										value="${baseInformation.annualFuelgasConsume}" number=true min="0"
										required>
								</div>
							</div>
							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>柴油（吨）</label>
								<div class="infpCon">
									<input type="" class="input" name="annualFueloilConsume"
										value="${baseInformation.annualFueloilConsume}" number=true min="0"
										required>
								</div>
							</div>
							<div class="infParaCon"
								style="width: 23%; float: left; padding-left: 20px;">
								<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>其他燃料（吨）</label>
								<div class="infpCon">
									<input type="" class="input" name="otherfuel"
										value="${baseInformation.otherfuel}" number=true min="0"
										required>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- <div style="clear:both;"></div>
				<div style="width: 100%; height: 50px;line-height: 50px;padding-left:20px;background: #d2d2d2;color:black;">其他信息</div> -->
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">煤堆场是否封闭</label>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="coalClosed1"
									name="coalClosed" value="true"
									<c:if test="${baseInformation.coalClosed=='true' }">checked='checked'</c:if>>
								<label for="coalClosed1" class="label">是</label> <input
									type="radio" class="radio" id="coalClosed2" name="coalClosed"
									value="false"
									<c:if test="${baseInformation.coalClosed=='false' }">checked='checked'</c:if>>
								<label for="coalClosed2" class="label">否</label>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable">卸煤沟是否封闭</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="unloadCoalClosed1"
									name="unloadCoalClosed" value="true"
									<c:if test="${baseInformation.unloadCoalClosed=='true' }">checked='checked'</c:if>>
								<label for="unloadCoalClosed1" class="label">是</label> <input
									type="radio" class="radio" id="unloadCoalClosed2"
									name="unloadCoalClosed" value="false"
									<c:if test="${baseInformation.unloadCoalClosed=='false' }">checked='checked'</c:if>>
								<label for="unloadCoalClosed2" class="label">否</label>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">食堂灶头数</label>
						<div class="infpCon">
							<input type="" class="input" name="kitchenNum"
								value="${baseInformation.kitchenNum}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable">是否安装油烟去除装置</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isLampblackWipe1"
									name="isLampblackWipe" value="true"
									<c:if test="${baseInformation.isLampblackWipe=='true' }">checked='checked'</c:if>>
								<label for="isLampblackWipe1" class="label">是</label> <input
									type="radio" class="radio" id="isLampblackWipe2"
									name="isLampblackWipe" value="false"
									<c:if test="${baseInformation.isLampblackWipe=='false' }">checked='checked'</c:if>>
								<label for="isLampblackWipe2" class="label">否</label>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">食堂油烟排放速率（立方米/时）</label>
						<div class="infpCon">
							<input type="" class="input" name="smokeDischargeRate"
								value="${baseInformation.smokeDischargeRate}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">食堂油烟去除装置安装时间 </label> 
						<input
							style="padding: 0 4px; border-radius: 4px; border: 1px solid #d2d2d2"
							type="" class="input" id="installTime"
							value="<fmt:formatDate value="${baseInformation.installTime}" pattern="YYYY-MM"/>"
						 	name="installTime"
							onfocus="this.blur()" />
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油烟去除率</label>
						<div class="infpCon">
							<input type="" class="input" name="lampblackWipeRate"
								value="${baseInformation.lampblackWipeRate}" number=true>
						</div>
					</div>
				</div>

				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉数（台）</label>
						<div class="infpCon">
							<input type="" class="input" name="boilerAmount"
								value="${baseInformation.boilerAmount}" number=true min="0"
								required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉总蒸吨数(吨/小时)</label>
						<div class="infpCon">
							<input type="" class="input" name="boilerZhengdun"
								value="${baseInformation.boilerZhengdun}" number=true min="0"
								required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>自备电厂发电机组数（个）</label>
						<div class="infpCon">
							<input type="" class="input" name="ownedpowerAmount"
								value="${baseInformation.ownedpowerAmount}" number=true min="0"
								required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>露天堆场数（个）</label>
						<div class="infpCon">
							<input type="" class="input" name="openyardAmount"
								value="${baseInformation.openyardAmount}" number=true min="0"
								required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>有机液体储罐数（个）</label>
						<div class="infpCon">
							<input type="" class="input" name="organicTankAmount"
								value="${baseInformation.organicTankAmount}" number=true min="0"
								required>
						</div>
					</div>
				</div>


				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable">车间是否整洁</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isClean1" name="isClean"
									value="整洁"
									<c:if test="${baseInformation.isClean=='整洁' }">checked='checked'</c:if>>
								<label for="isClean1" class="label">整洁</label> <input
									type="radio" class="radio" id="isClean2" name="isClean"
									value="一般"
									<c:if test="${baseInformation.isClean=='一般' }">checked='checked'</c:if>>
								<label for="isClean2" class="label">一般</label> <input
									type="radio" class="radio" id="isClean3" name="isClean"
									value="脏乱"
									<c:if test="${baseInformation.isClean=='脏乱' }">checked='checked'</c:if>>
								<label for="isClean3" class="label">脏乱</label>
							</div>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable">粉尘无组织排放是否严重</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isSeverityDust1"
									name="isSeverityDust" value="无"
									<c:if test="${baseInformation.isSeverityDust=='无' }">checked='checked'</c:if>>
								<label for="isClean1" class="label">无</label> <input
									type="radio" class="radio" id="isSeverityDus2"
									name="isSeverityDust" value="一般"
									<c:if test="${baseInformation.isSeverityDust=='一般' }">checked='checked'</c:if>>
								<label for="isSeverityDus2" class="label">一般</label> <input
									type="radio" class="radio" id="isSeverityDus3"
									name="isSeverityDust" value="严重"
									<c:if test="${baseInformation.isSeverityDust=='严重' }">checked='checked'</c:if>>
								<label for="isSeverityDus3" class="label">严重</label>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable">VOC气味是否浓重</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isSeverityVO1"
									name="isSeverityVOC" value="无"
									<c:if test="${baseInformation.isSeverityVOC=='无' }">checked='checked'</c:if>>
								<label for="isSeverityVOC1" class="label">无</label> <input
									type="radio" class="radio" id="isSeverityVO2"
									name="isSeverityVOC" value="一般"
									<c:if test="${baseInformation.isSeverityVOC=='一般' }">checked='checked'</c:if>>
								<label for="isSeverityVOC2" class="label">一般</label> <input
									type="radio" class="radio" id="isSeverityVOC3"
									name="isSeverityVOC" value="严重"
									<c:if test="${baseInformation.isSeverityVOC=='严重' }">checked='checked'</c:if>>
								<label for="isSeverityVOC3" class="label">严重</label>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable">通风设施是否常开</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isVentilateOpe1"
									name="isVentilateOpen" value="无"
									<c:if test="${baseInformation.isVentilateOpen=='无' }">checked='checked'</c:if>>
								<label for="isVentilateOpen1" class="label">无</label> <input
									type="radio" class="radio" id="isVentilateOpen2"
									name="isVentilateOpen" value="间歇"
									<c:if test="${baseInformation.isVentilateOpen=='间歇' }">checked='checked'</c:if>>
								<label for="isVentilateOpen2" class="label">间歇</label> <input
									type="radio" class="radio" id="isVentilateOpen3"
									name="isVentilateOpen" value="常开"
									<c:if test="${baseInformation.isVentilateOpen=='常开' }">checked='checked'</c:if>>
								<label for="isVentilateOpen3" class="label">常开</label>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable">生产设备是否封闭</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isEquipmentClosed1"
									name="isEquipmentClosed" value="无"
									<c:if test="${baseInformation.isEquipmentClosed=='无' }">checked='checked'</c:if>>
								<label for="isEquipmentClosed1" class="label">无</label> <input
									type="radio" class="radio" id="isEquipmentClosed2"
									name="isEquipmentClosed" value="重点部分"
									<c:if test="${baseInformation.isEquipmentClosed=='重点部分' }">checked='checked'</c:if>>
								<label for="isEquipmentClosed2" class="label">重点部分</label> <input
									type="radio" class="radio" id="isEquipmentClosed3"
									name="isEquipmentClosed" value="全封闭"
									<c:if test="${baseInformation.isEquipmentClosed=='全封闭' }">checked='checked'</c:if>>
								<label for="isEquipmentClosed3" class="label">全封闭</label>
							</div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<div class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否有喷漆工序</div>
						<div class="dialog_title_right ">
							<div class="infpCon errorType1">
								<input type="radio" class="radio" id="isPaint1"
									name="isPaint" value="是"
									<c:if test="${baseInformation.isPaint=='是' }">checked='checked'</c:if>>
								<label for="isPaint1" class="label">是</label> 
								<input type="radio" class="radio" id="isPaint2"
									name="isPaint" value="否"
									<c:if test="${baseInformation.isPaint=='否' }">checked='checked'</c:if>>
								<label for="isPaint2" class="label">否</label>
								
							</div>
						</div>
					</div>
				</div>

				<input type="hidden" name="sectionStatus"
					value="${baseInformation.sectionStatus}"> <input
					type="hidden" name="oilTankStatus"
					value="${baseInformation.oilTankStatus}"> <input
					type="hidden" name="monthlyStatus"
					value="${baseInformation.monthlyStatus}"> <input
					type="hidden" name="bovernanceMeasuresStatus"
					value="${baseInformation.bovernanceMeasuresStatus}"> <input
					type="hidden" name="exhaustionHoleStatus"
					value="${baseInformation.exhaustionHoleStatus}"> <input
					type="hidden" name="boilerStatus"
					value="${baseInformation.boilerStatus}"> <input
					type="hidden" name="exhaustionHoleStatus"
					value="${baseInformation.exhaustionHoleStatus}">

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${baseInformation.description}</textarea>
						</div>
					</div>
				</div>
		    </form>
			<c:if test="${principal.userType != 'system'}">
				<div class="buttonF">
					<div class="saveBtn" id="confirm">保存</div>
				</div>
		    </c:if>
		</div>
	</div>
</body>
</html>