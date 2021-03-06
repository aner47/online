<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加项目类型</title>
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
	      rootPId: 19,
	    }
	  },
	};
	
	queryMenu(19);
	function queryMenu(pid){
		$.ajax({  
	        type: 'POST',  
	        url: '../systemmenu/querymenuByPid.jhtml',  //url 
	        data: {pid:pid},  
	        dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
	        ContentType: "application/json; charset=utf-8",  
	        success: function (data) { 
	        	data = data.replace(/"id":/g ,'checked:true,"id":');
	            zNodes = data;  
	            tree =  $.fn.zTree.init($("#menuTree"), setting, eval('(' + zNodes + ')'));  
	        },  
	        error: function (msg) {
	        	console.log(msg);
	           // alert("失败");  
	        }  
	    }); 
	}
	
	
	
	enterpriseMenu={"SIMPLE":47,"NORMAL":19,"SINGLE_BOILER":54,"CONSTRUCTION_SITE":71,
			"PETROL_STATION":77,"DRY_CLEAR":81,"BREAKDOWN_SERVICE":84,"BEASTS_BIRDS":88,"CATERING":120,"GENERAL":141};
	
	var enterpriseType = $("#enterpriseType","#inputForm").select({name:"enterpriseType",zIndex:9,defaultValue:"NORMAL",change:function(event,value){
		queryMenu(enterpriseMenu[value]);
	}}); 
	/* var projectType = $("#projectType","#inputForm").select({name:"projectType",isCustom:true,zIndex:8,change:function(event,value){
		
	}});  */
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
				enterpriseType:{
					required:true
				},
				projectType:{
					required:true
				}
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
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
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
    	<input type="hidden" name="projectId" value="${projectId}"/>
   		<div class="form-line">
            <label for="projectId" >填表类型 ：</label>
            <div id="enterpriseType" class="selectF" data-code="企业【企业类型】" ></div>
        </div>
		<div class="form-line">
            <label for="enterpriseName" >子项目名称</label>
            <!-- <div id="projectType" class="selectF" data-code="项目管理【子项目名称】" ></div> -->
            <input type="text" id="projectType" name="projectType"  required/> 
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