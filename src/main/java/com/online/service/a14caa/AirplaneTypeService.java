package com.online.service.a14caa;

import com.online.entity.online.Enterprise;
import com.online.entity.online.a14caa.AirplaneType;
import com.online.entity.online.a14caa.CivilAviation;
import com.online.service.BaseService;

/**
 * 飞机机型服务接口
 *
 */
public interface AirplaneTypeService extends BaseService<AirplaneType, Integer> {
		
		public AirplaneType saveAirplaneType(AirplaneType airplaneType) throws Exception;
		public AirplaneType updateAirplaneType(AirplaneType airplaneType) throws Exception;
		
		public void updateEnterprise(CivilAviation civilAviation,Enterprise enterprise) throws Exception;
	
}