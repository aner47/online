package com.online.service.impl.a7trafficpolice;



import org.springframework.stereotype.Service;

import com.online.entity.online.a7trafficpolice.VehicleFlowrate;
import com.online.service.a7trafficpolice.VehicleFlowrateService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 车流量信息服务实现
 *
 */
@Service("vehicleFlowrateServiceImpl")
public class VehicleFlowrateServiceImpl extends BaseServiceImpl<VehicleFlowrate, Integer> implements VehicleFlowrateService {
	
	
	@Override
	public VehicleFlowrate saveVehicleFlowrate(VehicleFlowrate vehicleFlowrate) throws Exception {
		vehicleFlowrate.setProject(SpringUtils.getCurrentProject());
		return save(vehicleFlowrate);
	}


	@Override
	public VehicleFlowrate updateVehicleFlowrate(VehicleFlowrate vehicleFlowrate) throws Exception {
		//郑有权
		
		return update(vehicleFlowrate,"project");
	}
	
}
