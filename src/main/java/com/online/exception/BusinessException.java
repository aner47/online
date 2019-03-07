package com.online.exception;
	/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年7月26日 下午5:08:55 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class BusinessException extends RuntimeException{

	public BusinessException(String message){
        super(message);
    }
	
}
