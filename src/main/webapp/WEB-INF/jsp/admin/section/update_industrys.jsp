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
	<title>更新工段</title>
<style>
	.dialog_input,.dialog_inputmid{
		width:140px;
	}
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
	require(["hide","localCache","validate","ajaxform","panel","date","year"],function(hide,localCache){
		var year = $('#section_form #year').year({'name':'year',defaultValue:'${section.year}'});
		
		var putProductionYear = localCache.getCookie("putProductionYear");
		console.log(putProductionYear)
		
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
		function showProduct(value){
			if(value == '氧化铝生产' || value == '电解铝生产' || value == '铝型材生产' || value == '砖瓦' 
					|| value == '耐火材料' || value == '陶瓷' || value == '石墨碳素'
					|| value == '涂装'|| value == '喷涂'){
				$("#tachnologyProductTypeDiv").show();
			}else{
				$("#tachnologyProductTypeDiv").hide();
			}
		}		
		function showRaw(value){
			if(value == '喷涂'){
				$("#rawMaterialsDiv").show();
				$("input[name ='rawMaterials1.name']").attr("disabled", false);
				$("input[name ='rawMaterials2.name']").attr("disabled", false);
				$("input[name ='rawMaterials3.name']").attr("disabled", false);
				$("input[name ='rawMaterials4.name']").attr("disabled", false);
				$("input[name ='rawMaterials5.name']").attr("disabled", false);
				$("input[name ='rawMaterials1.consumption']").attr("disabled", false);
				$("input[name ='rawMaterials2.consumption']").attr("disabled", false);
				$("input[name ='rawMaterials3.consumption']").attr("disabled", false);
				$("input[name ='rawMaterials4.consumption']").attr("disabled", false);
				$("input[name ='rawMaterials5.consumption']").attr("disabled", false);
				$("input[name ='rawMaterials1.unit']").attr("disabled", false);
				$("input[name ='rawMaterials2.unit']").attr("disabled", false);
				$("input[name ='rawMaterials3.unit']").attr("disabled", false);
				$("input[name ='rawMaterials4.unit']").attr("disabled", false);
				$("input[name ='rawMaterials5.unit']").attr("disabled", false);
			}else{
				$("#rawMaterialsDiv").hide();
				$("input[name ='rawMaterials1.name']").attr("disabled", true);
				$("input[name ='rawMaterials2.name']").attr("disabled", true);
				$("input[name ='rawMaterials3.name']").attr("disabled", true);
				$("input[name ='rawMaterials4.name']").attr("disabled", true);
				$("input[name ='rawMaterials5.name']").attr("disabled", true);
				$("input[name ='rawMaterials1.consumption']").attr("disabled", true);
				$("input[name ='rawMaterials2.consumption']").attr("disabled", true);
				$("input[name ='rawMaterials3.consumption']").attr("disabled", true);
				$("input[name ='rawMaterials4.consumption']").attr("disabled", true);
				$("input[name ='rawMaterials5.consumption']").attr("disabled", true);
				$("input[name ='rawMaterials1.unit']").attr("disabled", true);
				$("input[name ='rawMaterials2.unit']").attr("disabled", true);
				$("input[name ='rawMaterials3.unit']").attr("disabled", true);
				$("input[name ='rawMaterials4.unit']").attr("disabled", true);
				$("input[name ='rawMaterials5.unit']").attr("disabled", true);
			}
		}
		
	    var name = $("#name","#section_form").select({isCustom: true,name:"name",zIndex:21,param:"${enterprise.productIndustry}",defaultValue:'${section.name}',change:function(data,value){
			productionTechnique.loadData(value);
			facilityName.loadData(value);
			facilityParamName.loadData(value);
			tachnologyProductType.loadData(value);
			showProduct(value);
			showRaw(value);
		}});
	    var tachnologyProductType = $("#tachnologyProductType","#section_form").select({isCustom: true,initLoad:false,name:"tachnologyProductType",defaultValue:'${section.tachnologyProductType}',zIndex:20,change:function(data,value){
		}});
		
		var productionTechnique = $("#productionTechnique","#section_form").select({isCustom: true,initLoad:false,name:"productionTechnique",defaultValue:'${section.productionTechnique}',zIndex:19,change:function(data,value){
			var nameSelect = name.getSelectValue();
			facilityName.loadData("firstParam:"+nameSelect+",secondParam:"+value);
		}});
		var facilityName = $("#facilityName","#section_form").select({isCustom: true,initLoad:false,name:"facilityName",defaultValue:'${section.facilityName}',zIndex:18});
		$("#useStatus","#section_form").select({filter: false,name:"useStatus",zIndex:17,isCustom : true,defaultValue:'${section.useStatus}'});
		var facilityParamName = $("#facilityParamName","#section_form").select({isCustom: true,initLoad:false,name:"facilityParamName",defaultValue:'${section.facilityParamName}',zIndex:16});
		if('${section.name}'){
			productionTechnique.loadData('${section.name}');
			facilityName.loadData('${section.name}');
			facilityParamName.loadData('${section.name}');
			tachnologyProductType.loadData('${section.name}');
			showProduct('${section.name}');
			showRaw('${section.name}');
		}
		$("#facilityUnit","#section_form").select({filter: false,name:"facilityUnit",zIndex:15,defaultValue:'${section.facilityUnit}',isCustom : true});
		 $("#productUnit","#section_form").select({filter: false,name:"product.unit",zIndex:14,defaultValue:'${section.product.unit}',isCustom : true});
		 
		 $("#fuelType", "#section_form").select({name : "fuelType",isCustom : true,zIndex : 13,defaultValue:'${section.fuelType}',
		  		validate : 'required'
		  	});
		  	$("#fuelUnit", "#section_form").select({name : "fuelUnit",isCustom : true,zIndex : 12,defaultValue:'${section.fuelUnit}',
		  		validate : 'required'
		  	});
		  	
	    $("#contaminantType","#section_form").select({filter: false,name:"contaminantType",zIndex:11,multselect: true,defaultValue:'${section.contaminantType}',isCustom : true});
		//$("#dischargeModality","#section_form").select({filter: false,name:"dischargeModality",zIndex:12,defaultValue:'${section.dischargeModality}'});
	
		var simpleData = {};
		 simpleData['-1'] ='无组织排放';
		 $("#dischargeModality","#section_form").select({filter: false,name:"dischargeModality",zIndex:10,defaultValue:'${section.dischargeModality}',change:function(event,value){
			if(value=='无组织'){
				console.log('无组织');
				exhaustionHoleId.loadDataSimpleData(simpleData);
			}else{
				exhaustionHoleId.loadData();
			}
			
		}});
		 
		 var defaultgovernanceMeasures1 = '${section.enterpriseEmissionsManagement.governanceMeasures1.id}'?'${section.enterpriseEmissionsManagement.governanceMeasures1.id}':-1;
		 var defaultgovernanceMeasures2 = '${section.enterpriseEmissionsManagement.governanceMeasures2.id}'?'${section.enterpriseEmissionsManagement.governanceMeasures2.id}':-1;
		 var defaultgovernanceMeasures3 = '${section.enterpriseEmissionsManagement.governanceMeasures3.id}'?'${section.enterpriseEmissionsManagement.governanceMeasures3.id}':-1;
		 var defaultgovernanceMeasures4 = '${section.enterpriseEmissionsManagement.governanceMeasures4.id}'?'${section.enterpriseEmissionsManagement.governanceMeasures4.id}':-1;
		 
		 
		$("#governanceMeasures1","#section_form").select({name:"governanceMeasures1",zIndex:9,defaultValue:defaultgovernanceMeasures1});
		$("#governanceMeasures2","#section_form").select({name:"governanceMeasures2",zIndex:8,defaultValue:defaultgovernanceMeasures2});
		$("#governanceMeasures3","#section_form").select({name:"governanceMeasures3",zIndex:7,defaultValue:defaultgovernanceMeasures3});
		$("#governanceMeasures4","#section_form").select({name:"governanceMeasures4",zIndex:6,defaultValue:defaultgovernanceMeasures4});
		
		//console.log('${section.dischargeModality}');
		if('${section.dischargeModality}'=='无组织'){
			console.log('无组织');
			console.log('${section.enterpriseEmissionsManagement.exhaustionHole.id}');
			// exhaustionHoleId.loadDataSimpleData(simpleData);
			 var exhaustionHoleId = $("#exhaustionHoleId","#section_form").select({simpleData:simpleData,name:"exhaustionHoleId",zIndex:5,defaultValue:'-1'});
		 }else{
			 var exhaustionHoleId = $("#exhaustionHoleId","#section_form").select({name:"exhaustionHoleId",zIndex:5,defaultValue:'${section.enterpriseEmissionsManagement.exhaustionHole.id}'});
		 }
		
		/* $("#rawMaterials1","#section_form").select({name:"rawMaterials1.solventCategory", zIndex:9,defaultValue:'${section.rawMaterials1.solventCategory}'});
		$("#rawMaterials2","#section_form").select({name:"rawMaterials2.solventCategory",zIndex:8,defaultValue:'${section.rawMaterials2.solventCategory}'});
		$("#rawMaterials3","#section_form").select({name:"rawMaterials3.solventCategory",zIndex:7,defaultValue:'${section.rawMaterials3.solventCategory}'});
		$("#rawMaterials4","#section_form").select({name:"rawMaterials4.solventCategory",zIndex:6,defaultValue:'${section.rawMaterials4.solventCategory}'});
		$("#rawMaterials5","#section_form").select({name:"rawMaterials5.solventCategory",zIndex:5,defaultValue:'${section.rawMaterials5.solventCategory}'}); */
		$('#productUnit').on('change',function(){
			if ($(this).val()) {
				$('#productCapacityUnit').html('<span>'+$(this).val()+'</span>');
			}else{
				$('#productCapacityUnit').html('');
			};
		})
		$('#productCapacityUnit').html('<span>'+$('#productUnit').val()+'</span>');
		
		if ($('#productUnit').val()) {
			$('#productCapacityUnit').html('<span>'+$('#productUnit').val()+'</span>');
		}else{
			$('#productCapacityUnit').html('');
		};
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
				"enterpriseEmissionsManagement.exhaustionHole.id":{
					required:true
				},
				"rawMaterials1.rawMaterialsType":{
					required:true
				},
				isSealing:{
					required:true
				},
				name:{
					required:true
				},
				productionTechnique:{
					required:true
				},
				facilityName:{
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
				
				if (putProductionYear > $("#section_form #putOnDate").val()) {
					layer.msg('投运时间不能早于开业时间',{icon:3});
					return false;
				}
				
			if (!isSubmit($("input[name ='product.name']")
				.val(), $(
					"input[name ='product.yield']")
				.val(), $("input[name ='product.unit']")
				.val())){qy.suc3({title:"产品必须填写3个参数！"});return false}

				var options  = {
					url:'update.jhtml',
					type:'post',
					dataType : 'json',
					success:function(data){
						qy.suc2({title:'更新成功！'});
						grid1.loadData();
						layer.closeAll('page');
					},
					error:function(){
						qy.fail({title:'更新失败'});
					}
				};
				$(form).ajaxSubmit(options);
				return false;}
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

//判断是否为有机溶剂
//存储select下拉框的defaultValue值
var arr=[
	'${section.rawMaterials1.solventCategory}',
	'${section.rawMaterials2.solventCategory}',
	'${section.rawMaterials3.solventCategory}',
	'${section.rawMaterials4.solventCategory}',
	'${section.rawMaterials5.solventCategory}'
];

var rawMaterials=[];
for(var i=1;i<=5;i++){
	(function(i){
		rawMaterials[i]= $("[name='rawMaterials"+i+".rawMaterialsType']:checked").val();
		 if(rawMaterials[i] == 'organic'){
			 $("#rawMaterials"+i,"#section_form").select({name:"rawMaterials"+i+".solventCategory",zIndex:10-i,defaultValue:arr[i-1]});
			 $("div#rawMaterials"+i).css("background","#fff");
			 $("input[name='rawMaterials"+i+".solventType']").removeAttr('disabled');
	      	 $("input[name='rawMaterials"+i+".vocRate']").removeAttr('disabled');
	      	 $("input[name='rawMaterials"+i+".vocVolatility']").removeAttr('disabled');
	   }else{
		   $("#rawMaterials"+i,"#section_form").select({name:"rawMaterials"+i+".solventCategory",zIndex:10-i,defaultValue:''});
			$("div#rawMaterials"+i).unbind("click");
			$("div#rawMaterials"+i).css("background","#ccc");
		    $("input[name='rawMaterials"+i+".solventType']").attr({disabled:'true'});
		    $("input[name='rawMaterials"+i+".vocRate']").attr({disabled:'true'});
		    $("input[name='rawMaterials"+i+".vocVolatility']").attr({disabled:'true'});
	   }           
		
		$("[name='rawMaterials"+i+".rawMaterialsType']").on('change',function(){
			if ($(this).val()=='organic') {
				 $("#rawMaterials"+i,"#section_form").select({name:"rawMaterials"+i+".solventCategory",zIndex:10-i,defaultValue:arr[i-1]});
				 $("div#rawMaterials"+i).css("background","#fff");
				 $("input[name='rawMaterials"+i+".solventType']").removeAttr('disabled');
		      	 $("input[name='rawMaterials"+i+".vocRate']").removeAttr('disabled');
		      	 $("input[name='rawMaterials"+i+".vocVolatility']").removeAttr('disabled');
			}else{
				/* var select4 = $("#rawMaterials1","#section_form").select({name:"rawMaterials1.solventCategory",zIndex:9,defaultValue:''});
				var select4 = $("#rawMaterials2","#section_form").select({name:"rawMaterials2.solventCategory",zIndex:8,defaultValue:''});
				var select4 = $("#rawMaterials3","#section_form").select({name:"rawMaterials3.solventCategory",zIndex:7,defaultValue:''});
				var select4 = $("#rawMaterials4","#section_form").select({name:"rawMaterials4.solventCategory",zIndex:6,defaultValue:''});
				var select4 = $("#rawMaterials5","#section_form").select({name:"rawMaterials5.solventCategory",zIndex:5,defaultValue:''}); */
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
hide.hide();
});
</script>

<body>
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form class="hideCls" id="section_form" style="padding:0;width:100%;">
		<input type="hidden" name="id" value="${section.id }" />
		<input type="hidden" name="rawMaterials1.id" value="${section.rawMaterials1.id }" />
		<input type="hidden" name="rawMaterials2.id" value="${section.rawMaterials2.id }" />
		<input type="hidden" name="rawMaterials3.id" value="${section.rawMaterials3.id }" />
		<input type="hidden" name="rawMaterials4.id" value="${section.rawMaterials4.id }" />
		<input type="hidden" name="rawMaterials5.id" value="${section.rawMaterials5.id }" />
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>生产单元名称</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="name" value="${section.name}" required> --%>
						<div id="name" data-code="工段【生产单元名称】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon" id="tachnologyProductTypeDiv" style="display: none">
					<label for="" class="infpLable">技术/产品类型</label>
					<div class="infpCon">
						<div id="tachnologyProductType" data-code="工段【技术产品类型】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>工艺名称</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="productionTechnique" value="${section.productionTechnique}" required> --%>
						<div id="productionTechnique" data-code="工段【工艺名称】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>生产设施名称</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="facilityName" value="${section.facilityName}"> --%>
						<div id="facilityName" data-code="工段【生产设施名称】" class="selF"></div>
					</div>
				</div>
			</div>
			<%-- <div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">生产设施编号</label>
					<div class="infpCon">
						<input type="text" class="input" name="facilityNo" value="${section.facilityNo}" onfocus="this.blur()"/>
					</div>
				</div>
			</div> --%>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>生产设施数量</label>
					<div class="infpCon">
						<input type="text" class="input" name="facilityNum" value="${section.facilityNum}" number=true required/>
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
					<label for="" class="infpLable">投运时间</label>
					<div class="infpCon">
						<input type="" class="input" id="putOnDate" name="putOnDate" value="<fmt:formatDate value='${section.putOnDate}' pattern='yyyy-MM-dd'/>" onfocus="this.blur()" required>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
						<input type="" class="input" id="abarbeitungTime" name="abarbeitungTime" 
						value="<fmt:formatDate value='${section.abarbeitungTime}' pattern='yyyy-MM-dd'/>"
						 onfocus="this.blur()">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopDate" name="stopDate" value="<fmt:formatDate value='${section.stopDate}' pattern='yyyy-MM-dd'/>" readonly>
					</div>
				</div>
			</div>
			
			<div class="infh1">设施参数</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">参数名称</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="facilityParamName" value="${section.facilityParamName}"> --%>
						<div id="facilityParamName" data-code="工段【参数名称】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">设计值</label>
					<div class="infpCon">
						<input type="" class="input" name="facilityDesignValue" value="${section.facilityDesignValue}">
						
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="facilityUnit" value="${section.facilityUnit}"> --%>
					<div class="selF" name="facilityUnit" id="facilityUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否封闭作业</label>
					<div class="infpCon">
						<div class="infpCon">
						<input type="radio" class="radio" id="isSealing1" name="isSealing" value="是"
						<c:if test="${section.isSealing=='是' }">checked='checked'</c:if>>
						<label for="isSealing1" class="label">是</label>
						<input type="radio" class="radio" id="isSealing2" name="isSealing" value="否"
						<c:if test="${section.isSealing=='否' }">checked='checked'</c:if>>
						<label for="isSealing2" class="label">否</label>
					</div>
					</div>
				</div>
			</div>
			
			<div id = "rawMaterialsDiv" style="display: none">
				<div class="infh1">油漆/涂料/塑粉</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油漆/涂料用量</label>
						<div class="infpCon">
							<input type="hidden" class="input" name="rawMaterials1.name" value="油漆/涂料"/>
							<input type="" class="input" name="rawMaterials1.consumption" number=true
							value="${section.rawMaterials1.consumption}" >
							<input type="hidden" class="input" name="rawMaterials1.unit" value="吨"/>
						</div>
					</div>
				</div>
				
				
				<div class="infPara">
					<div class="infParaCon">
						
						<label for="" class="infpLable">塑粉用量</label>
						<div class="infpCon">
							<input type="hidden" class="input" name="rawMaterials2.name" value="塑粉"/>
							<input type="" class="input" name="rawMaterials2.consumption" number=true
							value="${section.rawMaterials2.consumption}" >
							<input type="hidden" class="input" name="rawMaterials2.unit" value="吨"/>
						</div>
						
					</div>
				</div>
				
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">稀释剂用量</label>
						<div class="infpCon">
							<input type="hidden" class="input" name="rawMaterials3.name" value="稀释剂"/>
							<input type="" class="input" name="rawMaterials3.consumption" number=true
							value="${section.rawMaterials3.consumption}">
							<input type="hidden" class="input" name="rawMaterials3.unit" value="吨"/>
						</div>
					</div>
				</div>
				
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">固化剂用量</label>
						<div class="infpCon">
							<input type="hidden" class="input" name="rawMaterials4.name" value="固化剂"/>
							<input type="" class="input" name="rawMaterials4.consumption" number=true 
							value="${section.rawMaterials4.consumption}">
							<input type="hidden" class="input" name="rawMaterials4.unit" value="吨"/>
						</div>
					</div>
				</div>
				
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">其他有机溶剂用量</label>
						<div class="infpCon">
							<input type="hidden" class="input" name="rawMaterials5.name" value="其他有机溶剂"/>
							<input type="" class="input" name="rawMaterials5.consumption" number=true 
							value="${section.rawMaterials5.consumption}">
							<input type="hidden" class="input" name="rawMaterials5.unit" value="吨"/>
						</div>
					</div>
				</div>
				
					
			</div>

			

			<%-- <div class="infh1">原料信息1</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheA1" name="rawMaterials1.rawMaterialsType" value="organic" <c:if test="${section.rawMaterials1.rawMaterialsType=='organic' }">checked='checked'</c:if>>
						<label for="cheA1" class="label">是</label>
						<input type="radio" class="radio" id="cheA2" name="rawMaterials1.rawMaterialsType" value="inorganic" <c:if test="${section.rawMaterials1.rawMaterialsType=='inorganic' }">checked='checked'</c:if>>
						<label for="cheA2" class="label">否</label>
					</div>
				</div>
			</div>

			<input type="hidden" name="rawMaterials1.id" value="${section.rawMaterials1.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.name" value="${section.rawMaterials1.name }" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.consumption" 
						value="<fmt:formatNumber value="${section.rawMaterials1.consumption}" pattern="#.##"/>"
						 number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.unit" value="${section.rawMaterials1.unit}" required>
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
						<input type="radio" class="radio" id="cheB1" name="rawMaterials1.solventType" value="oil" <c:if test="${section.rawMaterials1.solventType=='oil' }">checked='checked'</c:if>>
						<label for="cheB1" class="label">油性</label>
						<input type="radio" class="radio" id="cheB2" name="rawMaterials1.solventType" value="water" <c:if test="${section.rawMaterials1.solventType=='water' }">checked='checked'</c:if>>
						<label for="cheB2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.vocRate" value="${section.rawMaterials1.vocRate}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.vocVolatility" value="${section.rawMaterials1.vocVolatility}">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息2</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheC1" name="rawMaterials2.rawMaterialsType" value="organic" <c:if test="${section.rawMaterials2.rawMaterialsType=='organic' }">checked='checked'</c:if>>
						<label for="cheC1" class="label">是</label>
						<input type="radio" class="radio" id="cheC2" name="rawMaterials2.rawMaterialsType" value="inorganic" <c:if test="${section.rawMaterials2.rawMaterialsType=='inorganic' }">checked='checked'</c:if>>
						<label for="cheC2" class="label">否</label>
					</div>
				</div>
			</div>

			<input type="hidden" name="rawMaterials2.id" value="${section.rawMaterials2.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.name" value="${section.rawMaterials2.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.consumption" 
						value="<fmt:formatNumber value="${section.rawMaterials2.consumption}" pattern="#.##"/>" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.unit" value="${section.rawMaterials2.unit}">
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
						<input type="radio" class="radio" id="cheD1" name="rawMaterials2.solventType" value="oil" <c:if test="${section.rawMaterials2.solventType=='oil' }">checked='checked'</c:if>>
						<label for="cheD1" class="label">油性</label>
						<input type="radio" class="radio" id="cheD2" name="rawMaterials2.solventType" value="water" <c:if test="${section.rawMaterials2.solventType=='water' }">checked='checked'</c:if>>
						<label for="cheD2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.vocRate" value="${section.rawMaterials2.vocRate}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.vocVolatility" value="${section.rawMaterials2.vocVolatility}">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息3</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheE1" name="rawMaterials3.rawMaterialsType" value="organic" <c:if test="${section.rawMaterials3.rawMaterialsType=='organic' }">checked='checked'</c:if>>
						<label for="cheE1" class="label">是</label>
						<input type="radio" class="radio" id="cheE2" name="rawMaterials3.rawMaterialsType" value="inorganic" <c:if test="${section.rawMaterials3.rawMaterialsType=='inorganic' }">checked='checked'</c:if>>
						<label for="cheE2" class="label">否</label>
					</div>
				</div>
			</div>

			<input type="hidden" name="rawMaterials3.id" value="${section.rawMaterials3.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.name" value="${section.rawMaterials3.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.consumption" 
						value="<fmt:formatNumber value="${section.rawMaterials3.consumption}" pattern="#.##"/>" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.unit" value="${section.rawMaterials3.unit}">
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
						<input type="radio" class="radio" id="cheF1" name="rawMaterials3.solventType" value="oil" <c:if test="${section.rawMaterials3.solventType=='oil' }">checked='checked'</c:if>>
						<label for="cheF1" class="label">油性</label>
						<input type="radio" class="radio" id="cheF2" name="rawMaterials3.solventType" value="water" <c:if test="${section.rawMaterials3.solventType=='water' }">checked='checked'</c:if>>
						<label for="cheF2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.vocRate" value="${section.rawMaterials3.vocRate}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.vocVolatility" value="${section.rawMaterials3.vocVolatility}">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息4</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheG1" name="rawMaterials4.rawMaterialsType" value="organic" <c:if test="${section.rawMaterials4.rawMaterialsType=='organic' }">checked='checked'</c:if>>
						<label for="cheG1" class="label">是</label>
						<input type="radio" class="radio" id="cheG2" name="rawMaterials4.rawMaterialsType" value="inorganic" <c:if test="${section.rawMaterials4.rawMaterialsType=='inorganic' }">checked='checked'</c:if>>
						<label for="cheG2" class="label">否</label>
					</div>
				</div>
			</div>

			<input type="hidden" name="rawMaterials4.id" value="${section.rawMaterials4.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.name" value="${section.rawMaterials4.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.consumption" 
						value="<fmt:formatNumber value="${section.rawMaterials4.consumption}" pattern="#.##"/>" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.unit" value="${section.rawMaterials4.unit}">
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
						<input type="radio" class="radio" id="cheH1" name="rawMaterials4.solventType" value="oil" <c:if test="${section.rawMaterials4.solventType=='oil' }">checked='checked'</c:if>>
						<label for="cheH1" class="label">油性</label>
						<input type="radio" class="radio" id="cheH2" name="rawMaterials4.solventType" value="water" <c:if test="${section.rawMaterials4.solventType=='water' }">checked='checked'</c:if>>
						<label for="cheH2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.vocRate" value="${section.rawMaterials4.vocRate}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.vocVolatility" value="${section.rawMaterials4.vocVolatility}">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息5</div>
			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheI1" name="rawMaterials5.rawMaterialsType" value="organic" <c:if test="${section.rawMaterials5.rawMaterialsType=='organic' }">checked='checked'</c:if>>
						<label for="cheI1" class="label">是</label>
						<input type="radio" class="radio" id="cheI2" name="rawMaterials5.rawMaterialsType" value="inorganic" <c:if test="${section.rawMaterials5.rawMaterialsType=='inorganic' }">checked='checked'</c:if>>
						<label for="cheI2" class="label">否</label>
					</div>
				</div>
			</div>

			<input type="hidden" name="rawMaterials5.id" value="${section.rawMaterials5.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.name" value="${section.rawMaterials5.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.consumption" 
						value="<fmt:formatNumber value="${section.rawMaterials5.consumption}" pattern="#.##"/>" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.unit" value="${section.rawMaterials5.unit}">
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
						<input type="radio" class="radio" id="cheJ1" name="rawMaterials5.solventType" value="oil" <c:if test="${section.rawMaterials5.solventType=='oil' }">checked='checked'</c:if>>
						<label for="cheJ1" class="label">油性</label>
						<input type="radio" class="radio" id="cheJ2" name="rawMaterials5.solventType" value="water" <c:if test="${section.rawMaterials5.solventType=='water' }">checked='checked'</c:if>>
						<label for="cheJ2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.vocRate" value="${section.rawMaterials5.vocRate}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.vocVolatility" value="${section.rawMaterials5.vocVolatility}">
					</div>
				</div>
			</div> --%>

			<div class="infh1">产品信息</div>

			<input type="hidden" name="product.id" value="${section.product.id }" />

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>产品名称</label>
					<div class="infpCon">
						<input type="" class="input" name="product.name" value="${section.product.name }">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>产品年产量</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield" 
						value="<fmt:formatNumber value="${section.product.yield}" pattern="#.##"/>"
						 number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="product.unit" value="${section.product.unit }" id="productUnit"> --%>
						<div class="selF" name="product.unit" id="productUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>设计年产能</label>
					<div class="infpCon">
						<input type="" class="input" name="productCapacity"
						value="<fmt:formatNumber value="${section.productCapacity}" pattern="#.##"/>"
						 number=true required>
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
							<input type="" class="input" name="fuelConsumption" min="0" number=true required value="${section.fuelConsumption}">
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
					<label for="" class="infpLable">排放形式</label>
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
						<textarea class="textarea" name="description">${section.description}</textarea>
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>