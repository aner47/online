<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet/less" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/from.less" />
<script
	src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title></title>
<script type="text/javascript">
require(["grid","select","panel","less","progresbar"],function(g){
	$("#queryForm #templateType").select({filter:true,name:"templateType",zIndex:3,change:function(event, value){}});
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条项目模板信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除项目模板成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除项目模板失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加项目模板信息",
				url : "addPage.jhtml",
				data:"",
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
	
		$("#update").click(function(){
			var opts = {
				title: "修改项目模板信息",
				url : "updatePage.jhtml",
				data:{id:grid1.getSelectedValue("id")[0]},
				yes : function(){
					$("#inputForm").submit();
				},
				cancel:function(){}
			}
			qy.panel(opts);
		});
			//查询
		$('#query').click(function(){
			var data = {};
			var array = $('#queryForm').serializeArray();
			$.each(array,function(i,v){
				data[v.name] = v.value;	
				
			})
			grid1.loadData(data);
		});
			
			
	
		var opts = {
				tab:"tab1",
				data:{pageSize:10,pageNumber:0,templatesType:"excel"},
				url:"query.jhtml",
				columns :[
						{name:"id",caption:"ID",hidden:true,type:"text"},
						{name:"name",caption:"模板名称",hidden:false,type:"text"},
                        {name:"templateType",caption:"填表类型",hidden:false,type:"text",valueSet:"企业【企业类型】"},
                        {name:"projectId",caption:"项目",hidden:false,type:"text",valueSet:"全局【项目】"},
                        {name:"operate",caption:"操作",isHtml:true,html:'<a id="view"  style="cursor: pointer;"><i class="iconfont icon-xiangqing"></i><i class="iconfont  icon-xiazai">导出</i></a>',
                            events :[{
                                     type:'click',
                                     select:'.icon-xiangqing',
                                     handle:function(){
                                           var id =grid1.getCurrentRowValue(this,'id');
                                           location.href = "../projectmoduleinfo/list.jhtml?templateId="+id+"&source=list";
                                     }
                                   },{
 					  				  type:'click',
					  				  select:'.icon-xiazai',
					  				  handle:function(){
					  					var id= grid1.getCurrentRowValue(this,'id');
					  					qy.save({title:'是否导出',yes:function(index){
					  						layer.close(index);
					  						qy.ajax({
					  							url: base+"/admin/excelexport/exportFileValid.jhtml?projectTemplatesId="+id,
					  							callBack:function(data){
					  								if(data.code!=20000){
					  									qy.fail({title:data.content});
					  									return;
					  								}
					  								var progressBar =  new ProgressBar({interval:200});
					  								progressBar.query();
					  								var taskId = progressBar.getCoverId();
					  								var link = document.createElement("a");
        					  					    link.href = base+"/admin/excelexport/download.jhtml?projectTemplatesId="+id+"&scheduleKey="+taskId;
                                                link.style = "visibility:hidden";
                                                document.body.appendChild(link);
                                                link.click();
                                                document.body.removeChild(link);
					  							}
					  						})
					  						
	                                
					  					}})
					  					
			                          
					  				  }
				  				  	}
                            ]
                     } 
					]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	})
</script>
<style>
.input {
    height: 30px;
    line-height: 28px;
    border: 1px solid #ccc;
    padding: 0 5px;
}
</style>
</head>
<body>
	<div class="block    ">
		<h1 class="title">
			<i class="iconfont icon-wuranyuan"></i> 项目模板信息
		</h1>
		<div class="form-line form-line2">
            <form id="queryForm" action="">
                <div class="group col-lg-5f1 col-md-4 unmust">
                    <label class="form-label" for="name">模板名称</label> <input
                        type="search" name="name" id="name" class="information_input input" />
                </div>
                <div class="group col-lg-5f1 col-md-4 unmust">
                    <label class="form-label" for="templateType">填表类型</label>
                    <div id="templateType" class="selectF" data-code="企业【企业类型】" ></div>
                </div>
                <div class="group unCol unmust btnF">
                    <a class="btn" id="query">查询</a>
                </div>
            </form>
	</div>
	<div class="grid-tools">
		<button id="add" class="btn-general">添加</button>
		<button id="update" class="btn-warning" data-grid="tab1"
			data-enable="1">修改</button>
		<button id="delete" class="btn-denger" data-grid="tab1"
			data-enable="2">删除</button>
	</div>
	<div id="tab1"></div>
</body>
</html>