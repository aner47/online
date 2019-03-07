<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
	.body2{
	height: 100%;
    padding: 22px 11px 14px 22px;
    
   
	}
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/block.css"/>
	<script src="<%=request.getContextPath()%>/resources/js/require/require.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/require/main.js"></script>
	<script type="text/javascript">
    require(["grid","panel"],function(g){
          // var simpelData = JSON.parse('${content}');
           var simpelData = JSON.parse(sessionStorage["par1"]);
           console.log(simpelData.listFails);
           if(simpelData.successNum != null){
        	   $("#succeed").html('导入成功'+simpelData.successNum+"条"+",失败"+simpelData.listFails.length+"条");
           }else{
        	   var tip = "";
        	   $.each(simpelData.maps,function(key,values){     
        		       console.log(key);     
        		       console.log(values);  
        		   		tip = tip+"【"+key+values+"条】" ;  
        		    }); 
        	   $("#succeed").html('导入成功'+tip); 
           }
           
           var data = {"content":simpelData.listFails};
           var opts2 = {
                tab:"tab2",
                checkbox:false,
                num:true,
                data:{pageSize:10,pageNumber:0},
				simpleData:data,  
                columns :[
                     {name:"row",caption:"行",width:"15%",hidden:false,type:"text",format:function(value){
                    	 return "第"+value+"行"
                     }},
                     {name:"msg",caption:"失败消息",hidden:false,type:"text"},
                    ]
            }
            grid2 =  g.grid(opts2);
            grid2.loadSimpleData();
            
        
    })
</script>
</head>
<body>
    <div class="body2">
        <div class="body2-block">
        	<div id="succeed" style="margin-bottom: 10px"></div>
            <div id="tab2" class="tab"></div>
        </div>
    </div>
</body>
</html>