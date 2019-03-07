package com.online.service.a1constructioncommittee;

import com.online.entity.online.a1constructioncommittee.HouseBuildArea;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 房屋建筑施工和竣工面积情况统计
 */
public interface HouseBuildAreaService extends BaseService<HouseBuildArea, Integer>,ExportService {
	
	public HouseBuildArea saveHouseBuildArea(HouseBuildArea houseBuildArea) throws Exception;
	public HouseBuildArea updateHouseBuildArea(HouseBuildArea houseBuildArea) throws Exception;

}