package com.online.service.a9agriculture;

import com.online.entity.online.a9agriculture.LivestockBreeding;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 畜禽养殖信息服务接口
 *
 */
public interface LivestockBreedingService extends BaseService<LivestockBreeding, Integer>,ExportService {
		
		public LivestockBreeding saveLivestockBreeding(LivestockBreeding livestockBreeding) throws Exception;
		public LivestockBreeding updateLivestockBreeding(LivestockBreeding livestockBreeding) throws Exception;
	
}