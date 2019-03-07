package com.online.service;

import java.util.List;

import com.online.entity.Dictionary;
import com.online.entity.DictionaryData;


/**
 * 字典服务
 * @author 左志平
 *
 */
public interface DictionaryService extends BaseService<Dictionary, Integer>{
	
	List<DictionaryData> findByname(String name,String param);
	
}
