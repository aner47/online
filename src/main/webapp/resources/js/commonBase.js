// hp = hp ? hp : {};
// (function(){
// 	// 判断是否为移动端，是移动端添加 mobileDevice 类，不是移动端则移除 mobileDevice 类
// 	hq.browserRedirect() {
// 		var sUserAgent = navigator.userAgent.toLowerCase();
// 		var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
// 		var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
// 		var bIsMidp = sUserAgent.match(/midp/i) == "midp";
// 		var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
// 		var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
// 		var bIsAndroid = sUserAgent.match(/android/i) == "android";
// 		var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
// 		var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
// 		if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
// 			console.log('Mobile device');
// 			document.body.className = document.body.className + " mobileDevice";
// 		} else {
// 			console.log('pc');
// 			document.body.className = document.body.className.replace(/mobileDevice/g,"");
// 		}
// 	}
// })()
// 判断是否为移动端，是移动端添加 mobileDevice 类，不是移动端则移除 mobileDevice 类
function browserRedirect() {
	var sUserAgent = navigator.userAgent.toLowerCase();
	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	var bIsAndroid = sUserAgent.match(/android/i) == "android";
	var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
		console.log('Mobile device');
		document.body.className = document.body.className + " mobileDevice";
	} else {
		console.log('pc');
		document.body.className = document.body.className.replace(/mobileDevice/g,"");
	}
}
// 启动全屏模式  
// launchFullScreen(document.documentElement); // 整个页面 
// 全屏
function launchFullScreen(element) {  
	if(element.requestFullScreen) {  
		element.requestFullScreen();  
	} else if(element.mozRequestFullScreen) {  
		element.mozRequestFullScreen();  
	} else if(element.webkitRequestFullScreen) {  
		element.webkitRequestFullScreen();  
	} else if(element.msRequestFullscreen){
		element.msRequestFullscreen();
	}
}
// 取消全屏
function cancelFullscreen() {  
	if(document.cancelFullScreen) {  
		document.cancelFullScreen();  
	} else if(document.mozCancelFullScreen) {  
		document.mozCancelFullScreen();  
	} else if(document.webkitCancelFullScreen) {  
		document.webkitCancelFullScreen();  
	} else if(document.msExitFullscreen){
		document.msExitFullscreen();
	}
}
// 获取到页面左边的距离
function getOffsetX(event){
	var evt = event||window.event;
	var srcObj = evt.target || evt.srcElement;
	if (evt.offsetX){
		return evt.offsetX;
	}else{
		var rect = srcObj.getBoundingClientRect();
		var clientx = evt.clientX;
		return clientx - rect.left;
	}
}
// 获取某年某月的天数
function getDaycount(year,month){
	var d = new Date(year,month,0);
	var daycount = d.getDate();
	return daycount; 
}
// 跳至第二天刷新
function refreshNewDay(fun,argumentArr){
	var d = new Date();
	var hours =d.getHours();
	var minutes = hours * 60 + d.getMinutes();
	var seconds = minutes * 60 + d.getSeconds();
	var milliseconds = (seconds)*60 + d.getMilliseconds();
	var remainingTime = 86400000 - milliseconds;
	function tempFun(){
		fun.apply(function(){},argumentArr);
		clearTimeout(t);
		t = setTimeout(function(){
			tempFun();
		},86400000);
	}
	var t=setTimeout(function(){
		tempFun();
	},remainingTime);
}
// 几天后的日期
function dateOfDaysLater(num,separator){
	if (num && typeof num !== 'number') {
		num = parseInt(num);
	};
	num = num || 0;
	if (separator && typeof separator !== 'string') {
		separator = String(separator);
	};
	separator = separator || '-';
	var d = new Date();
	var date = d.getDate();
	d.setDate(date + num);
	return d.getFullYear()+separator+(d.getMonth()+1)+separator+d.getDate();
}
// 几天后的日期对象
function laterObj(num){
	if (num && typeof num !== 'number') {
		num = parseInt(num);
	};
	num = num || 0;
	var d = new Date();
	var date = d.getDate();
	d.setDate(date + num);
	return d;
}
// 根据日期对象返回年月日时分秒
function getFormatDate(d,fmt){
	// author: meizz
	var o = {   
		"Y+" : d.getFullYear(),                 // 年份
		"M+" : d.getMonth()+1,                 // 月份
		"D+" : d.getDate(),                    // 日
		"h+" : d.getHours(),                   // 小时
		"m+" : d.getMinutes(),                 // 分
		"s+" : d.getSeconds(),                 // 秒
		"q+" : Math.floor((d.getMonth()+3)/3), // 季度
		"S"  : d.getMilliseconds()             // 毫秒
	};
	for(var k in o){
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("0000"+ o[k]).substr(-RegExp.$1.length)));
		}
	}
	return fmt; 
}
// 多级对象检测 object check a.b.c objC(a,'b','c')
function objC(){
	if (!arguments.length) {
		return '';
	};
	var obj = arguments[0];
	for (var i = 1; i < arguments.length; i++) {
		if (typeof obj !== "object" || !obj) {
			return '';
		};
		obj = obj[arguments[i]];
	};
	if (!obj) {
		return '';
	};
	return obj;
}
// 判断是否为火狐浏览器( 滚动事件为 DOMMouseScroll ，其他为 mousewheel )
function isFirefox(){
    if (navigator.userAgent.indexOf('Firefox') >= 0) {  
        return true;
    } else {  
        return false; 
    }
}
// 对数字进行处理，每隔三个，加一个逗号
function toThousands(num) {  
	var nums = (num || 0).toString().split('.');
	// num = nums[0];
	// var result = [ ], counter = 0;  
	// num = (num || 0).toString().split('');  
	// for (var i = num.length - 1; i >= 0; i--) {  
	// 	counter++;  
	// 	result.unshift(num[i]);  
	// 	if (!(counter % 3) && i != 0) { result.unshift(','); }  
	// }  
	// num = result.join(''); 
	num = (parseFloat(nums[0]) || 0).toLocaleString();
	if (nums[1]) {
		num += '.' + nums[1];
	};
	return  num;
}
// 数组去空
function arrayRemoveNullValue(arr){
	for(var i = 0;i<arr.length;i++){
		if(arr[i]==''||arr[i]==null||typeof(arr[i])==undefined){
			arr.splice(i,1);
			i=i-1;
		}
	}
}
// 数组去重
function unique(arr){
	var res = [];
	var json = {};
	for(var i = 0; i < arr.length; i++){
		if(!json[arr[i]]){
			res.push(arr[i]);
			json[arr[i]] = 1;
		}
	}
	return res;
}
// 对象的复制
function deepCopy(obj) {
    if (typeof obj !== 'object') return;
    var newObj = obj instanceof Array ? [] : {};
    for (var key in obj) {
        if (obj.hasOwnProperty(key)) {
            newObj[key] = typeof obj[key] === 'object' ? deepCopy(obj[key]) : obj[key];
        }
    }
    return newObj;
}
// 数字长度
function numberLength(num){
	num = parseFloat(num) || 0;
	var l = 0;
	if (num<1) {
		return l;
	};
	while(num>=1){
		num = num/10;
		l++;
	}
	return l;
}
// 向上5，10，50，等“取整”
function roundingInterval(num){
	var l = numberLength(num);
	if (num/Math.pow(10,l-1) <= 5) {
		return 5*Math.pow(10,l-1);
	}else{
		return 1*Math.pow(10,l);
	};
}
// 去除多余逗号
function symbolUnique(str){
	str = str || '';
	str = str.replace(/,+/,',');
	str = str.replace(/^,/,'');
	str = str.replace(/,$/,'');
	return str;
}
// 小数转化为百分比字符串
function percentage(num,l){
	num = parseFloat(num) || 0;
	l = l || 2;
	var str = '';
	num = num * 100;
	str = num.toFixed(l);
	return str + '%';
}
// 延时多次请求
function delayedRequest(callBack,isOk,n){
	n = parseInt(n) || 10;
	if (typeof isOk !== 'function') {
		return;
	};
	var t = setInterval(function(){
		if(isOk()){
			if (typeof callBack === 'function') {
				callBack();
			};
			clearInterval(t);
		}else if(n > 0){
			n--;
		}else{
			clearInterval(t);
		}
	},10);
}
// 保留n位小数位 fixedNum(1.234,2)
function fixedNum(num,n,isStr){
	n = parseInt(n) || 2;
	num = parseFloat(num) || 0;
	num = num.toFixed(n);
	if (isStr) {
		return num;
	};
	num = parseFloat(num) || 0;
	return num;
}
// 将科学计数法转化为字符串 
// scientific notation （科学计数法）exponent （指数）
// 将数字按"E"或"e"分割为 a 和 b，在将 a 按 "."分割为 c 和 d，然后根据 b 去对 c 和 d 进行操作
function exponentNumToString(exponentNum){
	exponentNum = parseFloat(exponentNum) || 0;
	// 数字部分 a，指数部分 b，整数部分 c，小数部分 d
	var a,b,c,d,arr;
	// 判断是否为科学计数法
	if ((exponentNum.toString().indexOf("E") != -1) || (exponentNum.toString().indexOf("e") != -1)) {
		if (exponentNum.toString().indexOf("E") != -1) {
			arr = exponentNum.toString().split("E");
		}else{
			arr = exponentNum.toString().split("e");
		};
		a = arr[0];
		b = arr[1];
		arr = a.split(".");
		c = arr[0];
		d = arr[1] || '';
		return abcdToString(a,b,c,d);
	}else{
		return exponentNum ? exponentNum.toString() : '';
	};
	// 根据数字部分 a，指数部分 b，整数部分 c，和小数部分 d
	function abcdToString(a,b,c,d){
		b = parseFloat(b);
		if(b>0){
			for (var i = 0; i < b; i++) {
				if (d) {
					c += d.slice(0,1);
					d = d.slice(1);
				}else{
					c+= "0";
				};
			};
			return d ? (c+"."+d) : (c);
		}else if (b<0) {
			var tempb = Math.abs(b);
			for (var i = 0; i < tempb; i++) {
				if (c) {
					d = c.slice(-1) + d;
					c = c.slice(0,-1);
				}else{
					d = "0" + d;
				};
			};
			return c ? (c+"."+d) : ("0."+d);
		}else{
			return a;
		};
	}
}