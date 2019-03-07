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
		var taskid = $("#taskid").val();
		
		var opts = {
				checkbox:false,
				num:false,
				tab:"tab2",
				data:{pageSize:10,pageNumber:0,id:taskid},
				url:"taskhistoryQuery.jhtml",
				
				columns :[
					{name:"status",caption:"状态",hidden:false,type:"text",valueSet:"每日任务清单【任务状态】"},
					{name:"oldModifyDate",caption:"操作时间",hidden:false,type:"date_time"},
					{name:"operating",caption:"操作记录",hidden:false,type:"text"},
					{name:"operatingPeople",caption:"操作人",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    {name:"description",caption:"处理意见",hidden:false,type:"text"},
                    {name:"currentPeople",caption:"当前处理人",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    
				    {name:"id",caption:"ID",hidden:true,type:"text"},
				    
				    {name:"taskType",caption:"任务类型",hidden:false,type:"text"},
				    {name:"dataSourceId",caption:"数据来源",hidden:false,type:"text"},
				    {name:"dataName",caption:"数据名称",hidden:false,type:"text"},
				    {name:"standardName",caption:"标准名称",hidden:false,type:"text"},
				    {name:"dataUnit",caption:"数据单位",hidden:false,type:"text"},
				    {name:"conversionFactor",caption:"单位转换系数",hidden:false,type:"text"},
				    {name:"industryCode",caption:"行业代码",hidden:false,type:"text"},
				    {name:"pscc",caption:"源分类代码",hidden:false,type:"text"},
				    //{name:"taskAccount",caption:"任务分配员",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    //{name:"disposeAccount",caption:"任务处理员",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    //{name:"checkAccount",caption:"任务复核员",hidden:false,type:"text",valueSet:"每日任务【人员】"},
                    
                    
					]
			}
            grid2 =  g.grid(opts);
            grid2.loadData();
        
    })
</script>
</head>
<body>
	<input type="hidden" id="taskid" value="${id}"/> 
	<div id="tab2"></div>
</body>
</html>