package com.online.service;

import com.online.entity.online.GasStove;
/**
 * 煤气炉
 */
public interface GasStoveService extends BaseService<GasStove, Integer> {
	
	GasStove saveGasStove (GasStove gasStove, Integer exhaustionHoleId);
	GasStove updateGasStove (GasStove gasStove, Integer exhaustionHoleId);
	
	void deleteGasStoveByEnterpriseId(Integer id) throws Exception;

}