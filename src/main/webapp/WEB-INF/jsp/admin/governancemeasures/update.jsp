<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>修改污染治理措施</title>
<style>
	.label_margin {
		margin-right: 7px !important;
	}
	.radio_margin {
		margin-right: 3px !important;
	}
</style>
</head>

<script type="text/javascript">
	require(["hide","localCache", "validate", "ajaxform", "select", "panel", "date" ], function(hide,localCache) {
		showRequired("${governanceMeasures.name}");
		
		var putProductionYear = localCache.getCookie("putProductionYear");
		console.log(putProductionYear)
		
		var measure = $("#measure", "#control_form").select({
			filter:false,
			name : "measure",
			isCustom: true,
			zIndex :10,
			defaultValue : "${governanceMeasures.measure}",
			empty : false,
			change : function(event, value) {
				name.loadData(value);
				
			}
		});
		var contaminantType = $("#contaminantType", "#control_form").select({
			filter:false,multselect: true,
			name : "contaminantType",
			isCustom: true,
			defaultValue : "${governanceMeasures.contaminantType}",
			zIndex : 9,
			initAfter:function(){
				divShow('${governanceMeasures.contaminantType}');
			},
			change : function(event, value) {
				console.log(value);
				divShow(value);
			}
		});
		var name = $("#name", "#control_form").select({
			name : "name",isCustom : true,
			multselect : true,
			defaultValue : "${governanceMeasures.name}",
			empty : false,
			change : function(event, value) {
				showRequired(value);
			}
		});
		$('#commit').click(function() {
			$("#control_form").submit();
		})
		
		
		/* 日期比较-开始  */
		function duibi(d1,d2)
		{
	  		//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
	  		return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
	  	}
	  	/* 日期比较-结束 */
		
		$("#putDate", "#control_form").jeDate({
			format : "YYYY-MM",
			isTime : false,
			zIndex : 999999900,

		})
		$("#stopDate", "#control_form").jeDate({
			format : "YYYY-MM",
			isTime : false,
			zIndex : 999999900,

		})
		
		function showRequired(value){
			if(value == '（99）无'){
				$("#putDate", "#control_form").removeAttr("required");
			}else{
				$("#putDate", "#control_form").attr("required", "true");
			}
			if (value == '（18）布袋除尘法') {
				$("[name='dedustingFrequency']").attr("required", true);
			} else {
				$("[name='dedustingFrequency']").removeAttr("required");
			}
		}

		function divShow(value) {
			$("#desulfuration").hide();
			$("#denitration").hide();
			$("#dedusting").hide();
			$("#vocdiv").hide();
			
			if (value.indexOf('SO2') != -1 ) {
				$("#desulfuration").show();
			}
			if (value.indexOf('NOx') != -1 || value.indexOf('NH3') != -1) {
				$("#denitration").show();
			} 
			if (value.indexOf('颗粒物') != -1) {
				$("#dedusting").show();
			}
			if (value.indexOf('VOCs') != -1) {
				$("#vocdiv").show();
			}
		}

		//提交表单
		$("#control_form").validate({
			rules : {
				workHours : {
					range : [ 0, 8784 ]
				},
				collectEfficiency : {
					range : [ 0, 100 ]
				},
				so2 : {
					range : [ 0, 100 ]
				},
				nox : {
					range : [ 0, 100 ]
				},
				co : {
					range : [ 0, 100 ]
				},
				pm25 : {
					range : [ 0, 100 ]
				},
				bc : {
					range : [ 0, 100 ]
				},
				oc : {
					range : [ 0, 100 ]
				},
				tsp : {
					range : [ 0, 100 ]
				},
				pm10 : {
					range : [ 0, 100 ]
				},
				voc : {
					range : [ 0, 100 ]
				},
				nh3 : {
					range : [ 0, 100 ]
				},
				putintoRate : {
					range : [ 0, 100 ]
				},
				desulfurationRate : {
					range : [ 0, 100 ]
				},
				denitrationRate : {
					range : [ 0, 100 ]
				},
				dedustingRate : {
					range : [ 0, 100 ]
				},
				desulfurationDosage : {
					min : 0
				},
				denitrationDosage : {
					min : 0
				},
				name : {
					required : true
				},
				measure : {
					required : true
				},
				putDate : {
					required : true
				},
				isGGH : {
					required : true
				},
				contaminantType : {
					required : true
				},
				description : {
					maxlength : 255
				}
			},
			messages : {
				description : {
					maxlength : "最多255字"
				}
			},
			submitHandler : function(form) {
				//日期比较
				if(duibi($("#control_form #putDate").val(),$("#control_form #stopDate").val())){
					layer.msg('停运时间不能早于投运时间',{icon:3});
					return false;
				}
				
				if (putProductionYear > $("#control_form #putDate").val()) {
					layer.msg('投运时间不能早于开业时间',{icon:3});
					return false;
				}
				
				var options = {
					url : '../governancemeasures/update.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						qy.suc2({
							title : '更新成功！'
						});
						grid1.loadData();
						layer.closeAll('page');
					},
					error : function() {
						qy.fail2({
							title : '更新失败'
						});
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
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
	<form class="hideCls" id="control_form" style="padding: 0; width: 100%;">

		<input type="hidden" name="id" value="${governanceMeasures.id}" />

		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施</label>
					<div class="infpCon">
						<div id="measure" data-code="治理措施" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理工艺</label>
					<div class="infpCon">
						<div id="name" data-code="治理工艺" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>污染物类型(多选)</label>
					<div class="infpCon">
						<div id="contaminantType" data-code="治理措施【污染物类型】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">污染物去除效率(%)</label>
					<div class="infpCon">
						<input type="" class="input" value='${governanceMeasures.contaminantRate}' name="contaminantRate" number="true" >
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>投运时间</label>
					<div class="infpCon">
						<input type="" class="input" name="putDate"
							value="<fmt:formatDate value='${governanceMeasures.putDate}' pattern='yyyy-MM'/>"
							id="putDate" onfocus="this.blur()" />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">停运时间</label>
					<div class="infpCon">
						<input type="" class="input" name="stopDate" id="stopDate"
						value="<fmt:formatDate value='${governanceMeasures.stopDate}' pattern='yyyy-MM'/>"
							onfocus="this.blur()"/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="putintoRate"
							value="${governanceMeasures.putintoRate}" number="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年工作时间（小时/年）</label>
					<div class="infpCon">
						<input type="" class="input" name="workHours"
						value="${governanceMeasures.workHours}" number="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">废气收集效率(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="collectEfficiency"
							value="${governanceMeasures.collectEfficiency}" number="true">
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否安装GGH(烟气加热)</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="isGGH1"
							name="isGGH" value="是"
							<c:if test="${governanceMeasures.isGGH=='是' }">checked='checked'</c:if>>
						<label for="isGGH1" class="label label_margin">是</label> 
						<input type="radio" class="radio radio_margin" id="isGGH2"
							name="isGGH" value="否"
							<c:if test="${governanceMeasures.isGGH=='否' }">checked='checked'</c:if>>
						<label for="isGGH2" class="label label_margin">否</label>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>初次投入成本（万元）</label>
					<div class="infpCon">
						<input type="" class="input" name="firstPut"
						value="${governanceMeasures.firstPut}"
							number="true" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>运行成本（万元/月）</label>
					<div class="infpCon">
						<input type="" class="input" name="operatingCost"
						value="${governanceMeasures.operatingCost}"
							number="true" required>
					</div>
				</div>
			</div>
			
			<div id="desulfuration">
				<div class="infh1">脱硫</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">脱硫率(%)</label>
						<div class="infpCon">
							<input type="" class="input" name="desulfurationRate"
								value="${governanceMeasures.desulfurationRate}" number="true">
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>脱硫剂名称</label>
						<div class="infpCon">
							<input type="" class="input"
								value="${governanceMeasures.desulfurationType}"
								name="desulfurationType" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>脱硫剂使用量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="desulfurationDosage"
								value="${governanceMeasures.desulfurationDosage}" number="true" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>脱硫剂更换频率（次/年）</label>
						<div class="infpCon">
							<input type="" class="input" name="desulfurationfrequency"
							value="${governanceMeasures.desulfurationfrequency}"
								number="true" required>
						</div>
					</div>
				</div>
			</div>
			<div id="denitration">
				<div class="infh1">脱硝</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">脱硝率(%)</label>
						<div class="infpCon">
							<input type="" class="input" name="denitrationRate"
								value="${governanceMeasures.denitrationRate}" number="true">
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>脱硝剂名称</label>
						<div class="infpCon">
							<input type="" class="input" name="denitrationType"
								value="${governanceMeasures.denitrationType}" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>脱硝剂使用量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="denitrationDosage"
								value="${governanceMeasures.denitrationDosage}" number="true" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>脱硝剂更换频率（次/年）</label>
						<div class="infpCon">
							<input type="" class="input" name="denitrationfrequency"
							value="${governanceMeasures.denitrationfrequency}"
								number="true" required>
						</div>
					</div>
				</div>
			</div>
			<div id="dedusting">
				<div class="infh1">除尘</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">除尘率(%)</label>
						<div class="infpCon">
							<input type="" class="input" name="dedustingRate"
								value="${governanceMeasures.dedustingRate}" number="true">
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">PM₁₀去除率（%）</label>
						<div class="infpCon">
							<input type="" class="input" name="pm10"
								value="${governanceMeasures.pm10}" >
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">PM₂.₅去除率（%）</label>
						<div class="infpCon">
							<input type="" class="input" name="pm25"
								value="${governanceMeasures.pm25}" number="true" >
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">布袋清洗频率（次/年）</label>
						<div class="infpCon">
							<input type="" class="input" name="dedustingFrequency" 
							value="${governanceMeasures.dedustingFrequency}" number="true" >
						</div>
					</div>
				</div>
			</div>
			<div id="vocdiv">
				<div class="infh1">去除voc</div>

				<%-- <div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">设备风量(立方米/小时)</label>
						<div class="infpCon">
							<input type="" class="input" name="equipmentWindMeasure"
								value="${governanceMeasures.equipmentWindMeasure}" number="true">
						</div>
					</div>
				</div> --%>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">VOC去除率（%）</label>
						<div class="infpCon">
							<input type="" class="input" name="voc"
								value="${governanceMeasures.voc}" number="true">
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>吸附剂名称</label>
						<div class="infpCon">
							<input type="" class="input" name="adsorbentName"
							value="${governanceMeasures.adsorbentName}" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>吸附剂用量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="adsorbentDosage"
							value="${governanceMeasures.adsorbentDosage}" number="true" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>吸附剂更换频率（次/年）</label>
						<div class="infpCon">
							<input type="" class="input" name="adsorbentfrequency"
							value="${governanceMeasures.adsorbentfrequency}" number="true" required>
						</div>
					</div>
				</div>
				<%-- <div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">有机废气排放浓度(毫克/立方米)</label>
						<div class="infpCon">
							<input type="" class="input" name="fluegasLetoutConcentration"
								value="${governanceMeasures.fluegasLetoutConcentration}"
								number="true">
						</div>
					</div>
				</div> --%>
			</div>


			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${governanceMeasures.description}</textarea>
					</div>
				</div>
			</div>

		</div>
	</form>
</body>
</html>