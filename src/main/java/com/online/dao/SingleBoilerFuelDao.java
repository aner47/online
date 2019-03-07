package com.online.dao;

import com.online.entity.online.SingleBoilerFuel;

/**
 * 
 * @author zuozhiping
 * 
 * dao-单独锅炉燃料信息
 *
 */
public interface SingleBoilerFuelDao extends BaseDao<SingleBoilerFuel, Integer> {

	void deleteSingleBoilerFuelByEnterpriseId(Integer id) throws Exception;

}