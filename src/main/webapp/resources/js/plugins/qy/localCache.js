define(["ajax"],function(){
	/*var user = top.getCurrentUser();*/
	return {
		getAreas:function(url){//获得所有区域信息
			var $this = this;
			url = url || base+"/admin/area/queryAreaUtils.jhtml";
			var areaKey = "hq.areas"
			var areas = this.get(areaKey);
			if(!areas){
				areas = {};
				qy.ajax({
					url:url,
					async:false,
					callBack:function(data){
						for(var i = 0 ;i<data.length;i++){
							areas[data[i].code] = data[i].name;
						}
						$this.set(areaKey,areas);
					}
				})
			};
			return areas;
		},
		getCodeValue:function(codeName,param){
			var valueCode = this.get(codeName);
			return valueCode?valueCode:param;
		},
		setCodeValue:function(codeName,param,values){
			var valueCode = this.get(codeName);
			if(valueCode){
				valueCode = new Map();
				valueCode.set(param, values);
			}
			this.set(codeName,JSON.stringify([...valueCode]));
		},
		setUserData:function(key,value){//设置用户数据信息
			var userInfo = this.get(user)||{};
			userInfo[key] = value;
			this.set(user,userInfo);
		},
		getUserData:function(key){////设置用户数据信息
			var userInfo  = this.get(user);
			return userInfo?userInfo[key]:"";
		},
		removeUser:function(key){
			var userInfo  = this.get(user);
			delete userInfo[key];
			this.set(user,userInfo);
		},
		clear:function(){
			localStorage.clear();
		},
		set:function(key ,value){
			localStorage.setItem(key,JSON.stringify(value));
		},
		get:function(key){
			return JSON.parse(localStorage.getItem(key));
		},
		getCookie: function(key) {// getCookie(键)
			 if(document.cookie.length > 0) {
				    c_start = document.cookie.indexOf(key + "=");//获取字符串的起点
				    if(c_start != -1) {
				      c_start = c_start + key.length + 1;//获取值的起点
				      c_end = document.cookie.indexOf(";", c_start);//获取结尾处
				      if(c_end == -1) c_end = document.cookie.length;//如果是最后一个，结尾就是cookie字符串的结尾
				      return decodeURI(document.cookie.substring(c_start, c_end));//截取字符串返回
				    }
				  }
			return "";
		},
		setCookie: function(key, value, expiredays) {// setCookie(键,值,过期时间)
			var exdate = new Date();
		    exdate.setTime(Number(exdate) + expiredays);
		    document.cookie = key + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
		}
	}
})