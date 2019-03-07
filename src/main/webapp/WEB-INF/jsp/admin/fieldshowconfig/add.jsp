<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加模块信息</title>
</head>

<script type="text/javascript">
require(["validate","select","ajaxform","ztree"],function(a){
	
	var treeId;
	var tree 
	/* ztree设置 */
	var zTree;
	var zNodes; 
	var setting = {
	  view: {
		     filter:true,  //是否启动过滤
		     expandLevel:0,  //展开层级
		     showFilterChildren:true, //是否显示过滤数据孩子节点
		     showFilterParent:true, //是否显示过滤数据父节点
		     showLine: false,//是否显示节点之间的连线
	         showIcon: false, //是否显示图标
	    selectedMulti: true //设置是否允许同时选中多个节点
	  },
	  check:{
	    chkStyle: "checkbox",//复选框类型
	    enable: true ,//每个节点上是否显示 CheckBox 
	  },
	  async: {
		  enable: true,
		   type: 'post',
		   autoParam: ['id'], //异步加载时需要自动提交父节点属性的参数
		   dataType:'json',
	    },
	  data: {
	    
		  simpleData: {//简单数据模式
	      enable:true,
	      idKey: "id",
	      pIdKey: "parent",
	      rootPId: 0,
	    }
	  },
	}; 
	var alreadysetting = {
	  view: {
		     filter:true,  //是否启动过滤
		     expandLevel:0,  //展开层级
		     showFilterChildren:true, //是否显示过滤数据孩子节点
		     showFilterParent:true, //是否显示过滤数据父节点
		     showLine: false,//是否显示节点之间的连线
	         showIcon: false, //是否显示图标
	    selectedMulti: true //设置是否允许同时选中多个节点
	  },
	  check:{
	    chkStyle: "checkbox",//复选框类型
	    enable: true ,//每个节点上是否显示 CheckBox 
	  },
	  async: {
		  enable: true,
		   type: 'post',
		   autoParam: ['id'], //异步加载时需要自动提交父节点属性的参数
		   dataType:'json',
	    },
	  data: {
		  key:{
			  name:"fieldModule"
		  },
	    
		  simpleData: {//简单数据模式
	      enable:true,
	      idKey: "id",
	      pIdKey: "parent",
	      rootPId: 0,
	    }
	  },
	}; 
	
	$.fn.zTree.init($("#menuTree"), alreadysetting, eval('([])'));
	$.fn.zTree.init($("#alreadyMenuTree"), alreadysetting, eval('([])'));
	
	var projectId = $("#inputForm #projectId").select({filter:true,name:"projectId",zIndex:9,change:function(event, value){
		tableType.loadData("project:"+value+",enterpriseTypeParam");
	}});
	
	var tableType =  $("#inputForm #tableType").select({initLoad:false,filter:true,name:"tableType",zIndex:8,change:function(event, value){
		fieldModule.loadData("tableType:"+value);
		var projectId1 = projectId.getSelectValue();
		var alreadyProjectId1 = alreadyProjectId.getSelectValue();
		projectTypeId.loadData("project:"+projectId1+",enterpriseType:"+value);
		
		alreadyprojectTypeId.clear();
		alreadyprojectTypeId.loadData("project:"+alreadyProjectId1+",enterpriseType:"+value);
	}});
	
	var projectTypeId = $("#inputForm #projectTypeId").select({initLoad:false,filter:true,name:"projectTypeId",zIndex:7});
	
	var fieldModule = $("#inputForm #fieldModule").select({initLoad:false,filter:true,name:"fieldModule",zIndex:6,change:function(event, value){
		queryField(value);
	}});
	
	
	
	var alreadyProjectId = $("#inputForm #alreadyProjectId").select({filter:true,name:"alreadyProjectId",zIndex:6,change:function(event, value){
		var enterpriseType = tableType.getSelectValue();
		alreadyprojectTypeId.loadData("project:"+value+",enterpriseType:"+enterpriseType);
	}});
	var alreadyprojectTypeId = $("#inputForm #alreadyprojectTypeId").select({initLoad:false,filter:true,name:"alreadyprojectTypeId",zIndex:4,change:function(event, value){
		var projectId = alreadyProjectId.getSelectValue();
		var enterpriseType = tableType.getSelectValue();
		
		queryAlreadMeun(projectId,enterpriseType,value);
	}});
	
	
	
	$("#alreadyProject").hide();
	$("[name=model]").on('change',function(){
		if ($(this).val()=='是') {
			$("#alreadyProject").show();
			$("#noProject").hide();
		}else{
			$("#noProject").show();
			$("#alreadyProject").hide();
		};
	})
	
	
	
		
	function queryField(moduleId){
		$.ajax({  
	        type: 'POST',  
	        url: '../fieldconfig/queryAll.jhtml',  //url 
	        data: {moduleId:moduleId},  
	        dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
	        ContentType: "application/json; charset=utf-8",  
	        success: function (data) { 
	            zNodes = data;  
	            tree =  $.fn.zTree.init($("#menuTree"), setting, eval('(' + zNodes + ')'));  
	        },  
	        error: function (msg) {
	        	console.log(msg);
	            alert("失败");  
	        }  
	    });  
	}
	
	function queryAlreadMeun(projectId,tableType,projectType){
		$.ajax({  
	        type: 'POST',  
	        url: '../fieldshowconfig/querybyprojectid.jhtml',  //url 
	        data: {projectId:projectId,tableType:tableType,projectType:projectType},  
	        dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
	        ContentType: "application/json; charset=utf-8",  
	        success: function (data) { 
	            zNodes = data;  
	            tree =  $.fn.zTree.init($("#alreadyMenuTree"), alreadysetting, eval('(' + zNodes + ')'));  
	        },  
	        error: function (msg) {
	        	console.log(msg);
	            alert("失败");  
	        }  
	    });  
	}
	
	
		//提交表单
		$("#inputForm").validate({
			rules: {
				projectId:{
					required:true
				},
				tableType:{
					required:true
				},
				fieldModule:{
					required:true
				},
				alreadyProjectId:{
					required:true
				}
			},
			messages:{
			},
			submitHandler:function(form){
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var nodes = treeObj.getCheckedNodes(true);
				var menuList= [];
				$.each(nodes,function(k,v){
					menuList.push(v.value)
				})
				$('#inputForm #hideField').val(menuList.join(','))
				
				var alreadytreeObj = $.fn.zTree.getZTreeObj("alreadyMenuTree");
				var alreadynodes = alreadytreeObj.getCheckedNodes(true);
				var alreadymenuList= [];
				$.each(alreadynodes,function(k,v){
					alreadymenuList.push(v.id)
				})
				$('#inputForm #alreadyMenu').val(alreadymenuList.join(','))
				
				
				
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		layer.msg('添加成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content,{icon:3});
			        	}
						
			        },
			        error:function(){
			        	layer.msg('添加失败',{icon:3});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});		
	});
</script>

<body>

	<form id="inputForm">
		<div class="container">
			<div class="form-line">
				<label for="projectId">项目 ：</label>
				<div data-code="全局【项目】" id="projectId" class=" selectF"></div>
			</div>
			<div class="form-line">
				<label for="tableType">填表类型：</label>
				<div data-code="企业【项目类型】" id="tableType" class=" selectF"></div>
			</div>
			<div class="form-line">
				<label for="projectTypeId">项目类型：</label>
				<div data-code="企业【项目类型】" id="projectTypeId" class=" selectF"></div>
			</div>
			<div class="form-line">
                <label for="">是否套用其他项目模板：</label>
                <div class="radio-block">
                    <input type="radio" class="radio-block-radio" id="cheD1" name="model" value="是"/>
                    <label for="cheD1" class="radio-block-label">是</label>
                    <input type="radio" class="radio-block-radio" id="cheD2" name="model" value="否" checked="checked"/>
                    <label for="cheD2" class="radio-block-label">否</label>
                </div>
			</div>
			<!-- 已配置隐藏字段 -->
			<div class="form-line" id="alreadyProject">
				<label for="projectId">已配置项目 ：</label>
				<div data-code="已配隐藏字段【项目】" id="alreadyProjectId" class=" selectF"></div>
				
				<div class="form-line">
					<label for="alreadyprojectTypeId">项目类型：</label>
					<div data-code="企业【项目类型】" id="alreadyprojectTypeId" class=" selectF"></div>
				</div>
				<label for="alreadyMenu">已配置菜单：</label>
				<input type="hidden" id="alreadyMenu" name="alreadyMenu" />
				<div class="form-group">
					<!-- // 组织树模型，固定格式   -->
					<ul id="alreadyMenuTree" class="ztree"></ul>
				</div>
			</div>
			<!-- 未配置隐藏字段 -->
			<div class="container" id="noProject" >
			
			<div class="form-line">
				<label for="fieldModule">显示菜单：</label>
				<div data-code="后台【显示菜单】" id="fieldModule" class=" selectF"></div>
			</div>
			<input type="hidden" id="hideField" name="hideField" placeholder="菜单列表" />
			<label for="fieldModule">隐藏字段：</label>
			<div class="form-group">
				<!-- // 组织树模型，固定格式   -->
				<ul id="menuTree" class="ztree">
				</ul>
			</div>
			</div>
			
			
		</div>

	</form>
</body>
</html>