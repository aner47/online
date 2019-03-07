package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.DryClearSolventDao;
import com.online.entity.online.dryclear.DryClearSolvent;

/**
 * 
 * @author 左志平
 * 
 * dao-年有机溶剂使用量
 *
 */
@Repository("dryClearSolventDaoImpl")
public class DryClearSolventDaoImpl extends BaseDaoImpl<DryClearSolvent, Integer> implements DryClearSolventDao {

	@Override
	public void deleteDryClearSolventByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_dry_clear_solvent t1 WHERE enterprise =?1";
	    entityManager.createNativeQuery(sql, DryClearSolvent.class).setParameter(1, id).executeUpdate();
		
	}

   


}