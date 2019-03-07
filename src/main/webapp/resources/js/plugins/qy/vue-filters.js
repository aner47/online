define([ ], function() {
	return { // 数据过滤器
		toLowerCase:function(value){
			return value?value.toLowerCase():""
		},
		toUpperCase:function(value){
			return value?value.toUpperCase():""
		},
		scm2Number:function(value){//科学计数法转数字
			 try {
				 var num = new Number(value);  
			     return num; 
			} catch (e) {
				return value;
			}
		   
		},  
		numberFormat : function(value, length) {
			length = length || 4;
			if (!value) {
				return value;
			}
			if (!isNaN(value)) {// isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
				value = Number(value).toFixed(length);
				return value;
			} else {
				return value;
			}
		},
		percentage: function(value,number){
			number = number || 2;
			var value = (parseFloat(value) * 100).toFixed(number);
			return !isNaN(value)?value +"%":"";  
		},
		percentage2: function(value,num) {
			var num = num || 2;
		    var value = (parseFloat(value) * 100).toFixed(num);
		    return !isNaN(value) ? (value == "0.00" ? "0" : value): "";
		},
		percentage3: function(value,num, retainNum){
		    var num = num || 2;
		    var retainNum = retainNum || 100;
		    if (typeof value !== 'undefined' && typeof value === 'number') {

		      if (value % 1 === 0) {
		        return value;
		      } else {
		        var value = (parseFloat(value) * retainNum).toFixed(num);
		        return value;
		      }

		    } else {
		    	return '';
		    }
		},
		getUnit : function(value) {
			if (value) {
				return value;
			} else {
				return '';
			}
		}

	}
})