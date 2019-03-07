package com.online.util;

import java.util.ArrayList;
import java.util.List;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年2月1日 下午5:46:12 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class Test {
	
	public static void main(String[] args) {
		List<String> lists = new ArrayList<>();
		lists.add("a");
		lists.add("b");
		lists.add("c");
		lists.add("d");
		lists.add("e");
		lists.add("f");
		
		for(int i=0;i<lists.size();i++){
			for(int y = i+1;y<lists.size();y++){
				System.out.println("------"+lists.get(i) +"------"+lists.get(y));
			}
		}
	}
}
