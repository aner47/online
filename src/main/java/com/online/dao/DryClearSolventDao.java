package com.online.dao;

import com.online.entity.online.dryclear.DryClearSolvent;

/**
 * 
 * @author zuozhiping
 * 
 * dao-年有机溶剂使用量
 *
 */
public interface DryClearSolventDao extends BaseDao<DryClearSolvent, Integer> {

	void deleteDryClearSolventByEnterpriseId(Integer id) throws Exception;

}