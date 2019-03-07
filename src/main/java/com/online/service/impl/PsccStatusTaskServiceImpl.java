package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.online.PsccStatusTaskManage;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.PsccStatusTaskService;
import com.online.util.Constants;

/**
 * Pscc任务状态管理
 */
@Service("psccStatusTaskServiceImpl")
public class PsccStatusTaskServiceImpl extends BaseServiceImpl<PsccStatusTaskManage, Integer> implements PsccStatusTaskService{

	
	@Autowired
    private ProjectEnterpriseUserService projectEnterpriseUserService;
	
	@Override
	public void updateStatusFinish(Integer taskListId) {
		
		List<Filter> filters = new ArrayList<>();
   	 	filters.add(new Filter("psccTaskId", Operator.eq, taskListId));
   	 	
   		List<PsccStatusTaskManage> taskList= findList(null, filters, null);
   		for(PsccStatusTaskManage psccStatusTaskManage:taskList){
   			psccStatusTaskManage.setPsccTaskStatus(Constants.ENTERPRISE_PSCC_STATUS_FINISH);
   			update(psccStatusTaskManage);
   			
   			//根据项目，企业id，未完成状态，查询。
   			List<Filter> unFinishfilters = new ArrayList<>();
   			unFinishfilters.add(new Filter("projectId", Operator.eq, psccStatusTaskManage.getProjectId()));
   			unFinishfilters.add(new Filter("enterpriseId", Operator.eq, psccStatusTaskManage.getEnterpriseId()));
   			unFinishfilters.add(new Filter("psccTaskStatus", Operator.eq, Constants.ENTERPRISE_PSCC_STATUS_UNFINISH));
   	   	 	
   	   	 	ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(psccStatusTaskManage.getProjectId(), psccStatusTaskManage.getEnterpriseId());
	      	 List<PsccStatusTaskManage> unFinishList= findList(null, unFinishfilters, null);
	      	 //如果pscc任务状态配置有未完成的,则企业项目用户配置中的状态为未完成
	      	 if(unFinishList != null && unFinishList.size()>0){
	      		 projectEnterpriseUser.setPscctaskStatus(Constants.ENTERPRISE_PSCC_STATUS_UNFINISH);
	   		 }else{
	   			 projectEnterpriseUser.setPscctaskStatus(Constants.ENTERPRISE_PSCC_STATUS_FINISH);
	   		 }
	      	 
	      	 projectEnterpriseUserService.update(projectEnterpriseUser);
   		}
		
	}

	@Override
	public void savePsccStatusTaskManage(PsccStatusTaskManage psccStatusTaskManage) {
		//郑有权
		List<Filter> filters = new ArrayList<>();
   	 	filters.add(new Filter("psccTaskId", Operator.eq, psccStatusTaskManage.getPsccTaskId()));
   	    filters.add(new Filter("enterpriseId", Operator.eq, psccStatusTaskManage.getEnterpriseId()));
   	    
   	    List<PsccStatusTaskManage> list= findList(null, filters, null);
   	    if(list != null && list.size()==0){
   	    	save(psccStatusTaskManage);
   	    }
   	    
   	 
	}

}
