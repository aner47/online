package com.online.dao;

import com.online.entity.online.catering.CateringFuel;

/**
 * 餐饮燃料信息
 */
public interface CateringFuelDao extends BaseDao<CateringFuel, Integer> {

	void deleteCateringFuelByEnterpriseId(Integer id) throws Exception;

}