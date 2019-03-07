<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/form/form.css">
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
    <meta charset="UTF-8">
    <title>修改角色</title>
</head>

<script type="text/javascript">
var zTree;
require(["validate","ajaxform","ztree","less"],function(a){
	var treeId;
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
	      rootPId: 0,
	    }
	  },
	}; 
	
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules: {
			},
			messages:{
			},
			submitHandler:function(form){
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var nodes = treeObj.getCheckedNodes(true);
				var permList = [];
				$.each(nodes, function(k, v) {
					permList.push(v.id)

				})
				$('#selectPerm').val(permList.join(','))
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
		
	            
		var roleId=$('.layui-layer-page #id').val();
		var perms=$('.layui-layer-page #selectPerm').val();
		 $.ajax({  
             type: 'POST',  
             url: '../systemmenu/queryMenus.jhtml',  //url 
             data: {id:roleId},  
             dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
             ContentType: "application/json; charset=utf-8",  
             success: function (dataid) { 
             	$.ajax({  
             		type: 'POST',  
             		url: '../systemmenu/querymenu.jhtml',
             		data: {id:roleId,selectPerm:perms},  
             		dataType: "text", 
             		ContentType: "application/json; charset=utf-8",  
             		success: function (data) { 
             			for (var i = 0; i < dataid.length; i++) {
             				do{
             					data = data.replace('"id":' + dataid[i] + ',','"id":temp,');
             				}while(data.search('"id":' + dataid[i] + ',')+1);
             				do{
             					data = data.replace('"id":temp,','"id":' + dataid[i] + ',checked:true,');
             				}while(data.search('"id":temp,')+1);
             				

             			};
             			zNodes = data;
             			tree =  $.fn.zTree.init($("#menuTree"), setting, eval('(' + zNodes + ')'));  
             		},  
             		error: function (msg) {
             			console.log(msg);
             			alert("失败");  
             		}  
             	}); 
             },  
             error: function (msg) {
             	console.log(msg);
                 alert("失败");  
             }  
         }); 

		
	  
	      
});
</script>

<body>
	<form id="inputForm">
		<div class="container">
			<div class="form-line">
				<input type="text" id="name" name="name" placeholder="角色名称" value="${systemRole.name}" required/>
				<input type="hidden" id="id" name="id" value="${systemRole.id}"/>
				<input type="hidden" id="selectPerm" name="selectPerm" placeholder="" value="${systemMenus.perms}" />
				<input id="select_all" value="no" type="hidden">
			</div>
		</div>
		<div class="form-group">
			<!-- // 组织树模型，固定格式   -->
			<ul id="menuTree" class="ztree">
			</ul>
		</div>
	</form>
</body>
</html>