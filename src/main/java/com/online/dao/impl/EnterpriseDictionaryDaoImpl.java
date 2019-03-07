package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.EnterpriseDictionaryDao;
import com.online.entity.EnterpriseDictionary;
import com.online.entity.online.Enterprise;


/**
 * 企业名录库
 */
@Repository("enterpriseDictionaryDaoImpl")
public class EnterpriseDictionaryDaoImpl extends BaseDaoImpl<EnterpriseDictionary, Integer> implements EnterpriseDictionaryDao {

	@Override
	public void setNull() {
		//郑有权
		String jpql = "UPDATE sys_enterprise_dictionary SET enterprise_dictionary_rep = NULL";
		try {
			 entityManager.createNativeQuery(jpql, EnterpriseDictionary.class).executeUpdate();
		} catch (Exception e) {
			
		}
	}

}
