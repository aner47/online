package com.online.service.a7trafficpolice;

import com.online.entity.online.a7trafficpolice.VehicleFlowrate;
import com.online.service.BaseService;

/**
 * 车流量信息服务接口
 *
 */
public interface VehicleFlowrateService extends BaseService<VehicleFlowrate, Integer> {
		
		public VehicleFlowrate saveVehicleFlowrate(VehicleFlowrate vehicleFlowrate) throws Exception;
		public VehicleFlowrate updateVehicleFlowrate(VehicleFlowrate vehicleFlowrate) throws Exception;
	
}