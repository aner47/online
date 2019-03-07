package com.online.service;

import com.online.entity.online.constructionsite.ConstructionConsumption;
/**
 * 
 * @author zuozhiping
 * 
 * 机械消耗
 *
 */
public interface ConstructionConsumptionService extends BaseService<ConstructionConsumption, Integer> {

	
	void deleteConstructionConsumptionByEnterpriseId(Integer id) throws Exception;
	
}