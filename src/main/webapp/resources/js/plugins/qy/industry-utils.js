define(["ajax"],function(){
	var localKey = "online.industrys";
	// var localUserIndustryKey = "eim.userindustry";
	var industrys = localStorage.getItem(localKey) ? JSON.parse(localStorage.getItem(localKey)) : '';
	if(!industrys){
		industrys = {};
		qy.ajax({
			url:base+"/common/allCategory.jhtml",
			async:false,
			callBack:function(data){
				for(var i = 0 ;i<data.length;i++){
					industrys[data[i].code] = data[i];
				}
				localStorage.setItem(localKey,JSON.stringify(industrys))
			}
		})
	};
	// var userIndustryCode = top.getCurrentIndustryCode();
	// var enlargeLevel = {provinces:8,city:10,county:12,street:14};
	// var industryLevel = {provinces:"省",city:"市",county:"区县",street:"街道"};
	return {
		getIndustryNameByCode:function(industryCode,defaultValue="未知行业"){//通过行业代码获得名称
			if(!industryCode)return defaultValue;
			return industrys[industryCode]?industrys[industryCode].name:defaultValue;
		},
		getName:function(industryCode,defaultValue="未知行业"){//通过行业代码获得名称
			return this.getIndustryNameByCode(industryCode,defaultValue);
		},
		getAll:function(){
			return industrys;
		}
	}
})