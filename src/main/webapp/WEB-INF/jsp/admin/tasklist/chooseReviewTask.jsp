<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
     $("#status","#inputForm").select({name:"status",filter:true});
        $('#commit').click(function(){
            $("#inputForm").submit();
        })
        //提交表单
        $("#inputForm").validate({
            rules: {
            	status:'required'
            },
            messages:{
            },
            submitHandler:function(form){
                var ids = grid1.getSelectedValue('id');
                var options  = {
                    url:'auditOpinion.jhtml',
                    data :{"ids":ids.join(',')},
                    type:'post',
                    dataType : 'json',
                    success:function(data){
                        layer.msg('审核成功！', {icon: 6})
                        layer.closeAll('page');
                        grid1.loadData();
                    },
                    error:function(){
                        layer.msg('审核失败',{icon:3});
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
                <label for="status">审核意见</label> 
                <div id="status" name="status"  data-code="每日任务清单【审核意见】"></div>
            </div>
              <div class="form-line">
                <label for="description">描述</label> 
                <textarea type="text" id="description" name="description" placeholder="请简短描述一下审核建议！">${taskList.description}</textarea>
            </div>
        </div>

</form>
</body>
</html>