<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加行业菜单配置</title>
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
           ,empty: true
           ,change:function(event, value){
        	  // queryField(value);
           }});
	   
	 //行业类别（大类）
		var categoryMain =  $("#industryCategoryCodeMainQuery", "#inputForm").select({filter:true,name:"industryCategoryCodeMain",zIndex:8,param:"categoryLevel:MAIN",change:function(event, value){
	    	//var text = categoryMain.getSelectCaption();
	    	//$("input[name=industryCategoryNameMain]").val(text);
	    	categoryMiddle.clear();
	    	categoryMiddle.loadData("parent:"+value)
	    }});
		//行业类别（中类）
		var categoryMiddle =  $("#industryCategoryCodeMiddleQuery", "#inputForm").select({name:"industryCategoryCodeMiddle",zIndex:7,change:function(event, value){
	    	//var text = categoryMiddle.getSelectCaption();
	    	//$("input[name=industryCategoryNameMiddle]").val(text);
	    	categorySmall.clear();
	    	categorySmall.loadData("parent:"+value);
	    }});
		//行业类别（小类）
		var categorySmall =  $("#industryCategoryCodeSmallQuery", "#inputForm").select({name:"industryCategoryCodeSmall",zIndex:6,change:function(event, value){
	    //	var text = categorySmall.getSelectCaption();
	    	//$("input[name=industryCategoryNameSmall]").val(text);
	    }});
		
		
		function queryField(moduleId){
			 
		}
		$.ajax({  
	        type: 'POST',  
	        url: '../systemmenu/querymenuByPid.jhtml',  //url 
	        data: {pid:152},  
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
		
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
				enterpriseType:{
					required:true
				},
				industryCategoryCodeMain:{
					required:true
				},
			},
			messages:{
			},
			submitHandler:function(form){
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var nodes = treeObj.getCheckedNodes(true);
				console.log(nodes);
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
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		layer.msg('添加成功！', {icon: 6})
							layer.closeAll('page');
							grid1.loadData();
			        	}else{
			        		layer.msg(data.content, {icon: 3})
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
				<label for="name">填报类型：</label>
				<div id="enterpriseType" class="selF" data-code="企业【企业类型】" />

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