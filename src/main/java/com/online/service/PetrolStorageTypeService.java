package com.online.service;

import com.online.entity.online.petrol.PetrolStorageType;
/**
 * 
 * @author zuozhiping
 * 
 * 加油站调查表
 *
 */
public interface PetrolStorageTypeService extends BaseService<PetrolStorageType, Integer> {

	
	void deletePetrolStorageTypeByEnterpriseId(Integer id) throws Exception;
	
}