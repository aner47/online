define(["jquery"],function(){
	return {
		hide:function(){
			$('body').hide();			
			/*$(document).ready(a);*/
			var hideParam = $("#fieldHide").val();
			console.log("隐藏字段:::::::::::::::::::::"+hideParam)
			var count = 0;
			a()
			function a(){
				var param = [];
				if(hideParam){
					var params = hideParam.split(",");
					for(var i = 0;i < params.length; i++){
						if(params[i] != ''){
							if($("input[name='"+params[i]+"']","form").length>0){
								$("input[name='"+params[i]+"']","form").attr("disabled","disabled").parents(".infPara").hide()
							}else if($("div #"+params[i],"form").length>0){
								$("div #"+params[i],"form").attr("disabled","disabled").parents(".infPara").hide()
							}
							else{
								param.push(params[i])
							}
						}
						
					}				
				}
				if(param.length>0){
					if(count <10){
						count++;
						console.log("count:::"+ count)
						setTimeout(a,1000)
					}else{
						top.layer.msg('页面加载错误请联系管理员！' + param);
					}
					
				}else{
					$('.hideCls').show();
					$('body').show();
					typeof window.complete === 'function'?complete():"";
				}
				
			}

			
		},
		gridHide:function(opt){
			var hideParam = $("#fieldHide").val();
			console.log("隐藏字段:::::::::::::::::::::"+hideParam)
			if(hideParam){
				var params = hideParam.split(",");
				for(var i = 0;i < opt.columns.length;i++){
					if(params.indexOf(opt.columns[i].name) != -1){
						opt.columns[i].hidden = true;
					}
				}
			}
			$('.hideCls').show();
		}
		
	}
	
	
// 	$('body').append('<script type="text/javascript" src="'+src+'"></script>');
	
})