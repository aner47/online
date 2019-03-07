package com.online.service;

import com.online.entity.online.constructionsite.ConstructionPeriod;
/**
 * 
 * @author zuozhiping
 * 
 * 施工周期
 *
 */
public interface ConstructionPeriodService extends BaseService<ConstructionPeriod, Integer> {

	
	void deleteConstructionPeriodByEnterpriseId(Integer id) throws Exception;
	
}