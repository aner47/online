package com.online.dao;

import com.online.entity.Dictionary;

/**
 * 字典
 * @author 左志平
 *
 */
public interface DictionaryDao extends BaseDao<Dictionary, Integer> {
	/**
	 * 通过名称查找字典
	 * @param name 字典名称
	 * @return
	 */
	Dictionary findByName(String name);
}
