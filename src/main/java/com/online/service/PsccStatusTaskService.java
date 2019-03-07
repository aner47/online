package com.online.service;

import com.online.entity.online.PsccStatusTaskManage;
/**
 * 任务历史
 */
public interface PsccStatusTaskService extends BaseService<PsccStatusTaskManage, Integer> {
	
	
	public void updateStatusFinish(Integer taskListId);
	
	public void savePsccStatusTaskManage(PsccStatusTaskManage psccStatusTaskManage);

}