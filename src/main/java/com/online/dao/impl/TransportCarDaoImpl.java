package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.TransportCarDao;
import com.online.entity.online.general.TransportCar;

/**
 * 普查表运输车
 */
@Repository("transportCarDaoImpl")
public class TransportCarDaoImpl extends BaseDaoImpl<TransportCar, Integer> implements TransportCarDao {

	@Override
	public void deleteTransportCarByEnterpriseId(Integer id) throws Exception {
		String sql = "delete t1 FROM ol_transport_car t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, TransportCar.class).setParameter(1, id).executeUpdate();
	}

   


}