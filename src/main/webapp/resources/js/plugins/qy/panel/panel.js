var qy = qy||{};
qy.panel = function(opts) {
	var defaultOpts = {
		width : "444px",
		height : "666px",
		btn:["确认","返回"],
		btn4:function(){}
	}
	opts = $.extend(defaultOpts,opts);
	
	$.get(opts.url, opts.data, function(str) {
		var openOpts = {
			title : opts.title,
			offset : "50px",
			area : [ opts.width, opts.height ], // 宽高
			type : 1,
			content : str, // 注意，如果str是object，那么需要字符拼接。
			shade: 0.6 //遮罩透明度
			,maxmin: true //允许全屏最小化
			,anim: 1 //0-6的动画形式，-1不开启
			,isOutAnim: false
			,scrollbar : false,
			cancel : function() {
				if (typeof opts.cancel === 'function')
					opts.cancel();
			},
			yes : function(index) {
				if (typeof opts.yes === 'function')
					opts.yes(index);
			},
			btn2:opts.btn2,
			btn4:opts.btn4,
			btn3:opts.btn3,
			success : function() {
			}
		}
		openOpts.btn = opts.btn;
		layer.open(openOpts);
	});
}
qy.save=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	var option={
		type: 1 ,
		anim: -1,
		isOutAnim: false,
		title:'',
		width: '520px',
		height: '234px',
		content: '<style>.'+layuiClass+'{width: 520px !important;height: 234px;}body .layui-layer-page .layui-layer-btn{bottom: 22px;text-align: center;padding-right: 0;}.layui-anim,.layui-layer-content{width: 520px !important;height: 234px;}.layui-layer-btn a{width: 166px;height: 47px;line-height: 45px;font-size: 19px;}.layui-layer-content h1{margin-top: 61px;}</style><h1 id="'+id+'">'+opts.title+'</h1><script type="text/javascript">$("#'+id+'").parents(".layui-layer").addClass("'+layuiClass+'");</script>' ,
		// btn: (opts.btn ? opts.btn:['删除','取消']) ,
		btn: ['确定','取消'],
		yes: opts.yes ,
		btn2: function(index){
			layer.close(index);
		} ,
		cancel:function(index){
			layer.close(index);
		}
	}
	layer.open(option);
}
qy.del=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	var option={
		type: 1 ,
		anim: -1,
		isOutAnim: false,
		title:'',
		width: '520px',
		height: '234px',
		content: '<style>.'+layuiClass+'{width: 520px !important;height: 234px;}body .layui-layer-page .layui-layer-btn{bottom: 22px;text-align: center;padding-right: 0;}.layui-anim,.layui-layer-content{width: 520px !important;height: 234px;}.layui-layer-btn a{width: 166px;height: 47px;line-height: 45px;font-size: 19px;color: #ffffff;box-shadow: 0 2px 2px #d8381e;background-color: #fa4122;border: 1px solid #fd715a;}.layui-layer-btn a:hover {background-color: #f5290e;}.layui-layer-content h1{margin-top: 61px;}</style><h1 id="'+id+'">'+opts.title+'</h1><script type="text/javascript">$("#'+id+'").parents(".layui-layer").addClass("'+layuiClass+'");</script>' ,
		// btn: (opts.btn ? opts.btn:['删除','取消']) ,
		btn: ['删除','取消'],
		yes: opts.yes ,
		btn2: function(index){
			layer.close(index);
		} ,
		cancel:function(index){
			layer.close(index);
		}
	}
	layer.open(option);
}
qy.suc=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	var option={
		type: 1 ,
		anim: -1,
		isOutAnim: false,
		title:'',
		width: '520px',
		height: '331px',
		content: '<style>.'+layuiClass+'{width: 520px !important;height: 331px;}.layui-layer-page .layui-layer-btn{bottom: 22px;}.layui-anim,.layui-layer-content{width: 520px !important;height: 331px;}.layui-layer-btn a{width: 166px;height: 47px;line-height: 45px;font-size: 19px;}.layerIcon{font-family: "iconfont" !important;color: #00d82d;font-size: 71px;background-color: transparent;transform:inherit;-ms-transform:inherit;-moz-transform:inherit;-webkit-transform:inherit;-o-transform:inherit;}</style><div class="layerIcon">&#xe6c0;</div><h1 id="'+id+'">'+opts.title+'</h1><script type="text/javascript">$("#'+id+'").parents(".layui-layer").addClass("'+layuiClass+'");</script>' ,
		// btn: (opts.btn ? opts.btn:['删除','取消']) ,
		btn: ['知道了'],
		yes: function(index){
			layer.close(index);
		} ,
		cancel:function(index){
			layer.close(index);
		}
	}
	layer.open(option);
}
qy.fail=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	var option={
		type: 1 ,
		anim: -1,
		isOutAnim: false,
		title:'',
		width: '520px',
		height: '331px',
		content: '<style>.'+layuiClass+'{width: 520px !important;height: 331px;}body .layui-layer-page .layui-layer-btn{bottom: 22px;text-align: center;padding-right: 0;}.layui-anim,.layui-layer-content{width: 520px !important;height: 331px;}body .layui-layer-btn a:last-child{width: 166px;height: 47px;line-height: 45px;font-size: 19px;margin-right: 0;}body .layui-layer-btn a:last-child:hover{line-height:45px;}body .layui-layer-btn a:last-child:active{line-height:45px;}.layerIcon{background-color: #fa4122;transform:inherit;-ms-transform:inherit;-moz-transform:inherit;-webkit-transform:inherit;-o-transform:inherit;}</style><div class="layerIcon">!</div><h1 id="'+id+'">'+opts.title+'</h1><script type="text/javascript">$("#'+id+'").parents(".layui-layer").addClass("'+layuiClass+'");</script>' ,
		// btn: (opts.btn ? opts.btn:['删除','取消']) ,
		btn: ['知道了'],
		yes: function(index){
			layer.close(index);
		} ,
		cancel:function(index){
			layer.close(index);
		}
	}
	layer.open(option);
}
qy.sure=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	var option={
		type: 1 ,
		anim: -1,
		isOutAnim: false,
		title:'',
		// width: '264px',
		// height: '132px',
		content: '<style>.'+layuiClass+'{width: 264px !important;height: 132px;}body .layui-layer-page .layui-layer-btn{bottom: 0px;position: absolute;text-align: center;padding: 0;}.layui-layer-content{width: 264px;height: 132px;overflow:hidden;}body .layui-layer-page .layui-layer-content{overflow:visible;}.layui-layer-setwin{display:none;}body .layui-layer-btn a{width: 131px;height: 39px;line-height: 39px;font-size: 16px;padding: 0;margin-right: 0px;float: left;border: 0;color: #ffffff;background-color: #4fc1ee;border-radius: 0px;}body .layui-layer-btn a:hover{line-height: 39px;padding: 0;}body .layui-layer-btn a:active{line-height: 39px;padding: 0;}body .layui-layer-btn a:last-child{border: 0;color: #ffffff;background-color: #4fc1ee;}body .layui-layer-btn a:last-child:hover{line-height: 39px;padding: 0;}body .layui-layer-btn a:last-child:active{line-height: 39px;padding: 0;}body .layui-layer-btn a.layui-layer-btn0{margin-right: 2px;}.layui-layer-content h1{margin-top: 0px;position: absolute;top: 0px;bottom: -69px;margin: auto;font-size: 18px;display: table;width: 100%;padding: 0 5px;}</style><h1 id="'+id+'">'+opts.title+'</h1><script type="text/javascript">$("#'+id+'").parents(".layui-layer").addClass("'+layuiClass+'");</script>' ,
		// btn: (opts.btn ? opts.btn:['删除','取消']) ,
		btn: ['确定','取消'],
		yes: opts.yes ,
		btn2: function(index){
			layer.close(index);
		} ,
		cancel:function(index){
			layer.close(index);
		}
	}
	layer.open(option);
}

qy.suc2=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	layer.msg('<style>.layui-layer.layui-layer-dialog { width: 272px;height: 71px;border: 2px solid #a3e1fa;box-shadow: none;}.layui-layer-msg .layui-layer-padding {padding: 0 22px;position: absolute;margin: auto 0;width: 192px;left: 76px;top: 0;bottom: 0;display: table;}.layui-layer-msg .layui-layer-content .layui-layer-ico {margin: auto;left: -76px;width: 76px;height: 55px;top: -100px;bottom: -100px;border-right: 1px solid #ececec;}</style>'+opts.title,{icon :'suc',time:1200});
}

qy.suc3=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	layer.msg('<style>.layui-layer.layui-layer-dialog { width: 272px;height: 71px;border: 2px solid #a3e1fa;box-shadow: none;}.layui-layer-msg .layui-layer-padding {padding: 0 22px;position: absolute;margin: auto 0;width: 192px;left: 76px;top: 0;bottom: 0;display: table;}.layui-layer-msg .layui-layer-content .layui-layer-ico {margin: auto;left: -76px;width: 76px;height: 55px;top: -100px;bottom: -100px;border-right: 1px solid #ececec;}</style>'+opts.title,{icon :'suc3',time:1200});
}

qy.fail2=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	layer.msg('<style>.layui-layer.layui-layer-dialog { width: 272px;height: 71px;border: 2px solid #a3e1fa;box-shadow: none;}.layui-layer-msg .layui-layer-padding {padding: 0 22px;position: absolute;margin: auto 0;width: 192px;left: 76px;top: 0;bottom: 0;display: table;}.layui-layer-msg .layui-layer-content .layui-layer-ico {margin: auto;left: -76px;width: 76px;height: 55px;top: -100px;bottom: -100px;border-right: 1px solid #ececec;}</style>'+opts.title,{icon :'fail',time:1200});
}
// .layui-anim{top:100px !important;bottom: calc(100% - 437px);}
qy.tableExport=function(opts){
	var id = 'i' + (new Date()).getTime();
	var layuiClass = 'p' + id;
	var option={
		type: 1 ,
		anim: -1,
		isOutAnim: false,
		title:'',
		// width: '520px',
		// height: '337px',
		content: '<style>.'+layuiClass+'{width: 520px !important;height: 337px;}.layui-layer-page .layui-layer-btn{bottom: 22px;}.layui-anim,.layui-layer-content{width: 520px !important;height:337px;}.layui-layer-btn a{width: 166px;height: 47px;line-height: 45px;font-size: 19px;}.layui-layer-content h1{margin-top: 89px;}.layui-layer-content h2{margin-top: 26px;font-size: 18px;text-align: center;}</style><h1 id="'+id+'">'+opts.title1+'</h1><h2>'+opts.title2+'</h2><script type="text/javascript">$("#'+id+'").parents(".layui-layer").addClass("'+layuiClass+'");</script>' ,
		// btn: (opts.btn ? opts.btn:['删除','取消']) ,
		btn: ['确定','取消'],
		yes: opts.yes ,
		btn2: function(index){
			layer.close(index);
		} ,
		cancel:function(index){
			layer.close(index);
		}
	}
	layer.open(option);
}