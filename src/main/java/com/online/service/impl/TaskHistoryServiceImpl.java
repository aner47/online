package com.online.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.online.entity.online.TaskHistory;
import com.online.entity.online.TaskList;
import com.online.service.TaskHistoryService;

/**
 * 任务历史
 */
@Service("taskHistoryServiceImpl")
public class TaskHistoryServiceImpl extends BaseServiceImpl<TaskHistory, Integer> implements TaskHistoryService{
	
	
	@Override
	public void insertEntery(TaskList oldEntery, String operating,String operatingPeople,String currentPeople) {
		
		//int s= 2/0;
		// TODO Auto-generated method stub
		//郑有权
		TaskHistory taskHistory = new TaskHistory();
		BeanUtils.copyProperties(oldEntery,taskHistory,new String[]{"id","createDate","modifyDate","version"});
		taskHistory.setOperating(operating);
		taskHistory.setOperatingPeople(operatingPeople);
		taskHistory.setCurrentPeople(currentPeople);
		taskHistory.setOldId(oldEntery.getId().toString());
		taskHistory.setOldCreateDate(oldEntery.getCreateDate());
		taskHistory.setOldModifyDate(oldEntery.getModifyDate());
		taskHistory.setOldVersion(oldEntery.getVersion());
		
		save(taskHistory);
	}

   
}
