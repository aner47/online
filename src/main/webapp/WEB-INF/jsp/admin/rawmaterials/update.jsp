<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改溶剂</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<style>
	label.error{
		position:absolute;
		right:10px;
		line-height:31px;
	}
</style>
</head>
<script type="text/javascript">
require(["hide","validate","ajaxform","select","year"],function(hide){
	
	var year = $('#inputForm #year').year({'name':'year',defaultValue:'${rawMaterials.year}'});
	
		var changeNames = ["solventCategory"];
		
		function required(value){
			for(var i = 0; i < changeNames.length; i++){
				if(value == 'inorganic' ){
					$('[name='+changeNames[i]+']', "#inputForm").removeAttr('required');
				}else{
					$('[name='+changeNames[i]+']', "#inputForm").attr('required','true');
				}
			}
		}
	
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		
		var sectionId = $("#sectionId","#inputForm").select({name:"sectionId",param:"product",zIndex:9,defaultValue:'${rawMaterials.sectionId}',selected:true,
			change:function(event, value){
				var text = sectionId.getSelectCaption();
				$("input[name=productName]").val(text);
			}
		})
		var unit = $("#unit","#inputForm").select({name:"unit",zIndex:8,defaultValue:'${rawMaterials.unit}',
		})
		
		//提交表单
		$("#inputForm").validate({
			rules: {
				vocRate:{
					range:[0,100]
				},
				vocVolatility:{
					range:[0,100]
				},
				consumption:{
					min:0,
				},
				unit:{
					checkUnit:/\D/,
					required:true
				},
				sectionId:{
					required:true
				},
				description:{
					maxlength:255
				},
			},
			messages:{
				vocRate:{
					range:"0~100!"
				},
				vocVolatility:{
					range:"0~100!"
				},
				consumption:{
					min:"大于等于0"
				},
				description:{
					maxlength:"最多255字"
				}
			},
			submitHandler:function(form){
				var options  = {
			        url:'update.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('修改成功！', {icon: 6})
						layer.closeAll('page');
						grid1.refresh();
			        },
			        error:function(){
			        	layer.msg('修改失败',{icon:3});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});	
		console.log(11);
		//判断是否为有机溶剂
		var rawMaterials = $("[name='rawMaterialsType']:checked").val();
		showRawMaterialsType(rawMaterials);
		
		required('${rawMaterials.rawMaterialsType}');
		
		$("[name='rawMaterialsType']").on('change',function(){
			showRawMaterialsType($(this).val());
			required($(this).val());
		})	
			
		function showRawMaterialsType(value){
			if (value=='organic') {
				 $("#solventCategory","#inputForm").select({filter: false,name:"solventCategory",zIndex:7,isCustom : true,defaultValue:'${rawMaterials.solventCategory}'});
				 $("div#solventCategory").css("background","#fff");
				 $("input[name='solventType']").removeAttr('disabled');
		      	 $("input[name='vocRate']").removeAttr('disabled');
		      	 $("input[name='vocVolatility']").removeAttr('disabled');
		      	 
		      	 $("input[name='organicName']").removeAttr('disabled');
		      	 $("input[name='organicConsumption']").removeAttr('disabled');
		      	 $("input[name='organicUnit']").removeAttr('disabled');
		      	$(".js-handleName").html("有机溶剂名称");
		      	 
			}else{
	    		$("#solventCategory","#inputForm").select({filter: false,name:"solventCategory",zIndex:7,isCustom : true});
				$("div#solventCategory").unbind("click");
				$("div#solventCategory").css("background","#ccc");
		    	$("input[name='solventType']").attr({disabled:'true'});
		    	$("input[name='vocRate']").attr({disabled:'true'});
		    	$("input[name='vocVolatility']").attr({disabled:'true'});
		    	
		    	$("input[name='organicName']").attr({disabled:'true'});
		    	$("input[name='organicConsumption']").attr({disabled:'true'});
		    	$("input[name='organicUnit']").attr({disabled:'true'});
		    	$(".js-handleName").html("原辅料名称")
			};
		}
			
			
		hide.hide();
	});
</script>

<body>
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form id="inputForm" style="padding:0;width:100%;">
		<input type="hidden" id="id" name="id" value="${rawMaterials.id}" />
		<input type="hidden" class="input" name="productName" value="${rawMaterials.productName}">
		<div class="infLine">
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
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>产品名称</label>
					<div class="infpCon">
						<div id="sectionId" data-code="简单表单【工段】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara infPara2">
				<div class="infParaCon">
				<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheA1" name="rawMaterialsType" 
						value="organic"
						<c:if test="${rawMaterials.rawMaterialsType=='organic' }">checked='checked'</c:if>>
						<label for="cheA1" class="label">是</label>
						<input type="radio" class="radio" id="cheA2" name="rawMaterialsType" value="inorganic"
						<c:if test="${rawMaterials.rawMaterialsType=='inorganic' }">checked='checked'</c:if>>
						<label for="cheA2" class="label">否</label>
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable js-handleName"><span style="margin-right: 5px;color: #eb2a33;">*</span>原辅料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="name" value="${rawMaterials.name}"  />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="consumption" number=true value="${rawMaterials.consumption}" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数量单位</label>
					<div class="infpCon">
						<%-- <input type="" class="input" name="unit" value="${rawMaterials.unit}" > --%>
						<div class="selF" id="unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara"></div>
			

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="solventCategory" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheB1" name="solventType" value="oil"
						<c:if test="${rawMaterials.solventType=='oil' }">checked='checked'</c:if>>
						<label for="cheB1" class="label">油性</label>
						<input type="radio" class="radio" id="cheB2" name="solventType" value="water"
						<c:if test="${rawMaterials.solventType=='water' }">checked='checked'</c:if>>
						<label for="cheB2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocRate" value="${rawMaterials.vocRate}">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocVolatility" value="${rawMaterials.vocVolatility}">
					</div>
				</div>
			</div>
			
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