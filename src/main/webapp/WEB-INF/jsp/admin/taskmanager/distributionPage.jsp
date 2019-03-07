<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>分配任务管理</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
	var project1Select = $("#project1","#inputForm").select({name:"projectId",zIndex:9,
		
		change:function(event, value){
			user.loadData("project.id:"+value+",userType.ne:system");
			
		}
	}); 
	var user = $("#user","#inputForm").select({filter: true,initLoad:false,name:"user",param:"userType.ne:system",zIndex:8,code:"codeValue"}); 
	
	var projectId1 = ${projectId};
	
	
	
	
	
	var enterpriseType = $("#enterpriseType","#inputForm").select({initLoad:false,name:"enterpriseType",immutable:false,zIndex:7,change:function(event, value){
   		
   		projectTypeId.loadData("project:"+projectId1+",enterpriseType:"+value);
	     
	}});
   	var projectTypeId = $("#projectTypeId","#inputForm").select({initLoad:false,name:"projectTypeId",zIndex:6});
	
   	enterpriseTypeloadData(projectId1);
	function enterpriseTypeloadData(value){
		enterpriseType.loadData("project:"+value+",enterpriseTypeParam");
	}
     
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
				enterpriseType:{
					required:true,
				},
				projectTypeId:{
					required:true,
				}
			},
			messages:{
			},
			submitHandler:function(form){
				var ids = "${ids}";
				if(!ids){layer.msg("请选择任务");return};
				
				var userSelect = user.getSelectValue();
				if(!userSelect){
					//layer.msg("请选择填报人");return
					layer.confirm('未选择填报人，将自动生成企业账号?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
			    		layer.close(index);
			    		var options  = {
						        url:'createEnterpriseUser.jhtml',
						        type:'post',
						        dataType : 'json',
						        success:function(data){
						        	if(data.code == 20000){
						        		layer.msg('分配成功！', {icon: 6})
										layer.closeAll('page');
										grid1.loadData();
						        	}else{
						        		layer.msg(data.content, {icon: 3})
						        	}
									
						        },
						        error:function(){
						        	layer.msg('分配失败',{icon:3});
						        }
						    };
							$(form).ajaxSubmit(options);
							return false;
					});
				}else{
					var options  = {
					        url:'distribution.jhtml',
					        type:'post',
					        dataType : 'json',
					        success:function(data){
					        	if(data.code == 20000){
					        		layer.msg('分配成功！', {icon: 6})
									layer.closeAll('page');
									grid1.loadData();
					        	}else{
					        		layer.msg(data.content, {icon: 3})
					        	}
								
					        },
					        error:function(){
					        	layer.msg('分配失败',{icon:3});
					        }
					    };
						$(form).ajaxSubmit(options);
						return false;
				}
				
			}
		});		
	});
</script>

<body>

<form id="inputForm">
    <div class="container">
			<input type="hidden" id="ids" name="ids" value="${ids}">
			<div class="form-line">
	            <label for="projectId" >项目 ：</label>
	            <div id="project1" class="selectF" data-code="全局【项目】" ></div>
	        </div>
			<div class="form-line">
	            <label for="user" >调查人 ：</label>
	            <div id="user" class="selectF" data-code="每日任务【人员】" ></div>
	        </div>
			<div class="form-line">
	            <label for="user" >填表类型 ：</label>
	            <div id="enterpriseType" class="selectF" data-code="企业【项目类型】" ></div>
	        </div>
			<div class="form-line">
	            <label for="user" >项目类型：</label>
	            <div id="projectTypeId" class="selectF" data-code="企业【项目类型】" ></div>
	        </div>
	        
    </div>

</form>
</body>
</html>