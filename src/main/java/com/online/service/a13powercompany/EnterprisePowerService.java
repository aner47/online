package com.online.service.a13powercompany;

import com.online.entity.online.a13powercompany.EnterprisePower;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 企业用电信息表服务接口
 *
 */
public interface EnterprisePowerService extends BaseService<EnterprisePower, Integer>,ExportService {
		
		public EnterprisePower saveEnterprisePower(EnterprisePower enterprisePower) throws Exception;
		public EnterprisePower updateEnterprisePower(EnterprisePower enterprisePower) throws Exception;
	
}