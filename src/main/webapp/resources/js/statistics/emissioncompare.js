var colors=['#2ec7c9','#b6a2de','#7b7bbb','#5ab1ef','#ffb980','#d87a80','#4be6c7','#f9dd53','#97b552','#95706d'];
var sourceType = ['电力生产及供应','民用燃烧','工业生产','机动车','溶剂使用','农业源','扬尘','生物质燃烧','油气储运','固废处理','餐饮'];
var cityMap = {
		'510100':'成都市',
		'510300':'自贡市',
		'510400':'攀枝花市',
		'510500':'泸州市',
		'510600':'德阳市',
		'510700':'绵阳市',
		'510800':'广元市',
		'510900':'遂宁市',
		'511000':'内江市',
		'511100':'乐山市',
		'511300':'南充市',
		'511400':'眉山市',
		'511500':'宜宾市',
		'511600':'广安市',
		'511700':'达州市',
		'511800':'雅安市',
		'511900':'巴中市',
		'512000':'资阳市',
		'513200':'阿坝藏族羌族自治州',
		'513300':'甘孜藏族自治州',
		'513400':'凉山彝族自治州'
}
var pollution = ['SO2','NOx','CO','PM10','PM2.5','BC','OC','NH3','VOCs'];
var formatdata = [];
var sourceData = {};
sourceData.format = function(oriData){
	var sourceCategory = oriData.sourceCategory;
	var electricityEmission = oriData.electricityEmission[0];
	var civilBurning = oriData.civilBurning[0];
	function pushData(singleData,sourceName) {
		var d = {};
		d.source = sourceName;
		d.SO2 = getObjValue(singleData,'SO2');
		d.NOx = getObjValue(singleData,'NOx');
		d.CO = getObjValue(singleData,'CO');
		d.PM10 = getObjValue(singleData,'PM10');
		d.PM25 = getObjValue(singleData,'PM25');
		d.BC = getObjValue(singleData,'BC');
		d.OC =  getObjValue(singleData,'OC');
		d.NH3 = getObjValue(singleData,'NH3');
		d.VOCs = getObjValue(singleData,'VOC');
		formatdata.push(d);
	}
	//电力生产及民用燃烧排放量
	pushData(electricityEmission,"电力生产及供应");
	pushData(civilBurning,"民用燃烧");
	//工业生产排放量
	var fixBurning = getEmission('0001');
	var process = getEmission('0002');
	function formatBurning(){
		var produce = {};
		produce.source ="工业生产"
		produce.SO2 = getObjValue(fixBurning,'SO2') - getObjValue(electricityEmission,'SO2')- getObjValue(civilBurning,'SO2') + getObjValue(process,'SO2');
		produce.NOx =getObjValue(fixBurning,'NOx') - getObjValue(electricityEmission,'NOx')- getObjValue(civilBurning,'NOx') + getObjValue(process,'NOx');
		produce.CO = getObjValue(fixBurning,'CO') - getObjValue(electricityEmission,'CO')- getObjValue(civilBurning,'CO') + getObjValue(process,'CO');
		produce.PM10 = getObjValue(fixBurning,'PM10') - getObjValue(electricityEmission,'PM10')- getObjValue(civilBurning,'PM10') + getObjValue(process,'PM10');
		produce.PM25 = getObjValue(fixBurning,'PM25') - getObjValue(electricityEmission,'PM25')- getObjValue(civilBurning,'PM25') + getObjValue(process,'PM25');
		produce.BC = getObjValue(fixBurning,'BC') - getObjValue(electricityEmission,'BC')- getObjValue(civilBurning,'BC') + getObjValue(process,'BC');
		produce.OC = getObjValue(fixBurning,'OC') - getObjValue(electricityEmission,'OC')- getObjValue(civilBurning,'OC') + getObjValue(process,'OC');
		produce.NH3 = getObjValue(fixBurning,'NH3') - getObjValue(electricityEmission,'NH3')- getObjValue(civilBurning,'NH3') + getObjValue(process,'NH3');
		produce.VOCs = getObjValue(fixBurning,'VOC') - getObjValue(electricityEmission,'VOC')- getObjValue(civilBurning,'VOC') + getObjValue(process,'VOC');
		formatdata.push(produce);
	}
	formatBurning();
	//格式化其他源数据
	for(var i=3;i<sourceType.length;i++){
		var st = i>=10?"00"+i:'000'+i
		pushData(getEmission(st),sourceType[i]);
	}
	//根据一级源分类获取排放量
	function getEmission(sourceType){
		for(var i= 0;i<sourceCategory.length;i++){
			var sValue = sourceCategory[i];
			if(sValue.source_type === sourceType){
				return sValue;
			}
		}
	}
}

function getObjValue(obj,key){
	return (obj == "" || obj == undefined || obj == null) ? 0: obj[key];
}

//var industryArray = [];
var industrycompare = {
		
}
industrycompare.format = function(oridata){
	var categoryData ={};
	if(oridata.length >=10){
		formatdata = oridata.slice(0,9);
		categoryData.industry_category_name = '其他行业';
		for(var i=10;i<oridata.length;i++){
			categoryData.SO2 = categoryData.SO2+getObjValue(oridata[i],'SO2');
			categoryData.NOx =categoryData.NOx + getObjValue(oridata[i],'NOx');
			categoryData.CO = categoryData.CO + getObjValue(oridata[i],'CO');
			categoryData.PM10 = categoryData.PM10+getObjValue(oridata[i],'PM10');
			categoryData.PM25 = categoryData.PM25+getObjValue(oridata[i],'PM25');
			categoryData.BC = categoryData.BC+getObjValue(oridata[i],'BC');
			categoryData.OC =  categoryData.OC+getObjValue(oridata[i],'OC');
			categoryData.NH3 = categoryData.NH3+getObjValue(oridata[i],'NH3');
			categoryData.VOC = categoryData.VOC+getObjValue(oridata[i],'VOC');
		}
		formatdata.push(categoryData);
	}
}

var citydistributiondata ={
		cityToData : {}
}
citydistributiondata.format = function(oridata){
	for(var i=0;i<oridata.length;i++){
		var cityname = cityMap[oridata[i].city];
		citydistributiondata.cityToData[cityname] = oridata[i];
	}
}
	//---------end----------