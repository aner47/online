<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resources/css/from.less" />
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
require(["grid","select","panel","less","ajax"],function(g){
		$("#modifiyPassword").click(function(){//重置密码
			 qy.ajax({
				url:base+"/admin/systemuser/modifiyPassword.jhtml",
				data:{ids:grid1.getSelectedValue('id').join(',')},
				callBack:function(d){
					if(d.code == 20000){
						layer.msg("重置密码为：123456!",{icon:6,time:2500});
					}else{
						layer.msg("重置密码错误",{icon:5,time:2500});
					}
					
				}
			}); 
			/* var opts = {
					title: "重置密码",
					url : "modifiyPassword.jhtml",
					yes : function(){
						$("#inputForm").submit();
					},
					cancel:function(){}
				}
				qy.panel(opts); */
		});
	
		$('#delete').click(function(e){//删除
			qy.del({
				title:'是否删除所选'+grid1.getSelectedValue('id').length+'条用户信息?',
				yes:function(index){
					layer.close(index);
					var ids = grid1.getSelectedValue('id');
					var opts ={
						url:'delete.jhtml',
						data :{"ids":ids.join(',')},
						callBack:function(data,errcode){
							if(data.code== 20000){
								qy.suc({title:'删除用户成功！'});
								grid1.loadData();
							}else{
								qy.fail({title:data.content});
							}
						}
					}
					qy.ajax(opts);
				}
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加用户管理信息",
				url : "addPage.jhtml",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改用户管理信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0]},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
		
		$("#projectId","#queryForm").select({name:'projectId'});
		
		$("#userType","#queryForm").select({name:"userType"});
		
			//查询
		$('#query').click(function(){
		 	var data = {};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data[v.name] = v.value;	
				
			})
			grid1.loadData(data); 
			
		});
			
		$("#export").click(function(){
			var param ="";
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				param += v.name+"="+v.value+"&";
			}) 
			param = param.substring(0,param.lastIndexOf("&"));
			window.location.href=base+"/admin/systemuser/exportUser.jhtml?"+param;
		});
	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
				    {name:"username",caption:"用户名",hidden:false,type:"text"},
				    {name:"email",caption:"邮箱",hidden:false,type:"text"},
				    {name:"userType",caption:"用户类型",hidden:false,type:"text",valueSet:"用户类型"},
				    {name:"phone",caption:"手机",hidden:false,type:"text"},
				    {name:"project.id",caption:"所属项目",hidden:false,type:"text",valueSet:"全局【项目】"},
				    {name:"id",caption:"ID",hidden:true,type:"text"},
								    	
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
<style>
@media screen and (max-width: 1080px) {
		.form-line input[type="text"]{
			font-size: 13px;
		}
	}
</style>
</head>
<body>
	<div class="block ">
		<h1 class="title">
			用户管理信息
		</h1> 
		<div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-3 unmust">
                    <label class="form-label" for="username">用户名</label> 
                    <input type="search" name="username" id="username" class="information_input" style="width:100%;height:100%"/>
                </div>
                <div class="group col-lg-5f1 col-md-3 unmust">
					<label class="form-label" for="name">项目</label>
					<div id="projectId" class="selectF" data-code="全局【项目】" ></div>
				</div>
                <div class="group col-lg-5f1 col-md-3 unmust">
					<label class="form-label" for="userType">用户类型 </label> 
					<div id="userType" class="selectF" data-code="用户类型"></div>
				</div>
                <div class="group unCol unmust btnF">
                    <a class="btn" id="query">查询</a>
                </div>
            </form>
        </div>
	</div>

	<div class = "grid-tools">
		<button id = "add" >添加</button>
		<button id = "update"  data-grid = "tab1" data-enable = "1" >修改</button>
		<button id = "delete" data-grid = "tab1" data-enable = "2" >删除</button>
		<button id="modifiyPassword" data-grid = "tab1" data-enable = "2">重置密码</button>
		<button id = "export">导出用户</button>
	</div>
	<div id="tab1"  class="tab"></div> 





</body>
</html>