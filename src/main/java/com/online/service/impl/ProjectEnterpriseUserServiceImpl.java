package com.online.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.ArrayStack;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.SystemUser;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.service.EnterpriseService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.SystemUserService;
import com.online.util.Constants;

/**
 * 项目企业用户维护
 */
@Service("projectEnterpriseUserServiceImpl")
public class ProjectEnterpriseUserServiceImpl extends BaseServiceImpl<ProjectEnterpriseUser, Integer> implements ProjectEnterpriseUserService {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private SystemUserService systemUserService;
	
	@Override
	public void updateStatus(Integer status, Integer projectId, Integer enterpriseid,boolean isPsccTaskUnFinish,String opinion) {
		// TODO Auto-generated method stub
		ProjectEnterpriseUser projectEnterpriseUser =  null;
		Project project = projectService.find(projectId);
		Enterprise enterprise = enterpriseService.find(enterpriseid);
		//郑有权
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project", Operator.eq, project));
		filters.add(new Filter("enterprise", Operator.eq, enterprise));
		List<ProjectEnterpriseUser> lists = findList(null, filters, null);
		if(lists != null && lists.size() >0 ){
			projectEnterpriseUser = lists.get(0);
		}
		
		if(projectEnterpriseUser != null){
			switch (status) {
			case 1:
				projectEnterpriseUser.setTaskStatus(Constants.STR_ENTERPRISE_STATUS_NOTSUBMIT);
				break;
			case 2:
				projectEnterpriseUser.setSubmitDate(new Date(System.currentTimeMillis()));
				projectEnterpriseUser.setTaskStatus(Constants.STR_ENTERPRISE_STATUS_ALREADYSUBMIT);
				break;
			case 3:
				projectEnterpriseUser.setTaskStatus(Constants.STR_ENTERPRISE_STATUS_APPROVE);
				break;
			case 4:
				projectEnterpriseUser.setTaskStatus(Constants.STR_ENTERPRISE_STATUS_NOAPPROVE);
				break;

			default:
				break;
			}
			//如果有未完成的pscc任务
			/*if(isPsccTaskUnFinish){
				projectEnterpriseUser.setPscctaskStatus(Constants.ENTERPRISE_PSCC_STATUS_UNFINISH);
			}else{
				projectEnterpriseUser.setPscctaskStatus(Constants.ENTERPRISE_PSCC_STATUS_FINISH);
			}*/
			
		}
		projectEnterpriseUser.setOpinion(opinion);
		update(projectEnterpriseUser);
		
	}

	@Override
	public List<Enterprise> findEnterpriseByUser(Integer userId) {
		
		List<Enterprise> listEnterprise = new ArrayList<>();
		
		SystemUser user = systemUserService.find(userId);
		
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("user", Operator.eq, user));
		List<ProjectEnterpriseUser> lists = findList(null, filters, null);
		if(lists != null && lists.size() >0 ){
			for(ProjectEnterpriseUser projectEnterpriseUser:lists){
				listEnterprise.add(projectEnterpriseUser.getEnterprise());
			}
		}
		
		//郑有权
		return listEnterprise;
	}

	@Override
	public List<Enterprise> findEnterpriseByProject(Integer projectId) {
		//郑有权
		List<Enterprise> listEnterprise = new ArrayList<>();
		
		Project project = projectService.find(projectId);
		
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project", Operator.eq, project));
		List<ProjectEnterpriseUser> lists = findList(null, filters, null);
		if(lists != null && lists.size() >0 ){
			for(ProjectEnterpriseUser projectEnterpriseUser:lists){
				listEnterprise.add(projectEnterpriseUser.getEnterprise());
			}
		}
		
		//郑有权
		return listEnterprise;
	}

	@Override
	public List<Enterprise> findEnterpriseByProjectAndUser(Integer projectId, Integer userId) {
		//郑有权
		List<Enterprise> listEnterprise = new ArrayList<>();
		
		Project project = projectService.find(projectId);
		SystemUser user = systemUserService.find(userId);
		
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project", Operator.eq, project));
		filters.add(new Filter("user", Operator.eq, user));
		List<ProjectEnterpriseUser> lists = findList(null, filters, null);
		if(lists != null && lists.size() >0 ){
			for(ProjectEnterpriseUser projectEnterpriseUser:lists){
				listEnterprise.add(projectEnterpriseUser.getEnterprise());
			}
		}
		
		//郑有权
		return listEnterprise;
	}

	@Override
	public ProjectEnterpriseUser saveProjectEnterpriseUser(Project project, Enterprise enterprise, SystemUser user,
			String taskStatus,Integer projectType) {
		ProjectEnterpriseUser projectEnterpriseUser = new ProjectEnterpriseUser();
		projectEnterpriseUser.setProject(project);
		projectEnterpriseUser.setEnterprise(enterprise);
		projectEnterpriseUser.setUser(user);
		projectEnterpriseUser.setTaskStatus(taskStatus);
		projectEnterpriseUser.setProjectType(projectType);
		List<ProjectEnterpriseUser> lists = findList(null, Arrays.asList(Filter.eq("project", project),Filter.eq("enterprise", enterprise)), null);
		if(lists != null && lists.size()>0){
				return lists.get(0);
		}else{
			
			return save(projectEnterpriseUser);
		}
	}

	@Override
	public void deleteByProjectEnterpriseUser(Project project, Enterprise enterprise, SystemUser user) {
		//郑有权
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project", Operator.eq, project));
		filters.add(new Filter("enterprise", Operator.eq, enterprise));
		filters.add(new Filter("user", Operator.eq, user));
		
		List<ProjectEnterpriseUser> lists = findList(null, filters, null);
		if(lists != null && lists.size() >0 ){
			delete(lists.get(0));
		}
	}

	@Override
	public ProjectEnterpriseUser findProjectTypeByProjectIdAndEnterpriseId(Integer projectId, Integer enterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, projectId));
		filters.add(new Filter("enterprise.id", Operator.eq, enterpriseId));
		List<ProjectEnterpriseUser> lists = findList(null, filters, null);
		if(lists != null && lists.size() >0 ){
			return lists.get(0);
		}
		
		return null;
	}

	@Override
	public ProjectEnterpriseUser saveOrUpdate(ProjectEnterpriseUser projectEnterpriseUser) {
		//郑有权
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project", Operator.eq, projectEnterpriseUser.getProject()));
		filters.add(new Filter("enterprise", Operator.eq, projectEnterpriseUser.getEnterprise()));
		List<ProjectEnterpriseUser> lists = findList(null, filters, null);
		if(lists != null && lists.size() >0 ){
			ProjectEnterpriseUser projectEnterpriseUserBack = lists.get(0);
			projectEnterpriseUserBack.setProjectType(projectEnterpriseUser.getProjectType());
			projectEnterpriseUserBack.setProject(projectEnterpriseUser.getProject());
			projectEnterpriseUserBack.setEnterprise(projectEnterpriseUser.getEnterprise());
			return update(projectEnterpriseUserBack);
		}else{
			return save(projectEnterpriseUser);
		}
	}

	@Override
	public void allot(Integer checkAccount, String ids) {
		if(StringUtils.isNotBlank(ids)){
			String [] enterids = ids.split(",");
			for(String id:enterids){
				ProjectEnterpriseUser projectEnterpriseUser = find(Integer.parseInt(id));
				projectEnterpriseUser.setAssessor(checkAccount);
				update(projectEnterpriseUser);
			}
		}
		//郑有权
	}

	

}