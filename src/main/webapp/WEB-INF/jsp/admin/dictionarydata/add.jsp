<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
	.layui-layer-page .layui-layer-content{
	    overflow-x hidden;
	}
</style>
<script type="text/javascript">
require(["validate","ajaxform"],function(a){
		//提交表单
		$("#inputForm").validate({
			rules:{
				name:"required"
			},
			submitHandler:function(form){
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType:  'json',
			        success:function(data){
						layer.msg(data.content, {icon:6})
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
<div style="padding-top:5px;">
	<form id="inputForm">
		<input type="hidden" name = "dictionaryId" value = "${dictionaryId}" >
	  	<div class="form-line">
	    	<label  for="code">编码</label>
	      		<input class="form-control" type="text" id="code" name = "code"  >
	  	</div>
	  	<div class="form-line">
	    	<label  for="codeValue">值</label>
	      	<input class="form-control" type="text" id="codeValue" name = "codeValue"  >
	  	</div>
	  	<div class="form-line">
		    	<label  for="codeValue">所属分组</label>
		      	<input class="form-control" type="text" id="groupbyName" name = "groupbyName" >
	  		</div>
	    	<div class="form-line">
		    	<label  for="codeValue">二级分组</label>
		      	<input class="form-control" type="text" id="secondGroupbyName" name = "secondGroupbyName"  >
	  		</div>
	  	<div class="form-line">
	    	<label  for="order">排序</label>
	      	<input class="form-control" type="text" id="order" name = "order"  >
	  	</div>
  	 
	</form>
</div>