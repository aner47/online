<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>编辑露天堆场信息</title>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel"],function(hide){
		$('#commit').click(function(){
			$("#openyard_form").submit();
		})
		var materialType = $("#materialType","#openyard_form").select({name:"materialType",defaultValue:'${openYard.materialType}',zIndex:8});
		var measure1 = $("#measure1","#openyard_form").select({name:"measure1",multselect:true,zIndex:7,defaultValue:'${openYard.measure1}'});
		var measure2 = $("#measure2","#openyard_form").select({name:"measure2",multselect:true,zIndex:6,defaultValue:'${openYard.measure2}'});
		var measure3 = $("#measure3","#openyard_form").select({name:"measure3",multselect:true,zIndex:5,defaultValue:'${openYard.measure3}'});
		$("#transportWay","#openyard_form").select({name:"transportWay",defaultValue:'${openYard.transportWay}',zIndex:4});
		//提交表单
		$("#openyard_form").validate({
			rules: {
				/* moistureContent: {
					range: [0,100]
				}, */
				area:{
					range: [0,1000000]
				},
				height:{
					range: [0,50]
				},
				loadAmount:{
					min:0
				},
				carryAmount:{
					min:0
				},
				cargoTrips:{
					min:0
				},
				
				material:{
					required:true
				},
				measure1:{
					required:true
				},
				description:{
					maxlength:255
				},
				transportWayRatio:{
					range: [0,100]
				},
			},
			messages:{
				/* moistureContent: {
					range: "0~100!"
				}, */
				area:{
					range: "0~1000000!"
				},
				height:{
					range: "0~50!"
				},
				loadAmount:{
					min:"大于0"
				},
				carryAmount:{
					min:"大于0"
				},
				cargoTrips:{
					min:"大于0"
				}
			},
			submitHandler:function(form){
				var options  = {
			        url:base+'/web/openyard/update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'更新成功！'});
						grid1.loadData();
						layer.closeAll('page');
			        },
			        error:function(){
			        	qy.fail2({title:'更新失败'});
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
	<form id="openyard_form" style="padding:0;width:100%;">
		<input type="hidden" name="id" value="${openYard.id}">
		<div class="infLine">

			<div class="infh1">基本信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料堆类型</label>
					<div class="infpCon">
						<div id="materialType" data-code="祥表【堆料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料堆材料</label>
					<div class="infpCon">
						<input type="" class="input" name="material" value="${openYard.material}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料堆占地面积(平方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="area" value="${openYard.area}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料堆最高高度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" value="${openYard.height}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">堆场装卸总量(吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="loadAmount" value="${openYard.loadAmount}" number=true>
					</div>
				</div>
			</div>

			<div class="infh1">运载信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每车运载量(吨/车)</label>
					<div class="infpCon">
						<input type="" class="input" name="carryAmount" value="${openYard.carryAmount}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每年物料运载车次(车)</label>
					<div class="infpCon">
						<input type="" class="input" name="cargoTrips" value="${openYard.cargoTrips}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">扬尘控制措施1</label>
					<div class="infpCon">
						<div id="measure1" data-code="控尘措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">扬尘控制措施2</label>
					<div class="infpCon">
						<div id="measure2" data-code="控尘措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">扬尘控制措施3</label>
					<div class="infpCon">
						<div id="measure3" data-code="控尘措施" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">物料运输方式</label>
					<div class="infpCon">
						<div id="transportWay" data-code="露天堆场【物料运输方式】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">物料方式运输比例(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="transportWayRatio" value="${openYard.transportWayRatio}" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">煤堆场是否封闭</label>
					<div class="infpCon errorType1">
						<input type="radio" class="radio" id="cheA1" name="coalClosed" value="true" <c:if test="${openYard.coalClosed == 'true'}"> checked</c:if>>
						<label for="cheA1" class="label">是</label> 
						<input type="radio" class="radio" id="cheA2" name="coalClosed" value="false" <c:if test="${openYard.coalClosed == 'false'}"> checked</c:if>>
						<label for="cheA2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">卸煤沟是否封闭</label>
					<div class="infpCon errorType1">
						<input type="radio" class="radio" id="cheB1" name="unloadCoalClosed" value="true" <c:if test="${openYard.unloadCoalClosed == 'true'}"> checked</c:if>>
						<label for="cheB1" class="label">是</label> 
						<input type="radio" class="radio" id="cheB2" name="unloadCoalClosed" value="false" <c:if test="${openYard.unloadCoalClosed == 'false'}"> checked</c:if>>
						<label for="cheB2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${openYard.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>