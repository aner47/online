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
<title>基本信息</title>


<style>
.block .buttonF {
	padding-right: 6px;
	padding-bottom: 18px;
}
.fixedExamine {
	position: fixed;
	right: 20px;
	bottom: 246px;
	z-index: 999;
	width: 100px;
	height: 50px;
	line-height: 50px;
	text-align: center;
	background-color: #4fc1ee;
}
</style>
</head>
<body>
	<!-- 隐藏字段 -->
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
	
	<div class="block hAuto">
		<h1 class="title">
			<c:if test="${principal.userType != 'system'}">
				<i class="iconfont icon-shengchanxinxi1"></i> 基本信息
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<i class="iconfont icon-shengchanxinxi1"></i>
				<span>企业名称：${enterprise.name }</span>
			</c:if>

			<c:if test="${principal.userType != 'system'}">
				<div id="back" class="titleBtn">返回</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
				<div id="check" class="titleBtn">审核</div>
			</c:if>
		</h1>
	</div>
	
	<div class="block hAuto" style="min-height: calc(100% - 65px);">
		<form id="product_form" class="js-prepend">
			<div class="infLine">
				<input type="hidden" name="id" value="${baseInformation.id}" />
				
				<input type="hidden" id="isSave" name="isSave" value="true" />

				<div class="infLine">
					<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${baseInformation.inquirer}" required>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${baseInformation.inquirerPhone}" required>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">企业状态</label>
						<div class="infpCon">
							<div data-code="简表【企业状态】" id="enterpriseState" class="selF"></div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon" id = "stopTimeDiv">
						<label for="" class="infpLable">关停时间</label>
						<input
							style="padding: 0 4px; border-radius: 4px; border: 1px solid #d2d2d2"
							type="" class="input" id="stopTime"
							value="<fmt:formatDate value="${baseInformation.stopTime}" pattern="YYYY-MM"/>"
						 	name="stopTime"
							onfocus="this.blur()" />
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">开业时间</label>
						<!-- <div class="infpCon">
							<div id="putProductionYear" class="yearF iconfont icon-nianfen"></div>
						</div> -->
						<input
							style="padding: 0 4px; border-radius: 4px; border: 1px solid #d2d2d2"
							type="" class="input" id="putProductionYear"
							value="${baseInformation.putProductionYear}"
						 	name="putProductionYear"
							onfocus="this.blur()" />
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">改扩建时间</label> 
						<input
							style="padding: 0 4px; border-radius: 4px; border: 1px solid #d2d2d2"
							type="" class="input" id="rebuildTime"
							value="${baseInformation.rebuildTime}"
						 	name="rebuildTime"
							onfocus="this.blur()" />
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">职工数量(人)</label>
						<div class="infpCon">
							<input type="" class="input" name="employeeNum" value="${baseInformation.employeeNum}" required number=true/>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">环保专工人数(人)</label>
						<div class="infpCon">
							<input type="" class="input" name="specialtyPeoples"
								value="${baseInformation.specialtyPeoples}" number=true>
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
						<label for="" class="infpLable">全年用电量（度）</label>
						<div class="infpCon">
							<input type="" class="input" name="energyUsed" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.energyUsed}" pattern="#.##"/>"
								number=true required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="workDay" class="infpLable">年生产天数(天)</label>
						<div class="infpCon">
							<input type="" class="input" name="workDay" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.workDay}" pattern="#.##"/>"
								required number=true>
						</div>
					</div>
				</div>
				
				<div class="infPara infPara2">
					<input type="hidden" name="productionMonth"
						value="${baseInformation.productionMonth}">
					<div class="infParaCon">
						<label for="" class="infpLable">生产月份</label>
						<div class="infpCon errorType2">
							<div class="months">
								<input type="checkbox" class="checkbox"
									name="productionMonthTemp" value="0" id="cheB0"> <input
									type="checkbox" class="checkbox" name="productionMonthTemp"
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
				

				<div class="infPara ">
					<div class="infParaCon">
						<label for="" class="infpLable">日生产小时数(小时)</label>
						<div class="infpCon">
							<input type="" class="input" id="dayHours" name="dayHours"
								value="<fmt:formatNumber value="${baseInformation.dayHours}" pattern="#.##"/>"
								number=true>
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
										<div data-code="" id="nightManufactureEnd" class="selF"></div>
								</div>
							</div>



						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年生产总值（万元）</label>
						<div class="infpCon">
							<input type="" class="input" name="grossProduct" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.grossProduct}" pattern="#.##"/>"
								number=true required />
						</div>
					</div>
				</div>
				
			<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年生产成本（万元）</label>
						<div class="infpCon">
							<input type="" id="input_const" class="input" name="annualProductionCost" placeholder=""
								value="<fmt:formatNumber value="${baseInformation.annualProductionCost}" pattern="#.##"/>"
								number=true required />
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">停机维修时间</label>
						<div class="infpCon">
							<input type="" class="input" name="stopServiceTime"
								value="${baseInformation.stopServiceTime}" placeholder="" />
						</div>
					</div>
				</div>
				
				<div class="infPara ">
					<div class="infParaCon">
						<label for="" class="infpLable">年生产小时数（小时）</label>
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
						<label for="" class="infpLable">是否连续在线监测点</label>
						<div class="infpCon">
							<div data-code="是否废气重点污染源" id="emphasisPolluteSource"
								class="selF"></div>
						</div>
					</div>
				</div>
				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">排污许可是否覆盖</label>
						<div class="infpCon errorType1">
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
				
				
				

				<%-- <div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">连续/间歇生产</label>
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
				</div> --%>

				

				

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
						<label for="" class="infpLable">SO<sub>2</sub>排放许可量（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="so2ControlLimitationValue"
								value="${baseInformation.so2ControlLimitationValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">SO<sub>2</sub>排放许可量（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="so2ExamineDischargeValue"
								value="${baseInformation.so2ExamineDischargeValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">NO<sub>x</sub>排放许可量（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="noxControlLimitationValue"
								value="${baseInformation.noxControlLimitationValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">NO<sub>x</sub>排放许可量（吨）
						</label>
						<div class="infpCon">
							<input type="" class="input" name="noxExamineDischargeValue"
								value="${baseInformation.noxExamineDischargeValue}" number=true>
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
									<input type="" id="proportion1" class="input" name="proportion1"
										value="${baseInformation.proportion1}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">汽车</label>
								<div class="infpCon">
									<input type="" id="proportion2" class="input" name="proportion2"
										value="${baseInformation.proportion2}" number=true>
								</div>
							</div>

							<div class="infParaCon"
								style="width: 25%; float: left; padding-left: 20px;">
								<label for="" class="infpLable">其他</label>
								<div class="infpCon">
									<input type="" id="proportion3" class="input" name="proportion3"
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
						<label for="" class="infpLable">烟气排放速率（立方米/时）</label>
						<div class="infpCon">
							<input type="" class="input" name="smokeDischargeRate"
								value="${baseInformation.smokeDischargeRate}" number=true>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">装置安装时间</label> 
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
						<label for="" class="infpLable">污水排放量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="sewageDischargeValue"
								value="${baseInformation.sewageDischargeValue}" number=true>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">污水自处理率（%）</label>
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
						<label for="" class="infpLable">污水废气治理措施</label>
						<div class="infpCon">
							<input type="" class="input" name="sewageMeasure"
								value="${baseInformation.sewageMeasure}" />
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
				
				
				
				
				
				
				<div class="infPara infPara2"">
					<div class="infParaCon">
						<label for="" class="infpLable">烟囱数量</label>
						<input type="" class="input" id="total" name="total" value="${baseInformation.total}" readonly="readonly">
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">低于20米数量 </label>
						<div class="infpCon">
							<input type="" class="input" name="chimney20Num"
								value="${baseInformation.chimney20Num}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"> 高20米至45米数量 </label>
						<div class="infpCon">
							<input type="" class="input" name="chimney2045Num"
								value="${baseInformation.chimney2045Num}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">超过45米数量</label>
						<div class="infpCon">
							<input type="" class="input" name="chimney45Num"
								value="${baseInformation.chimney45Num}" number=true>
						</div>
					</div>
				</div>
				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">是否有喷漆工序</label>
						<div class="infpCon errorType1">
							<input type="radio" class="radio" id="isPaint1" name="isPaint"
								value="是"
								<c:if test="${baseInformation.isPaint=='是' }">checked='checked'</c:if>>
							<label for="isPaint1" class="label">是</label> 
							<input type="radio"
								class="radio" id="isPaint2" name="isPaint" value="否"
								<c:if test="${baseInformation.isPaint=='否' }">checked='checked'</c:if>>
							<label for="isPaint2" class="label">否</label>
						</div>
					</div>
				</div>
				
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">脱硫设施数量 </label>
						<div class="infpCon">
							<input type="" class="input" name="vitriolFacilityNum"
								value="${baseInformation.vitriolFacilityNum}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">脱硝设施数量 </label>
						<div class="infpCon">
							<input type="" class="input" name="nitricAcidFacilityNum"
								value="${baseInformation.nitricAcidFacilityNum}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">除尘设施数量</label>
						<div class="infpCon">
							<input type="" class="input" name="dedustingFacilityNum"
								value="${baseInformation.dedustingFacilityNum}" number=true>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">除VOC设施数量</label>
						<div class="infpCon">
							<input type="" class="input" name="vocFacilityNum"
								value="${baseInformation.vocFacilityNum}" number=true>
						</div>
					</div>
				</div>

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
					<div class="saveBtn" id="confirm">保存</div>
				</div>
			</c:if>
		</form>
	</div>
	<script type="text/javascript">
    require(["grid","hide","select","panel","validate","ajaxform","year","date", "commonBase"],function(g,hide){
    	$("#confirm").click(function(){
            $("#product_form").submit();
        })
    	
    	// 煤炭运输方式及其比例
		/* function proportion(){
			 var _proportion1 = $("#proportion1").val();
			 var _proportion2 = $("#proportion2").val();
			 var _proportion3 = $("#proportion3").val();
			 var __proportion1 = Number(_proportion1);
			 var __proportion2 = Number(_proportion2);
			 var __proportion3 = Number(_proportion3);
			 if ((__proportion1 + __proportion2 + __proportion3) !== 100) {
				 layer.msg('运输比例相加必须等于100 ！', {icon: 7});
				 return false;
			 } else {
				 return true;
			 }
		} */
			
		// 油品运输方式及其比例
		/* function oilproportion(){
			 var _oilproportion1 = $("#oilproportion1").val();
			 var _oilproportion2 = $("#oilproportion2").val();
			 var _oilproportion3 = $("#oilproportion3").val();
			 var __oilproportion1 = Number(_oilproportion1);
			 var __oilproportion2 = Number(_oilproportion2);
			 var __oilproportion3 = Number(_oilproportion3);
			 if ((__oilproportion1 + __oilproportion2 + __oilproportion3) !== 100) {
				layer.msg('运输比例相加等于100 ！！', {icon: 7});
				return false;
			} else {
				return true;
			}
		} */
		
		/* 日期比较-开始  */
		function duibi(d1,d2)
		{
	  		//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
	  		return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
	  	}
	  	/* 日期比较-结束 */
	  	
		
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
		
		/* $("#forenoonManufacture", "#product_form").jeDate({
        	format:"hh",
        	isinitVal:true,
            initDate:[{hh:"${baseInformation.forenoonManufacture}" || 05,mm:00,ss:00},false],
        	minDate:getFormatDate(new Date(),"YYYY-MM-DD 05:00:00"),
        	maxDate:getFormatDate(new Date(),"YYYY-MM-DD 12:00:00"),
            isTime:false, 
            zIndex:999999900
        });
		$("#forenoonManufactureEnd", "#product_form").jeDate({
        	format:"hh",
        	isinitVal:true,
            initDate:[{hh:"${baseInformation.forenoonManufactureEnd}" || 12,mm:00,ss:00},false],
        	minDate:getFormatDate(new Date(),"YYYY-MM-DD 05:00:00"),
        	maxDate:getFormatDate(new Date(),"YYYY-MM-DD 12:00:00"),
            isTime:false, 
            zIndex:999999900
        });
		$("#afternoonManufacture", "#product_form").jeDate({
        	format:"hh",
        	isinitVal:true,
            initDate:[{hh:"${baseInformation.afternoonManufacture}" || 12,mm:00,ss:00},false],
        	minDate:getFormatDate(new Date(),"YYYY-MM-DD 12:00:00"),
        	maxDate:getFormatDate(new Date(),"YYYY-MM-DD 18:00:00"),
            isTime:false, 
            zIndex:999999900
        });
		$("#afternoonManufactureEnd", "#product_form").jeDate({
        	format:"hh",
        	isinitVal:true,
            initDate:[{hh:"${baseInformation.afternoonManufactureEnd}" || 18,mm:00,ss:00},false],
        	minDate:getFormatDate(new Date(),"YYYY-MM-DD 12:00:00"),
        	maxDate:getFormatDate(new Date(),"YYYY-MM-DD 18:00:00"),
            isTime:false, 
            zIndex:999999900
        });
		$("#nightManufacture", "#product_form").jeDate({
        	format:"hh",
        	isinitVal:true,
            initDate:[{hh:"${baseInformation.nightManufacture}" || 18,mm:00,ss:00},false],
        	minDate:getFormatDate(new Date(),"YYYY-MM-DD 18:00:00"),
        	maxDate:getFormatDate(new Date(),"YYYY-MM-DD 23:00:00"),
            isTime:false, 
            zIndex:999999900
        });
    	
		$("#nightManufactureEnd", "#product_form").jeDate({
        	format:"hh",
        	isinitVal:true,
            initDate:[{hh:"${baseInformation.nightManufactureEnd}" || 23,mm:00,ss:00},false],
        	minDate:getFormatDate(new Date(),"YYYY-MM-DD 18:00:00"),
        	maxDate:getFormatDate(new Date(),"YYYY-MM-DD 23:00:00"),
            isTime:false, 
            zIndex:999999900
        }); */
    	
        
         $("#stopTime", "#product_form").jeDate({
            format:"YYYY-MM",
            isTime:false,
            zIndex:999999900
        })
        $("#rebuildTime", "#product_form").jeDate({
            format:"YYYY-MM",
            isTime:false,
            zIndex:999999900
        })
        $("#putProductionYear", "#product_form").jeDate({
            format:"YYYY-MM",
            isTime:false,
            zIndex:999999900
        })
        $("#installTime", "#product_form").jeDate({
            format:"YYYY-MM",
            isTime:false,
            zIndex:999999900
        })
        
        $("#enterpriseState","#product_form").select({name:"enterpriseState",defaultValue:'${baseInformation.enterpriseState}',zIndex:10,
        	change:function(event, value){
				show(value);
			}
        });
        if('${baseInformation.enterpriseState}'){
			 show('${baseInformation.enterpriseState}');
		 }
		 
		 function show(value){
			 if(value == '关停/取缔' || value == '停产'){
					$("#stopTimeDiv").show();
					$("#stopTime").attr("required", true);
					$("#stopTime").attr("disabled", false);
				}else{
					$("#stopTimeDiv").hide();
					$("#stopTime").removeAttr("required");
					$("#stopTime").attr("disabled", true);
				}
			 
			 if (value === '正常生产') {
				 $("[name='productionMonthTemp']").attr('required', true);
				 $("#dayHours").attr('required', true);
			 } else {
				 $("[name='productionMonthTemp']").removeAttr('required');
				 $("#dayHours").removeAttr('required');
				
			 }
		 }
		 
        $("#emphasisPolluteSource","#product_form").select({name:"emphasisPolluteSource",defaultValue:'${baseInformation.emphasisPolluteSource}',zIndex:7});
       // $('#putProductionYear').year({'name':'putProductionYear',defaultValue:'${baseInformation.putProductionYear}'})
        var month = $("[name=productionMonth]").val();
        if(month){
            $.each(month.split(","),function(i,v){
                 $("[name=productionMonthTemp][value="+v+"]").attr("checked","checked");
            })
        } 
        
        
        //提交表单
        $("#product_form").validate({
            rules: {
            	workDay:{
            		range:[0,366]
            	},
            	dayHours:{
            		min:0
            	},
            	grossProduct:{
            		min:0,
            		maxlength:16
            	},
            	energyUsed:{
            		min:0,
            		maxlength:16
            	},
            	nomalProductionHour:{
            		min:0
            	},
            	so2ControlLimitationValue:{
            		min:0
            	},
            	so2ExamineDischargeValue:{
            		min:0
            	},
            	noxControlLimitationValue:{
            		min:0
            	},
            	noxExamineDischargeValue:{
            		min:0
            	},
            	so2Statistics:{
            		min:0
            	},
            	noxStatistics:{
            		min:0
            	},
            	smokeStive:{
            		min:0
            	},
            	vocStatistics:{
            		min:0
            	},
            	sewageDischargeValue:{
            		min:0
            	},
            	effluentDisposeRate:{
            		range:[0,100]
            	},
            	specialtyPeoples:{
            		min:0
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
            	emphasisPolluteSource:{
					required:true
				},
				continuousIntermittence:{
					required:true
				},
				putProductionYear:{
					required:true
				},
				
				statisticsCoverage:{
					required:true
				},
				grossExamineCoverage:{
					required:true
				},
				enterpriseState:{
					required:true
				},
				isDrainContaminationCoverage: {
					required:true
				},
				isPaint: {
					required:true
				}
            },
            messages:{
            	
            },
            submitHandler:function(form){
            	
                var month= [];
                $("[name=productionMonthTemp]:checked").each(function(i,v){month.push($(v).val())});
                $("[name=productionMonth]").val(month.join(","));
                
                /* if (!proportion()) {
                	return false;
                } */
                /* if (!oilproportion()) {
                	return false;
                } */
                
              //日期比较
              if(!$("#product_form #stopTime").attr("disabled")){
            	  if(duibi($("#product_form #putProductionYear").val(), $("#product_form #stopTime").val())){
  					layer.msg('关停时间不能早于开业时间', {icon:3});
  					return false;
  				} 
              }
				
                
                var _index;
                var options  = {
                    url:base + '/web/simplebaseinformation/save.jhtml',
                    type:'post',
                    dataType : 'json',
                    beforeSubmit: function() {
						_index = top.layer.load(2);
						return true;
					},
                    success:function(data){
                        qy.suc2({title:'保存成功！'});
                        top.layer.close(_index);
                    },
                    error:function(){
                        qy.fail2({title:'保存失败！'});
                        top.layer.close(_index);
                    }
                };
                $(form).ajaxSubmit(options);
                return false;
            }
        });
        
      //判断总量核查是否选择否
		 var isDrainContaminationCoverage= $("[name=isDrainContaminationCoverage]:checked").val();
		 if(isDrainContaminationCoverage == 'true'){
       	 $("input[name=licenceNo]").removeAttr('disabled');
        }else{
       	 $("input[name=licenceNo]").attr({disabled:'true'});
        }           
	
		$("[name=isDrainContaminationCoverage]").on('change',function(){
			if ($(this).val()=='true') {
				$("input[name=licenceNo]").removeAttr('disabled');
			}else{
				$("input[name=licenceNo]").attr({disabled:'true'});
				
			};
		})
		
		
      //判断总量核查是否选择否
		 var grossExamineCoverage= $("[name=grossExamineCoverage]:checked").val();
		 if(grossExamineCoverage == '覆盖'){
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
			if ($(this).val()=='覆盖') {
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
		 if(statisticsCoverage == '覆盖'){
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
			if ($(this).val()=='覆盖') {
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
		
		var chimney20Num = $("[name=chimney20Num]").val();
		var chimney2045Num = $("[name=chimney2045Num]").val();
		var chimney45Num = $("[name=chimney45Num]").val();
		$("#total").val(Number(chimney20Num)+Number(chimney2045Num)+Number(chimney45Num));
			
		$("[name=chimney20Num]").on('change',function(){
			var chimney20Num = $("[name=chimney20Num]").val();
			var chimney2045Num = $("[name=chimney2045Num]").val();
			var chimney45Num = $("[name=chimney45Num]").val();
			$("#total").val(Number(chimney20Num)+Number(chimney2045Num)+Number(chimney45Num));
				
		})	
		$("[name=chimney2045Num]").on('change',function(){
			var chimney20Num = $("[name=chimney20Num]").val();
			var chimney2045Num = $("[name=chimney2045Num]").val();
			var chimney45Num = $("[name=chimney45Num]").val();
			$("#total").val(Number(chimney20Num)+Number(chimney2045Num)+Number(chimney45Num));
				
		})	
		$("[name=chimney45Num]").on('change',function(){
			var chimney20Num = $("[name=chimney20Num]").val();
			var chimney2045Num = $("[name=chimney2045Num]").val();
			var chimney45Num = $("[name=chimney45Num]").val();
			$("#total").val(Number(chimney20Num)+Number(chimney2045Num)+Number(chimney45Num));
				
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
		
	/* 	$("#check").click(function(){
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
    })
</script>
</body>
</html>