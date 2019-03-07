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
<div style="padding-top:5px;">
	<form id="inputForm" class="form-horizontal">
			<input type="hidden" name = "id" value = "${dictionaryData.id}" >
	    	<input type="hidden" name = "dictionaryId" value = "${dictionary.id}" >
	    	<div class="form-line">
	    		<label class="" for="code" >编码</label>
	      		<input class="form-control" type="text" id="code" name = "code"  value = "${dictionaryData.code}" >
	    	</div>
 		    <div class="form-line">
	    		<label class="" for="codeValue" >值</label>
	      		<input class="form-control" type="text" id="codeValue" name = "codeValue"  value = "${dictionaryData.codeValue}" >
	    	</div>
	    	<div class="form-line">
		    	<label  for="codeValue">所属分组</label>
		      	<input class="form-control" type="text" id="groupbyName" name = "groupbyName"  value = "${dictionaryData.groupbyName}">
	  		</div>
	    	<div class="form-line">
		    	<label  for="codeValue">二级分组</label>
		      	<input class="form-control" type="text" id="secondGroupbyName" name = "secondGroupbyName"  value = "${dictionaryData.secondGroupbyName}">
	  		</div>
	    	<div class="form-line">
		    	<label  for="order">排序</label>
		      	<input class="form-control" type="text" id="order" name = "order"  value = "${dictionaryData.order}">
	  		</div>
	</form>
</div>