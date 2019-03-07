<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
	.layui-layer-page .layui-layer-content{
	    overflow-x: hidden;
	}
</style>
<script type="text/javascript">
require(["validate","ajaxform"],function(a){
		$("#inputForm").validate({
			rules: {
				name: "required"
			},
			submitHandler:function(form){
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg(data.content, {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
			        },
			        error:function(){
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
		<input type="hidden" name = "id" value = "${dictionary.id}" >
		<div class="form-line">
			<label for="name" >名称 </label>
			<input type="text" id="name" name="name" placeholder="" value = "${dictionary.name}" required>
		</div>
	</div>
</form>