package com.online.service.a9agriculture;

import com.online.entity.online.a9agriculture.AgriculturalMachinery;
import com.online.service.BaseService;

/**
 * 农业机械信息服务接口
 *
 */
public interface AgriculturalMachineryService extends BaseService<AgriculturalMachinery, Integer> {
		
		public AgriculturalMachinery saveAgriculturalMachinery(AgriculturalMachinery agriculturalMachinery) throws Exception;
		public AgriculturalMachinery updateAgriculturalMachinery(AgriculturalMachinery agriculturalMachinery) throws Exception;
	
}