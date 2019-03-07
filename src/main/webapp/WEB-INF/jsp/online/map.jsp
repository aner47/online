<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZwvKDkc8AosMeMixrVVDfEqK"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<script type="text/javascript">
		require(["jquery","panel"],function(){
			var address = "${address}";
			var map = new BMap.Map("map");    // 创建Map实例
			map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
			var local = new BMap.LocalSearch(map, {
				renderOptions:{map: map},
				onSearchComplete: function(results){
					// 判断状态是否正确
					if (local.getStatus() == BMAP_STATUS_SUCCESS){
						var s = [];
						for (var i = 0; i < results.getCurrentNumPois(); i ++){
							console.log(results.getPoi(i).point);
							map.centerAndZoom(results.getPoi(i).point);
							s.push(results.getPoi(i).title + ", " + results.getPoi(i).address);
						}
					}
				}
			});
			local.search(address);
			
			
			function showInfo(e){
				var marker = new BMap.Marker(e.point);  // 创建标注
				map.addOverlay(marker);              // 将标注添加到地图中
				map.panTo(e.point);
				qy.save({title:"是否定位当前点！",yes:function(index){
					console.log("经度：："+e.point.lng+" 纬度：：："+ e.point.lat);
					if( parent && typeof parent.getPoint === 'function'){parent.getPoint(e.point.lng,e.point.lat)}
					;layer.close(index);}})
			}
			map.addEventListener("click", showInfo);
			
 
		})
	
	</script>
	<style>
		#map{
			position: absolute !important;
		    top: 0;
		    bottom: 0;
		    width: 100%;
		}

	
	</style>
</head>
<body>

		<div id="map"></div>

	
</body>
</html>