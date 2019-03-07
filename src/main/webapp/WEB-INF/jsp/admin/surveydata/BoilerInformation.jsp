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
<title>增加锅炉信息</title>
</head>

<script type="text/javascript">
    require(
            [ "validate", "ajaxform", "select", "panel" ],
            function(a) {
                var select1 = $("#boilerType", "#boiler_form").select({
                    name : "boilerType",
                    zIndex : 9,
                    afterInit : function() {
                        alert(select.getSelectValue());
                    }
                });
                var select2 = $("#governanceMeasures1", "#boiler_form").select(
                        {
                            name : "governanceMeasures1",
                            zIndex : 7,
                            param : "normal"
                        });
                var select2 = $("#governanceMeasures2", "#boiler_form").select(
                        {
                            name : "governanceMeasures2",
                            zIndex : 6,
                            param : "normal"
                        });
                var select2 = $("#governanceMeasures3", "#boiler_form").select(
                        {
                            name : "governanceMeasures3",
                            zIndex : 5,
                            param : "normal"
                        });
                var select3 = $("#exhaustionHoleId", "#boiler_form").select({
                    name : "exhaustionHoleId",
                    param : "enterprise:enterprise,project:project"
                });
                var select4 = $("#fuelType", "#boiler_form").select({
                    name : "fuelType",
                    zIndex : 8,
                    afterInit : function() {
                        alert(select.getSelectValue());
                    }
                });
                $('#commit').click(function() {
                    $("#boiler_form").submit();
                })
                //提交表单
                $("#boiler_form")
                        .validate(
                                {
                                    rules : {
                                        fuelSulfurContent : {
                                            range : [ 0, 100 ],
                                        },
                                        fuelAsh : {
                                            range : [ 0, 100 ],
                                        },
                                        fuelVolatiles : {
                                            range : [ 0, 100 ],
                                        },

                                    },
                                    messages : {
                                        fuelSulfurContent : {
                                            range : "0~100!",
                                        },
                                        fuelAsh : {
                                            range : "0~100!",
                                        },
                                        fuelVolatiles : {
                                            range : "0~100!",
                                        },
                                    },
                                    submitHandler : function(form) {
                                        if (!((select4.getSelectValue()
                                                && $(
                                                        "input[name=fuelConsumption]")
                                                        .val() && $(
                                                "input[name=fuelUnit]").val()) || (!select4
                                                .getSelectValue()
                                                && !$(
                                                        "input[name=fuelConsumption]")
                                                        .val() && !$(
                                                "input[name=fuelUnit]").val()))) {
                                            qy.suc3({
                                                title : '燃料信息、年消耗量、单位三项补充完整！'
                                            });
                                            return false;
                                        }
                                        var options = {
                                            url : base
                                                    + '/web/boilerinformation/simplesave.jhtml',
                                            type : 'post',
                                            dataType : 'json',
                                            success : function(data) {
                                                qy.suc2({
                                                    title : '添加成功！'
                                                });
                                                grid1.loadData();
                                                layer.closeAll('page');
                                            },
                                            error : function() {
                                                qy.fail({
                                                    title : '添加失败'
                                                });
                                            }
                                        };
                                        $(form).ajaxSubmit(options);

                                        return false;
                                    }
                                });
            });
</script>

<body>
    <form id="boiler_form">
        <div class="dialog_div">
            <div class="dialog_title_left">锅炉类型</div>
            <div class="dialog_title_right">
                <div class="select dialog_input" id="boilerType" data-code="锅炉类型"></div>
                <input class="dialog_inputcen" name="steamTon" placeholder="锅炉蒸吨"
                    number=true> <span>（蒸吨/小时）</span> 
            </div>
            <div class="dialog_title_left">投运时间:</div>
            <div class="dialog_title_right">
                <input class="dialog_inputdate" name="operation" placeholder="投运时间"
                    type="date"
                    value="<fmt:formatDate value='' pattern='yyyy-MM-dd' />">
                    <div style="margin-left:20px;float:left">关停时间:</div>
                    <input class="dialog_inputdate" name="stopData" 
                    type="date"
                    value="<fmt:formatDate value='' pattern='yyyy-MM-dd' />">
            </div>
            <div class="dialog_title_left">燃料信息</div>
            <div class="dialog_title_right">
                <div class="select dialog_input" id="fuelType" data-code="燃料类型"></div>
                <input class="dialog_input" name="fuelConsumption"
                    placeholder="年消耗总量" number=true> <input
                    class="dialog_inputshort" name="fuelUnit" placeholder="单位">
                <input class="dialog_input" name="fuelSulfurContent"
                    placeholder="燃料含硫率" number=true> <span>（%）</span> <input
                    class="dialog_input" name="fuelAsh" placeholder="燃料灰分" number=true>
                <span>（%）</span> <!-- <input class="dialog_input" name="fuelVolatiles"
                    placeholder="燃料挥发分" number=true> <span>（%）</span> -->
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left title_long">脱硫工艺</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input" id="governanceMeasures1"
                        data-code="污染治理措施"></div>
                </div>
                <div class="dialog_title_left title_long">脱硝工艺</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input" id="governanceMeasures3"
                        data-code="污染治理措施"></div>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left title_long">除尘工艺</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input" id="governanceMeasures2"
                        data-code="污染治理措施"></div>
                </div>
            </div>
            <div style="clear:both"></div>
            <div class="content_footer">
                <div class="dialog_title_left">备注</div>
                <div class="dialog_title_right">
                    <textarea class="content_input input_big" name="description"></textarea>
                </div>
            </div>
        </div>
    </form>
</body>
</html>