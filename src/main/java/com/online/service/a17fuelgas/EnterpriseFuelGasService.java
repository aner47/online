package com.online.service.a17fuelgas;

import com.online.entity.online.a17fuelgas.EnterpriseFuelGas;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 工业企业燃气消耗信息服务接口
 *
 */
public interface EnterpriseFuelGasService extends BaseService<EnterpriseFuelGas, Integer>,ExportService {
		
		public EnterpriseFuelGas saveEnterpriseFuelGas(EnterpriseFuelGas enterpriseFuelGas) throws Exception;
		public EnterpriseFuelGas updateEnterpriseFuelGas(EnterpriseFuelGas enterpriseFuelGas) throws Exception;
	
}