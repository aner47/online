package com.online.service;

import com.online.entity.online.GovernanceMeasures;
import com.online.entity.online.Enterprise.EnterpriseType;
/**
 * 
 * @author zuozhiping
 * 
 * 污染治理措施服务接口
 *
 */
public interface GovernanceMeasuresService extends BaseService<GovernanceMeasures, Integer> {

	/**
	 * 简单表格通过治理工艺名称获取治理工艺信息
	 * @param name
	 * @return
	 */
	public GovernanceMeasures saveGovernanceMeasuresByName(String name,EnterpriseType userType);
}