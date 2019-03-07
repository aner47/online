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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&amp;ak=C8ANgrUU7EZGAm0CSpZXaFgwTorXl97n"></script>
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&amp;ak=C8ANgrUU7EZGAm0CSpZXaFgwTorXl97n&amp;services=&amp;t=20180521160403"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
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
				//获取session-start
				var userCheckValue = sessionStorage.getItem("userCheck");
				var json = JSON.parse(userCheckValue) || {};
				//获取session-end
				$('#delete').click(
						function(e) {
							//判断用户是否选择所要删除的企业信息，如没有则给出相应提示
							if (grid1.getSelectedValue('enterprise.id').length == 0) {
								qy.sure({
									title : "请选择所要删除的企业信息"
								})
								return false;
							}
							//删除
							qy.sure({
								title : '是否删除所选'
										+ grid1.getSelectedValue('enterprise.id').length
										+ '条企业信息?',
								yes : function(index) {
									layer.close(index);
									var ids = grid1.getSelectedValue('enterprise.id');
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
						btn:['保存','取消'],
						data : {
							id : grid1.getSelectedValue("id")[0],
							enterpriseId : grid1.getSelectedValue("enterprise.id")[0]
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
					sessionStorage.setItem("userCheck", userCheckStr);
					//本地存储--end

					var data = {};
					var array = $('#queryForm').serializeArray();
					$.each(array, function(i, v) {
						data[v.name] = v.value;

					})
					grid1.loadData(data);
				});
				//项目选择
				var project = $("#project").select({
					name : "projectCode",
					//codeValue : "name",
					defaultValue : json.project,
					//code : "invitationCode",
					zIndex : 8,
					//url : "../project/queryAll.jhtml",
					change : function(event, value) {
						userSearch.clear();
						userSearch.loadData("project.id:"+value);
					}
				});

				//用户选择
				var userSearch = $("#userSearch").select({
					name : "account",
					//codeValue : "username",
					defaultValue : json.userSearch,
					//code : "id",
					zIndex : 7,
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
				
				var modifyTime = $("#modifyTime").select({
					name : $("#modifyTime").val(),
					zIndex : 6,
					change : function(event, value) {
						console.log(value)
					}
				});
				
				
				var enterpriseTask = $("#enterpriseTask").select({filter:true,name:"enterpriseTask",zIndex:3});
				
				var opts = {
					tab : "tab1",
					data : {
						pageSize : 10,
						pageNumber : 0,
						account : "${account}",
						projectCode : json.project,
						name : ""
					},
					loadDataCallBack:function(data,pageNumber){
						top.enterpriseListPageNumber = pageNumber;
					},
					url : "query.jhtml",
					//checkbox:false,
					export_show_always : true,
					columns : [
							{
								name : "enterprise.id",
								caption : "企业id",
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
							{name:"taskStatus",caption:"状态",type:"text",format:function(value,rowValue,handle){
								if(value =='alreadySubmit'){
									var parent = handle.parent("tr");
									//parent.find("#view").html("");
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
							{
								name : "user.username",
								caption : "用户 ",
								hidden : false,
								type : "text"
							},
							{name:"operate",caption:"操作",width:120,isHtml:true,html:'<a id="view" style="cursor: pointer;color:#888888;"><i id="tianbao" class="iconfont icon-bianji" style="color:#6495ED;">查看图片</i></a>',	
								events :[{
										type:'click',
										select:'#tianbao',
										handle:function(){
											var id =grid1.getCurrentRowValue(this,'enterprise.id');
											location.href="../photofile/list.jhtml?enterpriseId="+id;
											
										}
									}] 
							},
							/* {
								name : "operate",
								caption : "操作",
								isHtml : true,
								html : '<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
								events : [ {
									type : 'click',
									select : '#view',
									handle : function() {
										var projectValue = project.getSelectValue();
										if(!projectValue){
											qy.fail2({
												title : '选择项目！'
											});
											return;
										}
										
										var id = grid1.getCurrentRowValue(this,'enterprise.id');
										var enterpriseType = grid1.getCurrentRowValue(this,'enterprise.enterpriseType');
										var projectTypeId =grid1.getCurrentRowValue(this,'projectType');
										var company_name=grid1.getCurrentRowValue(this,'enterprise.name');
										var industryCategoryCodeMain = grid1.getCurrentRowValue(this,'enterprise.industryCategoryCodeMain');
										var industryCategoryCodeMiddle = grid1.getCurrentRowValue(this,'enterprise.industryCategoryCodeMiddle');
										var industryCategoryCodeSmall = grid1.getCurrentRowValue(this,'enterprise.industryCategoryCodeSmall');
										$("#company").html(company_name);
										qy.ajax({
												url : base
														+ "/admin/enterprise/cacheEnterpriseId.jhtml",
														data:{enterpriseId:id},
														callBack:function(){
															qy.ajax({
																url: base+"/admin/enterprise/findMenuByProjectType.jhtml",
																data:{projectTypeId:projectTypeId},
																callBack:function(data){
																	if(data.code == 20000){
																		top.loadMenuByIndustry(top.enterpriseMenu[enterpriseType],objC(data,'content'));
																	}else{
																		top.loadMenu(top.enterpriseMenu[enterpriseType]);
																	}
																	
																}
															})
														}
										})

									}
								}
								]
							}, */
							/* {
								name : "operate",
								caption : "状态修改",
								isHtml : true,
								html : '<a id="update"  style="cursor: pointer;">修改</a>',
								events : [ {
									type : 'click',
									select : '#update',
									handle : function() {
										var id = grid1.getCurrentRowValue(this,'id');
										var enterpriseType = grid1.getCurrentRowValue(this,'enterpriseType');
										
										layer.msg('请确定修改为未提交状态？', {
											  time: 0 //不自动关闭
											  ,btn: ['确定', '取消']
											  ,area: ['280px', '150px']		//宽，高
											  ,yes: function(index){
												  qy.ajax({
														url : base+ "/admin/enterprise/updatestatus.jhtml",
														data : {
															enterpriseId : id
														},
														callBack : function(dataMenu) {
															//grid1 = g.grid(opts);
															//grid1.loadData();
															grid1.loadData({pageNumber:(top.enterpriseListPageNumber || 1)});
															layer.close(index);
														}
													})
											    
											    
											  }
											});
										
										

									}
								}]
							}, */
							
							]
				}
				grid1 = g.grid(opts);
				grid1.loadData({pageNumber:(top.enterpriseListPageNumber || 1)});

			})
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont">&#xe640;</i> 企业信息列表
			<!-- <div class="titleBtn" id="export" onclick="">导出</div> -->
			<!-- <div id="editBtn" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div> -->
			<div class="titleBtn" id="delete">删除</div>

			<%-- <c:if test="${principal.userType != 'system'}">
			<div class="titleBtn" id="add">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div>
		</c:if> --%>

		</h1>
		<form class="form" id="queryForm" action="#" onsubmit="return false">
			<div class="formLine">
				<div class="formPara">
					<label class="label">项目</label>
					<div id="project" 
						class="projectdiv selectF" data-code="全局【项目】"></div>
				</div>
				<!-- <div class="formPara">
					 <label class="label">用户</label>
					<div id="userSearch" class="userSearchdiv selectF" data-code="每日任务【人员】"></div>
				 </div> -->
				 <div class="formPara">
					<label class="label">状态</label> 
					<div data-code="企业【企业任务状态】" id="enterpriseTask" class="projectdiv selectF"></div>
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
					<div data-code="行业" id="industryCategoryCodeMainQuery"
						data-code="projectCode" class="projectdiv selectF"></div>
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
					<input type="text" class="input projectdiv" id="putOnTime" name="putOnTime" placeholder="开始时间" readonly />
				</div>
				<div class="formPara">
					<label class="label">结束日期</label>
					<input type="text" class="input projectdiv" id="stopTime" name="stopTime" placeholder="结束时间" readonly  />
				</div>
				
				

			</div>
		</form>




		<div id="tab1" class="tab" style="border-top: 1px solid #e7ebee;"></div>
	</div>
</body>
</html>