package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.PetrolSalesDao;
import com.online.entity.online.petrol.PetrolSales;
import com.online.service.PetrolSalesService;

/**
 * 加油站调查表
 *
 */
@Service("petrolSalesServiceImpl")
public class PetrolSalesServiceImpl extends BaseServiceImpl<PetrolSales, Integer>
		implements  PetrolSalesService {
	
	@Autowired
	private PetrolSalesDao petrolSalesDao;
	


	@Override
	public void deletePetrolSalesByEnterpriseId(Integer id) throws Exception {
		
		petrolSalesDao.deletePetrolSalesByEnterpriseId(id);
		
	}

	

	

}
