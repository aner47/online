<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改飞机机型</title>
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
							url : '../airplanetype/update.jhtml',
							type : 'post',
							dataType : 'json',
							success : function(data) {
								qy.suc2({
									title : '修改成功！'
								});
								grid1.loadData();
								layer.closeAll('page');
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
					<label for="" class="infpLable">总起降次数</label>
					<div class="infpCon">
						<input type="" class="input" id="totalOffLand" name="totalOffLand" value="${airplaneType.totalOffLand}">
					</div>
				</div>
			</div>
	 <input type="hidden" id="id" name="id" value="${airplaneType.id}"/>
 
		<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${airplaneType.description}</textarea>
					</div>
				</div>
			</div>


    </div>

</form>
<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>