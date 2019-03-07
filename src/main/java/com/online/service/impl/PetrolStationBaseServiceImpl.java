package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.PetrolStationBaseDao;
import com.online.entity.online.petrol.PetrolStationBase;
import com.online.service.PetrolSalesService;
import com.online.service.PetrolStationBaseService;
import com.online.service.PetrolStorageTypeService;
import com.online.util.SpringUtils;

/**
 * 加油站调查表
 *
 */
@Service("petrolStationBaseServiceImpl")
public class PetrolStationBaseServiceImpl extends BaseServiceImpl<PetrolStationBase, Integer>
		implements  PetrolStationBaseService {
	
	@Autowired
	private PetrolStationBaseDao petrolStationBaseDao;
	@Autowired
	private PetrolStorageTypeService petrolStorageTypeService;
	@Autowired
	private PetrolSalesService petrolSalesService;
	
	
	

	@Override
	public PetrolStationBase findListByProjectAndEnterprise() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<PetrolStationBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void deletePetrolStationBaseByEnterpriseId(Integer id) throws Exception {
		
		petrolStationBaseDao.deletePetrolStationBaseByEnterpriseId(id);
		//删除储罐类型
		petrolStorageTypeService.deletePetrolStorageTypeByEnterpriseId(id);
		//删除分月经销信息
		petrolSalesService.deletePetrolSalesByEnterpriseId(id);
		
	}

	@Override
	public PetrolStationBase findListByProjectAndEnterprise(Integer ProjectId, Integer EnterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,ProjectId));
		filters.add(new Filter("enterprise.id",Operator.eq,EnterpriseId));
		List<PetrolStationBase> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}

	

	

}
