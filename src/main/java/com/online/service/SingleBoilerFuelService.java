package com.online.service;

import com.online.entity.online.SingleBoilerFuel;
/**
 * 
 * @author zuozhiping
 * 
 * 锅炉信息服务接口
 *
 */
public interface SingleBoilerFuelService extends BaseService<SingleBoilerFuel, Integer> {
	
	void deleteSingleBoilerFuelByEnterpriseId(Integer id) throws Exception;
	
}