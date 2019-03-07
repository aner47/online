package com.online.service;

import com.online.entity.online.petrol.PetrolStationBase;
/**
 * 
 * @author zuozhiping
 * 
 * 加油站调查表
 *
 */
public interface PetrolStationBaseService extends BaseService<PetrolStationBase, Integer> {

	PetrolStationBase findListByProjectAndEnterprise();
	PetrolStationBase findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);
	
	void deletePetrolStationBaseByEnterpriseId(Integer id) throws Exception;
	
}