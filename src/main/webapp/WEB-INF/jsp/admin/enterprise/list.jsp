<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		require(["grid","select","panel","less","commonBase"],function(g){
		$('#delete').click(function(e){
			//判断用户是否选择所要删除的企业信息
			if($(this).attr("disabled")){
				return false;
			}
			//删除
			qy.sure({
				title:'是否删除所选'+grid1.getSelectedValue('enterprise.id').length+'条企业信息?', 
				yes: function(index){
					layer.close(index);
					var ids = grid1.getSelectedValue('enterprise.id');
					var opts ={
						url:'delete.jhtml',
						data :{"ids":ids.join(',')},
						callBack:function(data,errcode){
							if(data.code =="20000"){
								qy.suc2({title:'删除企业成功！'});
								grid1.loadData();
							}else{
								qy.fail2({title:'删除企业失败！'+data.content});
							}
						}
					}
					qy.ajax(opts);
				}
			});
		});

		$("#add").click(function(){
			var opts = {
				title: "添加企业信息",
				url : "addPage.jhtml",
				width:"100%",
				height:"962px",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});

		$("#editBtn").click(function(){
			//判断用户是否选择企业，如果没有给出相应提示
			if($(this).attr("disabled")){
				return false;
			}
			var id = grid1.getSelectedValue("enterprise.id")[0];
			var status = grid1.getSelectedValue("taskStatus")[0];
			
			
			dialogEdit(id,status);
		})
		function dialogEdit(id,status){
			console.log(status);
			if(status == 'approve' || status == 'noApprove'){
				qy.fail2({title:"已审核企业不能编辑"});
				return false;
			}
			
			if(!id){
				id = grid1.getSelectedValue("enterprise.id")[0];
			}
			var opts = {
				title: "企业信息编辑",
				url : "dialogEdit.jhtml",
				width:"100%",
				height:"962px",
				data:{id:id},
				btn:['保存','取消'],
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		}
			//查询
			$('#project_user_Search').click(function(){
				var data = {};
				var array = $('#queryForm').serializeArray();
				$.each(array,function(i,v){
					data[v.name] = v.value;	

				})
				grid1.loadData(data);
			});
		
		$("#enterpriseType").select({filter:true,name:"enterpriseType",zIndex:3,change:function(event, value){
			
        }});
		//行业类别（大类）
		var categoryMain =  $("#industryCategoryCodeMainQuery").select({filter:true,name:"industryCategoryCodeMain",zIndex:8,param:"categoryLevel:MAIN",change:function(event, value){
        	var text = categoryMain.getSelectCaption();
        	$("input[name=industryCategoryNameMain]").val(text);
        	categoryMiddle.clear();
        	categoryMiddle.loadData("parent:"+value)
        }});
		//行业类别（中类）
		var categoryMiddle =  $("#industryCategoryCodeMiddleQuery").select({name:"industryCategoryCodeMiddle",initLoad:false,zIndex:7,change:function(event, value){
        	var text = categoryMiddle.getSelectCaption();
        	$("input[name=industryCategoryNameMiddle]").val(text);
        }});
		
		var provinces = $("#provincesQuery").select({name:"address.provinces.code",zIndex:6,change:function(event, value){
        	city.clear();
        	if(value)city.loadData("parent:"+value);
        }});
        var city = $("#cityQuery").select({name:"address.city.code",zIndex:5,initLoad:false,clearEvent:function(){county.clear();},change:function(event, value){
        	
        	county.clear();
        	if(value)county.loadData("parent:"+value);
        }});
        
		var county = $("#countyQuery").select({filter:true,name:"address.county.code",zIndex:4,initLoad:false,clearEvent:function(){},change:function(event, value){
        }});
		var taskStatus = $("#taskStatus").select({filter:true,name:"taskStatus",zIndex:3});
		

			var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:1},
				url:"query.jhtml",
				loadDataCallBack:function(data,pageNumber){
					top.enterpriseListPageNumber = pageNumber;
				},
				//checkbox:false,
				columns :[
				
				{name:"enterprise.address.county.name",caption:"行政区划",hidden:false,type:"text"},
				{name:"enterprise.name",caption:"名称",hidden:false,type:"text"},
				{name:"enterprise.industryCategoryNameMain",caption:"行业类别（大类）",hidden:false,type:"text"},
				//{name:"enterprise.industryCategoryNameMiddle",caption:"行业类别（中类）",hidden:false,type:"text"},
				//{name:"enterprise.industryCategoryNameSmall",caption:"行业类别（小类）",hidden:false,type:"text"},
				{name:"enterprise.industryCategoryCodeMain",caption:"行业类别code",hidden:true,type:"text"},
				{name:"enterprise.industryCategoryCodeMiddle",caption:"行业类别中类code",hidden:true,type:"text"},
				{name:"enterprise.industryCategoryCodeSmall",caption:"行业类别小类code",hidden:true,type:"text"},
				//{name:"enterprise.code",caption:"组织编码",hidden:false,type:"text"},
				{name:"enterprise.contacts",caption:"企业联系人",hidden:false,type:"text"},
				{name:"enterprise.contactNumber",caption:"企业联系人电话",hidden:false,type:"text"},
				{name:"enterprise.id",caption:"ID",hidden:true,type:"text",format:function(value,rowValue,handle){
						//var parent = handle.parent("tr");
						//parent.find("#export").attr({"href":base+"/web/enterprise/exportpdf.jhtml?enterpriseId="+value});
					
				}},
				{name:"enterprise.enterpriseType",caption:"填表类型",type:"text",valueSet:"企业【企业类型】"},
				{name:"taskStatus",caption:"状态",type:"text",format:function(value,rowValue,handle){
					if(value =='alreadySubmit'){
						/* var parent = handle.parent("tr");
						parent.find("#view").html(""); */
						return "已提交";
					}else if(value =='noApprove'){
						var parent = handle.parent("tr");
						parent.find("#edit").html("");
						return "未通过";	
					}else if(value =='approve'){
						var parent = handle.parent("tr");
						parent.find("#view").html("");
						parent.find("#edit").html("");
						return "已通过";
					}else{
						return "未提交";
					}
					
				}},
				{name:"opinion",caption:"退回意见",type:"text"},
				
				{name:"enterprise.account",caption:"用户",hidden:true,type:"text"},
				{name:"enterprise.taskType",caption:"任务类型",hidden:true,type:"text",format:function(value,rowValue,handle){
					if(value =='UNTREATED'){
						var parent = handle.parent("tr")
						//handle.parent("tr").css("background","yellow");
						parent.find("#view").html('<span  ><a id="complement"  class="iconfont icon-bianji" style="color:#ff0000;">请补全企业信息</a></span>');
					}
				}},
				
				{name:"enterprise.contactNumber",caption:"联系号码",hidden:true,type:"text"},
				{name:"enterprise.address.provinces.name",caption:"省",hidden:true,type:"text"},
				{name:"enterprise.address.city.name",caption:"市",hidden:true,type:"text"},
				{name:"enterprise.address.street.name",caption:"街道",hidden:true,type:"text"},
				{name:"enterprise.address.houseNumber",caption:"门牌号",hidden:true,type:"text"},
				{name:"enterprise.address.longitude",caption:"经度",hidden:true,type:"text"},
				{name:"operate",caption:"编辑",isHtml:true,html:'<a id="edit"  style="cursor: pointer;">编辑</a>',	
					events :[{
						type:'click',
						select:'#edit',
						handle:function(){
							var id =grid1.getCurrentRowValue(this,'enterprise.id');
							var status =grid1.getCurrentRowValue(this,'taskStatus');
							dialogEdit(id,status);
						}
					}] 
				},
				{name:"operate",caption:"操作",isHtml:true,html:'<a id="view" style="cursor: pointer;color:#888888;"><i id="tianbao" class="iconfont icon-bianji" style="color:#6495ED;">填报</i></a>',	
					events :[
						{
							type:'click',
							select:'#tianbao',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'enterprise.id');
								var projectTypeId =grid1.getCurrentRowValue(this,'projectType');
								var enterpriseType =grid1.getCurrentRowValue(this,'enterprise.enterpriseType');
								var company_name=grid1.getCurrentRowValue(this,'enterprise.name');
								var industryCategoryCodeMain = grid1.getCurrentRowValue(this,'enterprise.industryCategoryCodeMain');
								var industryCategoryCodeMiddle = grid1.getCurrentRowValue(this,'enterprise.industryCategoryCodeMiddle');
								var industryCategoryCodeSmall = grid1.getCurrentRowValue(this,'enterprise.industryCategoryCodeSmall');
								top.$("#company").show();
								top.$("#company").html("企业名称："+company_name);
								top.$("#enterpriseid").val(id);
								top.$("#enterpriseType").val(enterpriseType);
								top.$("#enterprise_button_div").show();
								qy.ajax({
									url: base+"/web/enterprise/cacheEnterpriseId.jhtml",
									data:{enterpriseId:id},
									callBack:function(data){
										if(data.code == 20000){
											qy.ajax({
												//url: base+"/web/enterprise/findMenuByIndustry.jhtml",
												//data:{industryCategoryId:industryCategoryCodeMiddle},
												url: base+"/web/enterprise/findMenuByProjectType.jhtml",
												data:{projectTypeId:projectTypeId},
												callBack:function(data){
													if(data.code == 20000){
														top.loadMenuByIndustry(top.enterpriseMenu[enterpriseType],objC(data,'content'));
													}else{
														top.loadMenu(top.enterpriseMenu[enterpriseType]);
													}
													
												}
											})
										}else{
											qy.fail2({title:data.content});
										}
										
									}
								})
		
							}
						},{
							type:'click',
							select:'#complement',
							handle:function(){
								var id =grid1.getCurrentRowValue(this,'enterprise.id');
								dialogEdit(id);
							}
						}
					] 
				},
				
				{name:"operate",caption:"导出",isHtml:true,html:'<a id="export"  style="cursor: pointer;">导出pdf</a>',
					events :[{
					type:'click',
					select:'#export',
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
							url: base+"/web/enterprise/isExportpdf.jhtml",
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
				/* ,{name:"operate",caption:"预览",isHtml:true,html:'<a id="preview"  style="cursor: pointer;">预览pdf</a>',
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
							url: base+"/web/enterprise/isExportpdf.jhtml",
							data:{enterpriseId:id},
							callBack:function(data){
								if(data.code == 20000){
									window.open("../enterprise/exportpdf.jhtml?enterpriseId="+id+"&type=preview",'_self');
									//location.href="../enterprise/exportpdf.jhtml?enterpriseId="+id+"&type=preview";
								}else{
									qy.fail2({title:data.content});
								}
								
							}
						})
						
						
						
					}
				}]  	
				} */
			],
			events:{
				rowClick:function(handle){
					var rowData = grid1.getSelectedRow();
					$.each(rowData,function(i,v){
						if(v.status == "2"){
							$("#delete").attr("disabled","disabled");
							$("#editBtn").attr("disabled","disabled");
						}
					})
				}
			}
		}
		grid1 =  g.grid(opts);
		grid1.loadData({pageNumber:(top.enterpriseListPageNumber || 1)});
		top.$("#company").hide();
		top.$("#enterprise_button_div").hide();
		
	})
</script>
<style>
html body {
	padding:20px 20px !important;
}
.tab .mygrid_table {
	border: 1px solid #e9eaec;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont">&#xe640;</i> 企业信息列表
			<div class="titleBtn" data-grid="tab1"  data-enable="2" id="delete">删除</div>
			<!-- <div id="editBtn" class="titleBtn" data-grid="tab1" data-enable="1">编辑</div> -->
			<!-- <div class="titleBtn" id="add">添加</div> -->
		</h1>
		<c:if test="${principal.userType == 'investigator'}">
		<form class="form" id="queryForm" action="#" onsubmit="return false">
			<div class="formLine">
				<div class="formPara">
					<label style="width:70px;" class="label">企业名称</label> 
					<input type="text" class="input" name="name"/>
				</div>
				<div class="formPara">
					<label class="label">行业(大类)</label>
					<div data-code="行业" id="industryCategoryCodeMainQuery" data-code="projectCode" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">行业(中类)</label>
					<div data-code="行业" id="industryCategoryCodeMiddleQuery" class="userSearchdiv selectF"></div>
				</div>
				<button id="project_user_Search" class="button">搜索</button>
			</div>
			<div class="formLine">
				<div class="formPara">
					<label style="width:70px;" class="label">行政区划</label> 
					<div id = "provincesQuery" data-code="地域" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">市</label> 
					<div id = "cityQuery" data-code="地域" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">县</label> 
					<div id = "countyQuery" data-code="地域" class="projectdiv selectF"></div>
				</div>
			</div>
			<div class="formLine">
				<div class="formPara">
					<label style="width:70px;" class="label">填表类型</label> 
					<div data-code="企业【企业类型】" id="enterpriseType" class="projectdiv selectF"></div>
				</div>
				<div class="formPara">
					<label class="label">状态</label> 
					<div data-code="企业【企业任务状态】" id="taskStatus" class="projectdiv selectF"></div>
				</div>
				
			</div>
		</form>
		</c:if>
		<div id="tab1" class="tab" style="padding: 0 20px;"></div>
	</div>
</body>
</html>