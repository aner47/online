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
<title>增加石油产品储罐</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select","panel"],function(a){
    var select1 = $("#select1").select({name:"tankMaterialType",afterInit:function(){alert(select.getSelectValue());}});
    var select2 = $("#select2").select({name:"tankType",afterInit:function(){alert(select.getSelectValue());}});
        $('#commit').click(function(){
            $("#oiltank_form").submit();
        })
        //提交表单
        $("#oiltank_form").validate({
            rules: {
                workDays: {
                    range:[0,365],
                },
                storageCycle: {
                    range:[0,365],
                }
            },
            messages:{
                workDays: {
                range:"0~365!"
                },
                storageCycle: {
                    range:"0~365!"
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
    <form id="oiltank_form">
        <div class="dialog_div">
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">材料类型</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input" id="select1" data-code="储罐【材料】"></div>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">储罐类型</div>
                <div class="dialog_title_right">
                    <div class="select dialog_input" id="select2" data-code="储罐【类型】"></div>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">高度/长度</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="height" placeholder=""
                        number=true> <span>(米)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">平均直径</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="avgDiameter" placeholder=""
                        number=true>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">储罐容量</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="capacity" placeholder=""
                        number=true> <span>(立方米)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">全年使用天数</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="workDays" placeholder=""
                        number=true> <span>(天)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">储罐年总储量</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="storageCapacity" placeholder=""
                        number=true> <span>(吨)</span>
                </div>
            </div>
            <div class="dialog_twoinfo">
                <div class="dialog_title_left">储存周期</div>
                <div class="dialog_title_right">
                    <input class="dialog_input" name="storageCycle" placeholder=""
                        number=true> <span>(天)</span>
                </div>
            </div>
            <div class="dialog_title_left title_long">年装卸/填充次数</div>
            <div class="dialog_title_right">
                <input class="dialog_input" name="fillTimes" placeholder=""
                    number=true>
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