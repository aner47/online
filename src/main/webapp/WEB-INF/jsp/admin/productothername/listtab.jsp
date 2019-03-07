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
<style type="text/css">  
            #content {  
                width: 400px;  
                height: 200px;  
            }  
              
            #tab_bar {  
                width: 400px;  
                height: 20px;  
                float: left;  
            }  
            #tab_bar ul {  
                padding: 0px;  
                margin: 0px;  
                height: 20px;  
                text-align: center;  
            }  
              
            #tab_bar li {  
                list-style-type: none;  
                float: left;  
                width: 133.3px;  
                height: 20px;  
                background-color: gray;  
            }  
              
            .tab_css {  
                width: 400px;  
                height: 200px;  
                background-color: orange;  
                display: none;  
                float: left;  
            }  
              
        </style>  
<script type="text/javascript">

var myclick = function(v) {  
    var llis = document.getElementsByTagName("li");  
    for(var i = 0; i < llis.length; i++) {  
        var lli = llis[i];  
        if(lli == document.getElementById("tab" + v)) {  
            lli.style.backgroundColor = "orange";  
        } else {  
            lli.style.backgroundColor = "gray";  
        }  
    }  

    var divs = document.getElementsByClassName("tab_css");  
    for(var i = 0; i < divs.length; i++) {  

        var divv = divs[i];  

        if(divv == document.getElementById("tab" + v + "_content")) {  
            divv.style.display = "block";  
        } else {  
            divv.style.display = "none";  
        }  
    }  

}  

/* require(["grid","select","panel","less"],function(g){
		$('#delete').click(function(e){//删除
			layer.confirm('是否删除所选'+grid1.getSelectedValue('id').length+'条产品名称配置信息?', {icon: 2, title:'系统提示信息',cancel:function(index){layer.close(index);}}, function(index){
	    		layer.close(index);
				var ids = grid1.getSelectedValue('id');
				var opts ={
					url:'delete.jhtml',
					data :{"ids":ids.join(',')},
					callBack:function(data,errcode){
						if(errcode!=="000000"){
							layer.msg('删除产品名称配置成功！', {icon: 6});
							grid1.loadData();
						}else{
							layer.msg('删除产品名称配置失败！', {icon: 5});
						}
					}
				}
				qy.ajax(opts);
			});
		});
	
		$("#add").click(function(){
			var opts = {
				title: "添加产品名称配置信息",
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
				title: "修改产品名称配置信息",
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
				data:{pageSize:10,pageNumber:0},
				url:"query.jhtml",
				columns :[
                    {name:"id",caption:"ID",hidden:true,type:"text"},
                    {name:"otherName",caption:"产品名称",hidden:false,type:"text"},
                    {name:"industryCode",caption:"行业代码",hidden:false,type:"text"},
                    {name:"productStandardName.standardName",caption:"标准命名",hidden:false,type:"text"},
                    {name:"productStandardName.standardUnit",caption:"标准单位",hidden:false,type:"text"},
                        
    			]
			}
			grid1 =  g.grid(opts);
			grid1.loadData();
		
		
	}) */
</script>
</head>
<body>
   <div id="content">  
            <div id="tab_bar">  
                <ul>  
                    <li id="tab1" onclick="myclick(1)" style="background-color: orange">  
                        tab1  
                    </li>  
                    <li id="tab2" onclick="myclick(2)">  
                        tab2  
                    </li>  
                    <li id="tab3" onclick="myclick(3)">  
                        tab3  
                    </li>  
                </ul>  
            </div>  
            <div class="tab_css" id="tab1_content" style="display: block">  
                <div>
                
                </div>  
            </div>  
            <div class="tab_css" id="tab2_content">  
                <div>页面二</div>  
            </div>  
            <div class="tab_css" id="tab3_content">  
                <div>页面三</div>  
            </div>  
        </div>  
</body>
</html>