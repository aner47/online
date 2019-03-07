<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加项目模板</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","select"],function(a){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		var templatesType = $("#inputForm #templatesType").select({filter:true,name:"templatesType",zIndex:10,change:function(event, value){
			/* if(templateType.getSelectValue()){
				
			}else{
				
			} */
			nameId.loadData("templatesType:"+value+",templateType:"+templateType.getSelectValue()+",projectId:"+readyprojectId.getSelectValue());
		}});
		
		var templateType = $("#inputForm #templateType").select({filter:true,name:"templateType",zIndex:9,change:function(event, value){
			nameId.loadData("templateType:"+value+",templatesType:"+templatesType.getSelectValue()+",projectId:"+readyprojectId.getSelectValue());
		}});
		var projectId = $("#inputForm #projectId").select({filter:true,name:"projectId",zIndex:8,change:function(event, value){
	    }});
		var readyprojectId = $("#inputForm #readyprojectId").select({filter:true,name:"readyprojectId",zIndex:7,change:function(event, value){
			//nameId.loadData("templateType:"+templateType.getSelectValue()+",templatesType:"+templatesType.getSelectValue()+",projectId:"+value);
			nameId.loadData("templateType:"+templateType.getSelectValue()+",projectId:"+value);
		}});
		var nameId = $("#inputForm #nameId").select({filter:true,name:"nameId",zIndex:6,change:function(event, value){
	    }});

		//提交表单
		$("#inputForm").validate({
			rules: {
				templateType:{
					required:true
				},
				templatesType:{
					required:true
				},
				projectId:{
					required:true
				}
			},
			messages:{
			},
			submitHandler:function(form){
				console.log($("[name=model]:checked").val());
				if($("[name=model]:checked").val()=='是'){
					if(!nameId.getSelectValue()){
						layer.msg('请选择套用模板',{icon:3});
						return false;
					}
				}
				var load = top.layer.load(2);
				 var options  = {
			        url:'save.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.loadData();
						top.layer.close(load);
			        },
			        error:function(){
			        	layer.msg('添加失败',{icon:3});
			        	top.layer.close(load);
			        }
			    };
				$(form).ajaxSubmit(options);
				return false; 
			}
		});	
		
		$("[name=model]").on('change',function(){
			if ($(this).val()=='是') {
				$("#useModuleDiv").show();
			}else{
				$("#useModuleDiv").hide();
			};
		})
		
		
	});
</script>

<body>

<form id="inputForm">
    <div class="container">
            <div class="form-line">
                <label for="name">模块名称 ：</label> <input type="text" id="name"
                    name="name" placeholder="模块名称" required />

            </div>
            <div class="form-line">
                <label for="formType">配置类型：</label> 
               <div data-code="导出【模板类型】" id="templatesType" class=" selectF"></div>

            </div>
            <div class="form-line">
                <label for="formType">填表类型：</label> 
               <div data-code="企业【企业类型】" id="templateType" class=" selectF"></div>

            </div>
              <div class="form-line">
                <label for=""projectId"">项目 ：</label> 
               <div data-code="全局【项目】" id="projectId" class=" selectF"></div>

            </div>
            <div class="form-line">
                <label for="">是否套用其他项目模板：</label>
                <div class="radio-block">
                    <input type="radio" class="radio-block-radio" id="cheD1" name="model" value="是"/>
                    <label for="cheD1" class="radio-block-label">是</label>
                    <input type="radio" class="radio-block-radio" id="cheD2" name="model" value="否" checked="checked"/>
                    <label for="cheD2" class="radio-block-label">否</label>
                </div>
			</div>
			<div class="form-line" id="useModuleDiv" style="display: none;">
				<label for=""projectId"">项目 ：</label> 
               <div data-code="全局【项目】" id="readyprojectId" class=" selectF"></div>
                <label for="name">模块名称 ：</label>
                 <div data-code="模板管理【模板名称】" id="nameId" class=" selectF"></div>
            </div>
        </div>

</form>
</body>
</html>