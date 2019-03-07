<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改行业分类</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select","ztree"],function(a){
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
	      pIdKey: "pid",
	      rootPId: 152,
	    }
	  },
	}; 
	
	
	
	   var enterpriseType = $("#enterpriseType", "#inputForm").select({
           name : "enterpriseType"
           ,zIndex:9
           ,defaultValue:"${categoryMenuConfig.enterpriseType}"
           ,empty: true
           ,change:function(event, value){
        	  // queryField(value);
           }});
	   
	 //行业
		var categoryId =  $("#categoryId", "#inputForm").select({filter:true
			,name:"categoryId"
			,defaultValue:"${categoryMenuConfig.categoryId}"
			,zIndex:8,change:function(event, value){
	    	
	    }});
	 //行业类别（大类）
		var categoryMain =  $("#industryCategoryCodeMainQuery", "#inputForm").select({filter:true
			,name:"industryCategoryCodeMain",zIndex:7,param:"categoryLevel:MAIN",change:function(event, value){
	    	//var text = categoryMain.getSelectCaption();
	    	//$("input[name=industryCategoryNameMain]").val(text);
	    	categoryMiddle.clear();
	    	categoryMiddle.loadData("parent:"+value)
	    }});
		//行业类别（中类）
		var categoryMiddle =  $("#industryCategoryCodeMiddleQuery", "#inputForm").select({name:"industryCategoryCodeMiddle",zIndex:6,change:function(event, value){
	    	//var text = categoryMiddle.getSelectCaption();
	    	//$("input[name=industryCategoryNameMiddle]").val(text);
	    	categorySmall.clear();
	    	categorySmall.loadData("parent:"+value);
	    }});
		//行业类别（小类）
		var categorySmall =  $("#industryCategoryCodeSmallQuery", "#inputForm").select({name:"industryCategoryCodeSmall",zIndex:5,change:function(event, value){
	    //	var text = categorySmall.getSelectCaption();
	    	//$("input[name=industryCategoryNameSmall]").val(text);
	    }});
		
		
		function queryField(moduleId){
			 
		}
		var dataid = "${categoryMenuConfig.menus}".split(",");
		$.ajax({  
	        type: 'POST',  
	        url: '../systemmenu/querymenuByPid.jhtml',  //url 
	        data: {pid:152},  
	        dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
	        ContentType: "application/json; charset=utf-8",  
	        success: function (data) { 
	        	for (var i = 0; i < dataid.length; i++) {
	        		data = data.replace('"id":' + dataid[i]+",",'"id":' + dataid[i] + ',checked:true');
	 			};
	            zNodes = data;  
	            tree =  $.fn.zTree.init($("#menuTree"), setting, eval('(' + zNodes + ')'));  
	        },  
	        error: function (msg) {
	        	console.log(msg);
	           // alert("失败");  
	        }  
	    }); 
		
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
				enterpriseType:{
					required:true
				},
				categoryId:{
					required:true
				},
			},
			messages:{
			},
			submitHandler:function(form){
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var nodes = treeObj.getCheckedNodes(true);
				var menuList= [];
				var menuListName= [];
				$.each(nodes,function(k,v){
					if(v.level == 0){
						return;
					}
					menuList.push(v.id);
					menuListName.push(v.name);
				})
				$('#inputForm #menus').val(menuList.join(','))
				$('#inputForm #menusName').val(menuListName.join(','))
				
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('修改成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
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
	 <input type="hidden" id="id" name="id" value="${categoryMenuConfig.id}"/>
 		<div class="form-line">
				<label for="name">填表类型：</label>
				<div id="enterpriseType" class="selF" data-code="企业【企业类型】" 
				defaultValue="{categoryMenuConfig.enterpriseType}"/>

			</div>
			<div class="form-line">
				<label for="code">行业</label>
				<div id="categoryId" class="selF" data-code="行业" />
			</div>
			<div class="form-line">
				<label for="code">行业大类</label>
				<div id="industryCategoryCodeMainQuery" class="selF" data-code="行业" />
			</div>
			<div class="form-line">
				<label for="code">行业中类</label>
				<div id="industryCategoryCodeMiddleQuery" class="selF"
					data-code="行业" />
			</div>
			<div class="form-line">
				<label for="code">行业小类</label>
				<div id="industryCategoryCodeSmallQuery" class="selF" data-code="行业" />
			</div>
			<input type="hidden" id="menus" name="menus" placeholder="菜单列表" /> 
			<input type="hidden" id="menusName" name="menusName" placeholder="菜单列表" /> 
			<label for="fieldModule">显示菜单：</label>
			<div class="form-group">
				<!-- // 组织树模型，固定格式   -->
				<ul id="menuTree" class="ztree">
				</ul>
			</div>
		</div>
</form>
</body>
</html>