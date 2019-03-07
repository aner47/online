$.fn.MySelect = function(opts){
	function MySelect(opts){
		var container = $("<div class = 'myselect-container'></div>");
		var dd = $("<dd></dd>");
		container.append(dd);
		var dl = $("<dl class = 'myselect-dl'></dl>");
		var testData = [1,2,3,4,5,6,7,8,9];
		
		var method = {
			show : function(){
				if($('.myselect-container',$('#'+opts.id).parent()).length ==1){
					$('.myselect-container',$('#'+opts.id).parent()).css('display',"block");
				}else{
					$("#"+opts.id).after(container);
				}
				
			},
			hide : function(){
				$('.myselect-container',$('#'+opts.id).parent()).css('display',"none");
			},
			create : function(data){
				for(var i = 0 ; i < data.length;i++){
					dd.append(dl.clone().append(data[i]));
				}
			}
		}
		var my = this;
		$.extend(this,method);
		my.create(testData);
		$("#"+opts.id).parent().on("click",'.myselect-container .myselect-dl',function(){
				$("#"+opts.id).val($(this).html());
		})
		
		
		$("#"+opts.id).focus(function(){
			my.show();
		})
		$("#"+opts.id).blur(function(){
			setTimeout(function(){my.hide();}, 200); 
		})
		

	}
	function MySimpleSelect(opts){
		var $options = $('<option></option>');
		var $select;
		if(opts.contain){
			$select = $('#'+opts.simpleId,opts.contain);
		}else{
			$select = $('#'+opts.simpleId);
		}
		
		$select.html('');
		if(opts.simpleData){
			$.each(opts.simpleData,function(k,v){
				
				var options = $options.clone();
				options.attr("value",k).append(v);
				if(opts.selected == k){
					options.attr("selected",'selected');
				}
				$select.append(options);
			})
		}else if(opts.remote){
			var defaultOption = {
					url :base +"/common/dictionary/findCodeDataByName.jhtml",
					key :"code",
					value:"codeValue"
			}
			opts.remote = $.extend(true,defaultOption,opts.remote);
			var options = {
					url:opts.remote.url,
					data:opts.remote.data,
					callBack:function(data){
						if(!data) return ;
						$.each(data,function(k,v){
							var options = $options.clone();
							if(v[opts.remote.key]){
								var code = $select.attr('data-value');
								if(code == v[opts.remote.key] ){
									options.attr("selected","selected");
								}
								options.attr("value",v[opts.remote.key]).append(v[opts.remote.value]);
								$select.append(options);
							}
							
						}
				
						)
						if(typeof(opts.remote.loadAfter) === 'function'){
							opts.remote.loadAfter();
						}
					}
			
			}
			$.fn.MyAjax(options);
			
		}
		

	}
	if(opts.type =='simple'){
		return new MySimpleSelect(opts);
	}else{
		return new MySelect(opts);
	}
	

	
}


$(function(){
	$('select[data-code]').each(function(){
		var code = $(this).attr("data-code");
		var gradeOpts = {
				simpleId:this.id,
				type:"simple",
				remote:{
					data:{name:code}
				}	
			}
		$.fn.MySelect(gradeOpts);
	})
	
})