/**
 * Created by qianyue on 2016-10-19.
 */

(function () {
    $.fn.extend({
        select: function (options) {
        	var optionsData;
        	var selectValue;
        	var selectCaption;
            options = options || {};
            var _defualt = {
                url: base + "/common/findCodeDataByName.jhtml",
                zIndex: 1,
                name: "_default_Name",
                code: "code",
                codeValue: "codeValue",
                otherValue:"otherValue",
                param: "",
                async: true,               //是否异步，主要用于联动的回写
                empty: true,
                immutable: false,           //是否不可改变
                multselect: false,			//是否可多选
                filter: true,
                selected: false,
                disabled: false,
                validate: '',               // validate 插件应用引入属性部分
                isCustom: false,     // 是否可输入自定义内容
                initLoad:true,
                placeholder:'',
                change:function(){},
                clearEvent:function(){},
                dataFilter:function(data){return true;}
            };
            var loadFlag = false;
            options = $.extend(_defualt, options);
            options.defaultValue === 0 ? options.defaultValue = '0':'';
            var $select = this;
            var allData;
            var selectCount = 0;
            var width = $select.outerWidth(false);
            $.extend(this, {
                _simplleDatarRender: function () {
                	var data = [];
                	$.each(options.simpleData,function(key,value){
                		var c = {};
                		c[options.code] = key;
                		c[options.codeValue] = value + '';
                		data.push(c);
                	})
                	optionsData = data;
                	init();
                    if(!options.initLoad){
                    	return;
                    }
                	initOption(optionsData);
                },
                _dataCodeRender: function () {
                    var _defaultOption = {
                        key: "code",
                        value: "codeValue"
                    }
                    options = $.extend(options, _defaultOption);
                    init();
                    if(!options.initLoad){
                    	return;
                    }
                    var data = {name: $select.attr("data-code"), param: options.param};
                    queryData(data);

                },
                loadData: function (param) {
                	loadFlag = false;
                    options.param = param;
                    var data = {name: $select.attr("data-code"), param: options.param};
                    queryData(data);
                },
                loadDataSimpleData:function(simpleData){
                    var data = [];
                    $.each(simpleData,function(key,value){
                    	var b = {};
                    	b[options.code] = key;
                    	b[options.codeValue] = value + '';
                    	data.push(b);
                    })
                    options.data = data;
                	initOption(data);
                },
                getSelectValue: function () {
                    return $('input[name="'+options.name+'"]', this).val();
                },
                getSelectOtherValue: function () {
                    var key =  $('input[name="'+options.name+'"]', this).val();
                    var arr = [];
                    for(var i in allData){
                        if (options.multselect) {
                            if(allData[i][options.code] == key) arr.push($.parseJSON(allData[i][options.otherValue] || null));
                        }else{
                            if(allData[i][options.code] == key)return $.parseJSON(allData[i][options.otherValue] || null);
                        };
                    }
                    if (options.multselect){
                        return arr;
                    }
                },
                clearSelectValue:function(){
                    $('input[name="'+options.name+'"]', this).data('val','').val('').blur();
                    $('._caption', this).html(options.placeholder);
            		$select.find("._option").removeClass("selected");
                },
                getSelectNameOfValue: function(value){
                    return $('._s2 [value='+value+']',this).html();
                },
                setSelectValue: function (value) {
                	function setValue(){
                		selectCount++;
                		if(loadFlag){
                			$('._s2', $select).hide();
                			if ($('._s2 [value='+value+']',$select).length) {
                				$('._s2 [value='+value+']',$select).click();
                			}else{
                				$('._caption',$select).html('');
                				value = '';
                				if (typeof options.change === 'function') {
                					var yvalue = $('input[name = "'+options.name+'"]','._s_contain').val();
                					if (yvalue != value) {
                						options.change('', value,allData);
                					}
                				}
                				$('input[name="' + options.name + '"]',$select).data('val',value).val(value).blur();
                			};
                			if (selectCount === 20) {
                				window.clearInterval(timers);
                			}
                		}
                	}
                	var timers = window.setInterval(setValue,200)

                },
                getSelectCaption:function(){
                	return $('._caption', this).html();
                },
                clear:function(initClear){
                    if (!initClear) {
                        options.defaultValue = '';
                        $select.attr("data-defaultValue",'');
                    };
                	$('input[name="'+options.name+'"]', this).data('val','').val('').blur();
                	$select.find('._caption').html('');
                	$select.find("._s2").find('.option-value').remove();
                	if(!initClear && typeof options.clearEvent==='function' )
                		options.clearEvent();
                },
                _events: function () {
                    $(document).click(function () {
                        $('._s2').hide();
                    })
                    if(options.immutable){
                    	return;
                    }
                    $select.click(function (event) {
                    	$('._s2').hide();
                    	$($select).show();
                        if ($("._s2", $select).is(":hidden")) {
                            $("._s2", $select).show();
                        } else {
                            $('._s2', $select).hide();
                        }
                        event = event || window.event;
                        event.stopPropagation();
                    });

                    // 筛选
                    $select.on("click", "._input_edit", function (event) {
                        event.stopPropagation();
                    })
                    $select.on("keyup","._input_edit",function(){
                       initOption(optionsData,$(this).val());
                    })

                    // 自定义
                    $select.on("click", "._input_custom", function (event) {
                        event.stopPropagation();
                    })
                    $select.on("change","._input_custom",function(){
                        var value = $(this).val();
                        value = '其他_' + value;
                        var contain = $(this).parents("._s_contain");
                        $('._caption', contain).html(value);
                        $('input[name="' + options.name + '"]', contain).data('val',value).val(value).blur();
                        $(this).parents("._s2").hide();
                        $('._option', contain).removeClass("selected");
                    })


                    $('input[name="' + options.name + '"]','._s_contain').on('change',function(){
                        var val = $(this).val();
                        var oldVal = $(this).data('val');
                        console.log(val,oldVal);
                        if (val != oldVal) {
                            $(this).val(oldVal);
                        };
                        $(this).blur();
                    })

                    $select.on("click", "._option", function (event) {
                        var el = $(this);
                        var caption = el.html() == "-请选择-" ? "" : el.html();
                        var value = el.attr('value');
                        var contain = $(this).parents("._s_contain");
                        if(options.multselect){
                        	if(!value){
                        		$select.find("._option").removeClass("selected");
                        	}
                            var selectClass = el.hasClass("selected");
                            selectClass?el.removeClass("selected"):(el.html() == "-请选择-" ? el.removeClass("selected") : el.addClass("selected"));
                            var selectValue = [];
                            var selectCaption = [];
                            el.parent().find(".selected").each(function(k,v){
                                selectValue.push( $(v).attr("value"));
                                selectCaption.push( $(v).html());
                            })
                            $('._caption', contain).html(selectCaption.join(","));
                            value = selectValue.join(',');
                        }else{
                            $(this).parents("._s2").hide();

                            $('._caption', contain).html(caption);
                        }


                        var yvalue = $('input[name = "'+options.name+'"]', contain).val();
                        if (yvalue != value) {
                            if (typeof options.change === 'function') {
                            	$('input[name="' + options.name + '"]', contain).data('val',value).val(value).blur();
                                options.change(event, value, allData);
                            }else{
                                $('input[name="' + options.name + '"]', contain).data('val',value).val(value).blur();
                            }
                        }

                        // 触发 blur 事件是为了触发 validate 插件的校验功能
                        $('input[name="' + options.name + '"]', contain).data('val',value).val(value).blur();
                        event.stopPropagation();
                    })
                }
            });
            if (options.simpleData) {
                this._simplleDatarRender(options);
            } else {
                this._dataCodeRender(options);
            }
            
            function queryData(data){//加载数据
                qy.ajax({
                    url: options.url,
                    data: data,
                    async: options.async,
                    callBack: function (data) {
                    	optionsData = data || [];
                        if (!data) return;
                        initOption(data);
                    }
                })
            }
            
            function init(){//初始化select容器
                var str = '<div class="_s_contain1" style="z-index:' + options.zIndex + ' ;">' +
                '<div class="_s1"><span class="_caption"></span><i class="iconfont icon-xiaodansanjiao"></i>   </div>' +
                '  <input type="text" style="width:0px;height:1px;padding:0px;margin:0px;border:0px;float:left;" data-val="" name="' + options.name + '" '+ options.validate +' '+ (options.disabled ? 'disabled' : '') +'/>' +
                ' <div class="_s2">';
                if (options.filter) {
                   str = str + '<div class="_option _filter" ><input type="text" placeholder="筛选输入" class="_input_edit" /></div>';
                }
                if (options.isCustom) {
                   str = str + '<div class="_option _custom" ><input type="text" placeholder="其他(请说明)" class="_input_custom" /></div>';
                };

                if (options.empty) {
                    str += '<div class="_option_disable _option empty_option" value="">-请选择-</div>';
                }
                str += '</div>';
                str += '</div>';
                $select.css("cursor", "pointer");
                $select.hasClass("._s_contain") ? "" : $select.addClass("_s_contain");
                $select.html(str);
            }
            
            function initOption(data,filter){//初始化下拉选项
            	allData = data;
            	$select.clear(true);
                if (!data) return;
                var str = '';
                if(!filter){
                    filter = '';
                }
                $select.find("._s2").find('.option-value').remove();
                $.each(data, function (k, v) {
                	if(options.dataFilter(v)){
                		if(v[options.codeValue]&&v[options.codeValue].indexOf(filter)>= 0){
                			str += '<div class="_option option-value" value="' + v[options.code] + '">' + v[options.codeValue] + '</div>';
                		}
                	}
                })
                options.defaultValue ?"":options.defaultValue = $select.attr("data-defaultValue");
                $select.find("._s2").append(str);
                $select.find("._s2").append($select.find("._option._custom"));
                if (options.defaultValue) {
                	if(options.multselect){
                		var caption = [];
                		$.each(options.defaultValue.split(","),function(i,v){
                			var selectOption = $select.find("._option[value='" + v + "']");
                			if(selectOption.length){//如果选项存在则为其添加选择样式
                				caption.push(selectOption.addClass("selected").html());
                			}else if(typeof v == 'string' && v.match('其他_')){
                                caption.push(v);
                                v = v.replace('其他_','');
                                $('._input_custom', $select).val(v);
                            }
                		})

                		$('._caption', $select).html(caption.join(","));
                	}else{
                		var default_value = $select.find("._option[value='" + options.defaultValue + "']");
                		if(default_value.length){//如果选项存在则为其添加选择样式
                			$('._caption', $select).html(default_value.html());
                		}else if(typeof options.defaultValue == 'string' && options.defaultValue.match('其他_')){
                            var temp = options.defaultValue.replace('其他_','');
                            $('._caption', $select).html(options.defaultValue);
                            $('._input_custom', $select).val(temp);
                        }
                	}
                    $('input[name = "'+options.name+'"]', $select).data('val',options.defaultValue).val(options.defaultValue);
                }else if(options.selected){
                	var firstValue = $select.find("._option:eq(0)");
                	if(firstValue){//选项存在则执行
                		$('._caption', $select).html(firstValue.html());
                		$('input[name = "'+options.name+'"]', $select).data('val',firstValue.attr("value")).val(firstValue.attr("value"));
                	}
                }
                loadFlag = true;
                if(typeof options.initAfter === 'function'){
                	options.initAfter(data,$select.getSelectValue(),$select.getSelectOtherValue());
                }
                
            }
            this._events();
            return this;
        }

    })

})()
