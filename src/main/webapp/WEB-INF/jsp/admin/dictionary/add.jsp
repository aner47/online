<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
	.layui-layer-page .layui-layer-content{
	    overflow-x: hidden;
	}
</style>
<script type="text/javascript">
require(["validate","ajaxform"],function(a){
		//提交表单
		$("#inputForm").validate({
			rules: {
				name: "required"
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg(data.content, {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
			        },
			        error:function(data){
			        	layer.msg('失败',{icon:3})
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});
	});
</script>
<form id="inputForm">
	<div class="container">
		<div class="form-line">
			<label for="name" >名称 </label>
			<input type="text" id="name" name="name" placeholder="" required>
		</div>
		<div class="form-line">
			<label for="beanName" >bean名称</label>
			<input type="text" id="beanName" name="beanName" placeholder="" >
		</div>
	</div>
</form>