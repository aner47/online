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
	<title>加油站调查表基本信息</title>
	<script type="text/javascript">
		require(["grid","hide","select","panel","validate","ajaxform"],function(g,hide){
			$("#confirm").click(function(){
				$("#constructionsite_form").submit();
			})
			$("#holdingGroup","#constructionsite_form").select({name:"holdingGroup",defaultValue:'${petrolStationBase.holdingGroup}',zIndex:10});
		
			//提交表单
		$("#constructionsite_form").validate({
			rules: {
				annualSalesVolume:{
					min:0
				},
				occupyArea:{
					min:0
				},
				storageTinNumber:{
					min:0
				},
				storageArea:{
					min:0
				},
				recycleRate1:{
					min:0,
					range:[0,100]
				},
				recycleRate2:{
					min:0,
					range:[0,100]
				},
				recycleRate3:{
					min:0,
					range:[0,100]
				},
				description:{
					maxlength:255
				},
				isRecycle1:{
					required:true,
				},
				isRecycle2:{
					required:true,
				},
				isRecycle3:{
					required:true,
				},
			},
			messages:{
			},
			submitHandler:function(form){
				var options  = {
					url:base+'/web/petrolstationbase/save.jhtml',
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
			
		//判断油气回收是否选择
		var recoveryOil=[];
		for(var i=1;i<=3;i++){
			recoveryOil[i]= $("[name=isRecycle"+i+"]:checked").val();
			 if(recoveryOil[i] == 'true'||recoveryOil[i] == undefined ){
				 $("input[name=recycleRate"+i+"]").removeAttr('disabled');
		      	 $("input[name=dataSource"+i+"]").removeAttr('disabled');
	       }else{
	    	     $("input[name=recycleRate"+i+"]").attr({disabled:'true'});
	    	     $("input[name=dataSource"+i+"]").attr({disabled:'true'});
	       }           
		
			(function(i){
				$("[name=isRecycle"+i+"]").on('change',function(){
					if ($(this).val()=='true') {
						$("input[name=recycleRate"+i+"]").removeAttr('disabled');
				      	$("input[name=dataSource"+i+"]").removeAttr('disabled');
					}else{	
						$("input[name=recycleRate"+i+"]").attr({disabled:'true'});
			    	    $("input[name=dataSource"+i+"]").attr({disabled:'true'});
					};
				})	
			})(i)
		}
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
			<i class="iconfont icon-jiayouzhanxinxi"></i> 加油站调查表基本信息
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
			<input type="hidden" name="id" value="${petrolStationBase.id}" />
			<div class="infLine">
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${petrolStationBase.inquirer}" required>
						</div>
					</div>
				</div>
	
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${petrolStationBase.inquirerPhone}" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">控股集团</label>
						<div class="infpCon">
							<div data-code="加油站【控股集团】" id="holdingGroup" class="selF"></div>
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">年度经营额（万元）</label>
						<div class="infpCon">
							<input type="text" class="input" name="annualSalesVolume" placeholder="" value="${petrolStationBase.annualSalesVolume}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">占地面积（平方米）</label>
						<div class="infpCon">
							<input type="text" class="input" name="occupyArea" placeholder="" value="${petrolStationBase.occupyArea}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">储罐个数</label>
						<div class="infpCon">
							<input type="text" class="input" name="storageTinNumber" placeholder="" value="${petrolStationBase.storageTinNumber}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">储库面积（平方米）</label>
						<div class="infpCon">
							<input type="text" class="input" name="storageArea" placeholder="" value="${petrolStationBase.storageArea}" number=true required/>
						</div>
					</div>
				</div>
				
				<div class="infh1">一次油气回收</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">是否进行</label>
						<div class="infpCon">
							<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1"
								name="isRecycle1" value="true"
								<c:if test="${petrolStationBase.isRecycle1=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2" name="isRecycle1"
								value="false" 
								<c:if test="${petrolStationBase.isRecycle1!='true' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
							</div>
							<%-- <input type="text" class="input" name="isRecycle1" placeholder="" value="${petrolStationBase.isRecycle1}" /> --%>
						</div>
					</div>
				</div>
				
				<div class="clearfix"></div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油气回收率</label>
						<div class="infpCon">
							<input type="text" class="input" name="recycleRate1" placeholder="" value="${petrolStationBase.recycleRate1}" number=true />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油气回收率数据来源</label>
						<div class="infpCon">
							<input type="text" class="input" name="dataSource1" placeholder="" value="${petrolStationBase.dataSource1}" />
						</div>
					</div>
				</div>
				
				<div class="infh1">二次油气回收</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">是否进行2</label>
						<div class="infpCon">
							<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1" name="isRecycle2" value="true"
								<c:if test="${petrolStationBase.isRecycle2=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2" name="isRecycle2" value="false"
								<c:if test="${petrolStationBase.isRecycle2!='true' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
							</div>
							<%-- <input type="text" class="input" name="isRecycle2" placeholder="" value="${petrolStationBase.isRecycle2}" /> --%>
						</div>
							
					</div>
				</div>
				
				<div class="clearfix"></div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油气回收率2</label>
						<div class="infpCon">
							<input type="text" class="input" name="recycleRate2" placeholder="" value="${petrolStationBase.recycleRate2}" number=true/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油气回收率数据来源2</label>
						<div class="infpCon">
							<input type="text" class="input" name="dataSource2" placeholder="" value="${petrolStationBase.dataSource2}" />
						</div>
					</div>
				</div>
				
				<div class="infh1">三次油气回收</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">是否进行3</label>
						<div class="infpCon">
							<div class="infpCon errorType1">
							<input type="radio" class="radio" id="cheC1" name="isRecycle3" value="true"
								<c:if test="${petrolStationBase.isRecycle3=='true' }">checked='checked'</c:if>>
							<label for="cheC1" class="label">是</label> 
							<input type="radio" class="radio" id="cheC2" name="isRecycle3" value="false"
								<c:if test="${petrolStationBase.isRecycle3!='true' }">checked='checked'</c:if>>
							<label for="cheC2" class="label">否</label>
							</div>
							<%-- <input type="text" class="input" name="isRecycle3" placeholder="" value="${petrolStationBase.isRecycle3}" /> --%>
						</div>
							
					</div>
				</div>
				
				<div class="clearfix"></div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油气回收率3</label>
						<div class="infpCon">
							<input type="text" class="input" name="recycleRate3" placeholder="" value="${petrolStationBase.recycleRate3}" number=true/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">油气回收率数据来源3</label>
						<div class="infpCon">
							<input type="text" class="input" name="dataSource3" placeholder="" value="${petrolStationBase.dataSource3}" />
						</div>
					</div>
				</div>
				

				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${petrolStationBase.description}</textarea>
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