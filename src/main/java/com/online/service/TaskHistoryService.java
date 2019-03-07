package com.online.service;

import com.online.entity.online.TaskHistory;
import com.online.entity.online.TaskList;
/**
 * 任务历史
 */
public interface TaskHistoryService extends BaseService<TaskHistory, Integer> {
	
	/**
	 * 保存任务历史
	 * @author 郑有权
	 * @date 创建时间：2017年9月20日 下午12:44:37 
	 * @param taskList		任务
	 * @param operating		操作
	 * @param operatingPeople		操作人
	 * @param currentPeople		当前处理人
	 */
	public void insertEntery(TaskList taskList,String operating,String operatingPeople,String currentPeople);
	

}