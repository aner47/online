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

define(["ajax"],function(){
	return{
		grid:function(opts){
			var GridInStance = function(){
				var table = '<table class="mygrid_table"></table>';
				var pagination	= '<div class="quotes"></div>';
				var div	= '<div></div>';
				var tr = "<tr></tr>";
				var tbody = "<tbody></tbody>";
				var th = "<th></th>";
				var td = "<td></td>";
				var colDefault={
						sort:false
				}
				var defaultOpts = {
					showHead:true,
					toolBar:false,
					num:true,
					checkbox:true,
					pageSize:10,
					pageNumber:1,
					codeValueUrl :base+"/common/findCodeDataByName.jhtml",
					events :{
						rowClick : function(){},// 行点击
						rowSelected: function(){},
						dblClick:function(){}
					}
				}
				$('#'+opts.tab).unbind(); // 移除所有
				opts = $.extend(true,defaultOpts,opts);
				
				var my = this;
				my.codeValue = {};
				my.opts = opts;
				my.columnName = [];
				$.each(my.opts.columns,function(i,col){
					$.extend(colDefault,col);
				});
				function loadCodeValue (){
					$.each(my.opts.columns,function(i,data){
						if(! data.valueSet){
							return;
						}
						if(my.codeValue[ data.valueSet]){
							return ;
						}else{
						}
						qy.ajax({
							async:false,
							url:opts.codeValueUrl,
							data:{name:data.valueSet},
							callBack:function(datas){
								my.codeValue[data.valueSet]=datas;
							}
						})
					})
				}
			
				loadCodeValue();	

				/**
				 * 初始化模板
				 */
				this.initTemplate = function(){
					my.$tdata = $(tr).clone();
					$.each(my.opts.columns,function(i,data){
						var $td = $(td).clone();
						if(data.isHtml){
							$td.append(data.html)
						}else{
							$td.attr("name",data.name);
							$td.attr("id",data.name);
						}
						if(data.hidden){
							$td.addClass('mygrid_hidden');
						}
						my.$tdata.append($td);
					});
					my.$tdata.addClass('mygrid_column');
				}
				this.initDataModel = function(){// 初始化数据模型
					$.each(my.opts.columns,function(i,data){
						my.columnName.push(data.name);
					})
				}
				this.initDataManage = function(){// 初始化数据处理项
					my.dataDeal = {};
					$.each(my.opts.columns,function(i,data){
						my.dataDeal[data.name] = data;
					})
				}
				
				// 初始化列事件
				this.initEvent = function(){
					$.each(my.opts.columns,function(i,data){
						if(data.events){
							$.each(data.events,function(j,event){
								$('#'+my.opts.tab).on(event.type,event.select?event.select:"[name='"+data.name+"']",event.handle)
							})
						}
					})
				}
				/**
				 * 数据处理函数
				 */
				this.dataDealFun = function(key,value,handle){
					var calValue = value;
					$.each(key.split('.'),function(i,name){
						if(!value) {
							return value;
						} else{
							value = value[name];
						}
					})
					if(my.dataDeal[key]){
						var data = my.dataDeal[key];
						if(data.icon){
							return {html:true,domObject : $("<img height = 30>").attr("src",value)}
						}
						if(data.codeData){
							value = data.codeData[value] 
						}
						if(typeof(data.format) === 'function'){
							value = data.format(value,calValue,handle);
						}
						if(data.type && data.type=="date"){
							value =  new Date(value).Format("yyyy-MM-dd");
						}
						if(data.valueSet){
							var codevalues = my.codeValue[data.valueSet];
							$.each(codevalues,function(i,v){
								if(value && v.code == value.toString()) {
									value = v.codeValue + "<input type='hidden' name= 'status' value="+v.code+">";
								}
							})
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
					
				}
				 var lhandle ;
				$("#"+opts.tab).on('mouseenter mouseout',"td",function(event){
					  if(event.type === "mouseenter"){
						  var that = this;
						  var tips = $(that).html() ; 
						  if(tips){
							  lhandle = layer.tips(tips ,that)
							  layer.style(lhandle, {
									"backgroud-color":"green",
							  }); 
						  } else{
							  layer.close(lhandle);
						  }
					  }else{
						  layer.close(lhandle);
					  }
		
					
				})
	/*			
				*//**
					 * 初始化工具栏
					 *//*
						 * this.initToolBar= function(){ var $btnAdd = $("<button></button>");
						 * var $btnDel = $("<button></button>"); var
						 * $btnModfiy = $("<button></button>");
						 * $btnAdd.html("增加"); $btnAdd.click( function(){
						 * layer.open({ title:'增加', type: 1, area:'auto',
						 * closeBtn :1, shadeClose:true, maxWidth:600,
						 * minHeight:400, content:
						 * $(opts.tab+"_tEditData")//这里content是一个普通的String });
						 * }); $btnDel.html("删除"); $btnModfiy.html("修改")
						 * my.$toolBar = $('<div></div>');
						 * my.$toolBar.append($btnAdd);
						 * my.$toolBar.append($btnDel);
						 * my.$toolBar.append($btnModfiy); }
						 */
				/**
				 * 初始化表头
				 */
				this.initHead= function(){
					my.$head = $(tr).clone();
					my.$head.addClass('mygrid_title');
					var showColumns = 0 ;
					$.each(my.opts.columns,function(i,column){
						if(!column.hidden){
							showColumns++;
						}
						
					})
					var width = 100/showColumns;
					$.each(my.opts.columns,function(i,data){
						var $th = $(th).clone();
						$th.attr("col-name",data.name);
						if(data.hidden){
							$th.addClass('mygrid_hidden');
						}
						if(data.width){
							$th.attr('width',data.width)
						}
						var head  = data.caption;
						if(!data.html){
							if(data.sort){
								head = '<span>'+data.caption+'</span><div class="order"><div class="goUp"></div><div class="goDown"></div></div>';
							}
						}
						$th.html(head);
						my.$head.append($th);
					});
					
				}
				
				
				var $contain = $('#'+opts.tab);
				if(!opts.template){// 生成模板
					my.initTemplate();
				}else{
					my.$tdata =  $(opts.template);
				}
				my.initDataManage();
				$contain.html('');// 清空容器
		
				
				if(opts.showHead){// 初始化表头
					my.initHead();
				}
				
				if(my.opts.toolBar){
					my.initToolBar();
					$contain.prepend(my.$toolBar);
				}
				if(my.opts.num){// 是否显示序号
					var $th = $(th).clone();
					$th.html('序号').css('width',"50px");
					my.$head.prepend($th);
					var $td = $(td).clone();
					$td.attr("name","@num");
					my.$tdata.prepend($td);
				}
				
				if(my.opts.checkbox){// 是否显示checkbox
					var $th = $(th).clone();
					$th.html('<input type = "checkbox" name ="checkall" ></checkbox>').css('width',"50px");
					my.$head.prepend($th);
					var $td = $(td).clone();
					$td.html('<input type = "checkbox" name ="@check" ></checkbox>');
					my.$tdata.prepend($td);
				}
				var $tab = $(table);
				$contain.append($tab);
				my.initEvent();
				my.$pagination=$(pagination)
				$contain.append(my.$pagination);
				my.initDataModel();
				my.$tbody = $('<tbody></tbody>').attr("id",opts.tab+'_datas');
				$tab.append(my.$head);
				$tab.append(my.$tbody);
				function initGridBtn(){
					$.each($('[data-grid="'+opts.tab+'"]'),function(key,value){
						var enable = $(value).attr("data-enable");
						if(enable ==1  ){
							var curLength = getSelectRowLength();
							if(curLength == 1){
								// my.buttonEnable(value);
								$(value).removeAttr("disabled");
							}else{
								// my.buttonEnable(value)
								$(value).attr("disabled","disabled");
							}
							
						}else if(enable ==2 ){
							var curLength = getSelectRowLength();
							if(curLength>0){
								$(value).removeAttr("disabled");
							}else{
								$(value).attr("disabled","disabled");
							}
						}
						
					})
				}
				this.buttonDisable = function(element){
					$(element).attr("disabled","disabled");
				}
				this.buttonEnable = function(element){
					$(element).removeAttr("disabled");
				}
				this.loadData = function(params){
					my.opts.data.pageNumber=1;
					var mygrid = my;
					var index;
					$.ajax({
						url: mygrid.opts.url,
						type: "POST",
			        	dataType : 'json',
						data : $.extend(opts.data,params),
						beforeSend:function(){
							index = layer.load(0);
						},
						success:function(data){
							layer.close(index);
							mygrid.DataObj = data;
							var content = data.content;
							mygrid.$tbody.html('');
							initGridBtn();
			            	$.each(content, function(i, n){
			                    var row = mygrid.$tdata.clone();
			                    row.find('[name="@num"]').append((my.opts.data.pageSize*(my.opts.data.pageNumber -1))+i+1);
			                    if(i%2!==0)row.addClass("mygrid_column_odd");else row.addClass("mygrid_column_even");
			                    $.each(mygrid.columnName,function(key,val){
			                    	var $handle = row.find('#'+val+',[name="'+val+'"]');
			                    	var data = mygrid.dataDealFun(val,n,$handle);
			                    	if(data !=null&&typeof data == 'object'&&data.hasOwnProperty("html")){
			                    		$handle.append(data.domObject)
			                    	}else{
			                    		$handle.html(data);
			                    	}
			                    	
			                    })
			                    mygrid.$tbody.append(row);// 添加到模板的容器中
			            	});
/*
 * for(var i = 0 ;i < my.opts.data.pageSize - content.length ;i++ ){
 * mygrid.$tbody.append(tr);// 添加到模板的容器中 }
 */
			            	mygrid.$pagination.pagination(data.total, {  
				                    'items_per_page': data.pageSize,  
				                    'num_display_entries': 10,  
				                    'num_edge_entries': 3,
				                    'current_page': data.pageNumber -1, 
				                    'prev_text': "",  
				                    'next_text': "",  
				                    "callback": mygrid.pageselectCallback 
				            }); 
			               if(typeof mygrid.opts.loadDataCallBack === 'function'){
			            	   mygrid.opts.loadDataCallBack(data);
			               }
						},
						error:function(){
							layer.close(index); 
							
						}
						
					})
				}
				
				this.pageselectCallback = function (page,options){
					var mygrid = my;
					my.opts.data.pageNumber= page +1;
					$.ajax({
						url: opts.url,
						type: "POST",
			        	dataType : 'json',
						data : opts.data,
						beforeSend:function(){
							index = layer.load(0);
						},
						success:function(data){
							layer.close(index);
							mygrid.DataObj = data;
							var content = data.content;
							mygrid.$tbody.html('');
							initGridBtn();
							$.each(content, function(i, n){
			                    var row = mygrid.$tdata.clone();
			                    row.find('[name="@num"]').append((my.opts.data.pageSize*(my.opts.data.pageNumber -1))+i+1);
			                    if(i%2!==0)row.addClass("mygrid_column_odd");else row.addClass("mygrid_column_even");
			                    $.each(mygrid.columnName,function(key,val){
			                    	var $handle = row.find('#'+val+',[name="'+val+'"]');
			                    	var data = mygrid.dataDealFun(val,n,$handle);
			                    	if(data !=null&&typeof data == 'object'&&data.hasOwnProperty("html")){
			                    		$handle.append(data.domObject)
			                    	}else{
			                    		$handle.html(data);
			                    	}
			                    	
			                    })
			                    mygrid.$tbody.append(row);// 添加到模板的容器中
			                    if(typeof mygrid.opts.loadDataCallBack === 'function'){
				            	   mygrid.opts.loadDataCallBack(data);
			                    }
			            	});
			            	
						},
						error:function(){
							layer.close(index); 
							
						}
					})
				}
				function getSelectRowLength(){
					return $('tr.mygrid_selected','#'+my.opts.tab).length;
				}
				
				this.getSelectedRow = function(){
					var rowData = [];
					
					$.each($('tr.mygrid_selected','#'+my.opts.tab),function(i,data){
						var colData = {};
						$.each($(data).find('td'),function(j,d){
							colData[$(d).attr('name')] = $(d).html();
						})
						rowData.push(colData)
						
					})
					return rowData ;
				}
				
				
				this.getSelectedValue = function(name){
					var colData = [];
					$.each($('tr.mygrid_selected td[name="'+name+'"]','#'+my.opts.tab),function(i,data){
						colData.push($(data).html());
					})
					return colData ;
				};
				
				this.getSingleSelectedValue = function(name){
					return $('tr.mygrid_selected td[name="'+name+'"]','#'+my.opts.tab).html();
				};
				
				this.getCurrentRowValue = function(element,name){
					return $(element).parents('tr').find('#'+name+',[name='+name+']').html();
					
				}
				var tTD = '';
				$("th",my.$head).mousedown(function(){
					tTD = this;
					if (event.offsetX > tTD.offsetWidth - 10) {
						tTD.mouseDown = true;
						tTD.oldX = event.x;
						tTD.oldWidth = tTD.offsetWidth;
					}
				})

				$("th",my.$head).mouseup(function(){
					if(tTD){
						tTD.mouseDown = false;
						tTD.style.cursor = 'default';	
					}

				})
				
				$("th > div > div",my.$head).click(function(){
					
					var orderDirection =  '';
					var $th = $(this).parents('th');
					$th.siblings().attr("order","disorder");
					var orderDirection = "";
					if($th.attr("order") !=='up' && $(this).hasClass('goUp')){
						$th.attr("order","up");
						orderDirection = 'asc';
						my.loadData({"orderProperty":$(this).parents('th').attr("col-name"),"orderDirection":orderDirection});
					}else if($th.attr("order") !=='down' && $(this).hasClass('goDown')){
						$th.attr("order","down");
						orderDirection = "desc";
						my.loadData({"orderProperty":$(this).parents('th').attr("col-name"),"orderDirection":orderDirection});
					}else{
						$th.attr("order","disorder");
						my.loadData({"orderProperty":'',"orderDirection":orderDirection});
					}
				})
				/*
				 * $("th .goUp",my.$head).click(function(){
				 * 
				 * var orderDirection = "asc";
				 * if($(this).parents('th').attr('order') == 'up'){
				 *  } var $th = $(this).parents('th');
				 * $th.siblings().attr("order","disorder");
				 * if($(this).hasClass('goUp')){ $th.attr("order","up"); }else{
				 * $th.attr("order","down"); } $(this).toggleClass('active');
				 * if($(this).hasClass('active')){
				 * my.loadData({"orderProperty":$(this).parents('th').attr("col-name"),"orderDirection":orderDirection});
				 * }else{ $th.attr("order","disorder"); my.loadData(); }
				 * 
				 *  })
				 */
				
				
				
				
				

				$("th",my.$head).mousemove(function(){
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
				
				
				
				
				$('#'+opts.tab).delegate(':checkbox[name="checkall"]',"click",function(e){
					if($(this).prop('checked')){
						$(':checkbox','#'+opts.tab).prop('checked',"checked");
						$('tr','#'+opts.tab+'_datas').removeClass("mygrid_selected");
						$('tr','#'+opts.tab+'_datas').addClass("mygrid_selected");
						initGridBtn();
						my.opts.events.rowSelected($(this).parents("tr"));// 触发选中事件
					}else{
						$(':checkbox','#'+opts.tab).removeAttr('checked');
						$('tr','#'+opts.tab+'_datas').removeClass("mygrid_selected");
						initGridBtn();
					}
					my.opts.events.rowClick($(this).parents("tr"));// 触发click事件
					
				})
				$('#'+opts.tab+'_datas').delegate("tr","dblclick",function(e){
					my.opts.events.dblClick(e);// 触发选中事件
				})
				
				$('#'+opts.tab+'_datas').delegate("tr","click",function(e){
					var $this = $(this);
					if($this.hasClass("mygrid_selected")){
						$(this).removeClass("mygrid_selected");
						$(this).find('input[type="checkbox"]').removeAttr('checked')
						initGridBtn()
					}else{
						$(this).addClass("mygrid_selected");
						if(!my.opts.checkbox){
							$(this).siblings().removeClass("mygrid_selected");
						}
						$(this).find('input[type="checkbox"]').prop('checked',"checked");
						initGridBtn();
						my.opts.events.rowSelected($(this));// 触发选中事件
					}
					var count  = $this.parents("#tab1_datas").find('input[type="checkbox"]:checked').size();
					if(count==my.opts.pageSize){
						my.$head.find('input[type="checkbox"]').prop('checked',"checked");
					}else{
						my.$head.find('input[type="checkbox"]').removeAttr('checked');
					}
					my.opts.events.rowClick($(this));// 触发click事件
					
				})
			}
		
			return new GridInStance();
		}
	}
})