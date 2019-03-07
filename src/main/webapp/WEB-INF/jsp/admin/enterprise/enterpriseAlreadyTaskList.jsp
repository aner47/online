<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZwvKDkc8AosMeMixrVVDfEqK"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
	require(
			[ "grid", "select", "panel", "less" ,"date","commonBase"],
			function(g) {
				$("#putOnTime").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false,
		            zIndex:999999900
		        })
		        $("#stopTime").jeDate({
		            format:"YYYY-MM-DD",
		            isTime:false,
		            zIndex:999999900
		        })
		        //定义全局变量
		        var arr=[];
		        var index;
		        var k;
		        
		        
				//获取session-start
				var userCheckValue = sessionStorage.getItem("userCheckAlready");
				var json = JSON.parse(userCheckValue) || {};
				//获取session-end
				$('#delete').click(
						function(e) {
							//判断用户是否选择所要删除的企业信息，如没有则给出相应提示
							if (grid1.getSelectedValue('id').length == 0) {
								qy.sure({
									title : "请选择所要删除的企业信息"
								})
								return false;
							}
							//删除
							qy.sure({
								title : '是否删除所选'
										+ grid1.getSelectedValue('id').length
										+ '条企业信息?',
								yes : function(index) {
									layer.close(index);
									var ids = grid1.getSelectedValue('id');
									var opts = {
										url : 'delete.jhtml',
										data : {
											"ids" : ids.join(',')
										},
										callBack : function(data, errcode) {
											if (errcode !== "000000") {
												qy.suc2({
													title : '删除企业成功！'
												});
												grid1.loadData();
											} else {
												qy.fail2({
													title : '删除企业失败！'
												});
											}
										}
									}
									qy.ajax(opts);
								}
							});
						});
				$('#export').click(function(e) {//导出
					$("#exportA").href;
				});

				$("#add").click(function() {
					var opts = {
						title : "添加企业信息",
						url : "addPage.jhtml",
						width : "100%",
						height : "80%",
						yes : function() {
							$("#inputForm").submit();
						},
						cancel : function() {
						}
					}
					qy.panel(opts);
				});

				$("#editBtn").click(function() {
					var opts = {
						title : "修改企业信息",
						url : "updatePage.jhtml",
						width : "100%",
						height : "80%",
						data : {
							id : grid1.getSelectedValue("id")[0]
						},
						yes : function() {
							$("#inputForm").submit();
						},
						cancel : function() {
						}
					}
					qy.panel(opts);

				})
				/* 日期比较-开始  */
				function duibi(d1,d2)
				{
		  			return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
				}
				/* 日期比较-结束 */
				//查询
				$('#project_user_Search').click(function() {
					if(duibi($("#putOnTime").val(),$("#stopTime").val())){
						layer.msg('结束时间不能早于开始时间',{icon:3});
						return false;
					}
					//本地存储--start
					//定义json变量
					var userCheck = {
						"project" : project.getSelectValue(),//项目选择
						"project" : project.getSelectValue(),//项目选择
						"userSearch" : userSearch.getSelectValue(),//用户选择
						"categoryMain" : categoryMain.getSelectValue(),//行业类别（大类）
						"categoryMiddle" : categoryMiddle.getSelectValue(),//行业类别（中类）
						"provinces" : provinces.getSelectValue(),//省
						"city" : city.getSelectValue(),//市
						"county" : county.getSelectValue(),//县
						"enterpriseType" : enterpriseTypeQuery.getSelectValue(),
						
					//填表类型
					}
					console.log(userCheck.city)
					//json变量转换成json字符串
					var userCheckStr = JSON.stringify(userCheck);
					//存储json字符串
					sessionStorage.setItem("userCheckAlready", userCheckStr);
					//本地存储--end

					var data = {};
					var array = $('#queryForm').serializeArray();
					$.each(array, function(i, v) {
						data[v.name] = v.value;

					})
					grid1.loadData(data);
				});
				var projectparam = "";
				
				if('${role}' != '企业任务审核员'){
					//alert("企业任务审核员");
					projectparam = "assessor:"+'${userId}';
				}
				//项目选择
				var project = $("#project").select({
					name : "projectCode",
					defaultValue : json.project,
					zIndex : 8,
					param:projectparam,
					change : function(event, value) {
						userSearch.clear();
						userSearch.loadData("project.id:" + value+",userType:investigator");
					},
					initAfter: function(){
						userSearch.clear();
						if(json.project){
							userSearch.loadData("project.id:" + json.project+",userType:investigator");
						}
						
					}
				});

				//用户选择
				var userSearch = $("#userSearch").select({
					name : "account",
					//codeValue : "username",
					defaultValue : json.userSearch,
					//code : "id",
					zIndex : 7,
					initLoad:false,
					//url : "../systemuser/queryAll.jhtml",
					change : function(event, value) {

					}
				});
				//行业类别（大类）
				var categoryMain = $("#industryCategoryCodeMainQuery").select({
					filter : true,
					name : "industryCategoryCodeMain",
					defaultValue : json.categoryMain,
					zIndex : 3,
					param : "categoryLevel:MAIN",
					change : function(event, value) {
						var text = categoryMain.getSelectCaption();
						$("input[name=industryCategoryNameMain]").val(text);
						categoryMiddle.clear();
						categoryMiddle.loadData("parent:" + value)
					}
				});
				//行业类别（中类）
				var categoryMiddle = $("#industryCategoryCodeMiddleQuery")
						.select(
								{
									name : "industryCategoryCodeMiddle",
									defaultValue : json.categoryMiddle,
									zIndex : 2,
									initLoad:false,
									change : function(event, value) {
										var text = categoryMiddle
												.getSelectCaption();
										$(
												"input[name=industryCategoryNameMiddle]")
												.val(text);
									}
								});

				var provinces = $("#provincesQuery").select({
					name : "address.provinces.code",
					defaultValue : json.provinces,
					zIndex : 6,
					initAfter: function(){
						city.clear();
						if(json.provinces){
							console.log(json.provinces);
							city.loadData("parent:" + json.provinces);
						}
						
					},
					change : function(event, value) {
						city.clear();
						city.loadData("parent:" + value);
					}
				});
				var city = $("#cityQuery").select({
					name : "address.city.code",
					defaultValue : json.city,
					initLoad : false,
					param : "parent:" + json.provinces,
					zIndex : 5,
					clearEvent : function() {
						county.clear();
					},
					change : function(event, value) {

						county.clear();
						county.loadData("parent:" + value);
					}
				});

				var county = $("#countyQuery").select({
					filter : true,
					defaultValue : json.county,
					name : "address.county.code",
					initLoad : false,
					param : "parent:" + json.city,
					zIndex : 4,
					clearEvent : function() {
					},
					change : function(event, value) {
					}
				});
				var enterpriseTypeQuery = $("#enterpriseType").select({
					filter : true,
					name : "enterpriseType",
					defaultValue : json.enterpriseType,
					zIndex : 3,
					change : function(event, value) {

					}
				});

				var opts = {
					tab : "tab1",
					data : {
						pageSize : 10,
						pageNumber : 1,
						account : json.userSearch,
						projectCode : json.project,
						name : "",
						enterpriseTask:'approve'
					},
					loadDataCallBack:function(data,pageNumber){
						top.enterpriseListPageNumber = pageNumber;
					},
					url : "query.jhtml",
					//checkbox:false,
					export_show_always : false,
					columns : [
							{
								name : "enterprise.address.provinces.name",
								caption : "行政区划",
								hidden : true,
								type : "text"
							},
							{
								name : "enterprise.address.city.name",
								caption : "行政区划",
								hidden : true,
								type : "text"
							},
							{
								name : "enterprise.address.county.name",
								caption : "行政区划",
								hidden : false,
								type : "text"
							},
							{
								name : "enterprise.name",
								caption : "名称",
								hidden : false,
								type : "text"
							},
							{
								name : "enterprise.createDate",
								caption : "填报时间",
								hidden : false,
								type : "date"
							},
							{
								name : "enterprise.industryCategoryNameMain",
								caption : "行业类别（大类）",
								hidden : false,
								type : "text"
							},
							{
								name : "enterprise.industryCategoryNameMiddle",
								caption : "行业类别（中类）",
								hidden : false,
								type : "text"
							},
							{
								name : "enterprise.code",
								caption : "组织编码",
								hidden : false,
								type : "text"
							},
							{
								name : "enterprise.contacts",
								caption : "联系人",
								hidden : false,
								type : "text"
							},
							{
								name : "enterprise.id",
								caption : "ID",
								hidden : true,
								type : "text"
							},
							{
								name : "enterprise.enterpriseType",
								caption : "填表类型",
								type : "text",
								valueSet : "企业【企业类型】"
							},
							{name:"enterprise.opinion",caption:"审核意见",type:"text"},
							{name:"taskStatus",caption:"状态",type:"text",format:function(value,rowValue,handle){
								if(value =='alreadySubmit'){
									var parent = handle.parent("tr");
									return "已提交";
								}else if(value =='noApprove'){
									return "未通过";	
								}else if(value =='approve'){
									var parent = handle.parent("tr");
									parent.find("#view").html("");
									return "已通过";
								}else{
									return "未提交";
								}
								
							}},
							{
								name : "enterprise.address.houseNumber",
								caption : "门牌号",
								hidden : true,
								type : "text"
							},
							{
								name : "enterprise.address.longitude",
								caption : "经度",
								hidden : true,
								type : "text"
							},
							{
								name : "enterprise.address.latitude",
								caption : "纬度 ",
								hidden : true,
								type : "text"
							},
							{name: "operate", caption: "查看", isHtml: true, html: '<a id="look"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
								events: [{
	               					type: 'click',
									select: '#look',
									handle: function() {
	                 	 				var id = grid1.getCurrentRowValue(this,'enterprise.id');
	                  					var projectTypeId =grid1.getCurrentRowValue(this,'projectType');
										var enterpriseType = grid1.getCurrentRowValue(this,'enterprise.enterpriseType');
										qy.ajax({url: base + "/admin/enterprise/cacheEnterpriseId.jhtml",
											data:{enterpriseId:id},
											callBack:function(){
												qy.ajax({
													url: base+"/admin/enterprise/findMenuByProjectType.jhtml",
													data:{projectTypeId:projectTypeId},
													callBack:function(data){
														if(data.code == 20000){
															console.log(enterpriseType);
															top.loadMenuByIndustry(top.enterpriseMenu[enterpriseType],objC(data,'content'));
														}else{
															top.loadMenu(top.enterpriseMenu[enterpriseType]);
														}
														
													}
												})
											}
										})
									}
								}]
							},
							{name:"operate",caption:"导出",isHtml:true,html:'<a id="export"  style="cursor: pointer;">导出pdf</a>',
								events :[{
								type:'click',
								select:'#export',
								handle:function(){
									var id =grid1.getCurrentRowValue(this,'enterprise.id');
									qy.ajax({
										url: "../enterprise/isExportpdf.jhtml",
										data:{enterpriseId:id},
										callBack:function(data){
											if(data.code == 20000){
												location.href="../enterprise/exportpdf.jhtml?enterpriseId="+id;
											}else{
												qy.fail2({title:data.content});
											}
											
										}
									})

								}
							}]  	
							}
							/* ,{name:"operate",caption:"预览",isHtml:true,html:'<a style="display: block; width: 100%; height:29px; line-height: 29px; cursor: pointer;" target:"_blank" id="preview">预览pdf</a>',
								events :[{
									type:'click',
									select:'#preview',
									handle:function(){
										var $this_ = this;
										var id =grid1.getCurrentRowValue(this,'enterprise.id');
										if($(this).attr("disabled")){
											layer.msg("请稍后...");
											return;
										}
										$(this).attr("disabled","disabled");
										setTimeout(function(handle){
											handle.removeAttr("disabled");
										},5000,$(this));
										qy.ajax({
											url: base+"/admin/enterprise/isExportpdf.jhtml",
											data:{enterpriseId:id},
											callBack:function(data){
												if(data && data.code != 20000){
													qy.fail2({title:data.content});
												} else {
													window.open("../enterprise/exportpdf.jhtml?enterpriseId="+id+"&type=preview");
												}
											}
										})
										
									}
								}]  	
								} */
							
							,{name: "operate", caption: "操作", isHtml: true, html: '<a id="back"  style="cursor: pointer;">直接退回</a>',
								events: [{
	               					type: 'click',
									select: '#back',
									handle: function() {
	                 	 				var id = grid1.getCurrentRowValue(this,'enterprise.id');
	                 	 				layer.msg('请确定直接退回，状态会改为未提交？', {
	                 	 				  time: 0 //不自动关闭
	                 	 				  ,btn: ['确定', '取消']
	                 	 				  ,area: ['280px', '150px']		//宽，高
	                 	 				  ,yes: function(index){
	                 	 					  qy.ajax({
	                 	 							url: "../enterprise/updatestatus.jhtml",
	                 	 							data:{enterpriseId:id,status:1},
	                 	 							callBack:function(data){
	                 	 								if(data.code == '20000'){
	                 	 									layer.close(index);
	                 	 									grid1.loadData({pageNumber:(top.enterpriseListPageNumber || 1)});
	                 	 								}else{
	                 	 									qy.fail2({title:data.content});
	                 	 								}
	                 	 							
	                 	 							}
	                 	 						})
	                 	 				  }
	                 	 				});
									}
								}]
							},
							
							
							
							]
				}
				grid1 = g.grid(opts);
				//grid1.loadData({pageNumber:(top.enterpriseListPageNumber || 1)});
				grid1.loadData();
				
			})
</script>
</head>
<style>
.showPDFWrap {
  display: none;
  position: fixed;
  left: 0; 
  right: 0; 
  top: 0; 
  margin: auto; 
  bottom: 0; 
  width: 300px;
  height: 160px;
  z-index: 9999999;
  background: rgba(0, 0, 0, .5);
}
.showPDFWrap h4 {
	height: 90px;
	text-align: center;
	line-height: 90px;
	font-size: 16px;
	font-weight: 900;
	color: #fff;
}
.showPDF {
	display: block;
	width: 40%;
	height: 50px;
	float: left;
	text-align: center;
	line-height: 50px;
	font-size: 16px;
	color: #fff;
	background: red;
}
.showPDF1 {
	margin-left: 5%;
	margin-right: 10%;
}
.showMy {
	display: block;
}
.layui-layer-shade {
	display: none;
	top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, .3);
    z-index: 999;
}
</style>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont">&#xe640;</i> 企业任务列表
			

		</h1>
		<form class="form" id="queryForm" action="#" onsubmit="return false">
			<div class="formLine">
				<div class="formPara">
					<label class="label">项目</label>
					<div id="project" class="projectdiv selectF" data-code="全局【项目】"></div>
				</div>
				<div class="formPara">
					<label class="label">用户</label>
					<div id="userSearch" class="userSearchdiv selectF" data-code="每日任务【人员】"></div>
				</div>
				
				<div class="formPara">
					<label class="label">企业名称</label> <input type="text" class="input"
						name="name">
				</div>
				<button id="project_user_Search" class="button">搜索</button>
			</div>
			<div class="formLine">
				<div class="formPara">
					<label class="label">行政区划</label>
					<div id="provincesQuery" data-code="地域" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">市</label>
					<div id="cityQuery" data-code="地域" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">县</label>
					<div id="countyQuery" data-code="地域" class="projectdiv selectF"></div>
				</div>
			</div>
			<div class="formLine">
				<div class="formPara">
					<label class="label">行业(大类)</label>
					<div data-code="行业" id="industryCategoryCodeMainQuery" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">行业(中类)</label>
					<div data-code="行业" id="industryCategoryCodeMiddleQuery"
						class="userSearchdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">填表类型</label>
					<div data-code="企业【企业类型】" id="enterpriseType"
						class="projectdiv selectF"></div>
				</div>

			</div>
			<div class="formLine">
				<div class="formPara">
					<label class="label">开始日期</label>
					<input type="text" id="putOnTime" name="putOnTime" class="input" placeholder="开始时间" style="width:160px;" readonly />
				</div>
				<div class="formPara">
					<label class="label">结束日期</label>
					<input type="text" id="stopTime" name="stopTime" class="input" placeholder="结束时间" style="width:160px;" readonly  />
				</div>
				

			</div>
		</form>
		<div class="layui-layer-shade js-handleMyShowBa"></div>
		<div class="showPDFWrap js-handleMyshow">
			<h4>确定打开页面吗</h4>
			<a href="javascript:;" class="showPDF showPDF1">
				确定
			</a>
		</div>
	
		<div id="tab1" class="tab" style="border-top: 1px solid #e7ebee;"></div>
	</div>
</body>
</html>