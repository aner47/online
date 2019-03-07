package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.EnterpriseDictionary;
import com.online.entity.EnterpriseDictionaryRep;
import com.online.service.EnterpriseDictionaryRepService;
import com.online.service.EnterpriseDictionaryService;


/**
 * 企业名录库
 */
@Service("enterpriseDictionaryRepServiceImpl")
public class EnterpriseDictionaryRepServiceImpl extends BaseServiceImpl<EnterpriseDictionaryRep, Integer>
		implements EnterpriseDictionaryRepService {

	@Autowired
	EnterpriseDictionaryService enterpriseDictionaryService;
	
	@Override
	public void deleteAll() {
		//郑有权
		List<EnterpriseDictionaryRep> lists = findAll();
		lists.stream().forEach(o1->deleteRep(o1));
	}

	@Transactional
	@Override
	public void updateRep(List<Set<EnterpriseDictionary>> sets,Integer projectId) {
		for (int r = 0; r < sets.size(); r++) {
			EnterpriseDictionaryRep enterpriseDictionaryRep = new EnterpriseDictionaryRep();
			enterpriseDictionaryRep.setProjectId(projectId);
			enterpriseDictionaryRep.setGroupName("重复组" + r);
			save(enterpriseDictionaryRep);
			sets.get(r).stream().forEach(o1 ->updateEnterpriseDictionary(o1,enterpriseDictionaryRep));
		}
		
	}
	
	public  void  updateEnterpriseDictionary(EnterpriseDictionary o1,EnterpriseDictionaryRep o2){
		
		EnterpriseDictionary  enterpriseDictionaryBack = enterpriseDictionaryService.find(o1.getId());
		enterpriseDictionaryBack.setEnterpriseDictionaryRep(o2);
		enterpriseDictionaryService.update(enterpriseDictionaryBack);
	}

	
	public void  deleteRep(EnterpriseDictionaryRep rep){
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("enterpriseDictionaryRep.id", Operator.eq,rep.getId()));
		List<EnterpriseDictionary> lists = enterpriseDictionaryService.findList(null, filters, null);
		lists.stream().forEach(o1->enterpriseDictionaryService.updateRepNull(o1));
		delete(rep);
		
	}

	
}
