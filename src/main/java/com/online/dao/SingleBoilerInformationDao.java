package com.online.dao;

import com.online.entity.online.SingleBoilerInformation;

/**
 * 
 * @author zuozhiping
 * 
 * dao-单独锅炉信息
 *
 */
public interface SingleBoilerInformationDao extends BaseDao<SingleBoilerInformation, Integer> {

	void deleteSingleBoilerInformationByEnterpriseId(Integer id) throws Exception;


}