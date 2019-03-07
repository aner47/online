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
<style type="text/css">
.form-row {
	border: 1px solid #ccc;
	box-shadow: 1px 1px 5px #ccc;
	min-height: 140px;
	background: :#F7FAFF;
}

.form-row>div {
	float: left;
	padding: 10px;
}
</style>
<script type="text/javascript">
require(["grid","select","panel","less","upload"],function(g,s,p,less,upload){
	
	
		var opts = {
				tab:"tab1",
				data:{pageSize:100,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"id",caption:"ID",hidden:true,type:"text"},
					{name:"groupName",caption:"分组名称",hidden:false,type:"text"},
				    {name:"projectId",caption:"所属项目",hidden:false,type:"text",valueSet:"全局【项目】"},
				    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;">查看</a>',	
						events :[{
							type:'click',
							select:'#view',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'id');
								location.href = "../enterprisedictionary/listrep.jhtml?repId="+id;
							}
						}] 
					},
				    
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
			
	})
</script>
<style>
	.repeatBlock{
		width: 100%;
		float: left;
		padding-bottom: 10px;
	}
	.docblock {
		width: 100%;
		overflow: hidden;
		float: left;
		position: relative;
		border: 1px solid #e6e6e6;
		margin-top: 10px;
	}
	.docblock .docblock-h1 {
		width: 100%;
		height: 44px;
		padding: 0 15px;
		line-height: 44px;
		font-size: 14px;
	}
	.docblock.docblock-hide .docblock-switch .iconfont {
		transform: rotate(90deg);
		-ms-transform: rotate(90deg);
		/* IE 9 */
		-webkit-transform: rotate(90deg);
		/* Safari and Chrome */
	}
	.docblock .docblock-switch {
		position: absolute;
		top: 14px;
		right: 10px;
		color: #c2c2c2;
	}
	.docblock .docblock-switch .iconfont {
		font-size: 14px;
	}
	.docblock .docblock-header,
	.docblock .docblock-body {
		width: 100%;
		float: left;
		color: #282828;
	}
	.docblock .docblock-header {
		min-height: 44px;
	}
	.docblock .docblock-body {
		overflow: hidden;
		-webkit-transition: height 1s ease;
		-moz-transition: height 1s ease;
		-ms-transition: height 1s ease;
		-o-transition: height 1s ease;
		transition: height 1s ease;
	}
	.docblock .docblock-body::before {
		content: '';
		width: 100%;
		display: block;
		border-top: 1px dashed #f6f6f6;
	}
</style>
</head>
<body>
	<div class="block    ">
		<h1 class="title">
			</i> 名录库信息
		</h1>
		
	<div id="tab1"></div>
	</div>
</body>
</html>