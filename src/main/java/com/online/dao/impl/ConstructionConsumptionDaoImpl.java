package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ConstructionConsumptionDao;
import com.online.entity.online.constructionsite.ConstructionConsumption;

/**
 * 
 * @author 左志平
 * 
 * dao-机械消耗
 *
 */
@Repository("constructionConsumptionDaoImpl")
public class ConstructionConsumptionDaoImpl extends BaseDaoImpl<ConstructionConsumption, Integer> implements ConstructionConsumptionDao {

	@Override
	public void deleteConstructionConsumptionByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_construction_consumption t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, ConstructionConsumption.class).setParameter(1, id).executeUpdate();
		
	}

   


}