package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.SingleBoilerInformationDao;
import com.online.entity.DictionaryData;
import com.online.entity.online.SingleBoilerInformation;
import com.online.service.SelectService;
import com.online.service.SingleBoilerInformationService;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 *         锅炉信息服务实现
 *
 */
@Service("singleBoilerInformationServiceImpl")
public class SingleBoilerInformationServiceImpl extends BaseServiceImpl<SingleBoilerInformation, Integer>
		implements SelectService,SingleBoilerInformationService {

	@Autowired
	private SingleBoilerInformationDao singleBoilerInformationDao;
	
	
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<DictionaryData> list = new ArrayList<>();
		if("id".equals(param)){
			
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<SingleBoilerInformation> findList = findList(null, filters, null);
		for (SingleBoilerInformation section : findList) {
			DictionaryData data = new DictionaryData();
			data.setCode(section.getId().toString());
			data.setCodeValue(section.getId().toString());
			list.add(data);
		}
		
		}else {
			if(StringUtils.isNotEmpty(param)){
				int  singleBoilerId= Integer.parseInt(param);
				
				SingleBoilerInformation singleBoilerInformation = find(singleBoilerId);
				DictionaryData data = new DictionaryData();
				data.setCode(singleBoilerInformation.getFuelType());
				data.setCodeValue(singleBoilerInformation.getFuelType());
				list.add(data);
			}
			
			
		}
		
		
		return list;
	}

	@Override
	public void deleteSingleBoilerInformationByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		singleBoilerInformationDao.deleteSingleBoilerInformationByEnterpriseId(id);
	}

}
