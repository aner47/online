package com.online.controller;

public class ScheduleBean {
	
	public enum ScheduleStatus{
		/**
		 * 进行中
		 */
		normal,
		/**
		 * 完成
		 */
		complete,
		/**
		 * 任务失败
		 */
		error
	}
	
	private ScheduleStatus scheduleStatus;
	
	/**
	 * 任务关键字
	 */
	private String key; //任务关键字
	
	/**
	 * 进度显示
	 */
	private float degree;
	
	/**
	 * 消息
	 */
	private String message;

	public ScheduleStatus getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(ScheduleStatus scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public float getDegree() {
		return degree;
	}

	public void setDegree(float degree) {
		this.degree = degree;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ScheduleBean(ScheduleStatus scheduleStatus, String key, float degree, String message) {
		super();
		this.scheduleStatus = scheduleStatus;
		this.key = key;
		this.degree = degree;
		this.message = message;
	}
	
	
	public static ScheduleBean createSchedule(String key){
		return new ScheduleBean(ScheduleStatus.normal, key, 0, "");
	}

	@Override
	public String toString() {
		return "ScheduleBean [scheduleStatus=" + scheduleStatus + ", key=" + key + ", degree=" + degree + ", message="
				+ message + "]";
	}
	
	


}
