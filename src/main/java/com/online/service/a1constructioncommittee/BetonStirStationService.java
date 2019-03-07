package com.online.service.a1constructioncommittee;

import com.online.entity.online.a1constructioncommittee.BetonStirStation;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 住建委-混凝土搅拌站信息
 */
public interface BetonStirStationService extends BaseService<BetonStirStation, Integer>, ExportService {
	
	public BetonStirStation saveBetonStirStation(BetonStirStation betonStirStation) throws Exception;
	public BetonStirStation updateBetonStirStation(BetonStirStation betonStirStation) throws Exception;

}