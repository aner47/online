<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>增加单独锅炉信息</title>
</head>

<script type="text/javascript">
	require(
			["hide", "validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				$("#stopData", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        })
		        $("#operation", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        })
		         /* 日期比较-开始  */
				function duibi(d1,d2)
				{
		  			//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		  			return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
				}
				/* 日期比较-结束 */
		        
				$("#boilerType", "#boiler_form").select({name:"boilerType",zIndex:9});
				//锅炉用途
				$("#purpose", "#boiler_form").select({name:"purpose",zIndex:8});
				//燃料类型
				$("#fuelType", "#boiler_form").select({name:"fuelType",zIndex:7});
				//脱硫工艺
				$("#governanceMeasures1", "#boiler_form").select({name:"governanceMeasures1",multselect:true,zIndex:6});
				//脱硝工艺
				$("#governanceMeasures2", "#boiler_form").select({name:"governanceMeasures2",multselect:true,zIndex:5});
				//除尘工艺
				$("#governanceMeasures3", "#boiler_form").select({name:"governanceMeasures3",multselect:true,zIndex:4});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form")
						.validate(
								{
									rules : {
										fuelSulfurContent : {
											range : [ 0, 100 ],
										},
										fuelAsh : {
											range : [ 0, 100 ],
										},
										fuelVolatiles : {
											range : [ 0, 100 ],
										},
										boilerType:{
											required:true
										},
										steamTon:{
											min:0
										},
										purpose:{
											required:true
										},
										fuelType:{
											required:true
										},
										height:{
											min:0
										},
										governanceMeasures1:{
											required:true
										},
										governanceMeasures2:{
											required:true
										},
										governanceMeasures3:{
											required:true
										},
										description:{
											maxlength:255
										}

									},
									messages : {
										fuelSulfurContent : {
											range : "0~100!",
										},
										fuelAsh : {
											range : "0~100!",
										},
										fuelVolatiles : {
											range : "0~100!",
										},
										
									},
									submitHandler : function(form) {
										//日期比较
										if(duibi($("#boiler_form #operation").val(),$("#boiler_form #stopData").val())){
											layer.msg('关停时间不能早于投运时间',{icon:3});
											return false;
										}
										var options = {
											url : base
													+ '/web/singleboilerinformation/save.jhtml',
											type : 'post',
											dataType : 'json',
											success : function(data) {
												qy.suc2({
													title : '添加成功！'
												});
												grid1.loadData();
												layer.closeAll('page');
											},
											error : function() {
												qy.fail({
													title : '添加失败'
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
	
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉类型</label>
					<div class="infpCon">
						<div id="boilerType" data-code="锅炉类型" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉型号</label>
					<div class="infpCon">
						<input class="input" name="boilerModel" placeholder="锅炉型号" />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">蒸吨数（t/h）</label>
					<div class="infpCon">
						<input type="" class="input" name="steamTon" number=true>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="" id="operation" name="operation" class="Yinput" onfocus="this.blur()" required>
						</div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="" id="stopData" name="stopData" class="Yinput" readonly>
						</div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉用途</label>
					<div class="infpCon">
						<div id="purpose" data-code="单独锅炉【锅炉用途】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="单独锅炉【燃料类型】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口高度（米）</label>
					<div class="infpCon">
						<input type="" class="input" name="height" number=true required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">锅炉调查是否覆盖</label>
					<div class="infpCon errorType2">
						<input type="radio" class="radio" id="cheC1" name="isSurveyCover" value="true" required>
						<label for="cheC1" class="label">是</label>
						<input type="radio" class="radio" id="cheC2" name="isSurveyCover" value="false">
						<label for="cheC2" class="label" style="margin-right: 0;">否</label>
					</div>
				</div>
			</div>
			
			<div class="infh1">污染物治理措施（可多选）</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硫工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="单独锅炉【脱硫工艺】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">脱硝工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="单独锅炉【脱硝工艺】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">除尘工艺</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="单独锅炉【除尘工艺】" class="selF"></div>
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