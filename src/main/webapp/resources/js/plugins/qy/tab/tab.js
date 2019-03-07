/**
 * Created by DEV2 on 2017/4/12.
 */
(function () {
    $.fn.extend({tab:function(options){
        options = options || {};
        var _defualt = {

        };
        options = $.extend(_defualt, options);
        var $this = this;
        var $t = $('<ul class="nav-tab"></ul>');//头容器
        var $content = $('<ul class="tab-content"></ul>');//内容容器
        $.each(options.tabs,function(i,v){
            var $tab = $('<li  data-tab='+i+'>'+ v.caption+'</li>');
            if(v.iframe){
                $content.append("<iframe width='100%' height='100%'  frameborder='0' marginwidth='0' marginheight='0' data-tab="+i+" src=''></iframe>")
            }else if(v.html){
                $content.append($tab.clone().attr("data-load",1).html(v.html));
            }else{
                $content.append($tab.clone().attr("data-load",0).html(''));
            }
            $t.append($tab)
        })
        this.append($("<div class = '_tab_contain'></div>").append($t).append($content));
        $('.nav-tab',$this).on('click','li',function(){
            $('.nav-tab li',$this).removeClass("active");
            $('.tab-content>*',$this).css("display","none");
            var dataTab = $(this).attr("data-tab");
            $content.find("[data-tab = '"+dataTab+"']").css("display","block");
            $(this).addClass("active");
             if(options.tabs[dataTab].url&& $content.find("[data-tab = '"+dataTab+"']").attr('data-load')!=2){
                 if(options.tabs[dataTab].iframe){
                     $content.find("[data-tab = '"+dataTab+"']").attr("data-load",2).attr("src",options.tabs[dataTab].url);
                 }else{
                     var param = options.tabs[dataTab].param||{};
                     qy.ajax({
                         url:options.tabs[dataTab].url,
                         data:param,
                         callBack:function(htmlContent){
                             $content.find("[data-tab = '"+dataTab+"']").attr("data-load",2).html(htmlContent.responseText);
                         }
                     })
                 }

            }
            if(typeof options.tabs[dataTab].handle === 'function'){
                options.tabs[dataTab].handle(arguments);
            }
        })

    }})

})()