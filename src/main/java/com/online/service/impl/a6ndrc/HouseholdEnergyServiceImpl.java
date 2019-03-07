package com.online.service.impl.a6ndrc;



import org.springframework.stereotype.Service;

import com.online.entity.online.a6ndrc.HouseholdEnergy;
import com.online.service.a6ndrc.HouseholdEnergyService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 居民能源统计信息服务实现
 *
 */
@Service("householdEnergyServiceImpl")
public class HouseholdEnergyServiceImpl extends BaseServiceImpl<HouseholdEnergy, Integer> implements HouseholdEnergyService {
	
	
	@Override
	public HouseholdEnergy saveHouseholdEnergy(HouseholdEnergy householdEnergy) throws Exception {
		householdEnergy.setProject(SpringUtils.getCurrentProject());
		return save(householdEnergy);
	}


	@Override
	public HouseholdEnergy updateHouseholdEnergy(HouseholdEnergy householdEnergy) throws Exception {
		//郑有权
		
		return update(householdEnergy,"project");
	}
	
}
