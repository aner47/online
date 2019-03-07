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
<title>生产信息</title>
<script type="text/javascript">
    require([ "grid", "select", "panel", "validate", "ajaxform" ], function(g) {
    	$('inputp[type=radio]').click(function(){
    		 $(this).attr('checked','checked').siblings().removeAttr('checked');
    		});
        $("#confirm").click(function() {
            $("#product_form").submit();
        })

        //提交表单
        $("#product_form").validate({
            rules : {},
            messages : {},
            submitHandler : function(form) {
                var options = {
                    url : base + '/web/simplebaseinformation/save.jhtml',
                    type : 'post',
                    dataType : 'json',
                    success : function(data) {
                        qy.suc2({
                            title : '保存成功！'
                        });
                    },
                    error : function() {
                        qy.fail2({
                            title : '保存失败！'
                        });
                    }
                };
                $(form).ajaxSubmit(options);
                return false;
            }
        });
    })
</script>
<style>
.block .buttonF {
    padding-top: 48px;
    padding-right: 6px;
}

textarea {
    resize: none;
}
</style>
</head>
<body>
    <div class="block">
        <h1 class="title">
            <i class="iconfont icon-shengchanxinxi"></i> 生产信息
        </h1>
        <div class="content_div">
            <form id="product_form">
                <input type="hidden" name="id" value="${baseInformation.id}" />
                <div class="content_top">
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu">
                                <span></span>年生产天数(天)
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="workDay"
                                    value="<fmt:formatNumber value="${baseInformation.workDay}" pattern="#.##"/>"
                                    number=true />
                            </div>
                        </div>
                        <div class="content_top_right">
                            <div class="content_menu menu_long">
                                <span></span>日平均生产小时数(小时)
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="grossProduct"
                                    value="<fmt:formatNumber value="${baseInformation.grossProduct}" pattern="#.##"/>"
                                    number=true />
                            </div>
                        </div>
                    </div>
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu menu_long">
                                <span></span>全年生产总值（万元）
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="dayHours"
                                    value="<fmt:formatNumber value="${baseInformation.dayHours}" pattern="#.##"/>"
                                    number=true />
                            </div>
                        </div>
                        <div class="content_top_right">
                            <div class="content_menu menu_long">
                                <span></span>全年用电量（万千瓦时）
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="energyUsed" required
                                    value="<fmt:formatNumber value="${baseInformation.energyUsed}" pattern="#.##"/>"
                                    number=true />
                            </div>
                        </div>
                    </div>
                    <div>
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu menu_long">
                                <span></span>年正常生产时间（小时/年）
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="nomalProductionHour" required
                                    value="<fmt:formatNumber value="${baseInformation.nomalProductionHour}" pattern="#.##"/>"
                                    number=true />
                            </div>
                        </div>
                    </div>
                    <div class="contentdiv">
                               <div class="content_top_left">
                        <div class="content_menu menu_long">
                            <span></span>是否废气重点污染源
                        </div>
                          <div class="natureC left">
                       <input type="radio"
                            name="emphasisPolluteSource" id="s" value="water"> <label
                            for="s" class="span_click" value="${baseInformation.emphasisPolluteSource}">是</label> <span
                            class="blue span_click"></span> 
                            <input type="radio"
                            name="emphasisPolluteSource" id="t" checked='checked'
                            value="${baseInformation.emphasisPolluteSource}"> <label for="t" class="span_click">否</label>
                    </div>
                    </div>
                             <div class="content_top_right">
                        <div class="content_menu menu_long">
                            <span></span>连续/间歇生产
                        </div>
                          <div class="natureC left">
                       <input type="radio"
                            name="continuousIntermittence" id="c" value="water"> <label
                            for="c" class="span_click" value="${baseInformation.continuousIntermittence}">连续</label> <span
                            class="blue span_click"></span> 
                            <input type="radio"
                            name="continuousIntermittence" id="d" checked='checked'
                            value="${baseInformation.continuousIntermittence}"> <label for="d" class="span_click">间歇</label>
                    </div>
                    </div>
                            
                    </div>
                    <div class="contentdiv">
                        <div class="content_menu menu_long">
                            <span></span>生产月份
                        </div>
                        <div class="content_inputdiv">
                            <input class="content_input" name="productionMonth"
                                value="${baseInformation.productionMonth}" />
                        </div>

                    </div>
                    <div class="contentdiv">
                        <div class="content_menu menu_long">
                            <span></span>投产年份
                        </div>
                        <div class="content_inputdiv">
                            <input class="content_input" name="putProductionYear"
                                value="${baseInformation.putProductionYear}" />
                        </div>

                    </div>
                    <div class="contentdiv">
                        <div class="content_menu menu_long">
                            <span></span>总量核查是否覆盖
                        </div>
                          <div class="natureC left">
                       <input type="radio"
                            name="grossExamineCoverage" id="e" value="water"> <label
                            for="e" class="span_click" value="${baseInformation.grossExamineCoverage}">覆盖</label> <span
                            class="blue span_click"></span> 
                            <input type="radio"
                            name="grossExamineCoverage" id="f" checked='checked'
                            value="${baseInformation.grossExamineCoverage}"> <label for="f" class="span_click">不覆盖</label>
                    </div>
                    </div>
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu menu_long">
                                <span></span>SO2控制限制值
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="so2ControlLimitationValue"
                                    value="${baseInformation.so2ControlLimitationValue}" />
                            </div>
                        </div>
                        <div class="content_top_right">
                            <div class="content_menu menu_long">
                                <span></span>SO2核查排放量
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="so2ExamineDischargeValue"
                                    value="${baseInformation.so2ExamineDischargeValue}" />
                            </div>
                        </div>
                    </div>
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu menu_long">
                                <span></span>NOx控制限制值
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="noxControlLimitationValue"
                                    value="${baseInformation.noxControlLimitationValue}" />
                            </div>
                        </div>
                        <div class="content_top_right">
                            <div class="content_menu menu_long">
                                <span></span>NOx核查排放量
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="noxExamineDischargeValue"
                                    value="${baseInformation.noxExamineDischargeValue}" />
                            </div>
                        </div>
                    </div>
                    
                    <div class="contentdiv">
                        <div class="content_menu menu_long">
                            <span></span>环统是否覆盖
                        </div>
                        <div class="natureC left">
                       <input type="radio"
                            name="statisticsCoverage" id="g" value="water"> <label
                            for="g" class="span_click" value="${baseInformation.statisticsCoverage}">覆盖</label> <span
                            class="blue span_click"></span> 
                            <input type="radio"
                            name="statisticsCoverage" id="h" checked='checked'
                            value="${baseInformation.statisticsCoverage}"> <label for="h" class="span_click">不覆盖</label>
                    </div>
                    
                    </div>
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu menu_long">
                                <span></span>SO2(环统信息)
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="so2Statistics"
                                    value="${baseInformation.so2Statistics}" />
                            </div>
                        </div>
                        <div class="content_top_right">
                            <div class="content_menu menu_long">
                                <span></span>NOx(环统信息)
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="noxStatistics"
                                    value="${baseInformation.noxStatistics}" />
                            </div>
                        </div>
                    </div>
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu menu_long">
                                <span></span>烟粉尘(环统信息)
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="smokeStive"
                                    value="${baseInformation.smokeStive}" />
                            </div>
                        </div>
                        <div class="content_top_right">
                            <div class="content_menu menu_long">
                                <span></span>VOC(环统信息)
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="vocStatistics"
                                    value="${baseInformation.vocStatistics}" />
                            </div>
                        </div>
                    </div>
                    <div class="contentdiv">
                        <div class="content_top_left">
                            <div class="content_menu menu_long">
                                <span></span>污水排放量（吨）
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="sewageDischargeValue"
                                    value="${baseInformation.sewageDischargeValue}" />
                            </div>
                        </div>
                        <div class="content_top_right">
                            <div class="content_menu menu_long">
                                <span></span>废水自处理率（%）
                            </div>
                            <div class="content_inputdiv">
                                <input class="content_input" name="effluentDisposeRate"
                                    value="${baseInformation.effluentDisposeRate}" />
                            </div>
                        </div>
                    </div>
                    <div class="contentdiv">
                            <div class="content_menu menu_long">
                                <span></span>污水处理池是否加盖
                            </div>
                              <div class="natureC left">
                       <input type="radio"
                            name="sewageCap" id="i" value="water"> <label
                            for="i" class="span_click" value="${baseInformation.sewageCap}">加盖</label> <span
                            class="blue span_click"></span> 
                            <input type="radio"
                            name="sewageCap" id="m" checked='checked'
                            value="${baseInformation.sewageCap}"> <label for="m" class="span_click">不加盖</label>
                    </div>
                    </div>
                </div>
                <input type="hidden" name="sectionStatus"
                    value="${baseInformation.sectionStatus}"> <input
                    type="hidden" name="oilTankStatus"
                    value="${baseInformation.oilTankStatus}"> <input
                    type="hidden" name="monthlyStatus"
                    value="${baseInformation.monthlyStatus}"> <input
                    type="hidden" name="bovernanceMeasuresStatus"
                    value="${baseInformation.bovernanceMeasuresStatus}"> <input
                    type="hidden" name="exhaustionHoleStatus"
                    value="${baseInformation.exhaustionHoleStatus}"> <input
                    type="hidden" name="boilerStatus"
                    value="${baseInformation.boilerStatus}"> <input
                    type="hidden" name="exhaustionHoleStatus"
                    value="${baseInformation.exhaustionHoleStatus}">

                <div class="content_footer">
                    <div class="content_oneline_left">备注</div>
                    <div class="content_oneline_right">
                        <textarea class="content_input input_big" name="description">${baseInformation.description}</textarea>
                    </div>
                </div>
            </form>
        </div>
        <c:if test="${principal.userType != 'system'}">
            <div class="buttonF">
                <a class="saveBtn" id="confirm">保存</a>
            </div>
        </c:if>
    </div>
</body>
</html>