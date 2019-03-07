package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.DryClearBaseDao;
import com.online.entity.online.dryclear.DryClearBase;

/**
 * 
 * @author 左志平
 * 
 * dao-干洗调查表
 *
 */
@Repository("dryClearBaseDaoImpl")
public class DryClearBaseDaoImpl extends BaseDaoImpl<DryClearBase, Integer> implements DryClearBaseDao {

	@Override
	public void deleteDryClearBaseByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_dry_clear_base t1 WHERE enterprise =?1";
	    entityManager.createNativeQuery(sql, DryClearBase.class).setParameter(1, id).executeUpdate();
		
	}

   


}