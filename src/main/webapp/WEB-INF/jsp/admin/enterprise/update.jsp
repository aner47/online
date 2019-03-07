<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/admin/dialogEdit/dialogEdit.css" />
<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta charset="UTF-8">
<title>企业信息编辑</title>
</head>
<style>
#map {
	width: 100%;
	height: 400px;
}
</style>
<script type="text/javascript">
  require(["validate","upload","ajaxform","select"],function(a,upload){ 
	  	var inpLon,inpLat,latMaxj,latMinj,lonMaxj,lonMinj,cityCode,countyCode,returnCode;
	  	var countyName,cityName;
		  $('[name=isEmergency]').on('click', function(){
			  if ($(this).val() === '是') {
				  $('#cheC1_1').removeAttr('disabled')
				$('#cheC1_1').removeAttr('checked')		
				} else {
					$('#cheC1_1').attr('disabled', 'disabled')
					$('#cheC1_1').removeAttr('checked')
				}
		  })
		  if (${enterprise.isEmergency == '否'}){
			  $('#cheC1_1').attr('disabled', 'disabled')
				$('#cheC1_1').removeAttr('checked')
		  }
		  if (${enterprise.isEmergency == '否'} && ${enterprise.isStaggerFastigium == '是'} ) {
			  $('#cheC1_1').attr('disabled', 'disabled')
				$('#cheC1_1').removeAttr('checked')
		  }
	  	/* $('[name=isEmergency]').trigger('click'); */
		
	//上传
	upload.upload({element:"#uploadPhoto",postfixType:"img",acceptCallBack:function(handle,data){
		console.log(data);
		if(data.message.code == 20000){
			$("#fileName").html(handle.file.name);
			$("#photoUrl").val(data.url);
			$("#photoImg ").attr("src",data.url);
			console.log(data.url);
		}else{
			qy.suc2({title:"请上传正确的图片格式"});
		}
		
		}
	});
	
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
	
    // 经纬度的渲染
    (function(){
      	var d = {};
      	d.lat = parseFloat($("#latitude","#inputForm").val());
      	d.log = parseFloat($("#longitude","#inputForm").val());
      	inpLat = d.lat;
      	inpLon = d.log;
      
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
	    	  
		      $("#latitude","#inputForm").parent().find('input').each(function(k,v){
		        if (k==0) {return};
		        $(v).val(d['lat'+k]);
		      })
	      }
	      if(!isNaN(d.log)){
		      $("#longitude","#inputForm").parent().find('input').each(function(k,v){
		        if (k==0) {return};
		        $(v).val(d['log'+k]);
		      })
	      }
    })();
    $("#latitude","#inputForm").parent().find('input').on('change',function(){
    	var inputs = $("#latitude","#inputForm").parent().find('input');
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
      	$('#latitude').val(valA);
      	addressChange();
    })
    $("#longitude","#inputForm").parent().find('input').on('change',function(){
      var inputs = $("#longitude","#inputForm").parent().find('input');
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
      $('#longitude').val(valA);
     	 addressChange()
    })
    function addressChange(){
    	map.clearOverlays();
	    if($("#latitude","#inputForm").val() != "" && $("#longitude","#inputForm").val() != ""){
	        var point = new BMap.Point($("#longitude","#inputForm").val(),$("#latitude","#inputForm").val() );
	        var marker = new BMap.Marker(point);
	        map.addOverlay(marker);
	        map.panTo(point);
	    }
      	/*------------获取到输入的经纬度------------------*/
		inpLon = $("#longitude","#inputForm").val();
		inpLat = $("#latitude","#inputForm").val();
    }
    
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	
   /*  // 鼠标控制点击切换经纬度 
	map.addEventListener("click",function(e){
		inpLon = $("#longitude","#inputForm").val(e.point.lng);
		inpLat = $("#latitude","#inputForm").val(e.point.lat);
		addressChange()
	    //alert(e.point.lng + "," + e.point.lat);
	}); */
	
	$('#confirm').click(function(){
	  	$("#inputForm").submit();
	})
//提交表单
$("#inputForm").validate({
	rules: {
		inquirerPhone:{
			isTelephone : true
		},
		name:{
			maxlength:50,
			required:true
		},
		enterpriseType:{
			required:true
		},
		"address.provinces.code":{
			required:true
		},
		"address.city.code":{
			required:true
		},
		"address.county.code":{
			required:true
		},
		"address.houseNumber":{
			maxlength:255
		},
		contacts:{
			maxlength:20
		},
		contactNumber : {
			isTelephone : true
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
		code:{
			maxlength:30
		},
		industryCategoryCodeMain:{
			required:true
		},
		industryCategoryCodeMiddle:{
			required:true
		},
		projectType : {
			required : true
		},
		productIndustry : {
			required : true
		},
	},
	messages:{
		inquirerPhone:{
			isTelephone : "格式错误！"
		},
		addresstemp1:{
			range:"-180~180"
		},
		addresstemp4:{
			range:"-90~90"
		},
		contacts:{
			maxlength:"超出20字限制"
		},
		enterpriseType:{
			required:"必选字段"
		},
		"address.provinces.code":{
			required:"必选字段"
		},
		"address.city.code":{
			required:"必选字段"
		},
		"address.county.code":{
			required:"必选字段"
		},
	},
	submitHandler:function(form){
		if(!$('#inputForm #organizationCode').is(':hidden')){
			if(!$('#isSanluanwu').is(':checked')){
				if($.trim($("#code","#inputForm").val()).length == 0){
					qy.fail2({title:'组织机构代码必填！'});
					return false;
				}
				if($.trim($("#photoUrl","#inputForm").val()).length == 0){
					qy.fail2({title:'必须上传营业执照！'});
					return false;
				}
			}
		}
		
		//如果选择的是陕西省西咸新区则单独判断。经度大于108.2小于109，纬度大于34小于34.7
		var selectCity = city.getSelectValue();
		var selectCitySource = city.getSelectOtherValue().source;
		console.log(selectCity);
		console.log(city.getSelectOtherValue());
		var maxinpLon = city && city.getSelectOtherValue() && city.getSelectOtherValue().longitudeMax ? city.getSelectOtherValue().longitudeMax : 109;
		var mininpLon = city && city.getSelectOtherValue() && city.getSelectOtherValue().longitudeMin ? city.getSelectOtherValue().longitudeMin:parseFloat(108.2);
		var maxinpLat = city && city.getSelectOtherValue() && city.getSelectOtherValue().latitudeMax ? city.getSelectOtherValue().latitudeMax:parseFloat(34.7);
		var mininpLat = city && city.getSelectOtherValue() && city.getSelectOtherValue().latitudeMin ?city.getSelectOtherValue().latitudeMin : 34;
		
		if(selectCitySource == 2){
			console.log("判断自定义经纬度范围输入经度inpLon--"+inpLon);
			console.log("判断自定义经纬度范围输入纬度inpLat--"+inpLat);
			console.log(mininpLon);
			console.log(maxinpLon);
			console.log(mininpLat);
			console.log(maxinpLat);
			console.log(inpLon<mininpLon);
			console.log(inpLon>maxinpLon);
			console.log(inpLat<mininpLat);
			console.log(inpLat>maxinpLat);
			if(inpLon<mininpLon || inpLon>maxinpLon || inpLat<mininpLat || inpLat>maxinpLat){
				qy.fail2({title:'经纬度不在范围之内'});
				return false;
			}else{
				opt();
			}
			
		}else{
			
			/*-------发送ajax返回地区code start--------*/
			qy.ajax({
			 	type:"post",
			 	url:"../getAreaCode.jhtml",
			 	data:{
			 		lngs: inpLon,
			 		lats: inpLat
			 	},
			 	callBack: function(data){
					returnCode = data.adcode;
					console.log(returnCode)
					/*----------ajax获取来源 start-----------*/
					qy.ajax({
					 	type:"post",
					 	url:"../enterprise/viewArea.jhtml?code="+countyCode,
					 	callBack: function(datas){
							var source = datas.source;
							if(source == 1){
								var code2 = returnCode+'';
								if(countyCode != code2){
									if(countyName){
										qy.fail2({title:'不在'+countyName+'经纬度范围之内'});
									}else{
										qy.fail2({title:'经纬度不在范围之内'});
									}
									
									
									return false;
								}else{
									opt()
								}
							};
							
							if(source == 2){
								var code1 = returnCode+"";
								var returnCode2 = code1.substring(0,4)+"00";
								if(cityCode != returnCode2){
									if(cityName){
										qy.fail2({title:'不在'+cityName+'经纬度范围之内'});
									}else{
										qy.fail2({title:'经纬度不在范围之内'});
									}
									
									return false;
								}else{
									opt()
								}
							};
							
					 	}
					});
					/*----------ajax获取来源 end-----------*/
			 	}
			});
				
		}
		/*-------发送ajax返回code end--------*/
		function opt(){
			var options  = {
                  url:'update.jhtml',
                  type:'post',
                  dataType : 'json',
                  success:function(data){
               	   if(data.code == '20000'){
               		   qy.suc2({title:'修改成功！'})
                          layer.closeAll('page');
                          grid1.loadData();
               	   }else{
               		   qy.fail2({title:data.content});
               	   }
                     
                 },
                 error:function(){
                   qy.fail2({title:'修改失败'});
                }
            };
            $(form).ajaxSubmit(options);
            return false;
		}
        }
    });		
	var productIndustry =  $("#inputForm #productIndustry").select({name:"productIndustry",param:"project.id:"+'${project.id}',defaultValue:'${enterprise.productIndustry}'
			,zIndex:16,change:function(event, value){}
		});
	var enterpriseType = $("#enterpriseType","#inputForm").select({name:"enterpriseType",immutable:false,defaultValue:'${enterprise.enterpriseType}',zIndex:14,change:function(event, value){
   		projectType.loadData("project:"+'${projectId}'+",enterpriseType:"+value);
	},initAfter:function(data,value,other){
		projectType.loadData("project:"+'${projectId}'+",enterpriseType:"+value);
	},
	});
   	var projectType = $("#projectType","#inputForm").select({name:"projectType",param:"project:"+'${projectId}'+",enterpriseType:"+'${enterprise.enterpriseType}',defaultValue:'${projectType}',zIndex:13});
	var categoryMain =  $("#inputForm #industryCategoryCodeMain").select({filter:true,name:"industryCategoryCodeMain",defaultValue:'${enterprise.industryCategoryCodeMain}'
			,zIndex:12,param:"categoryLevel:MAIN",change:function(event, value){
			var text = categoryMain.getSelectCaption();
        	$("input[name=industryCategoryNameMain]").val(text);
        	if(value == ''){
       	 		$("input[name=industryCategoryNameMain]").val('');
        	}
	   	 	$("input[name=industryCategoryNameMiddle]").val('');
	   	 	categorySmall.clear();
	   	 	categoryMiddle.clear();
	        categoryMiddle.loadData("parent:"+value);
	        
    	}});
	var main = '${enterprise.industryCategoryCodeMain}';
	var param = main?"parent:"+main:'';
	var categoryMiddle =  $("#inputForm #industryCategoryCodeMiddle").select({name:"industryCategoryCodeMiddle",defaultValue:'${enterprise.industryCategoryCodeMiddle}',param:param,zIndex:11,change:function(event, value){
    		var text = categoryMiddle.getSelectCaption();
        	$("input[name=industryCategoryNameMiddle]").val(text);
        	if(value == ''){
       	 	$("input[name=industryCategoryNameMiddle]").val('');
        	}
        	categorySmall.loadData("parent:"+value);
    	}});
		
	var middle = '${enterprise.industryCategoryCodeMiddle}';
	var parammiddle = middle?"parent:"+middle:'';
	var categorySmall =  $("#inputForm #industryCategoryCodeSmall").select({name:"industryCategoryCodeSmall",defaultValue:'${enterprise.industryCategoryCodeSmall}',param:parammiddle,zIndex:10,change:function(event, value){
    		var text = categorySmall.getSelectCaption();
        	$("input[name=industryCategoryNameSmall]").val(text);
        	if(value == ''){
       	 	$("input[name=industryCategoryNameSmall]").val('');
        	}
    	}});
		
	var provinces = $("#inputForm #provinces").select({filter:true,name:"address.provinces.code",zIndex:9,defaultValue:'${enterprise.address.provinces.code}',change:function(event, value){
    	city.clear();
    	city.loadData("parent:"+value);
  		}});
   
   	//市code默认值
	cityCode = "${enterprise.address.city.code}";
	cityName = "${enterprise.address.city.name}";
    var city = $("#inputForm #city").select({name:"address.city.code",zIndex:8,initLoad:false,defaultValue:'${enterprise.address.city.code}',clearEvent:function(){county.clear();},change:function(event, value){
     county.clear()
     county.loadData("parent:"+value);
     cityCode = value;
     cityName = county.getSelectNameOfValue(value);
   }});
   
   	//县code默认值
   	countyCode = '${enterprise.address.county.code}';
   	countyName = '${enterprise.address.county.name}';
    var county = $("#county").select({
	    	name:"address.county.code",zIndex:7,
	    	defaultValue:'${enterprise.address.county.code}',
	    	clearEvent:function(){street.clear();},
	    	initLoad:false,
	    	change:function(event, value){
			     street.clear();
			     street.loadData("parent:"+value);
			     countyCode = value;
			     countyName = county.getSelectNameOfValue(value)
			}
    	});
    
    var street = $("#street").select({name:"address.street.code",zIndex:6,initLoad:false,defaultValue:'${enterprise.address.street.code}'}); 
    if('${enterprise.address.provinces.code}'){
    	city.loadData("parent:"+'${enterprise.address.provinces.code}');
    }
    if('${enterprise.address.city.code}'){
    	county.loadData("parent:"+'${enterprise.address.city.code}');
   	}
   	if('${enterprise.address.county.code}'){
    	street.loadData("parent:"+'${enterprise.address.county.code}');
   	}

   	
  		
   	console.log('${enterprise.enterpriseType}');
  		if("${enterprise.enterpriseType}"=="SINGLE_BOILER"){
	  		$("#addName").html("企业名称");
			$("#industryCategory").hide();
			$("#organizationCode").hide();
			$("#productIndustrydiv").hide();
			$("#isEmergencydiv").hide();
			$("#isStaggerFastigiumdiv").hide();
			$("#legalPerson").hide();
		}else if("${enterprise.enterpriseType}"=="CONSTRUCTION_SITE"){
			$("#addName").html("工地名称");
			$("#industryCategory").hide();
			$("#organizationCode").hide();
			$("#productIndustrydiv").hide();
			$("#isEmergencydiv").hide();
			$("#isStaggerFastigiumdiv").hide();
			$("#legalPerson").hide();
		}else if("${enterprise.enterpriseType}"=="PETROL_STATION"){
			$("#addName").html("企业名称");
			$("#industryCategory").hide();
			$("#organizationCode").show();
			$("#productIndustrydiv").hide();
			$("#isEmergencydiv").hide();
			$("#isStaggerFastigiumdiv").hide();
			$("#legalPerson").hide();
		}else if("${enterprise.enterpriseType}"=="DRY_CLEAR"){
			$("#addName").html("企业名称");
			$("#industryCategory").hide();
			$("#organizationCode").show();
			$("#productIndustrydiv").hide();
			$("#isEmergencydiv").hide();
			$("#isStaggerFastigiumdiv").hide();
			$("#legalPerson").hide();
		}else if("${enterprise.enterpriseType}"=="BREAKDOWN_SERVICE"){
			$("#addName").html("企业名称");
			$("#industryCategory").hide();
			$("#organizationCode").show();
			$("#productIndustrydiv").hide();
			$("#isEmergencydiv").hide();
			$("#isStaggerFastigiumdiv").hide();
			$("#legalPerson").hide();
		}else if("${enterprise.enterpriseType}"=="CATERING"){
			$("#addName").html("企业名称");
			$("#industryCategory").hide();
			$("#organizationCode").show();
			$("#productIndustrydiv").hide();
			$("#isEmergencydiv").hide();
			$("#isStaggerFastigiumdiv").hide();
			$("#legalPerson").hide();
		}else if("${enterprise.enterpriseType}"=="BEASTS_BIRDS"){
			$("#addName").html("企业名称");
			$("#industryCategory").hide();
			$("#organizationCode").show();
			$("#productIndustrydiv").hide();
			$("#isEmergencydiv").hide();
			$("#isStaggerFastigiumdiv").hide();
			$("#legalPerson").hide();
		}else{
			$("#addName").html("企业名称");
			$("#industryCategory").show();
			$("#organizationCode").show();
			$("#legalPerson").show();
		}
  		
  		$(".js-photoClickBig").on("click", function() {
  			$(".js-photoImgBigShow").css({
  				"display": "block"
  			})
  		})
  		$(".js-closeBigImg").on("click", function() {
  			$(".js-photoImgBigShow").css({
  				"display": "none"
  			})
  		})
  		
	  });
</script>
<body>
<div style="height: 640px;padding: 5px;">
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
		<form id="inputForm" style="padding: 0; width: 100%;">
			<input type="hidden" id="id" name="id" value="${enterprise.id}" /> <input
				type="hidden" name="industryCategoryNameMain"
				value="${enterprise.industryCategoryNameMain}"> <input
				type="hidden" name="industryCategoryNameMiddle"
				value="${enterprise.industryCategoryNameMiddle}"> <input
				type="hidden" name="industryCategoryNameSmall"
				value="${enterprise.industryCategoryNameSmall}"> 
				<input type="hidden" name="token" value="${token}" />
	
			<div style="padding:10px 0 21px;float: left; width: 50%; border-bottom: 1px dashed #c0c0c0;" class="infLine">
	
	
				<%-- <div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${enterprise.inquirer}" required>
						</div>
					</div>
				</div>
	
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${enterprise.inquirerPhone}" required>
						</div>
					</div>
				</div> --%>
	
	
				<div class="infPara infPara2">
					<div class="infParaCon">
						<!-- <label for="" class="infpLable" id="addName"><span style="margin-right: 5px;color: #eb2a33;">*</span>企业名称</label> -->
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>企业名称</label>
						<div class="infpCon">
							<input type="" class="input" name="name" value="${enterprise.name}" required />
						</div>
					</div>
				</div>
	
				<div class="infPara infPara2" id="productIndustrydiv">
					<div class="infParaCon">
						<c:if test="${sysProductIndustrys != null}">
							<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>产品行业</label>
							<div class="infpCon">
								<div id="productIndustry" data-code="项目【产品行业】" class="selF"></div>
							</div>
						</c:if>
						 
					</div>
				</div>
				<%-- <input type="hidden" class="input" name="enterpriseType" value="${enterprise.enterpriseType}" /> --%>
				  <div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">填表类型</label>
						<div class="infpCon">
							<div id="enterpriseType" data-code="企业【企业类型】" class="selF"></div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">所属项目类型</label>
						<div class="infpCon">
							<div id="projectType" data-code="企业【项目类型】" class="selF"></div>
						</div>
					</div>
				</div> 
				
				<div class="infPara infPara2" id="industryCategory">
					<div style="margin-top:4px;" class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>行业类别</label>
						<div class="infpCon">
							<div data-code="行业" id="industryCategoryCodeMain" class="selF"></div>
						</div>
					</div>
					<div style="margin-top:4px;" class="infParaCon">
						<label style="opacity:0;" for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>行业类别</label>
						<div class="infpCon">
							<div data-code="行业" id="industryCategoryCodeMiddle" class="selF"></div>
						</div>
					</div>
					<div style="margin-top:4px;" class="infParaCon">
						<label style="opacity:0;" for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>行业类别</label>
						<div class="infpCon">
							<div data-code="行业" id="industryCategoryCodeSmall" class="selF"></div>
						</div>
					</div>
					
				</div>
				
				<div style="padding-right:0;" class="infPara" id="isEmergencydiv">
					<div id="infParaCon_cheC1" class="infParaCon infParaCon_cheC1_change">
						<label style="padding-right: 4px;" for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否纳入应急名单</label>
						<div class="infpCon">
							<input type="radio" class="radio" id="cheC1"
									name="isEmergency" value="是" required
									<c:if test="${enterprise.isEmergency=='是' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2"
								 name="isEmergency" value="否" required
								<c:if test="${enterprise.isEmergency=='否' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
						</div>
					</div>
				</div>
				<div style="padding-right:0;" class="infPara"  id="isStaggerFastigiumdiv">
					<div class="infParaCon infParaCon_cheC1_change">
						<label style="padding-right: 4px;" for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否错峰企业</label>
						<div class="infpCon">
							<input type="radio" class="radio" id="cheC1_1"
									name="isStaggerFastigium" value="是" required
									<c:if test="${enterprise.isStaggerFastigium=='是' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2_1"
								 name="isStaggerFastigium" value="否" required
								<c:if test="${enterprise.isStaggerFastigium=='否' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
						</div>
					</div>
				</div>
	
				<div class="infPara infPara2" id="organizationCode">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>组织机构代码(统一社会信用代码)</label>
						<div style="margin-right: 3px;" class="inputChild col4 infpCon">
							<input type="" class="input" id="code" name="code" value="${enterprise.code}">
						</div>
					</div>
					<div class="infParaCon">
						<div style="position: relative;" class="inputChild col4 infpCon">
							<!-- <label style="float: left;" for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>上传营业执照</label> -->
							<label style="float: left;padding-right:0;" for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span></label>
							<input type="hidden" id="photoUrl" name="photoUrl" value="${enterprise.photoUrl}" /> 
							<input type="button" id="uploadPhoto" value="上传营业执照" style="float: left;width: 90px; font-size: 14px;" /> 
							
							<div style="float: left;width: 90px; height: 102px;border:1px dashed #c0c0c0;" class="businessLicense js-photoClickBig">
								<img style="display: block; width: 100%;height: 100%;" id="photoImg" src="${enterprise.photoUrl}" />
							</div>
							<div class="photoImgBigShow js-photoImgBigShow">
								<h6>
									<span class="js-closeBigImg closeBigImg">X</span>
								</h6>
								<img style="width: 100%; height: 100%;" id="photoImg" class="" src="${enterprise.photoUrl}" />
							</div>
						</div>
					</div>
					
				</div>
				
				<div style="padding: 0 20px;" class="infPara infPara2" id="industryCategory">
					<div style="padding-left: 0;" class="infPara">
						<div class="infParaCon">
							<label for="isSanluanwu" class="infpLable"><span style="margin-right: 5px;">-</span>散乱污</label>
							<div style="margin-top: 9px;" class="infpCon">
								<input type="checkbox" id="isSanluanwu" name="isSanluanwu" value="是"
									<c:if test="${enterprise.isSanluanwu=='是' }">checked='checked'</c:if>
								/>
							</div>
						</div>
					</div>
					
				</div>
				
				
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>联系人</label>
						<div class="infpCon">
							<input type="" class="input" name="contacts"
								value="${enterprise.contacts}" required>
						</div>
					</div>
				</div>
				
				<div class="infPara" id="legalPerson">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>法人</label>
						<div class="infpCon">
							<input type="" class="input" name="corporation"
								value="${enterprise.corporation}" required>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>联系电话</label>
						<div class="infpCon">
							<input type="" class="input" name="contactNumber"
								value="${enterprise.contactNumber}" required>
						</div>
					</div>
				   
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;">-</span>邮箱</label>
						<div class="infpCon">
							<input type="" class="input" name="email"
								value="${enterprise.email}" >
						</div>
					</div>	
				</div>
				
			</div>
			
			<div style="float: left; width: 50%; padding:10px 10px 10px; border:1px dashed #c0c0c0;" class="infPara infPara2">
				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>所在地区</label>
						<div class="infpCon">
							<div class="inputs infPara2">
								<div class="inputChild col6">
									<div data-code="地域" id="provinces" class="selF"></div>
								</div>
								<div class="inputChild col6">
									<div data-code="地域" id="city" class="selF"></div>
								</div>
							</div>
						</div>
					</div>
					<div style="margin-top: 4px;" class="infParaCon">
						<label style="opacity:0;" for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>所在地区</label>
						<div class="infpCon">
							<div class="inputs infPara2">
								<div class="inputChild col6">
									<div data-code="地域" id="county" class="selF"></div>
								</div>
								<div class="inputChild col6">
									<div data-code="地域" id="street" class="selF"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
	
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;">-</span>详细地址</label>
						<div class="infpCon">
							<input type="" class="input" name="address.houseNumber" value="${enterprise.address.houseNumber}">
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>经度</label>
						<div class="infpCon">
							<div class="inputs">
								<input type="hidden" name="address.longitude" id="longitude"
									value="${enterprise.address.longitude}" />
								<div class="inputChild col4">
									<input type="" class="input" placeholder="经度"
										name="addresstemp1" required>
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="经分"
										name="addresstemp2" required>
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="经秒"
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
								<input type="hidden" name="address.latitude" id="latitude"
									value="${enterprise.address.latitude}" />
								<div class="inputChild col4">
									<input type="" class="input" placeholder="纬度"
										name="addresstemp4" required>
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="纬分"
										name="addresstemp5" required>
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="纬秒"
										name="addresstemp6" required>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				<div style="width: 100%;" id="map"></div>
			</div>
	</form>
</div>
<p style="font-size: 12px;margin:0 auto;color: #c0c0c0;">- - - - 恭喜完成本页填报 - - - -</p>
</body>
</html>