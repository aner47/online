package com.online.service.a1constructioncommittee;

import com.online.entity.online.a1constructioncommittee.CityMaterialsStation;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 住建委-混凝土搅拌站信息
 */
public interface CityMaterialsStationService extends BaseService<CityMaterialsStation, Integer>,ExportService {
	
	public CityMaterialsStation saveCityMaterialsStation(CityMaterialsStation cityMaterialsStation) throws Exception;
	public CityMaterialsStation updateCityMaterialsStation(CityMaterialsStation cityMaterialsStation) throws Exception;

}