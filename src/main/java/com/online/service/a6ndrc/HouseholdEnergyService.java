package com.online.service.a6ndrc;

import com.online.entity.online.a6ndrc.HouseholdEnergy;
import com.online.service.BaseService;

/**
 * 居民能源统计信息服务接口
 *
 */
public interface HouseholdEnergyService extends BaseService<HouseholdEnergy, Integer> {
		
		public HouseholdEnergy saveHouseholdEnergy(HouseholdEnergy householdEnergy) throws Exception;
		public HouseholdEnergy updateHouseholdEnergy(HouseholdEnergy householdEnergy) throws Exception;
	
}