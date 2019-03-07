<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>增加调查车辆</title>
</head>

<script type="text/javascript">
require(["validate","ajaxform","date"],function(a){
	
		$("#buyDate", "#inputForm").jeDate({
	        format:"YYYY-MM-DD",
	        isTime:false, 
	        zIndex:999999900
	    })
    	$("#fuelType", "#inputForm").select({
			name : "fuelType",
			zIndex : 10
		});
    	$("#emissionStandard", "#inputForm").select({
			name : "emissionStandard",
			zIndex : 9
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
			        url:'save.jhtml',
			        type:'post',
			        data:{motor_vehicles:"${motor_vehicles}"},
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
				<label for="sealedPoint">车辆代码/品牌型号</label> <input type="text"
					id="carModel" name="carModel" placeholder="品牌型号" />

			</div>
			<div class="form-line">
				<label for="leakedSealedPoint">购买日期</label> <input type="text"
					class="input" id="buyDate" name="buyDate" placeholder="调查日期">
			</div>
			<div class="form-line">
				<label for="fixedSealedPoint">车辆类型</label> <input type="text"
					id="carType" name="carType" placeholder="车辆类型" />
			</div>
			<div class="form-line">
				<label for="fixedSealedPoint">总质量（吨）</label> <input type="text"
					id="weight" name="weight" placeholder="总质量（吨）" />
			</div>
			<div class="form-line">
				<label for="fixedSealedPoint">燃料类型</label> 
				<div  data-code="机动车调查【燃料类型】" id="fuelType" class="selF"></div>
			</div>
			<div class="form-line">
				<label for="fixedSealedPoint">排放标准</label> 
				<div  data-code="机动车调查【排放标准】" id="emissionStandard" class="selF"></div>
			</div>
			<div class="form-line">
				<label for="fixedSealedPoint">累积行驶里程（公里）</label> <input type="text"
					id="mileage" name="mileage" placeholder="累积行驶里程（公里）" />
			</div>
		</div>
	</form>
</body>
</html>