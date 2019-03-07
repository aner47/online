<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改企业燃气消耗分月统计</title>
</head>

<script type="text/javascript">
require([ "hide", "validate", "ajaxform", "select", "panel", "date" ],
			function(hide) {

		$("#stockgroundDustMeasures", "#boiler_form").select({name:"stockgroundDustMeasures",defaultValue:"${betonStirStation.stockgroundDustMeasures}",zIndex:9});
		
				$('#commit').click(function() {
					$("#boiler_form").submit();
				})
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {

						var options = {
							url : '../enterprisefuelgasmonth/update.jhtml',
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
    <div class="container">
    
    		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">工业企业名称</label>
					<div class="infpCon">
						<input type="" class="input" id="enterprise.name" name="enterprise.name" value="${enterpriseFuelGasMonth.enterprise.name}">
					</div>
				</div>
			</div>
			<div class="infh1">燃气消耗量（万立方米）</div>
    		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">一月</label>
					<div class="infpCon">
						<input type="" class="input" id="january" name="january" value="${enterpriseFuelGasMonth.january}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">二月</label>
					<div class="infpCon">
						<input type="" class="input" id="february" name="february" value="${enterpriseFuelGasMonth.february}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">三月</label>
					<div class="infpCon">
						<input type="" class="input" id="march" name="march" value="${enterpriseFuelGasMonth.march}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">四月</label>
					<div class="infpCon">
						<input type="" class="input" id="april" name="april" value="${enterpriseFuelGasMonth.april}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">五月</label>
					<div class="infpCon">
						<input type="" class="input" id="may" name="may" value="${enterpriseFuelGasMonth.may}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">六月</label>
					<div class="infpCon">
						<input type="" class="input" id="june" name="june" value="${enterpriseFuelGasMonth.june}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">七月</label>
					<div class="infpCon">
						<input type="" class="input" id="july" name="july" value="${enterpriseFuelGasMonth.july}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">八月</label>
					<div class="infpCon">
						<input type="" class="input" id="august" name="august" value="${enterpriseFuelGasMonth.august}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">九月</label>
					<div class="infpCon">
						<input type="" class="input" id="september" name="september" value="${enterpriseFuelGasMonth.september}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">十月</label>
					<div class="infpCon">
						<input type="" class="input" id="october" name="october" value="${enterpriseFuelGasMonth.october}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">十一月</label>
					<div class="infpCon">
						<input type="" class="input" id="november" name="november" value="${enterpriseFuelGasMonth.november}" number="true">
					</div>
				</div>
			</div>
		<div class="infPara ">
				<div class="infParaCon">
					<label for="" class="infpLable">十二月</label>
					<div class="infpCon">
						<input type="" class="input" id="december" name="december" value="${enterpriseFuelGasMonth.december}" number="true">
					</div>
				</div>
			</div>
	 <input type="hidden" id="id" name="id" value="${enterpriseFuelGasMonth.id}"/>
 
		<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${enterpriseFuelGasMonth.description}</textarea>
					</div>
				</div>
			</div>


    </div>

</form>
<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>