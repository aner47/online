package com.online.service.impl.a14caa;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.entity.online.Enterprise;
import com.online.entity.online.a14caa.AirportMachinery;
import com.online.service.EnterpriseService;
import com.online.service.a14caa.AirportMachineryService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 机场车辆信息服务实现
 *
 */
@Service("airportMachineryServiceImpl")
public class AirportMachineryServiceImpl extends BaseServiceImpl<AirportMachinery, Integer> implements AirportMachineryService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Override
	public AirportMachinery saveAirportMachinery(AirportMachinery airportMachinery) throws Exception {
		//郑有权
		String enterpriseName = airportMachinery.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		airportMachinery.setEnterprise(enterprise);
		airportMachinery.setProject(SpringUtils.getCurrentProject());
		return save(airportMachinery);
	}


	@Override
	public AirportMachinery updateAirportMachinery(AirportMachinery airportMachinery) throws Exception {
		//郑有权
		String enterpriseName = airportMachinery.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		airportMachinery.setEnterprise(enterprise);
		return update(airportMachinery,"project");
	}
	
}
