var colors=['#2ec7c9','#b6a2de','#7b7bbb','#5ab1ef','#ffb980','#d87a80','#a9f0e2','#f9dd53','#97b552','#95706d'];
var citys = ['成都市','广元市','绵阳市','资阳市','阿坝藏族羌族自治州','达州市','雅安市','甘孜藏族自治州','宜宾市','乐山市','南充市',
'巴中市','泸州市','凉山彝族自治州','攀枝花市','眉山市','广安市','德阳市','内江市','遂宁市','自贡市'];
var areas ={
	"四川省":['成都市','广元市','阿坝藏族羌族自治州','资阳市','绵阳市','达州市','自贡市','雅安市','宜宾市','乐山市','南充市','巴中市','泸州市','凉山彝族自治州','攀枝花市','眉山市','广安市','德阳市','内江市','遂宁市','甘孜藏族自治州'],
	"成都市":['锦江区','青羊区','金牛区','武侯区','成华区','高新区','天府新区','龙泉驿区新都区','温江区','双流区','简阳市','都江堰市','邛崃市','彭州市','崇州市','金堂县','郫都区','大邑县','蒲江县','新津县']
}
var classification = ['电力生产及供应','工业生产','机动车','农业源','溶剂使用','生物质燃烧','油气储运','餐饮'];
var pollution = ['SO2','NOx','CO','PM10','PM2.5','BC','OC','NH3','VOCs'];


function DataConvert(){
	
}

DataConvert.prototype.industryDistribution = function(oridata,sources){
	
}

//城市总量分布
DataConvert.prototype.cityTotalDistribution = function(){
	
}

//城市占比分析
DataConvert.prototype.cityPercent = function(){
	
}

//城市多数据比较
DataConvert.prototype.cityMoreDataCompare = function(){
	
}

//城市贡献率
DataConvert.prototype.ContributionRate = function(){
	
}

//区域贡献率
DataConvert.prototype.areaContributionRate = function(){
	
}


DataConvert.prototype.getCityEmission = function(obj,city,sourcetype){
	
}

DataConvert.prototype.getObjValue = function(obj,key){
	return (obj == "" || obj == undefined || obj == null) ? 0: obj[key];
}






