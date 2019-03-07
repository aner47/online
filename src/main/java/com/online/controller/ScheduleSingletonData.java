package com.online.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.online.controller.ScheduleBean.ScheduleStatus;

/**
 * 进度
 * @author DEV2
 *
 */
public class ScheduleSingletonData {

	private ScheduleSingletonData(){
		
	}
	
	private static Map<String, ScheduleBean> map = new ConcurrentHashMap<>();
	
	public static void put(String key,ScheduleBean value){
		if(StringUtils.isEmpty(key)){throw new RuntimeException("key不能为空");}
		map.put(key, value);
	}
	
	public static ScheduleBean get(String key){
		if(StringUtils.isEmpty(key)){throw new RuntimeException("key不能为空");}
		return map.get(key);
	}
	
	public static void clear(String key){
		if(StringUtils.isEmpty(key)){throw new RuntimeException("key不能为空");}
		map.remove(key);
	}
	
	public static void createTask(String key){
		put(key, ScheduleBean.createSchedule(key));
	}
	
	
	public static ScheduleBean query(String key){
		ScheduleBean scheduleBean = get(key);
		
		if(scheduleBean != null && scheduleBean.getScheduleStatus() == ScheduleStatus.complete){
			clear(key);
		}
		return scheduleBean;
	}
	
	/**
	 * 更新任务状态
	 * @param key 任务关键字
	 * @param degree 进度值
	 */
	public static void updateDegree(String key,float degree){
		ScheduleBean scheduleBean = map.get(key);
		if(scheduleBean == null){
			throw new RuntimeException("没有找到【"+key+"】的任务！");
		}
		if(degree == 100){
			scheduleBean.setScheduleStatus(ScheduleStatus.complete);
			scheduleBean.setDegree(degree);
			scheduleBean.setMessage("任务完成");
		}else{
			scheduleBean.setScheduleStatus(ScheduleStatus.normal);
			scheduleBean.setDegree(degree);
			scheduleBean.setMessage("任务进行中....");
		}
	}
	
	
	public static void error(String key,String message){//任务异常
		ScheduleBean scheduleBean = map.get(key);
		if(scheduleBean == null){
			throw new RuntimeException("没有找到【"+key+"】的任务！");
		}
		scheduleBean.setMessage(message);
		scheduleBean.setScheduleStatus(ScheduleStatus.error);
	}
	
	public static void error(String key,Exception exception){//任务异常
		ScheduleBean scheduleBean = map.get(key);
		if(scheduleBean == null){
			throw new RuntimeException("没有找到【"+key+"】的任务！");
		}
		scheduleBean.setMessage(exception2String(exception));
		scheduleBean.setScheduleStatus(ScheduleStatus.error);
	}
	
    public  static String exception2String(Throwable e){   
        StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        e.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
        return sw.toString();   
    } 
}

