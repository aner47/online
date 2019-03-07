<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改任务管理</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
	var project1Select = $("#project1","#inputForm").select({name:"projectId",zIndex:1,defaultValue:"${taskManager.projectId}"}); 
    $("#status","#inputForm").select({immutable: true,name:"status",zIndex:2,defaultValue:"${taskManager.status}"});   
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
			        	if(data.code == 20000){
			        		layer.msg('修改成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content, {icon: 6})
							layer.closeAll('page');
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
		<div class="container">
			<div class="form-line">
				<label for="enterpriseName">已调查企业id ：</label> <input type="text"
					id="alreadyEnterpriseId" name="alreadyEnterpriseId" placeholder="企业名称"
					value="${taskManager.alreadyEnterpriseId}" />
			</div>
			<div class="form-line">
				<label for="enterpriseName">企业名称 ：</label> <input type="text"
					id="enterpriseName" name="enterpriseName" placeholder="企业名称"
					value="${taskManager.enterpriseName}" required/>
			</div>
			<div class="form-line">
				<label for="contactPerson">联系人 ：</label> <input type="text"
					id="contactPerson" name="contactPerson" placeholder="联系人"
					value="${taskManager.contactPerson}" required/>
			</div>
			<div class="form-line">
				<label for="contactPhone">联系电话 ：</label> <input type="text"
					id="contactPhone" name="contactPhone" placeholder="联系电话"
					value="${taskManager.contactPhone}" required/>
			</div>
			<div class="form-line">
				<label for="countyName">区县 ：</label> <input type="text"
					id="countyName" name="countyName" placeholder="区县"
					value="${taskManager.countyName}" required/>
			</div>
			<div class="form-line">
				<label for="executor">执行人 ：</label> <input type="text"
					id="executor" name="executor" placeholder="执行人"
					value="${taskManager.executor}" />
			</div>
			<div class="form-line">
				<label for="detailAddress">详细地址 ：</label> <input type="text"
					id="detailAddress" name="detailAddress" placeholder="详细地址"
					value="${taskManager.detailAddress}" required/>
			</div>
			<div class="form-line">
				<label for="detailAddress">组织机构代码：</label> <input type="text"
					id="code" name="code" placeholder="组织机构代码"
					value="${taskManager.code}" required/>
			</div>
			<div class="form-line">
				<label for="detailAddress">法人 ：</label> <input type="text"
					id="corporation" name="corporation" placeholder="法人"
					value="${taskManager.corporation}" required/>
			</div>
			<div class="form-line">
				<label for="status">任务状态 ：</label>
				<div id="status" class="selectF" data-code="【任务管理】状态"></div>
			</div>
			<div class="form-line">
				<label for="projectId">项目 ：</label>
				<div id="project1" class="selectF" data-code="全局【项目】"></div>
			</div>
			<input type="hidden" id="id" name="id" value="${taskManager.id}" />


		</div>

	</form>
</body>
</html>