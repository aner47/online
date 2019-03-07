<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZwvKDkc8AosMeMixrVVDfEqK"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta charset="UTF-8">
<title>修改企业web</title>
</head>
<style>
#map {
	width: 100%;
	height: 400px;
}
</style>
<script type="text/javascript">
  require(["validate","upload","ajaxform","select"],function(a,upload){ 
	  	var latMaxj,latMinj,lonMaxj,lonMinj,cityCode,countyCode,returnCode;
	  	var countyName,cityName;
	
	
	var map = new BMap.Map("map"); // 创建Map实例
	var centerPoint = new BMap.Point(116.404, 39.915);
	if($("#latitude").val() != null && $("#latitude").val() != '' && $("#longitude").val() != null && $("#longitude").val() != ''){
		centerPoint = new BMap.Point($("#longitude").val(), $("#latitude").val());
		var marker = new BMap.Marker(centerPoint);
		map.addOverlay(marker);
	}
	map.centerAndZoom(centerPoint, 11);
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map},
		onSearchComplete: function(results){
			// 判断状态是否正确
			if (local.getStatus() == BMAP_STATUS_SUCCESS){
				if(results.getCurrentNumPois() > 0 ){
					map.centerAndZoom(results.getPoi(0).point);
					$("#latitude").val(results.getPoi(0).point.lat)
					$("#longitude").val(results.getPoi(0).point.lng)
				}else{
					qy.suc2({title:"地址不存在！"});
				}
			}else{
				qy.suc2({title:"地址不存在！"});
			}
		}
	});

   
    function addressChange(){
    	map.clearOverlays();
     	if($("#latitude","#inputForm").val() != "" && $("#longitude","#inputForm").val() != ""){
        	var point = new BMap.Point($("#longitude","#inputForm").val(),$("#latitude","#inputForm").val() );
        	var marker = new BMap.Marker(point);
        	map.addOverlay(marker);
        	map.panTo(point);
      	}
      
    }

	
		
	var provinces = $("#provinces").select({name:"address.provinces.code",defaultValue:'${enterprise.address.provinces.code}',change:function(event, value){
    		city.clear();
    		city.loadData("parent:"+value);
  		}});
   
   	//市code默认值
    var city = $("#city").select({name:"address.city.code","initLoad":false,
    	clearEvent:function(){county.clear();},change:function(event, value){
     		county.clear()
     		county.loadData("parent:"+value);
     		cityCode = value;
     		cityName = county.getSelectNameOfValue(value);
   }});
   
   	//县code默认值
    var county = $("#county").select({
	    	name:"address.county.code",
	    	clearEvent:function(){street.clear();},
	    	"initLoad":false,
	    	change:function(event, value){
			     street.clear();
			     street.loadData("parent:"+value);
			     countyCode = value;
			     countyName = county.getSelectNameOfValue(value);
			     if(value){
			     	$(".countyjCode").html(value);
			     };
			     
			     qy.ajax({
			     	type:"post",
				 	url: base +"/web/getGeocoderLatitude.jhtml",
				 	data:{
				 		address: countyName
				 	},
				 	callBack: function(data){
						$("#countyjlng").html(data.lng);
						$("#countyjlat").html(data.lat);
						$("#longitude").val(data.lng);
						$("#latitude").val(data.lat);
						changeDuFenMiao();
						addressChange();
					}	
			     })
			}
    	});
    
    var street = $("#street").select({name:"address.street.code","initLoad":false}); 

   
	
	
	
	
	$("#inputQuery").click(function(){
		var address = $("#inputAddress").val();
		qy.ajax({
	     	type:"post",
		 	url: base +"/web/getGeocoderLatitude.jhtml",
		 	data:{
		 		address: address
		 	},
		 	callBack: function(data){
		 		$("#inputlng").html(data.lng);
				$("#inputlat").html(data.lat);
				$("#longitude").val(data.lng);
				$("#latitude").val(data.lat);
				changeDuFenMiao();
				
			}	
	     })
	});	
	$("#changej").click(function(){
		changeLatLon();
	});	
	$("#changej2").click(function(){
		changeDuFenMiao();
	});
	$("#latlonQuery").click(function(){
		var inpLat = $("#latitude").val();
		var inpLon = $("#longitude").val();
		$.ajax({
		 	type:"post",
		 	url: base +"/web/getAreaCode.jhtml",
		 	data:{
		 		lngs: inpLon,
		 		lats: inpLat,
		 	},
		 	success: function(data){
				$(".returnjCode").html("code："+data.adcode+"地址："+data.country+data.province+data.city+data.district

					);
			}	
		})
	});
	
	
	function changeDuFenMiao(){
		var d = {};
		d.log = parseFloat($("#longitude").val());
      	d.lat = parseFloat($("#latitude").val());
      	d.lat1 = parseInt(d.lat);
      	if(d.lat < 0){
    	  	d.lat2 = parseInt(((d.lat*-1)-(d.lat1*-1))*60) ;
    	  	d.lat3 = (parseFloat((((d.lat*-1)-(d.lat1*-1))*60-d.lat2)*60)).toFixed(3);
      	}else{
    	  	d.lat2 = parseInt((d.lat-d.lat1)*60);
    	  	d.lat3 = (parseFloat(((d.lat-d.lat1)*60-d.lat2)*60)).toFixed(3);
      	}
      
      	d.log1 = parseInt(d.log);
      	if(d.log < 0){
    	  	d.log2 = parseInt(((d.log*-1)-(d.log1*-1))*60);
    	 	d.log3 = (parseFloat((((d.log*-1)-(d.log1*-1))*60-d.log2)*60)).toFixed(3);
      	}else{
    	  	d.log2 = parseInt((d.log-d.log1)*60);
    	  	d.log3 = (parseFloat(((d.log-d.log1)*60-d.log2)*60)).toFixed(3);
      	}
		$("#lata").val(d.lat1);
		$("#latb").val(d.lat2);
		$("#latc").val(d.lat3);
		$("#lona").val(d.log1);
		$("#lonb").val(d.log2);
		$("#lonc").val(d.log3);
		addressChange();
	}
	
	function changeLatLon(){
	      var val1 = parseFloat($("#lata").val());
	      var val2 = parseFloat($("#latb").val());
	      var val3 = parseFloat($("#latc").val());
	      var valA;
	      if(val1 < 0){
	    	  valA = (val1*-1) + (val2/60) + val3/3600;
	    	  valA = valA * -1;
	      }else{
	    	  valA = val1 + val2/60 + val3/3600;
	      }
	      $('#latitude').val(valA);
	      
	      var val11 = parseFloat($("#lona").val());
	      var val12 = parseFloat($("#lonb").val());
	      var val13 = parseFloat($("#lonc").val());
	      var valB;
	      if(val11 < 0){
	    	  valB = (val11*-1) + val12/60 + val13/3600;
	    	  valB = valB * -1;
	      }else{
	    	  valB = val11 + val12/60 + val13/3600;
	      }
	      $('#longitude').val(valB);
	      addressChange();
	}
	
	
  
  });
</script>
<body>
	<form id="inputForm" style="padding: 0; width: 100%;">


		<div class="infPara infPara2">
			<div class="infParaCon">
				<label for="" class="infpLable">所在地区</label>
				<div class="infpCon">
					<div class="inputs">
						<div class="inputChild col3">
							<div data-code="地域" id="provinces" class="selF"></div>
						</div>
						<div class="inputChild col3">
							<div data-code="地域" id="city" class="selF"></div>
						</div>
						<div class="inputChild col3">
							<div data-code="地域" id="county" class="selF"></div>
						</div>
						<div class="inputChild col3">
							<div data-code="地域" id="street" class="selF"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="infPara infPara2">
			选择县的code：<span class="countyjCode"></span> 经度：<span id="countyjlng"></span>
			纬度：<span id="countyjlat"></span>

		</div>


		<div class="infPara infPara2">
			<div class="infParaCon">
				<label for="" class="infpLable">输入地区查询：</label>
				<div class="infpCon">
					<div class="inputs">
						<div class="inputChild col4">
							<input type="" class="input" id="inputAddress"
								name="inputAddress">
						</div>
						<button type="button" id="inputQuery" style="margin-left: 10px">查询</button>
					</div>
				</div>
			</div>
		</div>

		<div class="infPara infPara2">
			输入地区查询的 经度：<span id="inputlng"></span> 纬度：<span id="inputlat"></span>

		</div>

		<div class="infPara infPara2">
			<div class="infParaCon">
				<label for="" class="infpLable">经度</label>
				<div class="infpCon" style="padding-right: 14px">
					<input class="input" id="longitude" placeholder="经度" name="longitude" />
				</div>
				<label for="" class="infpLable" style="margin-left: 28px">纬度</label>
				<div class="infpCon">
					<input class="input" id="latitude" placeholder="纬度" name="latitude" />
				</div>
			</div>
		</div>
		<div class="infPara infPara2">
			<button type="button" id="changej2">转为度分秒</button>
			<button type="button" id="changej">转为经纬度</button>
		</div>

		<div class="infPara">
			<div class="infParaCon">
				<label for="" class="infpLable">经度</label>
				<div class="infpCon">
					<div class="inputs">
						<div class="inputChild col4">
							<input type="" class="input" id="lona" placeholder="经度"
								name="addresstemp1" min="-180" max="180">
						</div>
						<div class="inputChild col4">
							<input type="" class="input" id="lonb" placeholder="经分"
								name="addresstemp2" min="0" max="60">
						</div>
						<div class="inputChild col4">
							<input type="" class="input" id="lonc" placeholder="经秒"
								name="addresstemp3" min="0" max="60">
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="infPara">
			<div class="infParaCon">
				<label for="" class="infpLable">纬度</label>
				<div class="infpCon">
					<div class="inputs">
						<div class="inputChild col4">
							<input type="" class="input" id="lata" placeholder="纬度"
								name="addresstemp4" min="-90" max="90">
						</div>
						<div class="inputChild col4">
							<input type="" class="input" id="latb" placeholder="纬分"
								name="addresstemp5" min="0" max="60">
						</div>
						<div class="inputChild col4">
							<input type="" class="input" id="latc" placeholder="纬秒"
								name="addresstemp6" min="0" max="60">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="infPara infPara2">
			<button type="button" id="latlonQuery">经纬度查询</button>
		</div>
		<div class="infPara infPara2">
			返回结果：<span class="returnjCode"></span>
		</div>




		<div class="infPara infPara2">
			<div id="map"></div>
		</div>

		</div>
	</form>
</body>
</html>