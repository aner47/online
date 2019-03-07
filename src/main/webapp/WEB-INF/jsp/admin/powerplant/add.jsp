<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>增加自备发电机组</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
	<style>
		#putOnTime-error{
			right:40px;
		}
	</style>
</head>

<script type="text/javascript">
	require(["hide","validate","ajaxform","date","year"],function(hide){
		var projectYear = '${year}';
		var year = $('#inputForm #fuelYear').year({'name':'fuelYear'})
		year.setYearValue(projectYear);
		var changeNames = ["annualPowerGeneration","annualHeatSupply","fuelConsumption","fuelUnit","putOnTime"];
		var ochangeNames = ["stopTime"];
		
		$("#useStatus","#inputForm").select({
			name:"useStatus",zIndex:10,
			filter: false,
			change:function(data,value){
			for(var i = 0; i < changeNames.length; i++){
				if(value == '备用' || value == '停用'){
					$('[name='+changeNames[i]+']', "#inputForm").removeAttr('required');
				}else{
					$('[name='+changeNames[i]+']', "#inputForm").attr('required','true');
				}
			}
			for(var i = 0; i < ochangeNames.length; i++){
				if( value == '停用'){
					// 获取 dom 元素, 第一个在第二个中
					$('[name='+ochangeNames[i]+']', "#inputForm").attr('required','true');
				}else{
					$('[name='+ochangeNames[i]+']', "#inputForm").removeAttr('required');
				}
			}
			
		 	
		}});
		
		$("#emphasisPolluteSource", "#product_form").select({
			name : "emphasisPolluteSource",
			defaultValue : '${baseInformation.emphasisPolluteSource}',
			zIndex : 9
		});
		
		$("#putOnTime", "#inputForm").jeDate({
            format:"YYYY-MM",
            isTime:false,
            zIndex:999999900
        })
        $("#abarbeitungTime", "#inputForm").jeDate({
            format:"YYYY-MM",
            isTime:false,
            zIndex:999999900
        })
        $("#stopTime", "#inputForm").jeDate({
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
		
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		$("#boilerType","#inputForm").select({filter: false,name:"boilerType",zIndex:10,validate:'required',isCustom : true});
		$("#fuelType","#inputForm").select({name:"fuelType",zIndex:9,isCustom : true});
		$("#fuelUnit","#inputForm").select({name:"fuelUnit",zIndex:8,isCustom : true});
		
		$("#governanceMeasures11", "#inputForm").select(
		{
			name : "governanceMeasures11",
			zIndex : 7,
			param : "enterprise:enterprise,project:project"
		});
		$("#governanceMeasures21", "#inputForm").select(
		{
			name : "governanceMeasures21",
			zIndex : 6,
			param : "enterprise:enterprise,project:project"
		});
		$("#governanceMeasures31", "#inputForm").select(
		{
			name : "governanceMeasures31",
			zIndex : 5,
			param : "enterprise:enterprise,project:project"
		});
		$("#exhaustionHoleId1", "#inputForm").select(
		{
			name : "exhaustionHoleId1",
			zIndex : 4,
			param : "enterprise:enterprise,project:project"
		});
		//提交表单
		$("#inputForm").validate({
			rules: {
				installedCapacity:{
					min:0,
					number:true,
					maxlength:16
				},
				annualPowerGeneration:{
					min:0,
					number:true,
					maxlength:16
				},
				annualHeatSupply:{
					min:0,
					number:true,
					maxlength:16
				},
				steamTon:{
					min:0,
					maxlength:16
				},
				fuelType:{
					required:true
				},
				fuelConsumption:{
					min:0,
					maxlength:16
				},
				fuelUnit:{
					checkUnit:/\D/
				},
				fuelSulfurContent:{
					range:[0,100]
				},
				fuelAshContent:{
					range:[0,100]
				},
				fuelVolatiles:{
					range:[0,100]
				},
				isRedian:{
					required:true
				},
				governanceMeasures11:{
					required:true
				},
				governanceMeasures21:{
					required:true
				},
				governanceMeasures31:{
					required:true
				},
				exhaustionHoleId1:{
					required:true
				},
				putOnTime:{
					required:true
				},
				useStatus: {
					required:true
				},
			},
			messages:{
			},
			submitHandler:function(form){
				//日期比较
				if(duibi($("#inputForm #putOnTime").val(),$("#inputForm #stopTime").val())){
					layer.msg('关停时间不能早于投运时间',{icon:3});
					return false;
				}
				if(duibi($("#inputForm #putOnTime").val(),$("#inputForm #abarbeitungTime").val())){
					layer.msg('整改时间不能早于投运时间',{icon:3});
					return false;
				}
				var options  = {
					url:'save.jhtml',
					type:'post',
					dataType : 'json',
					success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
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
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />
	<form class="hideCls" id="inputForm" style="padding:0;width:100%;">
		<div class="infLine">

			<!-- <div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">机组编号</label>
					<div class="infpCon">
						<input type="" class="input" id="no" name="no" placeholder="机组编号">
					</div>
				</div>
			</div> -->

			<div class="infh1">机组信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>装机容量（兆瓦）</label>
					<div class="infpCon">
						<input type="" class="input input_span" id="installedCapacity" name="installedCapacity"
						 value="" number="true" required />
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>使用状态</label>
					<div id="infpCon" class="infpCon">
						<div id="useStatus" data-code="排放口【使用状态】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>投运时间</label>
					<div class="infpCon">
						<input class="input" id="putOnTime" name="putOnTime" type="text" 
						 style="width:180px;"required="true" onfocus="this.blur()" />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">整改时间</label>
					<div class="infpCon">
						<input class="input" id="abarbeitungTime" name="abarbeitungTime" type="text" 
						 style="width:180px;" onfocus="this.blur()" />
					</div>
				</div>
			</div>

			

			<div id="infPara" class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">关停时间</label>
					<div class="infpCon">
						<input class="input" id="stopTime" name="stopTime" type="text" 
						 style="width:180px;"  onfocus="this.blur()" />
					</div>
				</div>
			</div>
			
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年发电量(万千瓦时)</label>
					<div class="infpCon">
						<input type="" class="input input_span" id="annualPowerGeneration" 
						name="annualPowerGeneration"
						value="" number="true" required/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年供热量（万吉焦）</label>
					<div class="infpCon">
						<input type="" class="input input_span" id="annualHeatSupply" 
						name="annualHeatSupply" 
						value="" number="true" required/>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>是否热电联产</label>
					<div class="infpCon">
						<input type="radio" class="radio" id="isRedian"
							name="isRedian" value="是"/>
						<label for="isRedian" class="label">是</label> 
						<input type="radio" class="radio" id="isRedian2"
							name="isRedian" value="否" />
						<label for="isRedian2" class="label">否</label>
					</div>
				</div>
			</div>

			<div class="infh1">设备信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>设备类型</label>
					<div class="infpCon">
						<div data-code="锅炉类型" id="boilerType" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉铭牌型号</label>
					<div class="infpCon">
						<input type="" class="input" id="boilerModel" name="boilerModel" required="true"/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>锅炉蒸吨(蒸吨/小时)</label>
					<div class="infpCon">
						<input type="" class="input input_span" id="steamTon" name="steamTon" 
						 number="true" required/>
					</div>
				</div>
			</div>

			<div class="infh1">燃料信息</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料年份</label>
					<div class="infpCon">
						<div id="fuelYear" class="yearF iconfont icon-nianfen"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>燃料类型</label>
					<div class="infpCon">
						<div data-code="燃料类型" id="fuelType" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年消耗总量</label>
					<div class="infpCon">
						<input type="" class="input" id="fuelConsumption" name="fuelConsumption" number="true" required />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">数量单位</label>
					<div class="infpCon">
						<!-- <input type="" class="input" id="fuelUnit" name="fuelUnit" required/> -->
						<div class="selF" id="fuelUnit" data-code="数量单位"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料含硫率（%）</label>
					<div class="infpCon">
						<input type="" class="input" id="fuelSulfurContent" name="fuelSulfurContent" number="true"/>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料灰分（%）</label>
					<div class="infpCon">
						<input type="" class="input" id="fuelAshContent" name="fuelAshContent" number="true" />
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">燃料挥发分（%）</label>
					<div class="infpCon">
						<input type="" class="input input_span" id="fuelVolatiles" name="fuelVolatiles" number="true"/>
					</div>
				</div>
			</div>

			<div class="infh1"></div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>污染治理措施1</label>
					<div class="infpCon">
						<div class="selF" id="governanceMeasures11" data-code="污染治理措施"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>污染治理措施2</label>
					<div class="infpCon">
						<div class="selF" id="governanceMeasures21" data-code="污染治理措施"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>污染治理措施3</label>
					<div class="infpCon">
						<div class="selF" id="governanceMeasures31" data-code="污染治理措施"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>排放口</label>
					<div class="infpCon">
						<div class="selF" id="exhaustionHoleId1" data-code="排放口"></div>
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
</body>
</html>