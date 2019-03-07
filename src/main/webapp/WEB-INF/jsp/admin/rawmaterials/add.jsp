<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加溶剂</title>
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
require(["hide","validate","ajaxform","year"],function(hide){
	var year = $('#inputForm #year').year({'name':'year'});

	var projectYear = '${project.dataYear}';
	year.setYearValue(projectYear);
	
	var changeNames = ["solventCategory"];
	var changeNames2 = ["solventCategory2"];
	var changeNames3 = ["solventCategory3"];
	
	function required(value){
		if(value == 'inorganic' ){
			console.log('inorganic');
			$('[name=solventCategory]', "#inputForm").removeAttr('required');
		}else{
			$('[name=solventCategory]', "#inputForm").attr('required','true');
		}
	}
	function required2(value){
		if(value == 'inorganic' ){
			console.log('inorganic');
			$('[name=solventCategory2]', "#inputForm").removeAttr('required');
		}else{
			$('[name=solventCategory2]', "#inputForm").attr('required','true');
		}
	}
	function required3(value){
		if(value == 'inorganic' ){
			console.log('inorganic');
			$('[name=solventCategory3]', "#inputForm").removeAttr('required');
		}else{
			$('[name=solventCategory3]', "#inputForm").attr('required','true');
		}
	}
	
	
	$('#commit').click(function(){
		$("#inputForm").submit();
	})
	
	$(".js-judgeIfNull2").on("change", function() {
		var judgeIfNull = $(this).val();
		if (judgeIfNull != "") {
			$(".js-judgeEndchangeYear2").attr("required", true)
			$(".js-judgeEndchangeNumber2").attr("required", true)
		} else {
			$(".js-judgeEndchangeYear2").removeAttr("required")
			$(".js-judgeEndchangeNumber2").removeAttr("required")
		}
	})
	
	$(".js-judgeIfNull3").on("change", function() {
		var judgeIfNull = $(this).val();
		if (judgeIfNull != "") {
			$(".js-judgeEndchangeYear3").attr("required", true)
			$(".js-judgeEndchangeNumber3").attr("required", true)
		} else {
			$(".js-judgeEndchangeYear3").removeAttr("required")
			$(".js-judgeEndchangeNumber3").removeAttr("required")
		}
	})
	
	var sectionId = $("#sectionId", "#inputForm").select({
		name: "sectionId",
		param: "product",
		zIndex: 21,
		change:function(event, value) {
			var text = sectionId.getSelectCaption();
			$("input[name=productName]").val(text);
		}
	})
	
	$("#unit","#inputForm").select({filter: false,name:"unit",zIndex:20,isCustom : true});
	$("#solventCategory","#inputForm").select({filter: false,name:"solventCategory",zIndex:19,isCustom : true});
	$("#unit2","#inputForm").select({filter: false,name:"unit2",zIndex:18,isCustom : true});
	$("#solventCategory2","#inputForm").select({filter: false,name:"solventCategory2",zIndex:17,isCustom : true});
	$("#unit3","#inputForm").select({filter: false,name:"unit3",zIndex:16,isCustom : true});
	$("#solventCategory3","#inputForm").select({filter: false,name:"solventCategor3",zIndex:15,isCustom : true});
	
	$("[name='rawMaterialsType']").on('change',function(){
		showRawMaterialsType($(this).val());
		required($(this).val());
	})	
	$("[name='rawMaterialsType2']").on('change',function(){
		showRawMaterialsType2($(this).val());
		required2($(this).val());
	})	
	$("[name='rawMaterialsType3']").on('change',function(){
		showRawMaterialsType3($(this).val());
		required3($(this).val());
	})	
	
			
		function showRawMaterialsType(value){
			if (value=='organic') {
				console.log("是");
				 $("#solventCategory","#inputForm").select({filter: false,name:"solventCategory",zIndex:8,isCustom : true});
				 $("div#solventCategory").css("background","#fff");
				 $("input[name='solventType']").removeAttr('disabled');
		      	 $("input[name='vocRate']").removeAttr('disabled');
		      	 $("input[name='vocVolatility']").removeAttr('disabled');
		      	 $(".js-handleName1").html("有机溶剂名称")
			}else{
				//是
				console.log("否");
	    		$("#solventCategory","#inputForm").select({filter: false,name:"solventCategory",zIndex:8,isCustom : true});
				$("div#solventCategory").unbind("click");
				$("div#solventCategory").css("background","#ccc");
		    	$("input[name='solventType']").attr({disabled:'true'});
		    	$("input[name='vocRate']").attr({disabled:'true'});
		    	$("input[name='vocVolatility']").attr({disabled:'true'});
		    	$(".js-handleName1").html("原辅料名称")
			};
		}
		function showRawMaterialsType2(value){
			if (value=='organic') {
				console.log("是");
				 $("#solventCategory2","#inputForm").select({filter: false,name:"solventCategory2",zIndex:7,isCustom : true});
				 $("div#solventCategory2").css("background","#fff");
				 $("input[name='solventType2']").removeAttr('disabled');
		      	 $("input[name='vocRate2']").removeAttr('disabled');
		      	 $("input[name='vocVolatility2']").removeAttr('disabled');
		      	 $('input[name=solventCategory2]', "#inputForm").attr('required', true);
		      	 $(".js-handleName2").html("有机溶剂名称")
			}else{
				//是
				console.log("否");
	    		$("#solventCategory2","#inputForm").select({filter: false,name:"solventCategory2",zIndex:7,isCustom : true});
				$("div#solventCategory2").unbind("click");
				$("div#solventCategory2").css("background","#ccc");
		    	$("input[name='solventType2']").attr({disabled:'true'});
		    	$("input[name='vocRate2']").attr({disabled:'true'});
		    	$("input[name='vocVolatility2']").attr({disabled:'true'});
		    	$('input[name=solventCategory2]', "#inputForm").removeAttr('required');
		    	$(".js-handleName2").html("原辅料名称")
			};
		}
		function showRawMaterialsType3(value){
			if (value=='organic') {
				console.log("是");
				 $("#solventCategory3","#inputForm").select({filter: false,name:"solventCategory3",zIndex:6,isCustom : true});
				 $("div#solventCategory3").css("background","#fff");
				 $("input[name='solventType3']").removeAttr('disabled');
		      	 $("input[name='vocRate3']").removeAttr('disabled');
		      	 $("input[name='vocVolatility3']").removeAttr('disabled');
		      	$('input[name=solventCategory3]', "#inputForm").attr('required', true);
		      	 $(".js-handleName3").html("有机溶剂名称")
			}else{
				//是
				console.log("否");
	    		$("#solventCategory3","#inputForm").select({filter: false,name:"solventCategory3",zIndex:6,isCustom : true});
				$("div#solventCategory3").unbind("click");
				$("div#solventCategory3").css("background","#ccc");
		    	$("input[name='solventType3']").attr({disabled:'true'});
		    	$("input[name='vocRate3']").attr({disabled:'true'});
		    	$("input[name='vocVolatility3']").attr({disabled:'true'});
		    	$('input[name=solventCategory3]', "#inputForm").removeAttr('required');
		    	$(".js-handleName3").html("原辅料名称")
			};
		}
		
		
		
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
				rawMaterialsType:{
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
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.refresh();
			        },
			        error:function(){
			        	layer.msg('添加失败',{icon:3});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});	
		
		
	 
		
		
		hide.hide();
	});
</script>

<body>
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form id="inputForm" style="padding:0;width:100%;">
		<input type="hidden" class="input" name="productName">
		<input type="hidden" name="token" value="${token}" />
		
		
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
			
			
			<div class="infh1">原辅料1</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
				<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheA1_1" name="rawMaterialsType" value="organic">
						<label for="cheA1_1" class="label">是</label>
						<input type="radio" class="radio" id="cheA1_2" name="rawMaterialsType" value="inorganic">
						<label for="cheA1_2" class="label">否</label>
					</div>
				</div>
			</div>
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable js-handleName1"><span style="margin-right: 5px;color: #eb2a33;">*</span>原辅料名称</label>
					<div class="infpCon">
						<input type="" class="input" name="name" required />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="consumption" number=true required />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="unit" required /> -->
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
						<input type="radio" class="radio" id="cheB1" name="solventType" value="oil">
						<label for="cheB1" class="label">油性</label>
						<input type="radio" class="radio" id="cheB2" name="solventType" value="water">
						<label for="cheB2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocRate">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocVolatility">
					</div>
				</div>
			</div>
			<!-- ************************************************************** -->
			<div class="infh1">原辅料2</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheA2_1" name="rawMaterialsType2" value="organic">
						<label for="cheA2_1" class="label">是</label>
						<input type="radio" class="radio" id="cheA2_2" name="rawMaterialsType2" value="inorganic">
						<label for="cheA2_2" class="label">否</label>
					</div>
				</div>
			</div>
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable js-handleName2">原辅料名称</label>
					<div class="infpCon">
						<input type="" class="input js-judgeIfNull2" name="name2" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input js-judgeEndchangeYear2" name="consumption2" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input js-judgeEndchangeNumber2" name="unit2" > -->
						<div class="selF" id="unit2" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="solventCategory2" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheB1" name="solventType2" value="oil">
						<label for="cheB1" class="label">油性</label>
						<input type="radio" class="radio" id="cheB2" name="solventType2" value="water">
						<label for="cheB2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocRate2">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocVolatility2">
					</div>
				</div>
			</div>
			
			<!-- ************************************************************** -->
			<div class="infh1">原辅料3</div>
			
			<div class="infPara infPara2">
				<div class="infParaCon">
				<label for="" class="infpLable">是否为有机溶剂</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheA3_1" name="rawMaterialsType3" value="organic">
						<label for="cheA3_1" class="label">是</label>
						<input type="radio" class="radio" id="cheA3_2" name="rawMaterialsType3" value="inorganic">
						<label for="cheA3_2" class="label">否</label>
					</div>
				</div>
			</div>
			
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable js-handleName3">原辅料名称</label>
					<div class="infpCon">
						<input type="" class="input js-judgeIfNull3" name="name3" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年消耗量</label>
					<div class="infpCon">
						<input type="" class="input js-judgeEndchangeYear3" name="consumption3" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input js-judgeEndchangeNumber3" name="unit3" > -->
						<div class="selF" id="unit3" data-code="数量单位"></div>
					</div>
				</div>
			</div>
			
			<div class="infPara"></div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类别</label>
					<div class="infpCon">
						<div id="solventCategory3" data-code="详表原料【溶剂类别】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
				<label for="" class="infpLable">溶剂性质</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="cheB1" name="solventType3" value="oil">
						<label for="cheB1" class="label">油性</label>
						<input type="radio" class="radio" id="cheB2" name="solventType3" value="water">
						<label for="cheB2" class="label">水性</label>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂VOC含量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocRate3">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">有机溶剂挥发度(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="vocVolatility3">
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