<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>修改项目</title>
</head>

<script type="text/javascript">
	require([ "validate", "ajaxform","ztree" ,"date","year"], function(a) {
		var treeId;
		var tree 
		/* ztree设置 */
		var zTree;
		var zNodes; 
		var setting = {
		  view: {
			     filter:true,  //是否启动过滤
			     expandLevel:0,  //展开层级
			     showFilterChildren:true, //是否显示过滤数据孩子节点
			     showFilterParent:true, //是否显示过滤数据父节点
			     showLine: false,//是否显示节点之间的连线
		         showIcon: false, //是否显示图标
		    selectedMulti: true //设置是否允许同时选中多个节点
		  },
		  check:{
		    chkStyle: "checkbox",//复选框类型
		    enable: true ,//每个节点上是否显示 CheckBox 
		  },
		  async: {
			  enable: true,
			   type: 'post',
			   autoParam: ['id'], //异步加载时需要自动提交父节点属性的参数
			   dataType:'json',
		    },
		   
		  data: { 
			  key:{
		    	name: "industryName",
		      },
		    
			  simpleData: {//简单数据模式
		      enable:true,
		      idKey: "id",
		      pIdKey: "pid",
		    }
		  },
		}; 
		
		var dataYear = $('#inputForm #dataYear').year({'name':'dataYear',defaultValue:"${project.dataYear}"});
		
		$("#startDate", "#inputForm").jeDate({
            format:"YYYY-MM-DD",
            isTime:false, 
            zIndex:999999900
        })
        $("#endDate", "#inputForm").jeDate({
            format:"YYYY-MM-DD",
            isTime:false, 
            zIndex:999999900
        })
        
		/* 日期比较-开始  */
			function duibi(d1,d2)
			{
		  		//return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		  		return (new Date(Date.parse(d1)) > new Date(Date.parse(d2)));
		  	}
		  	/* 日期比较-结束 */
		
		var dataid = "${project.sysProductIndustrys}".split(",");
		$.ajax({  
	        type: 'POST',  
	        url: '../sysproductindustry/queryAll.jhtml',  //url 
	        data: {},  
	        dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json  
	        ContentType: "application/json; charset=utf-8",  
	        success: function (data) { 
	        	console.log(data);
	        	for (var i = 0; i < dataid.length; i++) {
	        		data = data.replace('"id":' + dataid[i]+',','"id":' + dataid[i] + ',checked:true,');
	 			};
	 			data = data.replace(/"id":/g ,'"pid":0,"id":');
	 			data = data.replace('[','[{"id":0,"industryName":"产品行业"},');
	 			console.log(data);
	            zNodes = data;  
	            tree =  $.fn.zTree.init($("#menuTree"), setting, eval('(' + zNodes + ')')); 
	        },  
	        error: function (msg) {
	        	console.log(msg);
	           // alert("失败");  
	        }  
	    }); 	
		
		
		$('#commit').click(function() {
			$("#inputForm").submit();
		})
		//提交表单
		$("#inputForm").validate({
			rules : {
				provinces:{
					required:true
				},
				city:{
					required:true
				},
				startDate:{
					required:true
				},
				endDate:{
					required:true
				},
				dataYear:{
					required:true
				},
				projectType:{
					required:true
				}
			},
			messages : {},
			submitHandler : function(form) {
				//日期比较
				if(duibi($("#inputForm #startDate").val(), $("#inputForm #endDate").val())){
					layer.msg('结束时间不能早于开始时间', {icon:3});
					return false;
				}
				
				var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				var nodes = treeObj.getCheckedNodes(true);
				console.log(nodes);
				var c="";
				for(var i=0;i<nodes.length;i++){
		            if(nodes[i].isParent!=true){
		                c+=nodes[i].id+",";
		            }
		    	}
				console.log(c);
				$('#inputForm #sysProductIndustrys').val(c);
				/* var menuList= [];
				$.each(nodes,function(k,v){
					menuList.push(v.id);
				}) 
				$('#inputForm #sysProductIndustrys').val(menuList.join(','));*/
				
				
				var options = {
					url : 'update.jhtml',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('修改成功！', {
							icon : 6
						})
						layer.closeAll('page');
						grid1.loadData();
					},
					error : function() {
						layer.msg('修改失败', {
							icon : 3
						});
					}
				};
				$(form).ajaxSubmit(options);
				return false;
			}
		});
		
		var provinces = $("#provinces","#inputForm").select({name:"provinces",zIndex:9
			,change:function(event, value){
	         city.clear();
	         county.clear();
	         city.loadData("parent:"+value);
	     	}
			,initAfter:function(data,value,other){
				city.loadData("parent:"+value);
			}
		});
	    var city = $("#city","#inputForm").select({name:"city",initLoad:false,zIndex:8
			,change:function(event, value){
				county.clear();
				county.loadData("parent:"+value);
		     	}
				,initAfter:function(data,value,other){
					county.loadData("parent:"+value);
				}
			});
	    var county = $("#county","#inputForm").select({name:"county",zIndex:7,initLoad:false});
	    var projectType = $("#projectType","#inputForm").select({name:"projectType",zIndex:6});
	    
	});
</script>
<style>
.dataYearCls {
	height: 40px;
	width: 100%;
	border:1px solid #d4d8de;
}
</style>
<body>

	<form id="inputForm">
		<div class="container">

			<div class="form-line">
				<label for="name">名称 ：</label> <input type="text" id="name"
					name="name" placeholder="名称" value="${project.name}" required/>
			</div>
			<div class="form-line">
				<label for="startDate">开始时间 ：</label>
				<input type="text" id="startDate" name="startDate"  value="<fmt:formatDate value="${project.startDate}" pattern="yyyy-MM-dd"/>" placeholder="开始时间" onfocus="this.blur()"/>
				 <%-- <input type="date"
					id="startDate" name="startDate" placeholder="开始时间"
					value="<fmt:formatDate value="${project.startDate}" pattern="yyyy-MM-dd"/>" required/> --%>
			</div>
			<div class="form-line">
				<label for="endDate">结束时间 ：</label>
				<input type="text" id="endDate"
					name="endDate" placeholder="结束时间" 
					value="<fmt:formatDate value="${project.endDate}" pattern="yyyy-MM-dd"/>"
					onfocus="this.blur()"/>
				 <%-- <input type="date" id="endDate"
					name="endDate" placeholder="结束时间"
					value="<fmt:formatDate value="${project.endDate}" pattern="yyyy-MM-dd"/>" required/> --%>
			</div>
			<div class="form-line">
				<label for="invitationCode">邀请码 ：</label> <input type="text"
					id="invitationCode" name="invitationCode" placeholder="邀请码"
					value="${project.invitationCode}" required/>
			</div>
			<%-- <div class="form-line">
				<label for="dataYear">数据年份 ：</label>
				<!-- <div id="dataYear" class=" iconfont icon-nianfen"></div> -->
				<input type="text" id="dataYear"
					name="dataYear" placeholder="数据年份" value="${project.dataYear}"/>
			</div> --%>
			<div class="form-line">
				<label for="">数据年份 ：</label>
			</div>
			<div class="form-line">
				<!-- <input type="text" id="dataYear" name="dataYear" placeholder="数据年份" /> -->
				<div class="dataYearCls">
					<div style="padding-top:10px;" id="dataYear" class="yearF iconfont icon-nianfen"></div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${project.id}" />
			<div class="form-line">
				<label for="provinces">省 ：</label> 
				<div data-code="地域" id="provinces" data-defaultValue="${project.provinces}"></div>
			</div>
			<div class="form-line">
				<label for="city">市 ：</label> 
				<div data-code="地域" id="city" data-defaultValue="${project.city}"></div>
			</div>
			<div class="form-line">
				<label for="city">县 ：</label> 
				<div data-code="地域" id="county" data-defaultValue="${project.county}"></div>
			</div>
			<div class="form-line">
				<label for="city">项目类型：</label> 
				<div data-code="项目【类型】" id="projectType" data-defaultValue="${project.projectType}"></div>
			</div>
			<div class="form-line">
				<input type="hidden" id="sysProductIndustrys" value="${project.sysProductIndustrys}" name="sysProductIndustrys" placeholder="菜单列表" /> 
				<label for="fieldModule">产品行业：</label>
				<div class="form-group">
					<!-- // 组织树模型，固定格式   -->
					<ul id="menuTree" class="ztree">
					</ul>
				</div>
			</div>

		</div>

	</form>
</body>
</html>