<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加区域</title>
</head>

<script type="text/javascript">
	require([ "validate","select", "ajaxform" ], function(a) {
		$("#inputForm #source").select({filter:true,name:"source",zIndex:3,change:function(event, value){
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
					url : 'save.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('添加成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('添加失败', {
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
			<div class="form-line">
				<label for="name">区域名称 ：</label> <input type="text" id="name"
					name="name" placeholder="区域名称" />

			</div>
			<div class="form-line">
				<label for="level">区域等级 ：</label> <input type="number" class="areaNumj"
					id="level" name="level" placeholder="区域等级" maxlength="1"/>

			</div>
			<div class="form-line">
				<label for="code">地域编码：</label> <input type="number" id="code" class="areaNumj"
					name="code" placeholder="地域编码" />

			</div>
			<div class="form-line">
				<label for="parent">上级区域编码 ：</label> <input type="number" class="areaNumj"
					id="parent" name="parent" placeholder="上级区域编码" />
			</div>
			<div class="form-line">
				<label for="parent">数据来源 ：</label> 
				<div data-code="区域【数据来源】" id="source" name="source" class=" selectF"></div>
			</div>
			<div id = "scopeDiv">
			 <div class="form-line">
				<label for="parent">经度最大范围 ：</label> <input type="number" class="areaNumj"
					id="longitudeMax" name="longitudeMax" placeholder="经度最大范围" />
			</div>
			<div class="form-line">
				<label for="parent">经度最小范围 ：</label> <input type="number" class="areaNumj"
					id="longitudeMin" name="longitudeMin" placeholder="经度最小范围" />
			</div>
			<div class="form-line">
				<label for="parent">纬度最大范围 ：</label> <input type="number" class="areaNumj"
					id="latitudeMax" name="latitudeMax" placeholder="纬度最大范围" />
			</div>
			<div class="form-line">
				<label for="parent">纬度最小范围 ：</label> <input type="number" class="areaNumj"
					id="latitudeMin" name="latitudeMin" placeholder="纬度最小范围" />
			</div> 
			</div> 
		</div>
	</form>
</body>
</html>