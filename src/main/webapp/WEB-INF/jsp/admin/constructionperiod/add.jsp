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
<title>添加施工周期信息</title>
<style type="text/css">
#sumDays-error,#constructionPhases-error{
	top:0px;
}
.infParaCon .infpCon label.error{
	top:20px;
	width:80px;
	right:-25px;
}
</style>
</head>

<script type="text/javascript">
	require(
			["hide", "validate", "ajaxform", "select", "panel" ,"date" ],
			function(hide) {
				
				$("#workDate", "#boiler_form").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false, 
		            zIndex:999999900
		        })
		        $("#completedDate", "#boiler_form").jeDate({
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
		        
				//燃料类型
				$("#constructionPhases", "#boiler_form").select({name:"constructionPhases",zIndex:9});
				
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
										constructionPhases:{
											required:true
										},
										sumDays:{
											min:0
										},
										mechanical1:{
											min:0
										},
										mechanical2:{
											min:0
										},
										mechanical3:{
											min:0
										},
										mechanical4:{
											min:0
										},
										mechanical5:{
											min:0
										},
										mechanical6:{
											min:0
										},
										mechanical7:{
											min:0
										},
										mechanical8:{
											min:0
										},
										mechanical9:{
											min:0
										},
										mechanical10:{
											min:0
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
										if(duibi($("#boiler_form #workDate").val(),$("#boiler_form #completedDate").val())){
											layer.msg('竣工日期不能早于开工日期',{icon:3});
											return false;
										}
										var options = {
											url : base
													+ '/web/constructionperiod/save.jhtml',
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
					<label for="" class="infpLable">施工阶段</label>
					<div class="infpCon">
						<div id="constructionPhases" data-code="施工工地【施工阶段】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">总施工周期（天）</label>
					<div class="infpCon">
						<input class="input" name="sumDays" placeholder="总施工周期" number=true required/>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">开工日期</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="" id="workDate" name="workDate" class="Yinput" placeholder="开工日期" onfocus="this.blur()">
						</div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">竣工日期</label>
					<div class="infpCon">
						<div class="yearF iconfont icon-nianfen">
							<input type="" id="completedDate" name="completedDate" class="Yinput" placeholder="竣工日期" readonly>
						</div>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">挖掘机</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical1" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">装载机</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical2" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">推土机</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical3" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">铲运机</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical4" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">平地机</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical5" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">起重机械</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical6" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">桩工机械</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical7" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">工程车辆</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical8" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">叉车</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical9" number="true">
					</div>
				</div>
			</div>
			
			<div class="infPara infPara4">
				<div class="infParaCon">
					<label for="" class="infpLable">其他</label>
					<div class="infpCon">
						<input type="" class="input" name="mechanical10" number="true">
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