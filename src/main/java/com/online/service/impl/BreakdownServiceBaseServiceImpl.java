package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.BreakdownServiceBaseDao;
import com.online.entity.online.breakdownservice.BreakdownServiceBase;
import com.online.service.BreakdownServiceBaseService;
import com.online.service.BreakdownServiceSolventService;
import com.online.util.SpringUtils;

/**
 * 汽修调查表
 *
 */
@Service("breakdownServiceBaseServiceImpl")
public class BreakdownServiceBaseServiceImpl extends BaseServiceImpl<BreakdownServiceBase, Integer>
		implements  BreakdownServiceBaseService {
	
	@Autowired
	private BreakdownServiceBaseDao breakdownServiceBaseDao;
	@Autowired
	private BreakdownServiceSolventService breakdownServiceSolventService;
	
	
	

	@Override
	public BreakdownServiceBase findListByProjectAndEnterprise() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<BreakdownServiceBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteBreakdownServiceBaseByEnterpriseId(Integer id) throws Exception {
		
		breakdownServiceBaseDao.deleteBreakdownServiceBaseByEnterpriseId(id);
		
		breakdownServiceSolventService.deleteBreakdownServiceSolventByEnterpriseId(id);
		
		
	}

	@Override
	public BreakdownServiceBase findListByProjectAndEnterprise(Integer ProjectId, Integer EnterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,ProjectId));
		filters.add(new Filter("enterprise.id",Operator.eq,EnterpriseId));
		List<BreakdownServiceBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	

	

}
