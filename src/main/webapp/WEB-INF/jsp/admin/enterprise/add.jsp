<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=ZwvKDkc8AosMeMixrVVDfEqK"></script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<title>添加企业</title>
</head>
<style>
#map {
	width: 100%;
	height: 400px;
}
</style>
<script type="text/javascript">
	  require(["validate","upload","ajaxform","select"],function(a,upload){
	  	
	  	var inpLon,inpLat,latMaxj,latMinj,lonMaxj,lonMinj,cityCode,countyCode,returnCode,countyName,cityName;
		  
			//上传
		upload.upload({element:"#uploadPhoto",acceptCallBack:function(handle,data){
				$("#fileName").html(handle.file.name);
				$("#photoUrl").val(data.url);
				$("#photoImg ").attr("src",data.url);
				console.log(data.url);
				}
			});
		var map = new BMap.Map("map"); // 创建Map实例
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
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
						qy.fail2({title:"地址不存在！"});
					}
				}else{
					qy.fail2({title:"地址不存在！"});
				}
			}
		});


		$("#latitude","#inputForm").parent().find('input').on('change',function(){
		  var inputs = $("#latitude","#inputForm").parent().find('input');
		  var val1 = parseFloat(inputs.eq(1).val()) || 0;
		  var val2 = parseFloat(inputs.eq(2).val()) || 0;
		  var val3 = parseFloat(inputs.eq(3).val()) || 0;
		  var valA;
		  if(val1 < 0){
			  valA = (val1*-1) + (val2/60) + (val3/3600);
			  valA = valA * -1;
		  }else{
			  valA = val1 + val2/60 + val3/3600;
		  }
		  $('#latitude').val(valA);
		  addressChange();
		});
		$("#longitude","#inputForm").parent().find('input').on('change',function(){
		  var inputs = $("#longitude","#inputForm").parent().find('input');
		  var val1 = parseFloat(inputs.eq(1).val()) || 0;
		  var val2 = parseFloat(inputs.eq(2).val()) || 0;
		  var val3 = parseFloat(inputs.eq(3).val()) || 0;
		  var valA;
		  if(val1 < 0){
			  valA = (val1*-1) + val2/60 + val3/3600;
			  valA = valA * -1;
		  }else{
			  valA = val1 + val2/60 + val3/3600;
		  }
		  $('#longitude').val(valA);
		  addressChange()
		});
		
		function addressChange(){
			map.clearOverlays();
		  if($("#latitude","#inputForm").val() != "" && $("#longitude","#inputForm").val() != ""){
		    var point = new BMap.Point($("#longitude","#inputForm").val(),$("#latitude","#inputForm").val() );
		    var marker = new BMap.Marker(point);
		    map.addOverlay(marker);
		    map.panTo(point);
		  };
		/*------------获取到输入的经纬度start------------------*/
			inpLon = $("#longitude","#inputForm").val();
			inpLat = $("#latitude","#inputForm").val();
		/*------------获取到输入的经纬度end------------------*/
		};
		
		var categoryMain =  $("#industryCategoryCodeMain","#inputForm").select({filter:true,name:"industryCategoryCodeMain",zIndex:8,param:"categoryLevel:MAIN",change:function(event, value){
		 var text = categoryMain.getSelectCaption();
		 $("input[name=industryCategoryNameMain]").val(text);
		 if(value == ''){
		   $("input[name=industryCategoryNameMain]").val('');
		 }
		 $("input[name=industryCategoryNameMiddle]").val('');
		 categoryMiddle.loadData("parent:"+value)
		}});
		var categoryMiddle =  $("#industryCategoryCodeMiddle","#inputForm").select({name:"industryCategoryCodeMiddle",zIndex:8,change:function(event, value){
		 var text = categoryMiddle.getSelectCaption();
		 $("input[name=industryCategoryNameMiddle]").val(text);
		 if(value == ''){
		   $("input[name=industryCategoryNameMiddle]").val('');
		 }
		}});
		
		var provinces = $("#provinces","#inputForm").select({
				name:"address.provinces.code",
				defaultValue:"${project.provinces}",
				clearEvent:function(){city.clear();},
				change:function(event, value){
				  city.clear();
				  city.loadData("parent:"+value);
				}
			});
			
		cityCode = "${project.city}";
		
		var city = $("#city","#inputForm").select({
			name:"address.city.code",
			"initLoad":"${project.provinces}"?true:false,
			defaultValue:"${project.city}",
			param:'parent:${project.provinces}',
			clearEvent:function(){county.clear();},
			change:function(event, value){
				 county.clear();
				 county.loadData("parent:"+value);
				 cityCode = value;
				 cityName = county.getSelectNameOfValue(value);
				}
			});
		var county = $("#county","#inputForm").select({
				name:"address.county.code",
				"initLoad":"${project.city}"?true:false,
				param:'parent:${project.city}',
				clearEvent:function(){street.clear();},
				change:function(event, value){
					street.clear();
					street.loadData("parent:"+value);
					countyCode = value;
					countyName = county.getSelectNameOfValue(value)
				}
		});
		var street = $("#street","#inputForm").select({name:"address.street.code","initLoad":false}); 
		
		var enterpriseType = $("#enterpriseType","#inputForm").select({name:"enterpriseType",zIndex:10,selected:true,change:function(event, value){
			console.log(enterpriseType.getSelectCaption());
			if(enterpriseType.getSelectCaption()=="单独锅炉（非工业）"){
				$("#addName").html("企业名称");
				$("#industryCategory").hide();
				$("#organizationCode").hide();
				$("#legalPerson").hide();
			}else if(enterpriseType.getSelectCaption()=="施工工地"){
				$("#addName").html("工地名称");
				$("#industryCategory").hide();
				$("#organizationCode").hide();
				$("#legalPerson").hide();
			}else if(enterpriseType.getSelectCaption()=="加油站/加气站/储油库"){
				$("#addName").html("企业名称");
				$("#industryCategory").hide();
				$("#organizationCode").show();
				$("#legalPerson").hide();
			}else if(enterpriseType.getSelectCaption()=="干洗企业"){
				$("#addName").html("企业名称");
				$("#industryCategory").hide();
				$("#organizationCode").show();
				$("#legalPerson").hide();
			}else if(enterpriseType.getSelectCaption()=="汽修"){
				$("#addName").html("企业名称");
				$("#industryCategory").hide();
				$("#organizationCode").show();
				$("#legalPerson").hide();
			}else if(enterpriseType.getSelectCaption()=="餐饮"){
				$("#addName").html("企业名称");
				$("#industryCategory").hide();
				$("#organizationCode").show();
				$("#legalPerson").hide();
			}else if(enterpriseType.getSelectCaption()=="畜禽养殖"){
				$("#addName").html("企业名称");
				$("#industryCategory").hide();
				$("#organizationCode").show();
				$("#legalPerson").hide();
			}else{
				$("#addName").html("企业名称");
				$("#industryCategory").show();
				$("#organizationCode").show();
				$("#legalPerson").show();
			}
		}});


		
		/* upload.upload();
		$("#uploadPhoto","#inputForm").click(function(){
			if ($(this).hasClass('disabled')) {
				return;
			};
			function acceptCallBack(file,data) {
				console.dir(file);
				console.dir(data);
				
				$("#uploadPhoto","#inputForm").val(data.url);
				 qy.ajax({
					url :base+"/web/enterprise/upload.jhtml",
					data:{
						url:data.url,
						filename:file.file.name,
					},
					callBack:function(data2,code){
						if(code == "999999"){
							render('上传图片成功！');
						}else{
							qy.fail2({title:'上传文件失败！'});
						}
						if(data2.code =="10000"){
							qy.fail2({title:data2.content});
						}
					}
		
				}) 
			}
			upload.setAcceptCallBack(acceptCallBack);
			var $handle = $("#_defualt_upload_handle").find('input.webuploader-element-invisible');//.attr("accept","image/*")
			$handle.click();
		}); */





	
		  $('#confirm').click(function(){
		   $("#inputForm").submit();
		 })
		 
		 /* $("#isSanluanwu").change(function() { 
			 if($("#isSanluanwu").is(':checked')){
				 if(!$('#organizationCode').is(':hidden')){
					$("#code").removeAttr("required");
					$("#photoUrl").removeAttr("required");
				}
				 
			 }else{
				 if(!$('#organizationCode').is(':hidden')){
					 $("#code").attr("required","true");
					 $("#photoUrl").attr("required","true");
				}else{
					$("#code").removeAttr("required");
					$("#photoUrl").removeAttr("required");
				}
			 }
				
		  }); */
		//提交表单
		
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
					range:[-90,90],
					
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
				}
				
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
				//如果散乱污没选择则社会组织代码不能为空
				if(!$('#organizationCode').is(':hidden')){
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
				
				/*-------发送ajax返回地区code start--------*/
				qy.ajax({
				 	type:"post",
				 	url: base +"/web/getAreaCode.jhtml",
				 	data:{
				 		lngs: inpLon,
				 		lats: inpLat
				 	},
				 	callBack: function(data){
						returnCode = data.adcode;
						/*----------ajax获取来源 start-----------*/
						qy.ajax({
						 	type:"post",
						 	url: base +"/web/enterprise/viewArea.jhtml?code="+countyCode,
						 	callBack: function(datas){
								var source = datas.source;
								if(source == null){
									qy.fail2({title:'没有地区来源'});
								}
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
				/*-------发送ajax返回code end--------*/
				function opt(){
					var options = {
		                   url:'save.jhtml',
		                   type:'post',
		                   dataType : 'json',
		                   success:function(data){
		                	   if(data.code == '20000'){
		                		   qy.suc2({title:'添加成功！'});
		                           layer.closeAll('page');
		                           grid1.loadData();
		                	   }else{
		                		   qy.fail2({title:data.content});
		                	   }
		                      
		                  },
		                  error:function(){
		                    qy.fail2({title:'添加失败'});
		                } 
		            }; 
		            $(form).ajaxSubmit(options);
            		return false;
				}
				 
           
        }
    });
  
  
	
	

  });
</script>
<body>
	<form id="inputForm" style="padding: 0; width: 100%;">

		<input type="hidden" name="industryCategoryNameMain" /> 
		<input type="hidden" name="industryCategoryNameMiddle" /> 
		<input type="hidden" name="status" value="1" /> 
		<input type="hidden" name="token" value="${token}" />
		
		
		<div class="infLine">


				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer" required>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone" required>
						</div>
					</div>
				</div>


			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable" id="addName">企业名称</label>
					<div class="infpCon">
						<input type="" class="input" name="name" required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">填表类型</label>
					<div class="infpCon">
						<div id="enterpriseType" data-code="企业【企业类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara infPara2" id="industryCategory">
				<div class="infParaCon">
					<label for="" class="infpLable">行业类别</label>
					<div class="infpCon">
						<div class="inputs">
							<div class="inputChild col6">
								<div data-code="行业" id="industryCategoryCodeMain" class="selF"></div>
							</div>
							<div class="inputChild col6">
								<div data-code="行业" id="industryCategoryCodeMiddle" class="selF"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="infPara infPara2" id="organizationCode">
				<div class="infParaCon">
					<label for="" class="infpLable">组织机构代码/统一社会信用代码</label>
					<div class="inputChild col4">
						<input type="" class="input" id="code" name="code">
					</div>
					<div class="inputChild col4">
						<input type="hidden"  id="photoUrl" name="photoUrl"  />
						<input type="button" id="uploadPhoto" value="上传营业执照" style="width: 110px;" ></input>
						<img id="photoImg" src="" width="100px" >
					</div>
					<div  class="inputChild col4">
						<label for="isSanluanwu" class="label">散乱污</label>
						 <input type="checkbox"  id="isSanluanwu" name="isSanluanwu" value="是"  />
					</div>
					
				</div>
			</div>

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
				<div class="infParaCon">
					<label for="" class="infpLable">详细地址</label>
					<div class="infpCon">
						<input type="" class="input" name="address.houseNumber">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">经度</label>
					<div class="infpCon">
						<div class="inputs">
							<input type="hidden" name="address.longitude" id="longitude" />
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
					<label for="" class="infpLable">纬度</label>
					<div class="infpCon">
						<div class="inputs">
							<input type="hidden" name="address.latitude" id="latitude" />
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

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable">联系人</label>
					<div class="infpCon">
						<input type="" class="input" name="contacts" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable">联系电话</label>
					<div class="infpCon">
						<input type="" class="input" name="contactNumber" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3" id="legalPerson">
				<div class="infParaCon">
					<label for="" class="infpLable">法人</label>
					<div class="infpCon">
						<input type="" class="input" name="corporation" required>
					</div>
				</div>
			</div>
			
			<div class="infPara infPara3" >
				<div class="infParaCon">
					<label for="" class="infpLable">邮箱</label>
					<div class="infpCon">
						<input type="" class="input" name="email" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara2">
				<div id="map"></div>
			</div>

		</div>
	</form>
</body>
</html>