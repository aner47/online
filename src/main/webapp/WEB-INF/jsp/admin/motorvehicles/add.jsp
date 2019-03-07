<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加机动车调查</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
</head>

<script type="text/javascript">
require(["validate","ajaxform","date"],function(a){
	
	$("#surveyDate", "#inputForm").jeDate({
        format:"YYYY-MM-DD",
        isTime:false, 
        zIndex:999999900
    })
    
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		$("#estimationEethod", "#inputForm").select({
			name : "estimationEethod",
			zIndex : 10
		});
		//提交表单
		$("#inputForm").validate({
			rules: {
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
			        },
			        error:function(){
			        	layer.msg('添加失败',{icon:3});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});		
	});
</script>

<body>
	<form id="inputForm" style="padding:0;width:100%;">
		
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">调查地点</label>
					<div class="infpCon">
						<input type="" class="input" id="surveyPlace" name="surveyPlace" placeholder="调查地点">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">调查人</label>
					<div class="infpCon">
						<input type="" class="input" id="surveyPeople" name="surveyPeople" placeholder="调查人">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">调查日期</label>
					<div class="infpCon">
						<input type="text"  class="input" id="surveyDate" name="surveyDate" placeholder="调查日期">
					</div>
				</div>
			</div>

			

		</div>
	</form>
</body>
</html>