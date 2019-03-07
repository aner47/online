package com.online.service;

import java.util.List;
import java.util.Set;

import com.online.entity.EnterpriseDictionary;
import com.online.entity.EnterpriseDictionaryRep;


/**
 * 企业名录库
 */
public interface EnterpriseDictionaryRepService extends BaseService<EnterpriseDictionaryRep, Integer>{
	
	
	public void deleteAll();
	
	public void updateRep(List<Set<EnterpriseDictionary>> sets,Integer projectId);
	
}
