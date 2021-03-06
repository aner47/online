<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加原辅料配置</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
        $('#commit').click(function(){
            $("#inputForm").submit();
        })
        var select = $('#rawMaterialsStandardName ',"#inputForm").select({name:"pds",empty:true ,filter:true,zIndex:2});
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
                <label for="otherName" >原辅料名称 </label>
                                 <input type="text" id="otherName" name="otherName"  required/>
                               
            </div>
                <div class="form-line">
                <label for="rawMaterialsStandardName">标准名称</label>
                <div id="rawMaterialsStandardName"  name="rawMaterialsStandardName" class="selectF"   data-code="原辅料标准名称" ></div>
            </div>              
                                <div class="form-line">
                <label for="industryCode" >行业代码 </label>
                                 <input type="number" id="industryCode" name="industryCode" min=0 style="width:100%;height:41px" required/>
                               
            </div>                                  

    </div>

</form>
</body>
</html>