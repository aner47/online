package com.online.service;

import com.online.entity.online.breakdownservice.BreakdownServiceSolvent;
/**
 * 有机溶剂使用情况
 *
 */
public interface BreakdownServiceSolventService extends BaseService<BreakdownServiceSolvent, Integer> {

	
	void deleteBreakdownServiceSolventByEnterpriseId(Integer id) throws Exception;
	
}