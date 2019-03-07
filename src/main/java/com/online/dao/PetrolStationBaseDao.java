package com.online.dao;

import com.online.entity.online.petrol.PetrolStationBase;

/**
 * 
 * @author zuozhiping
 * 
 * dao-加油站调查表
 *
 */
public interface PetrolStationBaseDao extends BaseDao<PetrolStationBase, Integer> {

	void deletePetrolStationBaseByEnterpriseId(Integer id) throws Exception;

}