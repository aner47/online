 /**
 * Created by DEV2 on 2017/2/8.
 */
 define(function(){
  var a = {
    year: function (options) {
      var _default = {
        validate:'',
        change:function(){},
      	defaultC:1,
      	deafultCount:5,
      	moveValue:1//+为有移动，-为左移动
      }
      options = $.extend(_default,options);
      var c = options.defaultC;
      var count = options.deafultCount;
      var year=new Date().getFullYear();

      var $this = $(this);
      $.extend(this, {
        getYearValue: function(){
          return $('input',this).val();
        },
        setYearValue: function(value){
          if (value) {
            $('#_caption',this).html(value + "年");
            $('input',this).data('val',value).val(value).blur();
          }else{
            $('#_caption',this).html('');
            $('input',this).data('val','').val('').blur();
          };
        },
      });
      var name = options.name || "_name";
      var str = '<span id="_caption"></span><input type="text" data-val="" style="width:0px;height:1px;padding:0px;margin:0px;border:0px;" name="'+name+'" '+ options.validate +'>' ;
      $(this).html(str);
      if(options.defaultValue){
       $('#_caption',$this).html(options.defaultValue + "年");
       $('input',$this).data('val',options.defaultValue).val(options.defaultValue);
     }
     $('input',$this).on('change',function(){
      var val = $(this).val();
      var oldVal = $(this).data('val');
      console.log(val,oldVal);  
      if (val != oldVal) {
        $(this).val(oldVal);
      };
      $(this).blur();
    })

     $(this).click(function(e){
      var $this = $(this);
      var name = options.name || "_name";
      var str = '<span id="_caption"></span><input type="text" data-val="" style="width:0px;height:1px;padding:0px;margin:0px;border:0px;" name="'+name+'" '+ options.validate +'>  <div class="year-container"><ul><li id="year-left"><i class = "iconfont icon-dansanjiaoleft"></i></li> ' ;
      for(var i = (year-count*c)+options.moveValue ; i < (year - count * (c-1))+options.moveValue ; i++){
        str = str +  '<li data-id ='+i+'>'+i+'年</li>';
      }

      str = str +  '<li id ="year-right"><i class = "iconfont icon-dansanjiaoright"></i></li></ul>';
              //str = str + '  <div style="clear: both"></div>  <button id="year-left">《</button> <button id ="year-right">》</button></div>';

              $(this).html(str);
              $('input',$this).blur();
              if(options.defaultValue){
                $('#_caption',$this).html(options.defaultValue + "年");
                $('input',$this).data('val',options.defaultValue).val(options.defaultValue);
              }
              e.stopPropagation();
              $('.year-container ul ',this).on("click","li",function(e){
                $('#_caption',$this).html($(this).html());
                var yvalue =  ( $('input',$this).val());
                $('input',$this).data('val',$(this).attr("data-id")).val($(this).attr("data-id")).blur();
                if(yvalue != $(this).attr("data-id")){
                 options.change($(this).attr("data-id"),arguments);
                }
                $('.year-container',$this).hide();
                e.stopPropagation();
              })
              $('input',$this).on('change',function(){
                var val = $(this).val();
                var oldVal = $(this).data('val');
                console.log(val,oldVal);  
                if (val != oldVal) {
                  $(this).val(oldVal);
                };
                $(this).blur();
              })
              $("#year-left",$this).click(function(){
                $this.find('ul').html('');
                c++ ;
                var strli = '';
                for(var i = (year-count*c) ; i < (year - count * (c-1)) ; i++){
                  str = str +  '<li data-id ='+i+'>'+i+'年</li>';
                }
                $this.find('ul').html(str);
                e.stopPropagation();
              });
              $("#year-right",$this).click(function(){
                $this.find('ul').html('');
                c-- ;
                var strli = '';
                for(var i = (year-count*c) ; i < (year - count * (c-1)) ; i++){
                  str = str +  '<li data-id ='+i+'>'+i+'年</li>';
                }
                $this.find('ul').html(str);
                e.stopPropagation();
              })
              $(document).click(function(e){
                $('.year-container',this).hide();
              })
            })
 return this;

}
}
$.extend($.fn,a)

})



