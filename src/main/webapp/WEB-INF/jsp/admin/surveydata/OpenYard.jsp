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
<title>增加露天堆场信息</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select","panel"],function(a){
        $('#commit').click(function(){
            $("#openyard_form").submit();
        })
        var materialType = $("#materialType","#openyard_form").select({name:"materialType",zIndex:4});
        var measure1 = $("#measure1","#openyard_form").select({name:"measure1",zIndex:3});
        var measure2 = $("#measure2","#openyard_form").select({name:"measure2",zIndex:2});
        var measure3 = $("#measure3","#openyard_form").select({name:"measure3",zIndex:1});

        //提交表单
        $("#openyard_form").validate({
            rules: {
                moistureContent: {
                    range: [0,100]
                }
            },
            messages:{
                moistureContent: {
                    range: "0~100!"
                }
            },
            submitHandler:function(form){
                var options  = {
                    url:'save.jhtml',
                    type:'post',
                    dataType : 'json',
                    success:function(data){
                        qy.suc2({title:'添加成功！'});
                        grid1.loadData();
                    },
                    error:function(){
                        qy.fail2({title:'添加失败'});
                    }
                };
                $(form).ajaxSubmit(options);
                return false;
            }
        });     
    });
</script>

<body>
    <form id="openyard_form">
        <div class="dialog_div">
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">堆料类型</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input" id="materialType" data-code="堆料类型"></div>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">堆场装卸总量</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="loadAmount" placeholder=""
                        number=true> <span>(吨)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left title_long">每年物料运载车次</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="cargoTrips" placeholder=""
                        number=true> <span>(车)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">每车运载量</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="carryAmount" placeholder=""
                        number=true> <span>(吨/车)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">料堆占地面积</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="area" placeholder="" number=true>
                    <span>(平方米)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">料堆最高高度</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="height" placeholder=""
                        number=true> <span>(米)</span>
                </div>
            </div>
            <div class="dialog_title_left">物料含水率</div>
            <div class="dialog_title_right">
                <input class="dialog_input" name="moistureContent" number=true>
                <span>(%)</span>
            </div>
            <div class="dialog_title_left">扬尘控制措施</div>
            <div class="dialog_title_right">
                <div class="select dialog_input" id="measure1" data-code="控尘措施"></div>
                <div class="select dialog_input" id="measure2" data-code="控尘措施"></div>
                <div class="select dialog_input" id="measure3" data-code="控尘措施"></div>
            </div>
            <!-- <div class="dialog_threeinfo">
                <div class="dialog_title_left title_long">出入车辆是否清洗</div>
                <div class="dialog_title_right ">
                    <input type="radio" name="carCleaning" id="a" value="true">
                    <label for="a" class="span_click">是</label> <span
                        class="blue span_click">|</span> <input type="radio"
                        name="carCleaning" id="b" checked='checked' value="false">
                    <label for="b" class="span_click">否</label>
                </div>
            </div>
            <div class="dialog_threeinfo">
                <div class="dialog_title_left title_long">是否进行破碎/筛选</div>
                <div class="dialog_title_right ">
                    <input type="radio" name="broken" id="e" value="true"> <label
                        for="e" class="span_click">是</label> <span class="blue span_click">|</span>
                    <input type="radio" name="broken" id="f" checked='checked'
                        value="false"> <label for="f" class="span_click">否</label>
                </div>
            </div>
            <div class="dialog_threeinfo">
                <div class="dialog_title_left title_long">破碎环境是否封闭</div>
                <div class="dialog_title_right ">
                    <input type="radio" name="closed" id="i" value="true"> <label
                        for="i" class="span_click">是</label> <span class="blue span_click">|</span>
                    <input type="radio" name="closed" id="j" checked='checked'
                        value="false"> <label for="j" class="span_click">否</label>
                </div>
            </div> -->
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