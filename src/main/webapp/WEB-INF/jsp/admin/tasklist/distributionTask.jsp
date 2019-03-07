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
	 $("#disposeAccount","#inputForm").select({name:"disposeAccount",param:"每日任务处理员"});
        $('#commit').click(function(){
            $("#inputForm").submit();
        })
        //提交表单
        $("#inputForm").validate({
            rules: {
            	disposeAccount:'required'
            },
            messages:{
            },
            submitHandler:function(form){
            	var ids = grid1.getSelectedValue('id');
                var options  = {
                    url:'distribution.jhtml',
                    data :{"ids":ids.join(',')},
                    type:'post',
                    dataType : 'json',
                    success:function(data){
                        layer.msg('分配成功！', {icon: 6})
                        layer.closeAll('page');
                        grid1.loadData();
                    },
                    error:function(){
                        layer.msg('分配失败',{icon:3});
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
				<label for="disposeAccount">指派 给</label> 
				<div id="disposeAccount" name="disposeAccount"  data-code="每日任务清单【人员指派】"></div>
			</div>
		</div>

</form>
</body>
</html>