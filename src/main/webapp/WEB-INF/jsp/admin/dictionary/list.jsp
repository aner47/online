<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/from.less" />
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>字典管理</title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
		$("#add").click(function(){
			var opts = {
				title:"添加",
				url : "addPage.jhtml",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
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
				columns :[{name:"id",caption:"主键",hidden:true},
					  {name:"name",caption:"名称"},
					  {name:"beanName",caption:"服务beanName"},
					  {name:"enabled",caption:"可用",valueSet:"字典管理【状态】"},
					  {name:"createDate",caption:"创建时间",type:'date'},
					  {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i></a>',
					  		 events :[{
					  				  type:'click',
					  				  select:'#view',
					  				  handle:function(){
				  				  			var id =grid1.getCurrentRowValue(this,'id');
				  				  			location.href = "../dictionarydata/list.jhtml?dictionaryId="+id;
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
			字典管理信息
		</h1> 
		<div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="name">字典名称</label> 
                    <input type="search" name="name" id="name" class="information_input" style="width:100%;height:100%"/>
                </div>
                <div class="group unCol unmust btnF">
                    <a class="btn" id="query">查询</a>
                </div>
            </form>
        </div>
	</div>
	<div class = "grid-tools">
		<button id = "add" >添加</button>
	</div>
	<div id="tab1"  class="tab"></div> 
</body>
</html>