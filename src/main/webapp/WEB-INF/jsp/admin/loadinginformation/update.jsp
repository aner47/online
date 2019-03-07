<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta charset="UTF-8">
<title>修改装载</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<style>
	#materialDensity-error{
		top:20px;
		right:-20px;
		width:80px;
	}
</style>
<script type="text/javascript">
	require(["hide", "validate", "ajaxform" ], function(hide) {
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		$("#loadMethod", "#inputForm").select({
			name : "loadMethod",filter:false,
			defaultValue : '${loadingInformation.loadMethod}',
			zIndex : 10
		});
		$("#operationMethod", "#inputForm").select({
			name : "operationMethod",filter:false,
			defaultValue : '${loadingInformation.operationMethod}',
			zIndex : 9
		});
		$("#recycling", "#inputForm").select({
			name : "recycling",isCustom : true,
			multselect:true,
			defaultValue : '${loadingInformation.recycling}',
			zIndex : 8
		});
		//提交表单
		$("#inputForm").validate({
			rules: {
				annualLoad:{
					min:0,
					number:true,
					required:true,
				},
				materialDensity:{
					min:0,
				},
				recoveryRate:{
					range:[0,100]
				},
				materialType:{
					required:true
				},
				loadMethod:{
					required:true
				},
				operationMethod:{
					required:true,
				},
				recycling:{
					required:true,
				},
			},
			messages:{
				annualLoad:{
					min:"最小为0"
				},
				materialDensity:{
					min:"最小为0",
				},
				recoveryRate:{
					range:"0~100!"
				}
			},
			submitHandler : function(form) {
				var options = {
					url : 'update.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('修改成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('修改失败', {
							icon : 3
						});
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
	<form class="hideCls" id="inputForm" style="padding:0;width:100%;">
		<input type="hidden" id="id" name="id" value="${loadingInformation.id}" />
		<input type="hidden" id="no" name="no" value="${loadingInformation.no}" />
		<div class="infLine">
			<%-- <div class="infPara">
				<div class="infParaCon">
					<label for="no" class="infpLable">装卸环节编号</label>
					<div class="infpCon">
						<input type="" class="input"  id="no" name="no" placeholder="编号" value="${loadingInformation.no}" >
					</div>
				</div>
			</div> --%>

			<div class="infPara">
				<div class="infParaCon">
					<label for="location" class="infpLable">作业部/车间</label>
					<div class="infpCon">
						<input type="" class="input" id="location" name="location" value="${loadingInformation.location}" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="platformNo" class="infpLable">装车站台编号</label>
					<div class="infpCon">
						<input type="" class="input" id="platformNo" name="platformNo" value="${loadingInformation.platformNo}" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="materialType" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>装卸物料名称</label>
					<div class="infpCon">
						<input type="" class="input" id="materialType" name="materialType" value="${loadingInformation.materialType}" >
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="loadMethod" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>装载方式</label>
					<div class="infpCon">
						<div  data-code="装载方式" id="loadMethod" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="operationMethod" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>操作方式</label>
					<div class="infpCon">
						<div  data-code="操作方式" id="operationMethod" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="annualLoad" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>年装载量（吨）</label>
					<div class="infpCon">
						<input type="" class="input" id="annualLoad" name="annualLoad" value="${loadingInformation.annualLoad}" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="materialDensity" class="infpLable">装载物料密度（千克/立方米）</label>
					<div class="infpCon">
						<input type="" class="input" id="materialDensity" name="materialDensity" value="${loadingInformation.materialDensity}" number="true">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable"><span style="margin-right: 5px;color: #eb2a33;">*</span>油气回收技术(可多选)</label>
					<div class="infpCon">
						<div  data-code="装载信息【回收技术】" id="recycling" class="selF"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="recoveryRate" class="infpLable">油气回收效率（%）</label>
					<div class="infpCon">
						<input type="" class="input" id="recoveryRate" name="recoveryRate" value="${loadingInformation.recoveryRate}" number="true">
					</div>
				</div>
			</div>
			
		</div>
	</form>
	<input type="hidden" id="fieldHide" value="${fieldShowConfig}" />