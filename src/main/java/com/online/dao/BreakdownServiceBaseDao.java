package com.online.dao;

import com.online.entity.online.breakdownservice.BreakdownServiceBase;

/**
 * 
 * @author zuozhiping
 * 
 * dao-汽修调查表
 *
 */
public interface BreakdownServiceBaseDao extends BaseDao<BreakdownServiceBase, Integer> {

	void deleteBreakdownServiceBaseByEnterpriseId(Integer id) throws Exception;

}