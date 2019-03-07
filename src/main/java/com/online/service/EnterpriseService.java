package com.online.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.online.CustomFilter;
import com.online.Page;
import com.online.entity.SystemUser;
import com.online.entity.online.Enterprise;
/**
 * 
 * @author zuozhiping
 * 
 * 企业服务接口
 *
 */
public interface EnterpriseService extends BaseService<Enterprise, Integer> {

	/**
	 * 通过企业名查询企业
	 * @param enterpriseName
	 * @return
	 */
	public Enterprise findByEnterpriseName(String enterpriseName);

	/**
	 * 根据用户Id查询企业
	 * @param userId
	 * @return
	 */
	public Enterprise findByAccount(Integer userId);
	
	public void deleteByEnterpriseId(Integer id) throws Exception;
	
	
	public void exportEnterprise(OutputStream out,List<SystemUser> systemUser,List<Enterprise> enterprises) throws Exception;
	
	public List<Enterprise> findALLByAccount(List<Integer> userIds) throws Exception;
	
	public List<Map<String, Object>> findALLEnterpriseByAccount(List<Integer> userIds,String polluteSource) throws Exception;
	
	public boolean isexist(Enterprise enterprise) throws Exception;
	
	public List<Enterprise> findALLByProjectid(Integer projectId) throws Exception;
	
	public Page<Enterprise> findCustomPage(CustomFilter customFilter) throws Exception;
	
	public void updatestatus(Integer projectId,Integer enterpriseId,Integer status,String opinion);
	
	public boolean isSave(Integer enterpriseId,Integer projectId,String enterpriseType);
	
	public boolean isBaseSave(Integer enterpriseId,Integer projectId,String enterpriseType);
	
	public Enterprise saveEnterprise(Enterprise enterprise,Integer projectType,Integer userId,Integer projectId) throws Exception;
	
	public Enterprise updateEnterprise(Enterprise enterprise) throws Exception;
	
	/**
	 * 提交企业
	 * @author 郑有权
	 * @date 创建时间：2018年6月1日 下午2:41:48 
	 * @param projectId
	 * @param enterpriseId
	 * @param status	状态
	 * @param opinion	意见
	 * @throws Exception
	 */
	public void submitEnterprise(Integer projectId,Integer enterpriseId,Integer status)throws Exception;

	
	public List<Map<String, Object>> findStatistics(Integer projectId,Integer type,Date start,Date end,String enterpriseType);
	
	
	public boolean isFinish(Integer enterpriseId);
	
	/**
	 * 通过企业名查询企业,没有则创建一个企业。
	 * @param enterpriseName
	 * @return
	 */
	public Enterprise findSaveByEnterpriseName(String enterpriseName);
	
}