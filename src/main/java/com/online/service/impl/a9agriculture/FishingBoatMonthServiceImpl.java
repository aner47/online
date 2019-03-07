package com.online.service.impl.a9agriculture;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.entity.online.Enterprise;
import com.online.entity.online.a9agriculture.FishingBoatMonth;
import com.online.service.EnterpriseService;
import com.online.service.a9agriculture.FishingBoatMonthService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 渔船每月活动水平信息服务实现
 *
 */
@Service("fishingBoatMonthServiceImpl")
public class FishingBoatMonthServiceImpl extends BaseServiceImpl<FishingBoatMonth, Integer> implements FishingBoatMonthService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Override
	public FishingBoatMonth saveFishingBoatMonth(FishingBoatMonth fishingBoatMonth) throws Exception {
		
		//郑有权
		String enterpriseName = fishingBoatMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		fishingBoatMonth.setEnterprise(enterprise);
		fishingBoatMonth.setProject(SpringUtils.getCurrentProject());
				
		return save(fishingBoatMonth);
	}


	@Override
	public FishingBoatMonth updateFishingBoatMonth(FishingBoatMonth fishingBoatMonth) throws Exception {
		//郑有权
		String enterpriseName = fishingBoatMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		fishingBoatMonth.setEnterprise(enterprise);
		return update(fishingBoatMonth,"project");
	}
	
}
