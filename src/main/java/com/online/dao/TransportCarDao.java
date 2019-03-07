package com.online.dao;

import com.online.entity.online.general.TransportCar;

/**
 * 普查表运输车
 */
public interface TransportCarDao extends BaseDao<TransportCar, Integer> {

	void deleteTransportCarByEnterpriseId(Integer id) throws Exception;

}