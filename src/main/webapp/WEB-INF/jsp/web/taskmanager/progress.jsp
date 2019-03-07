<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title></title>
	<style>
		.form{
			background: #F7FAFF;
		}
		.form-row{
			width: 100%;
			border: none;
			float: left;

		}
		.form-row .div{
			float:left;
			padding:10px;
		}
		.label{
			float:left;
			margin-right:10px;
			height:30px;
			line-height:30px;
		}
		.clear{
			clear:both;
		}
		.photo{
			display:none;
		}
		.remarks{
			height:100px;
			display:none;
		}
		.form-row .input{
			margin-top: 2px;
			border: 1px solid #d2d2d2;
			border-radius: 3px;
			height: 26px;
			line-height: 24px;
			padding: 0 4px;
			outline: 0;
		}
		.imgF{
			width: 68px;
			height: 80px;
			position: relative;
			float: left;
			cursor: pointer;
			text-align: center;
			color: #DBDBDB;
			background-color: #FAFAFA;
		}
		.imgF:hover{
			background-color: #267DFF;
			color: #ffffff;
		}
		.imgF::before{
			font-size: 42px;
			display: block;
			width: 100%;
			height: 58px;
			line-height: 58px;
		}
		.imgF::after{
			content:'添加照片';
			font-size: 12px;
		}
		.imgF .img{
			position: absolute;
			top: 0px;
			left: 0px;
			height: 80px;
			min-width: 68px;
			width: 68px;
		}
		.imgF.hasimg .img{
			width: auto;
		}
		#progress{
			width: 100%;
			height: 500px;
			padding-top: 20px;
			float: left;
		}
	</style>
	<script type="text/javascript">
require(["echarts","ajax","commonBase"],function(echarts){
	qy.ajax({
		url:base+"/web/taskmanager/progressQuery.jhtml",
		data:{},
		callBack:function(data){
			console.log(data);
			showByEcharts(data.entity);
		}
	})

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

    // 
    var totalArr = [];

	// 通过 echarts 将获取数据显示 
	function showByEcharts(data){
		data = data || [];
		totalArr = [];
		// 状态 STATUS 转中文
		var statusDict = {new:'新任务',complete:'完成',processed:'已处理',assigned:'已分配'};
		for (var i = 0; i < data.length; i++) {
			data[i]
			if(objC(data[i],'STATUS')){
				data[i].STATUS = statusDict[data[i].STATUS] || data[i].STATUS;
			}
		};
		// 生成 legend 的数据和 xAxis 的数据
		var legendData = [],legendDataDict = {},dataByLegend = {};
		var xAxisData = [],xAxisDataDict = {};
		for (var i = 0; i < data.length; i++) {
			// xAxis
			if (objC(data[i],"executor")){
				if(!xAxisDataDict[data[i].executor]) {
					xAxisDataDict[data[i].executor] = true;
					xAxisData.push(data[i].executor);
					dataByLegend[data[i].executor] = {};
					if(data[i].STATUS){
						dataByLegend[data[i].executor][data[i].STATUS]=data[i];
					}
				}else{
					if(data[i].STATUS){
						dataByLegend[data[i].executor][data[i].STATUS]=data[i];
					}
				}
			};
			// legend
			if (objC(data[i],"executor") && objC(data[i],"STATUS") && !legendDataDict[data[i].STATUS]) {
				legendDataDict[data[i].STATUS] = true;
				legendData.push(data[i].STATUS);
			};
		};
		// 生成 series 的数据
		var seriesData = [];
		for (var i = 0; i < legendData.length; i++) {
			seriesData[i] = {name:legendData[i],type:'bar',data:[],dataTemp:[],stack: '进度'};
			for (var j = 0; j < xAxisData.length; j++) {
				seriesData[i].data[j] = objC(dataByLegend[xAxisData[j]],legendData[i],'statusCount') || 0;
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
		
})
</script>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-loutianweichang1"></i> 任务进度
		</h1>
		<div id="progress" ></div>
	</div>
	
	
</body>
</html>