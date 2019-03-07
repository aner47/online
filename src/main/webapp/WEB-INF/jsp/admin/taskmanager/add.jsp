<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加任务管理</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
	var project1Select = $("#project1","#inputForm").select({name:"projectId",zIndex:1}); 
  //  $("#status","#inputForm").select({name:"status",zIndex:2});   
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
	            <label for="enterpriseName" >已调查企业id ：</label>
	            <input type="text" id="alreadyEnterpriseId" name="alreadyEnterpriseId" placeholder="已调查企业id" />
	        </div>
			<div class="form-line">
	            <label for="enterpriseName" >企业名称 ：</label>
	            <input type="text" id="enterpriseName" name="enterpriseName" placeholder="企业名称" required/>
	        </div>
			<div class="form-line">
	            <label for="contactPerson" >联系人 ：</label>
	            <input type="text" id="contactPerson" name="contactPerson" placeholder="联系人" required/>
	        </div>
			<div class="form-line">
	            <label for="contactPhone" >联系电话 ：</label>
	         <input type="text" id="contactPhone" name="contactPhone" placeholder="联系电话" required/>
	            	           
	        </div>
			<div class="form-line">
	            <label for="countyName" >区县 ：</label>
	            <input type="text" id="countyName" name="countyName" placeholder="区县" required/>
	        </div>
			<!-- <div class="form-line">
	            <label for="executor" >执行人 ：</label>
	            <input type="text" id="executor" name="executor" placeholder="执行人" required/>
	        </div> -->
			<div class="form-line">
	            <label for="detailAddress" >详细地址 ：</label>
	            <input type="text" id="detailAddress" name="detailAddress" placeholder="详细地址" required/>
	        </div>
			<div class="form-line">
	            <label for="detailAddress" >组织机构代码 ：</label>
	            <input type="text" id="code" name="code" placeholder="组织机构代码" required/>
	        </div>
			<div class="form-line">
	            <label for="detailAddress" >法人 ：</label>
	            <input type="text" id="corporation" name="corporation" placeholder="法人" required/>
	        </div>
			<!-- <div class="form-line">
	            <label for="status" >任务状态 ：</label>
	            <div id="status" class="selectF" data-code="【任务管理】状态" ></div>
	        </div> -->
	        <input type="hidden" id="status" name="status" value="new"/>
			<div class="form-line">
	            <label for="projectId" >项目 ：</label>
	            <div id="project1" class="selectF" data-code="全局【项目】" ></div>
	        </div>
    </div>

</form>
</body>
</html>