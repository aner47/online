<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

			
			top.$("#company").hide();
			top.$("#enterprise_button_div").hide();
			
			$("#relocationDate").jeDate({
				format:"YYYY-MM",
				isTime:false, 
				zIndex:999999900
			})

			// 省市县街
			var provinces = $("#provinces").select({name:"address.provinces.code",async:false,clearEvent:function(){city.clear();},change:function(event, value){
				city.clear();
				if (!value) {
					return;
				};
				city.loadData("parent:"+value);
			}
			
			});
			var city = $("#city").select({name:"address.city.code","initLoad":false,defaultValue:top.tempData.project.city,async:false,param:"parent:"+top.tempData.project.provinces,clearEvent:function(){county.clear();},change:function(event, value){
				county.clear();
				if (!value) {
					return;
				};
				county.loadData("parent:"+value);
			}});
			var county = $("#county").select({name:"address.county.code","initLoad":false,async:false,clearEvent:function(){street.clear();},change:function(event, value){
				street.clear();
				if (!value) {
					return;
				};
				street.loadData("parent:"+value);
			}});
			var street = $("#street").select({name:"address.street.code","initLoad":false,async:false}); 

			// 经纬度
			$("#latitude").parent().find('input').on('change',function(){
				var inputs = $("#latitude").parent().find('input');
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
				$('#latitude').val(valA);
			})
			$("#longitude").parent().find('input').on('change',function(){
				var inputs = $("#longitude").parent().find('input');
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
			})
				
			// 重复企业
			var repeatIdSelect = $('#repeatId').select({filter: true,name:'repeatId',zIndex:1});
			
			
			
			var enterpriseStatusSelect = $("#enterpriseStatus").select({name:"enterpriseStatus",zIndex:2,change:function(handle,value){
				$(".otherpara").addClass('hide');
				$(".D_09").addClass('hide');
				if(value == "D_05"){
					$(".all").removeClass('hide');
					$(".D5").removeClass('hide');
					/* $("#inquirer").attr("required", true);
					$("#inquirerPhone").attr("required", true);
					$("#relocationAddress").attr("required", true);
					$("#relocationDate").attr("required", true); */
				}else if(value == "P_06"){
					$(".all").removeClass('hide');
				}else if(value == "P_07"){
					$(".all").removeClass('hide');
					$(".P7").removeClass('hide');
					provinces.setSelectValue(top.tempData.project.provinces);
					city.setSelectValue(top.tempData.project.city);
				}else if(value == "D_08"){
					$(".all").removeClass('hide');
					$(".D8").removeClass('hide');
				}else if(value == "D_09"){
					$(".D_09").removeClass('hide');
				}
				$('.otherpara.hide input').prop('disabled','disabled');
				$('.otherpara:not(.hide) input').removeProp('disabled');
				/* $("#inquirer").removeAttr("required");
				$("#inquirerPhone").removeAttr("required");
				$("#relocationAddress").removeAttr("required");
				$("#relocationDate").removeAttr("required"); */
			}
		});
			// var radioSelect = $("#report").radio({
			// 	data:[{value:"false",showValue:"否"},{value:"true",showValue:"是"}],label:"是否上报",change:function(value){
			// 	}
			// })
			
			// 企业搜索
			var opts = {
				tab:"tab1",
				data: {},
				url: "query.jhtml",
				checkbox:true,
				num:true,
				columns :[
					{name:"enterpriseName",caption:"企业名称",hidden:false,type:"text"},
					{name:"contactPerson",caption:"联系人",hidden:false,type:"text"},
					{name:"contactPhone",caption:"联系电话",hidden:false,type:"text"},
					{name:"corporation",caption:"法人",hidden:false,type:"text"},
					{name:"countyName",caption:"区县",hidden:false,type:"text"},
					{name:"executor",caption:"调查人",hidden:false,type:"text"},
					{name:"detailAddress",caption:"详细地址",hidden:false,type:"text"},
					{name:"status",caption:"任务状态",hidden:false,type:"text",valueSet:"【任务管理】状态"},
					{name:"id",caption:"ID",hidden:true,type:"text"},
					],
					success: function(result) {
						console.log(result)
					}
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
			$("#project_user_Search").on("click", function() {
				console.log('我是搜索事件')
				/* qy.ajax(opts); */
				grid1.loadData({enterpriseName:$("#enterpriseName").val()});
				
			})
			upload.upload({element:"#fileHandle",acceptCallBack:function(handle,data){
				$("#fileName").html(handle.file.name);
				$("#photoPath",".form").val(data.url);
				$("#photoImg ").attr("src",data.url);
				$(".imgF").addClass('hasimg');
				console.log(data.url);
			}
		});
			
			$("#enterpriseId").blur(function(){
				var enterpriseId = $(this).val();
				qy.ajax({
					url:base+"/web/enterprise/viewData.jhtml",
					data:{"id":enterpriseId},
					callBack:function(data){
						if(!data.name){
							layer.msg("无效企业编号");
							$("#enterpriseId").val('');
							$("#showEnterpriseInfo").html("");
						}else{
							if(data.address){
								var detailAddress = "";
								if(data.address.provinces){
									detailAddress = data.address.provinces.name;
									if(data.address.city){
										detailAddress = detailAddress + data.address.city.name;
										if(data.address.county){
											detailAddress = detailAddress + data.address.county.name
											if(data.address.street){
												detailAddress = detailAddress + data.address.street.name
											}
										}
									}
									detailAddress = detailAddress + data.address.houseNumber;		
								}
								$("#showEnterpriseInfo").html("企业名称："+data.name+"</br>详细地址："+detailAddress)							
							}
							
							
						}
						console.log(data);
					}
				})
			})
			
			$("#importTask").click(function(){
				$("#submitForm").submit();
			})
			function formSubmit(){
				var tips = "";
				var enterpriseStatus = enterpriseStatusSelect.getSelectValue();
				if(!enterpriseStatus){
					layer.msg("请选择企业状态！");
					return;
				}
				var relocationDate = $("#relocationDate","#submitForm").val();
				if(enterpriseStatus.slice(0,1)=="T" ||(enterpriseStatus =='D_05'&&relocationDate>top.tempData.project.dataYear)){
					tips = "此操作会创建企业填报任务！"
				}else{
					tips = "是否提交！"
				}
				layer.confirm(tips, {icon: 1, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
					var data = {};
					var status = grid1.getSelectedValue("status")[0]
					data.id = grid1.getSelectedValue("id")[0];
					// data.report = radioSelect.getSelectValue();
					data.enterpriseStatus = enterpriseStatusSelect.getSelectValue();
					// data.reportEnterpriseName=$("#reportEnterpriseName",".form").val();
					// if (!data.reportEnterpriseName) {
					// 	layer.msg("请填写上报企业名称！");
					// 	return;
					// };
					var ids = grid1.getSelectedValue('id');
					data.ids = ids.join(',');
					$('.otherpara:not(.hide) input').each(function(k,v){
						var name = $(v).attr('name');
						var val = $(v).val();
						data[name] = val;
					})

					qy.ajax({
						url:"reportData.jhtml",
						data:data,
						callBack:function(data){
							if(data.code=='20000'){
								layer.msg("提交成功！");
								grid1.refresh();
							}else{
								layer.msg(data.content);
							}
						}
					})
					
				})
			}

			$("#submitForm").validate({
				rules: {
					enterpriseStatus:{
						required:true
					},
					inquirer: {
						required:true
					},
					inquirerPhone: {
						required:true
					},
					relocationDate: {
						required:true
					},
					inquirerPhone:{
						isTelephone : true
					},
					addresstemp1:{
						range:[-180,180]
					},
					addresstemp2:{
						range:[0,59.99],
						digits:true,
					},
					addresstemp3:{
						range:[0,59.99]
					},
					addresstemp4:{
						range:[-90,90],

					},
					addresstemp5:{
						range:[0,59.99],
						digits:true,
					},
					addresstemp6:{
						range:[0,59.99]
					},
					repeatId:{
						required:true
					},
				},
				messages:{
				},
				submitHandler:function(form){
					formSubmit();
					return false;
				}
			}); 
			
			

})
</script>
<style>
.enterpriseNameCls {
    min-width:150px; float: left;
	height: 30px;
    line-height: 28px;
    border: 1px solid #ccc;
    padding: 0 5px;
}
.tab .mygrid_title {
	border: 1px solid #e9eaec;	
}

</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i style="padding-left: 20px;width: 0;margin:0;" class="iconfont icon-loutianweichang1"></i> <div style="float:left;">任务列表<div>
		</h1>
		<c:if test="${principal.userType == 'investigator'}">
		<form id="queryForm" class="form"  action="#" onsubmit="return false">
			<div class="form-row">
				<div style="padding-left: 45px;" class="div">
					<!-- <label class="label">企业名称</label> 
					<input type="text" class="input" name="name"/> -->
					<div class="label" style="float:left; margin-right: 10px;">企业名称</div>
					<input id="enterpriseName" class="enterpriseNameCls" />
				</div>
				<div class="div">
					<input type="button" value="搜索" id="project_user_Search" class="button project_user_Search_1">
				</div>
			</div>
		</form>
		</c:if>
		<div id="tab1" style="margin:0;padding: 0 20px;" class="tab" ></div>
		<div style="clear:both"></div>
		<form id="submitForm" class="form">
			<div class="form-row">
				<div class="div">
					<div class="label" style="float:left;margin-right: 10px;">企业状态</div>
					<div id="enterpriseStatus" data-code="【任务管理】企业状态" style="min-width:150px"></div>
				</div>
				<!-- <div class="div">
					<div id="report"></div>
				</div> -->

				<div class="bottonCatain div">
					<button id="importTask" data-grid = "tab1" data-enable = "2" >确定</button>
				</div>
			</div> 
			<div class="form-row otherpara D_09 hide">
				<div class="div">
					<span class="label"  style="float:left">上报企业编号</span>
					<input id="enterpriseId" name="enterpriseId" type="text" class="input">
				</div>
				<div class="div" id="showEnterpriseInfo"></div>
			</div>
			<div class="form-row otherpara all hide">

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" id="inquirer" name="inquirer" required>
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人联系电话</label>
						<div class="infpCon">
							<input type="" class="input" id="inquirerPhone" name="inquirerPhone" required>
						</div>
					</div>
				</div>
				
			</div>

			<div class="form-row otherpara D5 hide">

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">搬迁地址</label>
						<div class="infpCon">
							<input type="" class="input" id="relocationAddress" name="relocationAddress"  />
						</div>
					</div>
				</div>

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">搬迁时间</label>
						<div class="infpCon">
							<input type="" class="input" id="relocationDate" name="relocationDate" onfocus="this.blur()" required/>
						</div>
					</div>
				</div>

			</div>

			<div class="form-row otherpara P7 hide">

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
							<input type="" class="input" name="address.houseNumber" id="houseNumber">
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
									name="addresstemp1" >
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="经分"
									name="addresstemp2" >
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="经秒"
									name="addresstemp3" >
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
									name="addresstemp4" >
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="纬分"
									name="addresstemp5" >
								</div>
								<div class="inputChild col4">
									<input type="" class="input" placeholder="纬秒"
									name="addresstemp6" >
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="div">
					<input id="photoPath" name="photoPath" type="hidden">
					<div class="label" style="float:left">提交现场图片</div>
					<div id="fileHandle" class="imgF iconfont icon-iconjia">
						<img id="photoImg" src="" class="img">
					</div>
				</div>

			</div>

			<div class="form-row otherpara D8 hide">

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">选取重复企业</label>
						<div class="infpCon">
							<div data-code="任务管理【重复】" id="repeatId" class="selF"></div>
						</div>
					</div>
				</div>

			</div>

		</form>
		<div id="tab2" style="padding: 0 20px;" class="tab" ></div>
	</div>
	
	
</body>
</html>