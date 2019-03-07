package com.online.service;

import java.util.List;

import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.SystemUser;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
/**
 * 项目企业用户
 */
public interface ProjectEnterpriseUserService extends BaseService<ProjectEnterpriseUser, Integer> {

	/**
	 * 修改项目企业用户表状态
	 * @author 郑有权
	 * @date 创建时间：2018年3月29日 下午5:46:15 
	 * @param status
	 * @param projectId
	 * @param enterpriseId 
	 * @param isPsccTaskUnFinish 是否有未完成的pscc任务
	 * @param opinion 审核意见
	 */
	public void updateStatus(Integer status,Integer projectId,Integer enterpriseId,boolean isPsccTaskUnFinish,String opinion);
	
	public List<Enterprise> findEnterpriseByUser(Integer userId);
	
	public List<Enterprise> findEnterpriseByProject(Integer projectId);
	
	public List<Enterprise> findEnterpriseByProjectAndUser(Integer projectId,Integer userId);
	
	/**
	 * 保存项目企业用户配置关系
	 * @author 郑有权
	 * @date 创建时间：2018年6月1日 下午1:52:33 
	 * @param project	项目
	 * @param enterprise	企业	
	 * @param user			用户	
	 * @param taskStatus	企业任务状态
	 * @return
	 */
	public ProjectEnterpriseUser saveProjectEnterpriseUser(Project project,Enterprise  enterprise,SystemUser user,String taskStatus,Integer projectType);
	
	public void deleteByProjectEnterpriseUser(Project project,Enterprise enterprise,SystemUser user);
	
	
	public ProjectEnterpriseUser findProjectTypeByProjectIdAndEnterpriseId(Integer projectId,Integer enterpriseId);
	
	public ProjectEnterpriseUser saveOrUpdate(ProjectEnterpriseUser projectEnterpriseUser);
	
	public void allot(Integer checkAccount,String ids);

}