<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生产信息</title>
<script type="text/javascript">
	require(["grid","upload","select","panel","ajaxform"],function(g,upload){
		upload.upload();
		var title,element;
		$('.imgBlock').on('click','.imgF',function(){
			title=$('h2',this).text();
			element=$('.img',this);
			console.log(title,element);
			function acceptCallBack(file,data) {
				console.dir(file);
				console.dir(data);
				element.css('backgroundImage','url('+data.url+')');
				qy.ajax({
					url :"/web/photofile/save.jhtml",
					data:{
						// id:id,
						path:data.url,
						fileName:file.file.name,
					},
					callBack:function(data,code){
						if(code == "999999"){
							qy.suc({title:'上传文件成功！'});
						}else{
							qy.fail({title:'上传文件失败！'});
						}
					}

				})
			}
			upload.setAcceptCallBack(acceptCallBack);
			var $handle = $("#_defualt_upload_handle").find('input.webuploader-element-invisible')
			$handle.click();
		})
	})
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-zhaopian"></i>
			传照片
		</h1> 
		<ul class="imgBlock">
			<li class="imgF">
				<div class="img"></div>
				<h2>营业执照</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>环评报告</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>治理措施验收报告</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>煤质检验报告</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>进出货单</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>排放口</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>治理措施</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>锅炉</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>炉窑</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>产品与原辅料</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>露天堆场</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>储罐</h2>
			</li>
			<li class="imgF">
				<div class="img"></div>
				<h2>其他</h2>
			</li>
		</ul>
		<div class="buttonF">
			<a class="saveBtn">保存</a>
		</div>
	</div>
</body>
</html>