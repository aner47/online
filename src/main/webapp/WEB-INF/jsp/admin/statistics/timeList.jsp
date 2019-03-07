<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>登陆</title>
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/iconfont/iconfont.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/login/login.css">
   <link type="text/css" rel="stylesheet"
href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/admin/statistics/search.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-1.9.1.min.js"></script>
  	
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/qy/ajax/ajax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rsa.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/base64.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/prng4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/rng.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/rsa/jsbn.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/statistics/common.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
</head>
  <script type="text/javascript">
	require(["echarts","validate","select","date"],function(echarts){
		$("#putOnTime").jeDate({
            format:"YYYY-MM-DD",
            isTime:false,
            zIndex:999999900
        })
        $("#stopTime").jeDate({
            format:"YYYY-MM-DD",
            isTime:false,
            zIndex:999999900
        })
        /* 日期比较-开始  */
		function duibi(d1,d2)
		{
  			return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		}
		/* 日期比较-结束 */
        var provinces = $("#provincesQuery").select({name:"address.provinces.code",zIndex:6,change:function(event, value){
			city.clear();
        	city.loadData("parent:"+value);
        }});
        var city = $("#cityQuery").select({name:"address.city.code",initLoad:false,zIndex:5,clearEvent:function(){},change:function(event, value){
        	county.clear();
        	county.loadData("parent:"+value);
        }});
        var county = $("#countyQuery").select({name:"address.county.code",initLoad:false,zIndex:4,clearEvent:function(){},change:function(event, value){
        	
        }});
        
      //行业类别（大类）
		var categoryMain =  $("#industry_category_code_main").select({filter:true,name:"industry_category_code_main",zIndex:3,param:"categoryLevel:MAIN",change:function(event, value){
        	var text = categoryMain.getSelectCaption();
        	$("input[name=industry_category_code_main]").val(text);
        	categoryMiddle.clear();
        	categoryMiddle.loadData("parent:"+value)
        }});
		//行业类别（中类）
		var categoryMiddle =  $("#industry_category_code_middle").select({name:"industry_category_code_middle",zIndex:2,change:function(event, value){
        	var text = categoryMiddle.getSelectCaption();
        	$("input[name=industry_category_code_middle]").val(text);
        }}); 
        
		var project = $("#project").select({name:"proejctCode",codeValue:"name",code:"invitationCode",defaultValue:20000,zIndex:8,url:"../project/queryAll.jhtml",change:function(event, value){
			$.ajax({
				url:"<%=request.getContextPath() %>/admin/statistics/query.jhtml",
				data:{
					project:value
				},
				dataType:"json",
				success:function(data){
					setData(data);
				 
				}
			})
        }});
		// 基于准备好的dom，初始化echarts实例--地区统计
		var areaCounty=[];
		var areaCount=[];
        var area = echarts.init(document.getElementById('area'));
        $(window).resize(function(){
        	area.resize();
        });
        // 指定图表的配置项和数据--地区统计
		area.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '地区统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
	            {
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 40,
	                handleSize: 8
	            },
	        ],
		});
        //地区统计--end
        
        // 基于准备好的dom，初始化echarts实例--时间统计
		var timeDate=[];
		var timeCount=[];
        var time = echarts.init(document.getElementById('time'));
        $(window).resize(function(){
        	time.resize();
        });
        // 指定图表的配置项和数据--时间统计
		time.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
//				text: '时间统计',
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data:['数量']
			},
			xAxis:  {
				type: 'category',
				boundaryGap: false,
				data: []
			},
			yAxis: {
				type: 'value',
				axisLabel: {
					formatter: '{value}'
				}
			},
			series: [
				{	
				   name:'最大值',
				   type:'line',
				   markLine:true,
				   markPoint: {
					   data: [
					     {type: 'max', name: '最大值'},
					     {type: 'min', name: '最小值'}
					   ]
					},           
				},        
			],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
			dataZoom: [
			   {
				     type: 'slider',
				     show: true,
				     start: 0,
				     end: 50,
				     handleSize: 8
			    },
			],
		});
        //时间统计--end
        
        // 基于准备好的dom，初始化echarts实例--用户统计
		var userUser=[];
		var userCount=[];
        var user = echarts.init(document.getElementById('user'));
        $(window).resize(function(){
        	user.resize();
        });
        // 指定图表的配置项和数据
		user.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '用户统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
		    	{
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 30,
	                handleSize: 8
	            },
	        ],
		});
        
		 // 基于准备好的dom，初始化echarts实例--填报人统计
		var inquirerInquirer=[];
		var inquirerCount=[];
        var inquirer = echarts.init(document.getElementById('inquirer'));
        $(window).resize(function(){
        	inquirer.resize();
        });
        // 指定图表的配置项和数据
		inquirer.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '填报人统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
		    	{
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 50,
	                handleSize: 8
	            },
	        ],
		});
		
		// 基于准备好的dom，初始化echarts实例--填表类型统计
		var enterprise_type=[];
		var enterpriseTypeCount=[];
        var enterpriseType = echarts.init(document.getElementById('enterpriseType'));
        $(window).resize(function(){
        	enterpriseType.resize();
        });
        // 指定图表的配置项和数据--地区统计
		enterpriseType.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '填表类型统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
	            {
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 40,
	                handleSize: 8
	            },
	        ],
		});
        //填表类型统计--end
        
		// 基于准备好的dom，初始化echarts实例--详表行业大类
		var normal_Main=[];
		var normalMainCount=[];
        var normalMain = echarts.init(document.getElementById('normalMain'));
        $(window).resize(function(){
        	normalMain.resize();
        });
        // 指定图表的配置项和数据--详表行业大类统计
		normalMain.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '详表行业大类统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
	            {
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 40,
	                handleSize: 8
	            },
	        ],
		});
        //详表行业大类统计--end
        
        
		// 基于准备好的dom，初始化echarts实例--详表行业中类
		var normal_Middle=[];
		var normalMiddleCount=[];
        var normalMiddle = echarts.init(document.getElementById('normalMiddle'));
        $(window).resize(function(){
        	normalMiddle.resize();
        });
        // 指定图表的配置项和数据--详表行业中类统计
		normalMiddle.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '详表行业中类统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
	            {
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 40,
	                handleSize: 8
	            },
	        ],
		});
        //详表行业中类统计--end
        
		// 基于准备好的dom，初始化echarts实例--简表行业大类
		var simple_Main=[];
		var simpleMainCount=[];
        var simpleMain = echarts.init(document.getElementById('simpleMain'));
        $(window).resize(function(){
        	simpleMain.resize();
        });
        // 指定图表的配置项和数据--简表行业大类统计
		simpleMain.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '简表行业大类统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
	            {
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 40,
	                handleSize: 8
	            },
	        ],
		});
        //简表行业大类统计--end
        
        
		// 基于准备好的dom，初始化echarts实例--简表行业中类
		var simple_Middle=[];
		var simpleMiddleCount=[];
        var simpleMiddle = echarts.init(document.getElementById('simpleMiddle'));
        $(window).resize(function(){
        	simpleMiddle.resize();
        });
        // 指定图表的配置项和数据--简表行业中类统计
		simpleMiddle.setOption({
			color: ['#3699d8','#e84b3e','#f29c11'],
			title: {
		        text: '简表行业中类统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['数量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '数量',
		        type: 'bar',
		        data: []
		    }],
			toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		left:"80%",
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},
		    dataZoom: [
	            {
	                type: 'slider',
	                show: true,
	                start: 0,
	                end: 40,
	                handleSize: 8
	            },
	        ],
		});
        //简表行业中类统计--end
        
		// 异步加载数据
		$.get("<%=request.getContextPath() %>/admin/statistics/query.jhtml?project=20000").done(function (data) {
			setData(data);
		});
		
		
        
		function dataZoom(n,l){
			l = l ? l : 10;
			if(n>l){
				return [
		            {
		                type: 'slider',
		                show: true,
		                start: 0,
		                end: 40,
		                handleSize: 8
		            },
		        ];
			}else{
				return [
		            {
		                type: 'slider',
		                show: false,
		                start: 0,
		                end: 100,
		                handleSize: 8
		            },
		        ];
			}
		}
		
		
		
		function setData(data){
			// 填入数据--area--start
			areaCounty=[];
			areaCount=[];
			for(var i=0;i<data.area.length;i++){
				if(data.area[i].county!= null){
				areaCounty.push(data.area[i].county);
				areaCount.push(data.area[i].count);
				}
			}
		    area.setOption({
		        xAxis: {
		            data: areaCounty
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: areaCount
		        }],
				dataZoom: [
					   {
						     show: dataZoom(areaCounty.length,15),
					    },
					]
		    });
		 // 填入数据--area--end
		 
		 // 填入数据--time--start
		 	timeDate=[];
		 	timeCount=[];
			for(var i=0;i<data.date.length;i++){
				timeDate.push(data.date[i].date);
				timeCount.push(data.date[i].count)
			}
		    time.setOption({
		        xAxis: {
		            data: timeDate
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: timeCount
		        }],
				dataZoom: dataZoom(timeDate.length,15),
		    });
		 // 填入数据--time--end
		 
		 // 填入数据--user--start
		 userUser=[];
		 userCount =[];
			for(var i=0;i<data.user.length;i++){
				if(data.user[i].user!= null){
				userUser.push(data.user[i].user);
				userCount.push(data.user[i].count);
				}
			}
		    user.setOption({
		        xAxis: {
		            data: userUser
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: userCount
		        }],
				dataZoom: [
					   {
						     show: dataZoom(userUser.length,15),
					    },
					]
		    });
		 // 填入数据--user--end
		 
		 // 填入数据--inquirer--start
		 inquirerInquirer= [];
		 inquirerCount =[];
			for(var i=0;i<data.inquirer.length;i++){
				if(data.inquirer[i].inquirer!= null){
					inquirerInquirer.push(data.inquirer[i].inquirer);
					inquirerCount.push(data.inquirer[i].count)
				}
			}
			inquirer.setOption({
		        xAxis: {
		            data: inquirerInquirer
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: inquirerCount
		        }],
				dataZoom: [
					   {
						     show: dataZoom(inquirerInquirer.length,15),
					    },
					]
		    });
		 // 填入数据--inquirer--end
		 
		 // 填入数据--enterpriseType--start
			enterprise_type= [];
			enterpriseTypeCount =[];
			var map=[];
			map["NORMAL"]="详表";
			map["SIMPLE"]="简表";
			map["SINGLE_BOILER"]="单一锅炉";
			map["CONSTRUCTION_SITE"]="施工工地";
			map["PETROL_STATION"]="加油站";
			map["DRY_CLEAR"]="干洗";
			map["BREAKDOWN_SERVICE"]="汽修";
			map["BEASTS_BIRDS"]="畜禽养殖";
			map["CATERING"]="餐饮";
				for(var i=0;i<data.enterpriseType.length;i++){
					if(data.enterpriseType[i].enterprise_type!= null){
						enterprise_type.push(map[data.enterpriseType[i].enterprise_type]);
						enterpriseTypeCount.push(data.enterpriseType[i].count)
					}
				}
				enterpriseType.setOption({
			        xAxis: {
			            data: enterprise_type
			        },
			        series: [{
			            // 根据名字对应到相应的系列
			            name: '数量',
			            data: enterpriseTypeCount
			        }],
					dataZoom: [
						   {
							     show: dataZoom(enterprise_type.length,15),
						    },
						]
			    });
		 // 填入数据--enterpriseType--end
		 
		// 填入数据--normalMain--start
		 normal_Main=[];
		 normalMainCount=[];
			for(var i=0;i<data.normalMain.length;i++){
				if(data.normalMain[i].normalMain!= null){
					normal_Main.push(data.normalMain[i].normalMain);
					normalMainCount.push(data.normalMain[i].count)
				}
			}
			normalMain.setOption({
		        xAxis: {
		            data: normal_Main
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: normalMainCount
		        }],
				dataZoom: [
					   {
						     show: dataZoom(normal_Main.length,15),
					    },
					]
		    });
		 // 填入数据--normalMain--end
				 
		// 填入数据--normalMiddle--start
		 normal_Middle=[];
		 normalMiddleCount=[];
			for(var i=0;i<data.normalMiddle.length;i++){
				if(data.normalMiddle[i].normalMiddle!= null){
					normal_Middle.push(data.normalMiddle[i].normalMiddle);
					normalMiddleCount.push(data.normalMiddle[i].count)
				}
			}
			normalMiddle.setOption({
		        xAxis: {
		            data: normal_Middle
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: normalMiddleCount
		        }],
				dataZoom: [
					   {
						     show: dataZoom(normal_Middle.length,15),
					    },
					]
		    });
		 // 填入数据--normalMain--end
		 
		// 填入数据--simpleMain--start
		 simple_Main=[];
		 simpleMainCount=[];
			for(var i=0;i<data.simpleMain.length;i++){
				if(data.simpleMain[i].simpleMain!= null){
					simple_Main.push(data.simpleMain[i].simpleMain);
					simpleMainCount.push(data.simpleMain[i].count)
				}
			}
			simpleMain.setOption({
		        xAxis: {
		            data: simple_Main
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: simpleMainCount
		        }],
				dataZoom: [
					   {
						     show: dataZoom(simple_Main.length,15),
					    },
					]
		    });
		 // 填入数据--simpleMain--end
				 
		// 填入数据--simpleMiddle--start
		 simple_Middle=[];
		 simpleMiddleCount=[];
			for(var i=0;i<data.simpleMiddle.length;i++){
				if(data.simpleMiddle[i].simpleMiddle!= null){
					simple_Middle.push(data.simpleMiddle[i].simpleMiddle);
					simpleMiddleCount.push(data.simpleMiddle[i].count)
				}
			}
			simpleMiddle.setOption({
		        xAxis: {
		            data: simple_Middle
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '数量',
		            data: simpleMiddleCount
		        }],
				dataZoom: [
					   {
						     show: dataZoom(simple_Middle.length,15),
					    },
					]
		    });
		 // 填入数据--simpleMain--end
				 
		}
		
		
		
		
		
		
		//地区搜索--start
		$("#project_user_Search_area").click(function(){
			//判断是否选择项目
			if(project.getSelectCaption()=="-请选择-"){
				layer.msg('请选择项目',{icon:3});
				return false;
			}else{
				$.ajax({
					url:"<%=request.getContextPath() %>/admin/statistics/query.jhtml",
					data:{
						project:project.getSelectValue(),
						provinces:provinces.getSelectValue(),
						city:city.getSelectValue()
					},
					dataType:"json",
					success:function(data){
						// 填入数据--area--start
						areaCounty=[];
						areaCount=[];
						for(var i=0;i<data.area.length;i++){
							if(data.area[i].county!= null){
								areaCounty.push(data.area[i].county);
								areaCount.push(data.area[i].count);
							}
						}
					    area.setOption({
					        xAxis: {
					            data: areaCounty
					        },
					        series: [{
					            // 根据名字对应到相应的系列
					            name: '数量',
					            data: areaCount
					        }]
					    });
					 // 填入数据--area--end
					}
				});
			}
		});
		//地区搜索--end
		
		//时间搜索--start
		$("#handleSubmit").click(function(){
			//日期比较
			if(duibi($("#putOnTime").val(),$("#stopTime").val())){
				layer.msg('结束时间不能早于开始时间',{icon:3});
				return false;
			}else if(project.getSelectCaption()=="-请选择-"){
				layer.msg('请选择项目',{icon:3});
				return false;
			}else{
				$.ajax({
					url:"<%=request.getContextPath() %>/admin/statistics/query.jhtml",
					data:{
						project:project.getSelectValue(),
						provinces:provinces.getSelectValue(),
						city:city.getSelectValue(),
						county:county.getSelectValue(),
						startDate:$("#putOnTime").val(),
						endDate:$("#stopTime").val(),
						industry_category_code_main:categoryMain.getSelectValue(),
						industry_category_code_middle:categoryMiddle.getSelectValue(),
					},
					dataType:"json",
					success:function(data){
						setData(data);
						// 填入数据--time--start
					 	/* timeDate=[];
					 	timeCount=[];
						for(var i=0;i<data.date.length;i++){
							timeDate.push(data.date[i].date);
							timeCount.push(data.date[i].count)
						}
					    time.setOption({
					        xAxis: {
					            data: timeDate
					        },
					        series: [{
					            // 根据名字对应到相应的系列
					            name: '数量',
					            data: timeCount
					        }]
					    }); */
					 	// 填入数据--time--end
					}
				});
			}
		});
		//时间搜索--end
			
	
	})
  
  
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
</script>
<style>
body {
	width: 100%;
    min-height: 100%;
    margin-bottom: 14px;
    float: left;
    position: relative;
    background-color: #fff!important;
    border: 1px solid #e4ebf1;
    box-shadow: 0 0 21px -7px rgba(12, 51, 92, 0.4);
}
.label {
    float: left;
	width: 90px;
    padding-right: 4px;
    text-align: right;
    line-height: 30px;
}
.input {
    float: left;
	height: 30px;
    line-height: 28px;
    border: 1px solid #ccc;
    padding: 0 5px;
    width: calc(100% - 90px);
}
.searchInWrap {
	float: left;
	width: 25%;
	height: 40px;
}
.searchInWrap .projectdiv {
	float: left!important;
	width: calc(100% - 90px)!important;
	margin:0!important;
}
#project {
	margin: 0;
    border: 1px solid #ccc;
    width: calc(100% - 90px)!important;
    float: left;
}
h1.title {
	height: 49px;
    border-bottom: 1px solid #e7ebee;
    background-color: #f9fafc;
    line-height: 48px;
    font-size: 16px;
    font-weight: bold;
    position: relative;
    padding-left: 45px;
}
</style>
<body onkeypress=KeyDown(event)>
	<h1 class="title">条件筛选</h1>
		<div class="areaModel">
			<div class="infParaCon searchInWrap">
				<div class="label">项目</div>
				<div id="project" data-code="projectCode"></div>
			</div>
			<div class="infParaCon searchInWrap">
				<div for="" class="label">开始时间</div>
				<input type="text" class="input" id="putOnTime" name="putOnTime" required="true" onfocus="this.blur()"  />
			</div>
			<div class="infParaCon searchInWrap">
				<div for="" class="label">结束时间</div>
				<input type="text" class="input" id="stopTime" name="stopTime" readonly  />
			</div>
		</div>
		<div class="areaModel">
			<div class="searchInWrap">
				<div class="label">省</div>
				<div id="provincesQuery" data-code="地域" class="projectdiv selectF"></div>
			</div>
			<div class="searchInWrap">
				<div class="label">市</div> 
				<div id="cityQuery" data-code="地域" class="projectdiv selectF"></div>
			</div>
			<div class="searchInWrap">
				<div class="label">县</div> 
				<div id="countyQuery" data-code="地域" class="projectdiv selectF"></div>
			</div>
			<button id="handleSubmit" class="button projectdiv" style="margin-left:30px;">搜索</button>
		</div>
	<div class="clearfix"></div>
	 <h1 class="title">时间统计</h1>
	 <div id="area" hidden="true"></div>
	 <div id="time" class="areaj"></div>
	 <div class="clearfix"hidden="true"></div>
	 <div id="user" style="margin-top:100px;" hidden="true"></div>
	 <div id="inquirer" style="margin-top:100px;" hidden="true"></div>
	 <div class="clearfix"></div>
	 <div id="enterpriseType" style="margin-top:100px;" hidden="true"></div>
	 <div class="clearfix"></div>
	 <div id="normalMain" style="margin-top:100px;" hidden="true"></div>
	 <div id="normalMiddle" style="margin-top:100px;" hidden="true"></div>
	 <div class="clearfix"></div>
	 <div id="simpleMain" style="margin-top:100px;" hidden="true"></div>
	 <div id="simpleMiddle" style="margin-top:100px;" hidden="true"></div>
</body>

</html>