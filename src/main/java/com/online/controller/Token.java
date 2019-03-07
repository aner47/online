package com.online.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年8月18日 下午2:11:57 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	
	boolean save() default false;
	
	boolean remove() default false;

}
