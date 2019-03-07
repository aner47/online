package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.CateringBaseDao;
import com.online.entity.online.catering.CateringBase;
import com.online.service.CateringBaseService;
import com.online.service.CateringFuelService;
import com.online.util.SpringUtils;

/**
 * 餐饮燃料基本信息
 */
@Service("cateringBaseServiceImpl")
public class CateringBaseServiceImpl extends BaseServiceImpl<CateringBase, Integer>
		implements  CateringBaseService {
	
	@Autowired
	private CateringBaseDao cateringBaseDao;
	
	@Autowired
	private CateringFuelService  cateringFuelService;
	
	
	
	
	

	@Override
	public CateringBase findListByProjectAndEnterprise() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<CateringBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteCateringBaseByEnterpriseId(Integer id) throws Exception {
		
		cateringBaseDao.deleteCateringBaseByEnterpriseId(id);
		//删除餐饮燃料
		cateringFuelService.deleteCateringFuelByEnterpriseId(id);
		
	}

	@Override
	public CateringBase findListByProjectAndEnterprise(Integer ProjectId, Integer EnterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,ProjectId));
		filters.add(new Filter("enterprise.id",Operator.eq,EnterpriseId));
		List<CateringBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	

	

}
