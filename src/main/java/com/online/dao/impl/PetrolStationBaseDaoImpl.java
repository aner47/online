package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.PetrolStationBaseDao;
import com.online.entity.online.petrol.PetrolStationBase;

/**
 * 
 * @author 左志平
 * 
 * dao-加油站调查表
 *
 */
@Repository("petrolStationBaseDaoImpl")
public class PetrolStationBaseDaoImpl extends BaseDaoImpl<PetrolStationBase, Integer> implements PetrolStationBaseDao {

	@Override
	public void deletePetrolStationBaseByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_petrol_station_base t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, PetrolStationBase.class).setParameter(1, id).executeUpdate();
		
	}

   


}