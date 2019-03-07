<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加角色</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","ztree","less"],function(a){
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
				var menuList= [];
				$.each(nodes,function(k,v){
					menuList.push(v.id)
				})
				$('#menuIds').val(menuList.join(','))
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
		$.ajax({  
            type: 'POST',  
            url: '../systemmenu/querymenu.jhtml',  //url 
            data: { id:0},  
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
	});
</script>

<body>
	<form id="inputForm">
		<div class="container">
			<div class="form-line">
				<input type="text" id="name" name="name" placeholder="角色名称" required/>
				<input type="hidden" id="menuIds" name="menuIds" placeholder="菜单列表" />
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