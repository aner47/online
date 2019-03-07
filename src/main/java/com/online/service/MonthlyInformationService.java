package com.online.service;

import com.online.entity.online.MonthlyInformation;
/**
 * 
 * @author zuozhiping
 * 
 * 分月数据服务接口
 *
 */
public interface MonthlyInformationService extends BaseService<MonthlyInformation, Integer> {

	public void deleteByType(String type,Integer powerId);
	
}