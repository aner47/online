package com.online.dao;

import com.online.entity.online.catering.CateringBase;

/**
 * 餐饮基本信息
 */
public interface CateringBaseDao extends BaseDao<CateringBase, Integer> {

	void deleteCateringBaseByEnterpriseId(Integer id) throws Exception;

}