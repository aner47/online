package com.online.service;

import com.online.entity.online.constructionsite.ConstructionYard;
/**
 * 
 * @author zuozhiping
 * 
 * 露天堆场信息
 *
 */
public interface ConstructionYardService extends BaseService<ConstructionYard, Integer> {

	
	void deleteConstructionYardByEnterpriseId(Integer id) throws Exception;
	
}