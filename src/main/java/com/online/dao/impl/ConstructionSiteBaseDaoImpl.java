package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ConstructionSiteBaseDao;
import com.online.entity.online.constructionsite.ConstructionSiteBase;

/**
 * 
 * @author 左志平
 * 
 * dao-施工工地基本信息
 *
 */
@Repository("constructionSiteBaseDaoImpl")
public class ConstructionSiteBaseDaoImpl extends BaseDaoImpl<ConstructionSiteBase, Integer> implements ConstructionSiteBaseDao {

	@Override
	public void deleteConstructionSiteBaseByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_construction_site t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, ConstructionSiteBase.class).setParameter(1, id).executeUpdate();
		
	}

   


}