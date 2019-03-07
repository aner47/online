package com.online.service.a2urbanmanagementbureau;

import com.online.entity.online.a2urbanmanagementbureau.CityRoadClean;
import com.online.service.BaseService;
/**
 * 城管局-城市道路保洁信息汇总表
 */
public interface CityRoadCleanService extends BaseService<CityRoadClean, Integer> {
	
	public CityRoadClean saveCityRoadClean(CityRoadClean cityRoadClean) throws Exception;
	public CityRoadClean updateCityRoadClean(CityRoadClean cityRoadClean) throws Exception;

}