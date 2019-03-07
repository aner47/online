package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.BeastsBirdsDao;
import com.online.entity.online.beastsbirds.BeastsBirds;
import com.online.service.BeastsBirdsService;

/**
 * 畜禽养殖场
 */
@Service("beastsBirdsServiceImpl")
public class BeastsBirdsServiceImpl extends BaseServiceImpl<BeastsBirds, Integer>
		implements  BeastsBirdsService {
	
	@Autowired
	private BeastsBirdsDao beastsBirdsDao;
	

	@Override
	public void deleteBeastsBirdsByEnterpriseId(Integer id) throws Exception {
		
		beastsBirdsDao.deleteBeastsBirdsByEnterpriseId(id);
		
	}

	

	

}
