/**
 * Created by qianyue on 2016-10-19.
 */

(function(){
    $.fn.extend({
         select:function(options){
        	 options = options||{};
             var _defualt={
            	url :base+"/common/findCodeDataByName.jhtml",	 
                zIndex:1,
                name:"_default_Name",
                param:"",
                empty:true
             };
             options = $.extend(_defualt,options);
             var $select = this;
             var width = $select.outerWidth(false);
             $.extend(this,{
                 _simplleDatarRender:function(opts){
                     var str='<div class="_s_contain1" style="z-index:'+opts.zIndex+' ;">'+
                     '<div class="_s1"> <span class="_caption"></span><i class="iconfont icon-xiaodansanjiao"></i> </div>'+
                     '  <input type="hidden" name='+opts.name+' />'+
                     '  <div class="_s2" style="">';
                         $.each(opts.simpleData,function(key,value){
                            str+= '<div class="_option" value="'+ key+'">'+value+'</div>';
                         })
                     str+= '   </div>';
                     str+='</div>';
                     $select.css("cursor", "pointer");
                     $select.hasClass("._s_contain")?"": $select.addClass("_s_contain");
                     $select.html(str);
                     if(opts.defaultValue){
                         var options =  $select.find("._option[value='"+opts.defaultValue+"']");
                         $('._caption',$select).html(options.html());
                         $('input',$select).val(opts.defaultValue);
                     }
                 },
                 _dataCodeRender:function(opts){
         			var _defaultOption = {
        					key :"code",
        					value:"codeValue"
        			}
         			opts = $.extend(opts,_defaultOption);
         			var data = {name:$select.attr("data-code"),param:opts.param};
         			qy.ajax({
         				url:opts.url,
         				data:data,
         				callBack:function(data){
         					if(!data) return ;
                            var str='<div class="_s_contain1" style="z-index:'+opts.zIndex+' ;">'+
                            '<div class="_s1"><span class="_caption"></span><i class="iconfont icon-xiaodansanjiao"></i>  </div>'+
                            '  <input type="hidden" name='+opts.name+' />'+
                            '  <div class="_s2" style="">';
                            if(opts.empty){str += '<div class="_option" value=""></div>'}
         					$.each(data,function(k,v){
         						  str+= '<div class="_option" value="'+ v.code+'">'+v.codeValue+'</div>';
         					})
                            str+= '   </div>';
                            str+='</div>';
                            $select.css("cursor", "pointer");
                            $select.hasClass("._s_contain")?"": $select.addClass("_s_contain");
                            $select.html(str);
                            if(opts.defaultValue){
                                var options =  $select.find("._option[value='"+opts.defaultValue+"']");
                                $('._caption',$select).html(options.html());
                                $('input',$select).val(opts.defaultValue);
                            }

         				}
         			})
                 },
                 loadData:function(param){
                	 options.param=param;
                	 this._dataCodeRender(options);
                 },
                 getSelectValue:function(){
                	 return $('input',this).val();
                 },
                 _events:function(){
                     $(document).click(function(){
                         $('._s2').hide();
                     })
                     $select.click(function(event){
                         if($("._s2",$select).is(":hidden")){
                             $("._s2",$select).show();
                         }else{
                             $('._s2',$select).hide();
                         }
                         event=event||window.event;
                         event.stopPropagation();
                     });
                     $select.on("click","._option",function(event){
                         var el = $(this);
                         var caption = el.html();
                         var value = el.attr('value')
                         $(this).parents("._s2").hide();
                         var contain = $(this).parents("._s_contain");
                         $('._caption', contain).html(caption);
                         if (typeof options.change === 'function') {
                             var yvalue = $('input[name = "'+options.name+'"]', contain).val();
                             if (yvalue != value) {
                                 options.change(event, value)
                             }
                         }
                         $('input[name="' + options.name + '"]', contain).val(value);
                         event.stopPropagation();
                     })
                 }
             });
             if(options.simpleData){
            	 this._simplleDatarRender(options);
             }else{
            	 this._dataCodeRender(options);
             }
             this._events();
             return this;
         }

    })

})()
