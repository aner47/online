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
	require(["select","panel","ajaxform"],function(){
		var title,element,files,fileValue;
		$('.imgBlock').on('click','.imgF',function(){
			title=$('h2',this).text();
			element=$(this).index();
		})
		$('.imgBlock').on('change','#imgFile',function(){
			files=this.files;
			fileValue=this.value;
			var opts = {
				title:"添加"+title,
				width:"646px",
				height:"402px",
				btn:['保存','','保存并继续'],
				data:[files:files,fileValue:fileValue],
				url : "addPage.jhtml",
				yes : function(index){
					imgSubmit(index);
				},
				btn3:function(index){
					imgSubmit(index);
					$('#imgFile').click();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		// 提交图片
		function imgSubmit(index){
			// 获取修改名称和备注

			// 提交图片
			
			// 关闭弹窗
			layer.close(index);
		}
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
			<input type="file" id="imgFile" accept="image/*" multiple="multiple">
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>营业执照</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>环评报告</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>治理措施验收报告</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>煤质检验报告</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>进出货单</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>排放口</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>治理措施</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>锅炉</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>炉窑</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>产品与原辅料</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>露天堆场</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>储罐</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
			<li class="imgF">
				<label for="imgFile" class="img"></label>
				<h2>其他</h2>
				<textarea name="" id="" placeholder="备注"></textarea>
			</li>
		</ul>
		<div class="buttonF">
			<a class="saveBtn">保存</a>
		</div>
	</div>
</body>
</html>