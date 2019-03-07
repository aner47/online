<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/resources/css/block.css" />
<script	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&amp;ak=C8ANgrUU7EZGAm0CSpZXaFgwTorXl97n"></script>
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&amp;ak=C8ANgrUU7EZGAm0CSpZXaFgwTorXl97n&amp;services=&amp;t=20180521160403"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>

</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont">&#xe640;</i> 企业任务列表
			<div id="editBtn" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div>
		</h1>
		<form class="form" id="queryForm" action="#" onsubmit="return false">
			<div class="formLine">
				<div class="formPara">
					<label class="label">项目</label>
					<div id="project" 
						class="projectdiv selectF" data-code="全局【项目】"></div>
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
				<c:if test="${role == '企业任务审核员'}">
					<div class="formPara">
						<label class="label"></label>
						<button id="allot" class="button">分配任务</button>
					</div>
				</c:if>
			</div>
			<div class="formLine">
				<div class="formPara">
					<label class="label">行业(大类)</label>
					<div data-code="行业" id="industryCategoryCodeMainQuery"
						data-code="projectCode" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">行业(中类)</label>
					<div data-code="行业" id="industryCategoryCodeMiddleQuery"
						class="userSearchdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">修改时间</label>
					<input type="text"  id="modifyDate" name="modifyDate" class="projectdiv selectF" />
				</div>
			</div>
			<div class="formLine">
				<div class="formPara">
					<label class="label">开始日期</label>
					<input class="input" id="putOnTime" name="putOnTime" type="text" 
							placeholder="开始时间" style="width:calc(100% - 90px);" readonly />
				</div>
				<div class="formPara">
					<label class="label">结束日期</label>
					<input class="input" id="stopTime" name="stopTime" type="text" 
							placeholder="结束时间" style="width:calc(100% - 90px);" readonly />
				</div>
				<div class="formPara">
					<label class="label">填表类型</label>
					<div data-code="企业【企业类型】" id="enterpriseType"
						class="projectdiv selectF"></div>
				</div>
			</div>
			
		</form>
		<div id="tab1" class="tab" style="border-top: 1px solid #e7ebee;margin-top: 20px"></div>
	</div>
	<script type="text/javascript">
	var mainVar = {};
	require(
			[ "grid","layer", "select", "panel", "less" ,"date","commonBase"],
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
		    	$("#modifyDate").jeDate({
					format:"YYYY-MM-DD",
					isTime:false,
					zIndex:999999900
		    	})
				//获取session-start
				var userCheckValue = sessionStorage.getItem("userCheck");
				var json = JSON.parse(userCheckValue) || {};
				/* 日期比较-开始  */
				function duibi (d1,d2) {
					var date_1 = new Date(d1.replace(/-/g,"\/"));
					var date_2 = new Date(d2.replace(/-/g,"\/"));
		  		return (date_1 > date_2);
				}
				//查询
				$('#project_user_Search').on("click", function() {
					var project_1 = project.getSelectValue();
					var modifyTime = $("#modifyTime").val();
					if( !project_1 ){
						layer.msg('请选择项目', {icon:3});
						return false;
					}
					if(duibi($("#putOnTime").val(), $("#stopTime").val())){
						layer.msg('结束时间不能早于开始时间', {icon:3});
						return false;
					}
					//本地存储--start
					//定义json变量
					var userCheck = {
						"project" : project.getSelectValue(),//项目选择
						"userSearch" : userSearch.getSelectValue(),//用户选择
						"categoryMain" : categoryMain.getSelectValue(),//行业类别（大类）
						"categoryMiddle" : categoryMiddle.getSelectValue(),//行业类别（中类）
						"provinces" : provinces.getSelectValue(),//省
						"city" : city.getSelectValue(),//市
						"county" : county.getSelectValue(),//县
						"enterpriseType" : enterpriseTypeQuery.getSelectValue(),
					}
					//json变量转换成json字符串
					var userCheckStr = JSON.stringify(userCheck);
					//存储json字符串
					sessionStorage.setItem("userCheck", userCheckStr);
					var data = {};
					var ay = $('#queryForm').serializeArray();
					$.each(ay, function(i, v) {
						data[v.name] = v.value;
					})
					grid1.loadData(data);
				});
				
				//分配任务
				$('#allot').on("click", function() {
					var ids = grid1.getSelectedValue("id");
					console.log(ids);
					if(ids.length == 0){
						var project_1 = project.getSelectValue();
						console.log(project_1);
						if( !project_1 ){
							layer.msg('请选择项目', {icon:3});
							return false;
						}
						if(duibi($("#putOnTime").val(), $("#stopTime").val())){
							layer.msg('结束时间不能早于开始时间', {icon:3});
							return false;
						}
						
						var data = {};
						var ay = $('#queryForm').serializeArray();
						$.each(ay, function(i, v) {
							data[v.name] = v.value;
						})
					}
					
					var opts = {
							title: "分配任务",
							url : "allotPage.jhtml",
							width:"30%",
							height:"562px",
							data:$.extend(data,data,{ids:ids.join(",")}),
							yes : function(){
								$("#inputForm").submit();
							},
							cancel:function(){}
						}
						qy.panel(opts);
					
					
				});
				var projectparam = "";
				if('${role}' != '企业任务审核员'){
					//alert("企业任务审核员");
					projectparam = "assessor:"+'${userId}';
				}
				
				//项目选择
				var project = $("#project").select({
					name: "projectCode",
					defaultValue: json.project,
					filter: true,
					param:projectparam,
					zIndex: 8,
					change: function(event, value) {
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
					name: "account",
					defaultValue: json.userSearch,
					zIndex: 7,
					initLoad: false
				});
				//行业类别（大类）
				var categoryMain = $("#industryCategoryCodeMainQuery").select({
					filter: true,
					name: "industryCategoryCodeMain",
					defaultValue: json.categoryMain,
					zIndex: 3,
					param: "categoryLevel:MAIN",
					change: function (event, value) {
						var text = categoryMain.getSelectCaption();
						$("input[name=industryCategoryNameMain]").val(text);
						categoryMiddle.clear();
						categoryMiddle.loadData("parent:" + value);
					}
				});
				//行业类别（中类）
				var categoryMiddle = $("#industryCategoryCodeMiddleQuery").select({
					name: "industryCategoryCodeMiddle",
					defaultValue: json.categoryMiddle,
					zIndex: 2,
					initLoad: false,
					change: function (event, value) {
						var text = categoryMiddle.getSelectCaption();
						$("input[name=industryCategoryNameMiddle]").val(text);
					}
				});

				var provinces = $("#provincesQuery").select({
					name: "address.provinces.code",
					defaultValue: json.provinces,
					zIndex: 6,
					filter: true,
					initAfter: function(){
						city.clear();
						if(json.provinces){
							console.log(json.provinces);
							city.loadData("parent:" + json.provinces);
						}
						
					},
					change: function (event, value) {
						city.clear();
						city.loadData("parent:" + value);
					}
				});
				var city = $("#cityQuery").select({
					name: "address.city.code",
					defaultValue: json.city,
					initLoad: false,
					param: "parent:" + json.provinces,
					zIndex: 5,
					filter: true,
					clearEvent: function() {
						county.clear();
					},
					change: function(event, value) {
						county.clear();
						county.loadData("parent:" + value);
					}
				});

				var county = $("#countyQuery").select({
					filter: true,
					initLoad: false,
					defaultValue: json.county,
					name: "address.county.code",
					param: "parent:" + json.city,
					zIndex: 4
				});
				var enterpriseTypeQuery = $("#enterpriseType").select({
					filter: true,
					name: "enterpriseType",
					defaultValue: json.enterpriseType,
					zIndex: 3
				});
				
				
				$("#editBtn").click(function(){
					//判断用户是否选择企业，如果没有给出相应提示
					var id = grid1.getSelectedValue("enterprise.id")[0];
					dialogEdit(id);
				})
				
				function dialogEdit(id){
						if(!id){
							id = grid1.getSelectedValue("enterprise.id")[0];
						}
						var opts = {
							title: "修改企业信息",
							url : "dialogEdit.jhtml",
							width:"100%",
							height:"962px",
							data:{id:id},
							yes : function(){
								$("#inputForm").submit();
							},
							cancel:function(){}
						}
						qy.panel(opts);
					}
				
				
				 var enterpriseIds = [];
				var opts = {
					tab: "tab1",
					data: {
						pageSize: 10,
						pageNumber: 1,
						projectCode: json.project,
						categoryMain : json.categoryMain,
						categoryMiddle : json.categoryMiddle,
						provinces : json.provinces,
						city : json.city,
						county : json.county,
						enterpriseType : json.enterpriseType,
						name: "",
						enterpriseTask: 'alreadySubmit',
					},
					loadDataCallBack: function (data,pageNumber) {
						data = data && data.content ? data.content : "";
	                	var ids = [];
	                	for(var i = 0; i< data.length; i++){
	                		ids.push(data[i].enterprise.id);
	                	}
	                	sessionStorage.setItem("active.pass_",JSON.stringify(ids));
						
						top.enterpriseListPageNumber = pageNumber;
						 var enterpriseList = data.content;
						 //把所有企业id缓存
						enterpriseIds = [];
						if(enterpriseList){
							for(var y=0;y<enterpriseList.length;y++){
								// console.log(enterpriseList[y].enterprise.id);
								enterpriseIds.push(enterpriseList[y].enterprise.id);
							}
						}
					},
					url: "query.jhtml",
					export_show_always: false,
					columns: [
						{name: "enterprise.address.provinces.name", caption: "行政区划", hidden: true, type: "text"},
						{name: "enterprise.address.city.name", caption: "行政区划", hidden: true, type: "text"},
						{name: "enterprise.address.county.name", caption: "行政区划", hidden: false, type: "text"},
						{name: "enterprise.name", caption: "名称", hidden: false, type: "text"},
						{name: "modifyDate", caption: "修改时间", hidden: false, type: "date"},
						{name: "enterprise.industryCategoryNameMain", caption: "行业类别（大类）", hidden: false, type: "text"},
						{name: "enterprise.industryCategoryNameMiddle", caption: "行业类别（中类）", hidden: false, type: "text"},
						{name: "enterprise.code",caption: "组织编码", hidden: false, type: "text"},
						{name: "enterprise.contacts",caption: "联系人", hidden: false, type : "text"},
						{name: "assessor",caption: "审核员", hidden: false, type : "text",valueSet: "每日任务清单【人员指派】"},
						{name: "enterprise.id",caption : "ID", hidden: true, type: "text"},
						{name: "enterprise.enterpriseType", caption: "填表类型", type: "text", valueSet: "企业【企业类型】"},
						{name: "taskStatus", caption: "状态", type: "text", format: function (value,rowValue,handle) {
							if(value === 'alreadySubmit'){
								var parent = handle.parent("tr");
								return "已提交";
							}else if(value === 'noApprove'){
								return "未通过";	
							}else if(value === 'approve'){
								var parent = handle.parent("tr");
								parent.find("#view").html("");
								return "已通过";
							}else{
								return "未提交";
							}
						}},
						{name:"opinion",caption:"审核意见",type:"text"},
						{name: "address.houseNumber", caption: "门牌号", hidden: true, type: "text"},
						{name: "address.longitude", caption: "经度", hidden: true, type: "text"},
						{name: "address.latitude", caption: "纬度 ", hidden: true, type: "text"},
						{name: "operate", caption: "查看", isHtml: true, html: '<a id="look"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
							events: [{
               					type: 'click',
								select: '#look',
								handle: function() {
                 	 				var id = grid1.getCurrentRowValue(this,'enterprise.id');
                  					var projectTypeId =grid1.getCurrentRowValue(this,'projectType');
									var enterpriseType = grid1.getCurrentRowValue(this,'enterprise.enterpriseType');
									qy.ajax({url: base + "/admin/enterprise/cacheEnterpriseId.jhtml",
										/* data: {enterpriseId: id},
										callBack: function (dataMenu) {
                      top.loadMenu(top.enterpriseMenu[enterpriseType]);
										} */
										data:{enterpriseId:id},
										callBack:function(){
											qy.ajax({
												//url: base+"/web/enterprise/findMenuByIndustry.jhtml",
												//data:{industryCategoryId:industryCategoryCodeMiddle},
												url: base+"/admin/enterprise/findMenuByProjectType.jhtml",
												data:{projectTypeId:projectTypeId},
												callBack:function(data){
													if(data.code == 20000){
														console.log(enterpriseType);
														top.loadMenuByIndustry(top.enterpriseMenu[enterpriseType],objC(data,'content'));
													}else{
														top.loadMenu(top.enterpriseMenu[enterpriseType]);
														//qy.fail2({title:data.content});
													}
													
												}
											})
										}
									})
								}
							}]
						}
						,{name:"operate",caption:"预览",isHtml:true,html:'<a id="preview"  style="cursor: pointer;">预览pdf</a>',
							events :[{
								type:'click',
								select:'#preview',
								handle:function(){
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
										url: "../enterprise/isExportpdf.jhtml",
										data:{enterpriseId:id},
										callBack:function(data){
											if(data.code == 20000){
												window.open("../enterprise/exportpdf.jhtml?enterpriseId="+id+"&type=preview");
												//location.href="../enterprise/exportpdf.jhtml?enterpriseId="+id+"&type=preview";
											}else{
												qy.fail2({title:data.content});
											}
											
										}
									})
								}
							}]  	
							}
						,{name:"operate",caption:"审核",isHtml:true,html:'<a id="audit"  style="cursor: pointer;">审核</a>',
							events :[{
								type:'click',
								select:'#audit',
								handle:function(){
									var id =grid1.getCurrentRowValue(this,'enterprise.id');
									if($(this).attr("disabled")){
										layer.msg("请稍后...");
										return;
									}
									$(this).attr("disabled","disabled");
									setTimeout(function(handle){
										handle.removeAttr("disabled");
									},5000,$(this));
									var newOpenWindow = window.open("../enterprise/auditPage.jhtml?enterpriseId=" + id,"auditPage.jhtml");
									if (!newOpenWindow) {
										window.open("../enterprise/auditPage.jhtml?enterpriseId=" + id,"auditPage.jhtml");
									}
									/* location.href = "../enterprise/auditPage.jhtml?enterpriseId=" + id; */
								}
							}]  	
							}
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
						/* {name: "operate1",caption: "操作",isHtml: true,
							html: '<a id="view" style="cursor: pointer;">审核</a>',
							events: [{
               	type: 'click',
								select: '#view',
								handle: function() {
                //获取到当前的id
									var id = grid1.getCurrentRowValue(this, 'enterprise.id');
									var opts = {
										title: "审核企业信息",
										url: "enterpriseAuditPage.jhtml",
										width: "90%",
										height: "90%",
										btn: ['', ''],
										btn:['通过','不通过','返回'],
										data: {
											enterpriseId: id,
											projectCode: json.project,
											enterpriseIds: JSON.stringify(enterpriseIds),
										},
										yes:function(){
											// 指定一个审核通过函数
											if(typeof mainVar.yes === 'function'){
												mainVar.yes();
											}
										},
										btn2:function(){
											// 指定一个审核不通过函数
											if (typeof mainVar.btn2 === 'function') {
												mainVar.btn2();
											}
											return false;
										},
										btn3: function() {
											grid1.loadData();
										},
										cancel:function(){
											// 重新加载表格
											grid1.loadData();
										}
									}
						    	qy.panel(opts);
								}
							}]
						} */
					]
				}
				grid1 = g.grid(opts);
				grid1.loadData({pageNumber:(top.enterpriseListPageNumber || 1)});
				
			})
</script>

</body>
</html>