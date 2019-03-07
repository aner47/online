package com.online;


/**
 * 消息
 * 
 * 
 * 
 */
public class Message {

	public static final String SUCCESS_CONTENT = "请求成功";
	
	public static final int SUCCESS_CODE=20000;
	
	public static final int WARN_CODE=20001;
	
	/**
	 * 请求错误
	 */
	public static final int ERROR_CODE=10000;
	
	/**
	 * session丢失
	 */
	public static final int NO_SESSION = 10001;
	/** 类型 */
	private int code;

	/** 内容 */
	private String content;
	
	private Object entity;

	/**
	 * 构造方法
	 */
	public Message() {
	}
	/**
	 * 构造方法
	 * 
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 */
	public Message(int code, String content) {
		this.code = code;
		this.content = content;
	}

	/** 
	 * 构造方法
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 */
	public Message(Object entity) {
		this.entity = entity;
		this.code = SUCCESS_CODE;
		this.content = SUCCESS_CONTENT;
	}

	/**
	 * 构造方法
	 * 
	 * @param type
	 *            类型
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 */
	public Message( int code, String content, Object... args) {
		this.code = code;
		this.content = content;
	}

	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success(String content, Object... args) {
		return new Message(SUCCESS_CODE, content, args);
	}
	
	
	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success(String content) {
		return new Message(SUCCESS_CODE, content);
	}
	
	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success() {
		return new Message(SUCCESS_CODE, "请求成功");
	}
	
	/**
	 * 返回成功消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 成功消息
	 */
	public static Message success(String code,Object entity) {
		return new Message(SUCCESS_CODE, "请求成功");
	}

	/**
	 * 返回警告消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 警告消息
	 */
	public static Message warn(String content, Object... args) {
		return new Message(WARN_CODE, content, args);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message error(int errorCode ,String content, Object... args) {
		return new Message(errorCode, content, args);
	}
	
	/**
	 * 返回错误消息
	 * 
	 * @param content
	 *            内容
	 * @param args
	 *            参数
	 * @return 错误消息
	 */
	public static Message error(String content, Object... args) {
		return new Message(ERROR_CODE, content, args);
	}

	public static Message success(Object entity) {
		return new Message(entity);
	}

	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

}