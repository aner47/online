package com.online.service;

import com.online.entity.online.general.TransportCar;
/**
 * 普查表运输车
 */
public interface TransportCarService extends BaseService<TransportCar, Integer> {
	
	void deleteTransportCarByEnterpriseId(Integer id) throws Exception;
	
}