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
<title>增加分月信息</title>
</head>

<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel","year"],function(hide){
	
	var year = $('#monthinfo_form #year').year({'name':'year'});
	var monthlyType = '${monthlyType}';
	var simpleData = {};
	var dataValue ;
	var sourceId = '${sourceId}';
	
	var sourceConsumption;
	var sourceUnit;
	
	//发电机组
	if(monthlyType == 'powerplant'){
		$('#powerPlantId').val('${sourceId}');
		simpleData['发电量'] ='发电量';
		simpleData['供热量'] ='供热量';
		dataValue = 'powerPlant';
	//锅炉	
	}else if(monthlyType =='boiler'){
		$('#boilerId').val('${sourceId}');
		//simpleData['发电量'] ='发电量';
		dataValue = 'boiler';
	//煤气炉	
	}else if(monthlyType =='gasstove'){
		$('#gasstoveId').val('${sourceId}');
		simpleData['煤气产生量'] ='煤气产生量';
		dataValue = 'gasstove';
	//炉窑	
	}else if(monthlyType =='kiln'){
		$('#kilnId').val('${sourceId}');
		dataValue = 'kiln';
	//生产线	
	}else if(monthlyType =='section'){
		$('#sectionId').val('${sourceId}');
		dataValue = 'section';
	//原辅料	
	}else if(monthlyType =='rawmaterials'){
		$('#rawMaterialsId').val('${sourceId}');
		dataValue = 'rawmaterials';
	}
	
	
	
	
	
	var name = $('#monthinfo_form #name').select({name:'name',zIndex:20
		,url:"../monthlyinformation/findSourceNameById.jhtml"
		,param:"type:"+dataValue+",sourceId:"+sourceId
		,change:function(enevt,value){
			console.log(value);
			console.log(name.getSelectOtherValue().consumption);
			console.log(name.getSelectOtherValue().unit);
			sourceConsumption = name.getSelectOtherValue().consumption;
			sourceUnit = name.getSelectOtherValue().unit;
			unit.setSelectValue(name.getSelectOtherValue().unit);
			year.setYearValue(name.getSelectOtherValue().year);
			if(sourceConsumption != -1){
				$('#monthinfo_form #consumption').val(sourceConsumption);
			}else{
				$('#monthinfo_form #consumption').val('');
			}
			$('#monthinfo_form #monthType').val(name.getSelectOtherValue().type);
		}
		});
	var unit = $('#monthinfo_form #unit').select({name:'unit',zIndex:19,isCustom: true});
	
		$('#commit').click(function(){
			$("#monthinfo_form").submit();
		})
		//提交表单
		$("#monthinfo_form").validate({
			rules: {
				monthlyInformationProcess: {
					required:true
				},
				name: {
					required:true
				},
				unit: {
					required:true,
					checkUnit:/\D/
				},
				january: {
					min:0
				},
				february: {
					min:0
				},
				march: {
					min:0
				},
				april: {
					min:0
				},
				may: {
					min:0
				},
				june: {
					min:0
				},
				july: {
					min:0
				},
				august: {
					min:0
				},
				september: {
					min:0
				},
				october: {
					min:0
				},
				november: {
					min:0
				},
				december: {
					min:0
				},
				description:{
					maxlength:255
				},
				year:{
					required:true
				}
			},
			messages:{
				january:{
					min:"最小为0"
				},
				february:{
					min:"最小为0"
				},
				march:{
					min:"最小为0"
				},
				april:{
					min:"最小为0"
				},
				may:{
					min:"最小为0"
				},
				june:{
					min:"最小为0"
				},
				july:{
					min:"最小为0"
				},
				august:{
					min:"最小为0"
				},
				september:{
					min:"最小为0"
				},
				october:{
					min:"最小为0"
				},
				november:{
					min:"最小为0"
				},
				december:{
					min:"最小为0"
				},
				unit:{
					checkUnit:"字符及符号"
				},
				description:{
					maxlength:"最多255字"
				}
			},
			submitHandler:function(form){
				//年消耗量与分月消耗量作比较
				var january=Number($("input[name=january]").val());
				var february=Number($("input[name=february]").val());
				var march=Number($("input[name=march]").val());
				var april=Number($("input[name=april]").val());
				var may=Number($("input[name=may]").val());
				var june=Number($("input[name=june]").val());
				var july=Number($("input[name=july]").val());
				var august=Number($("input[name=august]").val());
				var september=Number($("input[name=september]").val());
				var october=Number($("input[name=october]").val());
				var november=Number($("input[name=november]").val());
				var december=Number($("input[name=december]").val());
				var usageAmount=sourceConsumption;
				var monthlySum=january+february+march+april+may+june+july+august+september+october+november+december;
				var monthlySum = parseFloat(new Number(monthlySum).toFixed(2));
				console.log("总和==="+monthlySum);
				console.log("消耗量==="+new Number(usageAmount).toFixed(2));
				var usageAmount95 = parseFloat(new Number(sourceConsumption*0.95).toFixed(2));
				var usageAmount105 = parseFloat(new Number(sourceConsumption*1.05).toFixed(2));
				console.log("消耗量的95%===" +usageAmount95);
				console.log("消耗量的105%===" +usageAmount105);
				if(usageAmount != -1){
					//&& 
					if(monthlySum < usageAmount95){
						layer.msg('错误：分月合计量与所填报的消耗总量差异过大',{icon:3});
						return false;
					}else if(monthlySum > usageAmount105){
						layer.msg('错误：分月合计量与所填报的消耗总量差异过大',{icon:3});
						return false;
					}
				}
				
				/* if(new Number(monthlySum).toFixed(2) > new Number(usageAmount).toFixed(2)){
					layer.msg('错误：月消耗量总和大于年用量',{icon:3});
					return false;
				} */
				
				if(sourceUnit){
					if(sourceUnit != unit.getSelectValue()){
						layer.msg('错误：月消耗单位和源数据单位不一致',{icon:3});
						return false;
					}
				}
				
				
				var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'添加成功！'})
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
		hide.hide();
	});
</script>

<body>
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form id="monthinfo_form" style="padding:0;width:100%;">
		<div class="infLine">

			<div class="infh1">数据信息</div>
			<input type="hidden" class="input" id="powerPlantId" name="powerPlantId" />
			<input type="hidden" class="input" id="boilerId" name="boilerId" />
			<input type="hidden" class="input" id="gasstoveId" name="gasstoveId" />
			<input type="hidden" class="input" id="kilnId" name="kilnId" />
			<input type="hidden" class="input" id="sectionId" name="sectionId" />
			<input type="hidden" class="input" id="rawMaterialsId" name="rawMaterialsId" />
			<input type="hidden" class="input" id="monthType" name="monthType" />

			<!-- <div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数据来源</label>
					<div class="infpCon">
						<div id="monthlyInformationProcess" data-code="祥表【数据来源】" class="selF"></div>
					</div>
				</div>
			</div> -->

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>数据名称</label>
					<div class="infpCon">
						<div id="name" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">总量</label>
					<div class="infpCon">
						<input type="" class="input" id="consumption" name="consumption" readonly="readonly">
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年份</label>
					<div class="infpCon">
						<div id="year" class="yearF iconfont icon-nianfen"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="unit"> -->
						<div class="selF" id="unit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			

			

			

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>一月</label>
					<div class="infpCon">
						<input type="" class="input" name="january" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>二月</label>
					<div class="infpCon">
						<input type="" class="input" name="february" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>三月</label>
					<div class="infpCon">
						<input type="" class="input" name="march" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>四月</label>
					<div class="infpCon">
						<input type="" class="input" name="april" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>五月</label>
					<div class="infpCon">
						<input type="" class="input" name="may" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>六月</label>
					<div class="infpCon">
						<input type="" class="input" name="june" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>七月</label>
					<div class="infpCon">
						<input type="" class="input" name="july" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>八月</label>
					<div class="infpCon">
						<input type="" class="input" name="august" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>九月</label>
					<div class="infpCon">
						<input type="" class="input" name="september" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>十月</label>
					<div class="infpCon">
						<input type="" class="input" name="october" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>十一</label>
					<div class="infpCon">
						<input type="" class="input" name="november" number="true" required>
					</div>
				</div>
			</div>

			<div class="infPara infPara3">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>十二</label>
					<div class="infpCon">
						<input type="" class="input" name="december" number="true" required>
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