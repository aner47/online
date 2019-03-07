package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.ConstructionConsumptionDao;
import com.online.entity.online.constructionsite.ConstructionConsumption;
import com.online.service.ConstructionConsumptionService;

/**
 *机械消耗
 */
@Service("constructionConsumptionServiceImpl")
public class ConstructionConsumptionServiceImpl extends BaseServiceImpl<ConstructionConsumption, Integer>
		implements  ConstructionConsumptionService {
	
	@Autowired
	private ConstructionConsumptionDao constructionConsumptionDao;

	
	@Override
	public void deleteConstructionConsumptionByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		constructionConsumptionDao.deleteConstructionConsumptionByEnterpriseId(id);
	}

	

	

}
