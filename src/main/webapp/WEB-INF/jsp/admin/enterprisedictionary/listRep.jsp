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
	
	var projectSelect = $("#project").select({name:"project"})	
	var projectIdSelect = $("#projectId").select({name:"projectId"})	
	
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条名录库信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除名录库成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除名录库失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加名录库信息",
				url : "addPage.jhtml",
				data:"",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改名录库信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0]},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		$("#insert").click(function(){
			var ids = grid1.getSelectedValue('id');
			if(ids.length ==0){layer.msg("请选择企业");return};
			qy.ajax({
				url:"insert.jhtml",
				data:{"ids":ids.join(',')},
				callBack:function(data){
					if(data.code == '20000'){
						layer.msg('生成任务成功！');
						grid1.refresh();
					}else{
						layer.msg(data.content, {icon: 5});
					}
					
				}
			})
			
			
		});
/* 		$("#insert").click(function(){
			var ids = grid1.getSelectedValue('id');
			var opts = {
				title: "加入标准名录库",
				url : "insertPage.jhtml",
				
				data :{"ids":ids.join(',')},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		}); */
		$("#filter").click(function(){
			var projectId = projectIdSelect.getSelectValue();
			
			if(!projectId){layer.msg("请选择项目");return};
			
			var data1 = {};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data1[v.name] = v.value;	
				
			})
			qy.ajax({
				url:"filtrate.jhtml",
				data:data1,
				callBack:function(data){
					if(data.code == '20000'){
						layer.msg('筛选开始！');
					}else{
						layer.msg(data.content, {icon: 5});
					}
					/* if(data && data.length){
						// grid1.refresh();
						// 显示重复数据
						showDataOfRepeat(data);
					} */
					
				}
			})
			
		});
		$("#filterResult").click(function(){
			
			location.href = base+"/admin/enterprisedictionaryrep/list.jhtml";
			
		});
		$("#export").click(function(){
			var param ="";
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				param += v.name+"="+v.value+"&";
			}) 
			param = param.substring(0,param.lastIndexOf("&"));
			window.location.href=base+"/admin/enterprisedictionary/exportEnterpriseDictionary.jhtml?"+param;
		});
		
		$('#queryForm').submit(function(){
			return false;
		})
			//查询
		$('#query').click(function(){
			var data = {};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data[v.name] = v.value;	
				
			})
			grid1.loadData(data);
		});
			
		$("#status","#queryForm").select({name:"status"})	
		var opts = {
				tab:"tab1",
				data:{pageSize:100,pageNumber:0,repId:'${repId}'},
				url:"query.jhtml",
				columns :[
					{name:"id",caption:"ID",hidden:false,type:"text"},
					{name:"alreadyEnterpriseId",caption:"已录入企业id",hidden:false,type:"text"},
				    {name:"enterpriseName",caption:"企业名称",hidden:false,type:"text"},
				    {name:"countyName",caption:"区县",hidden:false,type:"text"},
				    {name:"detailAddress",caption:"详细地址",hidden:false,type:"text"},
				    {name:"inputIndustry",caption:"行业",hidden:false,type:"text"},
				    {name:"code",caption:"组织机构代码",hidden:false,type:"text"},
				    {name:"contacts",caption:"联系人",hidden:false,type:"text"},
				    {name:"contactsPhone",caption:"联系电话",hidden:false,type:"text"},
				    {name:"corporation",caption:"法人",hidden:false,type:"text"},
				    {name:"projectId",caption:"所属项目",hidden:false,type:"text",valueSet:"全局【项目】"},
				    {name:"source",caption:"提交人",hidden:false,type:"text"},
				    {name:"status",caption:"状态",hidden:false,type:"text",valueSet:"名录库【状态】",
				    	format:function(value,rowValue,handle){
							if(value !='checkpending'){
								var parent = handle.parent("tr");
								parent.find("#view").html("");
							}
							return value;
				    	}
				    },
				    /* {name:"opinion",caption:"审核意见",hidden:false,type:"text"},
				    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;">审核</a>',	
						events :[{
							type:'click',
							select:'#view',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'id');
								var projectId =grid1.getCurrentRowValue(this,'projectId');
								if(!projectId){
									layer.msg('请先分配项目！');
									return;
								}
								var opts = {
										title: "审核",
										url : "checkPage.jhtml",
										data:{id:id},
										btn: ['通过', '不通过'],
										yes : function(){
											$("#inputForm #status").val("verified");
											$("#inputForm").submit();
										},
										btn2:function(){
											$("#inputForm #status").val("checknot");
											$("#inputForm").submit();
										},
										cancel:function(){}
									}
									qy.panel(opts);
		
							}
						}] 
					}, */
				    
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
			upload.upload({element:"#fileHandle",acceptCallBack:function(handle,data){
				$("#fileName").html(handle.file.name);
				$("#filePath").val(data.realPath);
				console.log(data.url);
				}
			});
			$("#importTask").click(function(){
				var filePath = $("#filePath").val();
				var projectId = projectSelect.getSelectValue();
				if(!projectId){layer.msg("请选择项目");return};
				if(!filePath){layer.msg("请选择文件");return};
				
				qy.ajax({
					url:"export.jhtml",
					data:{filePath:filePath,projectId:projectId},
					callBack:function(data){
						if(data.code == '20000'){
							layer.msg('导入成功！');
							grid1.refresh();
						}
						
					}
				})
				
			})

			// 
			var showI = 1;

			// 显示重复数据
			function showDataOfRepeat(dataOfRepeat){
				dataOfRepeat = dataOfRepeat || [];
				showI = 1;
				$('.repeatBlock').html('');
				for (var i = 0; i < dataOfRepeat.length; i++) {
					if (dataOfRepeat[i] && dataOfRepeat[i].length) {
						DataOfRepeatShow(dataOfRepeat[i]);
					};
				};
			}

			// 添加显示单组数据
			function DataOfRepeatShow(data){
				var isOk,gridTimp;
				do{
					showI++;
					isOk = $('#add'+showI)[0] && $('#update'+showI)[0] && $('#delete'+showI)[0] && $('#tab'+showI)[0];
				}while(isOk)
				$('.repeatBlock').append('<div class="docblock">'+
					'<div class="docblock-switch">'+
						'<div class="iconfont icon-dansanjiaodown"></div>'+
					'</div>'+
					'<div class="docblock-header">'+
						'<h1 class="docblock-h1">重复组'+(showI-1)+'</h1>'+
					'</div>'+
					'<div class="docblock-body">'+
						'<div class="grid-tools">'+
							'<button id="update'+showI+'" class="btn-warning" data-grid="tab'+showI+'"data-enable="1">修改</button>'+
							'<button id="delete'+showI+'" class="btn-denger" data-grid="tab'+showI+'"data-enable="2">删除</button>'+
							'</div>'+
							'<div id="tab'+showI+'"></div>'+
						'</div>'+
					'</div>');
				// 添加事件

				// 删除
				$('#delete'+showI).click(function(e){
					layer.confirm('是否删除所选'+gridTimp.getSelectedValue('id').length+'条名录库信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
						layer.close(index);
						var ids = gridTimp.getSelectedValue('id');
						var opts ={
							url:'delete.jhtml',
							data :{"ids":ids.join(',')},
							callBack:function(data,errcode){
								if(errcode!=="000000"){
									layer.msg('删除名录库成功！', {icon: 6});
									grid1.refresh();
									if ($('.repeatBlock').html()) {
										$("#filter").click();
									};
								}else{
									layer.msg('删除名录库失败！', {icon: 5});
								}
							}
						}
						qy.ajax(opts);
					});
				});

				// 修改
				$("#update"+showI).click(function(){
					var opts = {
						title: "修改名录库信息",
						url : "updatePage.jhtml",
						data:{id:gridTimp.getSelectedValue("id")[0]},
						yes : function(){
							$("#inputForm").submit();
						},
						cancel:function(){}
					}
					qy.panel(opts);
				});

				// 表格
				var opts = {
					tab:'tab'+showI,
					data:{pageSize:10,pageNumber:0},
					columns :[
					{name:"id",caption:"ID",hidden:false,type:"text"},
					{name:"alreadyEnterpriseId",caption:"已调查企业id",hidden:false,type:"text"},
					{name:"enterpriseName",caption:"企业名称",hidden:false,type:"text"},
					{name:"countyName",caption:"区县",hidden:false,type:"text"},
					{name:"detailAddress",caption:"详细地址",hidden:false,type:"text"},
					{name:"inputIndustry",caption:"行业",hidden:false,type:"text"},
					{name:"code",caption:"组织机构代码",hidden:false,type:"text"},
					{name:"contacts",caption:"联系人",hidden:false,type:"text"},
					{name:"contactsPhone",caption:"联系电话",hidden:false,type:"text"},
					{name:"corporation",caption:"法人",hidden:false,type:"text"},
					{name:"projectId",caption:"所属项目",hidden:false,type:"text",valueSet:"全局【项目】"},
					{name:"source",caption:"提交人",hidden:false,type:"text"},
					{name:"status",caption:"状态",hidden:false,type:"text",valueSet:"名录库【状态】"},
					{name:"opinion",caption:"审核意见",hidden:false,type:"text"}		    	
					],
					simpleData:{pageSize:10,content:data}
				}

				gridTimp = g.grid(opts);
				gridTimp.loadSimpleData();
			}

			// doc(可收缩)
			$('.repeatBlock').on('click','.docblock-switch',function(){
				var docblock = $(this).parent();
				docblock.toggleClass('docblock-hide');
				var docBody = docblock.find('.docblock-body');
				var height;
				if (!docBody.data('height')) {
					// 获取 docBody 的高度
					height = docBody.height();
					docBody.data('height',height).css('height',height);
				};
				height = docBody.data('height');
				setTimeout(function(){
					if (docblock.hasClass('docblock-hide')) {
						docBody.css('height',0);
					}else{
						docBody.css('height',height);
					};
				},10);
			})
		
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
		 
    </div>
	</div>
	<div class="grid-tools">
		
		<button id="update" class="btn-warning" data-grid="tab1"
			data-enable="1">修改</button>
		<button id="delete" class="btn-denger" data-grid="tab1"
			data-enable="2">删除</button>
		
	</div>
	<div id="tab1"></div>
	<div class="buttonF">
		<div class="align-right">
			<%-- <a class="btn" id="back" href="../projecttemplates/${source}.jhtml">返回</a> --%>
			<a class="btn" id="back" href="javascript:history.back(-1)">返回</a>
		</div>
	</div>
</body>
</html>