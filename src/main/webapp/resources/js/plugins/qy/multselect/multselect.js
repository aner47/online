/**
 * Created by DEV2 on 2017/2/4.
 */
define(["jquery"],function(){
	Array.prototype.contains=function(obj) {
		  var index=this.length;
		  while (index--){
		    if(this[index]==obj){
		      return true;
		    }
		  }
		  return false;
	}
    $.fn.extend({multselect:function(options){
        options = options||{};
        var _defualt={
            	url :base+"/common/findCodeDataByName.jhtml",	 
                zIndex:1,
                name:"_default_name",
                initLoad:true,
                key:"code",
                value:"codeValue"
             };
        options = $.extend(_defualt,options);
        var $this = this;
        $this.defaultLoad = true;//初始化加载
        this.addClass("multselect-container")
        var method = {
            _simplleDatarRender:function(options){
                var str = '<div id = "contain1"><h1>所有数据</h1> <ul>'
                $.each(options.simpleData,function(k,v){
                    str = str +'<li data-value='+k+'>'+v+'</li>';
                })
               str = str +'</ul></div>';
                str = str + '<div class="buttonContainer"><span class="criclebtn" id = "id1"><i class="iconfont icon-dansanjiaoright"></i></span><span  class="criclebtn" id = "id2"><i class="iconfont icon-dansanjiaoleft"></i></span><span  class="criclebtn" id = "id3"><i class="iconfont icon-shuangsanjiaoright"></i></span><span  class="criclebtn"  id = "id4"><i class="iconfont icon-shuangsanjiaoleft"></i></span> </div>';
                str = str + '<div id="contain2"><h1>已选择数据</h1><ul></ul><span class="clear"></span></div>';
                $this.html(str);
                $this._events();
            },
            init:function(){
				var str = '<div id = "contain1"><h1>所有数据</h1> <ul>';
                str = str + "</ul></div>";
                str = str + '<div class="buttonContainer"><span class="criclebtn" id = "id1"><i class="iconfont icon-dansanjiaoright"></i></span><span  class="criclebtn" id = "id2"><i class="iconfont icon-dansanjiaoleft"></i></span><span  class="criclebtn" id = "id3"><i class="iconfont icon-shuangsanjiaoright"></i></span><span  class="criclebtn"  id = "id4"><i class="iconfont icon-shuangsanjiaoleft"></i></span> </div>';
                str = str + '<div id="contain2"><h1>已选择数据</h1><ul></ul><span class="clear"></span></div><input type="hidden" name="'+options.name+'" >';
                $this.html(str);
                $this._events();
                if(options.initLoad)$this.loadData();
            },
            clear:function(){
            	$('#contain2 ul',this).html('');
            	$('#contain1 ul',this).html('');
            	$("input[name='"+options.name+"']").val('');
            },
            loadData:function(param){
            	param = param || {};
            	var data = {name:$this.attr("data-code")};
            	data = $.extend(param,data);
            	data = $.extend(options.params,data);
            	
     			qy.ajax({
     				url:options.url,
     				data:data,
     				callBack:function(data,errorCode){
     					if(errorCode != '999999') return ;
     					if(!data) return ;
     					var $contain1 = $this.find('#contain1 ul',$this);
     					var str = '';
     					 $.each(data,function(k,v){
     	                    str = str +'<li data-value='+v[options.key]+'>'+v[options.value]+'</li>';
     	                })
     	               $contain1.html(str);
     					$('#contain2 ul',$this).html('')
     					 $("input[name='"+options.name+"']").val('')	 
     				   if(options.defaultValue && $this.defaultLoad){
     					    var defualtLi = [];
                            var selectLi = $('#contain1 ul li',$this);
                            console.log(options.defaultValue);
                            $this.defaultLoad = false;
                            $.each(selectLi,function(i,v){
                            	v = $(v);
                            	console.log(v.attr('data-value'));
                            	if(options.defaultValue.contains(v.attr('data-value'))){
                            		v.remove();
                            		v.removeClass('selected');
                            		$('#contain2 ul',$this).append(v);
                            	}
                            })
                            $("input[name='"+options.name+"']").val(options.defaultValue.join(","))	 
     				   } 

     					 
     				}
     			});
            	
            },
            getSelectValue:function(){
                var key = [];
                $.each($('#contain2 ul li',$this),function(k,v){
                    key.push($(v).attr("data-value"));
                })
                return key;
            },
        
            _events:function(){
                $('#contain1 ul,#contain2 ul',this).on("click","li",function(){
                    $(this).toggleClass("selected")
                });
                $('#id1',this).click(function(){
                    var selectLi = $('#contain1 ul .selected');
                    selectLi.remove();
                    selectLi.removeClass('selected');
                    $('#contain2 ul',$this).append(selectLi);
                    var value =  $this.getSelectValue();
                    $("input[name='"+options.name+"']").val(value.join(","))
                });
                $('#id2',this).click(function(){
                    var selectLi = $('#contain2 ul .selected');
                    selectLi.remove();
                    selectLi.removeClass('selected');
                    $('#contain1 ul',$this).append(selectLi);
                    var value =  $this.getSelectValue();
                    $("input[name='"+options.name+"']").val(value.join(","))
                });
                $('#id3',this).click(function(){
                    var selectLi = $('#contain1 ul li');
                    selectLi.remove();
                    selectLi.removeClass('selected');
                    $('#contain2 ul',$this).append(selectLi);
                    var value =  $this.getSelectValue();
                    $("input[name='"+options.name+"']").val(value.join(","))
                });
                $('#id4',this).click(function(){
                    var selectLi = $('#contain2 ul li');
                    selectLi.remove();
                    selectLi.removeClass('selected');
                    $('#contain1 ul',$this).append(selectLi);
                    var value =  $this.getSelectValue();
                    $("input[name='"+options.name+"']").val(value.join(","))
                });
            }
        };
        $.extend(this,method);

        if(options.simpleData){
            this._simplleDatarRender(options);
        }else{
        	this.init();
        }
   
        return this;

    }})



})