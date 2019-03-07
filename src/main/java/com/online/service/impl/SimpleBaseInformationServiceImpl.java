package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.SimpleBaseInformationDao;
import com.online.entity.online.SimpleBaseInformation;
import com.online.service.SimpleBaseInformationService;
import com.online.util.SpringUtils;
/**
 * 简版企业基本信息
 *
 */
@Service("simpleBaseInformationServiceImpl")
public class SimpleBaseInformationServiceImpl extends BaseServiceImpl<SimpleBaseInformation, Integer> implements SimpleBaseInformationService {
	
	@Autowired
	private SimpleBaseInformationDao simpleBaseInformationDao;
	
	
	@Override
	public SimpleBaseInformation findListByProjectAndEnterprise() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<SimpleBaseInformation> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public SimpleBaseInformation findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,ProjectId));
		filters.add(new Filter("enterprise.id",Operator.eq,EnterpriseId));
		List<SimpleBaseInformation> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteByProjectAndEnterprise() {
		simpleBaseInformationDao.deleteByProjectAndEnterprise(SpringUtils.getProjectId(), SpringUtils.getPrincipal().getEnterpriseId());;
	}

	
}
