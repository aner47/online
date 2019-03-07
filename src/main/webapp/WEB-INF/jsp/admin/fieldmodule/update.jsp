<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改菜单信息</title>
</head>

<script type="text/javascript">
require(["validate","select","ajaxform"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		var tableType= $("#inputForm #tableType").select({filter:true,name:"tableType",zIndex:4,defaultValue:"${moduleInfo.tableType}",change:function(event, value){
			name.loadData(value);
			},initAfter:function(){
				name.loadData('${moduleInfo.tableType}');
			}
		});
		
		
		var name = $("#inputForm #name").select({filter:true,name:"name",param:tableType.getSelectValue(),zIndex:3,defaultValue:"${moduleInfo.name}",change:function(event, value){}});
		
		
		$("#inputForm").validate({
			rules: {
				tableType:{
					required:true
				},
				name:{
					required:true
				}
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		layer.msg('修改成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content,{icon:3});
			        	}
						
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
		<input type="hidden" id="id" name="id" value="${moduleInfo.id}"/>
		<div class="container">
			<div class="form-line">
				<label for="moduleClassification">填表类型：</label>
				<div data-code="企业【企业类型】" id="tableType" class=" selectF"></div>
			</div>
			<div class="form-line">
				<label for="name">菜单名称 ：</label>
				 <%-- <input type="text" id="name"
					name="name" placeholder="菜单名称" value="${moduleInfo.name }" /> --%>
					<div data-code="后台【显示菜单选择】" id="name" class=" selectF"></div>

			</div>
			<div class="form-line">
				<label for="name">排序：</label> 
				<input type="text" id="orders" name="orders" placeholder="排序" value="${moduleInfo.orders }" required/>

			</div>
		</div>

	</form>
</body>
</html>