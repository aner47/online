package com.online.service.a2urbanmanagementbureau;

import com.online.entity.online.a2urbanmanagementbureau.CityRoadCleanMachine;
import com.online.service.BaseService;
/**
 * 城管局-城市道路保洁信息机械化统计表
 */
public interface CityRoadCleanMachineService extends BaseService<CityRoadCleanMachine, Integer> {
	
	public CityRoadCleanMachine saveCityRoadCleanMachine(CityRoadCleanMachine cityRoadCleanMachine) throws Exception;
	public CityRoadCleanMachine updateCityRoadCleanMachine(CityRoadCleanMachine cityRoadCleanMachine) throws Exception;

}