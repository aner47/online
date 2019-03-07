<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>车重信息表</title>
<style type="text/css">
table {
	width: 100%;
}

td {
	width: 10%;
	height: 30px;
	border: 1px solid #ccc;
	text-align: center;
	position: relative;
}

input {
	border: none;
	width: 100%;
	height: 30px;
}

.infPara {
	padding: 20px 0px;
}

td label.error {
	position: absolute;
	left: 70px;
	top: 5px;
	width: 80px;
}

div {
	border: none;
}
</style>
<script type="text/javascript">
	require([ "grid", "hide", "select", "panel", "validate", "ajaxform" ],
			function(g, hide) {

				$("body").on("click", "#save", function() {
					$("#boiler_form").submit();
				})

				//提交表单
				$("#boiler_form").validate({

					submitHandler : function(form) {

						//以tr为单位，一个tr代表一条记录
						var constructionMachinerys = new Array();
						/* $("#table tr").each(function(){
							//this代表当前dom对象tr
							var that = this;
							var orderItemObj = new Object();
							$(that).find("input").each(function(){
								var name = $(this).attr("id");
								console.log(name);
								orderItemObj[name] = $(this).val();
							});
							orderItemArray.push(orderItemObj);
						}); */

						//constructionMachinerys.push({machineryType: "1", machineryInventory: "111", machineryPower: "123"});
						//constructionMachinerys.push({machineryType: "2", machineryInventory: "222", machineryPower: "332"});
						var options = {
							url : '../trafficcarweight/save.jhtml',
							type : 'post',
							//  contentType : 'application/x-www-form-urlencoded;charset=UTF-8', //设置请求头信息
							dataType : "json",
							//	data: JSON.stringify(constructionMachinerys),
							//data: $(form).toJSON(orderItemArray),
							//traditional: true,//必须指定为true
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

			})
</script>
<style>
.block {
	padding-bottom: 120px;
}

.examine {
	width: 42px;
	height: 30px;
	line-height: 30px !important;
	text-align: center;
	color: #fff !important;
	border-radius: 13px;
	background: #4fc1ee;
	margin-top: 9px !important;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-xuqin"></i> 车重信息
			<%-- <c:if test="${principal.userType != 'system'}">
			<!-- <div id="back" class="titleBtn">返回</div> -->
			<div id="addBtn" class="titleBtn">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<!-- <div id="fixed_return" class="titleBtn">返回</div> -->
				<div id="check" class="titleBtn examine">审核</div>
			</c:if> --%>
		</h1>


		<form id="boiler_form">
			<div class="dialog_div">
				<table id="table">
				
					<tr>
						<td colspan="2"><b>车型</b></td>
						<td><b>平均整备质量（吨）</b></td>
						<td><b>平均驾乘客及行李货物质量（吨）</b></td>
						<td><b>平均总质量（吨）</b></td>
					</tr>
					<tr>
						<input name="trafficCarWeights[0].id" type="hidden" value="${trafficCarWeight[0].id}" />
						<td class="species" rowspan="4">
							<input name="trafficCarWeights[0].carType" type="hidden" value="载客汽车" />载客汽车
						</td>
						<td class="species">
							<input name="trafficCarWeights[0].carType1" type="hidden" value="大型" />大型
						</td>
						<td>
							<input name="trafficCarWeights[0].avgQuality" value="${trafficCarWeight[0].avgQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[0].avgPassengerQuality" value="${trafficCarWeight[0].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[0].avgTotal" value="${trafficCarWeight[0].avgTotal}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[1].id" type="hidden" value="${trafficCarWeight[1].id}" />
						<input name="trafficCarWeights[1].carType" type="hidden" value="载客汽车" />
						<td class="species">
							<input name="trafficCarWeights[1].carType1" type="hidden" value="中型" />中型
						</td>
						<td>
							<input name="trafficCarWeights[1].avgQuality" value="${trafficCarWeight[1].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[1].avgPassengerQuality" value="${trafficCarWeight[1].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[1].avgTotal" value="${trafficCarWeight[1].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[2].id" type="hidden" value="${trafficCarWeight[2].id}" />
						<input name="trafficCarWeights[2].carType" type="hidden" value="载客汽车" />
						<td class="species">
							<input name="trafficCarWeights[2].carType1" type="hidden" value="小型" />小型
						</td>
						<td>
							<input name="trafficCarWeights[2].avgQuality" value="${trafficCarWeight[2].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[2].avgPassengerQuality" value="${trafficCarWeight[2].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[2].avgTotal" value="${trafficCarWeight[2].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[3].id" type="hidden" value="${trafficCarWeight[3].id}" />
						<input name="trafficCarWeights[3].carType" type="hidden" value="载客汽车" />
						<td class="species">
							<input name="trafficCarWeights[3].carType1" type="hidden" value="微型" />微型
						</td>
						<td>
							<input name="trafficCarWeights[3].avgQuality" value="${trafficCarWeight[3].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[3].avgPassengerQuality" value="${trafficCarWeight[3].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[3].avgTotal" value="${trafficCarWeight[3].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<!-- 载货汽车 -->
					<tr>
						<input name="trafficCarWeights[4].id" type="hidden" value="${trafficCarWeight[4].id}" />
						<td class="species" rowspan="4">
							<input name="trafficCarWeights[4].carType" type="hidden" value="载货汽车" />载货汽车
						</td>
						<td class="species">
							<input name="trafficCarWeights[4].carType1" type="hidden" value="重型" />重型
						</td>
						<td>
							<input name="trafficCarWeights[4].avgQuality" value="${trafficCarWeight[4].avgQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[4].avgPassengerQuality" value="${trafficCarWeight[4].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[4].avgTotal" value="${trafficCarWeight[4].avgTotal}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[5].id" type="hidden" value="${trafficCarWeight[5].id}" />
						<input name="trafficCarWeights[5].carType" type="hidden" value="载货汽车" />
						<td class="species">
							<input name="trafficCarWeights[5].carType1" type="hidden" value="中型" />中型
						</td>
						<td>
							<input name="trafficCarWeights[5].avgQuality" value="${trafficCarWeight[5].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[5].avgPassengerQuality" value="${trafficCarWeight[5].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[5].avgTotal" value="${trafficCarWeight[5].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[6].id" type="hidden" value="${trafficCarWeight[6].id}" />
						<input name="trafficCarWeights[6].carType" type="hidden" value="载货汽车" />
						<td class="species">
							<input name="trafficCarWeights[6].carType1" type="hidden" value="轻型" />轻型
						</td>
						<td>
							<input name="trafficCarWeights[6].avgQuality" value="${trafficCarWeight[6].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[6].avgPassengerQuality" value="${trafficCarWeight[6].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[6].avgTotal" value="${trafficCarWeight[6].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[7].id" type="hidden" value="${trafficCarWeight[7].id}" />
						<input name="trafficCarWeights[7].carType" type="hidden" value="载货汽车" />
						<td class="species">
							<input name="trafficCarWeights[7].carType1" type="hidden" value="微型" />微型
						</td>
						<td>
							<input name="trafficCarWeights[7].avgQuality" value="${trafficCarWeight[7].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[7].avgPassengerQuality" value="${trafficCarWeight[7].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[7].avgTotal" value="${trafficCarWeight[7].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<!-- 公交车 -->
					<tr>
						<input name="trafficCarWeights[8].id" type="hidden" value="${trafficCarWeight[8].id}" />
						<td class="species" rowspan="2">
							<input name="trafficCarWeights[8].carType" type="hidden" value="公交车" />公交车
						</td>
						<td class="species">
							<input name="trafficCarWeights[8].carType1" type="hidden" value="大型" />大型
						</td>
						<td>
							<input name="trafficCarWeights[8].avgQuality" value="${trafficCarWeight[8].avgQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[8].avgPassengerQuality" value="${trafficCarWeight[8].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[8].avgTotal" value="${trafficCarWeight[8].avgTotal}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[9].id" type="hidden" value="${trafficCarWeight[9].id}" />
						<input name="trafficCarWeights[9].carType" type="hidden" value="公交车" />
						<td class="species">
							<input name="trafficCarWeights[9].carType1" type="hidden" value="小型" />小型
						</td>
						<td>
							<input name="trafficCarWeights[9].avgQuality" value="${trafficCarWeight[9].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[9].avgPassengerQuality" value="${trafficCarWeight[9].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[9].avgTotal" value="${trafficCarWeight[9].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[10].id" type="hidden" value="${trafficCarWeight[10].id}" />
						
						<td class="species" rowspan="2">
							<input name="trafficCarWeights[10].carType" type="hidden" value="出租车" />出租车
						</td>
						<td class="species" >
							<input name="trafficCarWeights[10].carType1" type="hidden" value="大型" />大型
						</td>
						<td>
							<input name="trafficCarWeights[10].avgQuality" value="${trafficCarWeight[10].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[10].avgPassengerQuality" value="${trafficCarWeight[10].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[10].avgTotal" value="${trafficCarWeight[10].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[11].id" type="hidden" value="${trafficCarWeight[11].id}" />
						<input name="trafficCarWeights[11].carType" type="hidden" value="出租车" />
						<td class="species">
							<input name="trafficCarWeights[11].carType1" type="hidden" value="小型" />小型
						</td>
						<td>
							<input name="trafficCarWeights[11].avgQuality" value="${trafficCarWeight[11].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[11].avgPassengerQuality" value="${trafficCarWeight[11].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[11].avgTotal" value="${trafficCarWeight[11].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>
					<tr>
						<input name="trafficCarWeights[12].id" type="hidden" value="${trafficCarWeight[12].id}" />
						<input name="trafficCarWeights[12].carType1" type="hidden" value="" />
						<td class="species" colspan="2">
							<input name="trafficCarWeights[12].carType" type="hidden" value="摩托车" />摩托车
						</td>
						
						<td>
							<input name="trafficCarWeights[12].avgQuality" value="${trafficCarWeight[12].avgQuality}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[12].avgPassengerQuality" value="${trafficCarWeight[12].avgPassengerQuality}"  number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
						<td>
							<input name="trafficCarWeights[12].avgTotal" value="${trafficCarWeight[12].avgTotal}" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</td>
					</tr>

				</table>
				
			</div>
		</form>
		<div class="buttonF">
			<div class="saveBtn" id="save">保存</div>
		</div>
	</div>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>