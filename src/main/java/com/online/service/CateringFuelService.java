package com.online.service;

import com.online.entity.online.catering.CateringFuel;
/**
 * 餐饮燃料
 */
public interface CateringFuelService extends BaseService<CateringFuel, Integer> {

	
	void deleteCateringFuelByEnterpriseId(Integer id) throws Exception;
	
}