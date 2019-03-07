<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改产品名称配置</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
        $('#commit').click(function(){
            $("#inputForm").submit();
        })
        var select = $('#productStandardName ',"#inputForm").select({name:"pds",empty:true ,filter:true,'defaultValue':'${productOtherName.productStandardName.id}',zIndex:2});
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
     <input type="hidden" id="id" name="id" value="${productOtherName.id}"/>
 <div class="form-line">
    <label for="otherName" >其他名称</label>
    <input type="text" id="otherName" name="otherName"  value="${productOtherName.otherName}"/>
</div>
    <div class="form-line">
                <label for="productStandardName">标准名称</label>
                <div id="productStandardName"  name="productStandardName" class="selectF"    data-code="产品标准名称" ></div>
            </div>
    <div class="form-line">
    <label for="industryCode" >行业代码</label>
    <input type="text" id="industryCode" name="industryCode"  value="${productOtherName.industryCode}"/>
</div>

    </div>

</form>
</body>
</html>