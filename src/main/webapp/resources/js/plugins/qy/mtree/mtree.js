define([ 'jquery',"ztree","ajax"], function() {
	return {
		tree : function(option) {
			var treeObj 
			var setting = this.getSetting(option.setting);
			var ot = {
					url:option.url,
					async: false,
					data:option.data||{},
					callBack:function(data){
						treeObj = $.fn.zTree.init($("#"+option.id), setting, data);
					}
			}
			qy.ajax(ot);
			return treeObj;
		},
		getSetting : function(set) {
			var _defaultSetting = {
				view : {
					showLine : false,//是否显示节点之间的连线
					showIcon : false,
					selectedMulti : true
				},
				check : {
					chkStyle : "checkbox",//复选框类型
					enable : true,//每个节点上是否显示 CheckBox 
				},
				async : {
					enable : true,
					type : 'post',
					autoParam : [ 'id' ], //异步加载时需要自动提交父节点属性的参数
					dataType : 'json',
				},
				data : {
					simpleData : {//简单数据模式
						enable : true,
						idKey : "code",
						pIdKey : "parent",
						rootPId : ""
					}
				},
			};
			
			return  $.extend(_defaultSetting,set||{});
			
			
		}
		
		

	}
})