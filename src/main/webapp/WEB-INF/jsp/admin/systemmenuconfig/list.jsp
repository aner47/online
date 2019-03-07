<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/from.less" />
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
	
	enterpriseMenu={"SIMPLE":47,"NORMAL":19,"SINGLE_BOILER":54,"CONSTRUCTION_SITE":71,
			"PETROL_STATION":77,"DRY_CLEAR":81,"BREAKDOWN_SERVICE":84,"BEASTS_BIRDS":88,"CATERING":120,"GENERAL":141};
		enterpriseMenuEnd={"SIMPLE":61,"NORMAL":60,"SINGLE_BOILER":99,"CONSTRUCTION_SITE":102,
				"PETROL_STATION":108,"DRY_CLEAR":112,"BREAKDOWN_SERVICE":115,"BEASTS_BIRDS":118,"CATERING":123,"GENERAL":171};
		
		var enterpriseType = $("#enterpriseType").select({name:"enterpriseType",zIndex:9,change:function(event,value){
			systemMenuFrontId.loadData("pid:" + enterpriseMenu[value]);
			systemMenuEndId.loadData("pid:" + enterpriseMenuEnd[value]);
		}});
		
	var systemMenuFrontId = $("#systemMenuFrontId").select({filter: true,name:"systemMenuFrontId",zIndex:8});
	var systemMenuEndId = $("#systemMenuEndId").select({name:"systemMenuEndId",zIndex:7});
	
	
		$('#delete').click(function(e){//删除
			qy.del({
				title:'是否删除所选'+grid1.getSelectedValue('id').length+'条菜单信息?',
				yes:function(index){
					layer.close(index);
					var ids = grid1.getSelectedValue('id');
					var opts ={
						url:'delete.jhtml',
						data :{"ids":ids.join(',')},
						callBack:function(data,errcode){
							if(errcode!=="000000"){
								qy.suc({title:'删除菜单成功！'});
								grid1.loadData();
							}else{
								qy.fail({title:'删除菜单失败！'});
							}
						}
					}
					qy.ajax(opts);
				}
			});
		});
	       
		$("#add").click(function(){
			var opts = {
				title: "添加菜单信息",
				url : "addPage.jhtml",
				data:"",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改菜单信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0]},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
			//查询
		$('#query').click(function(){
			var data = {};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data[v.name] = v.value;	
				
			})
			grid1.loadData(data);
		});
	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
					{name:"enterpriseType",caption:"填表类型",hidden:false,type:"text",valueSet:"企业【企业类型】"},
				    {name:"systemMenuFrontId",caption:"前台菜单名称",hidden:false,type:"text",valueSet:"菜单管理"},
				    {name:"systemMenuEndId",caption:"后台菜单名称",hidden:false,type:"text",valueSet:"菜单管理"},
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
</head>
<body>

	<div class="block ">
		<h1 class="title">
			菜单信息
		</h1> 
		  <div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="name">填表类型</label> 
                    <div id="enterpriseType" class="selectF" data-code="企业【企业类型】" ></div>
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="name">前台</label> 
                    <div id="systemMenuFrontId" data-code="菜单管理" class="selectF"></div>
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="name">后台</label> 
                    <div id="systemMenuEndId" data-code="菜单管理" class="selectF"></div>
                </div>
                <div class="group unCol unmust btnF">
                    <a class="btn" id="query">查询</a>
                </div>
            </form>
        </div>
	</div>
	<div class = "grid-tools">
		<button id = "add" >添加</button>
		<button id = "update"  data-grid = "tab1" data-enable = "1" >修改</button>
		<button id = "delete" data-grid = "tab1" data-enable = "2" >删除</button>
	</div>
	<div id="tab1"  class="tab"></div> 
</body>
</html>