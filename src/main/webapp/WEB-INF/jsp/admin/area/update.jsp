<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改区域</title>
</head>

<script type="text/javascript">
require([ "validate","select", "ajaxform" ], function(a) {
	$("#inputForm #source").select({filter:true,name:"source",defaultValue:"${area.source }",zIndex:3,change:function(event, value){
		if(value == 1){
			$('#scopeDiv').hide();
		}else{
			$('#scopeDiv').show();
		}
	},initAfter:function(data,value,other){
		if(value == 1){
			$('#scopeDiv').hide();
		}else{
			$('#scopeDiv').show();
		}
	}});
	
	$('#commit').click(function() {
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules : {
				name:{
					required:true
				},
				level:{
					required:true
				},
				code:{
					required:true
				},
				parent:{
					required:true
				},
				source:{
					required:true
				},
			},
			messages : {},
			submitHandler : function(form) {
				var options = {
					url : 'update.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('修改成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.refresh();
					},
					error : function() {
						layer.msg('修改失败', {
							icon : 3
						});
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
			<input type="hidden" name="id" value="${area.id }"/>
			<div class="form-line">
				<label for="name">区域名称 ：</label> <input type="text" id="name"
					name="name" placeholder="区域名称" value="${area.name }"/>
			</div>
			<div class="form-line">
				<label for="level">区域等级 ：</label> <input type="number" class="areaNumj"
					id="level" name="level" placeholder="区域等级" maxlength="1" value="${area.level }"/>
			</div>
			<div class="form-line">
				<label for="code">地域编码：</label> <input type="number" id="code" class="areaNumj"
					name="code" placeholder="地域编码" value="${area.code }"/>
			</div>
			<div class="form-line">
				<label for="parent">上级区域编码 ：</label> <input type="number" class="areaNumj"
					id="parent" name="parent" placeholder="上级区域编码" value="${area.parent }"/>
			</div>
			<div class="form-line">
				<label for="parent">数据来源 ：</label> 
				<div data-code="区域【数据来源】" id="source" name="source" class=" selectF"></div>
			</div>
			<div id = "scopeDiv">
			 <div class="form-line">
				<label for="parent">经度最大范围 ：</label> <input type="number" class="areaNumj"
					id="longitudeMax" name="longitudeMax" placeholder="经度最大范围" value="${area.longitudeMax }"/>
			</div>
			<div class="form-line">
				<label for="parent">经度最小范围 ：</label> <input type="number" class="areaNumj"
					id="longitudeMin" name="longitudeMin" placeholder="经度最小范围" value="${area.longitudeMin }"/>
			</div>
			<div class="form-line">
				<label for="parent">纬度最大范围 ：</label> <input type="number" class="areaNumj"
					id="latitudeMax" name="latitudeMax" placeholder="纬度最大范围" value="${area.latitudeMax }"/>
			</div>
			<div class="form-line">
				<label for="parent">纬度最小范围 ：</label> <input type="number" class="areaNumj"
					id="latitudeMin" name="latitudeMin" placeholder="纬度最小范围" value="${area.latitudeMin }"/>
			</div> 
			</div> 
		</div>
	</form>
</body>
</html>