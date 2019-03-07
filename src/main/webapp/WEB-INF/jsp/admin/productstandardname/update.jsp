<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改产品标准名称配置</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
        $('#commit').click(function(){
            $("#inputForm").submit();
        })
        //提交表单
        $("#inputForm").validate({
            rules: {
            },
            messages:{
            },
            submitHandler:function(form){
                var options  = {
                    url:'update.jhtml',
                    type:'post',
                    dataType : 'json',
                    success:function(data){
                        layer.msg('修改成功！', {icon: 6})
                        layer.closeAll('page');
                        grid1.loadData();
                    },
                    error:function(){
                        layer.msg('修改失败',{icon:3});
                    }
                };
                $(form).ajaxSubmit(options);
                return false;
            }
        });     
    });
</script>

<body>
    <form id="inputForm">
        <div class="container">
            <input type="hidden" id="id" name="id" value="${productStandardName.id}"/>
            <div class="form-line">
                <label for="standardName" >产品标准名称 </label>
                <input type="text" id="standardName" name="standardName" value="${productStandardName.standardName}" required/>
            </div>
            <div class="form-line">
                <label for="industryCode" >行业代码 </label>
                <input type="number" id="industryCode" name="industryCode" min=0 value="${productStandardName.industryCode}" style="width:100%;height:41px" required/>
            </div>
            <div class="form-line">
                <label for="standardUnit" >标准单位 </label>
                <input type="text" id="standardUnit" name="standardUnit" value="${productStandardName.standardUnit}" required />
            </div>
            <div class="form-line">
                <label for="defaultUnit" >常用转换单位 </label>
                <input type="text" id="defaultUnit" name="defaultUnit" value="${productStandardName.defaultUnit}" required />
            </div>
            <div class="form-line">
                <label for="defaultConversionFactor" >常用转换系数 </label>
                <input type="text" id="defaultConversionFactor" name="defaultConversionFactor" value="${productStandardName.defaultConversionFactor}" number=true required/>
            </div>
            <div class="form-line">
                <label for="pscc" >污染源分类代码</label>
                <input type="number" id="pscc" name="pscc" min=0 value="${productStandardName.pscc}" style="width:100%;height:41px" required />
            </div>                                  
        </div>
    </form>
</body>
</html>