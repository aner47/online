(
	function(){
		 $.fn.extend({
			 tree:function(options){
				 var _default={//默认配置
					 simpleDate:{},
					 expandAll:false,
					 root:{name:"根节点",child:[],id:"0",icon:'&#xf00c8;',child:[],url:"#"},//根节点默认配置
					 events:{click:function(){},select:function(){}}
				 };
				 options = $.extend(true,_default,options);
				 var handle = this;
				 this.addClass("qy-tree");
				 function slideUp(em){
						// em.find('#icon').removeClass("icon-shixinsanjiao")
						// em.find('#icon').addClass("icon-shixinsanjiaoright")
						em.children(":not(span)").slideUp();
						em.attr('isslide',false);
					 }
				 function slideDown(em){
					 // em.find('#icon').removeClass("icon-shixinsanjiaoright");
					 // em.find('#icon').addClass("icon-shixinsanjiao");
					 em.children(":not(span)").slideDown();
					 em.attr('isslide',true);
				 }
				 $.extend(this,{
					 render:function(){//渲染
						this.html('');
						handle.d = d = {};
						d[options.root.id] = options.root;
						var data = options.simpleDate;
						var str = this.loadData(data,options.root);
						

						this._events();
					 },
					 loadData:function(data,root){
						    handle.html('');
						 	if(data.length<=0) return ;
						 	handle.d = d = {};
							$.each(data,function(i,v){
								d[v.id] = v ;
								v.child = [];
							})
							$.each(d,function(i,node){
								var pnode = d[node.pid];
								if(pnode){
									pnode.child.push(node);
									pnode.child.sort(function(node1,node2){
										return node1.sort-node2.sort;
									})
								}
							})

							var $root = a(d[root.id],$root,root.show,1,true,root.childId);
							function a(node,root,show,level,isRoot,childId){
								if(node.child.length>0){
									var $node = createNoLeafNode(node,show);
									
									$.each(node.child,function(i,v){//创建子节点
										a(v,$node,true,level+1,false);
									});
									if(root){
										root.append($node);
										if(level == 1)root.removeClass('tree-node');
									}else{
										$node.append($node);
										if(level == 1)$node.removeClass('tree-node');
										return $node;
									}
								}else{
									if(root){
										var cls = '';
										if(level >= 3){var cls = 'three-node'}
											root.append(createLeafNode(node,cls));
									}
									
								}
							}
							
							handle.html($root);
							$root.find('ul .tree-node').attr('isslide','false').find('li').hide();
							//slideUp();
							if((root.childId || root.childId === 0) && handle.find("li[node-id="+root.childId+"]").length){
								handle.find("li[node-id="+root.childId+"]").parent('ul.tree-node').attr('isslide','true').find('li').show();
								
								handle.find("li[node-id="+root.childId+"]").trigger('click');
							}else{
								handle.find("li:eq(0)").parent('ul.tree-node').attr('isslide','true').find('li').show();
								
								handle.find("li:eq(0)").trigger('click');
							}
							
							
							//产生一个图标
							function createIconfont(iconClass,fontSize,id){
								fontSize = fontSize||18;
								return '<i id='+id+' style="font-size:'+fontSize+'px;" class="iconfont '+iconClass+'"></i>'
							}
							//产生叶子节点
							function createLeafNode(node,cls){
								return $('<li class="tree-node tree-leaf '+cls+'" node-id='+node.id+'>'+createIconfont(node.icon)+'<a data-url="'+node.url+'" title="'+node.name+'"> </a>'+"<span class=caption>"+node.name+"</span>"+'</li>');
							}
							//产生非叶子节点
							function createNoLeafNode(node,isShow){
								var $p =  $('<ul class="tree-node tree-parent " node-id='+node.id+'></ul>');
								var str =  '<span><i style="font-size:18px;" class="iconfont '+node.icon+'"></i>'+"<span class=caption>"+node.name+"</span>"+'<i style="font-size:12px;" id="icon" class="iconfont icon-shixinsanjiaoright"></i></span>';
								if(isShow){
									var $content = $("<span></span>");
									if(node.icon){
										$content .append(createIconfont(node.icon))
									}
									$content.append("<span class=caption>"+node.name+"</span>");
									$content.append(createIconfont("icon-shixinsanjiaoright",12,'icon'));
									$p.html($content);
								}
								return $p;
							}
						
					 },
					 _events:function(){
					 	var activeCls = "active";
						 	handle.on('click','ul.tree-node',function(event) {	//收起展开
						 		// console.log(!$(this).attr('isslide'),$(this).attr('isslide'));
						 		var isslide = ($(this).attr('isslide') === "false") ? true : false;
						 		$.each($(this).siblings('ul'),function(i,v){
						 			slideUp($(v));
						 		});
								if(isslide){//展开
									slideDown($(this));
								}else{//收起
									slideUp($(this));
								}
								if(typeof options.events.click === 'function'){
									options.events.click(this,handle.d[$(this).attr("node-id")]);
								}
								
								$(this).find('li:eq(0)').trigger('click');
								event.stopPropagation();
							});

						 handle.on('click', "li", function(event) {//叶子click;
						 	$("."+activeCls,handle).removeClass(activeCls);
							 	$(this).addClass(activeCls);
							 	$(this).parents('.tree-node').addClass(activeCls);
								$(this).siblings('ul').removeClass(activeCls);
								$.each($(this).siblings('ul'),function(i,v){
									slideUp($(v));
								});
								var url = $(this).find('a').attr('data-url');
								console.dir(handle );
								if(typeof options.events.click === 'function'){
									options.events.click(this,handle.d[$(this).attr("node-id")]);
								}
								event.stopPropagation();
							});
					 }
				 })
				 var func = {
					 getNodeType:function(nodeId){
						 return handle.d[nodeId].child.length>0?'parent':'leaf';
					 }
				 }
				 $.extend(this,func);
				 this.render();
				 

				 return this;
			 }
		 	
		 })
		
		
	}
)()
