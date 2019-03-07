<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改模块信息</title>
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
	
	var projectId = $("#inputForm #projectId").select({filter:true,name:"projectId",zIndex:9,defaultValue:"${fieldShowConfig.projectId}", change:function(event, value){
		tableType.loadData("project:"+value+",enterpriseTypeParam");
	},initAfter:function(){
		tableType.loadData("project:"+'${fieldShowConfig.projectId}'+",enterpriseTypeParam");
	}
	});
	var tableType =  $("#inputForm #tableType").select({filter:true,name:"tableType",defaultValue:"${fieldShowConfig.tableType}",zIndex:8,change:function(event, value){
		fieldModule.clear();
		fieldModule.loadData("tableType:"+value);
		var projectId1 = projectId.getSelectValue();
		projectTypeId.loadData("project:"+projectId1+",enterpriseType:"+value);
	},initAfter:function(){
		fieldModule.loadData("tableType:"+'${fieldShowConfig.tableType}');
		projectTypeId.loadData("project:"+'${fieldShowConfig.projectId}'+",enterpriseType:"+'${fieldShowConfig.tableType}');
	}
	});
	var projectTypeId = $("#inputForm #projectTypeId").select({filter:true,name:"projectTypeId",defaultValue:"${fieldShowConfig.projectType.id}",zIndex:7});
	
	var fieldModule = $("#inputForm #fieldModule").select({filter:true,name:"fieldModule",defaultValue:"${fieldShowConfig.fieldModule}",zIndex:6,change:function(event, value){
		queryField(value);
	}});
	
	if('${fieldShowConfig.projectId}' && '${fieldShowConfig.tableType}'){
		projectTypeId.loadData("project:"+'${fieldShowConfig.projectId}'+",enterpriseType:"+'${fieldShowConfig.tableType}');
	}
		
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
	           // alert("失败");  
	        }  
	    });  
	}
	var dataid = "${fieldShowConfig.hideField}".split(",");
	$.ajax({  
        type: 'POST',  
        url: '../fieldconfig/queryAll.jhtml',  //url 
        data: {moduleId:'${fieldShowConfig.fieldModule}'},  
        dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
        ContentType: "application/json; charset=utf-8",  
        success: function (data) { 
        	for (var i = 0; i < dataid.length; i++) {
        		//data = data.replace('"id":temp,','"id":' + dataid[i] + ',checked:true,');
        		data = data.replace('"value":"' + dataid[i]+'"','"value":"' + dataid[i] + '",checked:true');
 				/* do{
 					data = data.replace('"id":' + dataid[i] + ',','"id":temp,');
 				}while(data.search('"id":' + dataid[i] + ',')+1);
 				do{
 					data = data.replace('"id":temp,','"id":' + dataid[i] + ',checked:true,');
 				}while(data.search('"id":temp,')+1); */
 				
			
 			};
            zNodes = data;  
            tree =  $.fn.zTree.init($("#menuTree"), setting, eval('(' + zNodes + ')'));  
        },  
        error: function (msg) {
        	console.log(msg);
           // alert("失败");  
        }  
    });  
	
	
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
				
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		layer.msg('修改成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content,{icon:3});
			        	}
						
			        },
			        error:function(){
			        	layer.msg('修改失败',{icon:3});
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
				<label for="tableType">项目类型：</label>
				<div data-code="企业【项目类型】" id="projectTypeId" class=" selectF"></div>
			</div>
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
	 <input type="hidden" id="id" name="id" value="${fieldShowConfig.id}"/>
 


</form>
</body>
</html>