package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.PetrolStorageTypeDao;
import com.online.entity.online.petrol.PetrolStorageType;

/**
 * 
 * @author 左志平
 * 
 * dao-加油站调查表
 *
 */
@Repository("petrolStorageTypeDaoImpl")
public class PetrolStorageTypeDaoImpl extends BaseDaoImpl<PetrolStorageType, Integer> implements PetrolStorageTypeDao {

	@Override
	public void deletePetrolStorageTypeByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_petrol_storage_type t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, PetrolStorageType.class).setParameter(1, id).executeUpdate();
		
	}

   


}