package com.online.service.a9agriculture;

import com.online.entity.online.a9agriculture.FishingBoatMonth;
import com.online.service.BaseService;

/**
 * 渔船每月活动水平信息服务接口
 *
 */
public interface FishingBoatMonthService extends BaseService<FishingBoatMonth, Integer> {
		
		public FishingBoatMonth saveFishingBoatMonth(FishingBoatMonth fishingBoatMonth) throws Exception;
		public FishingBoatMonth updateFishingBoatMonth(FishingBoatMonth fishingBoatMonth) throws Exception;
	
}