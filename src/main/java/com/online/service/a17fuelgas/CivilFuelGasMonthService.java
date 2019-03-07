package com.online.service.a17fuelgas;

import com.online.entity.online.a17fuelgas.CivilFuelGasMonth;
import com.online.service.BaseService;

/**
 * 民用燃气消耗分月统计服务接口
 *
 */
public interface CivilFuelGasMonthService extends BaseService<CivilFuelGasMonth, Integer> {
		
		public CivilFuelGasMonth saveCivilFuelGasMonth(CivilFuelGasMonth civilFuelGasMonth) throws Exception;
		public CivilFuelGasMonth updateCivilFuelGasMonth(CivilFuelGasMonth civilFuelGasMonth) throws Exception;
	
}