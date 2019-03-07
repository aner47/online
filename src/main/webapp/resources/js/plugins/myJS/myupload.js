$.fn.MyUpload = {
		upload:function (opts){
			var obj=window.location;
			var contextPath=obj.pathname.split("/")[1]; 
			var defaultOpts = {
				url:getRootPath()+"/common/file/upload.jhtml?fileType=image"
			}
			var handle = $('<div style = "display:none"></div>');
			var handle_id = opts.id+"_handle"
			handle.attr('id',handle_id)
			$("#"+opts.id,'form').parent().append(handle);
			$("#"+opts.id,'form').focus(function(){
				$('#'+handle_id,'form').find('input.webuploader-element-invisible').click();
			})
		
			opts = $.extend(defaultOpts,opts);
			uploader = WebUploader.create({
//	 	        文件接收服务端。
			server: opts.url,
//	 	        选择文件的按钮。可选。
	        pick: {
	        	id : '#'+handle_id,
	        	multiple : false
	        },
//	 	     图片质量，只有type为`image/jpeg`的时候才有效。
	        quality: 90,
	        // 只允许选择文件，可选。
	        accept: {
	            title: 'Images',
	            extensions: 'gif,jpg,jpeg,bmp,png',
	            mimeTypes: 'image/*'
	        },
	        auto:true
	    });
		// 当有文件添加进来的时候
	    uploader.on('fileQueued', function(file) {
//	    	var	$img = $('#' + $list.attr('target-img')).attr('fileName',file.name).siblings().show().find('img');
	        // 创建缩略图
/*	        uploader.makeThumb(file, function(error,src) {
	        	error ? $img.replaceWith('<span>不能预览</span>') : $img.attr('src', src);
	        }, 100, 100 );*/
	        
	    }).on('uploadAccept', function(file, data){
	    	$('#'+opts.id,'form').val(data.url)
/*	    	var $element = $("#"+$list.attr("target-img"));
	    	$element.prev("input:hidden").val(data.url);*/
	    	
	    });
		}

		
		
}
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

