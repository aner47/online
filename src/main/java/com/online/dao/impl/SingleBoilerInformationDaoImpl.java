package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.SingleBoilerInformationDao;
import com.online.entity.online.SingleBoilerInformation;

/**
 * 
 * @author 左志平
 * 
 * dao-锅炉信息实现
 *
 */
@Repository("singleBoilerInformationDaoImpl")
public class SingleBoilerInformationDaoImpl extends BaseDaoImpl<SingleBoilerInformation, Integer> implements SingleBoilerInformationDao {

	@Override
	public void deleteSingleBoilerInformationByEnterpriseId(Integer id) throws Exception {

		String sql = "delete t1 FROM ol_single_boiler_information t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, SingleBoilerInformation.class).setParameter(1, id).executeUpdate();
	}

   


}