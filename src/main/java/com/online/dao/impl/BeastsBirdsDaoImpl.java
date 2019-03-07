package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.BeastsBirdsDao;
import com.online.entity.online.beastsbirds.BeastsBirds;

/**
 * 畜禽养殖场
 */
@Repository("beastsBirdsDaoImpl")
public class BeastsBirdsDaoImpl extends BaseDaoImpl<BeastsBirds, Integer> implements BeastsBirdsDao {

	@Override
	public void deleteBeastsBirdsByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_beasts_birds t1 WHERE enterprise =?1";
	    entityManager.createNativeQuery(sql, BeastsBirds.class).setParameter(1, id).executeUpdate();
		
	}

   


}