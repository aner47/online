var qy = qy||{};
qy.ajax = function(opts){
	var load; 
	var defaultOpts ={
		type : "post",
		dataType:"json",
		async:true,
		data :{},
		loading:true
	}
	opts = $.extend(true, defaultOpts, opts);
	$.ajax({
		url: opts.url,
		type: opts.type,
		dataType: opts.dataType,
		cache: false,
		async: opts.async,
		data : opts.data,
		beforeSend: function() {
			opts.loading?load = top.layer.load(2):'';//loading
		},
		success: function(data){
			opts.loading?top.layer.close(load):"";
			if(data.code === 403){
				top.location.href=base+"/web/login.jhtml"
			}else if(data.code === 20000 && typeof opts.callBack === 'function'){
				opts.callBack(data,"999999",arguments);
			}else if(typeof opts.callBack === 'function'){
				opts.callBack(data,"000000",arguments);
			}
		},
		error: function(data, errCode){
			opts.loading?top.layer.close(load):"";//出错统一处理
			if(data.status === 403){
				top.location.href=base+"/web/login.jhtml";
				return;
			}
			if(typeof opts.callBack === 'function'){
				opts.callBack(data,"000000",arguments);
			}
		}
	});
}

