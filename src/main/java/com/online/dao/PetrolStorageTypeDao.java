package com.online.dao;

import com.online.entity.online.petrol.PetrolStorageType;

/**
 * 
 * @author zuozhiping
 * 
 * dao-加油站调查表
 *
 */
public interface PetrolStorageTypeDao extends BaseDao<PetrolStorageType, Integer> {

	void deletePetrolStorageTypeByEnterpriseId(Integer id) throws Exception;

}