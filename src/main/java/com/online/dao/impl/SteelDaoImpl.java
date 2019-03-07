package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.SteelDao;
import com.online.entity.online.Steel;
import com.online.entity.online.petrol.PetrolSales;

/**
 * 钢铁信息
 */
@Repository("steelDaoImpl")
public class SteelDaoImpl extends BaseDaoImpl<Steel, Integer> implements SteelDao {

	@Override
	public void deleteSteelByEnterpriseId(Integer id) throws Exception {
			//郑有权
		String sql = "delete t1 FROM ol_steel t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, Steel.class).setParameter(1, id).executeUpdate();
			
		
	}

   



}