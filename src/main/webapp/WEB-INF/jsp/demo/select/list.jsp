<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<title></title>
<style>
  .select{
	margin-top: 40px;
	height: 30px;
	width: 200px;
	position:relative;
}
</style>
<script>
	require([ "select" ], function() {
		var select = $("#select1").select({name:"agc",afterInit:function(){alert(select.getSelectValue());}});
        $("#provinces").select({name:"provinces",change:function(event, value){
        	city.clear();
        	city.loadData("parent:"+value);
        }});
        
        var city = $("#city").select({name:"city","initLoad":false,clearEvent:function(){county.clear();},change:function(event, value){
        	county.clear()
        	county.loadData("parent:"+value);
        }});
        var county = $("#county").select({name:"county",clearEvent:function(){street.clear();},"initLoad":false,change:function(event, value){
        	street.clear()
        	street.loadData("parent:"+value);
        }});
        var street = $("#street").select({name:"street","initLoad":false}); 

	})
</script>
</head>
<body>
		<div class="select" id="select1" data-code="测试【下拉】"></div>
  		<div class="select" data-code="地域" id="provinces" class=" reg_areaSelect"></div>
     	<div class="select" id ="city" data-code="地域" class="reg_areaSelect" ></div>
     	<div class="select" id = "county" data-code="地域" class="reg_areaSelect" ></div>
     	<div class="select" id = "street" data-code="地域" class="reg_areaSelect" ></div>
</body>
</html>