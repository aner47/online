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
				    {name:"name",caption:"菜单名称",hidden:false,type:"text"},
				    {name:"url",caption:"链接地址",hidden:false,type:"text"},
				    {name:"perms",caption:"授权信息",hidden:false,type:"text"},
				    {name:"icon",caption:"图标",hidden:false,type:"text"},
				    {name:"pid",caption:"上级菜单",hidden:false,type:"text",sort:true},
				    {name:"sort",caption:"菜单排序",hidden:false,type:"text",sort:true},
				    {name:"id",caption:"ID",hidden:true,type:"text"},
			    	{name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
				  		 events :[{
			  				  type:'click',
			  				  select:'#view',
			  				  handle:function(){
		  				  			var id =grid1.getCurrentRowValue(this,'id');
		  							var opts = {
		  									title:"查看菜单详细信息",
		  									url : "viewPage.jhtml?id="+id,
		  									cancel:function(){}
		  								}
		  								qy.panel(opts);
		  				  			
		  				  			
			  				  }
		  				  	}]
				    	}
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
                    <label class="form-label" for="name">菜单名称</label> <input
                        type="search" name="name" id="name" class="information_input" />
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