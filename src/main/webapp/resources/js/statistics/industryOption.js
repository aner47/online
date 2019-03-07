var colors=['#2ec7c9','#b6a2de','#7b7bbb','#5ab1ef','#ffb980','#d87a80','#4be6c7','#f9dd53','#97b552','#95706d'];
var sourceType = [];
var citys = ['成都市','广元市','绵阳市','资阳市','阿坝藏族羌族自治州','达州市','雅安市','甘孜藏族自治州','宜宾市','乐山市','南充市',
             '巴中市','泸州市','凉山彝族自治州','攀枝花市','眉山市','广安市','德阳市','内江市','遂宁市','自贡市'];
var pollution = ['SO2','NOx','CO','PM10','PM2.5','BC','OC','NH3','VOCs'];


//饼图
var piedata = {
    oriData : [],
    SO2 : [],
    NOx : [], 
    CO : [],
    PM10 : [],
    PM25 : [],
    BC : [],
    OC : [],
    NH3 : [],
    VOCs : []
};
piedata.format = function (oriData) {
	function pushData(key, sourceName, value) {
        piedata[key].push({value:value,name:sourceName});
    }
    for(var j=0;j<oriData.length;j++){
    	var singleData = oriData[j];
    	//console.log(singleData.name);
    	pushData('SO2',singleData['name'],singleData.SO2);
        pushData('NOx', singleData['name'], singleData.NOx);
        pushData('CO', singleData['name'], singleData.CO);
        pushData('PM10', singleData['name'], singleData.PM10);
        pushData('PM25', singleData['name'], singleData.PM25);
        pushData('BC', singleData['name'], singleData.BC);
        pushData('OC', singleData['name'], singleData.OC);
        pushData('NH3', singleData['name'], singleData.NH3);
        pushData('VOCs', singleData['name'], singleData.VOCs);
    }
}

function getObjValue(obj,key){
	return (obj == "" || obj == undefined || obj == null) ? 0: obj[key];
}
