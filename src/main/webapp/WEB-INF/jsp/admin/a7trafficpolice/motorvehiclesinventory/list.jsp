<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>机动车保有量信息表</title>
<style type="text/css">
table {
	width: 100%;
}

td {
	/* width: 10%; */
	height: 30px;
	border: 1px solid #ccc;
	text-align: center;
	position: relative;
}

input {
	border: none;
	width: 100%;
	height: 30px;
}

.infPara {
	padding: 20px 0px;
}

td label.error {
	position: absolute;
	left: 70px;
	top: 5px;
	width: 80px;
}

div {
	border: none;
}
</style>
<script type="text/javascript">
	require([ "grid", "hide","vue", "select", "panel", "validate", "ajaxform"],
			
			function(g, hide,Vue) {
				$("body").on("click", "#save", function() {
					$("#boiler_form").submit();
				})
				var cc = [{}];
				for(var i = 1 ;i<11;i++){
					cc[0]['key'+i] = i;
				}
				var keys = ["key1","key2","key3","key4","key5","key6","key7","key8","key9","key10"];
				
				var bb = [
						{"carType1":"载客汽车",rowspan:14,carType2:"大型","carType3":"国一前",rowspan1:7},
						{"ddd":"载客汽车",rowspan:0,ccc:"大型","key1":"国一",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"大型","key1":"国二",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"大型","key1":"国三",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"大型","key1":"国四",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"大型","key1":"国五",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"大型","key1":"国六",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"小型","key1":"国一前",rowspan1:7},
						{"ddd":"载客汽车",rowspan:0,ccc:"小型","key1":"国一",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"小型","key1":"国二",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"小型","key1":"国三",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"小型","key1":"国四",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"小型","key1":"国五",rowspan1:0},
						{"ddd":"载客汽车",rowspan:0,ccc:"小型","key1":"国六",rowspan1:0},
					]
				var carType1 = ['载客汽车']
				var carType2 = ["大型","小型","微型"]
				var carType3 = ["国一前","国一","国二","国三","国三","国三"]
				
				
				var vue = new Vue({
						el : "#app",
						data : {bb:bb,cc:cc,keys:keys},
						method:{
							show:function(row,key){
								//var  b = _.find(this.cc,{ddd:row.ddd,ccc:row.ccc});
								//return b[key]?b[key]:row[key];
							}
						}
				})
				
				
				//提交表单
				$("#boiler_form").validate({
					submitHandler : function(form) {
						var options = {
							url : '../motorvehiclesinventory/save.jhtml',
							type : 'post',
							dataType : "json",
							success : function(data) {
								qy.suc2({
									title : '保存成功！'
								});
							},
							error : function() {
								qy.fail2({
									title : '保存失败！'
								});
							}
						};
						$(form).ajaxSubmit(options);
						return false;
					}
				});
				
				
				var mo = ${motorVehiclesInventory};
				var type = [
					{"type":"载客汽车","type1":"大型","type2":"国一前",typeRowspan:28,type1Rowspan:7,colspan:0},
					{"type":"载客汽车","type1":"大型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"大型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"大型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"大型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"大型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"大型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"中型","type2":"国一前",typeRowspan:0,type1Rowspan:7,colspan:0},
					{"type":"载客汽车","type1":"中型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"中型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"中型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"中型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"中型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"中型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"小型","type2":"国一前",typeRowspan:0,type1Rowspan:7,colspan:0},
					{"type":"载客汽车","type1":"小型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"小型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"小型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"小型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"小型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"小型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"微型","type2":"国一前",typeRowspan:0,type1Rowspan:7,colspan:0},
					{"type":"载客汽车","type1":"微型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"微型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"微型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"微型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"微型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载客汽车","type1":"微型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					//载货汽车
					{"type":"载货汽车","type1":"重型","type2":"国一前",typeRowspan:28,type1Rowspan:7,colspan:0},
					{"type":"载货汽车","type1":"重型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"重型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"重型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"重型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"重型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"重型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"中型","type2":"国一前",typeRowspan:0,type1Rowspan:7,colspan:0},
					{"type":"载货汽车","type1":"中型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"中型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"中型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"中型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"中型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"中型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"轻型","type2":"国一前",typeRowspan:0,type1Rowspan:7,colspan:0},
					{"type":"载货汽车","type1":"轻型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"轻型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"轻型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"轻型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"轻型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"轻型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"微型","type2":"国一前",typeRowspan:0,type1Rowspan:7,colspan:0},
					{"type":"载货汽车","type1":"微型","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"微型","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"微型","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"微型","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"微型","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"载货汽车","type1":"微型","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"公交车","type1":"","type2":"国一前",typeRowspan:7,type1Rowspan:0,colspan:2},
					{"type":"公交车","type1":"","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"公交车","type1":"","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"公交车","type1":"","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"公交车","type1":"","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"公交车","type1":"","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"公交车","type1":"","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"出租车","type1":"","type2":"国一前",typeRowspan:7,type1Rowspan:0,colspan:2},
					{"type":"出租车","type1":"","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"出租车","type1":"","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"出租车","type1":"","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"出租车","type1":"","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"出租车","type1":"","type2":"国五",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"出租车","type1":"","type2":"国六",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"摩托车","type1":"","type2":"国一前",typeRowspan:5,type1Rowspan:0,colspan:2},
					{"type":"摩托车","type1":"","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"摩托车","type1":"","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"摩托车","type1":"","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"摩托车","type1":"","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"低速货车","type1":"","type2":"国一前",typeRowspan:5,type1Rowspan:0,colspan:2},
					{"type":"低速货车","type1":"","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"低速货车","type1":"","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"低速货车","type1":"","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"低速货车","type1":"","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮汽车","type1":"","type2":"国一前",typeRowspan:5,type1Rowspan:0,colspan:2},
					{"type":"三轮汽车","type1":"","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮汽车","type1":"","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮汽车","type1":"","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮汽车","type1":"","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮农用运输车","type1":"","type2":"国一前",typeRowspan:5,type1Rowspan:0,colspan:2},
					{"type":"三轮农用运输车","type1":"","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮农用运输车","type1":"","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮农用运输车","type1":"","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"三轮农用运输车","type1":"","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"四轮农用运输车","type1":"","type2":"国一前",typeRowspan:5,type1Rowspan:0,colspan:2},
					{"type":"四轮农用运输车","type1":"","type2":"国一",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"四轮农用运输车","type1":"","type2":"国二",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"四轮农用运输车","type1":"","type2":"国三",typeRowspan:0,type1Rowspan:0,colspan:0},
					{"type":"四轮农用运输车","type1":"","type2":"国四",typeRowspan:0,type1Rowspan:0,colspan:0},
					
					];
				 for (var i = 0; i < type.length; i++) {
					addTr('table',i,type[i].type,type[i].type1,type[i].type2,type[i].typeRowspan,type[i].type1Rowspan,type[i].colspan);
				} 
				
				function typeBack(row,type,typeRowspan,colspan){
					if(typeRowspan>0){
       					return '<td  class="species" rowspan="'+typeRowspan+'" colspan="'+colspan+'">'
						+'<input name="motorVehiclesInventorys['+row+'].carType" type="hidden" value="'+type+'" />'+type
						+'</td>'
       				}else{
       					return '<input name="motorVehiclesInventorys['+row+'].carType" type="hidden" value="'+type+'" />';
       				}
				}
				function type1Back(row,type1,type1Rowspan){
					if(type1Rowspan>0){
       					return '<td  class="species" rowspan="'+type1Rowspan+'">'
						+'<input name="motorVehiclesInventorys['+row+'].carType1" type="hidden" value="'+type1+'" />'+type1
						+'</td>'
       				}else{
       					return '<input name="motorVehiclesInventorys['+row+'].carType1" type="hidden" value="'+type1+'" />';
       				}
				}
				
				function idBack(i,mo){
					if(i<mo.length){
						return mo[i].id == null ?'':mo[i].id;
					}
					return '';
				}
				function gasolineBack(i,mo){
					if(i<mo.length){
						return mo[i].gasoline == null ?'':mo[i].gasoline;
					}
					return '';
				}
				function dieselBack(i,mo){
					if(i<mo.length){
						return mo[i].diesel == null ?'':mo[i].diesel;
					}
					return '';
				}
				function lngCngBack(i,mo){
					if(i<mo.length){
						return mo[i].lngCng == null ?'':mo[i].lngCng;
					}
					return '';
				}
				function lpgBack(i,mo){
					if(i<mo.length){
						return mo[i].lpg == null ?'':mo[i].lpg;
					}
					return '';
				}
				function gasElectricityBack(i,mo){
					if(i<mo.length){
						return mo[i].gasElectricity == null ?'':mo[i].gasElectricity;
					}
					return '';
				}
				function oilElectricityBack(i,mo){
					if(i<mo.length){
						return mo[i].oilElectricity == null ?'':mo[i].oilElectricity;
					}
					return '';
				}
				function oilGasBack(i,mo){
					if(i<mo.length){
						return mo[i].oilGas == null ?'':mo[i].oilGas;
					}
					return '';
				}
				function electricityBack(i,mo){
					if(i<mo.length){
						return mo[i].electricity == null ?'':mo[i].electricity;
					}
					return '';
				}
				function otherBack(i,mo){
					if(i<mo.length){
						return mo[i].other == null ?'':mo[i].other;
					}
					return '';
				}
				$('input').keyup(function () {
					var c=$(this);
					   if(/[^\d]/.test(c.val())){//替换非数字字符
					    var temp_amount=c.val().replace(/[^\d]/g,'');
					    $(this).val(temp_amount);
					   }

				});
				
			 	//动态添加tr
			    function addTr(tab,row,type,type1,type2,typeRowspan,type1Rowspan,colspan) {
			        var trHtml='<tr>'
			       				+ '<input name="motorVehiclesInventorys['+row+'].id" type="hidden" value="'+idBack(i,mo)+'" />'
			       				+typeBack(row,type,typeRowspan,colspan)
			       				+type1Back(row,type1,type1Rowspan)
								+'<td  class="species">'
								+'<input name="motorVehiclesInventorys['+row+'].carType2" type="hidden" value="'+type2+'" />'+type2
								+'</td>'
								+'<td>'
								+'<input name="motorVehiclesInventorys['+row+'].gasoline"'
								+'value="'+gasolineBack(i,mo) +'" number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].diesel"'
								+ 'value="'+dieselBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].lngCng"'
								+ 'value="'+lngCngBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].lpg"'
								+ 'value="'+lpgBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].gasElectricity"'
								+ 'value="'+gasElectricityBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].oilElectricity"'
								+ 'value="'+oilElectricityBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].oilGas"'
								+ 'value="'+oilGasBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].electricity"'
								+ 'value="'+electricityBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '<td>'
								+ '<input name="motorVehiclesInventorys['+row+'].other"'
								+ 'value="'+otherBack(i,mo)+'"  number="true" onKeyUp="value=value.replace(/[\D]/g,\'\')"/>'
								+ '</td>'
								+ '</tr>'; 
			        var $tr = $ ("."+tab+" tr").eq(row);
			         if($tr.size() == 0){
			           layer.confirm("指定的table id或行数不存在！！！");
			           return;
			        } 

			       $tr.after(trHtml);
			        //渲染样式
			        //$.parser.parse("."+tab);
			    } 

			})
</script>
<style>
.block {
	padding-bottom: 120px;
}

.examine {
	width: 42px;
	height: 30px;
	line-height: 30px !important;
	text-align: center;
	color: #fff !important;
	border-radius: 13px;
	background: #4fc1ee;
	margin-top: 9px !important;
}
</style>
</head>
<body>
	<div class="block" id="app">
		<h1 class="title">
			<i class="iconfont icon-xuqin"></i> 机动车保有量
		</h1>


		<form id="boiler_form">
			<div class="dialog_div">

				<table id="table" class="table">
					<tr>
						<td colspan="3" style="width: 300px"><b>车型</b></td>
						<td><b>汽油(辆)</b></td>
						<td><b>柴油(辆)</b></td>
						<td><b>天然气LNG/CNG(辆)</b></td>
						<td><b>液化石油气LPG(辆)</b></td>
						<td><b>气电混合(辆)</b></td>
						<td><b>油电混合(辆)</b></td>
						<td><b>油气混合(辆)</b></td>
						<td><b>纯电动(辆)</b></td>
						<td><b>其它(辆)</b></td>
					</tr>
					<!-- <tr>
						<td rowspan="4"><b>车型1</b></td>
						<td rowspan="2"><b>车型2</b></td>
						<td ><b>车型3</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<td ><b>车型3</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<td rowspan="2"><b>车型2</b></td>
						<td ><b>车型3</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<td ><b>车型3</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<td rowspan="3" colspan="2"><b>车型2</b></td>
						<td ><b>车型3</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<td ><b>车型3</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<td ><b>车型3</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr>
					<tr>
						<td colspan="3"><b>车型2</b></td>
						<td><b>汽油</b></td>
						<td><b>柴油</b></td>
						<td><b>天然气LNG/CNG</b></td>
						<td><b>液化石油气LPG</b></td>
						<td><b>气电混合</b></td>
						<td><b>油电混合</b></td>
						<td><b>油气混合</b></td>
						<td><b>纯电动</b></td>
						<td><b>其它</b></td>
					</tr> -->
					
					
					<!-- <tr v-for="(v,index) in bb" >
						<td :rowspan="v.rowspan" v-if="v.rowspan>0">{{v.ddd}}<input type="hidden" :value="v.ddd"></td>
						<td :rowspan="v.rowspan1" v-if="v.rowspan1>0">{{v.ccc}}<input type="hidden" :value="v.ccc"></td>
						<td v-for="key in keys"><input :name = "'app['+index+'][key]'" :value="v[key]"/></td>
					</tr> -->

				</table>
				
			</div>
		</form>
		<div class="buttonF">
			<div class="saveBtn" id="save">保存</div>
		</div>
	</div>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>