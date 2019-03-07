/**
 * Created by DEV2 on 2017/3/3.
 */
define(["jquery", "layer", "webuploader"], function (jq, lay, wu) {
    return {
        upload: function (options) {
        	
        	options = options|| {};
        	var postfixType = options.postfixType;
        	
        	$this = this;
        	this.acceptCallBack = this.acceptCallBack||options.acceptCallBack||function(){};
        	$("#_defualt_upload_handle","body").remove();
            $("body").append('<div id = "_defualt_upload_handle"  style="display: none;" > </div>');
            var uploader = wu.create({
                server: base+"/common/file/upload.jhtml",
                pick: {
                    id: '#_defualt_upload_handle',
                    multiple: false
                },
                formData: {  
                	postfixType:postfixType
                },  
                auto: true,
                duplicate:true
            });
            var load;
            
            // 当有文件添加进来的时候
            uploader.on('fileQueued', function (file) {
                load = layer.load(2);//loading
            }).on('uploadAccept', function (file, data) {
            	if(typeof $this.acceptCallBack === 'function'){
            		$this.acceptCallBack(file,data);
            	}
                layer.close(load);
            });
          
            if(options.element){
            	if(options.parent){
            		$(options.parent).on("click",options.element,function () {
            			typeof options.beforeUpload === 'function'? options.beforeUpload(arguments):"";
            			$("#_defualt_upload_handle").find('input.webuploader-element-invisible').click();
            			typeof options.afterUpload === 'function'? options.afterUpload(arguments):"";
            		})
            	}else{
            		$(options.element).unbind()
            		$(options.element).click(function(){
            			typeof options.beforeUpload === 'function'? options.beforeUpload(arguments):"";
            			$("#_defualt_upload_handle").find('input.webuploader-element-invisible').click();
            			typeof options.afterUpload === 'function'? options.afterUpload(arguments):"";
            		})
            	}
            }

        },
        setAcceptCallBack:function(acceptCallBack){
        	this.acceptCallBack = acceptCallBack;
        }
    }
})