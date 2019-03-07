package com.online.service;

import com.online.entity.online.petrol.PetrolSales;
/**
 * 
 * @author zuozhiping
 * 
 * 加油站调查表
 *
 */
public interface PetrolSalesService extends BaseService<PetrolSales, Integer> {

	
	void deletePetrolSalesByEnterpriseId(Integer id) throws Exception;
	
}