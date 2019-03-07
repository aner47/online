<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加项目模块信息</title>
</head>

<script type="text/javascript">
require(["validate","select","ajaxform"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		var param = '';
		if('${source}' == 'pdflist'){
			param = 'type:table';
		}
		
		var moduleClassification = $('#inputForm #moduleClassification').select({name:'moduleClassification',zIndex:13,change:function(event,value){
			moduleInfoSelect.loadData("type:"+value);
		}});
		var moduleInfoSelect = $('#inputForm #moduleInfo').select({name:'moduleInfoId',initLoad:false,zIndex:3});
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
        <input type="hidden" name="templateId" value = "${templateId}">
        <input type="hidden" name="status" value = "normal">
        <div class="container">
        	<div class="form-line">
				<label for="moduleClassification">模块类型：</label>
				<div data-code="字段模块【类型】" id="moduleClassification" class=" selectF"></div>
			</div>
            <div class="form-line">
                <label for="moduleInfo">菜单名称：</label>
                <div data-code="全局【项目模块】" id="moduleInfo" class=" selectF"></div>
            </div>
            
          

        </div>

</form>
</body>
</html>