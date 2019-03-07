package com.online.service.a13powercompany;

import com.online.entity.online.a13powercompany.PowerPlantMonth;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 电厂发电量分月统计信息服务接口
 *
 */
public interface PowerPlantMonthService extends BaseService<PowerPlantMonth, Integer>,ExportService {
		
		public PowerPlantMonth savePowerPlantMonth(PowerPlantMonth powerPlantMonth) throws Exception;
		public PowerPlantMonth updatePowerPlantMonth(PowerPlantMonth powerPlantMonth) throws Exception;
	
}