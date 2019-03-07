package com.online.service.a17fuelgas;

import com.online.entity.online.a17fuelgas.EnterpriseFuelGasMonth;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 企业燃气消耗分月统计服务接口
 *
 */
public interface EnterpriseFuelGasMonthService extends BaseService<EnterpriseFuelGasMonth, Integer>,ExportService {
		
		public EnterpriseFuelGasMonth saveEnterpriseFuelGasMonth(EnterpriseFuelGasMonth enterpriseFuelGasMonth) throws Exception;
		public EnterpriseFuelGasMonth updateEnterpriseFuelGasMonth(EnterpriseFuelGasMonth enterpriseFuelGasMonth) throws Exception;
	
}