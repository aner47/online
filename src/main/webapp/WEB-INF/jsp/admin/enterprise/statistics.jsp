<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>填表类型统计</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/login/login.css">
    <link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
    <script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-1.9.1.min.js"></script>
    	
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/layer/layer.js"></script>
<%--  	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/qy/ajax/ajax.js"></script> --%>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rsa.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/base64.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/prng4.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rng.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/jsbn.js"></script>
 	<script src="<%=request.getContextPath()%>/resources/js/statistics/common.js"></script>
 	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
 	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
  </head>
  <style>
  	
  	body{
  		margin:0px;
  		padding:0px;
  	}
  	.area {
  		width:80%;
  		height:50%;
  		float:left;
  		margin-bottom: 14px;
  		padding-bottom: 14px;
  		border: 1px solid #cccccc;
  	}
  	#project{
  		margin-left:10px;
  		border:1px solid #ccc;
  		width:250px;
  		float:left;
  	}
  	#enterpriseType{
  		margin-left:10px;
  		border:1px solid #ccc;
  		width:250px;
  		float:left;
  	}
  	div._s1{
  		font-size:16px;
  	}
  	span.label{
  		padding-top:6px;
  		float:left;
  		font-size:16px;
  	}
  	.countMain{
  		margin-bottom:20px;
  	}
  	.infPara{
  		padding:0;
  		
  		margin-right: 30px;
  		width:280px;
  		float: left;
  	}
  	.areaModel{
  		margin-top:20px;
  		/*display:flex;*/
  		min-height: 80px;
  	}
  	.areaModel1{
  		/*flex:1;*/
  		width: 50%;
  		min-height: 80px;
  		float: left;
  		margin-bottom: 5px;
  	}
  	#provincesQuery,#cityQuery,#countyQuery,#industry_category_code_main,#industry_category_code_middle{
  		margin-left:10px;
  		border:1px solid #ccc;
  		width:200px;
  		float:left;
  		margin-right:20px;
  	}
  	
  	
  	.areaj{
  		width: 100%!important;
  		height: 64%!important;
  		border: 1px solid #DEDEDE;
  		border-top: none;
  		background: #fbfcff;
  		padding-top: 40px;
  		padding-bottom: 100px;
  		margin-bottom: 10px;
  	}
  	
  </style>
 
<body onkeypress=KeyDown(event) style="background:#fff;padding-top:10px;" >
	<div class="screenj" style="position: relative; z-index: 1212;">
			条件筛选
	</div>
	<div class="countMain clearfix countMainj">
			<div class="areaModel1">
				<span class="label prodectj">项目</span>
				<div id="project" data-code="全局【项目】" class=""></div>
			</div>
			<div class="areaModel1">
				<span class="label prodectj">填表类型</span>
				<div id="enterpriseType" data-code="企业【企业类型】" class=""></div>
			</div>
			<div class="areaModel1">
				<div class="infPara" style="height: 40px;">
					<div class="infParaCon">
						<span for="" class="infpLable timej">开始时间</span>
						<div class="infpCon">
							<input class="input" id="putOnTime" name="putOnTime" type="text" 
							placeholder="开始时间" style="width:160px; height: 31px;"required="true" onfocus="this.blur()" />
						</div>
					</div>
				</div>
				<div class="infPara" style="height: 40px;">
					<div class="infParaCon">
						<span for="" class="infpLable timej">结束时间</span>
						<div class="infpCon">
							<input class="input" id="stopTime" name="stopTime" type="text" 
							placeholder="结束时间" style="width:160px; height: 31px;" readonly  />
						</div>
					</div>
				</div>
				<button id = "project_user_Search_time" class="button" style="background: #2a6ff1;">搜索</button>
			</div>
	</div>
	<div class="clearfix"></div>
    <div class="screenj">
		填表类型统计
    </div>
	<div id="progress" class="area"></div><br />
	
</body>
 <script type="text/javascript">
	require(["echarts","select","date", "ajax","commonBase"],function(echarts){
		
		var option = {
				toolbox: {
			        feature: {
			            magicType: {
			                type: ['stack', 'tiled']
			            },
			            dataView: {},
			            saveAsImage: {
			                pixelRatio: 2
			            }
			        }
			    },
				tooltip : {
					trigger: 'axis',
		        	axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            	type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		            },
		            formatter: function (params, ticket, callback){

		            	var str = '';
		            	var val;

		            	var total = totalArr[params[0].dataIndex];
		            	var name = params[0].name;

		            	str += name;
		            	str += ' (合计:' + (total ? toThousands(total) : 0) + ')';
		            	str += '<br/>';

		            	for (var i = 0; i < params.length; i++) {
		            		val = total ? fixedNum(params[i].value * 100/total,2) : 0;
		            		str += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+params[i].color+';"></span>'+params[i].seriesName + ':' + params[i].value +'（'+ val +'%）<br/>';
		            	};
		            	return str;
		            }
		        },
		        color:['#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622'],
		        legend: {
		        	data:[]
		        },
		        grid: {
		        	left: '3%',
		        	right: '4%',
		        	bottom: '3%',
		        	containLabel: true
		        },
		        xAxis : [
		        {
		        	type : 'category',
		        	data : []
		        }
		        ],
		        yAxis : [
		        {
		        	type : 'value',
		        	// max:100,
		        	axisLabel: {
		        		// formatter: "{value}%"
		        	}
		        }
		        ],
		        series : []
		    };
		
		var progress = echarts.init(document.getElementById('progress'));
	    $(window).resize(function(){
	    	progress.resize();
	    });	
	    
		query('','','','NORMAL');
		
		var project = $("#project").select({name:"project",filter: true,});
		var enterpriseType = $("#enterpriseType").select({name:"enterpriseType",filter: true,});
		
		var putOnTime = $("#putOnTime").jeDate({
            format:"YYYY-MM-DD",
            isTime:false,
            zIndex:999999900,
            
        })
        var stopTime = $("#stopTime").jeDate({
            format:"YYYY-MM-DD",
            isTime:false,
            zIndex:999999900,
            
        })
		
		$("#project_user_Search_time").on("click", function() {
			progress.clear();
			query(project.getSelectValue(),putOnTime.getValue(),stopTime.getValue(),enterpriseType.getSelectValue());
		})
		
		function query(projectId,start,end,enterpriseType){
			qy.ajax({
				url:base+"/admin/enterprise/statisticsQuery.jhtml",
				data:{projectId:projectId,start:start,end:end,enterpriseType:enterpriseType},
				callBack:function(data){
					console.log(data);
					showByEcharts(data.county_name);
				}
			})
		}
		
		
		
		// 通过 echarts 将获取数据显示 
		function showByEcharts(data){
			data = data || [];
			totalArr = [];
			// 状态 STATUS 转中文
			var statusDict = {notSubmit:'未提交',alreadySubmit:'已提交',noApprove:'审核未通过',approve:'审核通过'};
			for (var i = 0; i < data.length; i++) {
				data[i]
				if(objC(data[i],'taskStatus')){
					data[i].taskStatus = statusDict[data[i].taskStatus] || data[i].taskStatus;
				}
			};
			// 生成 legend 的数据和 xAxis 的数据
			var legendData = [],legendDataDict = {},dataByLegend = {};
			var xAxisData = [],xAxisDataDict = {};
			for (var i = 0; i < data.length; i++) {
				// xAxis
				if (objC(data[i],"county")){
					if(!xAxisDataDict[data[i].county]) {
						xAxisDataDict[data[i].county] = true;
						xAxisData.push(data[i].county);
						dataByLegend[data[i].county] = {};
						if(data[i].taskStatus){
							dataByLegend[data[i].county][data[i].taskStatus]=data[i];
						}
					}else{
						if(data[i].taskStatus){
							dataByLegend[data[i].county][data[i].taskStatus]=data[i];
						}
					}
				};
				// legend
				if (objC(data[i],"county") && objC(data[i],"taskStatus") && !legendDataDict[data[i].taskStatus]) {
					legendDataDict[data[i].taskStatus] = true;
					legendData.push(data[i].taskStatus);
				};
			};
			// 生成 series 的数据
			var seriesData = [];
			for (var i = 0; i < legendData.length; i++) {
				seriesData[i] = {name:legendData[i],type:'bar',data:[],dataTemp:[],stack: '进度'};
				for (var j = 0; j < xAxisData.length; j++) {
					seriesData[i].data[j] = objC(dataByLegend[xAxisData[j]],legendData[i],'count') || 0;
					totalArr[j] = (totalArr[j] || 0) + seriesData[i].data[j];
				};
			};

			// 数字转百分比
			// for (var i = 0; i < seriesData.length; i++) {
			// 	seriesData[i]
			// 	for (var j = 0; j < seriesData[i].dataTemp.length; j++) {
			// 		seriesData[i].dataTemp[j]
			// 		seriesData[i].data[j] = (seriesData[i].dataTemp[j] && totalArr[j]) ? fixedNum(seriesData[i].dataTemp[j]*100/totalArr[j],2) : 0;
			// 	};
			// };


			option.legend.data = legendData;
			option.xAxis[0].data = xAxisData;
			option.series = seriesData;

			progress.setOption(option);

			console.log(JSON.stringify(legendData));
			console.log(JSON.stringify(xAxisData));
			console.log(JSON.stringify(seriesData));
		}
		
  
	/*监听键盘 事件 */
	function KeyDown(event)
	{
	  if (event.keyCode == 13)
	  {
	    event.returnValue=false;
	    event.cancel = true;
	    Form1.btncommit.click();
	  }
	}
	
	})
</script>
</html>