package com.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.SystemRole;
import com.online.entity.SystemUser;
import com.online.entity.SystemUser.UserType;
import com.online.entity.online.TaskList;
import com.online.entity.online.TaskListBase.TaskStatus;
import com.online.service.PsccStatusTaskService;
import com.online.service.SelectService;
import com.online.service.SystemRoleService;
import com.online.service.SystemUserService;
import com.online.service.TaskHistoryService;
import com.online.service.TaskListService;
import com.online.util.Constants;

/**
 * 
 * @author mars
 * 
 *         每日任务清单服务实现
 *
 */
@Service("taskListServiceImpl")
public class TaskListServiceImpl extends BaseServiceImpl<TaskList, Integer> implements TaskListService, SelectService {

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private TaskHistoryService taskHistoryService;
    
   
    
    @Autowired
    private PsccStatusTaskService psccStatusTaskService;
    
    
    @Override
    public List<DictionaryData> getSelectData(String param) {
    	
        List<SystemUser> findAll = systemUserService.findByRole("system", param);
        
        List<DictionaryData> list = new ArrayList<>();
        if(findAll != null && findAll.size()>0){
        	for (SystemUser systemUser : findAll) {
                DictionaryData aData = new DictionaryData();
                aData.setCode(systemUser.getId() + "");
                aData.setCodeValue(systemUser.getUsername());
                list.add(aData);
            }
        }
        
        return list;
    }

	@Override
	public TaskList designate(Integer taskId, String taskAccount, String disposeAccount) {
		// TODO Auto-generated method stub
		//郑有权
		TaskList taskList =  find(taskId);
		taskList.setStatus(TaskStatus.active);
		taskList.setTaskAccount(taskAccount);
		taskList.setDisposeAccount(disposeAccount);
		
		return update(taskList);
//		taskHistoryService.insertEntery(taskList, "分配");
		
	}

	
	@Override
	public TaskList updateEntery(TaskList taskList) {
		// TODO Auto-generated method stub
		//郑有权
	   
		 return update(taskList,"description","status", "taskAccount", "disposeAccount", "checkAccount");
		
		 
	}

	@Override
	public TaskList designateCheck(Integer taskId, String disposeAccount, String checkAccount) {
		// TODO Auto-generated method stub
		//郑有权
		TaskList taskList =  find(taskId);
		taskList.setStatus(TaskStatus.pendingreview);
		taskList.setDisposeAccount(disposeAccount);
		taskList.setCheckAccount(checkAccount);
		taskList.setDescription("");
		
		return update(taskList);
//		taskHistoryService.insertEntery(taskList, "指定到审核");
	}

	@Override
	public TaskList checkPass(Integer taskId, String checkAccount,String description) {
		// TODO Auto-generated method stub
		//郑有权
		TaskList taskList =  find(taskId);
		taskList.setStatus(TaskStatus.Audited);
		taskList.setCheckAccount(checkAccount);
		taskList.setDescription(description);
		TaskList taskListback = update(taskList);
		psccStatusTaskService.updateStatusFinish(taskListback.getId());
		return taskListback;
//		taskHistoryService.insertEntery(taskList, "审核通过");
	}

	@Override
	public TaskList checkNoPass(Integer taskId, String checkAccount, String description) {
		// TODO Auto-generated method stub
		//郑有权
		TaskList taskList =  find(taskId);
		taskList.setStatus(TaskStatus.active);
		taskList.setCheckAccount("");
		taskList.setCheckAccount(checkAccount);
		taskList.setDescription(description);
		
		return update(taskList);
//		taskHistoryService.insertEntery(taskList, "审核不通过");
	}

	@Override
	public TaskList saveTaskList(TaskList taskList) {
		//郑有权
		taskList.setStatus(TaskStatus.assigned);
		TaskList taskListback = save(taskList);
		taskHistoryService.insertEntery(taskListback,"新建","","");
		return taskListback;
	}

	@Override
	public boolean isCompletion(Integer taskId) {
		//郑有权
		TaskList taskListback = find(taskId);
		if( taskListback!= null && (Constants.TASK_LIST_Completion).equals(taskListback.getIsCompletion())){
			return true;
		}
		
		return false;
	}

	

}
