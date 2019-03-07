package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.ConstructionPeriodDao;
import com.online.entity.online.constructionsite.ConstructionPeriod;
import com.online.service.ConstructionPeriodService;

/**
 *施工周期
 *
 */
@Service("constructionPeriodServiceImpl")
public class ConstructionPeriodServiceImpl extends BaseServiceImpl<ConstructionPeriod, Integer>
		implements  ConstructionPeriodService {
	
	@Autowired
	private ConstructionPeriodDao constructionPeriodDao;

	
	@Override
	public void deleteConstructionPeriodByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		constructionPeriodDao.deleteConstructionPeriodByEnterpriseId(id);
	}

	

	

}
