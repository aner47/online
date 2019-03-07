package com.online.service;

import com.online.entity.online.general.NonRoad;
/**
 *  普查表非道路机械
 */
public interface NonRoadService extends BaseService<NonRoad, Integer> {
	
	void deleteNonRoadByEnterpriseId(Integer id) throws Exception;
	
}