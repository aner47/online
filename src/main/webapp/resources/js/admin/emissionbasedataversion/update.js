var updataGrid=[
// 表1
function(){
	alert(1);
},
// 表2
function(){
	alert(2);
},
// 表3
function(){
	alert(3);
},
// 表4
function(){
	alert(4);
},
// 表5
function(){
	alert(5);
},
// 表6
function(){
	alert(6);
},
/**
 * '0001':'化石燃料固定燃烧源',
 */
function(g,fun){
	var opts1 = {
			tab:"tab7",
			num:false,
			data:{pageSize:10,pageNumber:0},
			url:"../activedatamodulation/query.jhtml",
			loadDataCallBack: function(data){
			},
			columns :[
			{name:"id",caption:"ID",hidden:true,type:"text",events:[{type:"click",handle:fun}]},
			{name:"emissionBaseDataVersion.year",caption:"统计年份",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"enterprise.name",caption:"单位名称",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"industryCategoryName",caption:"行业名称",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"pollutionSource.address.city.name",caption:"城市",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"pollutionSource.address.county.name",caption:"区县",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"fuelType",caption:"燃料",hidden:false,valueSet:"燃料类型",type:"text",events:[{type:"click",handle:fun}]},
			{name:"boilerModel",caption:"锅炉型号",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"activeData.activeDataName",caption:"活动数据名称",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"activeData.activeData",caption:"活动量",hidden:false,type:"text",events:[{type:"click",handle:fun}]},
			{name:"activeData.unit",caption:"单位",hidden:false,type:"text",events:[{type:"click",handle:fun}]}
			]
		}
		grid1 =g.grid(opts1);
		grid1.loadData();
},
/**
 * '0002':'工艺过程源',
 */
function(g){
	var opts1 = {
			tab:"tab7",
			num:false,
			data:{pageSize:10,pageNumber:0},
			url:"../processsource/query.jhtml",
			loadDataCallBack: function(data){
			},
			columns :[
			{name:"id",caption:"ID",hidden:true,type:"text"},
			{name:"emissionBaseDataVersion.year",caption:"统计年份",hidden:false,type:"text"},
			{name:"enterprise.name",caption:"单位名称",hidden:false,type:"text"},
			{name:"pollutionSource.address.city.name",caption:"城市",hidden:false,type:"text"},
			{name:"pollutionSource.county.name",caption:"区县",hidden:false,type:"text"},
			{name:"industryCategoryName",caption:"行业类别",hidden:false,type:"text"},
			{name:"technology",caption:"工艺技术",hidden:false,type:"text"},
			{name:"activeData.activeDataName",caption:"活动数据名称",hidden:false,type:"text"},
			{name:"activeData.activeData",caption:"活动量",hidden:false,type:"text"},
			{name:"activeData.unit",caption:"单位",hidden:false,type:"text"},
			{name:"operate",caption:"操作",isHtml:true,html:'<a data-upload style="cursor: pointer;"><i class="iconfont .icon-xiangqing"></i></a>',
			events :[{
				type:'click',
				select:'#view',
				handle:function(){
					var id =grid1.getCurrentRowValue(this,'id');
					var opts = {
						title:"查看角色详细信息",
						url : "viewPage.jhtml?id="+id,
						cancel:function(){}
					}
					qy.panel(opts);
				}
			}]}
			]
		}
		var grid1 =g.grid(opts1);
		grid1.loadData();
},
/**
 * '0003':'移动源',
 */
function(){
	alert(9);
},
/**
 * '0004':'溶剂使用源',
 */
function(g){
	var opts1 = {
			tab:"tab7",
			num:false,
			data:{pageSize:10,pageNumber:0},
			url:"../solventsource/query.jhtml",
			loadDataCallBack: function(data){
			},
			columns :[
				{name:"id",caption:"ID",hidden:true,type:"text"},
				{name:"emissionBaseDataVersion.year",caption:"统计年份",hidden:false,type:"text"},
				{name:"enterprise.name",caption:"单位名称",hidden:false,type:"text"},
				{name:"pollutionSource.address.city.name",caption:"城市",hidden:false,type:"text"},
				{name:"pollutionSource.address.county.name",caption:"区县",hidden:false,type:"text"},
				{name:"industryCategoryName",caption:"行业类别",hidden:false,type:"text"},
				{name:"dataSource",caption:"数据来源",hidden:false,type:"text",valueSet:"数据来源【活动数据】"},
				{name:"activeData.activeDataName",caption:"活动数据名称",hidden:false,type:"text"},
				{name:"activeData.activeData",caption:"活动量",hidden:false,type:"text"},
				{name:"activeData.unit",caption:"单位",hidden:false,type:"text"}
			]
		}
		var grid1 =g.grid(opts1);
		grid1.loadData();
},
/**
 * '0005':'农业源',
 */
function(){
	alert(11);
},
/**
 * '0006':'扬尘源',
 */
function(){
	alert(12);
},
/**
 * '0007':'生物质燃烧',
 */
function(){
	alert(13);
},
/**
 * '0008':'存储运输',
 */
function(){
	alert(14);
},
/**
 * '0009':'废弃物处理',
 */
function(){
	alert(15);
},
/**
 * '0010':'其他排放源',
 */
function(){
	alert(16);
},
]