<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加溶剂</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css">
<style>
	label.error{
		position:absolute;
		right:10px;
		line-height:31px;
	}
</style>
</head>
<script type="text/javascript">
require(["hide","validate","ajaxform"],function(hide){
		$('#commit').click(function(){
			$("#inputForm").submit();
		})
		$("#year","#inputForm").select({name:"year",zIndex:10});
		
		$("#solventCategory","#inputForm").select({name:"solventCategory",zIndex:9,selected:true})
		$("#sectionId","#inputForm").select({name:"sectionId",zIndex:7,selected:true})
		$("#solventType","#inputForm").select({name:"solventType",zIndex:8});
		//提交表单
		$("#inputForm").validate({
			rules: {
				year:{
					required:true
				},
				solventType:{
					required:true
				},
				name:{
					required:true
				},
				vocRate:{
					range:[0,100]
				},
				vocVolatility:{
					range:[0,100]
				},
				consumption:{
					min:0,
					required:true
				},
				unit:{
					required:true,
					checkUnit:/\D/
				},
				solventCategory:{
					required:true
				},
				sectionId:{
					required:true
				},
				description:{
					maxlength:255
				},
				
			},
			messages:{
				vocRate:{
					range:"0~100!"
				},
				vocVolatility:{
					range:"0~100!"
				},
				consumption:{
					min:"大于等于0"
				},
				description:{
					maxlength:"最多255字"
				}
			}, 
			submitHandler:function(form){
				var options  = {
			        url:'savesimple.jhtml',
			        type:'post',
			        dataType : 'json',
			        success:function(data){
						layer.msg('添加成功！', {icon: 6})
						layer.closeAll('page');
						grid1.refresh();
			        },
			        error:function(){
			        	layer.msg('添加失败',{icon:3});
			        }
			    };
				$(form).ajaxSubmit(options);
				return false;
			}
		});	
		hide.hide();
	});
</script>

<body>
	<form id="inputForm" style="padding:0;width:100%;">
		
		<div class="infLine">

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年份</label>
					<div class="infpCon">
						<div id="year" data-code="工段【年份】" class="selF"></div>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂类型</label>
					<div class="infpCon">
						<div class="selF" id="solventCategory" data-code="原辅料【溶剂类型】"></div>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂名称</label>
					<div class="infpCon">
						<input type="" class="input" id="name" name="name" placeholder="溶剂名称">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">溶剂性质</label>
					<div class="selF" id="solventType" data-code="简表【溶剂性质】"></div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs含量（%）</label>
					<div class="infpCon">
						<input type="" class="input"  id="vocRate" name="vocRate" placeholder="VOCs含量（%）" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">VOCs挥发度（%）</label>
					<div class="infpCon">
						<input type="" class="input" id="vocVolatility" name="vocVolatility" placeholder="VOCs挥发度（%）" number=true>
					</div>
				</div>
			</div>
			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">年/月消耗量</label>
					<div class="infpCon">
						<input type="" class="input" id="consumption" name="consumption" placeholder="年消耗量" number=true>
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">单位</label>
					<div class="infpCon">
						<input type="" class="input" id="unit" name="unit" placeholder="单位">
					</div>
				</div>
			</div>

			<div class="infPara">
				<div class="infParaCon">
					<label for="" class="infpLable">工段</label>
					<div class="infpCon">
						<div class="selF" id="sectionId" name="sectionId" data-code="简单表单【工段】"></div>
					</div>
				</div>
			</div>

			<div class="infPara infPara2">
				<div class="infParaCon">
					<label for="" class="infpLable">备注</label>
					<div class="infpCon">
						<textarea class="textarea" name="description"></textarea>
					</div>
				</div>
			</div>

		</div>
	</form>
	<input type="hidden"  id="fieldHide" value="${fieldShowConfig}" />
</body>
</html>