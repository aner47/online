package com.online.service;

import java.util.List;

import com.online.entity.DictionaryData;

/**
 * 下来框接口
 * 
 * @author DEV2
 *
 */
public interface SelectService {
	
	public List<DictionaryData> getSelectData(String param);

}
