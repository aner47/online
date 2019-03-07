<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改任务内容</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('修改成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
			        },
			        error:function(){
			        	layer.msg('修改失败',{icon:3});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});		
	});
</script>

<body>

<form id="inputForm">
    <div class="container">
	 <input type="hidden" id="id" name="id" value="${taskList.id}"/>
    <div class="form-line">
                <label for="dataName">数据名称 </label> 
                <input type="text" id="dataName"  name="dataName"  value="${taskList.dataName}" />
            </div>
            <div class="form-line">
                <label for="standardName">标准名称 </label> 
                <input type="text" id="standardName" name="standardName"  value="${taskList.standardName}" />
            </div>
            <div class="form-line">
                <label for="dataUnit">标准单位 </label> 
                <input type="text" id="dataUnit" name="dataUnit"  value="${taskList.dataUnit}" />
            </div>
            <div class="form-line">
                <label for="conversionFactor">转换系数 </label> 
                <input type="text" id="conversionFactor" name="conversionFactor"  value="${taskList.conversionFactor}" />
            </div>
            <div class="form-line">
                <label for="industryCode">行业代码 </label> 
                <input type="text" id="industryCode" name="industryCode"  value="${taskList.industryCode}" />
            </div>
            <div class="form-line">
                <label for="industryCode">源分类 </label> 
                <input type="text" id="industryCode" name="industryCode"  value="${taskList.pscc}" />
            </div>
    </div>

</form>
</body>
</html>