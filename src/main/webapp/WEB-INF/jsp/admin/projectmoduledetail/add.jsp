<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加项目模块明细</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		
		$("#moduleInfoDetail").select({name:"moduleDetailId",filter:true,param:"moduleInfo.moduleType:${projectModuleInfo.moduleType}"});
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

<form id="inputForm">
	<input type="hidden" name="projectModuleInfoId" value="${projectModuleInfo.id}">
    <div class="container">
            <div class="form-line">
                <label for="moduleInfo">模块信息：</label>
                <div data-code="全局【项目模块明细】" id="moduleInfoDetail" class=" selectF"></div>
            </div>	

    </div>

</form>
</body>
</html>