package com.online.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.online.entity.online.TaskManager;

/**
 * 
 * @author zuozhiping
 * 
 * dao-任务管理
 *
 */
public interface TaskManagerDao extends BaseDao<TaskManager, Long> {

	public void completeByEnterprise(Integer enterpriseId);
	public void processedByEnterprise(Integer enterpriseId);
	
	/**
	 * 重置任务
	 * @param enterpriseId
	 */
	public void resetTask(Integer enterpriseId);
	
	public void distribution(Long id, String user,String enterpriseType,Integer projectTypeId) throws Exception;
	
	public List<Map<String, Object>> progressQuery(Integer projectId);
	
	public List<Map<String, Object>> findStatistics(Integer projectId,Integer type,Date start,Date end);
	
	public List<Map<String, Object>> findCompleteTaskManager(Integer projectId);
}