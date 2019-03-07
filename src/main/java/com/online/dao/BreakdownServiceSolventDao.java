package com.online.dao;

import com.online.entity.online.breakdownservice.BreakdownServiceSolvent;

/**
 *有机溶剂使用情况
 */
public interface BreakdownServiceSolventDao extends BaseDao<BreakdownServiceSolvent, Integer> {

	void deleteBreakdownServiceSolventByEnterpriseId(Integer id) throws Exception;

}