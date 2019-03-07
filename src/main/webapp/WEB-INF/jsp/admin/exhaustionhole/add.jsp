<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>添加排放口信息</title>
<style type="text/css">
	.input_span {
		width: 40%!important;	
	}
</style>
</head>

<script type="text/javascript">
	require(["hide","validate","ajaxform","panel"],function(hide){
		
		$("#useStatus","#dialog_Discharge_port").select({filter:false,name:"useStatus",zIndex:10});
		$("#texture","#dialog_Discharge_port").select({filter:false,name:"texture",zIndex:9,isCustom : true,});
		
		
		$('#commit').click(function(){
			$("#dialog_Discharge_port").submit();
		})
		
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
		
		
		//提交表单
		$("#dialog_Discharge_port").validate({
			onsubmit:true,
			errorElement:'label',
			rules: {
				height:{
					min:0,
					maxlength:7
				},
				onlineDetectionType:{
					required:true
				},
				// diameter:{
				// 	min:0
				//	maxlength:7
				// },
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
				exhaustWidth:{
					min:0
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
				useStatus:{
					required:true,
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
				if ($("input[name=diameter]").val()=='') {
					var l = $("#exhaustLong").val();
					var k = $("#exhaustWidth").val();
					//长X宽/π 开根号
					$("input[name=diameter]").val((Math.sqrt((l*k)/Math.PI)).toFixed(2));
				}
				
				submitLatLot();
				
				var options  = {
			        url:base+'/web/exhaustionhole/save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
			        	if(data.code == 20000){
			        		qy.suc2({title:'添加成功！'});
							grid1.loadData();
							layer.closeAll('page');
			        	}else{
			        		qy.fail2({title:data.content});
			        	}
						
			        },
			        error:function(){
			        	qy.fail2({title:'添加失败！'});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});	
		$("[name=tempName1]").on('change',function(){
			if ($(this).val()=='circle') {
				$('.circle').removeClass('hide');
				$('.rectangle').addClass('hide');
				$("input[name=diameter]").attr({number:'true',min:'0',maxlength:'7'});
				$("#exhaustLong").removeAttr('number min maxlength');
				$("#exhaustWidth").removeAttr('number min maxlength');
			}else{
				$('.rectangle').removeClass('hide');
				$('.circle').addClass('hide');
				$("input[name=diameter]").removeAttr('number min maxlength');
				$("#exhaustLong").attr({number:'true',min:'0',maxlength:'7'});
				$("#exhaustWidth").attr({number:'true',min:'0',maxlength:'7'});
			};
		})	
		
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
	<input  type="hidden" id="fieldHide" value="${fieldShowConfig}" />
	<form  class="hideCls" id="dialog_Discharge_port" style="padding:0;width:100%;">
		<div class="infLine">

			<div class="infh1">排放口信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>排放口高度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口形状</label>
					<div class="infpCon errorType1">
						<input type="radio" class="radio" id="cheA1" name="tempName1" value="circle" checked>
						<label for="cheA1" class="label">圆</label>
						<input type="radio" class="radio" id="cheA2" name="tempName1" value="rectangle">
						<label for="cheA2" class="label">矩形</label>
					</div>
				</div>
			</div>

			<div class="infPara circle">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>排放口直径(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="diameter" number="true" min="0" maxlength="7" required>
					</div>
				</div>
			</div>

			<div class="col12 align-left rectangle hide">

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">排放口长度(米)</label>
						<div class="infpCon">
							<input type="" class="input" name="exhaustLong" id="exhaustLong" number="true" min="0" maxlength="7">
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">排放口宽度(米)</label>
						<div class="infpCon">
							<input type="" class="input" name="exhaustWidth" id="exhaustWidth" number="true" maxlength="7">
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>经度</label>
					<div class="infpCon">
						<div class="inputs">
							<input type="hidden" name="longitude" id="longitude"
								/>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="经度"
									id="addresstemp1" name="addresstemp1" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="经分"
									id="addresstemp2" name="addresstemp2" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="经秒"
									id="addresstemp3" name="addresstemp3" required>
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
								 />
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="纬度"
									id="addresstemp4" name="addresstemp4" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="纬分"
									id="addresstemp5" name="addresstemp5" required>
							</div>
							<div class="inputChild col4">
								<input type="text" class="input" placeholder="纬秒"
									id="addresstemp6" name="addresstemp6" required>
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
						<input type="" class="input " name="temperature" number="true" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">废气排放流量(标立方米/小时)</label>
					<div class="infpCon">
						<input type="" class="input " name="flow" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年总废气排放量(万标立方米)</label>
					<div class="infpCon">
						<input type="" class="input " name="emissions" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含氧量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="oxygen" number="true">
					</div>
				</div>
			</div>

			<div class="infh1">监测排放浓度(毫克/立方米)</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">SO<sub>2</sub></label>
					<div class="infpCon">
						<input type="" class="input" name="so2" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">NO<sub>x</sub></label>
					<div class="infpCon">
						<input type="" class="input" name="nox" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">颗粒物(TSP)</label>
					<div class="infpCon">
						<input type="" class="input" name="tsp" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs</label>
					<div class="infpCon">
						<input type="" class="input" name="voc" number="true">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">非甲烷总烃</label>
					<div class="infpCon">
						<input type="" class="input" name="unMethane" number="true">
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">是否安装在线监测设备</label>
					<div class="infpCon errorType2">
						<input type="radio" class="radio" id="cheB1" name="testingEquipment" value="true" required>
						<label for="cheB1" class="label">是</label>
						<input type="radio" class="radio" id="cheB2" name="testingEquipment" value="false" checked>
						<label for="cheB2" class="label" style="margin-right: 0;">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">在线监控类型</label>
					<div class="infpCon">
						<div id="onlineDetectionType" data-code="是否废气重点污染源" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description"></textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
</body>
</html>