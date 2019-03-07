package com.online.dao;

import com.online.entity.online.constructionsite.ConstructionConsumption;

/**
 * 
 * @author zuozhiping
 * 
 * dao-机械消耗
 *
 */
public interface ConstructionConsumptionDao extends BaseDao<ConstructionConsumption, Integer> {

	void deleteConstructionConsumptionByEnterpriseId(Integer id) throws Exception;

}