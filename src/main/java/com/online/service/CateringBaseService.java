package com.online.service;

import com.online.entity.online.catering.CateringBase;
/**
 * 餐饮基本信息
 */
public interface CateringBaseService extends BaseService<CateringBase, Integer> {

	CateringBase findListByProjectAndEnterprise();
	CateringBase findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);
	
	void deleteCateringBaseByEnterpriseId(Integer id) throws Exception;
	
}