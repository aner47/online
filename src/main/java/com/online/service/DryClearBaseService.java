package com.online.service;

import com.online.entity.online.dryclear.DryClearBase;
/**
 * 
 * @author zuozhiping
 * 
 * 干洗调查表
 *
 */
public interface DryClearBaseService extends BaseService<DryClearBase, Integer> {

	DryClearBase findListByProjectAndEnterprise();
	DryClearBase findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId);
	
	void deleteDryClearBaseByEnterpriseId(Integer id) throws Exception;
	
}