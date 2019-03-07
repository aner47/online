package com.online.service;

import com.online.entity.online.beastsbirds.BeastsBirds;
/**
 * 畜禽养殖场
 *
 */
public interface BeastsBirdsService extends BaseService<BeastsBirds, Integer> {

	
	void deleteBeastsBirdsByEnterpriseId(Integer id) throws Exception;
	
}