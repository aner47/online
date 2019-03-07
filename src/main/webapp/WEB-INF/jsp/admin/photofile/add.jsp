<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加照片</title>
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
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == '20000'){
			        		layer.msg('添加成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content,{icon:3});
			        	}
						
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
    <div class="container">
				

    </div>

</form>
</body>
</html>