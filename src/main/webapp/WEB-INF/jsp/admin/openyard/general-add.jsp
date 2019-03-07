<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<meta charset="UTF-8">
<title>增加露天堆场信息</title>
<style type="text/css">
label.error{
	position:absolute;
	right:70px;
	line-height:31px;
}
</style>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel"],function(hide){
		$('#commit').click(function(){
			$("#openyard_form").submit();
		})
		
		var materialType = $("#materialType","#openyard_form").select({name:"materialType",zIndex:8});
		var measure1 = $("#measure1","#openyard_form").select({name:"measure1",multselect:true,zIndex:7});
		var measure2 = $("#measure2","#openyard_form").select({name:"measure2",multselect:true,zIndex:6});
		var measure3 = $("#measure3","#openyard_form").select({name:"measure3",multselect:true,zIndex:5});
		var materialType = $("#transportWay","#openyard_form").select({name:"transportWay",zIndex:4});

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
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'添加成功！'});
						grid1.loadData();
						layer.closeAll('page');
			        },
			        error:function(){
			        	qy.fail2({title:'添加失败'});
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
						<input type="" class="input" name="material">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料堆占地面积(平方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="area" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">料堆最高高度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">堆场装卸总量(吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="loadAmount" number=true>
					</div>
				</div>
			</div>

			<div class="infh1">运载信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每车运载量(吨/车)</label>
					<div class="infpCon">
						<input type="" class="input" name="carryAmount" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">每年物料运载车次(车)</label>
					<div class="infpCon">
						<input type="" class="input" name="cargoTrips" number=true>
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
						<input type="" class="input" name="transportWayRatio" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">煤堆场是否封闭</label>
					<div class="infpCon errorType1">
						<input type="radio" class="radio" id="cheA1" name="coalClosed" value="true">
						<label for="cheA1" class="label">是</label> 
						<input type="radio" class="radio" id="cheA2" name="coalClosed" value="false">
						<label for="cheA2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">卸煤沟是否封闭</label>
					<div class="infpCon errorType1">
						<input type="radio" class="radio" id="cheB1" name="unloadCoalClosed" value="true">
						<label for="cheB1" class="label">是</label> 
						<input type="radio" class="radio" id="cheB2" name="unloadCoalClosed" value="false">
						<label for="cheB2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description"></textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>