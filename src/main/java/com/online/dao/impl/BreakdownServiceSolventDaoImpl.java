package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.BreakdownServiceSolventDao;
import com.online.entity.online.breakdownservice.BreakdownServiceSolvent;

/**
 *有机溶剂使用情况
 *
 */
@Repository("breakdownServiceSolventDaoImpl")
public class BreakdownServiceSolventDaoImpl extends BaseDaoImpl<BreakdownServiceSolvent, Integer> implements BreakdownServiceSolventDao {

	@Override
	public void deleteBreakdownServiceSolventByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_breakdown_service_solvent t1 WHERE enterprise =?1";
		entityManager.createNativeQuery(sql, BreakdownServiceSolvent.class).setParameter(1, id).executeUpdate();
		
	}

   


}