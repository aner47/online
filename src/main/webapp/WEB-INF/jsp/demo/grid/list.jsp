<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<title></title>
<style>
#tabs {
	margin-top: 40px;
	height: 600px;
	width: 100%;
}
</style>
<script>
	require([ "grid" ], function(g) {
		var data = {"content":[{"id":18,"createDate":1492088072000,"modifyDate":1492088072000,"version":0,"name":"Demo-Grid","perms":null,"url":"/demo/grid/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":15,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"权限管理","perms":null,"url":"12","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":16,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"demo示例","perms":null,"url":null,"icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":17,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"tab","perms":null,"url":"/demo/tab/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":14,"createDate":1491882241000,"modifyDate":1491882241000,"version":0,"name":"用户管理","perms":null,"url":"/admin/systemuser/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":13,"createDate":1491881926000,"modifyDate":1491881926000,"version":0,"name":"角色管理","perms":null,"url":"/admin/systemrole/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":12,"createDate":1491881812000,"modifyDate":1491882609000,"version":1,"name":"字典管理","perms":"","url":"/admin/dictionary/list.jhtml","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":11,"createDate":1491881228000,"modifyDate":1491881230000,"version":0,"name":"菜单管理","perms":"admin:systemmenu:list,admin:systemmenu:viewPage,admin:systemmenu:query,admin:systemmenu:addPage,admin:systemmenu:querymenu,ad","url":"/admin/systemmenu/list.jhtml","icon":"icon-caidanguanli","pid":15,"sort":0},{"id":1,"createDate":1491880520000,"modifyDate":1491880522000,"version":0,"name":"系统管理","perms":null,"url":null,"icon":null,"pid":0,"sort":0},{"id":18,"createDate":1492088072000,"modifyDate":1492088072000,"version":0,"name":"Demo-Grid","perms":null,"url":"/demo/grid/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":15,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"权限管理","perms":null,"url":"12","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":16,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"demo示例","perms":null,"url":null,"icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":17,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"tab","perms":null,"url":"/demo/tab/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":14,"createDate":1491882241000,"modifyDate":1491882241000,"version":0,"name":"用户管理","perms":null,"url":"/admin/systemuser/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":13,"createDate":1491881926000,"modifyDate":1491881926000,"version":0,"name":"角色管理","perms":null,"url":"/admin/systemrole/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":12,"createDate":1491881812000,"modifyDate":1491882609000,"version":1,"name":"字典管理","perms":"","url":"/admin/dictionary/list.jhtml","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":11,"createDate":1491881228000,"modifyDate":1491881230000,"version":0,"name":"菜单管理","perms":"admin:systemmenu:list,admin:systemmenu:viewPage,admin:systemmenu:query,admin:systemmenu:addPage,admin:systemmenu:querymenu,ad","url":"/admin/systemmenu/list.jhtml","icon":"icon-caidanguanli","pid":15,"sort":0},{"id":1,"createDate":1491880520000,"modifyDate":1491880522000,"version":0,"name":"系统管理","perms":null,"url":null,"icon":null,"pid":0,"sort":0},{"id":18,"createDate":1492088072000,"modifyDate":1492088072000,"version":0,"name":"Demo-Grid","perms":null,"url":"/demo/grid/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":15,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"权限管理","perms":null,"url":"12","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":16,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"demo示例","perms":null,"url":null,"icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":17,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"tab","perms":null,"url":"/demo/tab/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":14,"createDate":1491882241000,"modifyDate":1491882241000,"version":0,"name":"用户管理","perms":null,"url":"/admin/systemuser/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":13,"createDate":1491881926000,"modifyDate":1491881926000,"version":0,"name":"角色管理","perms":null,"url":"/admin/systemrole/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":12,"createDate":1491881812000,"modifyDate":1491882609000,"version":1,"name":"字典管理","perms":"","url":"/admin/dictionary/list.jhtml","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":11,"createDate":1491881228000,"modifyDate":1491881230000,"version":0,"name":"菜单管理","perms":"admin:systemmenu:list,admin:systemmenu:viewPage,admin:systemmenu:query,admin:systemmenu:addPage,admin:systemmenu:querymenu,ad","url":"/admin/systemmenu/list.jhtml","icon":"icon-caidanguanli","pid":15,"sort":0},{"id":1,"createDate":1491880520000,"modifyDate":1491880522000,"version":0,"name":"系统管理","perms":null,"url":null,"icon":null,"pid":0,"sort":0},{"id":18,"createDate":1492088072000,"modifyDate":1492088072000,"version":0,"name":"Demo-Grid","perms":null,"url":"/demo/grid/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":15,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"权限管理","perms":null,"url":"12","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":16,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"demo示例","perms":null,"url":null,"icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":17,"createDate":1491882342000,"modifyDate":1491882342000,"version":0,"name":"tab","perms":null,"url":"/demo/tab/list.jhtml","icon":"icon-shujuchaxun","pid":16,"sort":0},{"id":14,"createDate":1491882241000,"modifyDate":1491882241000,"version":0,"name":"用户管理","perms":null,"url":"/admin/systemuser/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":13,"createDate":1491881926000,"modifyDate":1491881926000,"version":0,"name":"角色管理","perms":null,"url":"/admin/systemrole/list.jhtml","icon":"icon-shujuchaxun","pid":15,"sort":0},{"id":12,"createDate":1491881812000,"modifyDate":1491882609000,"version":1,"name":"字典管理","perms":"","url":"/admin/dictionary/list.jhtml","icon":"icon-shujuchaxun","pid":1,"sort":0},{"id":11,"createDate":1491881228000,"modifyDate":1491881230000,"version":0,"name":"菜单管理","perms":"admin:systemmenu:list,admin:systemmenu:viewPage,admin:systemmenu:query,admin:systemmenu:addPage,admin:systemmenu:querymenu,ad","url":"/admin/systemmenu/list.jhtml","icon":"icon-caidanguanli","pid":15,"sort":0},{"id":1,"createDate":1491880520000,"modifyDate":1491880522000,"version":0,"name":"系统管理","perms":null,"url":null,"icon":null,"pid":0,"sort":0}],"total":18,"totalPages":1,"pageNumber":1,"pageSize":10};
		var opts = {
				tab:"tab1",
				simpleData:data,
				data:{pageSize:10,pageNumber:0},
				columns :[
						    {name:"name",caption:"菜单名称",hidden:false,type:"text"},
						    {name:"url",caption:"链接地址",hidden:false,type:"text"},
						    {name:"perms",caption:"授权信息",hidden:false,type:"text"},
						    {name:"icon",caption:"图标",hidden:false,type:"text"},
						    {name:"pid",caption:"上级菜单",hidden:false,type:"text"},
						    {name:"sort",caption:"菜单排序",hidden:false,type:"text"},
						    {name:"id",caption:"ID",hidden:true,type:"text"},
						    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
				    	}
					]
			}
			var grid1 =  g.grid(opts);
			grid1.loadSimpleData();

	})
</script>
</head>
<body>
	<div id="tab1"></div>
</body>
</html>