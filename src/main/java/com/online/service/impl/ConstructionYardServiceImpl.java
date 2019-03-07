package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.ConstructionYardDao;
import com.online.entity.online.constructionsite.ConstructionYard;
import com.online.service.ConstructionYardService;

/**
 *露天堆场信息
 *
 */
@Service("constructionYardServiceImpl")
public class ConstructionYardServiceImpl extends BaseServiceImpl<ConstructionYard, Integer>
		implements  ConstructionYardService {
	
	@Autowired
	private ConstructionYardDao constructionYardDao;

	
	@Override
	public void deleteConstructionYardByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		constructionYardDao.deleteConstructionYardByEnterpriseId(id);
	}



	

	

}
