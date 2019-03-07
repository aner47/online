define(["ajax"], function() {
	var localKey = "online.areas";
	var areas = localStorage.getItem(localKey) ? JSON.parse(localStorage.getItem(localKey)) : '';
	if (!areas) {
		areas = {};
		qy.ajax({
			url: base + "/common/allArea.jhtml",
			async: false,
			callBack: function(data) {
				for (var i = 0; i < data.length; i++) {
					if(data[i].level<=3){
						areas[data[i].code] = data[i];
					}
				}
				localStorage.setItem(localKey, JSON.stringify(areas))
			}
		})
	};
	var userAreaCode = "110000";
	var enlargeLevel = {
		provinces: 8,
		city: 10,
		county: 12,
		street: 14
	};
	var areaLevel = {
		provinces: "省",
		city: "市",
		county: "区县",
		street: "街道"
	};
	return {
		getEnlargeLevel: function(areaCode) { //获得放大级别
			return enlargeLevel[this.getAreaLevel(areaCode)]
		},
		getAreaLevel: function(areaCode) { //获得编码级别
			areaCode = areaCode || userAreaCode;
			if (areas[areaCode].level == 1) return "provinces";
			if (areas[areaCode].level == 2) return "city";
			if (areas[areaCode].level == 3) return "county";
			if (areas[areaCode].level == 4) return "street";

		},
		getGroupLevel: function(areaCode) { //获得编码分组字段
			areaCode = areaCode || userAreaCode;
			if (areas[areaCode].level == 1) return "city";
			if (areas[areaCode].level == 2) return "county";
			if (areas[areaCode].level == 3) return "county";
			if (areas[areaCode].level == 4) return "county";
		},
		getUserCode: function() { //获取用户当前区域
			return userAreaCode;
		},
		getUserAreaCode: function() { //获取用户当前区域
			return this.getUserCode();
		},
		getUserAreaName: function() { //获取用户当前区域名称
			return this.getAreaNameByCode(userAreaCode);
		},
		getUserLevel: function() { //获取用户当前级别
			return this.getAreaLevel(userAreaCode);
		},
		getUserAreaLevel: function() { //获取用户当前级别
			return this.getAreaLevel(userAreaCode);
		},
		getAreaLevelByCode: function(code) { //获取编码级别显示名称
			return areaLevel[code]
		},
		getAreaNameByCode: function(areaCode, defaultValue) { //通过区域代码获得名称
			defaultValue = defaultValue || "未知区域";
			if (!areaCode) return defaultValue;
			return areas[areaCode] ? areas[areaCode].name : defaultValue;
		},
		getParentCode: function(areaCode) { //返回上级编码
			if (!areaCode) {
				return ""
			}
			return areas[areaCode].parent;
		},
		getParentName: function(areaCode) { //返回上级编码名称
			var parentCode = this.getParentCode(areaCode);
			return this.getAreaNameByCode(parentCode);
		},
		getChildAreas: function(areaCode) { //返回所有下属区域
			areaCode = areaCode || userAreaCode;
			var tempAreas = areaCode.split(",");
			var result = {};
			for (var code in areas) {
				if (tempAreas.find(o => o == areas[code].parent)) {
					result[areas[code].code] = areas[code].name;
				}
			}
			return result;

		},
		getChildAreaByCode: function(areaCode, isContainSelf) { //获得所有下属区域代码
			isContainSelf = isContainSelf || false;
			if (!areaCode) return [];
			var length = 4;
			if (this.getAreaLevel(areaCode) === 'provinces') {
				length = 2;
			} else if (this.getAreaLevel(areaCode) === 'city') {
				length = 4;
			} else if (this.getAreaLevel(areaCode) === 'county') {
				length = 6;
			};
			var item = {};
			for (var key in areas) {
				if (key.slice(0, length) == areaCode.slice(0, length)) {
					if (!isContainSelf && key === areaCode) {
						continue;
					}
					if (length == 2 && '00' != key.slice(4, 6)) {
						continue;
					}

					item[key] = areas[key]
				}
			}
			return item;
		},

	}
})