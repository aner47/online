package com.online.exception;

/**
 * 任务异常信息描述类
 * @author DEV2
 *
 */
public class TaskManagerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1778468794815204869L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TaskManagerException(String message) {
		super();
		this.message = message;
	}
	
	
	
}
