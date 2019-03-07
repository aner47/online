package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.BreakdownServiceSolventDao;
import com.online.entity.online.breakdownservice.BreakdownServiceSolvent;
import com.online.service.BreakdownServiceSolventService;

/**
 * 汽修调查表
 *
 */
@Service("breakdownServiceSolventServiceImpl")
public class BreakdownServiceSolventServiceImpl extends BaseServiceImpl<BreakdownServiceSolvent, Integer>
		implements  BreakdownServiceSolventService {
	
	@Autowired
	private BreakdownServiceSolventDao breakdownServiceSolventDao;
	
	
	


	@Override
	public void deleteBreakdownServiceSolventByEnterpriseId(Integer id) throws Exception {
		
		breakdownServiceSolventDao.deleteBreakdownServiceSolventByEnterpriseId(id);
	}

	

	

}
