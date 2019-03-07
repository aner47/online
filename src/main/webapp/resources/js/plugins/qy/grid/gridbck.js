Date.prototype.Format = function(fmt)   
{ // author: meizz
  var o = {   
    "M+" : this.getMonth()+1,                 // 月份
    "d+" : this.getDate(),                    // 日
    "h+" : this.getHours(),                   // 小时
    "m+" : this.getMinutes(),                 // 分
    "s+" : this.getSeconds(),                 // 秒
    "q+" : Math.floor((this.getMonth()+3)/3), // 季度
    "S"  : this.getMilliseconds()             // 毫秒
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 	

define(function () {
    return {
        grid: function (opts) {
            var $this = this;
             $('#' + opts.tab).unbind(); 
            var GridInStance = function () {
                var tempData = {}
                var baseEl = {// 基础元素
                    table: '<table class="mygrid_table"></table>',
                    pagination: '<div class="quotes"></div>',
                    div: '<div></div>',
                    tr: "<tr></tr>",
                    tbody: "<tbody></tbody>",
                    th: "<th></th>",
                    td: "<td></td>"
                }
                var _default_options = {// 默认配置项
                    showHead: true,
                    toolBar: false,
                    num: true,
                    autoW: false,
                    checkbox: true,
                    pageSize: 10,
                    pageNumber: 1,
                    export_show_always:false,
                    trToSelect:true,  //点击tr是否可以选中
                    allSelect:function(){},
                    codeValueUrl: base + "/common/findCodeDataByName.jhtml",
                    events: {
                        rowClick: function () {
                        },// 行点击
                        rowSelected: function () {
                        },
                        dblClick: function () {
                        }
                    }

                }
                opts = $.extend(true, _default_options, opts);
                var privateMethod = {
                    initTemplate: function (columns) {// 初始化行模板
                        tempData.$tdata = $(baseEl.tr).clone();
                        $.each(columns, function (i, data) {
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
                    initDataModel: function (columns) {
                        $.each(columns, function (i, data) {
                            tempData.columnName.push(data.name);
                        })
                    },
                    initDataManage: function (columns) {// 初始化数据处理项
                        tempData.dataDeal = {};
                        $.each(columns, function (i, data) {
                            tempData.dataDeal[data.name] = data;
                        })
                    },
                    // 初始化列事件
                    initEvent: function (columns) {

                        $.each(columns, function (i, data) {
                            if (data.events) {
                                $.each(data.events, function (j, event) {
                                    $('#' + tempData.opts.tab).on(event.type, event.select ? event.select : "[name='" + data.name + "']", event.handle)
                                })
                            }
                        })
                    },
                    dataDealFun: function (key, value, handle) {
    					var calValue = value;
    					$.each(key.split('.'),function(i,name){
    						if(!value) {
    							return value;
    						} else{
    							value = value[name];
    						}
    					})
    					if(tempData.dataDeal[key]){
    						var data = tempData.dataDeal[key];
    						if(data.icon){
                                if (value) {
                                    return {html:true,domObject : $("<img height = 30>").attr("src",value)}
                                }else{
                                    return '';
                                };
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
    						if(data.valueSet){
    							var codevalues = tempData.codeValue[data.valueSet];
    							for(var i = 0;codevalues&&i < codevalues.length;i++ ){
    								if(value != undefined && codevalues[i].code == value.toString()) {
    									value = codevalues[i].codeValue;
    									break;
    								}
    							}
    							
    						}
    						if(data.prefix){
    							value = data.prefix + value;
    						}
    						if(data.postfix){
    							value = value+data.postfix ;
    						}
    						return value;
    					}else{
    						return value;
    					}
                    },
                    /**
					 * 初始化表头
					 */
                    initHead: function (columns) {// 初始化表头
                        tempData.$head = $(baseEl.tr).clone();
                        tempData.$head.addClass('mygrid_title');
                        $.each(columns, function (i, data) {
                            var $th = $(baseEl.th).clone();
                            $th.attr("col-name",data.name);
                            if (data.hidden) {
                                $th.addClass('mygrid_hidden');
                            }
                            if (data.width) {
                                $th.attr('width', data.width)
                            }
    						var head  = data.caption;
    						if(!data.html){
    							if(data.sort){
    								head = '<span>'+data.caption+'</span><div class="order"><div class="goUp"></div><div class="goDown"></div></div>';
    							}
    						}
                            
                            $th.html(head);
                            tempData.$head.append($th);
                        });
                    },
                    _render: function (opts) {
                        tempData.opts = opts;
                        tempData.columnName = [];
                        var $contain = $('#' + opts.tab);
                        if (!opts.template) {
                            privateMethod.initTemplate(tempData.opts.columns);
                        } else {
                            tempData.$tdata = $(opts.template);// 自定义模板
                        }
                        privateMethod.initDataManage(opts.columns);
                        privateMethod.loadCodeValue(opts);
                        $contain.html('');// 清空容器
                        if (opts.showHead) {// 初始化表头
                            privateMethod.initHead(opts.columns);
                        }
                        if (opts.num) {// 是否显示序号
                            var $th = $(baseEl.th).clone();
                            $th.html('序号').css('width', "50px");
                            tempData.$head.prepend($th);
                            var $td = $(baseEl.td).clone();
                            $td.attr("name", "@num");
                            tempData.$tdata.prepend($td);
                        }
                        if (tempData.opts.checkbox) {// 是否显示checkbox
                            var $th = $(baseEl.th).clone();
                            $th.html('<input type = "checkbox" name ="checkall" ></checkbox>').css('width', "50px");
                            tempData.$head.prepend($th);
                            var $td = $(baseEl.td).clone();
                            $td.html('<input type = "checkbox" name ="@check" ></checkbox>');
                            tempData.$tdata.prepend($td);
                        }
                        var $tab = $(baseEl.table).attr("id",opts.tab+"_table");
                        
                        var $tempContent = $('<div class="mygrid_content"></div>');
                        if (opts.autoW) {
                            $tempContent.addClass('autoW');
                        };
                        $tempContent.append($tab);
                        $contain.append($tempContent);
                        privateMethod.initEvent(opts.columns);
                        tempData.$pagination = $(baseEl.pagination)
                        $contain.append(tempData.$pagination);
                        privateMethod.initDataModel(opts.columns);
                        tempData.$tbody = $('<tbody></tbody>').attr("id", opts.tab + '_datas');
                        $tab.append(tempData.$head);
                        $tab.append(tempData.$tbody);
                    },
                    loadCodeValue: function (opts) {
                    	tempData.codeValue = {};
                        $.each(opts.columns, function (i, data) {
                            if (!data.valueSet||data.valueSet.length <= 0) {
                                return;
                            }
                            if(tempData.codeValue[data.valueSet]){
                            	return ;
                            }
                            if(top.codeValue[data.valueSet]){
                            	tempData.codeValue[data.valueSet] = top.codeValue[data.valueSet];
                            	return ;
                            }
                            qy.ajax({
                                async: false,
                                dataType:"json",
                                type:"post",
                                url: opts.codeValueUrl,
                                data: {name: data.valueSet},
                                callBack: function (datas) {
                                	top.codeValue[data.valueSet] = tempData.codeValue[data.valueSet] = datas;
                                }
                            })
                        })
                    }
                }

                function initEvent() {
    
                }


                var publicMethod = {
                	loadSimpleData:function(){
                		var data = opts.simpleData;
                		data.pageNumber = data.pageNumber||1;
                		tempData.opts.data.pageNumber =  1;
                         var content = data.content;
                         tempData.content = content.slice(0,tempData.opts.data.pageSize);
                         loadGrid(tempData.content);
                         tempData.$pagination.pagination(data.content.length, {
                             'items_per_page': data.pageSize,
                             'num_display_entries': 5,
                             'num_edge_entries': 1,
                             'current_page': data.pageNumber -1,
                             'prev_text': "",
                             'next_text': "",
                             export_show_always:opts.export_show_always,
                             "callback": publicMethod.simplateDataCallBack
                         });
                         if (typeof tempData.opts.loadDataCallBack === 'function') {
                             tempData.opts.loadDataCallBack(data);
                         }
                	},
                    columnsEI:function(arr,show){
                        var deviation=1;
                        if (tempData.opts.num) {//是否显示序号
                            deviation++;
                        }
                        if (tempData.opts.checkbox) {//是否显示checkbox
                            deviation++;
                        }
                        if (show) {
                            for (var i = 0; i < arr.length; i++) {
                                opts.columns[arr[i]].hidden=false;
                                $('#'+opts.tab+' .mygrid_title th:nth-child('+(arr[i]+deviation)+'),#'+opts.tab+' .mygrid_column td:nth-child('+(arr[i]+deviation)+')').removeClass('mygrid_hidden');
                            };
                        }else{
                            for (var i = 0; i < arr.length; i++) {
                                opts.columns[arr[i]].hidden=true;
                                $('#'+opts.tab+' .mygrid_title th:nth-child('+(arr[i]+deviation)+'),#'+opts.tab+' .mygrid_column td:nth-child('+(arr[i]+deviation)+')').addClass('mygrid_hidden');
                            };
                        };
                        
                        tempData.opts = opts;
                        privateMethod.initTemplate(tempData.opts.columns);
                        if (tempData.opts.num) {//是否显示序号
                            var $td = $(baseEl.td).clone();
                            $td.attr("name", "@num");
                            tempData.$tdata.prepend($td);
                        }
                        if (tempData.opts.checkbox) {//是否显示checkbox
                            var $td = $(baseEl.td).clone();
                            $td.html('<input type = "checkbox" name ="@check" ></checkbox>');
                            tempData.$tdata.prepend($td);
                        };
                    },
                    loadData: function (params) {
                        tempData.opts.data.pageNumber = 1;
                        tempData.opts.data = $.extend(tempData.opts.data,params)
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
                                tempData.$pagination.pagination(data.total, {
                                    'items_per_page': data.pageSize,
                                    'num_display_entries': 5,
                                    'num_edge_entries': 1,
                                    'current_page': data.pageNumber -1,
                                    'prev_text': "",
                                    'next_text': "",
                                    export_show_always:opts.export_show_always,
                                    "callback": publicMethod.pageselectCallback,
                                    exportAll:function(){publicMethod.exportExcel(3)},//全部导出
                                    exportSome:function(){publicMethod.exportExcel(1)},//导出选中行
                                    exportThis:function(){publicMethod.exportExcel(2)},//导出本页
                                });
                                if (typeof tempData.opts.loadDataCallBack === 'function') {
                                    tempData.opts.loadDataCallBack(data);
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
                             tempData.opts.loadDataCallBack(data);
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

                            },
                            error: function () {
                                layer.close(index);

                            }
                        })
                    },
                    refresh: function (fun) {
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
                                tempData.$pagination.pagination(data.total, {
                                    'items_per_page': data.pageSize,
                                    'num_display_entries': 5,
                                    'num_edge_entries': 1,
                                    'current_page': data.pageNumber -1,
                                    'prev_text': "",
                                    'next_text': "",
                                    export_show_always:opts.export_show_always,
                                    "callback": publicMethod.pageselectCallback,
                                    exportAll:function(){publicMethod.exportExcel(3)},//全部导出
                                    exportSome:function(){publicMethod.exportExcel(1)},//导出选中行
                                    exportThis:function(){publicMethod.exportExcel(2)},//导出本页
                                });
                                if (typeof tempData.opts.loadDataCallBack === 'function') {
                                    tempData.opts.loadDataCallBack(data);
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

                    getSelectedRow: function () {
                        var rowData = [];
                        $.each($('tr.mygrid_selected', '#' + opts.tab), function (i, row) {
                            rowData.push(tempData.content[$(row).attr("data-row")]);
                        })
                        return rowData;
                    },

                    getSelectedRowByTr: function (tr) {
                        var rowData = [];
                        $.each(tr, function (i, row) {
                            rowData.push(tempData.content[$(row).attr("data-row")]);
                        })
                        return rowData;
                    },


                    getSelectedValue: function (name) {
                        var colData = [];
                        $.each($('tr.mygrid_selected', '#' + opts.tab), function (i, row) {
                        	var value = getValue(row,name);
                        	colData.push(value);
                        })
                        return colData;
                    },

                    getSingleSelectedValue: function (name) {
                    	return this.getSelectedValue(name)[0];
                    },

                    getCurrentRowValue: function (element, name) {
                    	var row = $(element).parents('tr');
                        return getValue(row,name);
                    },
                    
                    getValueByRowNumber: function (number, name) {
                    	var a = name.split('.');
                    	var i = 0 ;
                    	var value = tempData.content[number];
                    	while(i < a.length){
                            if (!value) {
                                return '';
                            };
                    		var value = value[a[i]];
                    		i++; 
                    	}
                    	return value ;
                    },
                    
                    getContentByRowNumber: function (number, name) {
                    	return  tempData.content[number];
                    },
                    exportExcel: function (type) {//1为选中行，2为当前页,3为所有数据
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
                              	var data = privateMethod.dataDealFun(val.name,values,$handle);
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
                            for (var index in arrData[0]) {//生成标题
                                row += arrData[0][index] + ',';
                            }
                            row = row.slice(0, -1);
                            CSV += row + '\r\n';
                            for (var i = 1; i < arrData.length; i++) {
                                var row = "";
                                for (var index in arrData[i]) {
                                    row += '"' + arrData[i][index] + '",';
                                }
                                row.slice(0, row.length - 1);//去掉最后一个逗号
                                CSV += row + '\r\n';
                            }
                            if (CSV == '') {
                                alert("Invalid data");
                                return;
                            }
                            console.log(CSV.length);
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

                function loadGrid(content){
                    tempData.$tbody.html('');
                    initGridBtn();
	            	$.each(content, function(i, n){
	                    var row = tempData.$tdata.clone();
	                    row.attr("data-row",i);
	                    row.find('[name="@num"]').append((opts.data.pageSize*(opts.data.pageNumber -1))+i+1);
	                    if(i%2!==0)row.addClass("mygrid_column_odd");else row.addClass("mygrid_column_even");
	                    $.each(tempData.columnName,function(key,val){
	                    	var $handle = row.find('#'+val+',[name="'+val+'"]');
	                    	var data = privateMethod.dataDealFun(val,n,$handle);
	                    	if(data !=null&&typeof data == 'object'&&data.hasOwnProperty("html")){
	                    		$handle.append(data.domObject)
	                    	}else{
	                    		$handle.html(data);
	                    	}
	                    	
	                    })
	                    
	                    tempData.$tbody.append(row);// 添加到模板的容器中
	            	});
                }
                
                
                function registerEvents(){
                	var lhandle;
                    $("#" + opts.tab).on('mouseenter mouseout', "td", function (event) {
                        if (event.type === "mouseenter") {
                            var that = this;
                            var tips = $(that).html();
                            if (tips) {
                                lhandle = layer.tips(tips, that)
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
    				$("th",tempData.$head).mousedown(function(){
    					tTD = this;
    					if (event.offsetX > tTD.offsetWidth - 10) {
    						tTD.mouseDown = true;
    						tTD.oldX = event.x;
    						tTD.oldWidth = tTD.offsetWidth;
    					}
    				})

    				$("th",tempData.$head).mouseup(function(){
    					if(tTD){
    						tTD.mouseDown = false;
    						tTD.style.cursor = 'default';	
    					}

    				})
    				
    			$("th",tempData.$head).mousemove(function(){
					if(!tTD) tTD = this;
					if (event.offsetX > this.offsetWidth - 10)
						this.style.cursor = 'col-resize';
					else
						this.style.cursor = 'default';
						// 取出暂存的Table Cell
						// 调整宽度
					if (tTD.mouseDown) {
						tTD.style.cursor = 'default';
						if (tTD.oldWidth + (event.x - tTD.oldX)>40)
							tTD.width = tTD.oldWidth + (event.x - tTD.oldX);
						// 调整列宽
						tTD.style.width = tTD.width;
						tTD.style.cursor = 'col-resize';
				/*
				 * //调整该列中的每个Cell table = tTD; while (table.tagName != 'TABLE')
				 * table = table.parentElement; for (j = 0; j <
				 * table.rows.length; j++) {
				 * table.rows[j].cells[tTD.cellIndex].width = tTD.width; }
				 */
					}
				})
    				
    				$("th > div > div",tempData.$head).click(function(){
    					var orderDirection =  '';
    					var $th = $(this).parents('th');
    					$th.siblings().attr("order","disorder");
    					var orderDirection = "";
    					if($th.attr("order") !=='up' && $(this).hasClass('goUp')){
    						$th.attr("order","up");
    						orderDirection = 'asc';
    						publicMethod.loadData({"orderProperty":$(this).parents('th').attr("col-name"),"orderDirection":orderDirection});
    					}else if($th.attr("order") !=='down' && $(this).hasClass('goDown')){
    						$th.attr("order","down");
    						orderDirection = "desc";
    						publicMethod.loadData({"orderProperty":$(this).parents('th').attr("col-name"),"orderDirection":orderDirection});
    					}else{
    						$th.attr("order","disorder");
    						publicMethod.loadData({"orderProperty":'',"orderDirection":orderDirection});
    					}
    				})
                	
                	
                					
				$('#'+opts.tab).delegate(':checkbox[name="checkall"]',"click",function(e){
					if($(this).prop('checked')){
						opts.allSelect();
						$(':checkbox','#'+opts.tab).prop('checked',"checked");
						$('tr','#'+opts.tab+'_datas').removeClass("mygrid_selected");
						$('tr','#'+opts.tab+'_datas').addClass("mygrid_selected");
						initGridBtn();
						opts.events.rowSelected($("tr",'#'+opts.tab+'_datas'));// 触发选中事件
					}else{
						$(':checkbox','#'+opts.tab).removeAttr('checked');
						$('tr','#'+opts.tab+'_datas').removeClass("mygrid_selected");
						initGridBtn();
					}
					opts.events.rowClick($("tr",'#'+opts.tab+'_datas'),$(this).parents("tr").attr("data-row"));// 触发click事件
					
				})
				$('#'+opts.tab+'_datas').delegate("tr","dblclick",function(e){
					opts.events.dblClick(e);// 触发选中事件
				})
				
				
				
				$('#'+opts.tab+'_datas').delegate("tr","click",function(e){
					var $this = $(this);
					if(opts.trToSelect){
						if($this.hasClass("mygrid_selected")){
							$(this).removeClass("mygrid_selected");
							$(this).find('input[type="checkbox"]').removeAttr('checked')
							initGridBtn()
						}else{
							$(this).addClass("mygrid_selected");
							if(!opts.checkbox){
								$(this).siblings().removeClass("mygrid_selected");
							}
							$(this).find('input[type="checkbox"]').prop('checked',"checked");
							initGridBtn();
							opts.events.rowSelected($(this));// 触发选中事件
						}
					}else{
						var ischecked = $(this).find('input[type="checkbox"]').prop('checked');
						var ischeckedData = $(this).find('input[type="checkbox"]').data('checked');
						if(ischecked === ischeckedData){
							return;
						}
						$(this).find('input[type="checkbox"]').data('checked',ischecked);
						if(ischecked){
							$(this).addClass("mygrid_selected");
							if(!opts.checkbox){
								$(this).siblings().removeClass("mygrid_selected");
							}
							initGridBtn();
							opts.events.rowSelected($(this));// 触发选中事件
						}else{
							$(this).removeClass("mygrid_selected");
							initGridBtn();
						}
					}
					var count  = $this.parents("#tab1_datas").find('input[type="checkbox"]:checked').size();
					if(count==opts.pageSize){
						tempData.$head.find('input[type="checkbox"]').prop('checked',"checked");
						opts.allSelect();
					}else{
						tempData.$head.find('input[type="checkbox"]').removeAttr('checked');
					}
					opts.events.rowClick($(this),$(this).attr("data-row"));// 触发click事件
					
				})
                	
                } 
                
                
    	        function fixTable(tableId, lockCol, width, height) {
    	            if ($("#" + tableId + "_tableLayout").length != 0) {
    	                $("#" + tableId + "_tableLayout").before($("#" + tableId));
    	                $("#" + tableId + "_tableLayout").empty();
    	            }
    	            else {
    	                $("#" + tableId).after("<div id='" + tableId + "_tableLayout' style='overflow:hidden;height:" + height + "; width:" + width + ";'></div>");
    	            }
    	            $('<div id="' + tableId + '_tableData"></div>'+
    	            		 '<div id="' + tableId + '_tableColumn"></div>').appendTo("#" + tableId + "_tableLayout");
    	            var oldtable = $("#" + tableId);
    	            var tableFixClone = oldtable.clone(true);
    	            tableFixClone.attr("id", tableId + "_tableFixClone");
    	            $("#" + tableId + "_tableFix").append(tableFixClone);
    	            var tableHeadClone = oldtable.clone(true);
    	            var tableColumnClone = oldtable.clone(true);
    	            tableColumnClone.attr("id", tableId + "_tableColumnClone");
    	            $("#" + tableId + "_tableColumn").append(tableColumnClone);
    	            $("#" + tableId + "_tableData").append(oldtable);

    	            var HeadHeight = $("#" + tableId + "_tableHead thead").height();
    	            HeadHeight += 2;
    	            $("#" + tableId + "_tableHead").css("height", HeadHeight);
    	            $("#" + tableId + "_tableFix").css("height", HeadHeight);
    	            var ColumnsWidth = 0;
    	            var ColumnsNumber = 0;
    	            $("#" + tableId + "_tableColumn tr:last td:lt(" + lockCol + ")").each(function () {
    	                ColumnsWidth += $(this).outerWidth();
    	                ColumnsNumber++;
    	            });
    	            ColumnsWidth +=2
    	            $("#" + tableId + "_tableColumn").css("width", ColumnsWidth);
    	            $("#" + tableId + "_tableData").scroll(function () {
    	                $("#" + tableId + "_tableColumn").scrollTop($("#" + tableId + "_tableData").scrollTop());
    	            });
    	            $("#" + tableId + "_tableColumn").css({ "overflow": "hidden" ,"position": "relative", "z-index": "40", "background-color": "white" });
    	            $("#" + tableId + "_tableData").css({ "overflow-x": "scroll", "width": width,  "position": "absolute", "z-index": "35" });

    	            if ($("#" + tableId + "_tableColumn").height() > $("#" + tableId + "_tableColumn table").height()) {
    	                $("#" + tableId + "_tableColumn").css("height", $("#" + tableId + "_tableColumn table").height());
    	                $("#" + tableId + "_tableData").css("height", $("#" + tableId + "_tableColumn table").height() + 17);
    	            }
    	            $("#" + tableId + "_tableColumn").offset($("#" + tableId + "_tableLayout").offset());
    	            $("#" + tableId + "_tableData").offset($("#" + tableId + "_tableLayout").offset());
    	        }
                
                
                
                function getValue(row,name){
                	var a = name.split('.');
                	var i = 0 ;
                	var value = tempData.content[$(row).attr("data-row")];
                	while(i < a.length){
                		var value = value[a[i]];
                		i++; 
                	}
                	return value ;
                }
                function initGridBtn() {
                    $.each($('[data-grid="' + opts.tab + '"]'), function (key, value) {
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
