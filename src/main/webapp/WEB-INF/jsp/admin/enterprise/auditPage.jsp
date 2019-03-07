<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/logob.png" >
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reg/content.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/admin/auditPage/auditPage.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/viewtable/viewtable.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/admin/mygrid/mygrid1.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/block.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/iconfont/iconfont.css" />

<%-- <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/private/enterprise/auditpage.css" /> --%>

<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta charset="UTF-8">
<title>企业审核</title>
</head>
<style>

.padding_container {
	padding-left: 16px !important;
}
.layui-layer-page .layui-layer-content form {
	padding: 0 !important;
}
.layui-layer-shade{
	/* width: 0;
	height: 0; */
	z-index: -1 !important;
}
.des {
	background: #A6BBCE!important;
}
#topConta {
	height: 125px;
	padding-top: 20px;
}
.input {
	outline: 0;
}
.buttons {
	border-radius: 3px;
    box-shadow: 0 2px 2px #d6d6d6;
    text-align: center;
    display: block;
    cursor: pointer;
    float: left;
    position: relative;
    width: 92px;
    height: 30px;
    font-size: 16px;
    border: 0;
    margin-right: 20px;
    background-color: #3c88ff;
    color: #ffffff;
    line-height: 30px;
    padding: 0;
    outline: 0;
}
.dataShowLine {
	 width: 25%!important;
	 float: left;
	 box-sizing: border-box;
}

</style>
<body>
<div id="topConta">
		<form id="inputForm">
			<div class="container js-handleShow">
				<div class="form-line">
		            <textarea style="width: 426px ;height: 100px;padding-left:2px;" id="opinion" name="opinion" class="input" placeholder="请输入审核意见"></textarea>
					<input type="hidden" id="enterprieId" name="enterpriseId" value="${enterpriseId}" />
					<input type="hidden" id="status" name="status" />
		        </div>
			</div>
			
	    </form>
</div>
<div style="display: none;" id="app" >
	<div style="height: 30px; margin-bottom: 20px;">
				<input @click="handlePass" type="button" id="pass" class="buttons" value="通过" />
				<input @click="handleNopass" type="button" id="nopass" class="buttons" value="不通过">
				<!-- <input @click="next(-1)" type="button" class="buttons" v-show="preBtn" value="上一条">
				<input @click="next(1)" type="button" class="buttons" v-show="nextBtn" value="下一条"> -->
	</div>
    <div v-if="code == 20000" class="zkhq-ly_contain">
    	<div style="cursor:pointer;" class="zkhq-ly_title handSur" @click="showModel('企业信息', $event)">
	        <span>{{"企业信息"}}</span>
	        <i style="font-size: 14px;" v-show="moduleObj['企业信息'] == false" class="iconfont icon-sanjiaoxingdierbanyou-copy"></i>
	        <i style="font-size: 14px;" v-show="moduleObj['企业信息'] == true" class="iconfont icon-sanjiaoxingdierban"></i>
        </div>
        <div class="zkhq-ly_line dataShowLine" v-show="moduleObj['企业信息']"  v-for="(value,index) in enterpriseInfor()" >
            <div class="zkhq-ly_el" >
                <div class="zkhq-ly_lb zkhq-ly_flex7">{{value.key}}</div>
                <div :title="value.value" class="zkhq-ly_content zkhq-ly_flex3" >{{value.value}}</div>
            </div>

        </div>
        <div style="clear: both"></div>
        <div style="cursor:pointer;" class="zkhq-ly_title handSur" @click="showModel('基本信息', $event)">
	        <span>{{"基本信息"}}</span>
	        <i style="font-size: 14px;" v-show="moduleObj['基本信息'] == false" class="iconfont icon-sanjiaoxingdierbanyou-copy"></i>
	        <i style="font-size: 14px;" v-show="moduleObj['基本信息'] == true" class="iconfont icon-sanjiaoxingdierban"></i>
        </div>
        <div class="zkhq-ly_line dataShowLine" v-show="moduleObj['基本信息']"  v-for="(value,index) in base()" >
            <div class="zkhq-ly_el" >
                <div class="zkhq-ly_lb zkhq-ly_flex7">{{value.key}}</div>
                <div :title="value.value" class="zkhq-ly_content zkhq-ly_flex3" >{{value.value}}</div>
            </div>

        </div>
        <div style="clear: both"></div>
        <template  v-for="(value,key) in content"  v-if="key !='基本信息' && key != '企业信息' && gridContent(key) != ''" >
             <div style="cursor:pointer;" class="zkhq-ly_title handSur" @click="showModel(key, $event)">
	              <span>{{key}}</span>
			      <i style="font-size: 14px;" v-show="moduleObj[key] == false" class="iconfont icon-sanjiaoxingdierbanyou-copy"></i>
	              <i style="font-size: 14px;" v-show="moduleObj[key] == true" class="iconfont icon-sanjiaoxingdierban"></i>
             </div>
             <div v-show="moduleObj[key] && gridContent(key) != ''" class = "mygrid_content autoW">
                <table class="mygrid_table">
                    <tr class="mygrid_title">
                        <th v-for="h in head(key)">{{h.key}}</th>
                    </tr>
                    <tr class="mygrid_column" v-for="data in gridContent(key)">
                        <td v-for="c in data">{{c.value}}</td>
                    </tr>
                </table>
            </div>
        </template>
    </div>
</div>
<script type="text/javascript">
require(["vue","validate","select","commonBase","ajax","ajaxform"],function(Vue){
	/* $("#check").on("click", function() {
		var id = ${enterprise.id};
		top.panel1(id);
	}) */
	
	var id = ${enterpriseId};
	/* console.log(id) */
	var ids = JSON.parse(sessionStorage.getItem("active.pass_"))||[];
	/* console.log(ids) */
	var content;
	var vue;
	qy.ajax({
		url: base + "/admin/enterprise/audit.jhtml",
		data:{enterpriseId: id},
		callBack: function(data){
			if (data.code == 20000) {
				 data.content = data.entity;
		            var keys = Object.keys(data.content)
		            var moduleObj = {};
		            for(var ii = 0;ii<keys.length;ii++){
		                moduleObj[keys[ii]] = false;
		                data.content[keys[ii]].sort(function(o1,o2){return o1.orders-o2.orders})
		            }
					data.moduleObj = moduleObj;
					data.code = data.code;
					data.id = id;
		           /* data:{content: content,moduleObj: moduleObj,code: data.code,id: id}, */
		            vue = new Vue({
		                el: '#app',
		                data:data,
		                methods:{
		                    showModel:function(module){
		                       moduleObj[module] = !moduleObj[module];
		                    },
		                    base:function(){
		                        return  this.content["基本信息"];
		                    },
		                    enterpriseInfor: function(){
		                        return  this.content["企业信息"];
		                    },
		                    head:function(module){
		                        return this.content[module][0];
		                    },
		                    gridContent:function(module){
		                        return this.content[module];
		                    },
		                    handlePass: function() {
		                    	this.isOptionNull();
		                    },
		                    handleNopass: function() {
		                    	var optionVal = $("#opinion").val();
								if (optionVal == "") {
									layer.msg('请填写意见', {
                						icon : 3
                					});
								} else {
									var $this = this;
			                    	$("#status").attr("value",4);
			                    	
			                		var options = {
			                				url : base + '/admin/enterprise/updatestatus.jhtml',
			                				type : 'post',
			                				dataType : 'json',
			                				success : function(data) {
			                					
			                					if(data.code == 20000){
			                						layer.msg('审核不通过！', {
				                						icon : 6
				                					})
				                					
				                					$this.next(1, true);
				                					$("#opinion").val("");
			                					}else{
			                						layer.msg(data.content, {
				                						icon : 3
				                					});
			                					}
			                					
			                				},
			                				error : function() {
			                					layer.msg('审核失败', {
			                						icon : 3
			                					});
			                					$("#opinion").val("");
			                				}
			                			};
			                			$("#inputForm").ajaxSubmit(options);
								}
		                    },
		                    next: function(value, isSlice) {
								var $this = this;
								if (isSlice) {
									/* this.silceArray(ids,id,value) */
									id = this.id = ids[ids.indexOf(id)+ value];
								} else {
									id = this.id = ids[ids.indexOf(id)+ value];
								}
								
								console.log("id----::::",id)
								$("#enterprieId").val(id);
								
								qy.ajax({
											url: base + "/admin/enterprise/audit.jhtml",
											data:{enterpriseId: id},
											callBack : function(json) {
												 $this.content = json.entity;
												 $("#opinion").val("");
												
											}
							    })

							},
							isOptionNull: function() {
								var optionVal = $("#opinion").val();
								if (optionVal != "") {
									layer.msg('不能填写意见', {
                						icon : 3
                					});
								} else {
									var $this = this;
			                    	$("#status").attr("value",3);
			                    	
			                		var options = {
			                				url : base + '/admin/enterprise/updatestatus.jhtml',
			                				type : 'post',
			                				dataType : 'json',
			                				success : function(data) {
			                					
			                					if(data.code == 20000){
			                						layer.msg('审核通过！', {
				                						icon : 6
				                					})
				                					$this.next(1, true);
				                					$("#opinion").val("");
			                					}else{
			                						layer.msg(data.content, {
				                						icon : 3
				                					});
			                					}
			                					
			                				},
			                				error : function(data) {
			                					layer.msg('审核失败', {
			                						icon : 3
			                					});
			                					$("#opinion").val("");
			                				}
			                			};
			                			$("#inputForm").ajaxSubmit(options);
								}
							},
							silceArray: function(ids,id,value) {
								 var array = ids;
								 var index = array.indexOf(id)+value;
								 // 使用splice函数进行移除：

							     if (index > -1) {
							        array.splice(index, 1);
							     }
								 console.log(array)
							}
		                },
		                computed : {
							preBtn : function() {//上一条按钮
								return ids.indexOf(this.id) > 0;
							},
							nextBtn : function() {//下一条按钮
								return ids.indexOf(this.id) < ids.length - 1;
							}
						},
		          })
		          $("#app").show();
			} else {
				vue = new Vue({
					el: "#app",
					data:{code: data.code}
				})
				layer.msg(data.content, {
					icon : 6
				});
			}
		}
	})
	
});
</script>
</body>
</html>