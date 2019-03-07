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
<title>增加工段</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","panel"],function(a){
    var select3 = $("#governanceMeasures1","#section_form").select({name:"governanceMeasures1",zIndex:3,param:"normal"});
    var select3 = $("#governanceMeasures2","#section_form").select({name:"governanceMeasures2",zIndex:2,param:"normal"});
    var select3 = $("#governanceMeasures3","#section_form").select({name:"governanceMeasures3",zIndex:1,param:"normal"});
    var select3 = $("#governanceMeasures4","#section_form").select({name:"governanceMeasures4",zIndex:1,param:"normal"});
    var select4 = $("#exhaustionHoleId","#section_form").select({name:"exhaustionHoleId"});
        $('#commit').click(function(){
            $("#section_form").submit();
        })
        //提交表单
        $("#section_form").validate({
            rules: {
            },
            messages:{
            },
            submitHandler:function(form){
                if (!isSubmit($("input[name ='rawMaterials1.name']")
                        .val(), $(
                        "input[name ='rawMaterials1.consumption']")
                        .val(), $("input[name ='rawMaterials1.unit']")
                        .val())){qy.suc3({title:"辅料1必须填写3个参数！"});return false}
                if (!isSubmit($("input[name ='rawMaterials2.name']")
                        .val(), $(
                        "input[name ='rawMaterials2.consumption']")
                        .val(), $("input[name ='rawMaterials2.unit']")
                        .val())){qy.suc3({title:"辅料2必须填写3个参数！"});return false}
                if (!isSubmit($("input[name ='rawMaterials3.name']")
                        .val(), $(
                        "input[name ='rawMaterials3.consumption']")
                        .val(), $("input[name ='rawMaterials3.unit']")
                        .val())){qy.suc3({title:"辅料3必须填写3个参数！"});return false}
                if (!isSubmit($("input[name ='rawMaterials4.name']")
                        .val(), $(
                        "input[name ='rawMaterials4.consumption']")
                        .val(), $("input[name ='rawMaterials4.unit']")
                        .val())){qy.suc3({title:"辅料4必须填写3个参数！"});return false}
                if (!isSubmit($("input[name ='rawMaterials5.name']")
                        .val(), $(
                        "input[name ='rawMaterials5.consumption']")
                        .val(), $("input[name ='rawMaterials5.unit']")
                        .val())){qy.suc3({title:"辅料5必须填写3个参数！"});return false}
                if (!isSubmit($("input[name ='product.name']")
                        .val(), $(
                        "input[name ='product.yield']")
                        .val(), $("input[name ='product.unit']")
                        .val())){qy.suc3({title:"产品必须填写3个参数！"});return false}
                var options  = {
                    url:base+'/web/section/simplesave.jhtml',
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
        function isSubmit(str1, str2, str3) {
            if (str1 && str2 && str3) {
                return true;
            }
            if (!str1 && !str2 && !str3) {
                return true
            }
            return false;
        }
    });
</script>

<body>
    <form id="section_form">
        <div class="dialog_div">
            <div class="dialog_title_left">工段名称</div>
            <div class="dialog_title_right">
                <input class="dialog_input" name="name" placeholder="">
            </div>
            <div class="dialog_title_left" >产品信息</div>
            <div class="dialog_title_right">
                <input class="dialog_inputlong" name="product.name" placeholder="产品名称"/> 
                <input class="dialog_input" name="product.yield" placeholder="年产量" number=true /> 
                <input class="dialog_inputshort" name="product.unit" placeholder="单位" />
            </div>
            <div class="dialog_title_left">原料信息</div>
            <div class="dialog_title_right">
                <div class="left">
                    <input class="dialog_input" name="rawMaterials1.name"
                        placeholder="原料名称"> <input class="dialog_inputmid"
                        name="rawMaterials1.consumption" placeholder="年消耗量" number=true>
                    <input class="dialog_inputshort" name="rawMaterials1.unit"
                        placeholder="单位"> 
                       <!--  <input type="radio"
                        name="rawMaterials1.rawMaterialsType" id="a" value="organic">
                    <label for="a" class="span_click">是</label> <span
                        class="blue span_click">|</span> <input type="radio"
                        name="rawMaterials1.rawMaterialsType" id="b" checked='checked'
                        value="inorganic"> <label for="b" class="span_click">否</label>
                    <span>含有机溶剂</span> -->
                    <div class="natureC left">
                        <span style="margin-left: 5px;">性质:</span> <input type="radio"
                            name="rawMaterials1.solventType" id="c" value="water"> <label
                            for="c" class="span_click">水性</label> <span
                            class="blue span_click">|</span> 
                            <input type="radio"
                            name="rawMaterials1.solventType" id="d" checked='checked'
                            value="oil"> <label for="d" class="span_click">油性</label>
                    </div>
                </div>
                <div class="left">
                    <input class="dialog_input" name="rawMaterials2.name"
                        placeholder="原料名称"> <input class="dialog_inputmid"
                        name="rawMaterials2.consumption" placeholder="年消耗量" number=true>
                    <input class="dialog_inputshort" name="rawMaterials2.unit"
                        placeholder="单位"> 
                      <!--   <input type="radio"
                        name="rawMaterials2.rawMaterialsType" id="e" value="organic">
                    <label for="e" class="span_click">是</label> <span
                        class="blue span_click">|</span> <input type="radio"
                        name="rawMaterials2.rawMaterialsType" id="f" checked='checked'
                        value="inorganic"> <label for="f" class="span_click">否</label>
                    <span>含有机溶剂</span> -->
                    <div class="natureC left">
                        <span style="margin-left: 5px;">性质:</span> <input type="radio"
                            name="rawMaterials2.solventType" id="g" value="water"> <label
                            for="g" class="span_click">水性</label> <span
                            class="blue span_click">|</span> <input type="radio"
                            name="rawMaterials2.solventType" id="h" checked='checked'
                            value="oil"> <label for="h" class="span_click">油性</label>
                    </div>
                </div>
                <div class="left">
                    <input class="dialog_input" name="rawMaterials3.name"
                        placeholder="原料名称"> <input class="dialog_inputmid"
                        name="rawMaterials3.consumption" placeholder="年消耗量" number=true>
                    <input class="dialog_inputshort" name="rawMaterials3.unit"
                        placeholder="单位"> 
                       <!--  <input type="radio"
                        name="rawMaterials3.rawMaterialsType" id="i" value="organic">
                    <label for="i" class="span_click">是</label> <span
                        class="blue span_click">|</span> <input type="radio"
                        name="rawMaterials3.rawMaterialsType" id="j" checked='checked'
                        value="inorganic"> <label for="j" class="span_click">否</label>
                    <span>含有机溶剂</span> -->
                    <div class="natureC left">
                        <span style="margin-left: 5px;">性质:</span> <input type="radio"
                            name="rawMaterials3.solventType" id="k" value="water"> <label
                            for="k" class="span_click">水性</label> <span
                            class="blue span_click">|</span> <input type="radio"
                            name="rawMaterials3.solventType" id="l" checked='checked'
                            value="oil"> <label for="l" class="span_click">油性</label>
                    </div>
                </div>
                <div class="left">
                    <input class="dialog_input" name="rawMaterials4.name"
                        placeholder="原料名称"> <input class="dialog_inputmid"
                        name="rawMaterials4.consumption" placeholder="年消耗量" number=true>
                    <input class="dialog_inputshort" name="rawMaterials4.unit"
                        placeholder="单位"> 
                      <!--   <input type="radio"
                        name="rawMaterials4.rawMaterialsType" id="m" value="organic">
                    <label for="m" class="span_click">是</label> <span
                        class="blue span_click">|</span> <input type="radio"
                        name="rawMaterials4.rawMaterialsType" id="n" checked='checked'
                        value="inorganic"> <label for="n" class="span_click">否</label>
                    <span>含有机溶剂</span> -->
                    <div class="natureC left">
                        <span style="margin-left: 5px;">性质:</span> <input type="radio"
                            name="rawMaterials4.solventType" id="o" value="water"> <label
                            for="o" class="span_click">水性</label> <span
                            class="blue span_click">|</span> <input type="radio"
                            name="rawMaterials4.solventType" id="p" checked='checked'
                            value="oil"> <label for="p" class="span_click">油性</label>
                    </div>
                </div>
                <div class="left">
                    <input class="dialog_input" name="rawMaterials5.name"
                        placeholder="原料名称"> <input class="dialog_inputmid"
                        name="rawMaterials5.consumption" placeholder="年消耗量" number=true>
                    <input class="dialog_inputshort" name="rawMaterials5.unit"
                        placeholder="单位"> 
                       <!-- <input type="radio"
                        name="rawMaterials5.rawMaterialsType" id="q" value="organic">
                     <label for="q" class="span_click">是</label> <span
                        class="blue span_click">|</span> <input type="radio"
                        name="rawMaterials5.rawMaterialsType" id="r" checked='checked'
                        value="inorganic"> <label for="r" class="span_click">否</label>
                    <span>含有机溶剂</span> -->
                    <div class="natureC left">
                        <span style="margin-left: 5px;">性质:</span> <input type="radio"
                            name="rawMaterials5.solventType" id="s" value="water"> <label
                            for="s" class="span_click">水性</label> <span
                            class="blue span_click">|</span> <input type="radio"
                            name="rawMaterials5.solventType" id="t" checked='checked'
                            value="oil"> <label for="t" class="span_click">油性</label>
                    </div>
                </div>
            </div>
            <div class="dialog_title_left" style="margin-top: 145px">燃料信息</div>
            <div class="dialog_title_right">
                <input class="dialog_inputlong" name="fuelType" placeholder="燃料类型" />
                <input class="dialog_input" name="fuelConsumption" placeholder="燃料消耗量"
                    number=true /> 
                <input class="dialog_inputshort" name="fuelUnit"
                    placeholder="单位" />
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left title_long">脱硫工艺</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input " id="governanceMeasures1"
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
                <div class="dialog_title_left title_long">VOCs措施</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input" id="governanceMeasures4"
                        data-code="污染治理措施"></div>
                </div>
            </div>
            <div class="dialog_title_left">排放口高度(米)</div>
            <div class="dialog_title_right">
                <input class="dialog_input" name="enterpriseEmissionsManagement.exhaustionHole.height" placeholder="" number=true>
            </div>
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