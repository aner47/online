package com.online.service;

import com.online.entity.online.SimpleBaseInformation;
/**
 * 简版企业基本信息
 *
 */
public interface SimpleBaseInformationService extends BaseService<SimpleBaseInformation, Integer> {

	SimpleBaseInformation findListByProjectAndEnterprise();
	
	SimpleBaseInformation findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);
	public void deleteByProjectAndEnterprise();
}