package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.GovernanceMeasures;
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.service.GovernanceMeasuresService;
import com.online.service.SelectService;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 污染治理措施服务实现
 *
 */
@Service("governanceMeasuresServiceImpl")
public class GovernanceMeasuresServiceImpl extends BaseServiceImpl<GovernanceMeasures, Integer> implements GovernanceMeasuresService,SelectService {
	
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		if(param.equals("normal")){
			filters.add(new Filter("enterprise.id",Operator.eq,null));
		}else{
			filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
			filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		}
		List<GovernanceMeasures> findList = findList(null, filters, null);
		List<DictionaryData>  list = new ArrayList<>();
		DictionaryData aData1 = new DictionaryData();
		aData1.setCode("-1");
		aData1.setCodeValue("无");
		list.add(aData1);
		for (GovernanceMeasures g : findList) {
			DictionaryData aData = new DictionaryData();
			aData.setCode(g.getId().toString());
			aData.setCodeValue("治理"+g.getGmno()+"-" + g.getName());
			list.add(aData);
		}
		return list;
	}

	@Override
	@Transactional
	public GovernanceMeasures saveGovernanceMeasuresByName(String name,EnterpriseType userType) {
			GovernanceMeasures governanceMeasures = new GovernanceMeasures();
			governanceMeasures.setName(name);
			governanceMeasures.setUserType(userType);
			return save(governanceMeasures);
	}
}
