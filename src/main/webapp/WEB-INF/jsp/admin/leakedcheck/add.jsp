<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加设备泄露检测</title>
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
			        data:{equipment_leaked:"${equipment_leaked}"},
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
    <div class="container">
						<div class="form-line">
	            <label for="sealedPoint" >密封点(个)：</label>
	            	             <input type="text" id="sealedPoint" name="sealedPoint" placeholder="密封点(个"/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="leakedSealedPoint" >泄露点(个)：</label>
	            	             <input type="text" id="leakedSealedPoint" name="leakedSealedPoint" placeholder="泄露点(个"/>
	            	           
	        </div>
								<div class="form-line">
	            <label for="fixedSealedPoint" >修复(个)：</label>
	            	             <input type="text" id="fixedSealedPoint" name="fixedSealedPoint" placeholder="修复(个)"/>
	            	           
	        </div>
						

    </div>

</form>
</body>
</html>