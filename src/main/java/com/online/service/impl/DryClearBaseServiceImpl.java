package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.DryClearBaseDao;
import com.online.dao.DryClearSolventDao;
import com.online.entity.online.dryclear.DryClearBase;
import com.online.service.DryClearBaseService;
import com.online.util.SpringUtils;

/**
 * 干洗调查表
 *
 */
@Service("dryClearBaseServiceImpl")
public class DryClearBaseServiceImpl extends BaseServiceImpl<DryClearBase, Integer>
		implements  DryClearBaseService {
	
	@Autowired
	private DryClearBaseDao dryClearBaseDao;
	
	@Autowired
	private DryClearSolventDao dryClearSolventDao;
	
	
	
	
	

	@Override
	public DryClearBase findListByProjectAndEnterprise() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<DryClearBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deleteDryClearBaseByEnterpriseId(Integer id) throws Exception {
		
		dryClearBaseDao.deleteDryClearBaseByEnterpriseId(id);
		//删除有机溶剂使用量
		dryClearSolventDao.deleteDryClearSolventByEnterpriseId(id);
		
	}

	@Override
	public DryClearBase findListByProjectAndEnterprise(Integer ProjectId, Integer EnterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,ProjectId));
		filters.add(new Filter("enterprise.id",Operator.eq,EnterpriseId));
		List<DryClearBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	

	

}
