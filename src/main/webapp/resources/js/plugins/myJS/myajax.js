
qy.ajax = function(opts){
	var load; 
	var defaultOpts ={
		type : "post",
		dataType:"json",
		data :{}
	}
	opts = $.extend(true, defaultOpts, opts);
	$.ajax({
		url: opts.url,
		type: opts.type,
		dataType: opts.dataType,
		cache: false,
		data : opts.data,
		beforeSend: function() {
			load = layer.load(2);//loading
		},
		success: function(data){
			layer.close(load);
			if(typeof opts.callBack === 'function'){
				opts.callBack(data,'999999');
			}
		},
		error: function(data, errCode){
			layer.close(load);//出错统一处理
			if(typeof opts.callBack === 'function'){
				opts.callBack(data,"000000");
			}
		}
	});
}

