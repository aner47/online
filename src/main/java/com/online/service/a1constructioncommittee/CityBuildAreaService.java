package com.online.service.a1constructioncommittee;

import com.online.entity.online.a1constructioncommittee.CityBuildArea;
import com.online.service.BaseService;
/**
 * 市政项目建筑施工和竣工面积情况统计
 */
public interface CityBuildAreaService extends BaseService<CityBuildArea, Integer> {
	
	public CityBuildArea saveCityBuildArea(CityBuildArea cityBuildArea) throws Exception;
	public CityBuildArea updateCityBuildArea(CityBuildArea cityBuildArea) throws Exception;

}