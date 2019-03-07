package com.online.service.impl.a1constructioncommittee;

import org.springframework.stereotype.Service;

import com.online.entity.online.a1constructioncommittee.CityBuildArea;
import com.online.service.a1constructioncommittee.CityBuildAreaService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 市政项目建筑施工和竣工面积情况统计
 */
@Service("cityBuildAreaServiceImpl")
public class CityBuildAreaServiceImpl extends BaseServiceImpl<CityBuildArea, Integer>
		implements CityBuildAreaService{

	
	@Override
	public CityBuildArea saveCityBuildArea(CityBuildArea cityBuildArea) throws Exception {
		//郑有权
		cityBuildArea.setProject(SpringUtils.getCurrentProject());
		return save(cityBuildArea);
	}


	@Override
	public CityBuildArea updateCityBuildArea(CityBuildArea cityBuildArea) throws Exception {
		//郑有权
		return update(cityBuildArea,"project");
	}

	

}
