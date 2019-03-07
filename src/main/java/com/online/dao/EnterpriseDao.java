package com.online.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.online.CustomFilter;
import com.online.Page;
import com.online.entity.online.Enterprise;

/**
 * 
 * @author zuozhiping
 * 
 * dao-企业
 *
 */
public interface EnterpriseDao extends BaseDao<Enterprise, Integer> {

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
	
	public void exportEnterprise() throws Exception;
	
	public List<Enterprise> findALLByAccount(List<Integer> userIds) throws Exception;
	
	public List<Map<String, Object>> findALLEnterpriseByAccount(List<Integer> userIds,String polluteSource) throws Exception;
	
	
	public List<Enterprise> findALLByProjectid(Integer projectId) throws Exception;
	
	public Page<Enterprise> findCustomPage(CustomFilter customFilter) throws Exception;
	
	public void updatestatus(Integer enterpriseId,Integer status,String opinion);
	
	public List<Map<String, Object>> findStatistics(Integer projectId, Integer type, Date start, Date end,
			String enterpriseType);

}