package com.online.dao;

import com.online.entity.online.constructionsite.ConstructionPeriod;

/**
 * 
 * @author zuozhiping
 * 
 * dao-施工周期
 *
 */
public interface ConstructionPeriodDao extends BaseDao<ConstructionPeriod, Integer> {

	void deleteConstructionPeriodByEnterpriseId(Integer id) throws Exception;

}