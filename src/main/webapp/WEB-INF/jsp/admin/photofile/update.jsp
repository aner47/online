<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css" > -->
<meta charset="UTF-8">
<title>修改图片名称</title>
</head>

<script type="text/javascript">
	require([ "validate", "ajaxform", "select" ], function(a) {
		$('#photoName').val($('.imgCon.active .imgName').text());
		//提交表单
		$("#photo_form").validate(
		{
			rules : {
			},
			messages : {
			},
			submitHandler : function(form) {
				$('#changeBtn').addClass('disabled');
				$('#delBtn').addClass('disabled');
				$('#addBtn').removeClass('disabled');
				$('.imgBlock').removeClass('edit');
				qy.ajax({
					url :base+"/web/photofile/update.jhtml",
					data:{filename:$('#photoName').val(),id:$('.imgCon.active').data('id')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							render('修改名称成功！');
						}else{
							qy.fail2({title:'修改名称失败！'});
						}
					}
				})
				layer.closeAll();
				return false;
			}
		});
	});
</script>
<style>
	form#modifyinput *{
		box-sizing: content-box;
	}
	label.error {
		position: absolute;
		right: 10px;
		top:0px;
	}
	.dialog_div {
		width: 527px;
		height: auto;
	}
	.content {
		width: auto;
		height: 100%;
		border: 0;
	}
</style>
<body>
	<form id="photo_form">
		<div class="dialog_div">
		<div class="dialog_title_left"><span>*</span>图片名称</div>
			<div class="dialog_title_right">
				<input class="dialog_pswinput " type="text" id="photoName"  autocomplete="off" name="photoName" placeholder="" required>
			</div>
		</div>
	</form>
</body>
</html>