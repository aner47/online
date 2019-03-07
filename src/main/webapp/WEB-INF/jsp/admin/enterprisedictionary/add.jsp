<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加名录库</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
	var project1Select = $("#projectId","#inputForm").select({name:"projectId",zIndex:1}); 
    $("#status","#inputForm").select({name:"status",zIndex:2});   
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
				enterpriseName:{
					required:true
				},
				projectId:{
					required:true
				}
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
	            <label for="projectId" >项目 ：</label>
	            <div id="projectId" class="selectF" data-code="全局【项目】" ></div>
	        </div>
			<div class="form-line">
	            <label for="enterpriseName" >已录入企业id ：</label>
	            <input type="text" id="alreadyEnterpriseId" name="alreadyEnterpriseId" placeholder="已录入企业id"/>
	        </div>
			<div class="form-line">
	            <label for="enterpriseName" >标记项：</label>
	            <input type="text" id="sign" name="sign" />
	        </div>
			<div class="form-line">
	            <label for="enterpriseName" >企业名称 ：</label>
	            <input type="text" id="enterpriseName" name="enterpriseName" placeholder="企业名称"/>
	        </div>
			
			<div class="form-line">
	            <label for="countyName" >区县 ：</label>
	            <input type="text" id="countyName" name="countyName" placeholder="区县"/>
	        </div>
			
			<div class="form-line">
	            <label for="detailAddress" >详细地址 ：</label>
	            <input type="text" id="detailAddress" name="detailAddress" placeholder="详细地址"/>
	        </div>
			<div class="form-line">
	            <label for="inputIndustry" >行业 ：</label>
	            <input type="text" id="inputIndustry" name="inputIndustry" placeholder="行业"/>
	        </div>
			<div class="form-line">
	            <label for="code" >组织机构代码：</label>
	            <input type="text" id="code" name="code" placeholder="组织机构代码"/>
	        </div>
			<div class="form-line">
	            <label for="contacts" >联系人 ：</label>
	            <input type="text" id="contacts" name="contacts" placeholder="联系人"/>
	        </div>
			<div class="form-line">
	            <label for="contacts" >联系电话 ：</label>
	            <input type="text" id="contactsPhone" name="contactsPhone" placeholder="联系电话"/>
	        </div>
			<div class="form-line">
	            <label for="corporation" >法人：</label>
	            <input type="text" id="corporation" name="corporation" placeholder="法人"/>
	        </div>
			
			
    </div>

</form>
</body>
</html>