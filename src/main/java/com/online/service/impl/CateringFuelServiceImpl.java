package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.CateringFuelDao;
import com.online.entity.online.catering.CateringFuel;
import com.online.service.CateringFuelService;

/**
 * 餐饮燃料
 */
@Service("cateringFuelServiceImpl")
public class CateringFuelServiceImpl extends BaseServiceImpl<CateringFuel, Integer>
		implements  CateringFuelService {
	
	@Autowired
	private CateringFuelDao dryClearSolventDao;
	

	@Override
	public void deleteCateringFuelByEnterpriseId(Integer id) throws Exception {
		
		dryClearSolventDao.deleteCateringFuelByEnterpriseId(id);
		
	}

	

	

}
