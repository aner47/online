Date.prototype.Format = function(fmt) { // author: meizz
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


define(["commonBase"],function() {
    return {
        grid: function(opts) {
        	var firstQuery = true;
            var $this = this;
            $('#' + opts.tab).unbind();
            var GridInStance = function() {
                var tempData = {};
                var baseEl = { // 基础元素
                	contain:'<div class="mygrid_content"></div>',
                    table: '<table class="mygrid_table"></table>',
                    griddesc: '<div class="mygrid_desc"><div class="_desc-caption"></div><div class="_desc-tools"></div></div>',
                    pagination: '<div class="_grid-page" style="display:none"><div class="_right"><div class="total"><div class="total-value">共10条</div></div><div class="current-page"><select class="_page-size"><option value="10">10条/页</option><option value="20">20条/页</option><option value="30">30条/页</option><option value="50">50条/页</option><option value="100">100条/页</option></select></div><div class="_page-go"><div class="_left page-btn _page-pre"><</div><div class="_left _page-content">1/1</div><div class="_left page-btn _page-next">></div></div><div class="_page-number"><input type="number" class="_left" min="1"><div class="page-btn _page-number-btn _left">确定</div></div></div></div>',
                    excel: '<div class="quotes"></div>',
                    /*
                    * pagination: '<div class="_grid-page" style="display:none"><div class="_left"><div class="_btn _btn-fun2 _page-download-btn">下载 <i class="iconfont icon-xiazai1"></i></div></div><div class="_right"><div class="total"><div class="total-value">共10条</div></div><div class="current-page"><select class="_page-size"><option value="10">10条/页</option><option value="20">20条/页</option><option value="30">30条/页</option><option value="50">50条/页</option><option value="100">100条/页</option></select></div><div class="_page-go"><div class="_left page-btn _page-pre"><</div><div class="_left _page-content">1/1</div><div class="_left page-btn _page-next">></div></div><div class="_page-number"><input type="number" class="_left" min="1"><div class="page-btn _page-number-btn _left">确定</div></div></div></div>',
                    * */
                    /*
					 * tools:{ add:'<div class="grid-btn _left" id = "add" >添加</div>',
					 * update:'<div class="grid-btn _left" id = "update"
					 * data-grid = "tab1" data-enable = "1" >修改</div>', del:'<div
					 * class="grid-btn _left" id = "delete" data-grid = "tab1"
					 * data-enable = "2" >删除</div>"', },
					 */
                    tools:'<div class="_btn _left" style="margin-left:10px;"></div>',
                    div: '<div></div>',
                    tr: "<tr></tr>",
                    tbody: "<tbody></tbody>",
                    th: "<th></th>",
                    td: "<td></td>"
                }
                var _default_options = { // 默认配置项
                	tools:[],
                    showHead: true,
                    toolBar: false,
                    num: true,
                    autoW: false,
                    /*autoH: false,*/
                    checkbox: true,
                    scrollTop:false,
                    pageSize: 10,
                    pageNumber: 1,
                    export_show_always: false,
                    allSelect: function() {},
                    codeValueUrl: base + "/common/findCodeDataByName.jhtml",
                    events: {
                        rowClick: function() {}, // 行点击
                        rowSelected: function() {},
                        dblClick: function() {}
                    }
                }
                var simpleDataTemp = opts.simpleData;
                opts = $.extend(true, _default_options, opts);
                
                var privateMethod = {
                    initTemplate: function(columns) { // 初始化行模板
                        tempData.$tdata = $(baseEl.tr).clone();
                        $.each(columns, function(i, data) {
                            var $td = $(baseEl.td).clone();
                            if (data.isHtml) {
                                $td.append(data.html);
                            } else {
                                $td.attr("name", data.name);
                                $td.attr("id", data.name);
                            }
                            if (data.hidden) {
                                $td.addClass('mygrid_hidden');
                            }
                            tempData.$tdata.append($td);
                        });
                        tempData.$tdata.addClass('mygrid_column');
                    },
                    initDataModel: function(columns) {
                        $.each(columns, function(i, data) {
                            tempData.columnName.push(data.name);
                        })
                    },
                    initDataManage: function(columns) { // 初始化数据处理项
                        tempData.dataDeal = {};
                        $.each(columns, function(i, data) {
                            tempData.dataDeal[data.name] = data;
                        })
                    },
                    // 初始化列事件
                    initEvent: function(columns) {
                        $.each(columns, function(i, data) {
                            if (data.events) {
                                $.each(data.events, function(j, event) {
                                    $('#' + tempData.opts.tab).on(event.type, event.select ? event.select : "[name='" + data.name + "']", event.handle)
                                })
                            }
                        })
                    },
                    dataDealFunShow: function(key, value, handle) {
                        var calValue = value;
                        /*console.log(calValue + "aaaaaaaaaaaaaaaaaaa")*/
                        $.each(key.split('.'), function(k, name) {
                            if (!value) {
                                return value;
                            } else {
                                value = value[name];
                            }
                        })
                        if (tempData.dataDeal[key]) {
                            var data = tempData.dataDeal[key];
                            if (data.codeData) {
                                value = data.codeData[value]
                            }
                            
                            if (value && data.type && data.type == "date") {
                                value = new Date(value).Format("yyyy-MM-dd");
                            }
                            if(value && data.type && data.type=="date_yyyy_MM"){
    							value =  new Date(value).Format("yyyy-MM");
    						}
    						if(value && data.type && data.type=="date_time"){
    							value =  new Date(value).Format("yyyy-MM-dd hh:mm:ss");
    						}
    						
                            if (data.type && data.type == "boolean") {
                            	value = value===false?"否":value===true?"是":value;
                            }
                            if (data.number >= 0) {
                            	if(value===null ||  value ==''){
                            	}else if(!isNaN(value)){
                            		value = value.toFixed(data.number);
                           		}else{
                           			value = '-';
                           		}
                            }
                            if (data.valueSet) {
                                var codevalues = tempData.codeValue[data.valueSet];
                                var valueSetDict = {};
                                value = (value || '').toString();
                                var valueArr = value.split(',') || [];
                                value = '';
                                for (var i = 0; codevalues && i < codevalues.length; i++) {
                                    valueSetDict[codevalues[i].code] = codevalues[i].codeValue;
                                }
                                for (var i = 0; i < valueArr.length; i++) {
                                    value+=',';
                                    value += valueSetDict[valueArr[i]] || '';
                                };
                                value = symbolUnique(value);
                            }
                            if(data.simpleSet){
                            	value = data.simpleSet[value]|| value;
                            }
                            
                            if (typeof(data.format) === 'function') {
                                value = data.format(value, calValue, handle);
                            }
                            if (typeof(data.formatShow) === 'function') {
                                value = data.formatShow(value, calValue, handle);
                            }

                            if (data.prefix) {
                                value = data.prefix + value;
                            }
                            if (data.postfix) {
                                value = value + data.postfix;
                            }
                            if (data.icon) {
                                return {
                                    html: true,
                                    domObject: $("<img height = 30>").attr("src", value)
                                }
                            }
                            
                            return value;
                        } else {
                            return value;
                        }
                    },
                    dataDealFun: function(key, value, handle) {
                        var calValue = value;
                        $.each(key.split('.'), function(i, name) {
                            if (!value) {
                                return value;
                            } else {
                                value = value[name];
                            }
                        })
                        if (tempData.dataDeal[key]) {
                            var data = tempData.dataDeal[key];
                            if (data.icon) {
                                return {
                                    html: true,
                                    domObject: $("<img height = 30>").attr("src", value)
                                }
                            }
    						if(data.codeData){
    							value = data.codeData[value] 
    						}
    						if(typeof(data.format) === 'function'){
    							value = data.format(value,calValue,handle);
    						}
    						if(value && data.type && data.type=="date"){
    							value =  new Date(value).Format("yyyy-MM-dd");
    						}
    						if(value && data.type && data.type=="date_yyyy_MM"){
    							value =  new Date(value).Format("yyyy-MM");
    						}
    						if(value && data.type && data.type=="date_time"){
    							value =  new Date(value).Format("yyyy-MM-dd hh:mm:ss");
    						}
                            if (data.valueSet) {
                                var codevalues = tempData.codeValue[data.valueSet];
                                var valueSetDict = {};
                                value = (value || '').toString();
                                var valueArr = value.split(',') || [];
                                value = '';
                                for (var i = 0; codevalues && i < codevalues.length; i++) {
                                    valueSetDict[codevalues[i].code] = codevalues[i].codeValue;
                                }
                                for (var i = 0; i < valueArr.length; i++) {
                                    value += ',';
                                    value += valueSetDict[valueArr[i]] || '';
                                };
                                value = symbolUnique(value);
                            }
                            if (typeof(data.format) === 'function') {
                                value = data.format(value, calValue, handle);
                            }

                            if (data.prefix) {
                                value = data.prefix + value;
                            }
                            if (data.postfix) {
                                value = value + data.postfix;
                            }
                            return value;
                        } else {
                            return value;
                        }
                    },
                    /**
					 * 初始化表头
					 */
                    initHead: function(columns) { // 初始化表头
                        tempData.$head = $(baseEl.tr).clone();
                        tempData.$head.addClass('mygrid_title');
                        $.each(columns, function(i, data) {
                            var $th = $(baseEl.th).clone();
                            $th.attr("col-name", data.name);
                            if (data.hidden) {
                                $th.addClass('mygrid_hidden');
                            }
                            if (data.width) {
                                $th.attr('width', data.width)
                            }
                            var head = data.caption;
                            if (!data.html) {
                                if (data.sort) {
                                    head = '<span class="hasorder">' + data.caption + '<div class="order"><div class="goUp"></div><div class="goDown"></div></div></span>';
                                }
                            }

                            $th.html(head);
                            tempData.$head.append($th);
                        });
                    },
                    _render: function(opts) {// 渲染模板
                        tempData.opts = opts;
                        tempData.columnName = [];
                        var $contain = $('#' + opts.tab);
                        if (!opts.template) {
                            privateMethod.initTemplate(tempData.opts.columns);
                        } else {
                            tempData.$tdata = $(opts.template); // 自定义模板
                        }
                        privateMethod.initDataManage(opts.columns);
                        privateMethod.loadCodeValue(opts);
                        $contain.html(''); // 清空容器
                        $contain.addClass('mygrid-mygrid');
                        if (opts.showHead) { // 初始化表头
                            privateMethod.initHead(opts.columns);
                        }
                        if (opts.num) { // 是否显示序号
                            var $th = $(baseEl.th).clone();
                            $th.html('序号').css('width', "70px");
                            tempData.$head.prepend($th);
                            var $td = $(baseEl.td).clone();
                            $td.attr("name", "@num");
                            tempData.$tdata.prepend($td);
                        }
                        if (tempData.opts.checkbox) { // 是否显示checkbox
                            var $th = $(baseEl.th).clone();
                            $th.html('<input type = "checkbox" name ="checkall" ></checkbox>').css('width', "70px");
                            tempData.$head.prepend($th);
                            var $td = $(baseEl.td).clone();
                            $td.html('<input type = "checkbox" name ="@check" ></checkbox>');
                            tempData.$tdata.prepend($td);
                        }
                        var $tab = $(baseEl.table);
                        var $tempContent = $(baseEl.contain);
                        tempData.$tempContent = $tempContent;
                        if (opts.autoW) {
                            $tempContent.addClass('autoW');
                        };
                        if(opts.gridDesc){
                        	 tempData.$gridDesc = $(baseEl.griddesc);
                        	 $contain.append( tempData.$gridDesc);
                        	 if(opts.tools.length>0){
                        		 var $btn_tools = $(baseEl.tools);
                        		 $.each(opts.tools,function(i,v){
                                    if (v.onlineHtml) {
                                        tempData.$gridDesc.find("._desc-tools").append(v.caption);
                                        v.readerAfter();
                                        return;
                                    };
                        			 var btnClass = v.type==1?'_btn-danger': v.type==2?'_btn-warn':v.type==3?'_btn-fun1':v.type==4?'_btn-fun2':v.type==4?'_btn-fun3':"";
                        			 var clone = $btn_tools.clone().html(v.caption).attr("data-grid",opts.tab).attr("data-enable",v.enable).addClass(btnClass);
                        			 
                        			 clone.click(function(){
                        				 if($(this).attr("disabled"))return;
                        				 v.handle.call(this,arguments); 
                        			 });// 添加事件
                        			 tempData.$gridDesc.find("._desc-tools").append(clone);
                        		 })
                        	 }
          
                        	 tempData.$gridDesc.find("._desc-caption").html(opts.gridDesc);
                        }
                        $tempContent.append($tab);
                        $contain.append($tempContent);
                        privateMethod.initEvent(opts.columns);
                        tempData.$pagination = $(baseEl.pagination);
                        tempData.$excel = $(baseEl.excel);
                        $contain.append(tempData.$pagination);
                        $contain.append(tempData.$excel);
                        privateMethod.initDataModel(opts.columns);
                        tempData.$tbody = $(baseEl.tbody).attr("id", opts.tab + '_datas');
                        $tab.append(tempData.$head);
                        $tab.append(tempData.$tbody);
                    },
                    loadCodeValue: function(opts) {// 加载代码集
                        tempData.codeValue = {};
                        $.each(opts.columns, function(i, data) {
                            if (!data.valueSet || data.valueSet.length <= 0) {
                                return;
                            }
                            if (tempData.codeValue[data.valueSet]) {
                                return;
                            }
                            qy.ajax({
                                async: false,
                                dataType: "json",
                                type: "post",
                                url: opts.codeValueUrl,
                                data: {
                                    name: data.valueSet,
                                    param: data.param
                                },
                                callBack: function(datas) {
                                    tempData.codeValue[data.valueSet] = datas;
                                }
                            })
                        })
                    }
                }

                function initEvent() {

                }
                
                function loadGridData(){// 加载表格数据
                	if(tempData.dataMethod == 'remote'){
                		loadRemoteData();// 加载远程数据
                	}else if(tempData.dataMethod == 'simpleData'){
                		loadSimpleData();// 加载本地数据
                	}
                }
                
                
                function loadSimpleData(){
                	var  data = {};
                	data.total =  opts.simpleData.content.length;
                	data.totalPages = Math.ceil(data.total/opts.data.pageSize);
                	data.pageSize = opts.data.pageSize;
                	data.pageNumber = opts.data.pageNumber;
                	data.content = tempData.content = opts.simpleData.content.slice((opts.data.pageNumber-1) * opts.data.pageSize, (opts.data.pageNumber) * opts.data.pageSize);
                	tempData.data= data;
                    loadGrid(tempData.content);
                }
                

                function loadRemoteData(){
                	tempData.dataMethod = 'remote';
                	var index;
                    qy.ajax({
                        url: tempData.opts.url,
                        type: "POST",
                        dataType: 'json',
                        data: tempData.opts.data,
                        beforeSend: function() {
                            index = layer.load(0);
                        },
                        callBack: function(data) {
                            data = $.extend({
                                "content": [],
                                "total": 0,
                                "pageNumber": 1,
                                "pageSize": 10,
                                "totalPages": 0
                            }, data);
                            layer.close(index);
                            var content = data.content;
                            tempData.content = content;
                            tempData.total = data.total;
                            tempData.data = data;
                            loadGrid(content);// 渲染表格
                        },
                        error: function() {
                            layer.close(index);
                        }
                    })
                }
                

                var publicMethod = {
                    loadSimpleData: function() {
                    	tempData.dataMethod = 'simpleData';
                    	var data = opts.simpleData;
                        opts.data.pageNumber = 1;
                        loadGridData();
                    },
                    columnsEI: function(arr, show) {// 显示或隐藏列
                        var deviation = 1;
                        if (tempData.opts.num) { // 是否显示序号
                            deviation++;
                        }
                        if (tempData.opts.checkbox) { // 是否显示checkbox
                            deviation++;
                        }
                        if (show) {
                            for (var i = 0; i < arr.length; i++) {
                                opts.columns[arr[i]].hidden = false;
                                $('#' + opts.tab + ' .mygrid_title th:nth-child(' + (arr[i] + deviation) + '),#' + opts.tab + ' .mygrid_column td:nth-child(' + (arr[i] + deviation) + ')').removeClass('mygrid_hidden');
                            };
                        } else {
                            for (var i = 0; i < arr.length; i++) {
                                opts.columns[arr[i]].hidden = true;
                                $('#' + opts.tab + ' .mygrid_title th:nth-child(' + (arr[i] + deviation) + '),#' + opts.tab + ' .mygrid_column td:nth-child(' + (arr[i] + deviation) + ')').addClass('mygrid_hidden');
                            };
                        };

                        tempData.opts = opts;
                        privateMethod.initTemplate(tempData.opts.columns);
                        if (tempData.opts.num) { // 是否显示序号
                            var $td = $(baseEl.td).clone();
                            $td.attr("name", "@num");
                            tempData.$tdata.prepend($td);
                        }
                        if (tempData.opts.checkbox) { // 是否显示checkbox
                            var $td = $(baseEl.td).clone();
                            $td.html('<input type = "checkbox" name ="@check" ></checkbox>');
                            tempData.$tdata.prepend($td);
                        };
                    },
                    loadData: function(params) {
                    	tempData.dataMethod = 'remote';
                    	tempData.opts.data.pageNumber = 1;
                    	tempData.opts.data = $.extend(opts.data, params||{})
                        loadGridData();
                    	 var index;
                         qy.ajax({
                             url: opts.url,
                             type: "POST",
                             dataType: 'json',
                             data:  tempData.opts.data,
                             beforeSend: function () {
                                 index = layer.load(0);
                             },
                             callBack: function (data) {
                                 layer.close(index);
                                 var content = data.content;
                                 tempData.content = content;
                                 tempData.total = data.total;
                                 loadGrid(content);
                                 tempData.$excel.pagination(data.total, {
                                     'items_per_page': data.pageSize,
                                     'current_page': data.pageNumber -1,
                                     'select_show_always': false,
                                     'prev_show_always': false,
                                     'next_show_always': false,
                                     export_show_always: opts.export_show_always,
                                     "callback": publicMethod.pageselectCallback,
                                     exportAll:function(){publicMethod.exportExcel(3)},// 全部导出
                                     exportSome:function(){publicMethod.exportExcel(1)},// 导出选中行
                                     exportThis:function(){publicMethod.exportExcel(2)},// 导出本页
                                 });
                                 if (typeof tempData.opts.loadDataCallBack === 'function') {
                                     tempData.opts.loadDataCallBack(data,tempData.opts.data.pageNumber);
                                 }
                             },
                             error: function () {
                                 layer.close(index);

                             }

                         })
                    },
                    simplateDataCallBack:function(page){
                    	$("#" + opts.tab).unbind();
                		var data = opts.simpleData;
                		tempData.opts.data.pageNumber = page + 1;
                        var content = data.content;
                         tempData.content = content.slice(page*tempData.opts.data.pageSize,tempData.opts.data.pageSize*(page+1));
                         loadGrid(tempData.content);
                         if (typeof tempData.opts.loadDataCallBack === 'function') {
                             tempData.opts.loadDataCallBack(data,tempData.opts.data.pageNumber);
                         }
                    },
                    pageselectCallback: function (page, options) {
                    	tempData.opts.data.pageNumber = page + 1;
                        var index = '';
                        qy.ajax({
                            url: opts.url,
                            type: "POST",
                            dataType: 'json',
                            data: tempData.opts.data,
                            beforeSend: function () {
                                index = layer.load(0);
                            },
                            callBack: function (data) {
                                layer.close(index);
                                var content = data.content;
                                tempData.content = content;
                                loadGrid(tempData.content);
                                tempData.opts.loadDataCallBack(data,tempData.opts.data.pageNumber);
                            },
                            error: function () {
                                layer.close(index);

                            }
                        })
                    },
                    refresh: function(fun) {
                    	 loadGridData();
                    	 var index = '';
                         qy.ajax({
                             url: opts.url,
                             type: "POST",
                             dataType: 'json',
                             data: tempData.opts.data,
                             beforeSend: function () {
                                 index = layer.load(0);
                             },
                             callBack: function (data) {
                                 layer.close(index);
                                 var content = data.content;
                                 tempData.content = content;
                                 loadGrid(tempData.content);
                                 tempData.$excel.pagination(data.total, {
                                     'items_per_page': data.pageSize,
                                     'num_display_entries': 5,
                                     'num_edge_entries': 1,
                                     'current_page': data.pageNumber -1,
                                     'prev_text': "",
                                     'next_text': "",
                                     export_show_always:opts.export_show_always,
                                     "callback": publicMethod.pageselectCallback,
                                     exportAll:function(){publicMethod.exportExcel(3)},// 全部导出
                                     exportSome:function(){publicMethod.exportExcel(1)},// 导出选中行
                                     exportThis:function(){publicMethod.exportExcel(2)},// 导出本页
                                 });
                                 if (typeof tempData.opts.loadDataCallBack === 'function') {
                                     tempData.opts.loadDataCallBack(data,tempData.opts.data.pageNumber);
                                 }
                                 if (typeof fun === 'function') {
                                     fun();
                                 }

                             },
                             error: function () {
                                 layer.close(index);

                             }
                         })
                    },

                    getSelectedRow: function() {
                        var rowData = [];
                        $.each($('tr.mygrid_selected', '#' + opts.tab), function(i, row) {
                            rowData.push(tempData.content[$(row).attr("data-row")]);
                        })
                        return rowData;
                    },


                    getSelectedValue: function(name) {
                        var colData = [];
                        $.each($('tr.mygrid_selected', '#' + opts.tab), function(i, row) {
                            var value = getValue(row, name);
                            colData.push(value);
                        })
                        return colData;
                    },

                    getParentTrValue: function(td, name) {
                        var tr = $(td).parents('tr')[0];
                        return getValue(tr, name);;
                    },

                    getSingleSelectedValue: function(name) {
                        return this.getSelectedValue(name)[0];
                    },

                    getCurrentRowValue: function(element, name) {
                        var row = $(element).parents('tr');
                        return getValue(row, name);
                    },

                    getValueByRowNumber: function(number, name) {
                        var a = name.split('.');
                        var i = 0;
                        var d = tempData.content[number];
                        while (i < a.length) {
                            var value = d[a[i]];
                            i++;
                        }
                        return value;
                    },

                    getContentByRowNumber: function(number, name) {
                        return tempData.content[number];
                    },
                    exportExcel: function (type) {// 1为选中行，2为当前页,3为所有数据
                    	console.log(type);
                        var reportTitle = "export";

                        var dataResult = [];
                        var title = [];
                        $.each(opts.columns, function (j, column) {
                            if(!column.hidden ){
                            	if(column.isHtml === true){
                            		return;
                            	}
                        		title.push(column.caption);
                            }
                        })
                        dataResult.push(title);
                        var contents = [];
                        if(type === 1){
                            contents = publicMethod.getSelectedRow()
                        }else if(type === 2){
                            contents =   tempData.content;
                        }else if(type === 3){
                            var data = $.extend(true, {}, tempData.opts.data);
                            var index ;
                            for(var i = 1 ; i<= parseInt(tempData.total/1000)+1;i++){
                            	data.pageNumber = i;
                            	data.pageSize = tempData.total > 1000 ? 1000:tempData.total;
                            	qy.ajax({
                            		url: opts.url,
                            		type: "POST",
                            		dataType: 'json',
                            		data: data,
                            		async: false,
                            		beforeSend: function () {
                            			index = layer.load(0);
                            		},
                            		callBack: function (data) {
                            			layer.close(index);
                            			contents = contents.concat(data.content);
                            			console.log(contents.length);
                            		},
                            		error: function () {
                            			layer.close(index);
                            			
                            		}
                            	})
                            }
                        }else{
                        	return;
                        }

                        $.each(contents, function (i, values) {
                            var row = [];
                            var $row = tempData.$tdata.clone();
                            $.each(opts.columns,function(key,val){
	                    	  if(!val.hidden){
                              	if(val.isHtml === true){
                              		return;
                              	}
                              	var $handle = $row.find('#'+val.name+',[name="'+val.name+'"]');
                              	var data = privateMethod.dataDealFunShow(val.name,values,$handle);
                              	row.push(data);
                              }
                            })
                            dataResult.push(row);
                        })

                        createExcel(dataResult);
                        function createExcel(dataResult){
                            var arrData = dataResult;
                            var CSV = '';
                            var row = "";
                            for (var index in arrData[0]) {// 生成标题
                                row += arrData[0][index] + ',';
                            }
                            row = row.slice(0, -1);
                            CSV += row + '\r\n';
                            for (var i = 1; i < arrData.length; i++) {
                                var row = "";
                                for (var index in arrData[i]) {
                                    row += '"' + arrData[i][index] + '",';
                                }
                                row.slice(0, row.length - 1);// 去掉最后一个逗号
                                CSV += row + '\r\n';
                            }
                            if (CSV == '') {
                                alert("Invalid data");
                                return;
                            }
                            var fileName = "";
                            fileName += reportTitle.replace(/ /g, "_");
                            var blob = new Blob(["\ufeff", CSV]);
                            var url = URL.createObjectURL(blob);
                            var link = document.createElement("a");
                            link.href = url;
                            link.style = "visibility:hidden";
                            link.download = fileName + ".csv";
                            document.body.appendChild(link);
                            link.click();
                            document.body.removeChild(link);
                        }

                    }


                }

                function loadGrid(content) {
       
                	
                    $('.mygrid_title :checkbox').removeProp('checked');
                    tempData.$tbody.html('');
                    initGridBtn();
                    renderPage();
                    $.each(content, function(i, n) {
                        var row = tempData.$tdata.clone();
                        row.attr("data-row", i);
                        /*row.find('[name="@num"]').append(i + 1);*/
                        row.find('[name="@num"]').append((opts.data.pageSize * (opts.data.pageNumber - 1)) + i + 1);
                        if (i % 2 !== 0) row.addClass("mygrid_column_odd");
                        else row.addClass("mygrid_column_even");
                        $.each(tempData.columnName, function(key, val) {
                            var $handle = row.find('#' + val + ',[name="' + val + '"]');
                            var data = privateMethod.dataDealFunShow(val, n, $handle);
                            if (data != null && typeof data == 'object' && data.hasOwnProperty("html")) {
                                $handle.append(data.domObject)
                            } else {
                                $handle.html(data);
                            }
                        })
                        tempData.$tbody.append(row); // 添加到模板的容器中
                    });
                    if (typeof opts.loadDataCallBack === 'function') {
                        opts.loadDataCallBack(tempData.data);
                    }
                  //  localCache.setCache(tempData.opts.url,tempData.opts.data);
                
                }

                function renderPage(){
                	var content = tempData.data;
                	if (content) {
                		if(content.totalPages <= 1 && content.total<=10){
                    		$("._grid-page","#" + opts.tab).hide();
                    	}else{
                    		$("._grid-page","#" + opts.tab).show();
                    	}
                    	if(content.pageNumber==1){
                    		$("._grid-page ._page-pre","#" + opts.tab).addClass("disable");
                    	}else{
                    		$("._grid-page ._page-pre","#" + opts.tab).removeClass("disable");
                    	}
                       	if(content.pageNumber==content.totalPages){
                    		$("._grid-page ._page-next","#" + opts.tab).addClass("disable");
                    	}else{
                    		$("._grid-page ._page-next","#" + opts.tab).removeClass("disable");
                    	}
                       	$("._page-number input","#"+opts.tab).val(content.pageNumber);
                    	$("._grid-page .total-value","#" + opts.tab).html("共"+content.total+"条");// 共多少条
                    	$("._grid-page .total-value","#" + opts.tab).html("共"+content.total+"条");//
                    	$("._grid-page ._page-content","#" + opts.tab).html(content.pageNumber+"/"+content.totalPages);
                    	tempData.opts.data.pageNumber = content.pageNumber;
                    	opts.data.pageNumber = content.pageNumber;
                     	tempData.opts.data.pageSize= content.pageSize;
                     	opts.data.pageSize= content.pageSize;
                	}
                }
                
                // layer 提示
                function registerEvents() {
                    /** ********************注册翻页事件************************ */
                	$("._grid-page ._page-size","#" + opts.tab).on("change",function(e,value){
                		tempData.opts.data.pageSize = $(this).val();
                		loadGridData()
                	});
                	$("._grid-page ._page-number-btn","#" + opts.tab).on("click",function(){
                		var page = $("._page-number input","#"+opts.tab).val();
                		if(page == tempData.data.pageNumber)return;
                		console.log(page+"============"+ tempData.data.pageNumber);
                		page = page > tempData.data.totalPages?tempData.data.totalPages:page<1?1:page;
                		tempData.opts.data.pageNumber = 0 + page;
                		loadGridData()
                	})
                	
                	$("#" + opts.tab).keyup(function (e) {//捕获文档对象的按键弹起事件  
				        if (e.keyCode == 13) {//按键信息对象以参数的形式传递进来了  
				        	$("._grid-page ._page-number-btn","#" + opts.tab).click();
				        }  
				    });  
                	
                	$("._grid-page ._page-download-btn","#" + opts.tab).on("click",function(){
                		publicMethod.exportExcel(3,tempData.dataMethod=='remote'?false:true)
                		console.log("下载表格！");
                	})
                	
                	$("._grid-page ._page-pre","#" + opts.tab).on("click",function(){
                		if($(this).hasClass("disable")) return;
                		tempData.opts.data.pageNumber = opts.data.pageNumber -1;
                		loadGridData()
                		console.log("上一页！");
                	})
                	
                	$("._grid-page ._page-next","#" + opts.tab).on("click",function(){
                		if($(this).hasClass("disable")) return;
                		tempData.opts.data.pageNumber = parseInt(opts.data.pageNumber) +1;
                		loadGridData();
                		console.log("下一页！");
                	})
                var navH = tempData.$head.offset().top;
                tempData.$tabClone =$(baseEl.table);
                tempData.$tabClone.css("border","none");
			    $(window).scroll(function(){
			    // 获取滚动条的滑动距离
			      var scroH = $(this).scrollTop();
			      // 滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
			      if(scroH>=navH && opts.scrollTop){
			    	   tempData.$head.$clone = tempData.$head.clone();
		               tempData.$head.find('th').each(function(i,th){
		            	   tempData.$head.$clone.find("th:eq("+i+")").css("width",th.offsetWidth+"px"); 
		            	   tempData.$head.$clone.find("th:eq("+i+")").css("height",th.offsetHeight+"px");
		                })
		                tempData.$tabClone.html('');
		               
		                tempData.$head.$clone.css({"position":"fixed","top":0,width:$(".mygrid_title").width(),"border-left":"1px solid #e6e6e6","left":tempData.$head.offset().left});
		                tempData.$tabClone.append(tempData.$head.$clone);
		                $('body').append(tempData.$tabClone);
		                tempData.$head.$clone;
		                tempData.$tabClone.hide();
			    	    tempData.$tabClone.show();
			      }else if(scroH<navH){
			    	  tempData.$tabClone.hide();
			      }
			    });
                	
                	
                	
                	
                	
                	
                	var lhandle;
                    
  
                	
                    $("#" + opts.tab).on('mouseenter mouseleave', "td", function(event) {
                        if ($(this).data('prompt') == 'false') {
                            return;
                        };
                        if (event.type === "mouseenter") {
                            var that = this;
                            var tips = $(that).text();
                            if (tips) {
                                lhandle = layer.tips(tips, that,{anim: -1,isOutAnim: false})
                                layer.style(lhandle, {
                                    "backgroud-color": "green",
                                });
                            } else {
                                layer.close(lhandle);
                            }
                        } else {
                            layer.close(lhandle);
                        }
                    })
                    var tTD = '';
                    $("th", tempData.$head).mousedown(function() {
                        tTD = this;
                        if (event.offsetX > tTD.offsetWidth - 10) {
                            tTD.mouseDown = true;
                            tTD.oldX = event.x;
                            tTD.oldWidth = tTD.offsetWidth;
                        }
                    })

                    $("th", tempData.$head).mouseup(function() {
                        if (tTD) {
                            tTD.mouseDown = false;
                            tTD.style.cursor = 'default';
                        }

                    })

                    $("th", tempData.$head).mousemove(function() {
                            if (!tTD) tTD = this;
                            if (event.offsetX > this.offsetWidth - 10)
                                this.style.cursor = 'col-resize';
                            else
                                this.style.cursor = 'default';
                            // 取出暂存的Table Cell
                            // 调整宽度
                            if (tTD.mouseDown) {
                                tTD.style.cursor = 'default';
                                if (tTD.oldWidth + (event.x - tTD.oldX) > 40)
                                    tTD.width = tTD.oldWidth + (event.x - tTD.oldX);
                                // 调整列宽
                                tTD.style.width = tTD.width;
                                tTD.style.cursor = 'col-resize';
                                /*
								 * //调整该列中的每个Cell table = tTD; while
								 * (table.tagName != 'TABLE') table =
								 * table.parentElement; for (j = 0; j <
								 * table.rows.length; j++) {
								 * table.rows[j].cells[tTD.cellIndex].width =
								 * tTD.width; }
								 */
                            }
                        })
                        
                  	tempData.$head.on("mouseleave",function(){
                        if (tTD) {
                            tTD.mouseDown = false;
                            tTD.style.cursor = 'default';
                        }
                	})
                        // 排序
                    $("th span.hasorder", tempData.$head).click(function() {
                        var orderDirection = '';
                        var $th = $(this).parents('th');
                        $th.siblings().attr("order", "disorder");
                        var orderDirection = "";
                        if (!$th.attr("order") || $th.attr("order") == 'disorder') {
                            $th.attr("order", "down");
                            orderDirection = "desc";
                            fun($(this).parents('th').attr("col-name"))
                        } else if ($th.attr("order") == 'down') {
                            $th.attr("order", "up");
                            orderDirection = 'asc';
                            fun($(this).parents('th').attr("col-name"))
                        } else {
                            $th.attr("order", "disorder");
                            fun('');
                        }

                        function fun(orderProperty) {
                            if (simpleDataTemp) {
                                var data = simpleDataTemp.content.slice(0);
                                opts.simpleData.content = data.sort(function(a, b) {
                                    if (orderDirection === 'asc') {
                                        return a[orderProperty] - b[orderProperty];
                                    } else if (orderDirection === "desc") {
                                        return b[orderProperty] - a[orderProperty];
                                    } else {
                                        return false;
                                    };
                                })
                                publicMethod.loadSimpleData();
                            } else {
                                publicMethod.loadData({
                                    "orderProperty": orderProperty,
                                    "orderDirection": orderDirection
                                });
                            };
                        }
                    })
                    $('#' + opts.tab).delegate(':checkbox[name="checkall"]', "click", function(e) {
                        if ($(this).prop('checked')) {
                            opts.allSelect();
                            $(':checkbox', '#' + opts.tab).prop('checked', "checked");
                            $('tr', '#' + opts.tab + '_datas').removeClass("mygrid_selected");
                            $('tr', '#' + opts.tab + '_datas').addClass("mygrid_selected");
                            initGridBtn();
                            opts.events.rowSelected($(this).parents("tr")); // 触发选中事件
                        } else {
                            $(':checkbox', '#' + opts.tab).removeAttr('checked');
                            $('tr', '#' + opts.tab + '_datas').removeClass("mygrid_selected");
                            initGridBtn();
                        }
                        opts.events.rowClick($(this).parents("tr"), $(this).parents("tr").attr("data-row")); // 触发click事件

                    })
                    $('#' + opts.tab + '_datas').delegate("tr", "dblclick", function(e) {
                        opts.events.dblClick(e); // 触发选中事件
                    })



                    $('#' + opts.tab + '_datas').delegate("tr", "click", function(e) {
                        var $this = $(this);
                        if ($this.hasClass("mygrid_selected")) {
                            $(this).removeClass("mygrid_selected");
                            $(this).find('input[type="checkbox"]').removeAttr('checked')
                            initGridBtn()
                        } else {
                            $(this).addClass("mygrid_selected");
                            if (!opts.checkbox) {
                                $(this).siblings().removeClass("mygrid_selected");
                            }
                            $(this).find('input[type="checkbox"]').prop('checked', "checked");
                            initGridBtn();
                            opts.events.rowSelected($(this)); // 触发选中事件
                        }
                        var count = $this.parents("#tab1_datas").find('input[type="checkbox"]:checked').size();
                        if (count == opts.pageSize) {
                            tempData.$head.find('input[type="checkbox"]').prop('checked', "checked");
                            opts.allSelect();
                        } else {
                            tempData.$head.find('input[type="checkbox"]').removeAttr('checked');
                        }
                        opts.events.rowClick($(this), $(this).attr("data-row")); // 触发click事件

                    })

                }

                function getValue(row, name) {
                    var a = name.split('.');
                    var i = 0;
                    var value = tempData.content[$(row).attr("data-row")];
                    while (i < a.length) {
                        var value = value[a[i]];
                        i++;
                    }
                    return value;
                }

                function initGridBtn() {
                    $.each($('[data-grid="' + opts.tab + '"]'), function(key, value) {
                        var enable = $(value).attr("data-enable");
                        if (enable == 1) {
                            var curLength = getSelectRowLength();
                            if (curLength == 1) {
                                // my.buttonEnable(value);
                                $(value).removeAttr("disabled");
                            } else {
                                // my.buttonEnable(value)
                                $(value).attr("disabled", "disabled");
                            }

                        } else if (enable == 2) {
                            var curLength = getSelectRowLength();
                            if (curLength > 0) {
                                $(value).removeAttr("disabled");
                            } else {
                                $(value).attr("disabled", "disabled");
                            }
                        } else if (enable == 9) {
                            var curLength = getSelectRowLength();
                            if (curLength == 9) {
                                // my.buttonEnable(value);
                                $(value).removeAttr("disabled");
                            } else {
                                // my.buttonEnable(value)
                                $(value).attr("disabled", "disabled");
                            }
                        }

                    })
                }

                function getSelectRowLength() {
                    return $('tr.mygrid_selected', '#' + opts.tab).length;
                }

                $.extend(this, publicMethod);

                privateMethod._render(opts);
                registerEvents();
            }

            return new GridInStance();
        }

    }
})