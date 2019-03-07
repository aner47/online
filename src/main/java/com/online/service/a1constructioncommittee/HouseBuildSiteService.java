package com.online.service.a1constructioncommittee;

import com.online.entity.online.a1constructioncommittee.HouseBuildSite;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 房屋建筑工地信息
 */
public interface HouseBuildSiteService extends BaseService<HouseBuildSite, Integer> , ExportService {
	
	public HouseBuildSite saveHouseBuildSite(HouseBuildSite houseBuildSite) throws Exception;
	public HouseBuildSite updateHouseBuildSite(HouseBuildSite houseBuildSite) throws Exception;
	
}