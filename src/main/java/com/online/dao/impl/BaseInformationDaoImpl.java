package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.BaseInformationDao;
import com.online.entity.online.BaseInformation;

/**
 * 
 * @author 左志平
 * 
 * dao-生产实现
 *
 */
@Repository("baseInformationDaoImpl")
public class BaseInformationDaoImpl extends BaseDaoImpl<BaseInformation, Integer> implements BaseInformationDao {

	@Override
	public void deleteBaseInformationByEnterpriseId(Integer enterpriseId) throws Exception {
		//郑有权
		//生产信息
		String sql = "delete t1 FROM ol_base_info t1 WHERE enterprise =?1";
		entityManager.createNativeQuery(sql, BaseInformation.class).setParameter(1, enterpriseId).executeUpdate();
		
	}



}