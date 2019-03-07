package com.online.service.a9agriculture;

import com.online.entity.online.a9agriculture.FishingBoat;
import com.online.service.BaseService;

/**
 * 全市渔船信息服务接口
 *
 */
public interface FishingBoatService extends BaseService<FishingBoat, Integer> {
		
		public FishingBoat saveFishingBoat(FishingBoat fishingBoat) throws Exception;
		public FishingBoat updateFishingBoat(FishingBoat fishingBoat) throws Exception;
	
}