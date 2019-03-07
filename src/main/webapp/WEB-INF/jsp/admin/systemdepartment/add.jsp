<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加部门管理</title>
</head>

<script type="text/javascript">
	require([ "validate", "ajaxform", "ztree", "less" ], function(a) {
		var treeId;
		var tree
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
		
		$("#userType","#inputForm").select({name:"userType"});
		$("#belongProject","#inputForm").select({url: base+"/admin/project/queryAll.jhtml",name:'invitationCode',codeValue:'name',code:'id'});
		//提交表单
		$("#inputForm").validate({
			rules : {
				username: {
	                    remote: {
                        url: base+"/common/checkUserNameIsRepeat.jhtml",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式   
                        data: {                     //要传递的数据
                        	userName: function() {
                                return $("input[name=username]").val();
                            }
                        }
                    } 
                },
                phone: {
                    number:true,
                    rangelength:[11,11]
                },
                email:"email"
			},
			messages : {
				username: {
                    remote: '用户名已存在！'
                },
                phone:"请输入正确的电话号码"   
			},
			submitHandler : function(form) {
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var nodes = treeObj.getCheckedNodes(true);
				var roleList = [];
				$.each(nodes, function(k, v) {
					roleList.push(v.id)

				})
				$('#selectRole').val(roleList.join(','))
				var options = {
					url : 'save.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('添加成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('添加失败', {
							icon : 3
						});
					}
				};
				$(form).ajaxSubmit(options);
				return false;
			}
		});
		qy.ajax({
			type : 'POST',
			url : '../systemrole/queryRole.jhtml', //url 
			data : {
				id : 0
			},
			dataType : "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
			ContentType : "application/json; charset=utf-8",
			callBack : function(data) {
				zNodes = data;
				tree = $.fn.zTree.init($("#menuTree"), setting, eval('('
						+ zNodes + ')'));
			},
			error : function(msg) {
				alert("失败");
			}
		});
		

	});
</script>

<body>
	<form id="inputForm">
		<div class="container">
			<div class="form-line">
				<label for="name">部门名称 </label> 
				<input type="text" id="name" name="name" placeholder="部门名称" required />
			</div>
			<div class="form-line">
				<label for="name">上级部门 </label> 
				<input type="text" id="pid" name="pid" placeholder="上级部门" required />
			</div>
			
			<div class="form-line">
				<label for="role">选择角色 </label> 
				<input type="hidden" id="selectRole" name="selectRole" placeholder="角色选择" />
				<div class="ztreeF">
					<!--组织树模型，固定格式  -->
					<ul id="menuTree" class="ztree">
					</ul>
				</div>
			</div>
			<div class="form-line">
				<label for="email">描述</label>
				<textarea class="textarea" name="description"></textarea>
			</div>
			
		</div>

	</form>
</body>
</html>