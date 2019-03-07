<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>增加工段</title>
<style type="text/css">
	input[type=radio].radio{
		background:#000;
	}
	input:disabled.radio{
		box-shadow:none !important;
		background:#ccc !important;	
	}
	input[type="radio" i]{
		border:none;
		box-shadow:none;
	}
	.infParaCon .infpCon .radio:disabled{
		cursor: not-allowed;
	}
	</style>

</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","panel" ,"date", "select","year"],function(hide){
	
	var year = $('#section_form #year').year({'name':'year'});

	var projectYear = '${project.dataYear}';
	year.setYearValue(projectYear);
	
	$("#putOnDate", "#section_form").jeDate({
        format:"YYYY-MM",
        isTime:false, 
        zIndex:999999900
    })
    $("#abarbeitungTime", "#section_form").jeDate({
        format:"YYYY-MM",
        isTime:false, 
        zIndex:999999900
    })
    $("#stopDate", "#section_form").jeDate({
        format:"YYYY-MM",
        isTime:false, 
        zIndex:999999900
    })
    /* 日期比较-开始  */
	function duibi(d1,d2)
	{
 			//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
 			return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
	}
	/* 日期比较-结束 */
    
	//判断是否为有机溶剂
	var rawMaterials=[];
	for(var i=1;i<=5;i++){
		rawMaterials[i]= $("[name='rawMaterials"+i+".rawMaterialsType']:checked").val();
		 if(rawMaterials[i] == 'true'){
			 $("div#rawMaterials"+i).css("background","#fff");
			 $("input[name='rawMaterials"+i+".solventType']").removeAttr('disabled');
	      	 $("input[name='rawMaterials"+i+".vocRate']").removeAttr('disabled');
	      	 $("input[name='rawMaterials"+i+".vocVolatility']").removeAttr('disabled');
       }else{
    	   	var select4 = $("#rawMaterials"+i,"#section_form").select({name:"rawMaterials"+i+".solventCategory",zIndex:(9-i)});
    	   	/* var select4 = $("#rawMaterials1","#section_form").select({name:"rawMaterials1.solventCategory",zIndex:9});
    		var select4 = $("#rawMaterials2","#section_form").select({name:"rawMaterials2.solventCategory",zIndex:8});
    		var select4 = $("#rawMaterials3","#section_form").select({name:"rawMaterials3.solventCategory",zIndex:7});
    		var select4 = $("#rawMaterials4","#section_form").select({name:"rawMaterials4.solventCategory",zIndex:6});
    		var select4 = $("#rawMaterials5","#section_form").select({name:"rawMaterials5.solventCategory",zIndex:5}); */
    	    $("div#rawMaterials"+i).css("background","#ccc");
    	   	$("div#rawMaterials"+i).unbind("click");
    	   	/* $("div#rawMaterials1").unbind("click");
    	   	$("div#rawMaterials2").unbind("click");
    	   	$("div#rawMaterials3").unbind("click");
    	   	$("div#rawMaterials4").unbind("click");
    	   	$("div#rawMaterials5").unbind("click"); */
    	    $("input[name='rawMaterials"+i+".solventType']").attr({disabled:'true'});
    	    $("input[name='rawMaterials"+i+".vocRate']").attr({disabled:'true'});
    	    $("input[name='rawMaterials"+i+".vocVolatility']").attr({disabled:'true'});
       }           
	
		(function(i){
			$("[name='rawMaterials"+i+".rawMaterialsType']").on('change',function(){
				if ($(this).val()=='organic') {
					 $("#rawMaterials"+i,"#section_form").select({name:"rawMaterials"+i+".solventCategory",zIndex:10-i});
					 $("div#rawMaterials"+i).css("background","#fff");
					 $("input[name='rawMaterials"+i+".solventType']").removeAttr('disabled');
			      	 $("input[name='rawMaterials"+i+".vocRate']").removeAttr('disabled');
			      	 $("input[name='rawMaterials"+i+".vocVolatility']").removeAttr('disabled');
				}else{
					/* var select4 = $("#rawMaterials1","#section_form").select({name:"rawMaterials1.solventCategory",zIndex:9});
		    		var select4 = $("#rawMaterials2","#section_form").select({name:"rawMaterials2.solventCategory",zIndex:8});
		    		var select4 = $("#rawMaterials3","#section_form").select({name:"rawMaterials3.solventCategory",zIndex:7});
		    		var select4 = $("#rawMaterials4","#section_form").select({name:"rawMaterials4.solventCategory",zIndex:6});
		    		var select4 = $("#rawMaterials5","#section_form").select({name:"rawMaterials5.solventCategory",zIndex:5}); */
		    		$("#rawMaterials"+i,"#section_form").select({name:"rawMaterials"+i+".solventCategory",zIndex:10-i});
					$("div#rawMaterials"+i).unbind("click");
					$("div#rawMaterials"+i).css("background","#ccc");
			    	$("input[name='rawMaterials"+i+".solventType']").attr({disabled:'true'});
			    	$("input[name='rawMaterials"+i+".vocRate']").attr({disabled:'true'});
			    	$("input[name='rawMaterials"+i+".vocVolatility']").attr({disabled:'true'});
				};
			})	
		})(i)
	}
	
	$("#useStatus","#section_form").select({filter: false,name:"useStatus",zIndex:19,isCustom : true});
	$("#facilityUnit","#section_form").select({filter: false,name:"facilityUnit",zIndex:18,isCustom : true});
	$("#productUnit","#section_form").select({filter: false,name:"product.unit",zIndex:17,isCustom : true});
	
	$("#fuelType", "#section_form").select({name : "fuelType",isCustom : true,zIndex : 16,
  		validate : 'required'
  	});
  	$("#fuelUnit", "#section_form").select({name : "fuelUnit",isCustom : true,zIndex : 15,
  		validate : 'required'
  	});
	$("#contaminantType","#section_form").select({filter: false,name:"contaminantType",zIndex:14,multselect: true,isCustom : true});
	var simpleData = {};
	 simpleData['-1'] ='无组织排放';
	 $("#dischargeModality","#section_form").select({filter: false,name:"dischargeModality",zIndex:13,change:function(event,value){
		if(value=='无组织'){
			exhaustionHoleId.loadDataSimpleData(simpleData);
		}else{
			exhaustionHoleId.loadData();
		}
		
	}});
	$("#governanceMeasures1","#section_form").select({name:"governanceMeasures1",zIndex:12});
	$("#governanceMeasures2","#section_form").select({name:"governanceMeasures2",zIndex:11});
	$("#governanceMeasures3","#section_form").select({name:"governanceMeasures3",zIndex:10});
	$("#governanceMeasures4","#section_form").select({name:"governanceMeasures4",zIndex:9});
	var exhaustionHoleId = $("#exhaustionHoleId","#section_form").select({name:"exhaustionHoleId",zIndex:8});
	
	
  	
	
	$('#productUnit').on('change',function(){
		if ($(this).val()) {
			$('#productCapacityUnit').html('<span>'+$(this).val()+'</span>');
		}else{
			$('#productCapacityUnit').html('');
		};
	})
		$('#commit').click(function(){
			$("#section_form").submit();
		})
		
		//提交表单
		$("#section_form").validate({
			rules: {
				"rawMaterials1.consumption":{
					min:0,
					maxlength:16
				},
				"rawMaterials2.consumption":{
					min:0,
					maxlength:16
				},
				"rawMaterials3.consumption":{
					min:0,
					maxlength:16
				},
				"rawMaterials4.consumption":{
					min:0,
					maxlength:16
				},
				"rawMaterials5.consumption":{
					min:0,
					maxlength:16
				},
				"rawMaterials1.unit":{
					checkUnit:/\D/
				},
				"rawMaterials2.unit":{
					checkUnit:/\D/
				},
				"rawMaterials3.unit":{
					checkUnit:/\D/
				},
				"rawMaterials4.unit":{
					checkUnit:/\D/
				},
				"rawMaterials5.unit":{
					checkUnit:/\D/
				},
				"rawMaterials1.vocRate":{
					range:[0,100]
				},
				"rawMaterials2.vocRate":{
					range:[0,100]
				},
				"rawMaterials3.vocRate":{
					range:[0,100]
				},
				"rawMaterials4.vocRate":{
					range:[0,100]
				},
				"rawMaterials5.vocRate":{
					range:[0,100]
				},
				"rawMaterials1.vocVolatility":{
					range:[0,100]
				},
				"rawMaterials2.vocVolatility":{
					range:[0,100]
				},
				"rawMaterials3.vocVolatility":{
					range:[0,100]
				},
				"rawMaterials4.vocVolatility":{
					range:[0,100]
				},
				"rawMaterials5.vocVolatility":{
					range:[0,100]
				},
				"product.yield":{
					min:0,
					maxlength:16
				},
				"product.unit":{
					checkUnit:/\D/
				},
				productCapacity:{
					min:0,
					maxlength:16
				},
				governanceMeasures1:{
					required:true
				},
				governanceMeasures2:{
					required:true
				},
				governanceMeasures3:{
					required:true
				},
				governanceMeasures4:{
					required:true
				},
				"rawMaterials1.rawMaterialsType":{
					required:true
				},
				isSealing:{
					required:true
				},
				description:{
					maxlength:255
				}
			},
			messages:{
				description:{
					maxlength:"最多255字"
				},
			},
			submitHandler:function(form){
				//日期比较
				if(duibi($("#section_form #putOnDate").val(),$("#section_form #stopDate").val())){
					layer.msg('关停时间不能早于投运时间',{icon:3});
					return false;
				}
				
				// 投运 / putOnDate
				if(duibi($("#section_form #putOnDate").val(),$("#section_form #abarbeitungTime").val())){
					layer.msg('整改时间不能早于投运时间',{icon:3});
					return false;
				}
				
				if (!isSubmit($("input[name ='rawMaterials1.name']")
						.val(), $(
						"input[name ='rawMaterials1.consumption']")
						.val(), $("input[name ='rawMaterials1.unit']")
						.val())){qy.suc3({title:"辅料1必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials2.name']")
						.val(), $(
						"input[name ='rawMaterials2.consumption']")
						.val(), $("input[name ='rawMaterials2.unit']")
						.val())){qy.suc3({title:"辅料2必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials3.name']")
						.val(), $(
						"input[name ='rawMaterials3.consumption']")
						.val(), $("input[name ='rawMaterials3.unit']")
						.val())){qy.suc3({title:"辅料3必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials4.name']")
						.val(), $(
						"input[name ='rawMaterials4.consumption']")
						.val(), $("input[name ='rawMaterials4.unit']")
						.val())){qy.suc3({title:"辅料4必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials5.name']")
						.val(), $(
						"input[name ='rawMaterials5.consumption']")
						.val(), $("input[name ='rawMaterials5.unit']")
						.val())){qy.suc3({title:"辅料5必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='product.name']")
						.val(), $(
						"input[name ='product.yield']")
						.val(), $("input[name ='product.unit']")
						.val())){qy.suc3({title:"产品必须填写3个参数！"});return false}
				var options  = {
			        url:base+'/web/section/save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'添加成功！'});
						grid1.loadData();
						layer.closeAll('page');
			        },
			        error:function(){
			        	qy.fail2({title:'添加失败'});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});		
		function isSubmit(str1, str2, str3) {
			if (str1 && str2 && str3) {
				return true;
			}
			if (!str1 && !str2 && !str3) {
				return true
			}
			return false;
		}
		
		
		hide.hide();
	});
</script>

<body>
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form class="hideCls" id="section_form" style="padding:0;width:100%;">
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>生产单元名称</label>
					<div class="infpCon">
						<input type="" class="input" name="name" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>工艺名称</label>
					<div class="infpCon">
						<input type="" class="input" name="productionTechnique" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">生产设施名称</label>
					<div class="infpCon">
						<input type="" class="input" name="facilityName" >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>生产设施数量</label>
					<div class="infpCon">
						<input type="text" class="input" name="facilityNum" number=true required />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">设备/工序使用状态</label>
					<div class="infpCon">
						<div class="selF" id="useStatus" data-code="锅炉【锅炉状态】"></div>
					</div>
				</div>
			</div>
			
			<!-- <div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">生产设施编号</label>
					<div class="infpCon">
						<input type="text" class="input" name="facilityNo" >
					</div>
				</div>
			</div> -->
			
			<div class="infh1">相关时间</div>
			<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年份</label>
						<div class="infpCon">
							<div id="year" class="yearF iconfont icon-nianfen"></div>
						</div>
					</div>
				</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>投运时间</label>
					<div class="infpCon">
						<input type="" class="input" id="putOnDate" name="putOnDate" required onfocus="this.blur()">
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
						<input type="" class="input" id="abarbeitungTime" name="abarbeitungTime"  onfocus="this.blur()">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopDate" name="stopDate" readonly>
					</div>
				</div>
			</div>
			
			<div class="infh1">设施参数</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">参数名称</label>
					<div class="infpCon">
						<input type="" class="input" name="facilityParamName" >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">设计值</label>
					<div class="infpCon">
						<input type="" class="input" name="facilityDesignValue" >
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="facilityUnit" > -->
						<div class="selF" id="facilityUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否封闭作业</label>
					<div class="infpCon">
						<div class="infpCon">
						<input type="radio" class="radio" id="isSealing1" name="isSealing" value="是">
						<label for="isSealing1" class="label">是</label>
						<input type="radio" class="radio" id="isSealing2" name="isSealing" value="否">
						<label for="isSealing2" class="label">否</label>
					</div>
					</div>
				</div>
			</div>

			<!-- <div class="infh1">原料信息1</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheA1" name="rawMaterials1.rawMaterialsType" value="organic">
						<label for="cheA1" class="label">是</label>
						<input type="radio" class="radio" id="cheA2" name="rawMaterials1.rawMaterialsType" value="inorganic">
						<label for="cheA2" class="label">否</label>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.name" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.consumption" number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.unit" required>
					</div>
				</div>
			</div>

			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="rawMaterials1" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheB1" name="rawMaterials1.solventType" value="oil">
						<label for="cheB1" class="label">油性</label>
						<input type="radio" class="radio" id="cheB2" name="rawMaterials1.solventType" value="water">
						<label for="cheB2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.vocRate">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.vocVolatility">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息2</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheC1" name="rawMaterials2.rawMaterialsType" value="organic">
						<label for="cheC1" class="label">是</label>
						<input type="radio" class="radio" id="cheC2" name="rawMaterials2.rawMaterialsType" value="inorganic">
						<label for="cheC2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.name" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.consumption" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.unit" >
					</div>
				</div>
			</div>

			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="rawMaterials2" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheD1" name="rawMaterials2.solventType" value="oil">
						<label for="cheD1" class="label">油性</label>
						<input type="radio" class="radio" id="cheD2" name="rawMaterials2.solventType" value="water">
						<label for="cheD2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.vocRate">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.vocVolatility">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息3</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheE1" name="rawMaterials3.rawMaterialsType" value="organic">
						<label for="cheE1" class="label">是</label>
						<input type="radio" class="radio" id="cheE2" name="rawMaterials3.rawMaterialsType" value="inorganic">
						<label for="cheE2" class="label">否</label>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.name" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.consumption" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.unit" >
					</div>
				</div>
			</div>

			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="rawMaterials3" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheF1" name="rawMaterials3.solventType" value="oil">
						<label for="cheF1" class="label">油性</label>
						<input type="radio" class="radio" id="cheF2" name="rawMaterials3.solventType" value="water">
						<label for="cheF2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.vocRate">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.vocVolatility">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息4</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheG1" name="rawMaterials4.rawMaterialsType" value="organic">
						<label for="cheG1" class="label">是</label>
						<input type="radio" class="radio" id="cheG2" name="rawMaterials4.rawMaterialsType" value="inorganic">
						<label for="cheG2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.name" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.consumption" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.unit" >
					</div>
				</div>
			</div>

			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="rawMaterials4" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheH1" name="rawMaterials4.solventType" value="oil">
						<label for="cheH1" class="label">油性</label>
						<input type="radio" class="radio" id="cheH2" name="rawMaterials4.solventType" value="water">
						<label for="cheH2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.vocRate">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.vocVolatility">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息5</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheI1" name="rawMaterials5.rawMaterialsType" value="organic">
						<label for="cheI1" class="label">是</label>
						<input type="radio" class="radio" id="cheI2" name="rawMaterials5.rawMaterialsType" value="inorganic">
						<label for="cheI2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.name" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.consumption" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.unit" >
					</div>
				</div>
			</div>

			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="rawMaterials5" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheJ1" name="rawMaterials5.solventType" value="oil">
						<label for="cheJ1" class="label">油性</label>
						<input type="radio" class="radio" id="cheJ2" name="rawMaterials5.solventType" value="water">
						<label for="cheJ2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.vocRate">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.vocVolatility">
					</div>
				</div>
			</div> -->

			<div class="infh1">产品信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>产品名称</label>
					<div class="infpCon">
						<input type="" class="input" name="product.name" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>产品年产量</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield" number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="product.unit" id="productUnit" required> -->
						<div class="selF" name="product.unit" id="productUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>设计年产能</label>
					<div class="infpCon">
						<input type="" class="input" name="productCapacity" number=true required>
					</div>
					<label for="" class="infpUnit" id="productCapacityUnit"></label>
				</div>
			</div>
			
			<div class="infh1">燃料信息</div>

				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>燃料类型</label>
						<div class="infpCon">
							<div id="fuelType" data-code="燃料类型" class="selF"></div>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗总量</label>
						<div class="infpCon">
							<input type="" class="input" name="fuelConsumption" min="0" number=true required>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数量单位</label>
						<div class="infpCon">
							<!-- <input type="" class="input" name="fuelUnit" required> -->
							<div class="selF" id="fuelUnit" data-code="数量单位"></div>
						</div>
					</div>
				</div>
				
			
			<div class="infh1">治理措施</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">污染物种类(可多选)</label>
					<div class="infpCon">
						<div id="contaminantType" data-code="生产线【污染物种类】" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" id="infpLable_form" class="infpLable">排放形式</label>
					<div class="infpCon">
						<div id="dischargeModality" data-code="生产线【排放形式】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施编号1</label>
					<div class="infpCon">
						<div id="governanceMeasures1" data-code="污染治理措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施编号2</label>
					<div class="infpCon">
						<div id="governanceMeasures2" data-code="污染治理措施" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施编号3</label>
					<div class="infpCon">
						<div id="governanceMeasures3" data-code="污染治理措施" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>治理措施编号4</label>
					<div class="infpCon">
						<div id="governanceMeasures4" data-code="污染治理措施" class="selF"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">排放口编号</label>
					<div class="infpCon">
						<div id="exhaustionHoleId" data-code="排放口" class="selF"></div>
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
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>