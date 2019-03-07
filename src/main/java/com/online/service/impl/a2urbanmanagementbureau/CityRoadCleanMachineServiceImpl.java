package com.online.service.impl.a2urbanmanagementbureau;

import org.springframework.stereotype.Service;

import com.online.entity.online.a2urbanmanagementbureau.CityRoadCleanMachine;
import com.online.service.a2urbanmanagementbureau.CityRoadCleanMachineService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 城管局-城市道路保洁信息机械化统计表
 */
@Service("cityRoadCleanMachineServiceImpl")
public class CityRoadCleanMachineServiceImpl extends BaseServiceImpl<CityRoadCleanMachine, Integer>
		implements CityRoadCleanMachineService{

	
	@Override
	public CityRoadCleanMachine saveCityRoadCleanMachine(CityRoadCleanMachine cityRoadCleanMachine) throws Exception {
		cityRoadCleanMachine.setProject(SpringUtils.getCurrentProject());
		return save(cityRoadCleanMachine);
	}


	@Override
	public CityRoadCleanMachine updateCityRoadCleanMachine(CityRoadCleanMachine cityRoadCleanMachine) throws Exception {
		//郑有权
		return update(cityRoadCleanMachine,"project");
	}

	

}
