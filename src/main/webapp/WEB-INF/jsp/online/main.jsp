<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>在线填报系统</title>
		    <script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZwvKDkc8AosMeMixrVVDfEqK"></script>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/online/main/main.css" />
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/statistics/common.js"></script>
</head>
<style>
	.active{
		background:#fff;
	}
	body{
		opacity: 1;
	}
	/*全屏*/
.fullScreen{
}

.fullScreen::after{
  content: "全屏";
}

.fullScreen.active::after{
  content: "取消全屏";
}
header .company_name{
	position:absolute;
	top:30px;
	left:250px;
	width:auto;
	padding: 0 15px;
}
header .enterprise_button{
	position:absolute;
	top:30px;
	left:550px;
	width:auto;
	padding: 0 15px;
}
</style>
<script>
	var tempData={};
	tempData.project=${project};

	require(["tree","layer","jqueryui","panel","ajax","commonBase"],function() {
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
			//浏览器全屏下捕获esc事件
			window.onresize = function(){
				if(!checkFull()){
					isfullScreen=false;
					$('.fullScreen').removeClass('active')
				}
			} 
			function checkFull(){
				var isFull =  document.fullscreenEnabled || window.fullScreen || document.webkitIsFullScreen || document.msFullscreenEnabled;
				//to fix : false || undefined == undefined
				if(isFull === undefined) isFull = false;
				return isFull;
			}
		})()
		var data = [];
		top.codeValue = {};
		<c:forEach items="${menus}" var="menu">
		data.push({id:'${menu.id}',name:'${menu.name}',pid:'${menu.pid}',url:'${menu.url}',"icon":"${menu.icon}",sort:"${menu.sort}"})
	</c:forEach>
		
		top.enterpriseMenu={"SIMPLE":47,"NORMAL":19,"SINGLE_BOILER":54,"CONSTRUCTION_SITE":71,
				"PETROL_STATION":77,"DRY_CLEAR":81,"BREAKDOWN_SERVICE":84,"BEASTS_BIRDS":88,"CATERING":120,"GENERAL":141}
		
	$('#logout').click(function() {
		qy.sure({
			title:'是否退出?',
			yes: function(index){
				 qy.ajax({
					url :base+"/web/logout.jhtml",  
					type : "post",
					dataType:"json",
					callBack:function(data,code){
						location.href = "login.jhtml";
						document.cookie = "putProductionYear=; expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
					},
					error:function(data,code){
						layer.close(index);
						qy.fail2({title:'退出失败！'});
					}

				}) 
			} 
		})
	});
	if(data.length >0){
		// 左侧菜单树的构建
		var tree = $('.leftMenu').tree({
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
	var userType = "${principal.userType}"
	if(userType == "investigator"){
		$("#enterpriseList").show();
		$("#enterpriseBaseInfo").hide();
	}else{
		$("#enterpriseList").hide();
		$("#enterpriseBaseInfo").show();
	}
	
	
	$("#enterpriseList").click(function(){
		<%-- location.href = "<%=request.getContextPath()%>/web/main.jhtml"; --%>
		tree.loadData(data,{id:rootId,show:false});
		$("#company").hide();
		$("#enterprise_button_div").hide();
	})
	var rootId = "${menusRoot}"
	var menuData = data;
	tree.loadData(data,{id:rootId,show:false});
	
	top.loadMenu = function(root,childId){
		tree.loadData(data,{id:root,show:false,childId:childId});
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
	// 用户中心
	$('header .user').on('click',function(){
		$('.userset').toggleClass('active');
		event.stopPropagation()
	})
	$('body').on('click',function(){
		$('.userset').removeClass('active');
	})
	// 基本信息
	$('header .user .userset #enterpriseBaseInfo').on('click',function(){
		$('#myIframe').attr("src",base+'/web/enterprise/updatePage.jhtml');
	})
	// 修改密码
	$('header .user .userset #pasChange').on('click',function(){
		var opts = {
			title: "修改密码",
			width: "567px",
			height: "310px",
			url : base+"/web/modifiyPassword.jhtml",
			data:"",
			yes : function(){
				$("#modifyinput").submit();
			},
			cancel:function(){}
		}
		qy.panel(opts);
	})
	// 修改状态
	$('header  #enterprise_button').on('click',function(){
		var id = $('header #enterpriseid').val();
		var enterpriseType = $('header #enterpriseType').val();
		
		layer.msg('请确定所有选项已填选，提交后将不能进行修改？', {
			  time: 0 //不自动关闭
			  ,btn: ['确定', '取消']
			  ,area: ['280px', '150px']		//宽，高
			  ,yes: function(index){
				  qy.ajax({
						url: base+"/web/enterprise/updatestatus.jhtml",
						data:{enterpriseId:id,enterpriseType:enterpriseType},
						callBack:function(data){
							if(data.code == '20000'){
								tree.loadData(menuData,{"id":rootId,show:false});
								layer.close(index);
							}else{
								qy.fail2({title:data.content});
							}
						
						}
					})
			    
			    
			  }
			});
		
		 /* if (confirm("你确定提交吗？")) {  
	            alert("点击了确定");  
	        }  
	        else {  
	            alert("点击了取消");  
	        }  */ 
		 
		<%--  --%>
		
		
	})
});
</script>
<style>
header {
	padding-right: 40px;
}
.overflow_hide {
	overflow: hidden;
	white-space: nowrap;
	text-overflow:ellipsis;
}
</style>
<body>
	<header>
		<div class="company_name" id="company" style="font-size:12px"></div>
		<input type="hidden" id="enterpriseid" value="企业id" ></div>
		<input type="hidden" id="enterpriseType" ></div>
		
		<div class="title"></div>
		<div style="margin-right:0;" class="exit" id="logout">退出</div>
		<div class="exit fullScreen"></div>
		<div  class="exit" id="enterpriseList">企业列表</div>
		
		<div class="user">
			<div class="userlogo"><i class="iconfont icon-gerenzhongxin"></i></div>
			
			<div class="username overflow_hide">${principal.username}</div>
			<i class="iconfont icon-xiaodansanjiao"></i>
			<ul class="userset">
				<!-- <li id="enterpriseBaseInfo">基本信息</li> -->
				<li id="pasChange">修改密码</li>
			</ul>
		</div>
		
		<div  id="enterprise_button_div" hidden="true">
			<button class="enterprise_button_div" id="enterprise_button">提交</button>
		</div>
	</header>
	<div class="content">
		<div class="leftFrame">
			<div class="leftContent">
				<div class="leftMenu"></div>
			</div>
		</div>
		
		<iframe width="100%" height="100%" name="myIframe" id="myIframe" src="" frameborder=0 marginwidth=0 marginheight=0></iframe>
	</div>
</body>
</html>