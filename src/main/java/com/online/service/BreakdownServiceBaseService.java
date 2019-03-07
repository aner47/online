package com.online.service;

import com.online.entity.online.breakdownservice.BreakdownServiceBase;
/**
 * 
 * @author zuozhiping
 * 
 * 汽修调查表
 *
 */
public interface BreakdownServiceBaseService extends BaseService<BreakdownServiceBase, Integer> {

	BreakdownServiceBase findListByProjectAndEnterprise();
	BreakdownServiceBase findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);
	
	void deleteBreakdownServiceBaseByEnterpriseId(Integer id) throws Exception;
	
}