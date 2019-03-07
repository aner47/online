package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.NonRoadDao;
import com.online.entity.online.general.NonRoad;

/**
 * 普查表非道路机械
 */
@Repository("nonRoadDaoImpl")
public class NonRoadDaoImpl extends BaseDaoImpl<NonRoad, Integer> implements NonRoadDao {

	@Override
	public void deleteNonRoadByEnterpriseId(Integer id) throws Exception {
		String sql = "delete t1 FROM ol_non_road t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, NonRoad.class).setParameter(1, id).executeUpdate();
	}

   


}