package com.online.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年7月26日 下午5:07:08 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
    @ResponseBody
    String handleException(){
		System.out.println("aaaaaaaaaaaaaaaa");
        return "Exception Deal!";
    }
	
}
