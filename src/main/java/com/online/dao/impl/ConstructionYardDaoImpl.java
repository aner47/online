package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ConstructionYardDao;
import com.online.entity.online.constructionsite.ConstructionYard;

/**
 * 
 * @author 左志平
 * 
 * dao-施工周期
 *
 */
@Repository("constructionYardDaoImpl")
public class ConstructionYardDaoImpl extends BaseDaoImpl<ConstructionYard, Integer> implements ConstructionYardDao {

	@Override
	public void deleteConstructionYardByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_construction_yard t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, ConstructionYard.class).setParameter(1, id).executeUpdate();
		
	}

   


}