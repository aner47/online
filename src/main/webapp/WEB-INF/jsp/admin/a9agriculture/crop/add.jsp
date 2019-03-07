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
<title>市农业委-农作物产量及秸秆利用情况</title>
</head>

<script type="text/javascript">
	require(
			["hide","validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				
				
				$("#cropType", "#boiler_form").select({name:"cropType",isCustom:true,zIndex:9});
				$("#breedingMethod", "#boiler_form").select({name:"breedingMethod",zIndex:8});
				
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					rules:{
						strawManure:{
							range:[0,100]
						},
						strawFodder:{
							range:[0,100]
						},
						strawFuel:{
							range:[0,100]
						},
						industrialRaw:{
							range:[0,100]
						},
						edibleRaw:{
							range:[0,100]
						},
						openBurning:{
							range:[0,100]
						},
						other:{
							range:[0,100]
						},
					},
					messages:{
						
					},
					submitHandler : function(form) {
						
						var options = {
							url : '../crop/save.jhtml',
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
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">行政区</label>
					<div class="infpCon">
						<input type="" class="input" name="city">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">农作物类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="cropType" >  -->
						<div data-code="农业委农作物产量及秸秆利用【农作物类型】" id="cropType" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">产量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" name="yield" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')">
					</div>
				</div>
			</div>
			
			
			
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">秸秆肥料利用比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="strawManure" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
						
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">秸秆饲料利用比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="strawFodder" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/> 
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">秸秆燃料利用比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="strawFuel" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">工业原料利用比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="industrialRaw" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">食用菌基料利用比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="edibleRaw" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">露天焚烧利用比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="openBurning" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">其他利用比例（%）</label>
					<div class="infpCon">
						<input type="" class="input" name="other" number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/>
					</div>
				</div>
			</div>
			
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