<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
	<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css">
	<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>干洗企业基本信息</title>
	<style>
		#isDispose-error{
			position:absolute;
			left:100px;
		}
	</style>
	<script type="text/javascript">
		require(["grid","hide","select","panel","validate","ajaxform"],function(g,hide){
			$("#confirm").click(function(){
				$("#constructionsite_form").submit();
			})
			
			$("#disposeType","#constructionsite_form").select({name:"disposeType",defaultValue:'${dryClearBase.disposeType}',zIndex:10});
			$("#processMode","#constructionsite_form").select({name:"processMode",defaultValue:'${dryClearBase.processMode}',zIndex:9});
			
			//提交表单
		$("#constructionsite_form").validate({
			rules: {
				closedNum:{
					min:0
				},
				openNum:{
					min:0
				},
				equipmentCapacity:{
					min:0
				},
				annualOutput:{
					min:0
				},
				unit:{
					checkUnit:/\D/
				},
				annualSalesVolume:{
					min:0
				},
				description:{
					maxlength:255
				},
				isDispose:{
					required:true
				},
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
					url:base+'/web/dryclearbase/save.jhtml',
					type:'post',
					dataType : 'json',
					success:function(data){
						qy.suc2({title:'保存成功！'});
					},
					error:function(){
						qy.fail2({title:'保存失败！'});
					}
				};
				$(form).ajaxSubmit(options);
				return false;
			}
		});
			hide.hide();
			$("#fixed_return").on("click", function() {
				window.location.href = "../enterprise/enterpriseTaskList.jhtml";
				top.loadMenu('188');
				top.layer.closeAll('page');
			})
			$("#back").on("click", function() {
				window.location.href = "../enterprise/list.jhtml";
				top.loadMenu('2','31');
			})
			
			/* $("#check").click(function(){
				var id = ${enterprise.id};
				
				var opts = {
					title: "审核",
					url : "checkPage.jhtml",
					data:{"enterprieId": id},
					btn: ['通过', '不通过'],
					yes : function(){
						$("#inputForm #status").val("3");
						$("#inputForm").submit();
						
					},
					btn2:function(){
						$("#inputForm #status").val("4");
						$("#inputForm").submit();
						
					},
					cancel:function(){}
				}
				qy.panel(opts);
			}); */
			
			$("#check").on("click", function() {
				var id = ${enterprise.id};
				top.panel1(id);
			})
	})
</script>
<style>
.block .buttonF {
	padding-right: 6px;
	padding-bottom: 18px;
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
	<div class="block hAuto">
		<h1 class="title">
			<i class="iconfont icon-ganxi"></i> 干洗企业基本信息
			<c:if test="${principal.userType != 'system'}">
			<div id="back" class="titleBtn">返回</div>
			</c:if>
			<c:if test="${principal.userType == 'system'}">
				<div id="fixed_return" class="titleBtn">返回</div>
				<div id="check" class="titleBtn examine">审核</div>
			</c:if>
		</h1>
	</div>
	<div class="block hAuto" style="min-height: calc(100% - 65px);">
		<form id="constructionsite_form">
			<input type="hidden" name="id" value="${dryClearBase.id}" />
			<div class="infLine">
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${dryClearBase.inquirer}" required>
						</div>
					</div>
				</div>
	
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${dryClearBase.inquirerPhone}" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">干洗设备型号</label>
						<div class="infpCon">
							<input type="text" class="input" name="equipmentType" placeholder="" value="${dryClearBase.equipmentType}" />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">干洗设备品牌</label>
						<div class="infpCon">
							<input type="text" class="input" name="equipmentModel" placeholder="" value="${dryClearBase.equipmentModel}" />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">封闭式台数</label>
						<div class="infpCon">
							<input type="text" class="input" name="closedNum" placeholder="" value="${dryClearBase.closedNum}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">开启式台数</label>
						<div class="infpCon">
							<input type="text" class="input" name="openNum" placeholder="" value="${dryClearBase.openNum}" number=true required/>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">设备总容量（千克）</label>
						<div class="infpCon">
							<input type="text" class="input" name="equipmentCapacity" placeholder="" value="${dryClearBase.equipmentCapacity}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年营业额（万元）</label>
						<div class="infpCon">
							<input type="text" class="input" name="annualSalesVolume" placeholder="" value="${dryClearBase.annualSalesVolume}" number=true />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">废弃有机溶剂是否进行回收</label>
						<div class="infpCon">
							<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1" name="isDispose" value="true"
								<c:if test="${dryClearBase.isDispose=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2" name="isDispose" value="false"
								<c:if test="${dryClearBase.isDispose=='false' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
							</div>
							<%-- <input type="text" class="input" name="isDispose" placeholder="" value="${dryClearBase.isDispose}" /> --%>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">回收方式</label>
						<div class="infpCon">
							<div data-code="干洗企业【回收方式】" id="disposeType" class="selF"></div>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">处理方式</label>
						<div class="infpCon">
							<div data-code="干洗企业【处理方式】" id="processMode" class="selF"></div>
						</div>
					</div>
				</div>
				
				<%-- <div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">单位</label>
						<div class="infpCon">
							<input type="text" class="input" name="unit" placeholder="" value="${dryClearBase.unit}" />
						</div>
					</div>
				</div> --%>
				
				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${dryClearBase.description}</textarea>
						</div>
					</div>
				</div> 

			</div>
			<c:if test="${principal.userType != 'system'}">
			<div class="buttonF">
				<a class="saveBtn" id="confirm">保存</a>
			</div>
		</c:if>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</div>
</body>
</html>