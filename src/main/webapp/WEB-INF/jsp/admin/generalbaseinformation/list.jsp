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
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生产信息</title>
<style>
#jedatebox {
	width: 35%;
}
</style>
<script type="text/javascript">
	require(
			["grid","hide", "select", "panel", "validate", "ajaxform", "year", "date" ],
			function(g,hide) {
				$("#confirm").click(function() {
					$("#product_form").submit();
				})

				$("#stopTime", "#product_form").jeDate({
					format : "YYYY-MM",
					isTime : false,
					zIndex : 999999900
				})
				$("#stopTime2017", "#product_form").jeDate({
					format : "YYYY-MM",
					isTime : false,
					zIndex : 999999900
				})

				var enterpriseState = $("#enterpriseState", "#product_form")
						.select(
								{
									name : "enterpriseState",
									defaultValue : '${baseInformation.enterpriseState}',
									zIndex : 10,
									change:function(e,value){
										if(value == '（2）关停/取缔'){
											$("#stopTime").attr("required","true");
											$("[name ='productionMonthTemp']").removeAttr("required");
										}else{
											$("#stopTime").removeAttr("required");
											$("[name ='productionMonthTemp']").attr("required","true");
										}
									}
								});
				var enterpriseState2017 = $("#enterpriseState2017", "#product_form").select({
					name : "enterpriseState2017",
					defaultValue : '${baseInformation.enterpriseState2017}',
					zIndex : 10,
					change:function(e,value){
						if(value == '（2）关停/取缔'){
							$("#stopTime2017").attr("required","true");
							$("[name ='productionMonthTemp2017']").removeAttr("required");
						}else{
							$("#stopTime2017").removeAttr("required");
							$("[name ='productionMonthTemp2017']").attr("required","true");
						}
					}
				});
				$("#emphasisPolluteSource", "#product_form").select({
					name : "emphasisPolluteSource",
					defaultValue : '${baseInformation.emphasisPolluteSource}',
					zIndex : 9
				});
				$("#enterpriseType", "#product_form").select({
					name : "enterpriseType",
					defaultValue : '${baseInformation.enterpriseType}',
					zIndex : 7
				});
				$('#putProductionYear').year({
					'name' : 'putProductionYear',
					defaultValue : '${baseInformation.putProductionYear}'
				})
				var month = $("[name=productionMonth]").val();
				if (month) {
					$.each(month.split(","), function(i, v) {
						$("[name=productionMonthTemp][value=" + v + "]").attr(
								"checked", "checked");
					})
				}
				var month2017 = $("[name=productionMonth2017]").val();
				if (month2017) {
					$.each(month2017.split(","), function(i, v) {
						$("[name=productionMonthTemp2017][value=" + v + "]")
								.attr("checked", "checked");
					})
				}
				//提交表单
				$("#product_form")
						.validate(
								{
									rules : {
										workDay : {
											range : [ 0, 366 ]
										},
										workDay2017 : {
											range : [ 0, 366 ]
										},
										dayHours : {
											min : 0
										},
										grossProduct : {
											min : 0,
											maxlength : 16
										},
										grossProduct2017 : {
											min : 0,
											maxlength : 16
										},
										energyUsed : {
											min : 0,
											maxlength : 16
										},
										energyUsed2017 : {
											min : 0,
											maxlength : 16
										},
										nomalProductionHour : {
											min : 0
										},
										so2ControlLimitationValue : {
											min : 0
										},
										so2ExamineDischargeValue : {
											min : 0
										},
										noxControlLimitationValue : {
											min : 0
										},
										noxExamineDischargeValue : {
											min : 0
										},
										so2Statistics : {
											min : 0
										},
										noxStatistics : {
											min : 0
										},
										smokeStive : {
											min : 0
										},
										vocStatistics : {
											min : 0
										},
										sewageDischargeValue : {
											min : 0
										},
										effluentDisposeRate : {
											range : [ 0, 100 ]
										},
										specialtyPeoples : {
											min : 0
										},
										proportion1 : {
											range : [ 0, 100 ]
										},
										proportion2 : {
											range : [ 0, 100 ]
										},
										proportion3 : {
											range : [ 0, 100 ]
										},
										emphasisPolluteSource : {
											required : true
										},
										continuousIntermittence : {
											required : true
										},
										continuousIntermittence2017 : {
											required : true
										},
										
										putProductionYear : {
											required : true
										},

										statisticsCoverage : {
											required : true
										},
										grossExamineCoverage : {
											required : true
										},
										enterpriseState : {
											required : true
										},
										enterpriseState2017 : {
											required : true
										},
										procedureComplete : {
											required : true
										},
										isMonitoring : {
											required : true
										},
										licenceNo : {
											maxlength : 254
										},
									},
									messages : {

									},
									submitHandler : function(form) {
										var month = [];
										$("[name=productionMonthTemp]:checked")
												.each(function(i, v) {
													month.push($(v).val())
												});
										$("[name=productionMonth]").val(
												month.join(","));
										var month2017 = [];
										$(
												"[name=productionMonthTemp2017]:checked")
												.each(function(i, v) {
													month2017.push($(v).val())
												});
										$("[name=productionMonth2017]").val(
												month2017.join(","));


										var options = {
											url : base
													+ '/web/generalbaseinformation/save.jhtml',
											type : 'post',
											dataType : 'json',
											success : function(data) {
												qy.suc2({
													title : '保存成功！'
												});
											},
											error : function() {
												qy.fail2({
													title : '保存失败！'
												});
											}
										};
										$(form).ajaxSubmit(options);
										return false;
									}
								});

				//判断总量核查是否选择否
				var grossExamineCoverage = $(
						"[name=grossExamineCoverage]:checked").val();
				if (grossExamineCoverage == '覆盖') {
					$("input[name=so2ControlLimitationValue]").removeAttr(
							'disabled');
					$("input[name=so2ExamineDischargeValue]").removeAttr(
							'disabled');
					$("input[name=noxControlLimitationValue]").removeAttr(
							'disabled');
					$("input[name=noxExamineDischargeValue]").removeAttr(
							'disabled');
				} else {
					$("input[name=so2ControlLimitationValue]").attr({
						disabled : 'true'
					});
					$("input[name=so2ExamineDischargeValue]").attr({
						disabled : 'true'
					});
					$("input[name=noxControlLimitationValue]").attr({
						disabled : 'true'
					});
					$("input[name=noxExamineDischargeValue]").attr({
						disabled : 'true'
					});
				}

				$("[name=grossExamineCoverage]").on(
						'change',
						function() {
							if ($(this).val() == '覆盖') {
								$("input[name=so2ControlLimitationValue]")
										.removeAttr('disabled');
								$("input[name=so2ExamineDischargeValue]")
										.removeAttr('disabled');
								$("input[name=noxControlLimitationValue]")
										.removeAttr('disabled');
								$("input[name=noxExamineDischargeValue]")
										.removeAttr('disabled');

							} else {
								$("input[name=so2ControlLimitationValue]")
										.attr({
											disabled : 'true'
										});
								$("input[name=so2ExamineDischargeValue]").attr(
										{
											disabled : 'true'
										});
								$("input[name=noxControlLimitationValue]")
										.attr({
											disabled : 'true'
										});
								$("input[name=noxExamineDischargeValue]").attr(
										{
											disabled : 'true'
										});
							}
							;
						})

				//判断环统是否选择否
				var statisticsCoverage = $("[name=statisticsCoverage]:checked")
						.val();
				if (statisticsCoverage == '覆盖') {
					$("input[name=so2Statistics]").removeAttr('disabled');
					$("input[name=noxStatistics]").removeAttr('disabled');
					$("input[name=smokeStive]").removeAttr('disabled');
					$("input[name=vocStatistics]").removeAttr('disabled');
				} else {
					$("input[name=so2Statistics]").attr({
						disabled : 'true'
					});
					$("input[name=noxStatistics]").attr({
						disabled : 'true'
					});
					$("input[name=smokeStive]").attr({
						disabled : 'true'
					});
					$("input[name=vocStatistics]").attr({
						disabled : 'true'
					});
				}

				$("[name=statisticsCoverage]").on('change', function() {
					if ($(this).val() == '覆盖') {
						$("input[name=so2Statistics]").removeAttr('disabled');
						$("input[name=noxStatistics]").removeAttr('disabled');
						$("input[name=smokeStive]").removeAttr('disabled');
						$("input[name=vocStatistics]").removeAttr('disabled');

					} else {
						$("input[name=so2Statistics]").attr({
							disabled : 'true'
						});
						$("input[name=noxStatistics]").attr({
							disabled : 'true'
						});
						$("input[name=smokeStive]").attr({
							disabled : 'true'
						});
						$("input[name=vocStatistics]").attr({
							disabled : 'true'
						});
					}
					;
				})
				hide.hide();
				$("#fixed_return").on("click", function() {
					window.location.href = "../enterprise/enterpriseTaskList.jhtml";
					top.loadMenu('188');
				})
				$("#back").on("click", function() {
					window.location.href = "../enterprise/list.jhtml";
					top.loadMenu('2','31');
				})
				$("#check").click(function(){
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
				});
				
			})
</script>
<style>
.block .buttonF {
	padding-right: 6px;
	padding-bottom: 18px;
}
.examine {
	width: 42px;
	height: 30px;
	line-height: 30px!important;
	text-align: center;
	color: #fff !important;
	border-radius: 13px;
	background: #4fc1ee;
	margin-top: 9px !important;
}
</style>
</head>
<body>
	<div class="block hAuto">
		<h1 class="title">
			<i class="iconfont icon-shengchanxinxi1"></i> 生产信息
			<c:if test="${principal.userType != 'system'}">
				<div id="back" class="titleBtn">返回</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
				<div id="check" class="titleBtn examine">审核</div>
			</c:if>
		</h1>
	</div>
	<div class="block hAuto" style="min-height: calc(100% - 65px);">
		<form id="product_form">
			<div class="infLine">
				<input type="hidden" name="id" value="${baseInformation.id}" />
				<input type="hidden" name="isSave" value="2" />

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2016企业状态</label>
						<div class="infpCon">
							<div data-code="简表【企业状态】" id="enterpriseState" class="selF"
								required></div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2017企业状态</label>
						<div class="infpCon">
							<div data-code="简表【企业状态】" id="enterpriseState2017" class="selF"
								required></div>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2016关停时间</label>
						<div class="infpCon">
							<input class="input" id="stopTime" name="stopTime" type="text"
								placeholder="" onfocus="this.blur()"
								value="<fmt:formatDate value="${baseInformation.stopTime}" pattern="yyyy-MM"/>" />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2017关停时间</label>
						<div class="infpCon">
							<input class="input" id="stopTime2017" name="stopTime2017"
								type="text" placeholder="" onfocus="this.blur()"
								value="<fmt:formatDate value="${baseInformation.stopTime2017}" pattern="yyyy-MM"/>" />
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="workDay" class="infpLable">2016年生产天数(天)</label>
						<div class="infpCon">
							<input type="" class="input" name="workDay" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.workDay}" pattern="#.##"/>"
								number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="workDay" class="infpLable">2017年生产天数(天)</label>
						<div class="infpCon">
							<input type="" class="input" name="workDay2017" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.workDay2017}" pattern="#.##"/>"
								number=true>
						</div>
					</div>
				</div>



				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2016年产值（万元）</label>
						<div class="infpCon">
							<input type="" class="input" name="grossProduct" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.grossProduct}" pattern="#.##"/>"
								number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2017年产值（万元）</label>
						<div class="infpCon">
							<input type="" class="input" name="grossProduct2017"
								placeholder=""
								value="<fmt:formatNumber value="${baseInformation.grossProduct2017}" pattern="#.##"/>"
								number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2016年用电量（度）</label>
						<div class="infpCon">
							<input type="" class="input" name="energyUsed" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.energyUsed}" pattern="#.##"/>"
								number=true required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">2017年用电量（度）</label>
						<div class="infpCon">
							<input type="" class="input" name="energyUsed2017" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.energyUsed2017}" pattern="#.##"/>"
								number=true required>
						</div>
					</div>
				</div>

				<div class="infPara hide">
					<div class="infParaCon">
						<label for="" class="infpLable">日平均生产小时数(小时)</label>
						<div class="infpCon">
							<input type="" class="input" name="dayHours" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.dayHours}" pattern="#.##"/>"
								number=true>
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

				<div class="infPara hide">
					<div class="infParaCon">
						<label for="" class="infpLable">年正常生产时间（小时/年）</label>
						<div class="infpCon">
							<input type="" class="input" name="nomalProductionHour"
								placeholder=""
								value="<fmt:formatNumber value="${baseInformation.nomalProductionHour}" pattern="#.##"/>"
								number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">废气重点污染源</label>
						<div class="infpCon">
							<div data-code="是否废气重点污染源" id="emphasisPolluteSource"
								class="selF"></div>
						</div>
					</div>
				</div>
				

				<div class="infPara ">
					<div class="infParaCon">
						<label for="" class="infpLable">环评手续是否完善</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" name="procedureComplete"
								id="cheA1" value="是"
								<c:if test="${baseInformation.procedureComplete=='是' }">checked='checked'</c:if>>
							<label for="cheA1" class="label">是</label> <input type="radio"
								class="radio" name="procedureComplete" id="cheA2" value="否"
								<c:if test="${baseInformation.procedureComplete=='否' }">checked='checked'</c:if>>
							<label for="cheA2" class="label">否</label>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">填表类型</label>
						<div class="infpCon">
							<div data-code="普查表【企业类型】" id="enterpriseType" class="selF"
								required></div>
						</div>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">2016年连续/间歇生产</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" name="continuousIntermittence"
								id="cheA1" value="连续"
								<c:if test="${baseInformation.continuousIntermittence=='连续' }">checked='checked'</c:if>>
							<label for="cheA1" class="label">连续</label> <input type="radio"
								class="radio" name="continuousIntermittence" id="cheA2"
								value="间歇"
								<c:if test="${baseInformation.continuousIntermittence=='间歇' }">checked='checked'</c:if>>
							<label for="cheA2" class="label">间歇</label>
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<input type="hidden" name="productionMonth"
						value="${baseInformation.productionMonth}">
					<div class="infParaCon">
						<label for="" class="infpLable">2016年生产月份</label>
						<div class="infpCon errorType2">
							<div class="months">
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp" value="0" id="cheB0"> 
								<input type="checkbox" class="checkbox" name="productionMonthTemp"
									value="1" id="cheB1">
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
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">2017年连续/间歇生产</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio"
								name="continuousIntermittence2017" id="cheA1" value="连续"
								<c:if test="${baseInformation.continuousIntermittence2017=='连续' }">checked='checked'</c:if>>
							<label for="cheA1" class="label">连续</label> <input type="radio"
								class="radio" name="continuousIntermittence2017" id="cheA2"
								value="间歇"
								<c:if test="${baseInformation.continuousIntermittence2017=='间歇' }">checked='checked'</c:if>>
							<label for="cheA2" class="label">间歇</label>
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<input type="hidden" name="productionMonth2017"
						value="${baseInformation.productionMonth2017}">
					<div class="infParaCon">
						<label for="" class="infpLable">2017年生产月份</label>
						<div class="infpCon errorType2">
							<div class="months">
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="0" id="cheB02017">
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="1" id="cheB12017">
								<div class="label2f">
									<label for="cheB12017" class="label2">一月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="2" id="cheB22017">
								<div class="label2f">
									<label for="cheB22017" class="label2">二月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="3" id="cheB32017">
								<div class="label2f">
									<label for="cheB32017" class="label2">三月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="4" id="cheB42017">
								<div class="label2f">
									<label for="cheB42017" class="label2">四月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="5" id="cheB52017">
								<div class="label2f">
									<label for="cheB52017" class="label2">五月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="6" id="cheB62017">
								<div class="label2f">
									<label for="cheB62017" class="label2">六月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="7" id="cheB72017">
								<div class="label2f">
									<label for="cheB72017" class="label2">七月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="8" id="cheB82017">
								<div class="label2f">
									<label for="cheB82017" class="label2">八月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="9" id="cheB92017">
								<div class="label2f">
									<label for="cheB92017" class="label2">九月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="10" id="cheB102017">
								<div class="label2f">
									<label for="cheB102017" class="label2">十月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="11" id="cheB112017">
								<div class="label2f">
									<label for="cheB112017" class="label2">十一月</label>
								</div>
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp2017" value="12" id="cheB122017">
								<div class="label2f">
									<label for="cheB122017" class="label2">十二月</label>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">投产年份</label>
						<div class="infpCon">
							<div id="putProductionYear" class="yearF iconfont icon-nianfen"></div>
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">总量核查是否覆盖</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1"
								name="grossExamineCoverage" value="覆盖"
								<c:if test="${baseInformation.grossExamineCoverage=='覆盖' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> <input type="radio"
								class="radio" id="cheC2" name="grossExamineCoverage" value="不覆盖"
								<c:if test="${baseInformation.grossExamineCoverage=='不覆盖' }">checked='checked'</c:if>>
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
						<label for="" class="infpLable">环统是否覆盖</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheD1"
								name="statisticsCoverage" value="覆盖"
								<c:if test="${baseInformation.statisticsCoverage=='覆盖' }">checked='checked'</c:if>>
							<label for="cheD1" class="label">是</label> <input type="radio"
								class="radio" id="cheD2" name="statisticsCoverage" value="不覆盖"
								<c:if test="${baseInformation.statisticsCoverage=='不覆盖' }">checked='checked'</c:if>>
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
								<label for="" class="infpLable">VOC</label>
								<div class="infpCon">
									<input type="" class="input" name="vocStatistics"
										value="${baseInformation.vocStatistics}" number=true>
								</div>
							</div>

						</div>
					</div>
				</div>

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
								value="加盖"
								<c:if test="${baseInformation.sewageCap=='加盖' }">checked='checked'</c:if>>
							<label for="cheE1" class="label">是</label> <input type="radio"
								class="radio" id="cheE2" name="sewageCap" value="不加盖"
								<c:if test="${baseInformation.sewageCap=='不加盖' }">checked='checked'</c:if>>
							<label for="cheE2" class="label">否</label>
						</div>
					</div>
				</div>

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
						<label for="" class="infpLable">厂门是否安装监控</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheE1" name="isMonitoring"
								value="是"
								<c:if test="${baseInformation.isMonitoring=='是' }">checked='checked'</c:if>>
							<label for="cheE1" class="label">是</label> <input type="radio"
								class="radio" id="cheE2" name="isMonitoring" value="否"
								<c:if test="${baseInformation.isMonitoring=='否' }">checked='checked'</c:if>>
							<label for="cheE2" class="label">否</label>
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


			</div>
			<c:if test="${principal.userType != 'system'}">
				<div class="buttonF">
					<a class="saveBtn" id="confirm">保存</a>
				</div>
			</c:if>
		</form>
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>