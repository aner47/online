package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.SingleBoilerFuelDao;
import com.online.entity.online.SingleBoilerFuel;

/**
 * 
 * @author 左志平
 * 
 * dao-锅炉信息实现
 *
 */
@Repository("singleBoilerFuelDaoImpl")
public class SingleBoilerFuelDaoImpl extends BaseDaoImpl<SingleBoilerFuel, Integer> implements SingleBoilerFuelDao {

	@Override
	public void deleteSingleBoilerFuelByEnterpriseId(Integer id) throws Exception {
		String sql = "delete t1 FROM ol_single_boiler_fuel t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, SingleBoilerFuel.class).setParameter(1, id).executeUpdate();
	}

   


}