<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改用户管理</title>
</head>

<script type="text/javascript">
	require([ "validate", "ajaxform", "ztree", "less" ], function(a) {
		var treeId;
		var tree;
		/* ztree设置 */
		var zTree;
		var zNodes;
		var setting = {
			view : {
				filter : true, //是否启动过滤
				expandLevel : 0, //展开层级
				showFilterChildren : true, //是否显示过滤数据孩子节点
				showFilterParent : true, //是否显示过滤数据父节点
				showLine : false,//是否显示节点之间的连线
				showIcon : false, //是否显示图标
				selectedMulti : true
			//设置是否允许同时选中多个节点
			},
			check : {
				chkStyle : "checkbox",//复选框类型
				enable : true,//每个节点上是否显示 CheckBox 
			},
			async : {
				enable : true,
				type : 'post',
				autoParam : [ 'id' ], //异步加载时需要自动提交父节点属性的参数
				dataType : 'json',
			},
			data : {

				simpleData : {//简单数据模式
					enable : true,
					idKey : "id",
					pIdKey : "pid",
					rootPId : 0,
				}
			},
		};
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		var userType=$("#userType","#inputForm").select({name:"userType",defaultValue:"${systemUser.userType}"});
		var belongProject=$("#belongProject","#inputForm").select({url: base+"/admin/project/queryAll.jhtml",name:'invitationCode',codeValue:'name',code:'id',defaultValue:"${systemUser.project.id}"});
		//提交表单
		$("#inputForm").validate({
			rules : {},
			messages : {},
			submitHandler : function(form) {
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var nodes = treeObj.getCheckedNodes(true);
				var roleList = [];
				
				$.each(nodes, function(k, v) {
					roleList.push(v.id)
				})
				$('#selectRole').val(roleList.join(','))
				var options = {
					url : 'update.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('修改成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('修改失败', {
							icon : 3
						});
					}
				};
				$(form).ajaxSubmit(options);
				return false;
			}
		});
		var userId = $('.layui-layer-page #id').val();
		var roles = $('.layui-layer-page #role').val();
		
		qy.ajax({
			url : '../systemrole/queryRole.jhtml', //url 
			data : {
				id : 0
			},
			dataType : "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
			callBack : function(data) {
				$("[name='role']").each(function(){
		            var role = $(this).val();
		            //alert(role);
		           // data = data.replace('"id":'+role +',','"id":'+role+',checked:true,');
		            do{
     					data = data.replace('"id":' + role + ',','"id":temp,');
     				}while(data.search('"id":' + role + ',')+1);
     				do{
     					data = data.replace('"id":temp,','"id":' + role + ',checked:true,');
     				}while(data.search('"id":temp,')+1);
		        })
				
				zNodes = data;
				tree = $.fn.zTree.init($("#menuTree"), setting, eval('(' + zNodes + ')'));
			},
			error : function(msg) {
				alert("失败");
			}
		});
		/*$.ajax({
			type : 'POST',
			url : '../systemrole/queryRole.jhtml', //url 
			data : {
				id : 0
			},
			dataType : "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
			ContentType : "application/json; charset=utf-8",
			success : function(dataid) {
				zNodes = dataid;
				tree = $.fn.zTree.init($("#menuTree"), setting, eval('('
						+ zNodes + ')'));
				 $.ajax({
							type : 'POST',
							url : '../systemrole/queryRole.jhtml',
							data : {
								id : userId,
								selectRole : roles
							},
							dataType : "text",
							ContentType : "application/json; charset=utf-8",
							success : function(data) {
								for (var i = 0; i < dataid.length; i++) {
									do {
										data = data.replace('"id":' + dataid[i]
												+ ',', '"id":temp,');
									} while (data.search('"id":' + dataid[i]
											+ ',') + 1);
									do {
										data = data.replace('"id":temp,',
												'"id":' + dataid[i]
														+ ',checked:true,');
									} while (data.search('"id":temp,') + 1);

								}
								;
								zNodes = data;
								tree = $.fn.zTree.init($("#menuTree"), setting,
										eval('(' + zNodes + ')'));
							},
							error : function(msg) {
								alert("失败");
							}
						}); 
			},
			error : function(msg) {
				alert("失败");
			}
		});*/

	});
</script>

<body>

	<form id="inputForm">
		<div class="container">
			<input type="hidden" name="id" value="${systemDepartment.id }" />
			<div class="form-line">
				<label for="name">部门名称 </label> 
				<input type="text" id="name" name="name" placeholder="部门名称" value="${systemDepartment.name}" required />
			</div>
			<div class="form-line">
				<label for="name">上级部门 </label> 
				<input type="text" id="pid" name="pid" placeholder="上级部门" value="${systemDepartment.pid}"  />
			</div>
			<div class="form-line">
				<label for="role">选择角色 </label> 
				<c:forEach items="${systemDepartment.roles}" var="c">
    					<input type="hidden" id="role" name="role" placeholder="角色选择" value="${c.id}"/>
				</c:forEach>
				<input type="hidden" id="selectRole" name="selectRole" placeholder="角色选择" />
				<div class="ztreeF">
					<!--组织树模型，固定格式  -->
					<ul id="menuTree" class="ztree">
					</ul>
				</div>
			</div>
			<div class="form-line">
				<label for="email">描述</label>
				<textarea class="textarea" name="description">${systemDepartment.description }</textarea>
			</div>
		</div>


	</form>
</body>
</html>