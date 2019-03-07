package com.online.pscctask;

import com.online.entity.online.TaskList;

/**
 * 配置插入
 */
public interface ITaskListInsert {
	
	/**
	 * 生成配置文件
	 * @author 郑有权
	 * @date 创建时间：2017年9月27日 下午3:05:01 
	 * @param taskList
	 */
	public void insertConfig(TaskList taskList);
	
	/**
	 * 配置文件是否存在
	 * @author 郑有权
	 * @date 创建时间：2017年9月27日 下午3:05:24 
	 * @param taskList
	 * @return
	 */
	public boolean isExist(TaskList taskList);

}
