function mqbUplaod(opts){
	WebUploader.create({
	// 	        文件接收服务端。
		server: base +"/mingqiyun/file/upload.jhtml",
	// 	        选择文件的按钮。可选。
	    pick: {
	    	id : opts.id,//选择id
	    	multiple : false
	    },
	// 	     图片质量，只有type为`image/jpeg`的时候才有效。
	    quality: 90,
	    // 只允许选择文件，可选。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    },
	    auto:true
	}).on('fileQueued', function(file) {
	    	var	$img = $('#' + $list.attr('target-img')).attr('fileName',file.name).siblings().show().find('img');
	        // 创建缩略图
	        uploader.makeThumb(file, function(error,src) {//生成缩略图
	        	error ? $img.replaceWith('<span>不能预览</span>') : $img.attr('src', src);
	        }, 100, 100 );
	        
	    }).on('uploadAccept', function(file, data){//上传后回调
	    
	    	
	    });
		
};
