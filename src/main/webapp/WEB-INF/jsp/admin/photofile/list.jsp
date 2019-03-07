<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生产信息</title>
<style>
.block .imgsee.active img {
    max-width: 90%;
    max-height: 90%;
}
</style>
<script type="text/javascript">
	require(["grid","upload","select","panel","ajaxform"],function(g,upload){
		var degAdd = 1;
		// 渲染已上传图片
		render=function(str){
			qy.ajax({
				url:"query.jhtml",
				data:{
					pageSize:100,
				},
				callBack:function(data){
					$('.block').attr('hasImg','false');
					$('#editBtn').addClass('disabled');
					$('.imgBlock').html('');
					var loadimg = data.content.length;
					if (loadimg) {
						$('.block').attr('hasImg','true');
						$('#editBtn').removeClass('disabled');
						var load = layer.load(2);
					};
					$(data.content).each(function(i,v){
						$('.imgBlock').prepend('<li class="imgF"><div class="imgCon" data-id="'+v.id+'"><div class="img"><img src="'+v.url+'"></div><div class="imgName" title="'+v.filename+'">'+v.filename+'</div></li>');
					})
					$('.imgBlock img').each(function(i,v){
						$(this).on('load error',function(){
							loadimg--;
							console.log(loadimg);
							if (!loadimg) {
								layer.close(load);
							};
						})
						v.width >= v.height
						? 
						$(v).addClass('allH').removeClass('allW')
						: 
						$(v).addClass('allW').removeClass('allH');
					})
					if(str){
						qy.suc3({title:str});
					}
				}
			})
		}
		render();
		// 上传
		upload.upload();
		$('.title').on('click','#addBtn',function(){
			if ($(this).hasClass('disabled')) {
				return;
			};
			function acceptCallBack(file,data) {
				console.dir(file);
				console.dir(data);
				qy.ajax({
					url :base+"/web/photofile/save.jhtml",
					data:{
						url:data.url,
						filename:file.file.name,
					},
					callBack:function(data2,code){
						if(code == "999999"){
							render('上传图片成功！');
						}else{
							qy.fail2({title:'上传文件失败！'});
						}
						if(data2.code =="10000"){
							qy.fail2({title:data2.content});
						}
					}

				})
			}
			upload.setAcceptCallBack(acceptCallBack);
			var $handle = $("#_defualt_upload_handle").find('input.webuploader-element-invisible');//.attr("accept","image/*")
			$handle.click();
		})
		// 放大查看
		
		$('.imgBlock').on('click','img',function(){
			$('.imgsee').addClass('active');
			$('.imgsee img').attr('src',$(this).attr('src'));
			$(".jsCssCloseImg").css({
				"display": "block"
			})
		})
		$('.imgsee').on('click',function(){
			var _deg = 90 * degAdd;
			console.log("执行了")
		    $(this).find("img").css({
		      "transform": "rotate("+ _deg +"deg)",
		      "top": "200px",
		      "right": "0px"
		    })
		    degAdd++;
		})
		// 关闭图片
		$(".jsCloseImg").on("click", function() {
			$('.imgsee').removeClass('active');
			$(this).css({
				"display": "none"
			})
		})
		// 编辑
		$('.title').on('click','#editBtn',function(){
			if ($(this).hasClass('disabled')) {
				return;
			};
			$('.imgBlock').toggleClass('edit');
			if ($('.imgBlock').hasClass('edit')) {
				$('#addBtn').addClass('disabled');
				$('#delBtn').addClass('disabled');
				$('#changeBtn').addClass('disabled');
			}else{
				$('#addBtn').removeClass('disabled');
				$('#delBtn').addClass('disabled');
				$('#changeBtn').addClass('disabled');
				$('.imgCon').removeClass('active');
			};
		});
		$('.imgBlock').on('click','.imgCon',function(){
			if (!$('.imgBlock').hasClass('edit')) {
				return;
			};
			$(this).toggleClass('active');
			if($('.imgCon.active').length){
				$('#delBtn').removeClass('disabled');
				if($('.imgCon.active').length==1){
					$('#changeBtn').removeClass('disabled');
				}else{
					$('#changeBtn').addClass('disabled');
				}
			}else{
				$('#delBtn').addClass('disabled');
				$('#changeBtn').addClass('disabled');
			}
		})
		// 删除
		$('.title').on('click','#delBtn',function(){
			if ($(this).hasClass('disabled')) {
				return;
			};
			qy.sure({
				title:'是否确定删除',
				yes:function(index){
					var ids=[];
					$('.imgCon.active').each(function(i,v){
						ids.push($(v).data('id'));
					})
					qy.ajax({
						url :base+"/web/photofile/delete.jhtml",
						data:{ids:ids.join(',')},
						callBack:function(data,errcode){
							if(errcode!=="000000"){
								render('删除图片成功！');
							}else{
								qy.fail2({title:'删除图片失败！'});
							}
						}
					})
					$('#delBtn').addClass('disabled');
					$('#addBtn').removeClass('disabled');
					$('#changeBtn').addClass('disabled');
					$('.imgBlock').removeClass('edit');
					layer.close(index);
				}
			})
		})
		// 修改名称
		$('.title').on('click','#changeBtn',function(){
			if ($(this).hasClass('disabled')) {
				return;
			};
			var opts = {
				title:"修改图片名称",
				width: "567px",
				height: "200px",
				btn:['保存','取消'],
				url : "updatePage.jhtml",
				yes : function(index){
					$("#photo_form").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		})
		$("#fixed_return").on("click", function() {
				window.location.href = "../enterprise/enterpriseTaskList.jhtml";
				top.loadMenu('188');
				top.layer.closeAll('page');
			})
			$("#back").on("click", function() {
				window.location.href = "../enterprise/list.jhtml";
				top.loadMenu('2','31');
			})
	})
</script>
</head>
<body>
<style>
body {
	positon: static;
}
.layui-layer-content.layui-layer-loading2::after {
	/*content: "\6B63\5728\52A0\8F7D\56FE\7247...";*/
	content: "";
}
.closeImg {
	display: none;
	width: 60px;
	height: 30px;
	background: #54C2EC;
	font-size: 18px;
	font-weight: 900;
	color: #fff;
	line-height: 30px;
	text-align: center;
	position: absolute;
	top: 0;
	right: 0;
	z-index: 99;
	cursor: pointer;
}
.imgSeeBig {
	width: 100%;
	height: 100%;
	overflow: scroll;
}
</style>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-zhaopian"></i>
			上传图片
			<c:if test="${principal.userType != 'system'}">
			<div id="back" class="titleBtn">返回</div>
			<div id="addBtn" class="titleBtn">上传</div>
			<div id="editBtn" class="titleBtn">编辑</div>
			<div id="delBtn" class="titleBtn disabled">删除</div>
			<div id="changeBtn" class="titleBtn disabled">修改</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
			</c:if>
		</h1>
		<span class="closeImg jsCloseImg jsCssCloseImg">关闭</span>
		<ul class="imgBlock">
			
		</ul>
		<div class="upImgBg">还没有上传照片噢~<br>右上角点击上传(压缩包仅支持上传zip噢~)</div>
		<div class="imgsee imgSeeBig">
		  <img />
		</div>
	</div>
</body>
</html>