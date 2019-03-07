package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.ConstructionSiteBaseDao;
import com.online.entity.online.constructionsite.ConstructionSiteBase;
import com.online.service.ConstructionConsumptionService;
import com.online.service.ConstructionMemberService;
import com.online.service.ConstructionPeriodService;
import com.online.service.ConstructionSiteBaseService;
import com.online.service.ConstructionYardService;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 *         施工工地基本信息
 *
 */
@Service("constructionSiteBaseServiceImpl")
public class ConstructionSiteBaseServiceImpl extends BaseServiceImpl<ConstructionSiteBase, Integer>
		implements  ConstructionSiteBaseService {
	
	@Autowired
	private ConstructionSiteBaseDao constructionSiteBaseDao;
	@Autowired
	private ConstructionPeriodService constructionPeriodService;
	@Autowired
	private ConstructionConsumptionService constructionConsumptionService;
	@Autowired
	private ConstructionMemberService constructionMemberService;
	@Autowired
	private ConstructionYardService constructionYardService;
	

	@Override
	public ConstructionSiteBase findListByProjectAndEnterprise() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<ConstructionSiteBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteConstructionSiteBaseByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		constructionSiteBaseDao.deleteConstructionSiteBaseByEnterpriseId(id);
		//施工周期
		constructionPeriodService.deleteConstructionPeriodByEnterpriseId(id);
		//机械消耗
		constructionConsumptionService.deleteConstructionConsumptionByEnterpriseId(id);
		//机械数量
		constructionMemberService.deleteConstructionMemberByEnterpriseId(id);
		//露天堆场信息
		constructionYardService.deleteConstructionYardByEnterpriseId(id);
		
	}

	@Override
	public ConstructionSiteBase findListByProjectAndEnterprise(Integer ProjectId, Integer EnterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,ProjectId));
		filters.add(new Filter("enterprise.id",Operator.eq,EnterpriseId));
		List<ConstructionSiteBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	

	

}
