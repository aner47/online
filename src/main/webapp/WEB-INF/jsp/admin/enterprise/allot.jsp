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
     $("#checkAccount","#inputForm").select({name:"checkAccount",param:"任务审核员分部"});
        $('#commit').click(function(){
            $("#inputForm").submit();
        })
        //提交表单
        $("#inputForm").validate({
            rules: {
            	checkAccount:'required'
            },
            messages:{
            },
            submitHandler:function(form){
                //var ids = grid1.getSelectedValue('id');
                var ids = '${ids}';
                console.log(ids);
                if(ids.length == 0){
					layer.msg('请选择企业', {icon:3});
					return false;
                }
                var options  = {
                    url:'allot.jhtml',
                    data :{"ids":ids},
                    type:'post',
                    dataType : 'json',
                    success:function(data){
                    	if(data.code == '20000'){
                    		layer.msg('分配成功！', {icon: 6})
                            layer.closeAll('page');
                            grid1.loadData();
                    	}else{
                    		layer.msg('分配失败:'+data.content,{icon:3});
                    		layer.closeAll('page');
                    	}
                        
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
                <label for="checkAccount">指派 给</label> 
                <div id="checkAccount" name="checkAccount"  data-code="每日任务清单【人员指派】"></div>
            </div>
        </div>

</form>
</body>
</html>