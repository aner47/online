package com.online.service.impl.a2urbanmanagementbureau;

import org.springframework.stereotype.Service;

import com.online.entity.online.a2urbanmanagementbureau.CityRoadClean;
import com.online.service.a2urbanmanagementbureau.CityRoadCleanService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 城管局-城市道路保洁信息汇总表
 */
@Service("cityRoadCleanServiceImpl")
public class CityRoadCleanServiceImpl extends BaseServiceImpl<CityRoadClean, Integer>
		implements CityRoadCleanService{

	
	@Override
	public CityRoadClean saveCityRoadClean(CityRoadClean CityRoadClean) throws Exception {
		CityRoadClean.setProject(SpringUtils.getCurrentProject());
		return save(CityRoadClean);
	}


	@Override
	public CityRoadClean updateCityRoadClean(CityRoadClean CityRoadClean) throws Exception {
		//郑有权
		return update(CityRoadClean,"project");
	}

	

}
