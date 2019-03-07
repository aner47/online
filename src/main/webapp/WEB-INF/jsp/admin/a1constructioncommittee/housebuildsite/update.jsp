<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>修改畜禽养殖</title>
</head>

<script type="text/javascript">
	require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#startDate", "#boiler_form").jeDate({
            format:"YYYY-MM-DD",
            isTime:false, 
            zIndex:999999900
        });
		$("#endDate", "#boiler_form").jeDate({
            format:"YYYY-MM-DD",
            isTime:false, 
            zIndex:999999900
        });

		$("#dustMeasures", "#boiler_form").select({name:"dustMeasures",defaultValue:"${houseBuildSite.dustMeasures}",multselect:true,zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					
					submitHandler : function(form) {

						var options = {
							url : '../housebuildsite/update.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if(data.code == 20000){
									qy.suc2({
										title : '修改成功！'
									});
									grid1.loadData();
									layer.closeAll('page');
								}else{
									qy.fail({
										title : '修改失败'+data.content
									});
								}
							},
							error : function() {
								qy.fail({
									title : '修改失败'
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
	<form id="boiler_form">
		<div class="dialog_div">
				<input type="hidden" name="id" value="${houseBuildSite.id }" />
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">行政区域</label>
						<div class="infpCon">
							<input type="" class="input" name="city"
								value="${houseBuildSite.city }">
						</div>
					</div>
				</div>

				<div class="clearfix"></div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">项目名称</label>
						<div class="infpCon">
							<input type="" class="input" name="enterprise.name"
								value="${houseBuildSite.enterprise.name }" required>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">工程详细地址</label>
						<div class="infpCon">
							<input type="" class="input"
								name="houseNumber"
								value="${houseBuildSite.houseNumber}">
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">中心经度</label>
						<div class="infpCon">
							<input type="" class="input" name="longitude"
								value="${houseBuildSite.longitude}" number="true"   onKeyUp="value=value.replace(/[^0-9.]/g,'')">
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">中心纬度</label>
						<div class="infpCon">
							<input type="" class="input" name="latitude"
								value="${houseBuildSite.latitude}" number="true"   onKeyUp="value=value.replace(/[^0-9.]/g,'')">
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">占地面积（平方米）</label>
						<div class="infpCon">
							<input type="" class="input" name="floorSpace" number="true"
								value="${houseBuildSite.floorSpace}" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">建筑面积（平方米）</label>
						<div class="infpCon">
							<input type="" class="input" name="buildSpace" number="true"
								value="${houseBuildSite.buildSpace}" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">设计开挖土方量（立方米）</label>
						<div class="infpCon">
							<input type="" class="input" name="designExcavations" number="true"
								value="${houseBuildSite.designExcavations}" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">已开挖土方量（立方米）</label>
						<div class="infpCon">
							<input type="" class="input" name="excavatedVolume" number="true"
								value="${houseBuildSite.excavatedVolume}" onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">开工时间</label>
						<div class="infpCon">
							<input type="" class="input" id="startDate" name="startDate"
							value="<fmt:formatDate value='${houseBuildSite.startDate}' pattern='yyyy-MM-dd'/>"
							onfocus="this.blur()">
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">竣工时间</label>
						<div class="infpCon">
							<input type="" class="input" id="endDate" name="endDate"
								value="<fmt:formatDate value='${houseBuildSite.endDate}' pattern='yyyy-MM-dd'/>"
								onfocus="this.blur()">
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">扬尘控制措施</label>
						<div class="infpCon">
						<div data-code="房屋建筑工地【扬尘控制措施】" id="dustMeasures" class="selF"></div>
							<%-- <input type="" class="input" name="dustMeasures"
								value="${houseBuildSite.dustMeasures}"> --%>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">建筑涂料使用量（吨）</label>
						<div class="infpCon">
							<input type="" class="input" name="paintUseVol"
								value="${houseBuildSite.paintUseVol}" number=true onKeyUp="value=value.replace(/[^0-9.]/g,'')">
						</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${houseBuildSite.description}</textarea>
						</div>
					</div>
				</div>
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>