package com.online.service;

import com.online.entity.online.constructionsite.ConstructionSiteBase;
/**
 * 
 * @author zuozhiping
 * 
 * 施工工地基本信息
 *
 */
public interface ConstructionSiteBaseService extends BaseService<ConstructionSiteBase, Integer> {

	ConstructionSiteBase findListByProjectAndEnterprise();
	ConstructionSiteBase findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);
	
	void deleteConstructionSiteBaseByEnterpriseId(Integer id) throws Exception;
	
}