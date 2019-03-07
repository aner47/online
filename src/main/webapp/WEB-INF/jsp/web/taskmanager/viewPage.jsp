<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title></title>
	<style>
		
		.form{
			background: #F7FAFF;
		}
		.form-row{
			width: 100%;
			border: none;
			float: left;

		}
		.form-row .div{
			float:left;
			padding:10px;
		}
		.label{
			float:left;
			margin-right:10px;
			height:30px;
			line-height:30px;
		}
		.clear{
			clear:both;
		}
		.photo{
			display:none;
		}
		.remarks{
			height:100px;
			display:none;
		}
		.form-row .input{
			margin-top: 2px;
			border: 1px solid #d2d2d2;
			border-radius: 3px;
			height: 26px;
			line-height: 24px;
			padding: 0 4px;
			outline: 0;
		}
		.imgF{
			width: 68px;
			height: 80px;
			position: relative;
			float: left;
			cursor: pointer;
			text-align: center;
			color: #DBDBDB;
			background-color: #FAFAFA;
		}
		.imgF:hover{
			background-color: #267DFF;
			color: #ffffff;
		}
		.imgF::before{
			font-size: 42px;
			display: block;
			width: 100%;
			height: 58px;
			line-height: 58px;
		}
		.imgF::after{
			content:'添加照片';
			font-size: 12px;
		}
		.imgF .img{
			position: absolute;
			top: 0px;
			left: 0px;
			height: 80px;
			min-width: 68px;
			width: 68px;
		}
		.imgF.hasimg .img{
			width: auto;
		}
		.project_user_Search_1 {
			background-color: #4fc1ee !important;
		}
	</style>
	<script type="text/javascript">
		require(["grid","select","panel","upload","radio","date","validate", "ajax"],function(g,s,p,upload){
			if ($("#enterpriseStatus_1 span").html() === "D_05") {
				$("#enterpriseStatus_1 span").html("已搬迁")
			} else if ($("#enterpriseStatus_1 span").html() === "T_01") {
				$("#enterpriseStatus_1 span").html("正常生产")
			} else if ($("#enterpriseStatus_1 span").html() === "T_02") {
				$("#enterpriseStatus_1 span").html("半停产")
			} else if ($("#enterpriseStatus_1 span").html() === "T_03") {
				$("#enterpriseStatus_1 span").html("停产")
			} else if ($("#enterpriseStatus_1 span").html() === "T_04") {
				$("#enterpriseStatus_1 span").html("关停/取缔")
			} else if ($("#enterpriseStatus_1 span").html() === "P_06") {
				$("#enterpriseStatus_1 span").html("企业实体不存在")	
			} else if ($("#enterpriseStatus_1 span").html() === "P_07") {
				$("#enterpriseStatus_1 span").html("仅销售/门店/办公")
			} else if ($("#enterpriseStatus_1 span").html() === "D_08") {
				$("#enterpriseStatus_1 span").html("重复")
			} else if ($("#enterpriseStatus_1 span").html() === "D_09") {
				$("#enterpriseStatus_1 span").html("已上报")
			}
			
			
			if ($("#status_1 span").html() === "processed") {
				$("#status_1 span").html("已处理")
			} else if ($("#status_1 span").html() === "new") {
				$("#status_1 span").html("新任务")
			} else if ($("#status_1 span").html() === "assigned") {
				$("#status_1 span").html("已分配")
			} else if ($("#status_1 span").html() === "complete") {
				$("#status_1 span").html("完成")
			}
			
			
			show('${taskManager.enterpriseStatus}');
			
			function show(value){
				$(".otherpara").addClass('hide');
				$(".D_09").addClass('hide');
				if(value == "D_05"){
					$(".all").removeClass('hide');
					$(".D5").removeClass('hide');
				}else if(value == "P_06"){
					$(".all").removeClass('hide');
				}else if(value == "P_07"){
					$(".all").removeClass('hide');
					$(".P7").removeClass('hide');
				}else if(value == "D_08"){
					$(".all").removeClass('hide');
					$(".D8").removeClass('hide');
				}else if(value == "D_09"){
					$(".D_09").removeClass('hide');
				}
				$('.otherpara.hide input').prop('disabled','disabled');
				$('.otherpara:not(.hide) input').removeProp('disabled');
			}
			

			// 重复企业
			var repeatIdSelect = $('#repeatId').select({immutable: true,name:'repeatId',zIndex:1,defaultValue:'${taskManager.repeatId}'});
			
			
			var provinces = $("#provinces").select({immutable: true,name:"address.provinces.code",zIndex:9
				,defaultValue:'${taskManager.address.provinces.code}'});
			var city = $("#city").select({immutable: true,name:"address.city.code",zIndex:9
				,defaultValue:'${taskManager.address.city.code}'});
			var county = $("#county").select({immutable: true,name:"address.county.code",zIndex:9
				,defaultValue:'${taskManager.address.county.code}'});
			var street = $("#street").select({immutable: true,name:"address.street.code",zIndex:9
				,defaultValue:'${taskManager.address.street.code}'});
			if('${taskManager.address.provinces.code}'){
		    	city.loadData("parent:"+'${taskManager.address.provinces.code}');
		    }
		    if('${taskManager.address.city.code}'){
		    	county.loadData("parent:"+'${taskManager.address.city.code}');
		   	}
		   	if('${taskManager.address.county.code}'){
		    	street.loadData("parent:"+'${taskManager.address.county.code}');
		   	}
		   	
		 // 经纬度的渲染
		    (function(){
		      	var d = {};
		      	d.lat = parseFloat($("#latitude").val());
		      	d.log = parseFloat($("#longitude").val());
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
			    	  
				      $("#latitude").parent().find('input').each(function(k,v){
				        if (k==0) {return};
				        $(v).val(d['lat'+k]);
				      })
			      }
			      if(!isNaN(d.log)){
				      $("#longitude").parent().find('input').each(function(k,v){
				        if (k==0) {return};
				        $(v).val(d['log'+k]);
				      })
			      }
		    })()
})
</script>
</head>
<body>
	<div class="block">
		
		
		<form class="form">
			
			<div id="form_id" class="form-row">
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">企业名称:&ensp;&ensp;${taskManager.enterpriseName}</label>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">联系人:&ensp;&ensp;${taskManager.contactPerson}</label>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">联系电话:&ensp;&ensp;${taskManager.contactPhone}</label>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">区县:&ensp;&ensp;${taskManager.countyName}</label>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">详细地址:&ensp;&ensp;${taskManager.detailAddress}</label>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<div id="enterpriseStatus_1" class="infpLable">企业状态:&ensp;&ensp;<span>${taskManager.enterpriseStatus}</span></div>
					</div>
				</div>
				<div class="infPara infPara2">
					<div class="infParaCon">
						<div id="status_1" class="infpLable">任务状态:&ensp;&ensp; <span>${taskManager.status}</span></div>
					</div>
				</div>
			</div>
			
			<div class="form-row otherpara D_09">
				<div class="div">
					<span class="label"  style="float:left">上报企业编号:&ensp;&ensp;</span>
					<input id="enterpriseId" name="enterpriseId" type="text" class="input" value="${taskManager.inquirer}">
				</div>
				<div class="div" id="showEnterpriseInfo"></div>
			</div>
			
			<div class="form-row otherpara all hide">

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人:&ensp;&ensp;${taskManager.inquirer}</label>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人联系电话:&ensp;&ensp;${taskManager.inquirerPhone}</label>
					</div>
				</div>

			</div>

			<div class="form-row otherpara D5">

				<div class="infPara infPara2">
					<div class="infParaCon">
						<div class="infpLable">搬迁地址:&ensp;&ensp;${taskManager.relocationAddress}</div>
					</div>
				</div>

				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">搬迁时间:&ensp;&ensp;<fmt:formatDate value="${taskManager.relocationDate}" pattern="YYYY-MM"/> </label>
					</div>
				</div>

			</div>
			
			
			<div class="form-row otherpara P7">

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
						<label for="" class="infpLable">详细地址:&ensp;&ensp;${taskManager.address.houseNumber}</label>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">经度</label>
						<div class="infpCon">
							<div class="inputs">
								<input type="hidden" name="address.longitude" id="longitude" value="${taskManager.address.longitude}"/>
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
								<input type="hidden" name="address.latitude" id="latitude" value="${taskManager.address.latitude}"/>
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

				<div class="div">
					<input id="photoPath" name="photoPath" type="hidden">
					<div class="label" style="float:left">提交现场图片</div>
					<div id="fileHandle" class="imgF iconfont icon-iconjia">
						<img id="photoImg" src="${taskManager.photoPath}" class="img">
					</div>
				</div>

			</div>

			<div class="form-row otherpara D8">

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">选取重复企业</label>
						<div class="infpCon">
							<div data-code="任务管理【重复】" id="repeatId" class="selF" style="width: 300px"></div>
						</div>
					</div>
				</div>

			</div>

		</form>
	</div>
	
	
</body>
</html>