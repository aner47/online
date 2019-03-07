<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css" >
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
    <meta charset="UTF-8">
    <title>修改排放口信息</title>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","panel"],function(hide){
	
	$("#useStatus","#dialog_Discharge_port").select({filter:false,name:"useStatus",zIndex:10,defaultValue:'${exhaustionHole.useStatus}'});
	$("#texture","#dialog_Discharge_port").select({filter:false,name:"texture",zIndex:9,isCustom : true,defaultValue:'${exhaustionHole.texture}'});
	
      	var d = {};
      	d.lat = parseFloat($("#latitude","#dialog_Discharge_port").val());
      	d.log = parseFloat($("#longitude","#dialog_Discharge_port").val());
      	
      
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
	      if(!isNaN(d.lat)){
		      $("#latitude","#dialog_Discharge_port").parent().find('input').each(function(k,v){
		        if (k==0) {return};
		        $(v).val(d['lat'+k]);
		      })
	      }
	      if(!isNaN(d.log)){
		      $("#longitude","#dialog_Discharge_port").parent().find('input').each(function(k,v){
		        if (k==0) {return};
		        $(v).val(d['log'+k]);
		      })
	      }
   
	function lat(){
    	var inputs = $("#latitude","#dialog_Discharge_port").parent().find('input');
    	var val1 = parseFloat(inputs.eq(1).val());
    	var val2 = parseFloat(inputs.eq(2).val());
    	var val3 = parseFloat(inputs.eq(3).val());
    	var valA;
    	if(val1 < 0){
    		valA = (val1*-1) + (val2/60) + val3/3600;
    		valA = valA * -1;
      	}else{
    	  	valA = val1 + val2/60 + val3/3600;
      	}
    	$("input[name=latitude]").val(valA);
    	
    }
    function lot(){
      var inputs = $("#longitude","#dialog_Discharge_port").parent().find('input');
      var val1 = parseFloat(inputs.eq(1).val());
      var val2 = parseFloat(inputs.eq(2).val());
      var val3 = parseFloat(inputs.eq(3).val());
     // var valA = val1 + val2/60 + val3/3600;
      var valA;
      if(val1 < 0){
    	  valA = (val1*-1) + val2/60 + val3/3600;
    	  valA = valA * -1;
      }else{
    	  valA = val1 + val2/60 + val3/3600;
      }
     
      $("input[name=longitude]").val(valA);
    }
    function submitLatLot(){
    	lat();
    	lot();
    }
    
		$('#commit').click(function(){
			$("#dialog_Discharge_port").submit();
		})
		//提交表单
		$("#dialog_Discharge_port").validate({
			rules: {
				height:{
					min:0,
					maxlength:7
				},
				useStatus: {
					required:true
				},
				diameter:{
					min:0,
					maxlength:7
				},
				temperature:{
					min:0,
					maxlength:7
				},
				flow:{
					min:0,
					maxlength:7
				},
				emissions:{
					min:0,
					maxlength:7
				},
				oxygen:{
					range:[0,100]
				},
				so2:{
					min:0
				},
				nox:{
					min:0
				},
				tsp:{
					min:0
				},
				voc:{
					min:0
				},
				description:{
					maxlength:255
				},
				addresstemp1:{
					range:[-180,180]
				},
				addresstemp2:{
					range:[0,59],
					digits:true,
				},
				addresstemp3:{
					range:[0,59.99]
				},
				addresstemp4:{
					range:[-90,90]
				},
				addresstemp5:{
					range:[0,59],
					digits:true,
				},
				addresstemp6:{
					range:[0,59.99]
				},
			},
			messages:{
				height:{
					min:"最小为0"
				},
				diameter:{
					min:"最小为0"
				},
				temperature:{
					min:"最小为0"
				},
				flow:{
					min:"最小为0"
				},
				emissions:{
					min:"最小为0"
				},
				oxygen:{
					range:"0~100!"
				},
				so2:{
					min:"最小为0"
				},
				nox:{
					min:"最小为0"
				},
				tsp:{
					min:"最小为0"
				},
				voc:{
					min:"最小为0"
				},
				addresstemp1:{
					range:"-180~180"
				},
				addresstemp4:{
					range:"-90~90"
				},
			},
			submitHandler:function(form){
				submitLatLot();
				
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		qy.suc2({title:'更新成功！'});
							grid1.loadData();
							layer.closeAll('page');
			        	}else{
			        		qy.fail2({title:data.content});
			        	}
						
			        },
			        error:function(){
			        	qy.fail2({title:'更新失败'});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});	
		
		//判断在线监测系统是否安装
		 var testingEquipment= $("[name=testingEquipment]:checked").val();
		 if(testingEquipment == 'true'||testingEquipment == undefined ){
			 $("div#onlineDetectionType").css("background","#fff");
			 $("#onlineDetectionType","#dialog_Discharge_port").select({filter:false,name:"onlineDetectionType",zIndex:10});
       }else{
       		$("div#onlineDetectionType").css("background","#ccc");
       		$("#onlineDetectionType","#dialog_Discharge_port").select({filter:false,name:"onlineDetectionType",zIndex:10,immutable:true,disabled:true});
       }           
	
		$("[name=testingEquipment]").on('change',function(){
			if ($(this).val()=='true') {
				 $("div#onlineDetectionType").css("background","#fff");
				 $("#onlineDetectionType","#dialog_Discharge_port").select({filter:false,name:"onlineDetectionType",zIndex:10});
			}else{
				$("div#onlineDetectionType").unbind("click");
				$("div#onlineDetectionType").css("background","#ccc");
				$("#onlineDetectionType","#dialog_Discharge_port").select({filter:false,name:"onlineDetectionType",zIndex:10,immutable:true,disabled:true});
			};
		})
		hide.hide();
	});
</script>

<body >
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
 	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
	<form class="hideCls" id="dialog_Discharge_port" style="padding:0;width:100%;">
		<!-- 为了隐藏字段添加的无用字段 -->
		<input type="hidden" name="tempName1" readonly="readonly"/>
		<input type="hidden" name="exhaustLong" readonly="readonly"/>
		<input type="hidden" name="exhaustWidth" readonly="readonly"/>
		

		<input type="hidden" name="id" value="${exhaustionHole.id }"/>
		<input type="hidden" name="exhaustionHoleNum" value="${exhaustionHole.exhaustionHoleNum }"/>
		<input type="radio" name="type" id="Exhaust_cylinder" <c:if test="${exhaustionHole.type=='exhaustPipe'}">checked='checked'</c:if> value="exhaustPipe"/>

		<div class="infLine">

			<div class="infh1">排放口信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>排放口高度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" value="${exhaustionHole.height}" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>排放口直径(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="diameter" value="${exhaustionHole.diameter}" number="true" min="0" maxlength="7" required>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>经度</label>
					<div class="infpCon">
						<div class="inputs">
							<input type="hidden" name="longitude" id="longitude"
								value="${exhaustionHole.longitude}"
								/>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="经度"
									name="addresstemp1" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="经分"
									name="addresstemp2" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="经秒"
									name="addresstemp3" required>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>纬度</label>
					<div class="infpCon">
						<div class="inputs">
							<input type="hidden" name="latitude" id="latitude"
							value="${exhaustionHole.latitude}"
								 />
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="纬度"
									name="addresstemp4" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="纬分"
									name="addresstemp5" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="纬秒"
									name="addresstemp6" required>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>使用状态</label>
					<div class="infpCon">
						<div id="useStatus" data-code="排放口【使用状态】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">材质</label>
					<div class="infpCon">
						<div id="texture" data-code="排放口【材质】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infh1">废气信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">出口废气温度(°C)</label>
					<div class="infpCon">
						<input type="" class="input" name="temperature" value="${exhaustionHole.temperature}" number="true" />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">废气排放流量(标立方米/小时)</label>
					<div class="infpCon">
						<input type="" class="input" name="flow" number="true" value="${exhaustionHole.flow}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年总废气排放量(万标立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="emissions" number="true" value="${exhaustionHole.emissions}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含氧量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="oxygen" value="${exhaustionHole.oxygen}" number="true">
					</div>
				</div>
			</div>

			<div class="infh1">监测排放浓度(毫克/立方米)</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">SO<sub>2</sub></label>
					<div class="infpCon">
						<input type="" class="input" name="so2" value="${exhaustionHole.so2}" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">NO<sub>x</sub></label>
					<div class="infpCon">
						<input type="" class="input" name="nox" value="${exhaustionHole.nox}" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">颗粒物(TSP)</label>
					<div class="infpCon">
						<input type="" class="input" name="tsp" value="${exhaustionHole.tsp}" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs</label>
					<div class="infpCon">
						<input type="" class="input" name="voc" value="${exhaustionHole.voc}" number="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">非甲烷总烃</label>
					<div class="infpCon">
						<input type="" class="input" name="unMethane" value="${exhaustionHole.unMethane}" number="true">
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">是否安装在线监测设备</label>
					<div class="infpCon errorType2">
						<input type="radio" class="radio" id="cheB1" name="testingEquipment" value="true" <c:if test="${exhaustionHole.testingEquipment=='true' }">checked='checked'</c:if> required>
						<label for="cheB1" class="label">是</label>
						<input type="radio" class="radio" id="cheB2" name="testingEquipment" value="false" <c:if test="${exhaustionHole.testingEquipment=='false' }">checked='checked'</c:if>>
						<label for="cheB2" class="label" style="margin-right: 0;">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">在线监测类型</label>
					<div class="infpCon">
						<div id="onlineDetectionType" data-code="是否废气重点污染源" data-defaultValue="${exhaustionHole.onlineDetectionType}" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description">${exhaustionHole.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
</body>
</html>