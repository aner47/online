package com.online;

public class DataAccessException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7999175570239284446L;

	/**
	 * 错误代码
	 */
	private String code;
	
	/**
	 * 描述
	 */
	private String desciption;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public DataAccessException(String code, String desciption) {
		super();
		this.code = code;
		this.desciption = desciption;
	}

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.desciption = message;
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
		this.desciption = message;
	}

	public DataAccessException(String message) {
		super(message);
		this.desciption = message;
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
	
	

}
