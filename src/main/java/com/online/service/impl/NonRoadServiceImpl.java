package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.NonRoadDao;
import com.online.entity.online.general.NonRoad;
import com.online.service.NonRoadService;

/**
 *普查表非道路机械
 */
@Service("nonRoadServiceImpl")
public class NonRoadServiceImpl extends BaseServiceImpl<NonRoad, Integer>
		implements  NonRoadService {
	
	@Autowired
	private NonRoadDao nonRoadDao;

	@Override
	public void deleteNonRoadByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		nonRoadDao.deleteNonRoadByEnterpriseId(id);
	}
	

}
