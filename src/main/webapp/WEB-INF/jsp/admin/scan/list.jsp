<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet/less" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/from.less" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
		qy.ajax({
			url:'isScaning.jhtml',
			data :{},
			callBack:function(data){
				if(data){
					$('#showtext').html('扫描中');
					layer.msg("扫描中", {icon: 6});
					
				}
			}
		});
	
		$('#scanRawMaterials').click(function(e){
			layer.confirm('确定扫描原辅料', {icon: 3, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var opts ={
					url:'scan.jhtml',
					data :{type:"rawMaterials"},
					callBack:function(data,errcode){
						if(data.code == "20000"){
							layer.msg(data.content, {icon: 6});
							$('#showtext').html('扫描中');
						}else if(data.code == "10000"){
							layer.msg(data.content, {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
		$('#scanProduct').click(function(e){
			layer.confirm('确定扫描产品', {icon: 3, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var opts ={
					url:'scan.jhtml',
					data :{type:"product"},
					callBack:function(data,errcode){
						if(data.code == "20000"){
							layer.msg(data.content, {icon: 6});
							$('#showtext').html('扫描中');
						}else if(data.code == "10000"){
							layer.msg(data.content, {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
	})
</script>
</head>
<body>
	<div>
	<button class="btn" id="scanRawMaterials">扫描原辅料</button>
	<button class="btn" id="scanProduct">扫描产品</button>
	</div>
	<div>
		<label for="" class="infpUnit" id="showtext"></label>
	</div>
</body>
</html>