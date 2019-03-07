<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改产品单位配置</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform"],function(a){
    $('#productOtherName',"#inputForm").select({'name':"po",'defaultValue':'${productUnitConfig.productOtherName.id  }',empty:true ,filter:true,zIndex:1});     
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
     <input type="hidden" id="id" name="id" value="${productUnitConfig.id}"/>
 <div class="form-line">
                <label for="productOtherName">产品名称</label> 
                <div class="selectF" id="productOtherName" name="productOtherName"  data-code="产品名称配置" ></div>
            </div>
            <div class="form-line">
                <label for="nameEn">计量单位英文名称 </label> <input type="text" id="nameEn"
                    name="nameEn" value="${productUnitConfig.nameEn }"  onkeyup="this.value=this.value.replace(/[^a-zA-Z]+/g, '');" />

            </div>
            <div class="form-line">
                <label for="nameCh">计量单位中文名称</label> <input type="text" id="nameCh"
                    name="nameCh" value="${productUnitConfig.nameCh }"  />

            </div>
            <div class="form-line">
                <label for="conversionFactor">转换系数 </label> <input type="number" id="conversionFactor"
                    name="conversionFactor" value="${productUnitConfig.conversionFactor }" style="width:100%;height:41px" required/>

            </div>  
 

    </div>

</form>
</body>
</html>