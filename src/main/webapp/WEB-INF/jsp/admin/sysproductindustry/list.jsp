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
				title:'是否删除所选'+grid1.getSelectedValue('id').length+'条产品行业信息?',
				yes:function(index){
					layer.close(index);
					var ids = grid1.getSelectedValue('id');
					var opts ={
						url:'delete.jhtml',
						data :{"ids":ids.join(',')},
						callBack:function(data,errcode){
							if(errcode!=="000000"){
								qy.suc({title:'删除产品行业成功！'});
								grid1.loadData();
							}else{
								qy.fail({title:'删除产品行业失败！'});
							}
						}
					}
					qy.ajax(opts);
				}
			});
		});
	       
		$("#add").click(function(){
			var opts = {
				title: "添加产品行业信息",
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
				title: "修改产品行业信息",
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
			    {name:"industryName",caption:"产品行业名称",hidden:false,type:"text"},
			    {name:"industryValue",caption:"产品行业名称对应值",hidden:false,type:"text"},
			    {name:"sort",caption:"产品行业排序",hidden:false,type:"text",sort:true},
			    {name:"id",caption:"ID",hidden:true,type:"text"},
			    {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
				  		 events :[{
			  				  type:'click',
			  				  select:'#view',
			  				  handle:function(){
		  				  			var id =grid1.getCurrentRowValue(this,'id');
		  							var opts = {
		  									title:"查看产品行业详细信息",
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
<style>
.input {
    height: 30px;
    line-height: 28px;
    border: 1px solid #ccc;
    padding: 0 5px;
}
</style>
</head>
<body>

	<div class="block ">
		<h1 class="title">
			产品行业信息
		</h1> 
		  <div style="height:50px;" class="form-line form-line2">
            <form style="height:50px;" id="queryForm" action="">
                <div style="padding-left:68px;" class="group col-lg-5f1 col-md-5 unmust">
                    <label style="margin-right:10px;width:90px;" class="form-label" for="name">产品行业名称</label>
                    <input type="search" name="industryName" id="industryName" class="information_input input" />
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