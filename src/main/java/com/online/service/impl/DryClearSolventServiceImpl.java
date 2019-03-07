package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.DryClearSolventDao;
import com.online.entity.online.dryclear.DryClearSolvent;
import com.online.service.DryClearSolventService;

/**
 * 年有机溶剂使用量
 *
 */
@Service("dryClearSolventServiceImpl")
public class DryClearSolventServiceImpl extends BaseServiceImpl<DryClearSolvent, Integer>
		implements  DryClearSolventService {
	
	@Autowired
	private DryClearSolventDao dryClearSolventDao;
	

	@Override
	public void deleteDryClearSolventByEnterpriseId(Integer id) throws Exception {
		
		dryClearSolventDao.deleteDryClearSolventByEnterpriseId(id);
		
	}

	

	

}
