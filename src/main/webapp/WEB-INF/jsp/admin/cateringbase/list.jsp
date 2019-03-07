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
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生产信息</title>
<script type="text/javascript">
	require(["grid","hide", "select", "panel", "validate", "ajaxform" ,"date","year"], function(g,hide) {
		$("#startManufacture", "#product_form").jeDate({
        	format:"hh:mm:ss",
            isTime:false, 
            zIndex:999999900
        })
        $("#endManufacture", "#product_form").jeDate({
        	format:"hh:mm:ss",
            isTime:false, 
            zIndex:999999900
        })
        
        $("#cateringType", "#product_form").select({name:"cateringType",zIndex:9});
		
		$("#confirm").click(function() {
			$("#product_form").submit();
		})
		$("#cateringType", "#product_form").select({
			name : "cateringType",
			defaultValue : '${cateringBase.cateringType}',
			zIndex : 10
		});
		var month = $("[name=salesMonth]").val();
		if (month) {
			$.each(month.split(","), function(i, v) {
				$("[name=productionMonthTemp][value=" + v + "]").attr(
						"checked", "checked");
			})
		}
		var hour = $("[name=salesHour]").val();
		if (hour) {
			$.each(hour.split(","), function(i, v) {
				$("[name=salesHourTemp][value=" + v + "]").attr(
						"checked", "checked");
			})
		}

		//提交表单
		$("#product_form").validate({
			rules : {
				cateringType:{
					required:true
				},
				productionMonthTemp:{
					required:true
				},
				salesHourTemp:{
					required:true
				},
				energyUsed:{
					min:0
				},
				annualSalesVolume:{
					min:0
				},
				kitchenNum:{
					min:0,
					digits:true
				},
				salesArea:{
					min:0
				},
				description:{
					maxlength:255
				}
			},
			messages : {
				kitchenNum:{
					digits:"整数！"
				},
			},
			
			submitHandler : function(form) {
				var month = [];
				$("[name=productionMonthTemp]:checked").each(function(i, v) {
					month.push($(v).val())
				});
				$("[name=salesMonth]").val(month.join(","));
				var hour = [];
				$("[name=salesHourTemp]:checked").each(function(i, v) {
					hour.push($(v).val())
				});
				$("[name=salesHour]").val(hour.join(","));

				var options = {
					url : base + '/web/cateringbase/save.jhtml',
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
		
		hide.hide();
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
			<i class="iconfont icon-canyin"></i> 基本信息
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
			<input type="hidden" name="id" value="${cateringBase.id}" />
			
			<div class="infLine">
			
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${cateringBase.inquirer}" required>
						</div>
					</div>
				</div>
	
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${cateringBase.inquirerPhone}" required>
						</div>
					</div>
				</div>
				
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">餐饮类型</label>
					<div class="infpCon">
						<div data-code="餐饮【类型】" id="cateringType" class="selF"></div>
					</div>
				</div>
			</div>
	
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年用电量(度)</label>
					<div class="infpCon">
						<input type="" class="input" name="energyUsed" placeholder=""
						value="<fmt:formatNumber value="${cateringBase.energyUsed}" pattern="#.##"/>" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年营业额(万元)</label>
					<div class="infpCon">
						<input type="" class="input" name="annualSalesVolume" placeholder=""
						value="<fmt:formatNumber value="${cateringBase.annualSalesVolume}" pattern="#.##"/>" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">总灶头数(个)</label>
					<div class="infpCon">
						<input type="" class="input" name="kitchenNum" placeholder=""
						value="<fmt:formatNumber value="${cateringBase.kitchenNum}" pattern="#.##"/>" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">营业面积(平方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="salesArea" placeholder=""
						value="<fmt:formatNumber value="${cateringBase.salesArea}" pattern="#.##"/>" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara2">
						<div class="infParaCon">
							<label for="" class="infpLable">营业月份</label>
							<div class="infpCon errorType2">
							<input type="hidden" name="salesMonth"
							value="${cateringBase.salesMonth}">
								<div class="months">
									<input type="checkbox" class="checkbox"
									name="productionMonthTemp" value="0">
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
					
					<div class="infPara infPara2">
						<input type="hidden" name="salesHour"
							value="${cateringBase.salesHour}">
						<div class="infParaCon">
							<label for="" class="infpLable">营业时段</label>
							<div class="infpCon errorType2">
								<div class="months">
								<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="0">
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="1" id="cheH1">
									<div class="label2f">
										<label for="cheH1" class="label2">1时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="2" id="cheH2">
									<div class="label2f">
										<label for="cheH2" class="label2">2时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="3" id="cheH3">
									<div class="label2f">
										<label for="cheH3" class="label2">3时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="4" id="cheH4">
									<div class="label2f">
										<label for="cheH4" class="label2">4时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="5" id="cheH5">
									<div class="label2f">
										<label for="cheH5" class="label2">5时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="6" id="cheH6">
									<div class="label2f">
										<label for="cheH6" class="label2">6时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="7" id="cheH7">
									<div class="label2f">
										<label for="cheH7" class="label2">7时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="8" id="cheH8">
									<div class="label2f">
										<label for="cheH8" class="label2">8时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="9" id="cheH9">
									<div class="label2f">
										<label for="cheH9" class="label2">9时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="10" id="cheH10">
									<div class="label2f">
										<label for="cheH10" class="label2">10时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="11" id="cheH11">
									<div class="label2f">
										<label for="cheH11" class="label2">11时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="12" id="cheH12">
									<div class="label2f">
										<label for="cheH12" class="label2">12时</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="infPara infPara2">
						<div class="infParaCon">
							<label for="" class="infpLable">&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<div class="infpCon errorType2">
								<div class="months">
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="13" id="cheH13">
									<div class="label2f">
										<label for="cheH13" class="label2">13时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="14" id="cheH14">
									<div class="label2f">
										<label for="cheH14" class="label2">14时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="15" id="cheH15">
									<div class="label2f">
										<label for="cheH15" class="label2">15时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="16" id="cheH16">
									<div class="label2f">
										<label for="cheH16" class="label2">16时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="17" id="cheH17">
									<div class="label2f">
										<label for="cheH17" class="label2">17时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="18" id="cheH18">
									<div class="label2f">
										<label for="cheH18" class="label2">18时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="19" id="cheH19">
									<div class="label2f">
										<label for="cheH19" class="label2">19时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="20" id="cheH20">
									<div class="label2f">
										<label for="cheH20" class="label2">20时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="21" id="cheH21">
									<div class="label2f">
										<label for="cheH21" class="label2">21时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="22" id="cheH22">
									<div class="label2f">
										<label for="cheH22" class="label2">22时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="23" id="cheH23">
									<div class="label2f">
										<label for="cheH23" class="label2">23时</label>
									</div>
									<input type="checkbox" class="checkbox"
									name="salesHourTemp" value="24" id="cheH24">
									<div class="label2f">
										<label for="cheH24" class="label2">24时</label>
									</div>
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