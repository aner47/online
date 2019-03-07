package com.online.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.online.entity.online.TaskManager;
/**
 * 
 * @author zuozhiping
 * 
 * 任务管理服务接口
 *
 */
public interface TaskManagerService extends BaseService<TaskManager, Long> {
	
	public void exportTask(String filePath,Integer projectId);
	
	
	public void reportData(TaskManager taskManager,Long repeatId,Integer enterpriseId,Integer userId)throws Exception;

	public void completeTask(Integer enterpriseId);
	public void processedByEnterprise(Integer enterpriseId);
	
	/**
	 * 分配任务
	 * @author 郑有权
	 * @date 创建时间：2018年2月5日 上午10:38:43 
	 * @param ids		任务id
	 * @param user		分配至用户
	 * @throws Exception
	 */
	public void distribution(String ids,String user,String enterpriseType,Integer projectTypeId)throws Exception;
	
	/**
	 * 创建用户并分配任务
	 * @author 郑有权
	 * @date 创建时间：2018年2月5日 下午3:22:55 
	 * @param ids
	 * @throws Exception
	 */
	public void createEnterpriseUser(String ids,String enterpriseType,Integer projectTypeId)throws Exception;
	
	public List<Map<String, Object>> progressQuery(Integer projectId);
	
	public TaskManager findByEnterpriseId(Integer enterpriseId);
	
	public void deleteTaskManager(Long taskManagerId);
	
	
	public List<Map<String, Object>> findStatistics(Integer projectId,Integer type,Date start,Date end);
	
	/**
	 * 修改企业名称
	 * @author 郑有权
	 * @date 创建时间：2018年9月25日 下午2:18:49 
	 * @param taskManager
	 */
	public void updateName(TaskManager taskManager);
}