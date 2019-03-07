package com.online.dao;

import com.online.entity.online.general.NonRoad;

/**
 * 普查表非道路机械
 */
public interface NonRoadDao extends BaseDao<NonRoad, Integer> {

	void deleteNonRoadByEnterpriseId(Integer id) throws Exception;

}