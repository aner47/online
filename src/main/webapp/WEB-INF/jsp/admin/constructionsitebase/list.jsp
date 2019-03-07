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
	<title>施工工地基本信息</title>
	<script type="text/javascript">
		require(["grid","hide","select","panel","validate","ajaxform","date" ],function(g,hide){
			
			$("#everydayWorkTime", "#constructionsite_form").jeDate({
	            format:"hh:mm",
	            zIndex:999999900
	        });
			$("#everydayOffTime", "#constructionsite_form").jeDate({
	            format:"hh:mm",
	            zIndex:999999900
	        });
			
			
			$("#confirm").click(function(){
				$("#constructionsite_form").submit();
			})
			$("#siteType","#constructionsite_form").select({name:"siteType",defaultValue:'${constructionSiteBase.siteType}',zIndex:10});
			$("#governanceMeasures","#constructionsite_form").select({name:"governanceMeasures",defaultValue:'${constructionSiteBase.governanceMeasures}',zIndex:9,multselect:true,isCustom: true});
			$("#muckQuantityUnit","#constructionsite_form").select({name:"muckQuantityUnit",defaultValue:'${constructionSiteBase.muckQuantityUnit}',zIndex:7});
			$("#paintType","#constructionsite_form").select({name:"paintType",defaultValue:'${constructionSiteBase.paintType}',zIndex:8});
			//提交表单
		$("#constructionsite_form").validate({
			rules: {
				constructionArea:{
					min:0
				},
				buildArea:{
					min:0
				},
				occupyArea:{
					min:0
				},
				cubageRate:{
					min:0,
					range:[0,100]
				},
				hardenRoadArea:{
					min:0
				},
				nakedArea:{
					min:0
				},
				excavationQuantity:{
					min:0
				},
				governanceMeasures:{
					required:true
				},
				muckQuantity:{
					min:0
				},
				description:{
					maxlength:255
				},
				siteType:{
					required:true
				},
				paintUsageAmount:{
					min:0
				}
				
			},
			messages:{
			},
			submitHandler:function(form){
				//日期比较
            	if(duibi($("#constructionsite_form #everydayWorkTime").val(), $("#constructionsite_form #everydayOffTime").val())){
  					layer.msg('收工时间不能早于开工时间', {icon:3});
  					return false;
  				} 
				
				var options  = {
					url:base+'/web/constructionsitebase/save.jhtml',
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
		
		/* 日期比较-开始  */
		function duibi(d1,d2)
		{
			console.log(d1);
			console.log(d2);
	  		//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
	  		//return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
			var date = new Date();
			var a = d1.split(":");
			var b = d2.split(":");
			return date.setHours(a[0],a[1]) > date.setHours(b[0],b[1]);
	  	}
	  	/* 日期比较-结束 */
				  	
		
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
			<i class="iconfont icon-shigonggongdi"></i> 施工工地基本信息
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
			<input type="hidden" name="id" value="${constructionSiteBase.id}" />
			<div class="infLine">

				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">调查人</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirer"
								value="${constructionSiteBase.inquirer}" required>
						</div>
					</div>
				</div>
	
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">联系方式</label>
						<div class="infpCon">
							<input type="" class="input" name="inquirerPhone"
								value="${constructionSiteBase.inquirerPhone}" required>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">建设单位</label>
						<div class="infpCon">
							<input type="text" class="input" name="developmentOrganization" placeholder="" value="${constructionSiteBase.developmentOrganization}" required />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">施工单位</label>
						<div class="infpCon">
							<input type="text" class="input" name="constructionOrganization" placeholder="" value="${constructionSiteBase.constructionOrganization}" required />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">工地名称</label>
						<div class="infpCon">
							<input type="text" class="input" name="siteName" placeholder="" value="${constructionSiteBase.siteName}" required />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">工地类型</label>
						<div class="infpCon">
							<div data-code="施工工地【工地类型】" id="siteType" class="selF"></div>
							<%-- <input type="text" class="input" name="siteType" placeholder=""
							 value="${constructionSiteBase.siteType}" /> --%>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">每日开工时间</label>
						<div class="infpCon">
							<input type="text"  class="input" id="everydayWorkTime" 
							name="everydayWorkTime"  
							value="${constructionSiteBase.everydayWorkTime}" required
							onfocus="this.blur()"/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">每日收工时间</label>
						<div class="infpCon">
							<input type="text"  class="input" id="everydayOffTime" 
							name="everydayOffTime"  
							value="${constructionSiteBase.everydayOffTime}" required
							onfocus="this.blur()" />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">施工面积（平方米）</label>
						<div class="infpCon">
							<input type="text" class="input" name="constructionArea" placeholder="" value="${constructionSiteBase.constructionArea}" number=true />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">建筑面积（平方米）</label>
						<div class="infpCon">
							<input type="text" class="input" name="buildArea" placeholder="" value="${constructionSiteBase.buildArea}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">占地面积（平方米）</label>
						<div class="infpCon">
							<input type="text" class="input" name="occupyArea" placeholder="" value="${constructionSiteBase.occupyArea}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">容积率</label>
						<div class="infpCon">
							<input type="text" class="input" name="cubageRate" placeholder="" value="${constructionSiteBase.cubageRate}" number=true />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">硬化道路面积</label>
						<div class="infpCon">
							<input type="text" class="input" name="hardenRoadArea" placeholder="" value="${constructionSiteBase.hardenRoadArea}" number=true />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">裸土面积（平方米）</label>
						<div class="infpCon">
							<input type="text" class="input" name="nakedArea" placeholder="" value="${constructionSiteBase.nakedArea}" number=true required/>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">开挖土方量（立方米）</label>
						<div class="infpCon">
							<input type="text" class="input" name="excavationQuantity" placeholder="" value="${constructionSiteBase.excavationQuantity}" number=true />
						</div>
					</div>
				</div>
				
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">建筑涂料使用量（吨）</label>
						<div class="infpCon">
							<input type="text" class="input" name="paintUsageAmount" placeholder="" value="${constructionSiteBase.paintUsageAmount}" number=true />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">建筑涂料类型</label>
						<div class="infpCon">
							<div data-code="施工工地【建筑涂料类型】" id="paintType" class="selF"></div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">渣土清运量</label>
						<div class="infpCon">
							<input type="text" class="input" name="muckQuantity" placeholder="" value="${constructionSiteBase.muckQuantity}" number=true />
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">渣土清运量单位</label>
						<div class="infpCon">
							<div data-code="施工工地【渣土清运量单位】" id="muckQuantityUnit" class="selF"></div>
						</div>
					</div>
				</div>
				<div class="infPara">
					<div class="infParaCon">
						<label for="" class="infpLable">扬尘控制措施</label>
						<div class="infpCon">
							<div data-code="施工工地【扬尘控制措施】" id="governanceMeasures" class="selF"></div>
							<%-- <input type="text" class="input" name="governanceMeasures" 
							placeholder="" value="${constructionSiteBase.governanceMeasures}" /> --%>
						</div>
					</div>
				</div>

				
				<div class="infPara infPara2">
					<div class="infParaCon">
						<label for="" class="infpLable">备注</label>
						<div class="infpCon">
							<textarea class="textarea" name="description">${constructionSiteBase.description}</textarea>
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