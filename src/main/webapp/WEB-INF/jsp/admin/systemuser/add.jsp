<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加用户管理</title>
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
			callback: {
				onCheck: zTreeOnCheck
			},
		};
		
		function zTreeOnCheck(event, treeId, treeNode) {
			 // alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
		}
		
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		
		$("#userType","#inputForm").select({name:"userType"});
		//$("#belongProject","#inputForm").select({url: base+"/admin/project/queryAll.jhtml",name:'invitationCode',codeValue:'name',code:'id'});
		$("#projectId","#inputForm").select({name:'projectId'});
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
				
				//部门
				var treeObjDepartment = $.fn.zTree.getZTreeObj("menuDepartmentTree");
				var nodesDepartment = treeObjDepartment.getCheckedNodes(true);
				var roleListDepartment = [];
				$.each(nodesDepartment, function(k, v) {
					roleListDepartment.push(v.id)

				})
				$('#selectDepartment').val(roleListDepartment.join(','))
				var options = {
					url : 'save.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						if(data.code == 20000){
							layer.msg('添加成功！', {
								icon : 6
							})
							layer.closeAll('page');
							grid1.loadData();
						}else{
							layer.msg(data.content, {
								icon : 3
							});
						}
						
						
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
			url : '../systemdepartment/queryDepartment.jhtml', //url 
			data : {
				id : 0
			},
			dataType : "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
			callBack : function(data) {
				$("[name='department']").each(function(){
		            var department = $(this).val();
		           // alert(department);
		           // data = data.replace('"id":'+role +',','"id":'+role+',checked:true,');
		            do{
     					data = data.replace('"id":' + department + ',','"id":temp,');
     				}while(data.search('"id":' + department + ',')+1);
     				do{
     					data = data.replace('"id":temp,','"id":' + department + ',checked:true,');
     				}while(data.search('"id":temp,')+1);
		        })
				
				zNodes = data;
				tree = $.fn.zTree.init($("#menuDepartmentTree"), setting, eval('(' + zNodes + ')'));
				
			},
			error : function(msg) {
				alert("失败");
			}
		});
		
		
		$.ajax({
			type : 'POST',
			url : '../systemrole/queryRole.jhtml', //url 
			data : {
				id : 0
			},
			dataType : "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
			ContentType : "application/json; charset=utf-8",
			success : function(data) {
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
				<label for="username">用户名 </label> <input type="text" id="username"
					name="username" placeholder="用户名" required />
			</div>
			<div class="form-line">
				<label for="department">所属部门 </label> 
				<c:forEach items="${systemUser.departments}" var="c">
    					<input type="hidden" id="department" name="department"  value="${c.id}"/>
				</c:forEach>
				<input type="hidden" id="selectDepartment" name="selectDepartment" placeholder="部门选择"
					value="${systemUser.departments}" />
				<div class="ztreeF">
					<!--组织树模型，固定格式  -->
					<ul id="menuDepartmentTree" class="ztree">
					</ul>
				</div>
			</div>
			<div class="form-line">
				<label for="email">邮箱 </label> <input type="text" id="email" name="email" placeholder="邮箱"/>
			</div>
			<div class="form-line">
				<label for="userType">用户类型 </label> 
				<div id="userType" class="selectF" data-code="用户类型"></div>
			</div>
			
			<div class="form-line">
				<label for="phone">手机 </label> <input type="text" id="phone"
					name="phone" placeholder="手机" />

			</div>
			<input type="hidden" id="id" name="id" value="${systemUser.id}" />
			<div class="form-line">
				<label for="role">选择角色 </label> <input type="hidden"
					id="selectRole" name="selectRole" placeholder="角色选择" />
				<div class="ztreeF">
					<!--组织树模型，固定格式  -->
					<ul id="menuTree" class="ztree">
					</ul>
				</div>
			</div>
			<div class="form-line">
				<label for="belongProject">所属项目 </label> 
				<div id="projectId" class="selectF" data-code="全局【项目】"></div>
			</div>
		</div>

	</form>
</body>
</html>