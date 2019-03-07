package com.online.dao;

import com.online.entity.online.petrol.PetrolSales;

/**
 * 
 * @author zuozhiping
 * 
 * dao-加油站调查表
 *
 */
public interface PetrolSalesDao extends BaseDao<PetrolSales, Integer> {

	void deletePetrolSalesByEnterpriseId(Integer id) throws Exception;

}