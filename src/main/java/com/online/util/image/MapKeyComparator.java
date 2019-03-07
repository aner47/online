package com.online.util.image;

import java.util.Comparator;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2019年1月11日 下午12:00:52 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class MapKeyComparator implements Comparator<String>{
	@Override
    public int compare(String str1, String str2) {
        
        return str1.compareTo(str2);
    }
}
