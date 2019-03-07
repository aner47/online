<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta charset="UTF-8">
<title>增加有机液体储罐</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<script type="text/javascript">
require(["hide","validate","ajaxform","select","panel"],function(hide){
	$("#tankMaterialType","#oiltank_form").select({filter:false,name:"tankMaterialType",isCustom : true,afterInit:function(){alert(select.getSelectValue());}});
	$("#tankType","#oiltank_form").select({filter:false,name:"tankType",afterInit:function(){alert(select.getSelectValue());}});
		$('#commit').click(function(){
			$("#oiltank_form").submit();
		})
		//提交表单
		$("#oiltank_form").validate({
			rules: {
				workDays: {
					range:[0,366],
				},
				storageCycle: {
					min:0
				},
				tankMaterialType: {
					required:true,
				},
				tankType: {
					required:true,
				},
				capacity: {
					required:true,
					min:0
				},
				height: {
					min:0
				},
				avgDiameter: {
					min:0
				},
				storageCapacity: {
					min:0
				},
				fillTimes: {
					min:0,
					maxlength:10,
					digits:true,
				},
				description:{
					maxlength:255
				},
				storageCycle:{
					range:[0,366]
				},
				height:{
					range:[0,100]
				},
			},
			messages:{
				description:{
					maxlength:"最多255字"
				}
			},
			submitHandler:function(form){
				var options  = {
			        url:base+"/web/oiltank/save.jhtml",
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						qy.suc2({title:'添加成功！'});
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
	<h4 style="height: 27px;line-height: 27px; padding-left: 20px; color: #eb2a33; background: #7eb0ff;">注意: * 表示必填项</h4>
	<form class="hideCls" id="oiltank_form" style="padding:0;width:100%;">
		
		<div class="infLine">
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>材料类型</label>
					<div class="infpCon">
						<div  id="tankMaterialType" data-code="储罐【材料】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>储罐类型</label>
					<div class="infpCon">
						<div  id="tankType" data-code="储罐【类型】" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">高度/长度(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="height" placeholder="" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">平均直径(米)</label>
					<div class="infpCon">
						<input type="" class="input" name="avgDiameter" placeholder="" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>储罐容量(立方米)</label>
					<div class="infpCon">
						<input type="" class="input" name="capacity" placeholder="" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">全年使用天数(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="workDays" placeholder="" number=true >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>储罐年总储量(吨)</label>
					<div class="infpCon">
						<input type="" class="input" name="storageCapacity" placeholder="" number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>储存周期(天)</label>
					<div class="infpCon">
						<input type="" class="input" name="storageCycle" placeholder="" number=true required>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年装卸/填充次数</label>
					<div class="infpCon">
						<input type="" class="input" name="fillTimes" placeholder="" number=true required>
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