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
<title>起降次数</title>
<style type="text/css">
table {
	width: 100%;
}

td {
	width: 2%;
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
		
				function aa(){
					return 'aaaa';
				}
				

				$("body").on("click", "#back", function() {
					javascript:history.go(-1);
				})
				$("body").on("click", "#save", function() {
					$("#boiler_form").submit();
				})

				//提交表单
				$("#boiler_form").validate({

					submitHandler : function(form) {

						var options = {
							url : '../civilaviation/updateAirplanetype.jhtml',
							type : 'post',
							dataType : "json",
							success : function(data) {
								qy.suc2({
									title : '保存成功！'
								});
								setTimeout(function () {
									javascript:history.go(-1);
							    }, 500);
								
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
			<i class="iconfont icon-xuqin"></i> 起降次数
		</h1>


		<form id="boiler_form">
			<div class="dialog_div">
				<input name="id" type="hidden" value="${civilAviation.id}" />
				<table id="table">
					<tr>
						<td><b>总起降次数</b></td>
						<td><b>一月</b></td>
						<td><b>二月</b></td>
						<td><b>三月</b></td>
						<td><b>四月</b></td>
						<td><b>五月</b></td>
						<td><b>六月</b></td>
						<td><b>七月</b></td>
						<td><b>八月</b></td>
						<td><b>九月</b></td>
						<td><b>十月</b></td>
						<td><b>十一月</b></td>
						<td><b>十二月</b></td>
					</tr>
					<tr>
						<td>
							<input name="totalOffLand" value="${civilAviation.totalOffLand}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="january" value="${civilAviation.january}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="february" value="${civilAviation.february}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="march" value="${civilAviation.march}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="april" value="${civilAviation.april}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="may" value="${civilAviation.may}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="june" value="${civilAviation.june}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="july" value="${civilAviation.july}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="august" value="${civilAviation.august}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="september" value="${civilAviation.september}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="october" value="${civilAviation.october}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="november" value="${civilAviation.november}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
						<td>
							<input name="december" value="${civilAviation.december}" number="true" onKeyUp="value=value.replace(/[\D]/g,'')"/>
						</td>
					</tr>
					

				</table>
				
				<h1 class="title">
					 分机型年总起降次数
				</h1>
				<table id="table">
				
					<tr>
						<td>
							<b>
							<input name="airplaneTypes[0].id" type="hidden" value="${civilAviation.airplaneTypes[0].id}" />
							<input name="airplaneTypes[0].airplaneType" type="hidden" value="A300" />A300
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[1].id" type="hidden" value="${civilAviation.airplaneTypes[1].id}" />
							<input name="airplaneTypes[1].airplaneType" type="hidden" value="A310" />A310
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[2].id" type="hidden" value="${civilAviation.airplaneTypes[2].id}" />
							<input name="airplaneTypes[2].airplaneType" type="hidden" value="A319" />A319
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[3].id" type="hidden" value="${civilAviation.airplaneTypes[3].id}" />
							<input name="airplaneTypes[3].airplaneType" type="hidden" value="A320" />A320
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[4].id" type="hidden" value="${civilAviation.airplaneTypes[4].id}" />
							<input name="airplaneTypes[4].airplaneType" type="hidden" value="A321" />A321
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[5].id" type="hidden" value="${civilAviation.airplaneTypes[5].id}" />
							<input name="airplaneTypes[5].airplaneType" type="hidden" value="A330" />A330
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[6].id" type="hidden" value="${civilAviation.airplaneTypes[6].id}" />
							<input name="airplaneTypes[6].airplaneType" type="hidden" value="A340" />A340
							</b>
						</td>
					</tr>
					<tr>
						<td>
							<input name="airplaneTypes[0].totalOffLand" value="${civilAviation.airplaneTypes[0].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[1].totalOffLand" value="${civilAviation.airplaneTypes[1].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[2].totalOffLand" value="${civilAviation.airplaneTypes[2].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[3].totalOffLand" value="${civilAviation.airplaneTypes[3].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[4].totalOffLand" value="${civilAviation.airplaneTypes[4].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[5].totalOffLand" value="${civilAviation.airplaneTypes[5].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[6].totalOffLand" value="${civilAviation.airplaneTypes[6].totalOffLand}" type=""
							number="true" />
						</td>
					</tr>
					<!-- 第二行 -->
					<tr>
						<td>
							<b>
							<input name="airplaneTypes[7].id" type="hidden" value="${civilAviation.airplaneTypes[7].id}" />
							<input name="airplaneTypes[7].airplaneType" type="hidden" value="B707" />B707
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[8].id" type="hidden" value="${civilAviation.airplaneTypes[8].id}" />
							<input name="airplaneTypes[8].airplaneType" type="hidden" value="B727" />B727
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[9].id" type="hidden" value="${civilAviation.airplaneTypes[9].id}" />
							<input name="airplaneTypes[9].airplaneType" type="hidden" value="B737" />B737
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[10].id" type="hidden" value="${civilAviation.airplaneTypes[10].id}" />
							<input name="airplaneTypes[10].airplaneType" type="hidden" value="B747" />B747
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[11].id" type="hidden" value="${civilAviation.airplaneTypes[11].id}" />
							<input name="airplaneTypes[11].airplaneType" type="hidden" value="B757" />B757
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[12].id" type="hidden" value="${civilAviation.airplaneTypes[12].id}" />
							<input name="airplaneTypes[12].airplaneType" type="hidden" value="B767" />B767
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[13].id" type="hidden" value="${civilAviation.airplaneTypes[13].id}" />
							<input name="airplaneTypes[13].airplaneType" type="hidden" value="B777" />B777
							</b>
						</td>
					</tr>
					<tr>
						<td>
							<input name="airplaneTypes[7].totalOffLand" value="${civilAviation.airplaneTypes[7].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[8].totalOffLand" value="${civilAviation.airplaneTypes[8].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[9].totalOffLand" value="${civilAviation.airplaneTypes[9].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[10].totalOffLand" value="${civilAviation.airplaneTypes[10].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[11].totalOffLand" value="${civilAviation.airplaneTypes[11].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[12].totalOffLand" value="${civilAviation.airplaneTypes[12].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[13].totalOffLand" value="${civilAviation.airplaneTypes[13].totalOffLand}" type=""
							number="true" />
						</td>
					</tr>
					<tr>
						<td>
							<b>
							<input name="airplaneTypes[14].id" type="hidden" value="${civilAviation.airplaneTypes[14].id}" />
							<input name="airplaneTypes[14].airplaneType" type="hidden" value="CL60" />CL60
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[15].id" type="hidden" value="${civilAviation.airplaneTypes[15].id}" />
							<input name="airplaneTypes[15].airplaneType" type="hidden" value="DC10" />DC10
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[16].id" type="hidden" value="${civilAviation.airplaneTypes[16].id}" />
							<input name="airplaneTypes[16].airplaneType" type="hidden" value="DC8" />DC8
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[17].id" type="hidden" value="${civilAviation.airplaneTypes[17].id}" />
							<input name="airplaneTypes[17].airplaneType" type="hidden" value="DC9" />DC9
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[18].id" type="hidden" value="${civilAviation.airplaneTypes[18].id}" />
							<input name="airplaneTypes[18].airplaneType" type="hidden" value="DF3" />DF3
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[19].id" type="hidden" value="${civilAviation.airplaneTypes[19].id}" />
							<input name="airplaneTypes[19].airplaneType" type="hidden" value="F100" />F100
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[20].id" type="hidden" value="${civilAviation.airplaneTypes[20].id}" />
							<input name="airplaneTypes[20].airplaneType" type="hidden" value="GRJ" />GRJ
							</b>
						</td>
					</tr>
					<tr>
						<td>
							<input name="airplaneTypes[14].totalOffLand" value="${civilAviation.airplaneTypes[14].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[15].totalOffLand" value="${civilAviation.airplaneTypes[15].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[16].totalOffLand" value="${civilAviation.airplaneTypes[16].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[17].totalOffLand" value="${civilAviation.airplaneTypes[17].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[18].totalOffLand" value="${civilAviation.airplaneTypes[18].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[19].totalOffLand" value="${civilAviation.airplaneTypes[19].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[20].totalOffLand" value="${civilAviation.airplaneTypes[20].totalOffLand}" type=""
							number="true" />
						</td>
					</tr>
					<tr>
						<td>
							<b>
							<input name="airplaneTypes[21].id" type="hidden" value="${civilAviation.airplaneTypes[21].id}" />
							<input name="airplaneTypes[21].airplaneType" type="hidden" value="L1011" />L1011
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[22].id" type="hidden" value="${civilAviation.airplaneTypes[22].id}" />
							<input name="airplaneTypes[22].airplaneType" type="hidden" value="LRJ" />LRJ
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[23].id" type="hidden" value="${civilAviation.airplaneTypes[23].id}" />
							<input name="airplaneTypes[23].airplaneType" type="hidden" value="MD11" />MD11
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[24].id" type="hidden" value="${civilAviation.airplaneTypes[24].id}" />
							<input name="airplaneTypes[24].airplaneType" type="hidden" value="MD80" />MD80
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[25].id" type="hidden" value="${civilAviation.airplaneTypes[25].id}" />
							<input name="airplaneTypes[25].airplaneType" type="hidden" value="MD90" />MD90
							</b>
						</td>
						<td>
							<b>
							<input name="airplaneTypes[26].id" type="hidden" value="${civilAviation.airplaneTypes[26].id}" />
							<input name="airplaneTypes[26].airplaneType" type="hidden" value="其他" />其他
							</b>
						</td>
						
					</tr>
					<tr>
						<td>
							<input name="airplaneTypes[21].totalOffLand" value="${civilAviation.airplaneTypes[21].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[22].totalOffLand" value="${civilAviation.airplaneTypes[22].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[23].totalOffLand" value="${civilAviation.airplaneTypes[23].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[24].totalOffLand" value="${civilAviation.airplaneTypes[24].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[25].totalOffLand" value="${civilAviation.airplaneTypes[25].totalOffLand}" type=""
							number="true" />
						</td>
						<td>
							<input name="airplaneTypes[26].totalOffLand" value="${civilAviation.airplaneTypes[26].totalOffLand}" type=""
							number="true" />
						</td>
					</tr>
					
				</table>
				
			</div>
		</form>
		<div class="buttonF">
			<div class="saveBtn" id="save">保存</div>
			<div class="saveBtn" id="back">返回</div>
		</div>
	</div>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>