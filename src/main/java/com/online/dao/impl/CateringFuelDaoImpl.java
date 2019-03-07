package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.CateringFuelDao;
import com.online.entity.online.catering.CateringFuel;

/**
 * 餐饮燃料
 */
@Repository("cateringFuelDaoImpl")
public class CateringFuelDaoImpl extends BaseDaoImpl<CateringFuel, Integer> implements CateringFuelDao {

	@Override
	public void deleteCateringFuelByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_catering_fuel t1 WHERE enterprise =?1";
	    entityManager.createNativeQuery(sql, CateringFuel.class).setParameter(1, id).executeUpdate();
		
	}

   


}