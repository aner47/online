package com.online.dao;

import com.online.entity.online.beastsbirds.BeastsBirds;

/**
 *  畜禽养殖场
 *
 */
public interface BeastsBirdsDao extends BaseDao<BeastsBirds, Integer> {

	void deleteBeastsBirdsByEnterpriseId(Integer id) throws Exception;


}