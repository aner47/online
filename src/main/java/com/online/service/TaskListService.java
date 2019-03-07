package com.online.service;

import com.online.entity.online.TaskList;
/**
 * 
 * @author mars
 * 
 * 每日任务清单服务接口
 *
 */
public interface TaskListService extends BaseService<TaskList, Integer> {
	
	/**
	 * 创建任务
	 * @author 郑有权
	 * @date 创建时间：2017年9月25日 下午2:23:16 
	 * @param taskList
	 * @return
	 */
	public TaskList saveTaskList(TaskList taskList);
	
	
	
	/**
	 * 指派任务
	 * @author 郑有权
	 * @date 创建时间：2017年9月20日 上午11:14:25 
	 * @param taskId			任务id
	 * @param taskAccount		任务分配人
	 * @param disposeAccount	任务处理人
	 */
	public TaskList designate(Integer taskId,String taskAccount,String disposeAccount);
	
	/**
	 * 编辑任务
	 * @author 郑有权
	 * @date 创建时间：2017年9月20日 下午1:55:26 
	 * @param taskList
	 */
	public TaskList updateEntery(TaskList taskList);
	
	
	/**
	 * 指派审核
	 * @author 郑有权
	 * @date 创建时间：2017年9月20日 下午1:57:53 
	 * @param taskId				任务id
	 * @param disposeAccount		任务处理人
	 * @param checkAccount			任务复核人
	 */
	public TaskList designateCheck(Integer taskId,String disposeAccount,String checkAccount);
	
	/**
	 * 审核通过
	 * @author 郑有权
	 * @date 创建时间：2017年9月20日 下午2:00:02 
	 * @param taskId				任务id
	 * @param checkAccount			任务复核人
	 * @param description			审核意见
	 * 
	 */
	public TaskList checkPass(Integer taskId,String checkAccount,String description);
	
	
	/**
	 * 审核不通过
	 * @author 郑有权
	 * @date 创建时间：2017年9月20日 下午2:02:18 
	 * @param taskId				任务id
	 * @param checkAccount			任务复核人	
	 * @param description			退回描述
	 */
	public TaskList checkNoPass(Integer taskId,String checkAccount,String description);
	
	
	/**
	 * 数据是否填写完整
	 * @author 郑有权
	 * @date 创建时间：2017年11月22日 下午5:36:02 
	 * @param taskId
	 * @return
	 */
	public boolean isCompletion(Integer taskId);
	
	
	

}