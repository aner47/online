<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加产品名称配置</title>
    <style type="text/css">
    .layui-layer-page .layui-layer-content{
    overflow: visible;
    }
    </style>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
        $('#commit').click(function(){
            $("#inputForm").submit();
        })
        var select = $('#productStandardName ',"#inputForm").select({name:"pds",empty:true ,filter:true,zIndex:2});
        //提交表单
        $("#inputForm").validate({
            rules: {
            },
            messages:{
            },
            submitHandler:function(form){
                var options  = {
                    url:'save.jhtml',
                    type:'post',
                    dataType : 'json',
                    success:function(data){
                        layer.msg('添加成功！', {icon: 6})
                        layer.closeAll('page');
                        grid1.loadData();
                    },
                    error:function(){
                        layer.msg('添加失败',{icon:3});
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
<div class="form-line">
<label for="otherName" >产品名称</label>
<input type="text" id="otherName" name="otherName"  required/>
</div>
<div class="form-line">
<label for="productStandardName">标准名称</label>
                <div id="productStandardName"  name="productStandardName" class="selectF"   data-code="产品标准名称" ></div>
<!-- <input type="text" id="productStandardName" name="productStandardName"   required/> -->
</div>
<div class="form-line">
<label for="industryCode" >行业代码</label>
<input type="text" id="industryCode" name="industryCode"  number=true required/>
                               
</div>
            
</div>

</form>
</body>
</html>