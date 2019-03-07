<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet/less" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/from.less" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>任务分配</title>
<script type="text/javascript">
require(["grid","select","panel","less"],function(g){
	
		
		$("#distribution").click(function(){
            var opts = {
                title: "指派",
                url : "distributionTask.jhtml",
                data:{id:grid1.getSelectedValue("id")[0]},
                yes : function(){
                	$("#inputForm").submit();
                },
                cancel:function(){}
            }
            qy.panel(opts);
        });
		
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"query0.jhtml",
				columns :[
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    {name:"taskType",caption:"任务类型",hidden:false,type:"text"},
				    {name:"dataSourceId",caption:"数据来源",hidden:false,type:"text"},
				    {name:"dataName",caption:"数据名称",hidden:false,type:"text"},
				    {name:"standardName",caption:"标准名称",hidden:false,type:"text"},
				    {name:"dataUnit",caption:"数据单位",hidden:false,type:"text"},
				    {name:"conversionFactor",caption:"单位转换系数",hidden:false,type:"text"},
				    {name:"industryCode",caption:"行业代码",hidden:false,type:"text"},
				    {name:"pscc",caption:"源分类代码",hidden:false,type:"text"},
				    {name:"status",caption:"状态",hidden:false,type:"text",valueSet:"每日任务清单【任务状态】"},
				    //{name:"taskAccount",caption:"任务分配员",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    //{name:"disposeAccount",caption:"任务处理员",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    //{name:"checkAccount",caption:"任务复核员",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    {name:"operate",caption:"查看",isHtml:true,html:'<a id="history"  style="cursor: pointer;"><i class="iconfont  icon-chakan1">历史记录</i></a>',
                    	events :[{
                               type:'click',
                               select:'.icon-chakan1',
                               handle:function(){
                            	   var id= grid1.getCurrentRowValue(this,'id');
                            	   var opts = {
       		                             title: "历史记录",
       		                             url : "../tasklist/taskhistory.jhtml",
       		                          	 width:"80%",
       		       						 height:"962px",
       		                             data:{"id":id},
       		                             btn:[],
       		                             yes : function(){
       		                                
       		                             },
       		                             cancel:function(){}
       		                         }
       		                         qy.panel(opts);
                            	   
                                 //var id= grid1.getCurrentRowValue(this,'id');
                                 //location.href = "../tasklist/taskhistory.jhtml?id="+id;
                               }
                             },
                    	]}, 
					]
			}
            grid1 =  g.grid(opts);
            grid1.loadData();
        
    })
</script>
</head>
<body>
	<div class="block    ">
		<h1 class="title">待分配任务</h1>
		<div class="form-line form-line2">
			<form id="queryForm" action="">
				<div id='queryPanel' class="pull-left form-inline"></div>
			</form>
		</div>
	</div>
	<div class="grid-tools">
		<button id="distribution" class="btn-denger" data-grid="tab1" data-enable="2">任务指派</button>
	</div>
	<div id="tab1"></div>
</body>
</html>