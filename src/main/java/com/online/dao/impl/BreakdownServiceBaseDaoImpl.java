package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.BreakdownServiceBaseDao;
import com.online.entity.online.breakdownservice.BreakdownServiceBase;

/**
 * 
 * @author 左志平
 * 
 * dao-汽修调查表
 *
 */
@Repository("breakdownServiceBaseDaoImpl")
public class BreakdownServiceBaseDaoImpl extends BaseDaoImpl<BreakdownServiceBase, Integer> implements BreakdownServiceBaseDao {

	@Override
	public void deleteBreakdownServiceBaseByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_breakdown_service_base t1 WHERE enterprise =?1";
		entityManager.createNativeQuery(sql, BreakdownServiceBase.class).setParameter(1, id).executeUpdate();
		
	}

   


}