<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta charset="UTF-8">
<title>增加钢铁</title>
</head>

<script type="text/javascript">
	require([ "hide","validate", "ajaxform", "select","panel","date" ], function(hide) {
		
		//生产工序
		var manufactureProcess = $("#manufactureProcess", "#steel_form").select({
			name : "manufactureProcess",
			zIndex : 9,
			empty: true,
			change:function(event, value){
				equipmentType.loadData(value);
				productType.loadData(value);
			}
		});
		//设备类型
		var equipmentType = $("#equipmentType", "#steel_form").select({
			name : "equipmentType",
			zIndex : 8,
			empty: true,
			param:manufactureProcess.getSelectValue()
		});
		
		$("#putOnDate", "#steel_form").jeDate({
            format:"YYYY-MM",
            isTime:false, 
            zIndex:999999900
        })
        $("#stopData", "#steel_form").jeDate({
            format:"YYYY-MM",
            isTime:false, 
            zIndex:999999900
        })
        /* 日期比较-开始  */
		function duibi(d1,d2)
		{
		  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		}
		/* 日期比较-结束 */
		
		var fuelType = $("#fuelType", "#steel_form").select({
			name : "fuelType",isCustom : true,
			zIndex : 7,
			 empty: true
		});
		var productType = $("#productType", "#steel_form").select({
			name : "product.productType",
			zIndex : 6,
			 empty: true,
			 param:manufactureProcess.getSelectValue()
		});
		var rawMaterials1 = $("#rawMaterials1", "#steel_form").select({
			name : "rawMaterials1.type",
			zIndex : 5,
			empty: true,
		});
		var rawMaterials2 = $("#rawMaterials2", "#steel_form").select({
			name : "rawMaterials2.type",
			zIndex : 4,
			empty: true,
		});
		var rawMaterials3 = $("#rawMaterials3", "#steel_form").select({
			name : "rawMaterials3.type",
			zIndex : 3,
			empty: true,
		});
		var rawMaterials4 = $("#rawMaterials4", "#steel_form").select({
			name : "rawMaterials4.type",
			zIndex : 2,
			empty: true,
		});
		var rawMaterials5 = $("#rawMaterials5", "#steel_form").select({
			name : "rawMaterials5.type",
			zIndex : 1,
			empty: true,
		});
		
       
		
		//提交表单
		$("#steel_form").validate({
			rules : {
				manufactureProcess:{
					required:true
				},
				no:{
					required:true
				},
				equipmentType:{
					required:true
				},
				productionCapacity:{
					required:true
				},
				scale:{
					required:true
				},
				putOnDate:{
					required:true
				},
				fuelType:{
					required:true
				},
				
				fuelConsumption:{
					required:true
				},
				fuelUnit:{
					required:true,
					checkUnit:/\D/
				},
				
				description:{
					maxlength:255
				},
			},
			messages : {
				description:{
					maxlength:"最多255字"
				}
			},
			submitHandler : function(form) {
				//日期比较
				if(duibi($("#steel_form #putOnDate").val(),$("#steel_form #stopData").val())){
					layer.msg('关停时间不能早于投运时间',{icon:3});
					return false;
				}
				
				if (!isSubmit($("input[name ='rawMaterials1.type']").val(), 
					$("input[name ='rawMaterials1.consumption']").val(), 
					$("input[name ='rawMaterials1.fuelSulfurContent']").val()))
					{qy.suc3({title:"原料1必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials2.type']").val(), 
					$("input[name ='rawMaterials2.consumption']").val(),
					$("input[name ='rawMaterials2.fuelSulfurContent']").val()))
					{qy.suc3({title:"原料2必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials3.type']").val(),
					$("input[name ='rawMaterials3.consumption']").val(),
					$("input[name ='rawMaterials3.fuelSulfurContent']").val()))
					{qy.suc3({title:"原料3必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials4.type']").val(),
					$("input[name ='rawMaterials4.consumption']").val(), 
					$("input[name ='rawMaterials4.fuelSulfurContent']").val()))
					{qy.suc3({title:"原料4必须填写3个参数！"});return false}
				if (!isSubmit($("input[name ='rawMaterials5.type']").val(),
					$("input[name ='rawMaterials5.consumption']").val(),
					$("input[name ='rawMaterials5.fuelSulfurContent']").val()))
					{qy.suc3({title:"原料5必须填写3个参数！"});return false}
				
									
				if (!isSubmit($("input[name ='product.productType']").val(), 
					$("input[name ='product.yield']").val(), 
					$("input[name ='product.fuelSulfurContent']").val()))
					{qy.suc3({title:"产品必须填写3个参数！"});return false} 
					var options = {
						url : base + '/web/steel/save.jhtml',
						type : 'post',
						dataType : 'json',
						success : function(data) {
							qy.suc2({title:'添加成功！'});
							grid1.loadData();
							layer.closeAll('page');
						},
						error : function() {
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
	<form class="hideCls" id="steel_form" style="padding:0;width:100%;">
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>生产工序</label>
					<div class="infpCon">
						<div id="manufactureProcess" data-code="钢铁【生产工序】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>设备编号</label>
					<div class="infpCon">
						<input type="" class="input" id="no" name="no"/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>设备类型</label>
					<div class="infpCon">
						<div id="equipmentType" data-code="钢铁【设备类型】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>设计生产能力</label>
					<div class="infpCon">
						<input type="" class="input" id="productionCapacity" 
						min="0"
						name="productionCapacity" number="true"/>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>规模</label>
					<div class="infpCon">
						<input type="" class="input" id="scale" name="scale" min="0"/>
					</div>
				</div>
			</div>
			<div class="infPara">
				
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
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>关停时间</label>
					<div class="infpCon">
						<input type="" class="input" id="stopData" name="stopData" readonly>
					</div>
				</div>
			</div>
			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>燃料类型</label>
					<div class="infpCon">
						<div id="fuelType" data-code="钢铁【燃料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>消耗量</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelConsumption" number="true" min="0">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>单位</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelUnit">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="fuelSulfurContent" number="true" min="0" max="100">
					</div>
				</div>
			</div>

				
			<div class="infh1">原料信息1</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials1.type"> -->
						<div id="rawMaterials1" name="rawMaterials1.type" data-code="钢铁【原料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">消耗量(万吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.consumption" number="true" min="0">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials1.fuelSulfurContent" number="true" min="0" max="100">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息2</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials2.type"> -->
						<div id="rawMaterials2" name="rawMaterials2.type" data-code="钢铁【原料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">消耗量(万吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.consumption" number="true" min="0">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials2.fuelSulfurContent" number="true" min="0" max="100">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息3</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials3.type"> -->
						<div id="rawMaterials3" name="rawMaterials3.type" data-code="钢铁【原料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">消耗量(万吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.consumption" number="true" min="0">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials3.fuelSulfurContent" number="true" min="0" max="100">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息4</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials4.type"> -->
						<div id="rawMaterials4" name="rawMaterials4.type" data-code="钢铁【原料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">消耗量(万吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.consumption" number="true" min="0" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials4.fuelSulfurContent" number="true" min="0" max="100">
					</div>
				</div>
			</div>

			<div class="infh1">原料信息5</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">原辅料类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="rawMaterials5.type"> -->
						<div id="rawMaterials5" name="rawMaterials5.type" data-code="钢铁【原料类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">消耗量(万吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.consumption" number="true" min="0">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="rawMaterials5.fuelSulfurContent" number="true" min="0" max="100"> 
					</div>
				</div>
			</div>

			

			<div class="infh1">产品信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">产品类型</label>
					<div class="infpCon">
						<!-- <input type="" class="input" name="product.productType" required > -->
						<div id="productType" name="product.productType" data-code="钢铁【产品类型】" class="selF"></div>
						
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年产量(万吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="product.yield" number="true" min="0"/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">含硫量(%)</label>
					<div class="infpCon">
						<input type="" class="input" name="product.fuelSulfurContent" number="true" min="0" max="100"/>
					</div>
				</div>
			</div>

			<div class="infPara"></div>

			<div class="infh1">煤气利用情况</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">煤气产生量(万立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="coalGasContent" number="true" min="0"/>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">煤气自用量(万立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="coalGasUseContent" number="true" min="0"/>
					</div>
				</div>
			</div>
			<div class="infh1">预处理和后处理</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">是否存在化学加工</label>
					<div class="infpCon">
						<div class="infpCon errorType1">
									<input type="radio" class="radio" id="isLampblackWipe1"
										name="isChemical" value="是"
										<c:if test="${baseInformation.isLampblackWipe=='是' }">checked='checked'</c:if>>
									<label for="isLampblackWipe1" class="label">是</label> <input
										type="radio" class="radio" id="isLampblackWipe2"
										name="isChemical" value="否"
										<c:if test="${baseInformation.isLampblackWipe=='否' }">checked='checked'</c:if>>
									<label for="isLampblackWipe2" class="label">否</label>
								</div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">熄焦工艺</label>
					<div class="infpCon">
						<input type="" class="input" name="coke" />
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