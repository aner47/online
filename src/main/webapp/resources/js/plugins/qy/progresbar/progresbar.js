/***进度条***/
var ProgressBar = function(options){
	options = options||{};
	var defaultConfig={
			skin:'progresbar',
			width:200,
			height:20,
			url:base+"/admin/excelexport/downloadSchedule.jhtml",
			interval:1000,
			complete:function(){}
	}
	
	var options = $.extend(defaultConfig,options);
	var coverId;
	var content;
	this.getCoverId = function(){
		return coverId;
	}
	
	/**
	 * 产生一个唯一ID
	 * @returns
	 */
	this.generalKey = function() {
		  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		    var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
		    return v.toString(16);
		 });
	}
	
	this.clear = function(){
		this.$cover?this.$cover.remove():"";
		this.$content?this.$content.remove():'';
	}
	
	this.show = function(){//创建一个遮罩
		this.clear();
		coverId = 	this.generalKey();
		console.log(coverId);
		this.$cover = $("<div class = "+options.skin+"_cover id="+coverId+"></div>");
		$("body").append(this.$cover);
		this.$content = $('<div class="'+options.skin+'_degree_contain"><div class="'+options.skin+'_degree"></div> </div>')
		$("body").append(this.$content);
	}
	
	this.updateDegree = function(degreeObj){//创建一个遮罩
		if(degreeObj.scheduleStatus == 'complete'){
			this.clear();
			window.clearInterval(this.timers);
			options.complete();
		}else if(degreeObj.scheduleStatus == 'error'){
			this.clear();
			window.clearInterval(this.timers);
			this.content.html(degreeObj.message);
		}else if(degreeObj.scheduleStatus == 'normal'){
			this.$content.find("."+options.skin+"_degree").css("width", Math.ceil(degreeObj.degree )+"%");
			if( Math.ceil(degreeObj.degree) == 0){
				this.$content.find("."+options.skin+"_degree").html("请稍等正在为您加载数据！");
			}else{
				this.$content.find("."+options.skin+"_degree").html( Math.ceil(degreeObj.degree)+"%");
			}
			
		}

		
	}
	var $this = this;
	this.query = function(){
		this.show();
		function queryDegree(){
			qy.ajax({
				url:options.url,
				loading:false,
				data:{scheduleKey:$this.getCoverId()},
				callBack:function(data){
					$this.updateDegree(data.entity);
				}
			})
			
		}
		this.timers = window.setInterval(queryDegree,options.interval); 
		
	}
	
	
	
	
}
