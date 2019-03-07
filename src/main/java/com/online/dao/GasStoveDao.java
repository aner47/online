package com.online.dao;

import com.online.entity.online.GasStove;

/**
 *煤气炉
 */
public interface GasStoveDao extends BaseDao<GasStove, Integer> {
	
	void deleteGasStoveByEnterpriseId(Integer id) throws Exception;

}