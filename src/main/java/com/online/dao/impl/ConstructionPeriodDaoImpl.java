package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ConstructionPeriodDao;
import com.online.entity.online.constructionsite.ConstructionPeriod;

/**
 * 
 * @author 左志平
 * 
 * dao-施工周期
 *
 */
@Repository("constructionPeriodDaoImpl")
public class ConstructionPeriodDaoImpl extends BaseDaoImpl<ConstructionPeriod, Integer> implements ConstructionPeriodDao {

	@Override
	public void deleteConstructionPeriodByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_construction_period t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, ConstructionPeriod.class).setParameter(1, id).executeUpdate();
		
	}

	

   


}