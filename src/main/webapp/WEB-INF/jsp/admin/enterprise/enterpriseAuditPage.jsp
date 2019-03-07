<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/block.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/reg/content.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/admin/auditPage/auditPage.css" />
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta charset="UTF-8">
<title>企业审核</title>
</head>
<style>


.ideaj {
	width: 30%;
	height: 150px;
}

.textIdea {
	width: 100%;
	height: 100%;
}

.infParas {
	width: 50%;
	padding: 0 20px;
}

.incImgj {
	width: 500px;
	position: absolute;
	right: 60px;
	top: 100px;
	height: 500px;
}
.modelImg {
	position: absolute;
	top: 0;
	right: 0;
	width: 600px;
	height: 800px;
	padding: 24px 0 0 0;
	background-color: #fff; 
	display: none;
}
.closeImg {
	position: absolute;
	top: 2px;
	right: 4px;
	z-index: 3;
	font-size: 16px;
	font-weight: 900;
	cursor: pointer;
	background-color: #4fc1ee;
	color: #fff;
}
/* 整个渲染表单 */
#inputForm {
	position: relative;
}

/* 提交审核的按钮 */
#examine {
	width: 100%;
	height: 40px;
	position: absolute;
	top: 566px;
	left: 0;
	padding-left: 40%;
	padding-top: 0 !important;
}

#examineH1, #examineH2 {
	width: 100px;
	height: 40px;
	float: left;
	margin-top: 0 !important;
	margin-right: 20px;
	line-height: 40px;
	font-size: 16px;
	font-weight: 100;
	border: 1px solid #2199E2;
	border-radius: 5px;
	text-align: center;
	background-color: #fff;
	cursor: pointer;
}

#examineH1:hover {
	background-color: #2199E2;
	color: #fff;
}

#examineH2:hover {
	background-color: #2199E2;
	color: #fff;
}
.left {
   float: left;
   width: 100%;
   height:300px;
   margin-right: -320px;
 }
 .right {
   width: 300px;
   height: 400px;
   background: url(../../resources/img/examine.png) center center no-repeat;
 }

 .right_p {
 	cursor: pointer;
 	color: #4fc1ee;
 }
 .infPara_1 {
	position: relative;
 }
.photoImgSmallWrap {
	position: absolute;
	top: 41px;
	right: 0;
}     
</style>
<script type="text/javascript">
	require([ "validate", "select" ],function() {
		var enterpriseId = '${enterpriseId}';	//企业id
		var projectCode = '${projectCode}';		//项目id
		var enterpriseIds = JSON.parse('${enterpriseIds}');
		// 刷新
		mainVar.yes = function() {
			//审核通过
			/* $('#infj').children('.infParaCon').remove();
			$('#infj2').children('.infParaCon').remove();
			$('#raw').children('.infParaCon').remove();  */
			
			enterpriseIds = delEnterpriseId(enterpriseIds,enterpriseId);
			var nextEnterpriseId = enterpriseIds[0];
			
			qy.ajax({
				url : base + "/admin/enterprise/updatestatus.jhtml",
				data : {
					projectId : projectCode,
					enterpriseId : enterpriseId,
					status:3,
					opinion:$("#ideaj").val(),
					nextEnterpriseId:nextEnterpriseId,
				},
				callBack : function(data) {
					if(!nextEnterpriseId){
						layer.closeAll();
					}
					if(data.entity){
						showData(data.entity.enterprise, data.entity.kiln, data.entity.rawMaterials,
								data.entity.section);
						enterpriseId = nextEnterpriseId;
					}
					grid1.loadData();
				}
			})
			// 清空审核内容
			$("#ideaj").val("");
			
		}

		mainVar.btn2 = function() {
			//审核不通过
			/*  $('#infj').children('.infParaCon').remove();
			 $('#infj2').children('.infParaCon').remove();
			$('#raw').children('.infParaCon').remove();  */
			
				enterpriseIds = delEnterpriseId(enterpriseIds,enterpriseId);
				var nextEnterpriseId = enterpriseIds[0];
				
				qy.ajax({
					url : base + "/admin/enterprise/updatestatus.jhtml",
					data : {
						projectId : projectCode,
						enterpriseId : enterpriseId,
						status:4,
						opinion:$("#ideaj").val(),
						nextEnterpriseId:nextEnterpriseId,
						
					},
					callBack : function(data) {
						if(!nextEnterpriseId){
							layer.closeAll();
						}
						if(data.entity){
							showData(data.entity.enterprise, data.entity.kiln, data.entity.rawMaterials,
									data.entity.section);
							enterpriseId = nextEnterpriseId;
						}
						grid1.loadData();
					}
				})
				// 清空审核内容
				$("#ideaj").val("");
				
		}
		qy.ajax({
			url : base + "/admin/enterprise/auditPageQuery.jhtml",
			data : {
				enterpriseId : '${enterpriseId}',
			},
			callBack : function(data) {
				showData(data.enterprise, data.kiln, data.rawMaterials,
						data.section);
			}
		});
		
		function delEnterpriseId(enterpriseIds,enterpriseId){
			var arr = [];
			for(var i = 0; i<enterpriseIds.length; i++){
				if(enterpriseIds[i] != enterpriseId){
					arr.push(enterpriseIds[i]);
				}
			}
			return arr;
			console.log(enterpriseIds);
		}
	function showData(enterprise, kiln, rawMaterials, section) {
		//企业信息
		$('#enterpriseName').val(enterprise.name);
		/* $('#enterpriseCode').val(enterprise.code); */
		$('#indMain').val(enterprise.industryCategoryNameMain);
		$('#indMiddle').val(enterprise.industryCategoryNameMiddle);
		/* $("#enterpriseProvinces").val() */
		/* $("#enterpriseArea").val(enterprise.address.city.name); */
		/* $("#enterpriseCounty").val(enterprise.address.county.name) */
		$('#photoImg').attr('src',enterprise.photoUrl);
		/* if(enterprise.isSanluanwu == "是"){
			$('#isSanluanwu').attr('checked', true);
		} */
		
		//炉窑
		/* var html = '';
		if (kiln) {
			console.log(html);
			var kilnArr1 = [];
			for (var i = 0; i < kiln.length; i++) {
				if (kiln[i].product.name && kiln[i].product.unit) {
					html += ' <div class="infParaCon">'
						+ '<label for="" class="infpLable" >炉窑产品</label>'
						+ '<div class="infpCon">'
						+ '<input type="" class="input" value="'+kiln[i].product.name+'" readonly="readonly">'
						+ '</div>'
						+ '<label for="" class="infpLable" >单位</label>'
						+ '<div class="infpCon">'
						+ '<input type="" class="input" value="'+kiln[i].product.unit+'" readonly="readonly">'
						+ '</div>'
						+ '</div>';
				}
				if (kiln[i].rawMaterials1) {
					kilnArr1.push({
						'name' : kiln[i].rawMaterials1.name,
						'unit' : kiln[i].rawMaterials1.unit
					});
				}
				if (kiln[i].rawMaterials2) {
					kilnArr1.push({
						'name' : kiln[i].rawMaterials2.name,
						'unit' : kiln[i].rawMaterials2.unit
					});

				}
				if (kiln[i].rawMaterials3) {
					kilnArr1.push({
						'name' : kiln[i].rawMaterials3.name,
						'unit' : kiln[i].rawMaterials3.unit
					});

				}
				if (kiln[i].rawMaterials4) {
					kilnArr1.push({
						'name' : kiln[i].rawMaterials4.name,
						'unit' : kiln[i].rawMaterials4.unit
					});

				}
				if (kiln[i].rawMaterials5) {
					kilnArr1.push({
						'name' : kiln[i].rawMaterials5.name,
						'unit' : kiln[i].rawMaterials5.unit
					});
				}
			
				for (var j = 0; j < kilnArr1.length; j++) {
					if (kilnArr1[j].name && kilnArr1[j].unit) {
						html += ' <div class="infParaCon">'
							+ '<label for="" class="infpLable" >炉窑原辅料</label>'
							+ '<div class="infpCon">'
							+ '<input type="" class="input" value="'+kilnArr1[j].name+'" readonly="readonly">'
							+ '</div>'
							+ '<label for="" class="infpLable" >单位</label>'
							+ '<div class="infpCon">'
							+ '<input type="" class="input" value="'+kilnArr1[j].unit+'" readonly="readonly">'
							+ '</div>'
							+ '</div>';
					}
				};
			}
		}
		$("#infj").append(html); */

		//工段
		/* var sec = "";
		if (section) {
			console.log(sec);
			for (var i = 0; i < section.length; i++) {
				if (section[i].product && section[i].product.name
						&& section[i].product.unit) {
					sec += ' <div class="infParaCon">'
						+ '<label for="" class="infpLable" >工段产品</label>'
						+ '<div class="infpCon">'
						+ '<input type="" class="input" value="'+section[i].product.name+'" readonly="readonly">'
						+ '</div>'
						+ '<label for="" class="infpLable" >工段单位</label>'
						+ '<div class="infpCon">'
						+ '<input type="" class="input" value="'+section[i].product.unit+'" readonly="readonly">'
						+ '</div>'
						+ '</div>';
				}
			}
		}
		$("#infj2").append(sec); */
		
		/* var html = "";
		if (rawMaterials) {
			console.log(html);
			for (var i = 0; i < rawMaterials.length; i++) {
				if (rawMaterials[i].name && rawMaterials[i].unit) {
					html += ' <div class="infParaCon">'
						+ '<label for="" class="infpLable" >原辅料</label>'
						+ '<div class="infpCon">'
						+ '<input type="" class="input" value="'+rawMaterials[i].name+'" readonly="readonly">'
						+ '</div>'
						+ '<label for="" class="infpLable" >单位</label>'
						+ '<div class="infpCon">'
						+ '<input type="" class="input" value="'+rawMaterials[i].unit+'" readonly="readonly">'
						+ '</div>'
						+ '</div>';
				}
			}
		}
		$("#raw").append(html); */
	}
	$(".js-right_pBig").on("click", function() {
		$(".js-img").css({
			"display": "block"
		})
	})
	$(".js-close").on("click", function() {
		$(".js-img").css({
			"display": "none"
		})
	})
});
</script>
<body>
	<form id="inputForm" style="width: 100%">
		<input type="hidden" id="enterpriseId" name="enterpriseId" value="${enterprise.id}" />
		<div class="left">
			<div class="infLine">
				<div class="infPara infPara_1">
					<div class="infParaCon">
						<label for="" class="infpLable" >企业名称</label>
						<div class="infpCon">
							<input type="" class="input" id="enterpriseName" readonly="readonly">
						</div>
					</div>
					<!-- <div class="infParaCon">
						<label for="" class="infpLable" >组织机构代码/统一社会信用代码</label>
						<div class="infpCon">
							<input type="" class="input" id="enterpriseCode" readonly="readonly">
						</div>
					</div> -->
					<!-- <div class="infParaCon">
						<div  class="infpCon">
							<label for="" class="infpLable" >散乱污</label>
							<input style="margin-top: 9px" type="checkbox"  id="isSanluanwu" readonly="readonly">
						</div>
					</div> -->
					<!-- <div class="infParaCon">
						<label for="" class="infpLable" >省</label>
						<div class="infpCon">
							<input type="" class="input" id="enterpriseProvinces" readonly="readonly">
						</div>
					</div> -->
					<!-- <div class="infParaCon">
						<label for="" class="infpLable" >区</label>
						<div class="infpCon">
							<input type="" class="input" id="enterpriseArea" readonly="readonly">
						</div>
					</div>
					<div class="infParaCon">
						<label for="" class="infpLable" >县</label>
						<div class="infpCon">
							<input type="" class="input" id="enterpriseCounty" readonly="readonly">
						</div>
					</div> -->
					
					<div class="infParaCon">
						<label for="" class="infpLable">行业类别(大类)</label>
						<div  class="infpCon">
							<input type="" class="input" id="indMain"
								value="${enterprise.industryCategoryNameMain}"
								readonly="readonly">
						</div>
					</div>
					<div class="infParaCon">
						<label class="infpLable">行业类别(中类)</label>
						<div class="infpCon">
							<input type="" class="input" id="indMiddle" name=""
								value="${enterprise.industryCategoryNameMiddle}"
								readonly="readonly">
						</div>
					</div>
					<!-- <div id="infj"></div>
					<div id="infj2"></div>
					<div id="raw"></div> -->
					<!--新增审核意见-->
					<div class="" style="padding: 5px 0; height: 150px;">
						<label class="infpLable">审核意见</label>
						<textarea class="textIdea" style="padding: 5px 0; height: 100px;" name="ideaj" id="ideaj" rows="" cols=""></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="right photoImgSmallWrap infPara">
			<img id="photoImg" class="photoImgSmall" style="width: 100%; height: 100%;"/>
			<p class="right_p js-right_pBig">点击此处查看大图</p>
		</div>
		<div class="js-img modelImg">
			<span class="js-close closeImg">关闭</span>
			<img id="photoImg" class="photoImgSmall" style="width: 100%; height: 100%;"/>
		</div>
	</form>
</body>
</html>