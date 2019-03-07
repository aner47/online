<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工程机械信息</title>
<style type="text/css">
	table{
		width:100%;
	}
	td{
		width:20%;
		height:30px;
		border:1px solid #ccc;
		text-align:center;
		position:relative;
	}
	input{
		border:none;
		width:100%;
		height:30px;
	}
	.infPara{
		padding:20px 0px;
	}
	td label.error{
		position:absolute;
		left:70px;
		top:5px;
		width:80px;
	}
	div{
		border:none;
	}
</style>
<script type="text/javascript">
	require(["grid","hide","select","panel", "validate", "ajaxform"],function(g,hide){
		
		$("body").on("click","#save",function() {
			$("#boiler_form").submit();
		})
		
		total();
		function total(){
			var mac =["machineryInventory","machineryPower","annualConsumption","fuelSulfur"];
			  for(var i=0;i<mac.length;i++){
				  var inputVal0 = 0;
				   $.each($("td:eq("+(i+1)+") input[name]","tr"),function(o1,value){
					  // console.log($(value).val()*1)
					    inputVal0 = inputVal0 +$(value).val()*1;
				   })
				   $("#"+mac[i],"#boiler_content").val(inputVal0);
			  }	
		}
		
	   $("input[name]","#boiler_content").change(function(){
		   console.log('aaaaaaaaaa');
		   total();
	   })
		
		//提交表单
		$("#boiler_form").validate({
			rules: {
				"constructionMachinerys[0].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[1].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[2].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[3].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[4].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[5].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[6].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[7].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[8].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[9].fuelSulfur":{
					range:[0,100]
				},
				"constructionMachinerys[10].fuelSulfur":{
					range:[0,100]
				},
			},
			messages:{
				description:{
					maxlength:"最多255字"
				},
			},
			
			submitHandler : function(form) {
				
				//以tr为单位，一个tr代表一条记录
                var constructionMachinerys = new Array();
                /* $("#table tr").each(function(){
                	//this代表当前dom对象tr
                	var that = this;
                	var orderItemObj = new Object();
                	$(that).find("input").each(function(){
                		var name = $(this).attr("id");
                		console.log(name);
                		orderItemObj[name] = $(this).val();
                	});
                	orderItemArray.push(orderItemObj);
                }); */

                //constructionMachinerys.push({machineryType: "1", machineryInventory: "111", machineryPower: "123"});
                //constructionMachinerys.push({machineryType: "2", machineryInventory: "222", machineryPower: "332"});
				
				var options = {
					url : '../constructionmachinery/save.jhtml',
					type : 'post',
				  //  contentType : 'application/x-www-form-urlencoded;charset=UTF-8', //设置请求头信息
	                dataType:"json",
				//	data: JSON.stringify(constructionMachinerys),
					//data: $(form).toJSON(orderItemArray),
					//traditional: true,//必须指定为true
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
		
		
	})
</script>
<style>
.block{
	padding-bottom: 120px;
}
.examine {
	width: 42px;
	height: 30px;
	line-height: 30px!important;
	text-align: center;
	color: #fff !important;
	border-radius: 13px;
	background: #4fc1ee;
	margin-top: 9px !important;
}
</style>
</head>
<body>
	<div class="block">
		<h1 class="title">
			<i class="iconfont icon-xuqin"></i>
			工程机械信息
			<%-- <c:if test="${principal.userType != 'system'}">
			<!-- <div id="back" class="titleBtn">返回</div> -->
			<div id="addBtn" class="titleBtn">添加</div>
			<div id="editBtn" class="titleBtn" data-grid="tab" data-enable="1">编辑</div>
			<div id="delete" class="titleBtn" data-grid="tab" data-enable="2">删除</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<!-- <div id="fixed_return" class="titleBtn">返回</div> -->
				<div id="check" class="titleBtn examine">审核</div>
			</c:if> --%>
		</h1> 
			
	
	<form id="boiler_form">
		<div class="dialog_div">
			
				<table id="table">
				 <tr>
					<td><b>施工机械类型</b></td>
					<td><b>机械保有量（台）</b></td>
					<td><b>机械总功率（千瓦） </b></td>
					<td><b>单机燃料年消耗量（吨／台）</b></td>
					<td><b>燃料含硫率（%）</b></td>
				</tr>
				<tbody id="boiler_content"> 
				<tr>
					<input name="constructionMachinerys[0].id" type="hidden"  value="${constructionMachinery[0].id}"/>
					<td class="species"><input name="constructionMachinerys[0].machineryType" type="hidden"  value="挖掘机"/>挖掘机</td>
					<td><input name="constructionMachinerys[0].machineryInventory" value="${constructionMachinery[0].machineryInventory}" type=""  number="true"   onKeyUp="value=value.replace(/[\D]/g,'')" /></td>
					<td><input name="constructionMachinerys[0].machineryPower" value="${constructionMachinery[0].machineryPower}" type=""  number="true"   onKeyUp="value=value.replace(/[^0-9.]/g,'')" /></td>
					<td><input name="constructionMachinerys[0].annualConsumption" value="${constructionMachinery[0].annualConsumption}" type=""  number="true"   onKeyUp="value=value.replace(/[\D]/g,'')" /></td>
					<td><input name="constructionMachinerys[0].fuelSulfur" value="${constructionMachinery[0].fuelSulfur}" type=""  number="true"   onKeyUp="value=value.replace(/[^0-9.]/g,'')" /></td> 
				</tr>
				<tr>
				<input name="constructionMachinerys[1].id" type="hidden"  value="${constructionMachinery[1].id}"/>
					<td class="species"><input name="constructionMachinerys[1].machineryType" type="hidden"  value="装载机"/>装载机</td>
					<td><input name="constructionMachinerys[1].machineryInventory" value="${constructionMachinery[1].machineryInventory}" type=""  number="true"   onKeyUp="value=value.replace(/[\D]/g,'')" /></td>
					<td><input name="constructionMachinerys[1].machineryPower" value="${constructionMachinery[1].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[1].annualConsumption" value="${constructionMachinery[1].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[1].fuelSulfur" value="${constructionMachinery[1].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[2].id" type="hidden"  value="${constructionMachinery[2].id}"/>
					<td class="species"><input name="constructionMachinerys[2].machineryType"  type="hidden"  value="推土机"/>推土机</td>
					<td><input name="constructionMachinerys[2].machineryInventory" value="${constructionMachinery[2].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[2].machineryPower" value="${constructionMachinery[2].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[2].annualConsumption" value="${constructionMachinery[2].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[2].fuelSulfur" value="${constructionMachinery[2].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[3].id" type="hidden"  value="${constructionMachinery[3].id}"/>
					<td class="species"><input name="constructionMachinerys[3].machineryType" type="hidden"  value="铲运机"/>铲运机</td>
					<td><input name="constructionMachinerys[3].machineryInventory" value="${constructionMachinery[3].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[3].machineryPower" value="${constructionMachinery[3].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[3].annualConsumption" value="${constructionMachinery[3].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[3].fuelSulfur" value="${constructionMachinery[3].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[4].id" type="hidden"  value="${constructionMachinery[4].id}"/>
					<td class="species"><input name="constructionMachinerys[4].machineryType" type="hidden"  value="平地机"/>平地机</td>
					<td><input name="constructionMachinerys[4].machineryInventory" value="${constructionMachinery[4].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[4].machineryPower" value="${constructionMachinery[4].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[4].annualConsumption" value="${constructionMachinery[4].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[4].fuelSulfur" value="${constructionMachinery[4].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[5].id" type="hidden"  value="${constructionMachinery[5].id}"/>
					<td class="species"><input name="constructionMachinerys[5].machineryType" type="hidden"  value="起重机械"/>起重机械</td>
					<td><input name="constructionMachinerys[5].machineryInventory" value="${constructionMachinery[5].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[5].machineryPower" value="${constructionMachinery[5].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[5].annualConsumption" value="${constructionMachinery[5].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[5].fuelSulfur" value="${constructionMachinery[5].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[6].id" type="hidden"  value="${constructionMachinery[6].id}"/>
					<td class="species"><input name="constructionMachinerys[6].machineryType" type="hidden"  value="桩工机械"/>桩工机械</td>
					<td><input name="constructionMachinerys[6].machineryInventory" value="${constructionMachinery[6].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[6].machineryPower" value="${constructionMachinery[6].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[6].annualConsumption" value="${constructionMachinery[6].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[6].fuelSulfur" value="${constructionMachinery[6].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[7].id" type="hidden"  value="${constructionMachinery[7].id}"/>
					<td class="species"><input name="constructionMachinerys[7].machineryType" type="hidden"  value="工程车辆"/>工程车辆</td>
					<td><input name="constructionMachinerys[7].machineryInventory" value="${constructionMachinery[7].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[7].machineryPower" value="${constructionMachinery[7].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[7].annualConsumption" value="${constructionMachinery[7].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[7].fuelSulfur" value="${constructionMachinery[7].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[8].id" type="hidden"  value="${constructionMachinery[8].id}"/>
					<td class="species"><input name="constructionMachinerys[8].machineryType" type="hidden"  value="叉车"/>叉车</td>
					<td><input name="constructionMachinerys[8].machineryInventory" value="${constructionMachinery[8].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[8].machineryPower" value="${constructionMachinery[8].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[8].annualConsumption" value="${constructionMachinery[8].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[8].fuelSulfur" value="${constructionMachinery[8].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<input name="constructionMachinerys[9].id" type="hidden"  value="${constructionMachinery[9].id}"/>
					<td class="species"><input name="constructionMachinerys[9].machineryType" type="hidden"  value="其他"/>其他</td>
					<td><input name="constructionMachinerys[9].machineryInventory" value="${constructionMachinery[9].machineryInventory}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[9].machineryPower" value="${constructionMachinery[9].machineryPower}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td>
					<td><input name="constructionMachinerys[9].annualConsumption" value="${constructionMachinery[9].annualConsumption}" type=""  number="true"  onKeyUp="value=value.replace(/[\D]/g,'')"/></td>
					<td><input name="constructionMachinerys[9].fuelSulfur" value="${constructionMachinery[9].fuelSulfur}" type=""  number="true"  onKeyUp="value=value.replace(/[^0-9.]/g,'')"/></td> 
				</tr>
				<tr>
					<td class="species"><input id="constructionMachinerys[10].machineryType" type="hidden"  value="所有机械"/>所有机械</td>
					<td><input id="machineryInventory" value="" onfocus="this.blur()"/></td>
					<td><input id="machineryPower" value="" onfocus="this.blur()"/></td>
					<td><input id="annualConsumption" value="" onfocus="this.blur()"/></td>
					<td><input id="fuelSulfur" value="" onfocus="this.blur()"/></td> 
				</tr>
				</tbody>
			</table>
		</div>
	</form>
	<div class="buttonF">
					<div class="saveBtn" id="save">保存</div>
				</div>
	</div>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>