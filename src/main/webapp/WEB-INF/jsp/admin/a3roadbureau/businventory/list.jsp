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
<title>公交车保有量信息表</title>
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
							url : '../businventory/save.jhtml',
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
			<i class="iconfont icon-xuqin"></i> 公交车保有量
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
						<td><b>车型</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>LNG/CNG</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<input name="constructionMachinerys[0].id" type="hidden"
							value="${constructionMachinery[0].id}" />
						<td class="species"><input
							name="constructionMachinerys[0].carType" type="hidden"
							value="国一前" />国一前</td>
						<td><input name="constructionMachinerys[0].gasoline"
							value="${constructionMachinery[0].gasoline}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[0].diesel"
							value="${constructionMachinery[0].diesel}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[0].lngCng"
							value="${constructionMachinery[0].lngCng}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')" /></td>
						<td><input name="constructionMachinerys[0].oilElectricity"
							value="${constructionMachinery[0].oilElectricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[0].oilGas"
							value="${constructionMachinery[0].oilGas}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[0].electricity"
							value="${constructionMachinery[0].electricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[0].other"
							value="${constructionMachinery[0].other}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					</tr>
					<tr>
						<input name="constructionMachinerys[1].id" type="hidden"
							value="${constructionMachinery[1].id}" />
						<td class="species"><input
							name="constructionMachinerys[1].carType" type="hidden" value="国一" />国一</td>
						<td><input name="constructionMachinerys[1].gasoline"
							value="${constructionMachinery[1].gasoline}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')" /></td>
						<td><input name="constructionMachinerys[1].diesel"
							value="${constructionMachinery[1].diesel}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[1].lngCng"
							value="${constructionMachinery[1].lngCng}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')" /></td>
						<td><input name="constructionMachinerys[1].oilElectricity"
							value="${constructionMachinery[1].oilElectricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[1].oilGas"
							value="${constructionMachinery[1].oilGas}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[1].electricity"
							value="${constructionMachinery[1].electricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[1].other"
							value="${constructionMachinery[1].other}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					</tr>
					<tr>
						<input name="constructionMachinerys[2].id" type="hidden"
							value="${constructionMachinery[2].id}" />
						<td class="species"><input
							name="constructionMachinerys[2].carType" type="hidden" value="国二" />国二</td>
						<td><input name="constructionMachinerys[2].gasoline"
							value="${constructionMachinery[2].gasoline}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[2].diesel"
							value="${constructionMachinery[2].diesel}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[2].lngCng"
							value="${constructionMachinery[2].lngCng}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[2].oilElectricity"
							value="${constructionMachinery[2].oilElectricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[2].oilGas"
							value="${constructionMachinery[2].oilGas}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[2].electricity"
							value="${constructionMachinery[2].electricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[2].other"
							value="${constructionMachinery[2].other}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					</tr>
					<tr>
						<input name="constructionMachinerys[3].id" type="hidden"
							value="${constructionMachinery[3].id}" />
						<td class="species"><input
							name="constructionMachinerys[3].carType" type="hidden" value="国三" />国三</td>
						<td><input name="constructionMachinerys[3].gasoline"
							value="${constructionMachinery[3].gasoline}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[3].diesel"
							value="${constructionMachinery[3].diesel}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[3].lngCng"
							value="${constructionMachinery[3].lngCng}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[3].oilElectricity"
							value="${constructionMachinery[3].oilElectricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[3].oilGas"
							value="${constructionMachinery[3].oilGas}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[3].electricity"
							value="${constructionMachinery[3].electricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[3].other"
							value="${constructionMachinery[3].other}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					</tr>
					<tr>
						<input name="constructionMachinerys[4].id" type="hidden"
							value="${constructionMachinery[4].id}" />
						<td class="species"><input
							name="constructionMachinerys[4].carType" type="hidden" value="国四" />国四</td>
						<td><input name="constructionMachinerys[4].gasoline"
							value="${constructionMachinery[4].gasoline}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[4].diesel"
							value="${constructionMachinery[4].diesel}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[4].lngCng"
							value="${constructionMachinery[4].lngCng}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[4].oilElectricity"
							value="${constructionMachinery[4].oilElectricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[4].oilGas"
							value="${constructionMachinery[4].oilGas}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[4].electricity"
							value="${constructionMachinery[4].electricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[4].other"
							value="${constructionMachinery[4].other}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					</tr>
					<tr>
						<input name="constructionMachinerys[5].id" type="hidden"
							value="${constructionMachinery[5].id}" />
						<td class="species"><input
							name="constructionMachinerys[5].carType" type="hidden" value="国五" />国五</td>
						<td><input name="constructionMachinerys[5].gasoline"
							value="${constructionMachinery[5].gasoline}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[5].diesel"
							value="${constructionMachinery[5].diesel}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[5].lngCng"
							value="${constructionMachinery[5].lngCng}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[5].oilElectricity"
							value="${constructionMachinery[5].oilElectricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[5].oilGas"
							value="${constructionMachinery[5].oilGas}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[5].electricity"
							value="${constructionMachinery[5].electricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[5].other"
							value="${constructionMachinery[5].other}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					</tr>
					<tr>
						<input name="constructionMachinerys[6].id" type="hidden"
							value="${constructionMachinery[6].id}" />
						<td class="species"><input
							name="constructionMachinerys[6].carType" type="hidden" value="国六" />国六</td>
						<td><input name="constructionMachinerys[6].gasoline"
							value="${constructionMachinery[6].gasoline}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[6].diesel"
							value="${constructionMachinery[6].diesel}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[6].lngCng"
							value="${constructionMachinery[6].lngCng}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[6].oilElectricity"
							value="${constructionMachinery[6].oilElectricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[6].oilGas"
							value="${constructionMachinery[6].oilGas}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[6].electricity"
							value="${constructionMachinery[6].electricity}" type=""
							number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
						<td><input name="constructionMachinerys[6].other"
							value="${constructionMachinery[6].other}" type="" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					</tr>

				</table>
				
				<h1 class="title">
					 车重信息
				</h1>
				<table id="table">
				
					<tr>
						<td><b>车型</b></td>
						<td><b>平均整备质量（吨）</b></td>
						<td><b>平均驾乘客及行李货物质量（吨）</b></td>
						<td><b>平均总质量（吨）</b></td>
					</tr>
					<tr>
						<input name="carWeights[0].id" type="hidden"
							value="${carWeight[0].id}" />
						<td class="species"><input
							name="carWeights[0].carType" type="hidden"
							value="大型" />大型</td>
						<td><input name="carWeights[0].avgQuality"
							value="${carWeight[0].avgQuality}" type=""
							number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
						<td><input name="carWeights[0].avgPassengerQuality"
							value="${carWeight[0].avgPassengerQuality}" type="" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
						<td><input name="carWeights[0].avgTotal"
							value="${carWeight[0].avgTotal}" type="" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					</tr>
					<tr>
						<input name="carWeights[1].id" type="hidden"
							value="${carWeight[1].id}" />
						<td class="species"><input
							name="carWeights[1].carType" type="hidden"
							value="小型" />小型</td>
						<td><input name="carWeights[1].avgQuality"
							value="${carWeight[1].avgQuality}" type=""
							number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
						<td><input name="carWeights[1].avgPassengerQuality"
							value="${carWeight[1].avgPassengerQuality}" type="" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
						<td><input name="carWeights[1].avgTotal"
							value="${carWeight[1].avgTotal}" type="" number="true" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
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