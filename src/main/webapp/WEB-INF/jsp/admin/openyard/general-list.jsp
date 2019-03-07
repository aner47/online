<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生产信息</title>
<script type="text/javascript">
	require([ "grid","hide", "select", "panel" ], function(g,hide) {
		$("#addBtn").click(function() {
			var opts = {
				title : "添加露天堆场信息",
				width : "646px",
				height : "710px",
				btn : [ '保存','取消' ],
				url : "addgeneralPage.jhtml",
				yes : function(index) {
					$("#openyard_form").submit();
					//layer.close(index);
				},
				btn3 : function() {
					$("#openyard_form").submit();
				},
				cancel : function() {
				}
			}
			qy.panel(opts);
		});

		$("#editBtn").click(function() {
			if ($(this).attr("disabled"))
				return;
			var opts = {
				title : "修改露天堆场信息",
				width : "646px",
				height : "710px",
				btn : [ '更新', '取消' ],
				url : "updategeneralPage.jhtml",
				data : {
					"id" : grid1.getSingleSelectedValue('id')
				},
				yes : function(index) {
					$("#openyard_form").submit();
				},
				cancel : function() {
				}
			}
			qy.panel(opts);
		});

		var opts = {
			tab : "tab",
			checkbox : false,
			num : false,
			data : {
				pageSize : 10,
				pageNumber : 0
			},
			url : "query.jhtml",
			columns : [ {
				name : "materialType",
				caption : "堆料类型",
				width : 80 / 5,
				hidden : false,
				type : "text"
			}, {
				name : "material",
				caption : "堆料材料",
				width : 80 / 5,
				hidden : false,
				type : "text"
			}, {
				name : "id",
				caption : "编号",
				width : 60 / 5,
				hidden : true,
				type : "text",
				prefix : "LD"
			}, {
				name : "loadAmount",
				caption : "堆场装卸总量（吨）",
				width : 81 / 5,
				hidden : false,
				type : "text"
			}, {
				name : "cargoTrips",
				caption : "每年物料运载车次（车）",
				width : 95 / 5,
				hidden : false,
				type : "text"
			}, {
				name : "carryAmount",
				caption : "每车运载量（吨/车）",
				width : 81 / 5,
				hidden : false,
				type : "text"
			}, {
				name : "area",
				caption : "料堆占地面积（平方米）",
				width : 110 / 5,
				hidden : false,
				type : "text"
			}, {
				name : "height",
				caption : "料堆最高高度（米）",
				width : 81 / 5,
				hidden : false,
				type : "text"
			}, /* {
				name : "moistureContent",
				caption : "物料含水率（%）",
				width : 70 / 5,
				hidden : false,
				type : "text"
			}, */ {
				name : "measure1",
				caption : "措施",
				width : 181 / 5,
				hidden : false,
				type : "text",
				format:function(value,data,handle){
					var str = [];
					if(data.measure1){
						str.push(data.measure1)
					}
					if(data.measure2){
						str.push(data.measure2)
					}
					if(data.measure3){
						str.push(data.measure3)
					}
					return str.join(",");
				}
			}, {
				name : "coalClosed",
				caption : "煤堆场是否封闭",
				width : 81 / 5,
				hidden : false,
				type : "text",
				valueSet : "是否"
			}
			, {
				name : "unloadCoalClosed",
				caption : "卸煤沟是否封闭",
				width : 81 / 5,
				hidden : false,
				type : "text",
				valueSet : "是否"
			}

			],
		}
		hide.gridHide(opts);
		grid1 = g.grid(opts);
		grid1.loadData();

		$('#delete').click(
				function(e) {//删除
				if($(this).attr("disabled")) return;
					qy.sure({
						title : '是否删除所选' + grid1.getSelectedValue('id').length
								+ '条菜单信息?',
						yes : function(index) {
							layer.close(index);
							var ids = grid1.getSelectedValue('id');
							var opts = {
								url : 'delete.jhtml',
								data : {
									"ids" : ids.join(',')
								},
								callBack : function(data, errcode) {
									if (errcode !== "000000") {
										qy.suc2({
											title : '删除成功！'
										});
										grid1.loadData();
									} else {
										qy.fail2({
											title : '删除失败！'
										});
									}
								}
							}
							qy.ajax(opts);
						}
					});
				});
		$("#fixed_return").on("click", function() {
			window.location.href = "../enterprise/enterpriseTaskList.jhtml";
			top.loadMenu('188');
		})
		$("#back").on("click", function() {
			window.location.href = "../enterprise/list.jhtml";
			top.loadMenu('2','31');
		})
	})
</script>
<style>
.block{
	padding-bottom: 120px;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-loutianweichang"></i> 露天堆场信息
			<c:if test="${principal.userType != 'system'}">
			<div id="back" class="titleBtn">返回</div>
			<div id="addBtn" class="titleBtn">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
			</c:if>
		</h1>
		<div id="tab" class="tab"></div>
		
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>