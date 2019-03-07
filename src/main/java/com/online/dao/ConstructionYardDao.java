package com.online.dao;

import com.online.entity.online.constructionsite.ConstructionYard;

/**
 * 
 * @author zuozhiping
 * 
 * dao-露天堆场信息
 *
 */
public interface ConstructionYardDao extends BaseDao<ConstructionYard, Integer> {

	void deleteConstructionYardByEnterpriseId(Integer id) throws Exception;

}