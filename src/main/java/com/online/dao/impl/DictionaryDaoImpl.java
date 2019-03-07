package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.DictionaryDao;
import com.online.entity.Dictionary;


/**
 * 字典
 * @author 左志平
 *
 */
@Repository("dictionaryDaoImpl")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary, Integer> implements DictionaryDao {

	@Override
	public Dictionary findByName(String name) {
		String jpql = "select  dictionary from Dictionary  dictionary where dictionary.name=:name";
		try {
			return entityManager.createQuery(jpql,Dictionary.class).setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	 
}
