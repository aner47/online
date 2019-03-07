package com.online.service.a17fuelgas;

import com.online.entity.online.a17fuelgas.CivilFuelGas;
import com.online.service.BaseService;

/**
 * 民用燃气消耗信息服务接口
 *
 */
public interface CivilFuelGasService extends BaseService<CivilFuelGas, Integer> {
		
		public CivilFuelGas saveCivilFuelGas(CivilFuelGas civilFuelGas) throws Exception;
		public CivilFuelGas updateCivilFuelGas(CivilFuelGas civilFuelGas) throws Exception;
	
}