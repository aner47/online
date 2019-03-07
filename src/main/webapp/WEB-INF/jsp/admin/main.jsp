<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>后端系统管理</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/admin/main/main.css" />
	<script src="<%=request.getContextPath()%>/resources/js/statistics/common.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
</head>
<style>
	.active{
		background:#fff;
	}
	
</style>
<script>
var data = [];
	require(["echarts","tree","layer","panel","ajax","commonBase"],function(ec) {
		// 全屏
		(function(){
			var isfullScreen=false;
			$('.fullScreen').on('click',function(){
				$(this).toggleClass('active');
				if(!isfullScreen){
					launchFullScreen(document.documentElement);
					isfullScreen=!isfullScreen;
				}else{
					cancelFullscreen();
					isfullScreen=!isfullScreen;
				}
			})
		})()
		top.codeValue = {};
		top.enterpriseMenu={"SIMPLE":61,"NORMAL":60,"SINGLE_BOILER":99,"CONSTRUCTION_SITE":102,
				"PETROL_STATION":108,"DRY_CLEAR":112,"BREAKDOWN_SERVICE":115,"BEASTS_BIRDS":118,"CATERING":123,"GENERAL":171}
		<c:forEach items="${menus}" var="menu">
			data.push({id:'${menu.id}',name:'${menu.name}',pid:'${menu.pid}',url:'${menu.url}',"icon":"${menu.icon}","sort":"${menu.sort}"})
		</c:forEach>
			data = (function(){
				var arr = [],dict = {};
				for (var i = 0; i < data.length; i++) {
					if (!dict[data[i].id]) {
						arr.push(data[i]);
						dict[data[i].id] = true;
					};
				};
				return arr;
			})()
			
			
		//data.push({id:'a9999',name:'企业填报1级',pid:'0',url:'',"icon":"icon-shujuchaxun","sort":"0"});
		//data.push({id:'p35',name:'企业填报',pid:'a9999',url:'/admin/enterprise/list.jhtml',"icon":"icon-shujuchaxun","sort":"0"});
		//查询所有项目
		/* qy.ajax({
			 url :"../admin/project/queryAll.jhtml", 
			type : "post",
			dataType:"json",
			callBack:function(data1,code){
				for(var i= 0; i<data1.length;i++){
					data.push({id:'p'+data1[i].id,name:data1[i].name,pid:'a9999',url:'/admin/enterprise/list.jhtml?proejctCode='+data1[i].invitationCode,"icon":"icon-shujuchaxun","sort":"0"});
				}
			},
			error:function(data,code){
			}
		}) */
		//查询所有用户
		/* qy.ajax({
			 url :"../admin/systemuser/queryAll.jhtml", 
			type : "post",
			dataType:"json",
			callBack:function(data2,code){
				for(var i= 0; i<data2.length;i++){
					var proejctId = data2[i].project==null?'':data2[i].project.id;
					if(data2[i].userType == 'system'){
						data.push({id:'u'+data2[i].id,name:data2[i].username,pid:'p'+proejctId,url:"/admin/enterprise/list.jhtml?account="+data2[i].id+"&proejctId="+proejctId,"icon":"icon-shujuchaxun","sort":"0"})
					}else{
						data.push({id:'u'+data2[i].id,name:'&nbsp;&nbsp;&nbsp;'+data2[i].username,pid:'p'+proejctId,url:"/admin/enterprise/list.jhtml?account="+data2[i].id+"&proejctId="+proejctId,"icon":"icon-shujuchaxun","sort":"0"})
					}
					
				}
			},
			error:function(data,code){
			}
		}) */
	$('#logout').click(function() {
		var id = 'i' + (new Date()).getTime();
		var layuiClass = 'p' + id;
		layer.open({
			type: 1 ,
			title:'',
			width: '520px',
			height: '234px',
			content: '<style>.'+layuiClass+'{width: 520px !important;height: 234px;}.layui-layer-page .layui-layer-btn{bottom: 22px;}.layui-anim,.layui-layer-content{width: 520px !important;height: 234px;}.layui-layer-btn a{width: 166px;height: 47px;line-height: 45px;font-size: 19px;}.layui-layer-content h1{margin-top: 61px;}</style><h1 id="'+id+'">是否退出?</h1><script type="text/javascript">$("#'+id+'").parents(".layui-layer").addClass("'+layuiClass+'")<\/script>',
			btn: ['确定','取消'],
			yes: function(){
				 $.ajax({
					 url :"../admin/logout.jhtml", 
					type : "post",
					dataType:"json",
					success:function(data,code){
						location.href = "login.jhtml";
					},
					error:function(data,code){
						qy.fail({title:'退出失败！'});
					}

				}) 
			} ,
			btn2: function(){
				layer.closeAll();
			} ,
		})
	});


	// 移动端下，滚动事件
	(function(){
		if(!$('body').hasClass('mobileDevice')){
			return;
		}
		var start,end,arr=[],h,t,i,h1;
		$('.tiles').on('touchstart',function(){
			clearTimeout(t);
			$(this).stop();
			start=this.scrollTop;
			arr.length=0;
			$('.tiles .tile').each(function(i,v){
				if (i==0) {
					h1=v.offsetTop;
				};
				arr.push(v.offsetTop-h1);
			})
			h=$('.tiles').height()
		})
		$('.tiles').on('touchend',function(){
			var ele=this;
			t=setTimeout(function(){
				end=ele.scrollTop;
				i=Math.floor(end/h);
				var Hc=(end%h)/h;
				if(end > start){
					if(Hc < 0.05){
						i;
					}else{
						i++;
					}
				}else{
					if(Hc > 0.95){
						i++;
					}else{
						i;
					}
				}
				$(ele).animate({scrollTop:arr[i]},800);
			},200);
		})
	})()


	$('#updatepassword').click(function() {
		var opts = {
				title: "修改密码",
				width: "567px",
				height: "310px",
				url : "systemuser/modifiyPassword.jhtml",
				data:"",
				yes : function(){
					$("#modifyinput").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
	});
	if(data.length >0){
			// 左侧菜单树的构建
		var tree = $('.left-menu').tree({
			simpleDate:[],
			root:{show:false},
			events:{click:function(node,data){
				if(tree.getNodeType(data.id)==='leaf' && data.url != '#'){
					var index = layer.load(2);
					$('#myIframe').attr("src",base+ data.url);
					layer.close(index);
				}
			}
		}
		});
	}
	top.loadMenu = function(root){
		tree.loadData(data,{"id":root,show:false});
	}
	// 通过行业筛选菜单
	top.loadMenuByIndustry = function(root,menus){
		var tempData = [{id:'tempId',name:'行业分类',pid:'0',url:'',icon:"",sort:""}],tempObj;
		if (menus) {
			var arr = menus.split(',');
			var arrDict = {};
			for (var i = 0; i < arr.length; i++) {
				arrDict[arr[i]] = true;
			};
			for (var i = 0; i < data.length; i++) {
				if (arrDict[data[i].id]) {
					tempObj = deepCopy(data[i]);
					tempObj.pid = 'tempId';
					tempData.push(tempObj);
				};
			};
			root = 'tempId';
		}else{
			tempData = data;
		};
		tree.loadData(tempData,{id:root,show:false});
	}
	
		// 头部选项切换
		$('.navbar').on('click','li',function(){
			// 主页和其他页面的切换
			$('.navbar li').removeClass('actives');
			$(this).addClass('actives');
			/* if($(this).attr('id')==='tongji'){
				$('.wrap').addClass('pageFullScreen');
				$('.leftFrame').addClass('hide');
				$('#myIframe').attr("src",base+ '/admin/statistics/list.jhtml');
			}else{
				$('.wrap').removeClass('pageFullScreen');
				$('.leftFrame').removeClass('hide');
			} */
			if($(this).attr('id')==='homepage'){
				$('.wrap').addClass('hide');
				$('.homeContent').removeClass('hide');
				return;
			}else{
				$('.homeContent').addClass('hide');
				$('.wrap').removeClass('hide');
				if(data[0].id!=""){
					tree.loadData(data,{"id":$(this).data('id'),show:false});
				}
			}
		})

				
		// 左侧菜单显示和隐藏
		$('.drawBack').on('click',function(){
			$('.wrap').addClass('pageFullScreen');
		})
		$('.stretchOut').on('click',function(){
			$('.wrap').removeClass('pageFullScreen');
		})
		// 转屏
		$(window).resize(function(){
			$('.tile2 ul li.active').click();
			$('.tile4 ul li.active').click();
			thumb5();
		})
		// tile1
		function thumb1(tab1Data){
			var poll=['SO2','NOX','CO','PM10','PM25','BC','OC','VOC','NH3'];
			var arrV1=[];
			tab1Data.sort(function(a,b){
				return a.id - b.id;
			})
			for (var i = 0; i < tab1Data.length; i++) {
				if(tab1Data[i].pid==0){
					arrV1.push(tab1Data[i]);
					addChild(arrV1[arrV1.length-1],tab1Data);
				}
			};
			// 生成数据树
			function addChild(obj,arr){
				obj.data=[];
				for (var i = 0; i < arr.length; i++) {
					if(arr[i].pid==obj.id){
						obj.data.push(arr[i]);
					}
				};
			}
			// 生成tr
			var trI=0;
			function getTr(obj,trI){
				var str=$('<tr></tr>');
				if(obj.pid!=0){
					str.addClass('child');
				}
				if( trI%2 == 1 ){
					str.addClass('graybg');
				}
				var arr=[];
				for (var i = 0; i < poll.length; i++) {
					arr[i]=obj[poll[i]];
				};
				var max=Math.max.apply(null,arr);
				var j=arr.findIndex(function(v){return v==max});
				str.append('<td><span>'+obj.name+'</span></td>');
				for (var i = 0; i < arr.length; i++) {
					if(i==j){
						str.append('<td><span class="redFont">'+(parseInt(arr[i])||0)+'</span></td>');
					}else{
						str.append('<td><span>'+(parseInt(arr[i])||0)+'</span></td>');
					}
				};
				return str;
			}
			// 渲染tbody
			$('#thumb1 tbody').html('');
			for (var i = 0; i < arrV1.length; i++) {
				$('#thumb1 tbody').append(getTr(arrV1[i],trI));
				trI++;
				if(arrV1[i].data){
					for (var j = 0; j < arrV1[i].data.length; j++) {
						$('#thumb1 tbody').append(getTr(arrV1[i].data[j],trI));
						trI++;
					};
				}
			};
			// 生成合计
			function getFoot(){
				var obj={};
				obj.name='合计';
				for (var i = 0; i < poll.length; i++) {
					obj[poll[i]]=0;
					for (var j = 0; j < arrV1.length; j++) {
						obj[poll[i]]+=arrV1[j][poll[i]];
					};
				};
				return getTr(obj,0);
			}
			$('#thumb1 tfoot').html('').append(getFoot());
		}
		//thumb1(thumb1Data);
		// tile2
		function thumb2(str){
			var citymap = ec.init(document.getElementById('thumb2'));
			$(window).resize(function(){
				citymap.resize();
	        });
			var option = {
	       	// title:{text:'城市总量分布',x:'center',y:'top'},
	       	// color:colors,
	       	color: ['#3699d8','#e84b3e','#f29c11'],
	       	tooltip: {
	       		trigger: 'item',
	       		formatter:function(v){
	       			return v['seriesName']+" <br>"+v['name']+"："+v['data']+" 吨";
	       		}
	       	},
	       	toolbox:{
	       		show : true,
	       		orient: 'horizontal',
	       		x: 'right',
	       		y: 'top',
	       		feature : {
	       			restore : {show: true},
	       			saveAsImage : {show: true}
	       		}
	       	},

	       	dataZoom: {
	       		show: true,
	       		zoomLock: false,
	       		start: 0,
	       		end: 20
	       	},
	       	legend: {
	               // data:classification,
	               data: ['2013年','2014年','2015年'],
	               x:'center',
	               y:'top',
	               orient:'horizontal',
	               show: false
	           },
	           grid:{
	           	x:96,
	           	x2:50,
	           	y2:90
	           },
	           xAxis : [
	           {
	           	type : 'category',
	           	axisLabel:{show:true,interval:'0',rotate: -35},
	           	silent: true
	           }
	           ],
	           start:0,
	           yAxis : [
	           {
	           	type : 'value'
	           }
	           ],
	           series : []
	       	};
	       	var xAxis=[];
	       	for(var i=0;i<thumbCitys.length;i++){
	       		var city = thumbCitys[i];
	       		xAxis.push(city);
	       	}
	       	option.xAxis[0].data = xAxis;
	       	option.series.length=0;
	       	for (var i = 0; i < thumb2Data.length; i++) {
	       		option.series.push({
	       			name:thumb2Data[i].name,
	       			type: 'bar',
	       			"barGap" : '20%',
	       			data:thumb2Data[i].data[pollutionAbb[str]],
	       		})
	       	};
	       
	       	citymap.setOption(option);
	   }

	   $('.tile2 ul li').each(function(i,v){
	   	$(v).on('click',function(){
	   		$('.tile2 ul li').removeClass('active');
	   		$(this).addClass('active');
	   		thumb2($(this).text());
	   	})
	   })
	   //thumb2('SO2');


		// tile3
		function thumb3(i,str){
			$('.tile3 .thumb img').attr('src','<%=request.getContextPath()%>/resources/img/'+thumb3Data[i][pollutionAbb[str]]);
			setTimeout(function(){
				(
					$('.tile3 .thumb img').width()/$('.tile3 .thumb img').height()
					>= 
					$('.tile3 .thumb').width()/$('.tile3 .thumb').height()
					)
				? 
				$('.tile3 .thumb img').addClass('allW').removeClass('allH')
				: 
				$('.tile3 .thumb img').addClass('allH').removeClass('allW');
			},100)
		}
		// 当页面宽高发生变化时，图片自适应
		$(window).resize(function(){
			(
				$('.tile3 .thumb img').width()/$('.tile3 .thumb img').height()
				>= 
				$('.tile3 .thumb').width()/$('.tile3 .thumb').height()
				)
			? 
			$('.tile3 .thumb img').addClass('allW').removeClass('allH')
			: 
			$('.tile3 .thumb img').addClass('allH').removeClass('allW');
		});
		// 分页
		$('.tile3 ul.nav-tab li').each(function(i,v){
			$(v).on('click',function(){
				$('.tile3 ul.nav-tab li').removeClass('active');
				$(this).addClass('active');
				thumb3($(this).index(),$('.tile3 ul.column li.active').text());
			})
		})
		$('.tile3 ul.column li').each(function(i,v){
			$(v).on('click',function(){
				$('.tile3 ul.column li').removeClass('active');
				$(this).addClass('active');
				thumb3($('.tile3 ul.nav-tab li.active').index(),$(this).text());
			})
		})
		//thumb3(0,'SO2');
		if(!$('body').hasClass('mobileDevice')){
			$('.tile3 .thumb').on('click',function(){
				$('.tile3 .thumb').toggleClass('showImg');
			})
		}
		
		


		// tile4
		function thumb4(str){
			//饼图	         
			var legenddata = ['电力生产及供应','工业生产','机动车','农业源','溶剂使用','生物质燃烧','油气储运','餐饮'];     
			var pollutionPercent = ec.init(document.getElementById('thumb4')); 
			$(window).resize(function(){
				pollutionPercent.resize();
	        });
			option = {
				// title:{text:'行业占比分析',x:'center',y:'top'},
				color:colors,
				tooltip:{trigger: 'item',formatter: "{a} <br/>{b} : {c} 吨({d}%)"},
				legend:{show:false,data:legenddata},
				toolbox:{
					show : true,
					orient: 'horizontal',
					x: 'right',
					y: 'top',
					feature : {
						restore : {show: true},
						saveAsImage : {show: true}
					}
				}
			}
			var itemStyle = { 
				normal:{ 
					label:{ 
						show: true, 
						formatter: '{b} :\n{d}%' 
					}, 
					labelLine :{show:true}
				} 
			} ;
			var piedata = {
				oriData : [],
				SO2 : [],
				NOX : [], 
				CO : [],
				PM10 : [],
				PM25 : [],
				BC : [],
				OC : [],
				NH3 : [],
				VOC : []
			};
			piedata.format = function (oriData) {
				data.oriData = oriData;
				function pushData(key, sourceName, value) {
					piedata[key].push({value:value,name:sourceName});
				}
				for(var j=0;j<testData2.length;j++){
					var singleData = testData2[j];
					pushData('SO2',singleData['source'],singleData['SO2']);
					pushData('NOX', singleData['source'], singleData.NOx);
					pushData('CO', singleData['source'], singleData.CO);
					pushData('PM10', singleData['source'], singleData.PM10);
					pushData('PM25', singleData['source'], singleData['PM2.5']);
					pushData('BC', singleData['source'], singleData.BC);
					pushData('OC', singleData['source'], singleData.OC);
					pushData('NH3', singleData['source'], singleData.NH3);
					pushData('VOC', singleData['source'], singleData.VOCs);
				}
			}
			piedata.format(testData2);
			option.series = [{name:'', type:'pie',radius : '50%',center: ['50%', '50%'],itemStyle:itemStyle,data:piedata[pollutionAbb[str]]}];
			pollutionPercent.setOption(option);
		}
		$('.tile4 ul li').each(function(i,v){
			$(v).on('click',function(){
				$('.tile4 ul li').removeClass('active');
				$(this).addClass('active');
				thumb4($(this).text());
			})
		})
		//thumb4('SO2');
		


        // tile5
        function thumb5(){
        	// var legenddata = ['工艺过程源','民用','机动车','农业','餐饮','扬尘','固废处理','生物质燃烧','储存运输','机动车口溶剂使用'];
        	var legenddata = ['电力生产及供应','工业生产','机动车','农业源','溶剂使用','生物质燃烧','油气储运','餐饮'];
        	var myChart = ec.init(document.getElementById('thunb5'));
        	$(window).resize(function(){
        		myChart.resize();
	        });
			var option = {
        		// title:{text:'行业总量分布',x:'center',y:'top'},
        		// color:colors,
        		color: ['#8084EB','#E0E0E0','#BCC3C9','#FDDE55','#F6883F','#FA4742','#3AF2CE','#5ebbf8','#3ba5f3','#1495cf',],
        		tooltip: {
        			trigger: 'item',
        			formatter:function(v){
        				return v['seriesName']+" <br>"+v['name']+"："+v['data']+" 吨";
        			}
        		},
        		toolbox:{
        			show : true,
        			orient: 'horizontal',
        			x: 'right',
        			y: 'top',
        			feature : {
        				restore : {show: true},
        				saveAsImage : {show: true}
        			}
        		},
        		legend: {
        			data:legenddata,
        			width:450,
        			itemGap:15,
        			top:0,
        			orient:'horizontal',
        		},
        		grid:{
        			x:96,
        			x2:50,
        			y:60,
        			y2:50
        		},
        		xAxis : [
        		{
        			type : 'category',
        			axisLabel:{show:true,interval:'0'},
        			data : pollution,
        		}
        		],
        		yAxis : [
        		{
        			type : 'value'
        		}
        		],
        		series : []
        	};
        	var series =[];
        	var seriesitem;
        	for(var i=testData2.length-1;i>=0;i--){
        		seriesitem = {
        			"type":"bar",
        			"stack": '总量',
        			"barGap" : '20%',
        		}
        		var data = testData2[i]
        		seriesitem.name=data['source'];
        		seriesitem.data=[data['SO2'],data['NOx'],data['CO'],data['PM10'],data['PM2.5'],data['BC'],data['OC'],data['VOCs'],data['NH3']];
        		series.push(seriesitem);
        	}
        	option.series=series;
        	myChart.setOption(option);
        }
       // thumb5();
        /* jQuery.examine11 = function () {  
            	$("#inputForm #status").val("3");
    			var options = {
    					url : '../admin/enterprise/updatestatus.jhtml',
    					type : 'post',
    					dataType : 'json',
    					success : function(data) {
    						layer.msg('修改成功！', {
    							icon : 6
    						})
    						top.layer.closeAll('page');
    					},
    					error : function() {
    						layer.msg('修改失败', {
    							icon : 3
    						});
    					}
    				};
    				$("#inputForm").ajaxSubmit(options);
    				window.location.href = "../enterprise/enterpriseTaskList.jhtml";
    				loadMenu('188');
            
        }  */
        panel1 = function(id){
        	var opts = {
					title: "审核意见",
					width: "350px",
					height: "300px",
					url : "baseinformation/checkPage.jhtml",
					data:{"enterprieId": id},
					btn: ['通过', '不通过'],
					yes : function(){
						top.$("#inputForm #status").val("3");
						var parentBtnLEInsert = $(window.parent.document).find("#opinion");
						if (parentBtnLEInsert.val() != "") {
							layer.msg('不能点击通过', {
								icon : 3
							})
							return false;
						} else {
							var options = {
									url : '../admin/enterprise/updatestatus.jhtml',
									type : 'post',
									dataType : 'json',
									success : function(data) {
										layer.msg('审核通过！', {
											icon : 6
										})
										layer.closeAll('page');
										top.loadMenu('188');
										$("#myIframe").attr("src", "./enterprise/enterpriseTaskList.jhtml") 
									},
									error : function() {
										layer.msg('审核失败', {
											icon : 3
										});
									}
								};
								$("#inputForm").ajaxSubmit(options);
						}
							
					},
					btn2:function(){
						top.$("#inputForm #status").val("4");
						var parentBtnLEInsert = $(window.parent.document).find("#opinion");
						
						if (parentBtnLEInsert.val() != "") {
							
							var options = {
									url : '../admin/enterprise/updatestatus.jhtml',
									type : 'post',
									dataType : 'json',
									success : function(data) {
										layer.msg('审核不通过！', {
											icon : 6
										})
										layer.closeAll('page');
										top.loadMenu('188');
										$("#myIframe").attr("src", "./enterprise/enterpriseTaskList.jhtml") 
									},
									error : function() {
										layer.msg('审核失败', {
											icon : 3
										});
									}
								};
								$("#inputForm").ajaxSubmit(options);
						} else {
							layer.msg('审核意见未填写', {
								icon : 3
							});
							return false;
						}
					},
					cancel:function(){}
				}
        	if ($("#inputForm").length > 0) {
        		$("#enterprieId","#inputForm").val(id);
        		return false;
        	} else {
				qy.panel(opts);
        	}
        }
        
        $('.homeContent').addClass('hide');
		$('.wrap').removeClass('hide');
		var mid = $(".navbar li:eq(0)").attr("data-id");
		if(mid){
			tree.loadData(data,{"id":mid,show:false});
		}
		
});
	
</script>

<body>
	<!-- 头部 -->
	<%@ include file="/WEB-INF/jsp/admin/include/header.jsp" %> 
	<!-- 主体 -->
	<div class="wrap hide">
		<div class="leftFrame">
			<div class="stretchOut">
				<i class="iconfont icon-dansanjiaoright"></i>
			</div> 
			<div class="drawBack">
				<i class="iconfont icon-dansanjiaoleft"></i>
			</div>	
			<div class="leftContent">
				<div class="left-menu"></div>
			</div>
		</div>
		<iframe width="100%" height="100%" name="myIframe" id="myIframe" src="" frameborder=0 marginwidth=0 marginheight=0></iframe>
	</div>
	<!-- 主页 -->
	<div class="homeContent">
		<div class="fullScreen"></div>
		<!-- <ul class="tiles">
			<li class="tile tile1">
				<div class="tilepanel">
					<h1>2016排放源清单总量</h1>
					<div class="thumb" id="thumb1">
						<table>
							<thead>
								<tr>
									<th><span>排放量/吨</span></th>
									<th><span>SO<sub>2</sub></span></th>
									<th><span>NO<sub>x</sub></span></th>
									<th><span>CO</span></th>
									<th><span>PM<sub>10</sub></span></th>
									<th><span>PM<sub>2.5</sub></span></th>
									<th><span>BC</span></th>
									<th><span>OC</span></th>
									<th><span>VOCs</span></th>
									<th><span>NH<sub>3</sub></span></th>
								</tr>
							</thead>
							<tbody></tbody>
							<tfoot></tfoot>
						</table>
					</div>
				</div>
			</li>
			<li class="tile tile2">
				<div class="tilepanel">
					<h1>大气污染物排放地区贡献</h1>
					<div class="nav-thumb">
						<ul class="column">
							<li class="active">SO<sub>2</sub></li>
							<li>NO<sub>x</sub></li>
							<li>CO</li>
							<li>PM<sub>10</sub></li>
							<li>PM<sub>2.5</sub></li>
							<li>BC</li>
							<li>OC</li>
							<li>VOCs</li>
							<li>NH<sub>3</sub></li>
						</ul>
					</div>
					<div class="thumb" id="thumb2"></div>
				</div>
			</li>
			<li class="tile tile3">
				<div class="tilepanel">
					<h1>2016排放源清单分布</h1>
					<ul class="nav-tab">
						<li class="active">点源</li>
						<li>面源</li>
						<li>机动车</li>
					</ul>
					<div class="nav-thumb nav-thumb2">
						<ul class="column">
							<li class="active">SO<sub>2</sub></li>
							<li>NO<sub>x</sub></li>
							<li>CO</li>
							<li>PM<sub>10</sub></li>
							<li>PM<sub>2.5</sub></li>
							<li>BC</li>
							<li>OC</li>
							<li>VOCs</li>
							<li>NH<sub>3</sub></li>
						</ul>
					</div>
					<div class="thumb">
						<img src="" alt="">
					</div>
				</div>
			</li>
			<li class="tile tile4">
				<div class="tilepanel">
					<h1 title="2016大气污染物排放清单贡献">2016大气污染物排放清单贡献</h1>
					<div class="nav-thumb">
						<ul class="column">
							<li class="active">SO<sub>2</sub></li>
							<li>NO<sub>x</sub></li>
							<li>CO</li>
							<li>PM<sub>10</sub></li>
							<li>PM<sub>2.5</sub></li>
							<li>BC</li>
							<li>OC</li>
							<li>VOCs</li>
							<li>NH<sub>3</sub></li>
						</ul>
					</div>
					<div class="thumb" id="thumb4"></div>
				</div>
			</li>
			<li class="tile tile5">
				<div class="tilepanel">
					<h1>2016大气污染物排放分析</h1>
					<div class="thumb" id="thunb5"></div>
				</div>
			</li>
		</ul> -->
	</div>
</body>
<script>
	// 判断使用设备
	function browserRedirect() {
		var sUserAgent = navigator.userAgent.toLowerCase();
		var u= navigator.userAgent;
		var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
		var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
		var bIsMidp = sUserAgent.match(/midp/i) == "midp";
		var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
		var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
		var bIsAndroid = sUserAgent.match(/android/i) == "android";
		var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
		var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
		var mobile = u.match(/AppleWebKit.*Mobile.*/);
		var android = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;
		var ios = u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
		if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM || mobile || android || ios) {
			console.log('Mobile device');
			document.body.setAttribute("class", "mobileDevice");
		} else {
			console.log('pc');
		}
	}
	browserRedirect();
	// 全屏
	function launchFullScreen(element) {  
		if(element.requestFullScreen) {  
			element.requestFullScreen();  
		} else if(element.mozRequestFullScreen) {  
			element.mozRequestFullScreen();  
		} else if(element.webkitRequestFullScreen) {  
			element.webkitRequestFullScreen();  
		} else if(element.msRequestFullscreen){
			element.msRequestFullscreen();
		}
	}
	// 启动全屏模式  
	// launchFullScreen(document.documentElement); // 整个页面 
	// 取消全屏
	function cancelFullscreen() {  
		if(document.cancelFullScreen) {  
			document.cancelFullScreen();  
		} else if(document.mozCancelFullScreen) {  
			document.mozCancelFullScreen();  
		} else if(document.webkitCancelFullScreen) {  
			document.webkitCancelFullScreen();  
		} else if(document.msExitFullscreen){
			document.msExitFullscreen();
		}
	}
</script>

</html>