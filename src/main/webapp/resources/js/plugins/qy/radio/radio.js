(function() {
    $.fn.extend({
        radio: function (options) {
            var optionsData;
            var currentValue;
            var currentCaption;
            options = options || {};
            var _defualt = {
            	skin:"radio",
                zIndex: 1,
                lable:"",
                data:[],
                selected:true,
                change:function(){}
            };
            options = $.extend(_defualt, options);
            var dom = $('<div></div>')
            var $this = this;
            var label = dom.clone().addClass(options.skin+"_label").html(options.label); 
            $this.append(label)
            for(var i = 0 ; i < options.data.length;i++){
            	var values = options.data[i];
            	var currentDom = dom.clone();
            	currentDom.addClass(options.skin+'_type');
            	currentDom.attr("data-value",values.value);
            	currentDom.html(values.showValue);
            	if(i == 0 ){
            		currentDom.addClass(options.skin+"_first_el");
            		if(options.selected){
            			currentDom.addClass(options.skin+"_select");
            			currentValue = values.value;
            		}
            	}
            	if(i==options.data.length-1){
            		currentDom.addClass(options.skin+"_end_el")
            	}
            	$this.append(currentDom);
            }
            
            $this.append(dom.addClass(options.skin+"_clear"))
            $this.on("click","."+options.skin+"_type",function(){//注册点击事件
            	var selectDom = $(this);
            	var a  = selectDom.hasClass(options.skin+"_select");
     	   	    $this.find('.'+options.skin+'_type').removeClass(options.skin+"_select");
     	   	    a?"":selectDom.addClass(options.skin+"_select");
            	var value =a?"":selectDom.attr("data-value");
            	if(value != currentValue){
            		currentValue = value;
            		currentCaption = selectDom.html();
            		options.change(value);
            		
            	}
     	   	    
            })
           var method={
        	   getSelectValue:function(){ return currentValue;},
        	   getSelectCaption:function(){ return currentCaption;},
        	   setSelectValue:function(value){
        		   currentValue = value;
        	   	   $('.'+options.skin+'_type').removeClass(options.skin+"_select");
        	   	   $("[data-value='"+value+"']").addClass(options.skin+"_select");
        	   }
        	   
           } 
            $.extend($this,method);
            return $this;
            
        }
         

    })
	
})()