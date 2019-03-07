<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>审核企业</title>
</head>

<script type="text/javascript">
	require(["grid", "validate", "ajaxform" ], function(a) {
		var project1Select = $("#projectId","#inputForm").select({name:"projectId"
			,defaultValue:"${enterpriseDictionary.projectId}",zIndex:1}); 
		/* $('#commit').click(function() {
			$("#inputForm").submit();
		}) */
		
		//提交表单
		$("#inputForm").validate({
			rules : {
				enterpriseName:{
					required:true
				},
				projectId:{
					required:true
				}
				
			},
			messages : {},
			submitHandler : function(form) {
				/* var options = {
					url: '../admin/enterprise/updatestatus.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('审核成功！', {
							icon : 6
						})
						layer.closeAll('page');
						
						window.location.href = "../enterprise/enterpriseTaskList.jhtml";
						top.loadMenu('188');
					},
					error : function() {
						layer.msg('审核失败', {
							icon : 3
						});
					}
				};
				$(form).ajaxSubmit(options);
				return false; */
			}
		});
		
		$(document).ready(function() {
			$('.layui-layer.layui-layer-page', top.document).css({
				"width": "270px",
				"height": "66px",
			})
			$(".layui-layer-close", top.document).css({
				"display": "none"
			})
			$(".js-textar",  top.document).css({
				"display": "none"
			})
			$(".layui-layer-btn.layui-layer-btn-",  top.document).css({
				"display": "none"
			})
		})
		 
		// 审核框位置事件
		$('.layui-layer.layui-layer-page', top.document).on("mouseenter", function() {
			$(this).css({
				"width": "350px",
				"height": "300px"
			})
			$(".layui-layer-close", top.document).css({
				"display": "block"
			})
			$(".js-textar",  top.document).css({
				"display": "block"
			})
			$(".layui-layer-btn.layui-layer-btn-",  top.document).css({
				"display": "block"
			})
		}).on("mouseleave", function() {
			$(this).css({
				"width": "270px",
				"height": "66px",
			})
			$(".layui-layer-close", top.document).css({
				"display": "none"
			})
			$(".js-textar",  top.document).css({
				"display": "none"
			})
			$(".layui-layer-btn.layui-layer-btn-",  top.document).css({
				"display": "none"
			})
		})
		var parentTexaE = $(window.parent.document).find(".layui-layer-title");
		var parentBtnPass = $(window.parent.document).find(".layui-layer-btn0");
		var parentBtnLE = $(window.parent.document).find(".layui-layer-btn1");
		
		parentBtnLE.addClass("des");
		parentBtnPass.removeClass("des");
		
		var texaVal = $("#inputForm").find("#opinion");
		texaVal.on("keyup", function() {
			if ( texaVal.val() == "") {
				/* console.log("文本框是空的") */
				parentBtnLE.addClass("des");
				parentBtnPass.removeClass("des");
			} else {
				/* console.log("文本框不是空的") */
				parentBtnLE.removeClass("des");
				parentBtnPass.addClass("des");
			}
		})
		
		
	});
</script>
<style>

.padding_container {
	padding-left: 16px !important;
}
.layui-layer-page .layui-layer-content form {
	padding: 0 !important;
}
.layui-layer-shade{
	/* width: 0;
	height: 0; */
	z-index: -1 !important;
}
.des {
	background: #A6BBCE!important;
	box-shadow: 0 2px 2px #d6d6d6!important;
	border: 1px solid #d0d0d0!important;
	color: #fff!important;
}
</style>
<body>

	<form id="inputForm">
		<div class="container padding_container js-handleShow">
			<div class="form-line">
	            <textarea style="width: 300px ;height: 200px" id="opinion" name="opinion"></textarea>
				<input type="hidden" id="enterprieId" name="enterpriseId" value="${enterprieId}" />
				<input type="hidden" id="status" name="status" />
				
	        </div>
	        
		</div>
	</form>
</body>
</html>