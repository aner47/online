<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改行业分类</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
	 var select1 = $("#categoryLevel", "#inputForm").select({
         name : "categoryLevel",
         empty: true,
         defaultValue:'${category.categoryLevel}',
     });
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
	 <input type="hidden" id="id" name="id" value="${category.id}"/>
 <div class="form-line">
                <label for="name">行业名称</label>
                 <input type="text" id="name"  name="name" value="${category.name}" required/>

            </div>
            <div class="form-line">
                <label for="code">行业代码</label> 
                <input type="number" id="code" name="code" value="${category.code}" min=0 style="width:100%;height: 41px" required/>

            </div>
            <div class="form-line">
                <label for="parent">上级分类</label> 
                <input type="number" id="parent"  name="parent" value="${category.parent}" min=0 style="width:100%;height: 41px" required/>

            </div>
            <div class="form-line">
                <label for="coalPscc">燃料煤污染源分类</label>
                 <input type="number"  id="coalPscc" name="coalPscc" value="${category.coalPscc}" min=0 style="width:100%;height: 41px"/>
            </div>
             <div class="form-line">
                <label for="oilPscc">燃料油污染源分类</label>
                 <input type="number"  id="oilPscc" name="oilPscc" value="${category.oilPscc}" min=0 style="width:100%;height: 41px"/>
            </div>
             <div class="form-line">
                <label for="naturalGasPscc">天然气污染源分类</label>
                 <input type="number"  id="naturalGasPscc" name="naturalGasPscc" min=0 value="${category.naturalGasPscc}" style="width:100%;height: 41px"/>
            </div>
             <div class="form-line">
                <label for="cokePscc">焦炭污染源分类</label>
                 <input type="number"  id="cokePscc" name="cokePscc" value="${category.cokePscc}" min=0 style="width:100%;height: 41px"/>
            </div>
             <div class="form-line">
                <label for="categoryLevel">行业级别</label>
                 <div  id="categoryLevel" class="selF" data-code="全局【行业分类级别】"   style="width:100%;height: 41px"/>
            </div>
             <div class="form-line">
                <label for="description">行业描述 </label>
                 <textarea   id="description" name="description"  maxlength="50" placeholder="请简短的描述一下这个行业的特征吧！"  >${category.description}</textarea>
            </div>

    </div>

</form>
</body>
</html>