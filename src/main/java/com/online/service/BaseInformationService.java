package com.online.service;

import com.online.entity.online.BaseInformation;
/**
 * 
 * @author zuozhiping
 * 
 * 生产服务接口
 *
 */
public interface BaseInformationService extends BaseService<BaseInformation, Integer> {

	
	BaseInformation findListByProjectAndEnterprise();
	BaseInformation findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);
	
	void deleteBaseInformationByEnterpriseId(Integer id) throws Exception;
}