package com.online.service.a14caa;

import com.online.entity.online.a14caa.CivilAviation;
import com.online.service.BaseService;

/**
 * 民航飞机信息服务接口
 *
 */
public interface CivilAviationService extends BaseService<CivilAviation, Integer> {
		
		public CivilAviation saveCivilAviation(CivilAviation civilAviation) throws Exception;
		public CivilAviation updateCivilAviation(CivilAviation civilAviation) throws Exception;
		public CivilAviation updateAirplanetype(CivilAviation civilAviation) throws Exception;
	
}