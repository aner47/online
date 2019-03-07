package com.online.service;

import com.online.entity.online.dryclear.DryClearSolvent;
/**
 * 
 * @author zuozhiping
 * 
 * 年有机溶剂使用量
 *
 */
public interface DryClearSolventService extends BaseService<DryClearSolvent, Integer> {

	
	void deleteDryClearSolventByEnterpriseId(Integer id) throws Exception;
	
}