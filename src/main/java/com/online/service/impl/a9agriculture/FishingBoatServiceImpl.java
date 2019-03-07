package com.online.service.impl.a9agriculture;



import org.springframework.stereotype.Service;

import com.online.entity.online.a9agriculture.FishingBoat;
import com.online.service.a9agriculture.FishingBoatService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 全市渔船信息服务实现
 *
 */
@Service("fishingBoatServiceImpl")
public class FishingBoatServiceImpl extends BaseServiceImpl<FishingBoat, Integer> implements FishingBoatService {
	
	
	@Override
	public FishingBoat saveFishingBoat(FishingBoat fishingBoat) throws Exception {
		fishingBoat.setProject(SpringUtils.getCurrentProject());
		return save(fishingBoat);
	}


	@Override
	public FishingBoat updateFishingBoat(FishingBoat fishingBoat) throws Exception {
		//郑有权
		
		return update(fishingBoat,"project");
	}
	
}
