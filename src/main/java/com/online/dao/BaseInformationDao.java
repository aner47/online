package com.online.dao;

import com.online.entity.online.BaseInformation;

/**
 * 
 * @author zuozhiping
 * 
 * dao-生产
 *
 */
public interface BaseInformationDao extends BaseDao<BaseInformation, Integer> {

	void deleteBaseInformationByEnterpriseId(Integer id) throws Exception;

}