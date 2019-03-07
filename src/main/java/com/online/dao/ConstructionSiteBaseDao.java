package com.online.dao;

import com.online.entity.online.constructionsite.ConstructionSiteBase;

/**
 * 
 * @author zuozhiping
 * 
 * dao-施工工地基本信息
 *
 */
public interface ConstructionSiteBaseDao extends BaseDao<ConstructionSiteBase, Integer> {

	void deleteConstructionSiteBaseByEnterpriseId(Integer id) throws Exception;

}