<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改项目模块明细</title>
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
	 <input type="hidden" id="id" name="id" value="${projectModuleDetail.id}"/>
 	 <div class="form-line">
	    <label for="name" >对应表头 ：</label>
	    <input type="text" id="head" name="head" placeholder="模块名称" value="${projectModuleDetail.head}" required/>
	</div>
	<div class="form-line">
	    <label for="name" >数据关键字 ：</label>
	    <input type="text" id="key" name="key" placeholder="数据关键词" value="${projectModuleDetail.key}" required onfocus="this.blur()"/>
	</div>
	<div class="form-line">
	    <label for="name" >所属分组 ：</label>
	    <input type="text" id="headgroup" name="headgroup" placeholder="所属分组" value="${projectModuleDetail.headgroup}" />
	</div>
    <div class="form-line">
	    <label for="order" >模块排序 ：</label>
	    <input type="text" id="moduleOrder" name="moduleOrder" placeholder="模块排序" value="${projectModuleDetail.moduleOrder}" required/>
	</div>

    </div>

</form>
</body>
</html>