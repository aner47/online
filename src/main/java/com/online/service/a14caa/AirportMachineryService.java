package com.online.service.a14caa;

import com.online.entity.online.a14caa.AirportMachinery;
import com.online.service.BaseService;

/**
 * 机场车辆信息服务接口
 *
 */
public interface AirportMachineryService extends BaseService<AirportMachinery, Integer> {
		
		public AirportMachinery saveAirportMachinery(AirportMachinery airportMachinery) throws Exception;
		public AirportMachinery updateAirportMachinery(AirportMachinery airportMachinery) throws Exception;
	
}