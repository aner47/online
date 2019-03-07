package com.online.service.a9agriculture;

import com.online.entity.online.a9agriculture.FertilizerPesticide;
import com.online.service.BaseService;

/**
 * 化肥与农药施用信息服务接口
 *
 */
public interface FertilizerPesticideService extends BaseService<FertilizerPesticide, Integer> {
		
		public FertilizerPesticide saveFertilizerPesticide(FertilizerPesticide fertilizerPesticide) throws Exception;
		public FertilizerPesticide updateFertilizerPesticide(FertilizerPesticide fertilizerPesticide) throws Exception;
	
}