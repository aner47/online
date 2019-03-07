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
	<title>汽修调查表基本信息</title>
	<style type="text/css">
	input[type=radio].radio{
		background:#000;
	}
	input:disabled.radio{
		box-shadow:none !important;
		background:#ccc !important;	
	}
	input[type="radio" i]{
		border:none;
		box-shadow:none;
	}
	.infParaCon .infpCon .radio:disabled{
		cursor: not-allowed;
	}
	</style>
	<script type="text/javascript">
		require(["grid","hide","select","panel","validate","ajaxform"],function(g,hide){
			$("#confirm").click(function(){
				$("#constructionsite_form").submit();
			})
			$("#processType","#constructionsite_form").select({name:"processType",defaultValue:'${breakdownServiceBase.processType}',zIndex:10});
			$("#recycleMode","#constructionsite_form").select({name:"recycleMode",defaultValue:'${breakdownServiceBase.recycleMode}',zIndex:9});
			$("#processMode","#constructionsite_form").select({name:"processMode",defaultValue:'${breakdownServiceBase.processMode}',zIndex:8});
			//提交表单
		$("#constructionsite_form").validate({
			rules: {
				sprayBakeHouse:{
					min:0
				},
				bakeHouse:{
					min:0
				},
				annualSalesVolume:{
					min:0
				},
				processEfficiency:{
					min:0,
					range:[0,100]
				},
				carbonChangePeriod:{
					min:0
				},
				description:{
					maxlength:255
				}
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
					url:base+'/web/breakdownservicebase/save.jhtml',
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
			
		//判断废气是否选择-start
		 var isprocess= $("[name=isprocess]:checked").val();
		 if(isprocess == 'true'){
		 $("div#processType").css("background","#fff");
       	 $("input[name=ispreprocess]").removeAttr('disabled');
       	 $("input[name=processEfficiency]").removeAttr('disabled');
       	 $("input[name=carbonChangePeriod]").removeAttr('disabled');
        }else{
         $("#processType","#constructionsite_form").select({name:"processType",defaultValue:"",zIndex:10});
         $("div#processType").unbind("click");
         $("div#processType").css("background","#ccc");
       	 $("input[name=ispreprocess]").attr({disabled:'true'});
       	 $("input[name=processEfficiency]").attr({disabled:'true'});
       	 $("input[name=carbonChangePeriod]").attr({disabled:'true'});
        }           
	
		$("[name=isprocess]").on('change',function(){
			if ($(this).val()=='true') {
				 $("#processType","#constructionsite_form").select({name:"processType",zIndex:10});
				 $("div#processType").css("background","#fff");
				 $("input[name=ispreprocess]").removeAttr('disabled');
		       	 $("input[name=processEfficiency]").removeAttr('disabled');
		       	 $("input[name=carbonChangePeriod]").removeAttr('disabled');
			}else{
				 $("#processType","#constructionsite_form").select({name:"processType",defaultValue:"",zIndex:10});
				 $("div#processType").unbind("click");
				 $("div#processType").css("background","#ccc");
				 $("input[name=ispreprocess]").attr({disabled:'true'});
		       	 $("input[name=processEfficiency]").attr({disabled:'true'});
		       	 $("input[name=carbonChangePeriod]").attr({disabled:'true'});
			};
		})
		//判断废气是否选择-end
		
		//判断废有机溶剂是否选择-start
		var isrecycle= $("[name=isrecycle]:checked").val();
		if(isrecycle == 'true'){
			 $("div#recycleMode").css("background","#fff");
			 $("div#processMode").css("background","#fff");
       }else{
    	   	 $("#recycleMode","#constructionsite_form").select({name:"recycleMode",defaultValue:"",zIndex:9});
    	   	 $("#processMode","#constructionsite_form").select({name:"processMode",defaultValue:"",zIndex:8});
    	   	 $("div#recycleMode").unbind("click");
   	   	 	 $("div#processMode").unbind("click");
    	     $("div#recycleMode").css("background","#ccc");
			 $("div#processMode").css("background","#ccc");
       }           
	
		$("[name=isrecycle]").on('change',function(){
			console.log($(this).val());
			if ($(this).val()=='true') {
				 $("#recycleMode","#constructionsite_form").select({name:"recycleMode",zIndex:9});
				 $("#processMode","#constructionsite_form").select({name:"processMode",zIndex:8});
				 $("div#recycleMode").css("background","#fff");
				 $("div#processMode").css("background","#fff");
			}else{
				 $("#recycleMode","#constructionsite_form").select({name:"recycleMode",defaultValue:"",zIndex:9});
				 $("#processMode","#constructionsite_form").select({name:"processMode",defaultValue:"",zIndex:8});
				 $("div#recycleMode").unbind("click");
	    	   	 $("div#processMode").unbind("click");
	    	     $("div#recycleMode").css("background","#ccc");
				 $("div#processMode").css("background","#ccc");
			};
		})
		//判断废有机溶剂是否选择-end
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
			<i class="iconfont icon-qixiu"></i> 汽修调查表基本信息
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
			<input type="hidden" name="id" value="${breakdownServiceBase.id}" />
			<div class="infLine">
			
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${breakdownServiceBase.inquirer}" required>
						</div>
					</div>
				</div>
	
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${breakdownServiceBase.inquirerPhone}" required>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">喷烘两用房数量（个）</label>
						<div class="infpCon">
							<input type="text" class="input" name="sprayBakeHouse" placeholder="" value="${breakdownServiceBase.sprayBakeHouse}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">单烘房数量（个）</label>
						<div class="infpCon">
							<input type="text" class="input" name="bakeHouse" placeholder="" value="${breakdownServiceBase.bakeHouse}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年营业额（万元）</label>
						<div class="infpCon">
							<input type="text" class="input" name="annualSalesVolume" placeholder="" value="${breakdownServiceBase.annualSalesVolume}" number=true required/>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">废气是否进行处理</label>
						<div class="infpCon">
							<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1" name="isprocess" value="true"
								<c:if test="${breakdownServiceBase.isprocess=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2" name="isprocess" value="false"
								<c:if test="${breakdownServiceBase.isprocess=='false' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
							</div>
							<%-- <input type="text" class="input" name="isDispose" placeholder="" value="${dryClearBase.isDispose}" /> --%>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">是否进行预处理</label>
						<div class="infpCon">
							<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1" name="ispreprocess" value="true"
								<c:if test="${breakdownServiceBase.ispreprocess=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2" name="ispreprocess" value="false"
								<c:if test="${breakdownServiceBase.ispreprocess=='false' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
							</div>
							<%-- <input type="text" class="input" name="isDispose" placeholder="" value="${dryClearBase.isDispose}" /> --%>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">处理工艺类型</label>
						<div class="infpCon">
							<div data-code="汽修【处理工艺类型】" id="processType" class="selF"></div>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">处理效率（%）</label>
						<div class="infpCon">
							<input type="text" class="input" name="processEfficiency" placeholder="" value="${breakdownServiceBase.processEfficiency}" number=true />
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">活性炭更换周期（天）</label>
						<div class="infpCon">
							<input type="text" class="input" name="carbonChangePeriod" placeholder="" value="${breakdownServiceBase.carbonChangePeriod}" number=true/>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">废有机溶剂是否回收</label>
						<div class="infpCon">
							<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1" name="isrecycle" value="true"
								<c:if test="${breakdownServiceBase.isrecycle=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2" name="isrecycle" value="false"
								<c:if test="${breakdownServiceBase.isrecycle=='false' }">checked='checked'</c:if>>
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
							<div data-code="汽修【回收方式】" id="recycleMode" class="selF"></div>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">处理方式</label>
						<div class="infpCon">
							<div data-code="汽修【处理方式】" id="processMode" class="selF"></div>
						</div>
					</div>
				</div>
				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${breakdownServiceBase.description}</textarea>
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