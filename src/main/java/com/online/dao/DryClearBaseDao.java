package com.online.dao;

import com.online.entity.online.dryclear.DryClearBase;

/**
 * 
 * @author zuozhiping
 * 
 * dao-干洗调查表
 *
 */
public interface DryClearBaseDao extends BaseDao<DryClearBase, Integer> {

	void deleteDryClearBaseByEnterpriseId(Integer id) throws Exception;

}