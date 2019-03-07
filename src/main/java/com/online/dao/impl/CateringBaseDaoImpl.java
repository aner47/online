package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.CateringBaseDao;
import com.online.entity.online.catering.CateringBase;

/**
 * 餐饮基本信息
 */
@Repository("cateringBaseDaoImpl")
public class CateringBaseDaoImpl extends BaseDaoImpl<CateringBase, Integer> implements CateringBaseDao {

	@Override
	public void deleteCateringBaseByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_catering_base t1 WHERE enterprise =?1";
	    entityManager.createNativeQuery(sql, CateringBase.class).setParameter(1, id).executeUpdate();
		
	}

   


}