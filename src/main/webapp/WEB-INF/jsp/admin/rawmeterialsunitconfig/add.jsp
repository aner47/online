<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加原辅料单位配置</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
    $('#rawMaterialsOtherName',"#inputForm").select({'name':"po",empty:true ,filter:true,zIndex:1});    
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
                <label for="rawMaterialsOtherName">原辅料名称</label> 
                <div class="selectF" id="rawMaterialsOtherName" name="rawMaterialsOtherName"  data-code="原辅料名称"></div>
            </div>
            <div class="form-line">
                <label for="nameEn">计量单位英文名称 </label> 
                <input type="text" id="nameEn"    name="nameEn"  onkeyup="this.value=this.value.replace(/[^a-zA-Z]+/g, '');" />

            </div>
            <div class="form-line">
                <label for="nameCh">计量单位中文名称</label> 
                <input type="text" id="nameCh"  name="nameCh"  />

            </div>
            <div class="form-line">
                <label for="conversionFactor">转换系数 </label> 
                <input type="number" id="conversionFactor" name="conversionFactor"  required />

            </div>  
                

    </div>

</form>
</body>
</html>