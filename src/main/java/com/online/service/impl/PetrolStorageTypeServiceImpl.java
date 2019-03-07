package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.PetrolStorageTypeDao;
import com.online.entity.online.petrol.PetrolStorageType;
import com.online.service.PetrolStorageTypeService;

/**
 * 加油站调查表
 *
 */
@Service("petrolStorageTypeServiceImpl")
public class PetrolStorageTypeServiceImpl extends BaseServiceImpl<PetrolStorageType, Integer>
		implements  PetrolStorageTypeService {
	
	@Autowired
	private PetrolStorageTypeDao petrolStorageTypeDao;
	


	@Override
	public void deletePetrolStorageTypeByEnterpriseId(Integer id) throws Exception {
		
		petrolStorageTypeDao.deletePetrolStorageTypeByEnterpriseId(id);
		
	}

	

	

}
