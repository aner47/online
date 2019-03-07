package com.online.service.a1constructioncommittee;

import com.online.entity.online.a1constructioncommittee.CityBuildSite;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 市政项目建筑工地信息
 */
public interface CityBuildSiteService extends BaseService<CityBuildSite, Integer>,ExportService {
	
	public CityBuildSite saveCityBuildSite(CityBuildSite cityBuildSite) throws Exception;
	public CityBuildSite updateCityBuildSite(CityBuildSite cityBuildSite) throws Exception;

}