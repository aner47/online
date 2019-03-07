<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加菜单</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		
		enterpriseMenu={"SIMPLE":47,"NORMAL":19,"SINGLE_BOILER":54,"CONSTRUCTION_SITE":71,
			"PETROL_STATION":77,"DRY_CLEAR":81,"BREAKDOWN_SERVICE":84,"BEASTS_BIRDS":88,"CATERING":120,"GENERAL":141};
		enterpriseMenuEnd={"SIMPLE":61,"NORMAL":60,"SINGLE_BOILER":99,"CONSTRUCTION_SITE":102,
				"PETROL_STATION":108,"DRY_CLEAR":112,"BREAKDOWN_SERVICE":115,"BEASTS_BIRDS":118,"CATERING":123,"GENERAL":171};
		
		var enterpriseType = $("#enterpriseType","#inputForm").select({filter:true,name:"enterpriseType",zIndex:9,change:function(event,value){
			systemMenuFrontId.loadData("pid:" + enterpriseMenu[value]);
			systemMenuEndId.loadData("pid:" + enterpriseMenuEnd[value]);
		}});
		
		var systemMenuFrontId = $("#systemMenuFrontId","#inputForm").select({filter:true,name:"systemMenuFrontId",zIndex:8});
		var systemMenuEndId = $("#systemMenuEndId","#inputForm").select({filter:true,name:"systemMenuEndId",zIndex:7});
		
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
						grid1.refresh();
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
	            <label for="projectId" >填表类型 ：</label>
	            <div id="enterpriseType" class="selectF" data-code="企业【企业类型】" ></div>
	        </div>
        
			<div class="form-line">
				<label for="name">前台菜单名称 </label> 
				<div id="systemMenuFrontId" data-code="菜单管理" class="selectF"></div>

			</div>
			<div class="form-line">
				<label for="url">后台菜单名称  </label> 
				<div id="systemMenuEndId" data-code="菜单管理" class="selectF"></div>

			</div>
			

		</div>

	</form>
</body>
</html>